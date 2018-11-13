//
//  CreateProductViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CreateProductViewController.h"
#import "NSString+Category.h"
#import "NetworkTools.h"
#import "UIImage+ScaleImage.h"
#import "UIColor+Category.h"
#import "HomeModel.h"
#import <SDWebImage/UIImageView+WebCache.h>
#import "GlobalMethods.h"

@interface CreateProductViewController () <UITextViewDelegate, UIImagePickerControllerDelegate, UINavigationControllerDelegate>

@property (nonatomic, weak) IBOutlet UITextField *nameTextField;
@property (nonatomic, weak) IBOutlet UITextField *currentPriceTextField;
@property (nonatomic, weak) IBOutlet UITextField *originalPriceTextField;
@property (nonatomic, weak) IBOutlet UITextView *descTextView;
@property (nonatomic, weak) IBOutlet UILabel *placeholderLabel;
@property (nonatomic, weak) IBOutlet UIImageView *imageView;
@property (nonatomic, weak) IBOutlet UIView *uploadView;
@property (nonatomic, strong) UIImagePickerController *imagePicker;

@end

@implementation CreateProductViewController

- (void)awakeFromNib {
    [super awakeFromNib];
    
    self.model = nil;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self setupUI];
}

- (void)createProduct {
    if ([NSString isBlackString:_nameTextField.text]) {
        [self messageBox:@"商品名不能为空"];
        return;
    }
    
    if ([NSString isBlackString:_currentPriceTextField.text]) {
        [self messageBox:@"商品现价不能为空"];
        return;
    }
    
    if ([NSString isBlackString:_originalPriceTextField.text]) {
        [self messageBox:@"商品原价不能为空"];
        return;
    }
    
    if (![GlobalMethods isFloat:_currentPriceTextField.text] || ![GlobalMethods isFloat:_originalPriceTextField.text]) {
        [self messageBox:@"请输入有效的价格"];
        return;
    }
    
    if ([NSString isBlackString:_descTextView.text]) {
        [self messageBox:@"商品描述不能为空"];
        return;
    }
    
    if (!_imageView.image) {
        [self messageBox:@"请上传商品图片"];
        return;
    }
    
    CommodityParam *param = [[CommodityParam alloc] init];
    param.name = _nameTextField.text;
    param.currentPrice = _currentPriceTextField.text;
    param.originalPrice = _originalPriceTextField.text;
    param.describe = _descTextView.text;
    param.commodityImg = _imageView.image;
    
    BOOL isAdd = _model == nil;
    if (!isAdd) {
        param.commodityID = [NSString stringWithFormat:@"%li", _model.dataID];
    }
    
    [[NetworkTools sharedInstance] addOrModifyCommodity:param success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200) {
            [self messageBox:isAdd ? @"新增商品成功" : @"修改商品成功" handle:^{
                [self.navigationController popViewControllerAnimated:YES];
            }];
        } else {
            [self messageBox:isAdd ? @"新增商品失败，请重试" : @"修改商品失败，请重试"];
        }
    } failure:^(NSError *error) {
        [self messageBox:isAdd ? @"新增商品失败，请重试" : @"修改商品失败，请重试"];
    }];
}

- (void)hideKeyboard {
    [_nameTextField resignFirstResponder];
    [_currentPriceTextField resignFirstResponder];
    [_originalPriceTextField resignFirstResponder];
    [_descTextView resignFirstResponder];
}

- (void)setupUI {
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithTitle:@"确定" style:UIBarButtonItemStylePlain target:self action:@selector(createProduct)];
    
    UITapGestureRecognizer *singleGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(hideKeyboard)];
    [self.view addGestureRecognizer:singleGesture];
    
    UITapGestureRecognizer *uploadGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(presentImagePicker)];
    [_uploadView addGestureRecognizer:uploadGesture];
    _uploadView.layer.borderWidth = 1.0;
    _uploadView.layer.borderColor = [UIColor rgbWithRed:183 green:196 blue:203].CGColor;
    
    [_nameTextField addTarget:self action:@selector(textFieldDidChange) forControlEvents:UIControlEventEditingChanged];
    
    _imagePicker = [[UIImagePickerController alloc] init];
    _imagePicker.delegate = self;
    
    if (_model) {
        _uploadView.hidden = YES;
        _imageView.hidden = NO;
        _nameTextField.text = _model.commodityName;
        _currentPriceTextField.text = [NSString stringWithFormat:@"%.2f", _model.currentPrice];
        _originalPriceTextField.text = [NSString stringWithFormat:@"%.2f", _model.originPrice];
        _descTextView.text = _model.describe;
        
        if (_model.imageUrl && ![_model.imageUrl isEqualToString:@""]) {
            [_imageView sd_setImageWithURL:[NSURL URLWithString:_model.imageUrl]];
        } else {
            _imageView.image = [UIImage imageNamed:@"CarWashAvatar"];
        }
    } else {
        _uploadView.hidden = NO;
        _imageView.hidden = YES;
    }
}

- (void)textFieldDidChange {
    if ([_nameTextField.text length] > 10) {
        _nameTextField.text = [_nameTextField.text substringToIndex:10];
    }
}

- (void)textViewDidChange:(UITextView *)textView {
    _placeholderLabel.hidden = textView.hasText;
    if ([textView.text length] > 30) {
        textView.text = [textView.text substringToIndex:30];
    }
}

#pragma mark - UIImagePickerController

- (BOOL)isValidCamera {
    return [UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypeCamera];
}

- (BOOL)isValidPhotoLibrary {
    return [UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypePhotoLibrary];
}

- (void)presentImagePicker {
    UIAlertController *actionSheet = [UIAlertController alertControllerWithTitle:nil message:nil preferredStyle:UIAlertControllerStyleActionSheet];
    UIAlertAction *photoAction = [UIAlertAction actionWithTitle:@"从相册选择" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
        if (![self isValidPhotoLibrary]) {
            [self messageBox:@"不能打开相册"];
        }
        
        self.imagePicker.sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
        [self presentViewController:self.imagePicker animated:YES completion:nil];
    }];
    UIAlertAction *cameraAction = [UIAlertAction actionWithTitle:@"拍摄" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
        if (![self isValidCamera]) {
            [self messageBox:@"不能打开相机"];
        }
        
        self.imagePicker.sourceType = UIImagePickerControllerSourceTypeCamera;
        [self presentViewController:self.imagePicker animated:YES completion:nil];
    }];
    UIAlertAction *cancel = [UIAlertAction actionWithTitle:@"" style:UIAlertActionStyleCancel handler:nil];
    
    [actionSheet addAction:photoAction];
    [actionSheet addAction:cameraAction];
    [actionSheet addAction:cancel];
    
    [self presentViewController:actionSheet animated:YES completion:nil];
}

/**
 *  选中相片之后调用
 */
- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary<NSString *,id> *)info {
    UIImage *image = [[info objectForKey:UIImagePickerControllerOriginalImage] imageWithScale:320];
    self.imageView.image = image;
    self.imageView.hidden = NO;
    self.uploadView.hidden = YES;
    [picker dismissViewControllerAnimated:YES completion:nil];
}

@end
