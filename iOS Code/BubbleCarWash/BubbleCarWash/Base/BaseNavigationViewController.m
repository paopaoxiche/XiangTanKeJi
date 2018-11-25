//
//  BaseNavigationViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/13.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "BaseNavigationViewController.h"

@interface BaseNavigationViewController ()

@end

@implementation BaseNavigationViewController

- (void)viewDidLoad {
    [super viewDidLoad];
}

- (void)pushViewController:(UIViewController *)viewController animated:(BOOL)animated {
    [super pushViewController:viewController animated:animated];
    [self setNavigationBarHidden:NO animated:YES];
}

@end
