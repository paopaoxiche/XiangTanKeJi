//
//  UIImage+CreateByColor.m
//  FSMeetingClient
//
//  Created by Rachel on 2018/9/25.
//

#import "UIImage+CreateByColor.h"

@implementation UIImage (CreateByColor)

+ (UIImage *)fm_imageByColor:(UIColor *)color {
    return [self fm_imageByColor:color size:CGSizeMake(1, 1)];
}

+ (UIImage *)fm_imageByColor:(UIColor *)color size:(CGSize)size {
    CGRect rect = CGRectMake(0, 0, size.width, size.height);
    UIGraphicsBeginImageContext(rect.size);
    CGContextRef context = UIGraphicsGetCurrentContext();
    CGContextSetFillColorWithColor(context, [color CGColor]);
    CGContextFillRect(context, rect);
    UIImage *image = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    
    return image;
}

@end
