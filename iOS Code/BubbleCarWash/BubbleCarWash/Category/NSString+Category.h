//
//  NSString+Category.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/19.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSString (Category)

/**
 *  判断给定的字符串是否是空字符串
 *
 *  @param str 待判断的字符串
 *  @return YES，空字符串；反之非空字符串
 */
+ (BOOL)isBlackString:(NSString *)str;

@end
