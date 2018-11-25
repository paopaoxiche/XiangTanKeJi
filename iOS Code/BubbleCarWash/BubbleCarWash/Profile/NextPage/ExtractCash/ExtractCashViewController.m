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

@interface ExtractCashViewController ()

@property (nonatomic, weak) IBOutlet UILabel *balanceLabel;
@property (nonatomic, weak) IBOutlet UITextField *extractCashTextField;
@property (nonatomic, strong) UIBarButtonItem *submitBarButtom;

@end

@implementation ExtractCashViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.title = @"提取现金";
    [self.navigationController.navigationBar setTintColor:[UIColor whiteColor]];
    self.navigationController.navigationBar.barTintColor = [UIColor rgbWithRed:248 green:157 blue:14];
    self.navigationController.navigationBar.subviews[0].subviews[0].hidden = YES;
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName : [UIColor whiteColor]}];
    self.balanceLabel.text = [NSString stringWithFormat:@"%.2f", [UserManager sharedInstance].carWashInfo.balance];
    _submitBarButtom = [[UIBarButtonItem alloc] initWithTitle:@"提交" style:UIBarButtonItemStylePlain target:self action:@selector(onSubmitBarButtonClicked)];
    self.navigationItem.rightBarButtonItem = _submitBarButtom;
    _submitBarButtom.enabled = NO;
    _extractCashTextField.enabled = NO;
    
    [UIApplication showBusyHUD];
    [[NetworkTools sharedInstance] obtainBalance:[UserManager sharedInstance].carWashInfo.washID success:^(NSDictionary *response, BOOL isSuccess) {
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
    
    [self.navigationController.navigationBar setTintColor:[UIColor blackColor]];
    self.navigationController.navigationBar.barTintColor = [UIColor whiteColor];
    self.navigationController.navigationBar.subviews[0].subviews[0].hidden = NO;
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName : [UIColor blackColor]}];
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
    [[NetworkTools sharedInstance] extractCash:[UserManager sharedInstance].carWashInfo.washID money:_extractCashTextField.text success:^(NSDictionary *response, BOOL isSuccess) {
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
