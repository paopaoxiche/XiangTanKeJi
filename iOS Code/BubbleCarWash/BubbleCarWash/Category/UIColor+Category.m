//
//  UIColor+Category.m
//  FSMeetingClient
//
//  Created by Rachel on 17/2/15.
//
//

#import "UIColor+Category.h"

@implementation UIColor (Category)

+ (UIColor *)rgbByHexStr:(NSString *)hexStr {
    return [self rgbByHexStr:hexStr alpha:1.0];
}

+ (UIColor *)rgbByHexStr:(NSString *)hexStr alpha:(CGFloat)alpha {
    unsigned long red = strtoul([[hexStr substringWithRange:NSMakeRange(0, 2)] UTF8String], 0, 16);
    unsigned long green = strtoul([[hexStr substringWithRange:NSMakeRange(2, 2)] UTF8String], 0, 16);
    unsigned long blue = strtoul([[hexStr substringWithRange:NSMakeRange(4, 2)] UTF8String], 0, 16);
    
    return [self rgbWithRed:red green:green blue:blue alpha:alpha];
}

+ (UIColor *)rgbWithRed:(CGFloat)r green:(CGFloat)g blue:(CGFloat)b {
    return [self rgbWithRed:r green:g blue:b alpha:1.0];
}

+ (UIColor *)rgbWithRed:(CGFloat)r green:(CGFloat)g blue:(CGFloat)b alpha:(CGFloat)alpha {
    return [UIColor colorWithRed:r/255.0f green:g/255.0f blue:b/255.0f alpha:alpha];
}

@end
