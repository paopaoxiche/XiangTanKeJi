//
//  LYEdgeInsetsLabel.m
//  BubbleCarWash
//
//  Created by Rachel on 2018/9/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "LYEdgeInsetsLabel.h"

@interface LYEdgeInsetsLabel()

@property (assign, nonatomic) UIEdgeInsets edgeInsets;

@end

@implementation LYEdgeInsetsLabel

- (instancetype)initWithEdgeInsets:(UIEdgeInsets)edgeInsets {
    return [self initWithFrame:CGRectZero edgeInsets:edgeInsets];
}

- (instancetype)initWithFrame:(CGRect)frame edgeInsets:(UIEdgeInsets)edgeInsets {
    self = [super initWithFrame:frame];
    if (self) {
        self.edgeInsets = edgeInsets;
    }
    
    return self;
}

- (CGSize)intrinsicContentSize {
    // 计算包含edgeInsets的宽高
    CGSize originSize = [super intrinsicContentSize];
    CGFloat newWidth = originSize.width + self.edgeInsets.left + self.edgeInsets.right;
    CGFloat newHeight = originSize.height + self.edgeInsets.top + self.edgeInsets.bottom;
    
    return CGSizeMake(newWidth, newHeight);
}

- (CGRect)textRectForBounds:(CGRect)bounds limitedToNumberOfLines:(NSInteger)numberOfLines {
    return [super textRectForBounds:UIEdgeInsetsInsetRect(bounds, self.edgeInsets)
             limitedToNumberOfLines:numberOfLines];
}

- (void)drawTextInRect:(CGRect)rect {
    [super drawTextInRect:UIEdgeInsetsInsetRect(rect, self.edgeInsets)];
}

@end
