//
//  CustomerServicerViewController.m
//  BubbleCarWash
//
//  Created by Rachel on 2018/9/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CustomerServicerViewController.h"
#import "FunctionMacro.h"
#import "UILabel+CreateLabel.h"
#import "NSString+Category.h"
#import "NetworkTools.h"
#import "UIApplication+HUD.h"

#define NUMBER @"1234567890"

static const NSUInteger kContactMaxLenght = 12;
static const NSUInteger kContactMinLenght = 4;

@interface CustomerServicerViewController () <UITextFieldDelegate, UITextViewDelegate>

@property (weak, nonatomic) IBOutlet UITextView *textView;
@property (weak, nonatomic) IBOutlet UILabel *placeholderLabel;
@property (weak, nonatomic) IBOutlet UILabel *lengthLabel;
@property (weak, nonatomic) IBOutlet UITextField *contactTextFiled;
@property (nonatomic, strong) UIBarButtonItem *sendBtn;

@end

@implementation CustomerServicerViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.view.backgroundColor = RGBColor(237, 240, 244);
    self.title = @"反馈";
    
    _sendBtn = [[UIBarButtonItem alloc] initWithTitle:@"发送" style:UIBarButtonItemStylePlain target:self  action:@selector(send)];
    [_sendBtn setEnabled:NO];
    self.navigationItem.rightBarButtonItem = _sendBtn;
    
    [_contactTextFiled addTarget:self action:@selector(textChange) forControlEvents:UIControlEventEditingChanged];
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(hideKeyboard)];
    [self.view addGestureRecognizer:tap];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:NO animated:YES];
}

#pragma mark -

- (void)send {
//    CHECK_INTERNET();
    
    if (![self isValidTextFiledFormat]) {
        // 联系方式格式不对进行提示
        return;
    }
    
    [_sendBtn setEnabled:NO];
    [self hideKeyboard];
    // 开始loading
    [UIApplication showBusyHUD];
    FeedBackParam *param = [[FeedBackParam alloc] init];
    param.content = _textView.text;
    param.contactInformation = _contactTextFiled.text;
    [NetworkTools submitFeedBackInfo:param success:^(NSDictionary *response, BOOL isSuccess) {
        [UIApplication stopBusyHUD];
        if ([response[@"code"] integerValue] == 200) {
            [self messageBox:@"反馈成功" handle:^{
                [self.navigationController popViewControllerAnimated:YES];
            }];
        } else {
            [self messageBox:@"反馈失败，请稍后重试"];
        }
    } failed:^(NSError *error) {
        [UIApplication stopBusyHUD];
        [self messageBox:@"反馈失败，请稍后重试"];
    }];
}

/**
 *  判断联系方式格式是否正确
 */
- (BOOL)isValidTextFiledFormat {
    if (_contactTextFiled.hasText && _contactTextFiled.text.length < kContactMinLenght) {
        return NO;
    }
    
    return YES;
}

- (void)retryAlert {
    // 停止loading
//    [self stopAnyLoading];
    
    UIAlertController *alert  = [UIAlertController alertControllerWithTitle:nil message:@"反馈发送失败，请重试" preferredStyle:UIAlertControllerStyleAlert];
    UIAlertAction * cancel = [UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleCancel handler:^(UIAlertAction * _Nonnull action) {
        [self.navigationController popViewControllerAnimated:YES];
    }];
    UIAlertAction *retry = [UIAlertAction actionWithTitle:@"重试" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
        [self send];
    }];
    [alert addAction:cancel];
    [alert addAction:retry];
    [self presentViewController:alert animated:YES completion:nil];
}

- (void)hideKeyboard {
    [_textView resignFirstResponder];
    [_contactTextFiled resignFirstResponder];
}

- (void)textChange {
    NSString *text = _contactTextFiled.text;
    if (text.length > kContactMaxLenght) {
        _contactTextFiled.text = [text substringToIndex:kContactMaxLenght];
    }
}

#pragma mark - UITextView Delegete

- (void)textViewDidChange:(UITextView *)textView {
    _placeholderLabel.hidden = textView.hasText;
    _sendBtn.enabled = (textView.hasText && ![NSString isBlackString:_textView.text]);
    if ([textView.text length] >= 400) {
        textView.text = [textView.text substringToIndex:400];
    }
    
    NSUInteger existTextNum = [textView.text length];
    _lengthLabel.text = [NSString stringWithFormat:@"%lu/400", (unsigned long)existTextNum];
    [textView.undoManager disableUndoRegistration];
}

- (BOOL)textView:(UITextView *)textView shouldChangeTextInRange:(NSRange)range replacementText:(NSString *)text {
    [textView.undoManager disableUndoRegistration];
    return range.location >= 400 ? NO : YES;
}

#pragma mark - UITextFiled Delegete

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    // 限制只能输入数字
    [_contactTextFiled.undoManager disableUndoRegistration];
    NSCharacterSet *cs = [[NSCharacterSet characterSetWithCharactersInString:NUMBER] invertedSet];
    NSString *filtered = [[string componentsSeparatedByCharactersInSet:cs] componentsJoinedByString:@""];
    
    return [string isEqualToString:filtered];
}

@end
