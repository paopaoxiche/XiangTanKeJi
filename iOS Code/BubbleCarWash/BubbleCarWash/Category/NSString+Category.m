//
//  NSString+Category.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/19.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "NSString+Category.h"

@implementation NSString (Category)

+ (BOOL)isBlackString:(NSString *)str {
    // 获取除去空格后剩下的字符长度
    NSUInteger length = [str stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceCharacterSet]].length;
    
    if (!length) return YES;
    
    return NO;
}

@end
