//
//  EditNicknameViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "EditNicknameViewController.h"
#import "UserManager.h"
#import "UserInfoModel.h"
#import "NetworkTools.h"
#import "NSString+Category.h"
#import "FunctionMacro.h"
#import "UIApplication+HUD.h"

@interface EditNicknameViewController ()

@property (weak, nonatomic) IBOutlet UITextField *nicknameTextField;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *navigationHeightConstraint;

@end

@implementation EditNicknameViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.title = @"修改昵称";
    _nicknameTextField.text = [UserManager sharedInstance].userInfo.nickName;
    if (IS_IPHONE_X) {
        _navigationHeightConstraint.constant = 64 + 24;
    }
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [_nicknameTextField resignFirstResponder];
}

- (IBAction)onCancelBtnClicked:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (IBAction)onCompletionBtnClicked:(id)sender {
    if ([self isValidNickname]) {
        [self messageBox:@"昵称不能为空"];
        return;
    }
    
    [_nicknameTextField resignFirstResponder];
    
    [UIApplication showBusyHUD];
    // 保存修改
    [NetworkTools updateUserNickName:_nicknameTextField.text success:^(NSDictionary *response, BOOL isSuccess) {
        [UIApplication stopBusyHUD];
        [UserManager sharedInstance].isUpdateUserInfo = YES;
        [[UserManager sharedInstance] obtainUserInfo];
        // 提示，然后返回上级界面
        [self messageBox:@"修改昵称成功" handle:^{
            [self dismissViewControllerAnimated:YES completion:nil];
        }];
    } failed:^(NSError *error) {
        [UIApplication stopBusyHUD];
        // 提示
        [self messageBox:@"修改昵称失败，请稍后重试"];
    }];
}

- (BOOL)isValidNickname {
    return [NSString isBlackString:_nicknameTextField.text];
}

@end
