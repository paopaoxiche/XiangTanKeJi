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

@interface PaymentSuccessViewController ()

@end

@implementation PaymentSuccessViewController

- (void)viewDidLoad {
    [super viewDidLoad];
}

- (IBAction)go2Evaluation:(id)sender {
    DiscussViewController *discussVC = (DiscussViewController *)[GlobalMethods viewControllerWithBuddleName:@"Profile"
                                                                      vcIdentifier:@"DiscussVC"];
    discussVC.consumeID = self.consumeID;
    [self.navigationController pushViewController:discussVC animated:YES];
}

@end
