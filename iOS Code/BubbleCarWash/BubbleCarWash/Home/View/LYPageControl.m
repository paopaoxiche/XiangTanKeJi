//
//  LYPageControl.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/6.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "LYPageControl.h"

static CGFloat dotW = 14;
static CGFloat dotH = 3;
static CGFloat margin = 2;

@implementation LYPageControl

- (void)layoutSubviews {
    [super layoutSubviews];
    
    CGFloat kScreenW = [UIScreen mainScreen].bounds.size.width;
    CGFloat marginX = dotW + margin;
    CGFloat newW = (self.subviews.count - 1 ) * marginX;
    self.frame = CGRectMake(kScreenW * 0.5 - (newW + dotW) * 0.5, self.frame.origin.y, newW + dotW, self.frame.size.height);
    
    // 遍历subview，设置圆点frame
    for (int i = 0; i < [self.subviews count]; i++) {
        UIImageView *dot = [self.subviews objectAtIndex:i];
        [dot setFrame:CGRectMake(i * marginX, dot.frame.origin.y, dotW, dotH)];
    }
}

@end
