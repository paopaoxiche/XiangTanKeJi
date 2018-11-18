//
//  UIApplication+HUD.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/11/18.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@class MBProgressHUD;

@interface UIApplication (HUD)

@property (nonatomic, readonly) UIWindow *mainWindow;

+ (UIWindow *)mainWindow;
+ (void)showBusyHUD:(UIView *)parentView;
+ (void)showBusyHUD:(UIView *)parentView withTitle:(NSString *)title;
+ (void)showBusyHUD;
+ (void)stopBusyHUD;
/**
 *  toast提示，3秒后自动消失
 *
 *  @param parentView toast提示父视图
 *  @param tip 提示内容
 */
+ (void)showHUDAddTo:(UIView *)parentView withTip:(NSString *)tip;

@end
