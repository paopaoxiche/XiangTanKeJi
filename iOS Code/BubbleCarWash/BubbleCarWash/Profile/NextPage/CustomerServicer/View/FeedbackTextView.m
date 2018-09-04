//
//  FeedbackTextView.m
//  BubbleCarWash
//
//  Created by Rachel on 2018/9/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "FeedbackTextView.h"
#import "FunctionMacro.h"

@implementation FeedbackTextView

- (instancetype)init
{
    self = [super init];
    [self customInit];
    
    return self;
}
- (instancetype)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    [self customInit];
    
    return self;
}

- (instancetype)initWithCoder:(NSCoder *)aDecoder {
    self = [super initWithCoder:aDecoder];
    [self customInit];
    
    return self;
}

- (void)customInit {
    _textView = [[UITextView alloc] initWithFrame:CGRectMake(0, 0, self.frame.size.width, self.frame.size.height)];
    _textView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
    [self addSubview:_textView];
    
    _placeholder_lab = [[LYEdgeInsetsLabel alloc] initWithEdgeInsets:UIEdgeInsetsMake(10, 12, 10, 12)];
    _placeholder_lab.font = Font(18);
    _placeholder_lab.numberOfLines = 0;
    _placeholder_lab.lineBreakMode = NSLineBreakByCharWrapping;
    _placeholder_lab.textColor = RGBColor(199, 204, 209);
    _placeholder_lab.text = @"请输入您的意见或建议(必填)";
    _placeholder_lab.backgroundColor = [UIColor clearColor];
    [self addSubview:_placeholder_lab];
    
    // 布局占位符
    _placeholder_lab.translatesAutoresizingMaskIntoConstraints = NO;
    NSDictionary *view = @{@"placeholder_lab": _placeholder_lab};
    [self addConstraints:[NSLayoutConstraint constraintsWithVisualFormat:@"H:|-0-[placeholder_lab]-0-|"
                                                                 options:0
                                                                 metrics:nil
                                                                   views:view]];
    [self addConstraints:[NSLayoutConstraint constraintsWithVisualFormat:@"V:|-0-[placeholder_lab]"
                                                                 options:0
                                                                 metrics:nil
                                                                   views:view]];
}

@end
