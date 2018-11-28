//
//  ExtractCashViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/10/7.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "ExtractCashViewController.h"
#import "NetworkTools.h"
#import "UserManager.h"
#import "CarWashInfoModel.h"
#import "GlobalMethods.h"
#import "UIApplication+HUD.h"
#import "UIColor+Category.h"
#import "FunctionMacro.h"

@interface ExtractCashViewController ()

@property (nonatomic, weak) IBOutlet UILabel *balanceLabel;
@property (nonatomic, weak) IBOutlet UITextField *extractCashTextField;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *topConstraint;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *balanceTopConstraint;
@property (nonatomic, strong) UIBarButtonItem *submitBarButtom;

@end

@implementation ExtractCashViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.title = @"提取现金";
    
    if ([GlobalMethods isiPhoneX]) {
        _topConstraint.constant = 200;
        _balanceTopConstraint.constant = 86;
    }
    [self.navigationController.navigationBar setBackgroundImage:[[UIImage alloc] init] forBarMetrics:UIBarMetricsDefault];
    [self.navigationController.navigationBar setShadowImage:[[UIImage alloc] init]];
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName:[UIColor whiteColor]}];
    [self.navigationController.navigationBar setTintColor:[UIColor whiteColor]];
    self.navigationController.navigationBar.backgroundColor= [UIColor clearColor];
    
    self.balanceLabel.text = [NSString stringWithFormat:@"%.2f", [UserManager sharedInstance].carWashInfo.balance];
    _submitBarButtom = [[UIBarButtonItem alloc] initWithTitle:@"提交" style:UIBarButtonItemStylePlain target:self action:@selector(onSubmitBarButtonClicked)];
    self.navigationItem.rightBarButtonItem = _submitBarButtom;
    _submitBarButtom.enabled = NO;
    _extractCashTextField.enabled = NO;
    
    [UIApplication showBusyHUD];
    [NetworkTools obtainBalance:[UserManager sharedInstance].carWashInfo.washID success:^(NSDictionary *response, BOOL isSuccess) {
        [UIApplication stopBusyHUD];
        NSInteger code = [response[@"code"] integerValue];
        if (code == 200) {
            [UserManager sharedInstance].carWashInfo.balance = [response[@"data"] floatValue];
            self.balanceLabel.text = [NSString stringWithFormat:@"%.2f", [UserManager sharedInstance].carWashInfo.balance];
            if ([UserManager sharedInstance].carWashInfo.balance > 0) {
                self.submitBarButtom.enabled = YES;
                self.extractCashTextField.enabled = YES;
            }
        }
    } failure:^(NSError *error) {
        [UIApplication stopBusyHUD];
    }];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:NO animated:YES];
}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    
    // 不透明
    [self.navigationController.navigationBar setBackgroundImage:nil forBarMetrics:0];
    [self.navigationController.navigationBar setShadowImage:nil];
    [self.navigationController.navigationBar setTintColor:[UIColor blackColor]];
    self.navigationController.navigationBar.barTintColor = [UIColor whiteColor];
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName : [UIColor blackColor]}];
    self.navigationController.navigationBar.backgroundColor= [UIColor whiteColor];
}

- (void)onSubmitBarButtonClicked {
    if (![GlobalMethods isFloat:_extractCashTextField.text]) {
        [self messageBox:@"请输入有效的提取金额"];
        return;
    }
    
    if ([_extractCashTextField.text floatValue] > [UserManager sharedInstance].carWashInfo.balance) {
        [self messageBox:@"输入金额大于余额"];
        return;
    }
    
    [UIApplication showBusyHUD];
    [NetworkTools extractCash:[UserManager sharedInstance].carWashInfo.washID money:_extractCashTextField.text success:^(NSDictionary *response, BOOL isSuccess) {
        [UIApplication stopBusyHUD];
        NSInteger code = [response[@"code"] integerValue];
        if (code == 200) {
            [self messageBox:@"提取成功" handle:^{
                [self dismissViewControllerAnimated:YES completion:nil];
            }];
        } else {
            [self messageBox:@"提取失败，请重试"];
        }
    } failure:^(NSError *error) {
        [UIApplication stopBusyHUD];
        [self messageBox:@"提取失败，请重试"];
    }];
}

@end
