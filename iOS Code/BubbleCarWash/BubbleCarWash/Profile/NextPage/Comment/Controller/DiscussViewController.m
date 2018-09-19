//
//  DiscussViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "DiscussViewController.h"
#import "NetworkTools.h"
#import "NSString+Category.h"

@interface DiscussViewController ()

@property (weak, nonatomic) IBOutlet UILabel *placeholderLabel;
@property (weak, nonatomic) IBOutlet UITextView *discussTextView;

@end

@implementation DiscussViewController

- (void)viewDidLoad {
    [super viewDidLoad];
}

- (IBAction)onStartBtnClicked:(id)sender {
    UIButton *startButton = (UIButton *)sender;
    NSInteger tag = startButton.tag;
    
    for (int i = 2001; i <= tag; i++) {
        UIButton *start = (UIButton *)[self.view viewWithTag:i];
        [start setImage:[UIImage imageNamed:@"DiscussHighlighted"] forState:UIControlStateNormal];
        start.selected = YES;
    }
    
    for (int i = 2005; i > tag; i--) {
        UIButton *start = (UIButton *)[self.view viewWithTag:i];
        [start setImage:[UIImage imageNamed:@"DiscussNormal"] forState:UIControlStateNormal];
        start.selected = NO;
    }
}

- (IBAction)onCommitBtnClicked:(id)sender {
    BOOL isStart = NO;
    NSInteger grade = 0;
    for (int i = 2001; i <= 2005; i++) {
        UIButton *start = (UIButton *)[self.view viewWithTag:i];
        if (start.isSelected) {
            isStart = YES;
            grade = i;
        } // if
    } // for
    
    if (!isStart) {
        [self messageBox:@"请选择星级!"];
        return;
    }
    
    NSString *content = _discussTextView.text;
    if ([NSString isBlackString:content]) {
        [self messageBox:@"评分内容不能为空!"];
        return;
    }
    
    [[NetworkTools sharedInstance] submitEvaluate:_consumeID grade:grade - 2000 content:content success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200) {
            [self messageBox:@"评价成功" handle:^{
                [self.navigationController popToRootViewControllerAnimated:YES];
            }];
        } else {
            [self messageBox:@"评价失败，请稍后重试!"];
        }
    } failed:^(NSError *error) {
        [self messageBox:@"评价失败，请稍后重试!"];
    }];
}

@end
