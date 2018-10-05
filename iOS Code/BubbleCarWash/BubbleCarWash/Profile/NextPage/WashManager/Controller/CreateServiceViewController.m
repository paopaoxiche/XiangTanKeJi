//
//  CreateServiceViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/24.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CreateServiceViewController.h"
#import "NSString+Category.h"
#import "CarWashServiceModel.h"
#import "NetworkTools.h"
#import "UserManager.h"
#import "CarWashInfoModel.h"

@interface CreateServiceViewController () <UITextViewDelegate>

@property (weak, nonatomic) IBOutlet UITextField *nameTextField;
@property (weak, nonatomic) IBOutlet UITextField *priceTextField;
@property (weak, nonatomic) IBOutlet UITextView *textView;
@property (weak, nonatomic) IBOutlet UILabel *placeholderLabel;

@end

@implementation CreateServiceViewController

- (void)awakeFromNib {
    [super awakeFromNib];
    
    self.model = nil;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithTitle:@"确定" style:UIBarButtonItemStylePlain target:self action:@selector(createService)];
    
    UITapGestureRecognizer *singleGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(hideKeyboard)];
    [self.view addGestureRecognizer:singleGesture];
    
    [_nameTextField addTarget:self action:@selector(textFieldDidChange) forControlEvents:UIControlEventEditingChanged];
    
    if (_model) {
        _nameTextField.text = _model.carWashName;
        _priceTextField.text = [NSString stringWithFormat:@"%.2f", _model.price];
        _textView.text = _model.desc;
    }
}

- (void)createService {
    if ([NSString isBlackString:_nameTextField.text]) {
        [self messageBox:@"服务名不能为空"];
        return;
    }
    
    if ([NSString isBlackString:_priceTextField.text]) {
        [self messageBox:@"服务价格不能为空"];
        return;
    }
    
    if ([NSString isBlackString:_textView.text]) {
        [self messageBox:@"服务描述不能为空"];
        return;
    }
    
    ServiceParam *param = [[ServiceParam alloc] init];
    param.name = _nameTextField.text;
    param.describe = _textView.text;
    param.price = _priceTextField.text;
    param.washId = [NSNumber numberWithInteger:[UserManager sharedInstance].carWashInfo.washID];
    
    BOOL isAdd = _model == nil;
    if (!isAdd) {
        param.serviceId = [NSNumber numberWithInteger:_model.dataID];
    }
    
    [[NetworkTools sharedInstance] addOrModifyService:param success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200) {
            [self messageBox:isAdd ? @"新增服务成功" : @"修改服务成功" handle:^{
                [self.navigationController popViewControllerAnimated:YES];
            }];
        } else if (code == 40001) {
            [self messageBox:@"当前用户未拥有该洗车场"];
        } else {
            [self messageBox:isAdd ? @"新增服务失败，请重试" : @"修改服务失败，请重试"];
        }
    } failure:^(NSError *error) {
        [self messageBox:isAdd ? @"新增服务失败，请重试" : @"修改服务失败，请重试"];
    }];
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

- (void)hideKeyboard {
    [_nameTextField resignFirstResponder];
    [_priceTextField resignFirstResponder];
    [_textView resignFirstResponder];
}

@end
