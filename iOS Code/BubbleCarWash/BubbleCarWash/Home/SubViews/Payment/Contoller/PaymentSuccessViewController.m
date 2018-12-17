//
//  PaymentSuccessViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/10.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "PaymentSuccessViewController.h"
#import "DiscussViewController.h"
#import "GlobalMethods.h"
#import "NetworkTools.h"
#import "UIApplication+HUD.h"

@interface PaymentSuccessViewController ()

@end

@implementation PaymentSuccessViewController

- (void)viewDidLoad {
    [super viewDidLoad];
}

- (IBAction)go2Evaluation:(id)sender {
    [UIApplication showBusyHUD];
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(3 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        [NetworkTools obtainComsumeStatus:[NSString stringWithFormat:@"%li", self.consumeID] success:^(NSDictionary *response, BOOL isSuccess) {
            [UIApplication stopBusyHUD];
            NSInteger data = [response objectForKey:@"data"];
            if (data == 2) {
                DiscussViewController *discussVC = (DiscussViewController *)[GlobalMethods viewControllerWithBuddleName:@"Profile"
                                                                                                           vcIdentifier:@"DiscussVC"];
                discussVC.consumeID = self.consumeID;
                [self.navigationController pushViewController:discussVC animated:YES];
            } else {
                NSString *hint = (data == 3) ? @"支付被取消，请确认已支付" : @"请求失败，请稍后再试";
                [self messageBox:hint];
            }
        } failed:^(NSError *error) {
            [UIApplication stopBusyHUD];
            [self messageBox:@"请求失败，请稍后再试"];
        }];
    });
}

@end
