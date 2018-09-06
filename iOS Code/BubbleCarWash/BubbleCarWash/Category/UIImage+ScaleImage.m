//
//  UIImage+ScaleImage.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "UIImage+ScaleImage.h"

@implementation UIImage (ScaleImage)

- (UIImage *)imageWithScale:(CGFloat)width {
    // 根据宽度计算高度
    CGFloat height = width * self.size.height / self.size.width;
    
    // 按照宽高比绘制一张新的图片
    CGSize currentSize = CGSizeMake(width, height);
    UIGraphicsBeginImageContext(currentSize);
    [self drawInRect:CGRectMake(0, 0, width, height)];
    UIImage *newImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    
    return newImage;
}

@end
