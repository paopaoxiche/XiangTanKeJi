//
//  LoginViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/6/25.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "LoginViewController.h"
#import "GlobalMethods.h"
#import "NSString+Category.h"
#import "UIColor+Category.h"
#import "NetworkTools.h"
#import "UserInfoModel.h"
#import "UserManager.h"
#import "CertificationViewController.h"

static const NSInteger kSeconds = 120;

@interface LoginViewController () <UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet UITextField *phoneNumberTextField;
@property (weak, nonatomic) IBOutlet UITextField *verificationCodeTextField;
@property (weak, nonatomic) IBOutlet UIButton *verificationCodeButton;
@property (weak, nonatomic) IBOutlet UIButton *ownerSelectBtn;
@property (weak, nonatomic) IBOutlet UIButton *carWashSelectBtn;
@property (weak, nonatomic) IBOutlet UIButton *loginBtn;
@property (weak, nonatomic) IBOutlet UIButton *serviceTermsBtn;
@property (weak, nonatomic) IBOutlet UIView *backView;
@property (assign, nonatomic) NSUInteger type;
@property (strong, nonatomic) dispatch_source_t timer;
@property (assign, nonatomic) NSUInteger seconds;

@end

@implementation LoginViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _phoneNumberTextField.delegate = self;
    _verificationCodeTextField.delegate = self;
    _type = -1;
    _seconds = kSeconds;
    _verificationCodeButton.layer.borderColor = [UIColor rgbWithRed:248 green:155 blue:10].CGColor;
    
    UITapGestureRecognizer *single = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(backToSuperVC)];
    [_backView addGestureRecognizer:single];
    
    NSString *phone = [UserManager sharedInstance].userInfo.phoneNumber;
    if (phone && ![phone isEqualToString:@""]) {
        self.phoneNumberTextField.text = phone;
    }
}

- (void)backToSuperVC {
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (IBAction)onVerificationCodeBtnClicked:(id)sender {
    [self checkPhoneNumber];
    
    [[NetworkTools sharedInstance] obtainVerificationCodeWithPhoneNumber:_phoneNumberTextField.text success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (isSuccess && code == 200) {
            // 倒计时
            [self startCountingDown];
        } else {
            [self messageBox:@"获取验证码失败，稍后再试"];
        }
    } failed:^(NSError *error) {
        [self messageBox:@"获取验证码失败，稍后再试"];
    }];
}

- (IBAction)onSelectTypeBtnClicked:(id)sender {
    UIButton *btn = (UIButton *)sender;
    [_ownerSelectBtn setImage:[UIImage imageNamed:(btn.tag == 10001 ? @"SingleSelection_Selected" : @"SingleSelection_Normal")]
                     forState:UIControlStateNormal];
    [_carWashSelectBtn setImage:[UIImage imageNamed:(btn.tag == 10002 ? @"SingleSelection_Selected" : @"SingleSelection_Normal")]
                       forState:UIControlStateNormal];
    _type = btn.tag == 10001 ? 0 : 1;
}

- (IBAction)onLoginBtnClicked:(id)sender {
    [self checkPhoneNumber];
    [self checkVerificationCode];

    if (_type == -1) {
        [self messageBox:@"请选择登录类型"];
    }
    
    [[NetworkTools sharedInstance] loginWithPhoneNumber:_phoneNumberTextField.text code:[_verificationCodeTextField.text integerValue] userType:_type success:^(NSDictionary *response, BOOL isSuccess) {
        long code = [response[@"code"] longValue];
        if (code == 200) {
            [self stopCountingDown];
            // 登录成功
            UserInfoModel *userInfo = [[UserInfoModel alloc] initWithDic:response[@"data"]];
            [UserManager sharedInstance].userInfo = userInfo;
            [UserManager sharedInstance].isLogin = YES;
            [[UserManager sharedInstance] savaUserInfoWithPassword:self.verificationCodeTextField.text];
            if (self.type == 1 && ![UserManager sharedInstance].carWashInfo) {
                [UserManager sharedInstance].isUpdateUserInfo = YES;
                [[UserManager sharedInstance] obtainUserInfo];
            }
            UIViewController *mainVC = [GlobalMethods viewControllerWithBuddleName:@"Main" vcIdentifier:@"MainVC"];
            [self presentViewController:mainVC animated:YES completion:nil];
        } else if (code == 10008 && self.type == 1) {
            [self stopCountingDown];
            // 洗车场认证
            CertificationViewController *vc = (CertificationViewController *)[GlobalMethods viewControllerWithBuddleName:@"Certification" vcIdentifier:@"CertificationVC"];
            vc.state = CertificationStateAdd;
            UserInfoModel *info = [[UserInfoModel alloc] init];
            info.type = UserTypeCarWash;
            info.phoneNumber = self.phoneNumberTextField.text;
            info.code = self.verificationCodeTextField.text;
            [UserManager sharedInstance].userInfo = info;
            [self presentViewController:vc animated:YES completion:nil];
        } else {
            NSString *hint = @"";
            switch (code) {
                case 10001:
                    hint = @"用户已存在";
                    break;
                case 10002:
                    hint = @"登录手机号错误";
                    break;
                case 10003:
                    hint = @"登录验证码错误";
                    break;
                case 10004:
                    hint = @"用户不存在";
                    break;
                case 10005:
                    hint = @"用户类型错误";
                    break;
                case 10007:
                    hint = @"短信验证码错误";
                    break;
                case 10008:
                    hint = @"当前用户未注册洗车场";
                    break;
                case 10009:
                    hint = @"当前用户洗车场正在审核中";
                    break;
                case 10011:
                    hint = @"密码错误";
                    break;
                default:
                    break;
            }
            
            [self messageBox:hint];
        }
    } failed:^(NSError *error) {
        [UserManager sharedInstance].isLogin = NO;
    }];
}

- (IBAction)onServiceTermsBtnClicked:(id)sender {
    
}

- (void)startCountingDown {
    _seconds = kSeconds;
    _verificationCodeButton.layer.borderColor = [UIColor rgbWithRed:217 green:227 blue:255].CGColor;
    _verificationCodeButton.backgroundColor = [UIColor rgbWithRed:217 green:227 blue:232];
    [_verificationCodeButton setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
    _timer = dispatch_source_create(DISPATCH_SOURCE_TYPE_TIMER, 0, 0, queue);
    dispatch_resume(_timer);
    dispatch_source_set_timer(_timer, dispatch_time(DISPATCH_TIME_NOW, 0), 1.0 * NSEC_PER_SEC, 0);
    dispatch_source_set_event_handler(_timer, ^{
        dispatch_async(dispatch_get_main_queue(), ^{
            if (self.seconds <= 0) {
                [self stopCountingDown];
            } else {
                [self.verificationCodeButton setTitle:[NSString stringWithFormat:@"%lu s", self.seconds]
                                             forState:UIControlStateNormal];
            }
            
            self.seconds--;
        });
    });
}

- (void)stopCountingDown {
    if (_timer) {
        UIColor *color = [UIColor rgbWithRed:248 green:155 blue:10];
        _verificationCodeButton.layer.borderColor = color.CGColor;
        _verificationCodeButton.backgroundColor = [UIColor whiteColor];
        [_verificationCodeButton setTitleColor:color forState:UIControlStateNormal];
        [_verificationCodeButton setTitle:@"获取验证码" forState:UIControlStateNormal];
        // 取消timer，必须重建才能运行timer
        dispatch_source_cancel(_timer);
        _timer = nil;
    }
}

- (void)checkPhoneNumber {
    NSString *phoneNumberStr = _phoneNumberTextField.text;
    if (phoneNumberStr.length <= 0 || [NSString isBlackString:phoneNumberStr]) {
        [self messageBox:@"手机号不能为空"];
        return;
    }
}

- (void)checkVerificationCode {
    NSString *verificationCodeStr = _verificationCodeTextField.text;
    if (verificationCodeStr.length <= 0 || [NSString isBlackString:verificationCodeStr]) {
        [self messageBox:@"验证码不能为空"];
        return;
    }
}

#pragma mark - UITextFiledDelegate

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    if (textField == _phoneNumberTextField) {
        // 限制只能输入数字
        [_phoneNumberTextField.undoManager disableUndoRegistration];
        NSCharacterSet *cs = [[NSCharacterSet characterSetWithCharactersInString:@"1234567890"] invertedSet];
        NSString *filtered = [[string componentsSeparatedByCharactersInSet:cs] componentsJoinedByString:@""];
        
        return [string isEqualToString:filtered] && [_phoneNumberTextField.text length] < 11;
    }
    
    return YES;
}

@end
