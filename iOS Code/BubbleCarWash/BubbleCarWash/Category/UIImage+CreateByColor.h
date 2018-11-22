//
//  UIImage+CreateByColor.h
//  FSMeetingClient
//
//  Created by Rachel on 2018/9/25.
//

#import <UIKit/UIKit.h>

@interface UIImage (CreateByColor)

/**
 *  通过UIColor创建UIImage
 */
+ (UIImage *)fm_imageByColor:(UIColor *)color;
/**
 *  通过UIColor创建UIImage
 *
 *  @param color image颜色
 *  @param size image大小
 *  @return 返回创建的image
 */
+ (UIImage *)fm_imageByColor:(UIColor *)color size:(CGSize)size;

@end
