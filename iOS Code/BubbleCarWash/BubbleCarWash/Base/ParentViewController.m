//
//  ParentViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/19.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "ParentViewController.h"

@interface ParentViewController ()

@end

@implementation ParentViewController

- (void)viewDidLoad {
    [super viewDidLoad];
}

- (void)dealloc {
    NSLog(@"%@:delloc", [self.class description]);
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


@end