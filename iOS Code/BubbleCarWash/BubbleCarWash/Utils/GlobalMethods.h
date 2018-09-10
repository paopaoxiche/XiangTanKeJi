//
//  GlobalMethods.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/8.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "DataType.h"

@interface GlobalMethods : NSObject

+ (UIViewController *)viewControllerWithBuddleName:(NSString *)name vcIdentifier:(NSString *)identifier;

/**
 *  验证手机号格式是否正确
 *
 *  @return 文本框中手机号格式正确返回YES，否则返回NO
 */
+ (BOOL)checkPhoneNumberValid:(NSString *)phoneNumberString;

+ (NSString *)conversionTimestampToStr:(NSString *)timestamp dateFormat:(NSString *)dateFormat;

/**
 *  求两经纬度间距离
 */
+ (NSInteger)calculateDistanceWithLocation:(Location)location localLocation:(Location)localLocation;

+ (NSString *)deviceModel;
+ (NSString *)productVersion;
+ (NSString *)systemVersion;

/**
 *  设置view渐变色
 *  @param startPoint 渐变起点  (0,0)代表左上角，(0,1)代表左下角，结合起来就是竖直方向渐变
 *  @param endPoint 渐变结束点  (1,0)代表右上角，(1,1)代表右下角
 */
+ (void)setGradientColor:(NSArray *)colors
              startPoint:(CGPoint)startPoint
                endPoint:(CGPoint)endPoint
                    view:(UIView *)view;
/**
 *  设置view透明渐变色
 */
+ (void)setTransparentGradientColor:(NSArray *)colors
                         startPoint:(CGPoint)startPoint
                           endPoint:(CGPoint)endPoint
                               view:(UIView *)view;

@end
