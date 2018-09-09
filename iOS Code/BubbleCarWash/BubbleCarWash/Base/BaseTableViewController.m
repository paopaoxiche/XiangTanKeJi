//
//  BaseTableViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/9.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "BaseTableViewController.h"

@interface BaseTableViewController ()

@end

@implementation BaseTableViewController

- (void)viewDidLoad {
    [super viewDidLoad];
}

- (void)messageBox:(NSString *)lpszMessage {
    UIAlertController *alert = [UIAlertController alertControllerWithTitle:@"提示"
                                                                   message:lpszMessage
                                                            preferredStyle:UIAlertControllerStyleAlert];
    UIAlertAction *iKnow = [UIAlertAction actionWithTitle:@"我知道了"
                                                    style:UIAlertActionStyleCancel
                                                  handler:nil];
    [alert addAction:iKnow];
    [self presentViewController:alert animated:YES completion:nil];
}

- (void)messageBox:(NSString *)lpszMessage handle:(ActionHandle)handle {
    UIAlertController *alert = [UIAlertController alertControllerWithTitle:@"提示"
                                                                   message:lpszMessage
                                                            preferredStyle:UIAlertControllerStyleAlert];
    UIAlertAction *iKnow = [UIAlertAction actionWithTitle:@"我知道了"
                                                    style:UIAlertActionStyleCancel
                                                  handler:^(UIAlertAction * _Nonnull action) { handle(); }];
    [alert addAction:iKnow];
    [self presentViewController:alert animated:YES completion:nil];
}

@end
