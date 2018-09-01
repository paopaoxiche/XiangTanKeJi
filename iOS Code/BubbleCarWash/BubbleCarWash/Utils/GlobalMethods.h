//
//  GlobalMethods.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/8.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface GlobalMethods : NSObject

+ (UIViewController *)viewControllerWithBuddleName:(NSString *)name vcIdentifier:(NSString *)identifier;

/**
 *  验证手机号格式是否正确
 *
 *  @return 文本框中手机号格式正确返回YES，否则返回NO
 */
+ (BOOL)checkPhoneNumberValid:(NSString *)phoneNumberString;

+ (NSString *)conversionTimestampToStr:(NSString *)timestamp;

@end
