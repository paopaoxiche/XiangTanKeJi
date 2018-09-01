//
//  UIColor+Category.h
//  FSMeetingClient
//
//  Created by Rachel on 17/2/15.
//
//

#import <UIKit/UIKit.h>

@interface UIColor (Category)

+ (UIColor *)rgbByHexStr:(NSString *)hexStr;
+ (UIColor *)rgbWithRed:(CGFloat)r green:(CGFloat)g blue:(CGFloat)b;
+ (UIColor *)rgbWithRed:(CGFloat)r green:(CGFloat)g blue:(CGFloat)b alpha:(CGFloat)alpha;

@end
