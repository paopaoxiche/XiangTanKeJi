//
//  UILabel+CreateLabel.m
//  BubbleCarWash
//
//  Created by Rachel on 2018/9/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "UILabel+CreateLabel.h"

@implementation UILabel (CreateLabel)

+ (instancetype)createLabelWithFontSize:(CGFloat)fontSize textColor:(UIColor *)color {
    UILabel *label = [[UILabel alloc] init];
    label.font = [UIFont systemFontOfSize:fontSize];
    label.textColor = color;
    
    return label;
}

@end
