//
//  UIApplication+HUD.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/11/18.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "UIApplication+HUD.h"
#import "MBProgressHUD.h"
#import "FunctionMacro.h"

static MBProgressHUD *_hud;

@implementation UIApplication (HUD)

- (UIWindow *)mainWindow {
    return [[self class] mainWindow];
}

+ (MBProgressHUD *)hud {
    return _hud;
}

+ (UIWindow*)mainWindow {
    UIWindow* window = [UIApplication sharedApplication].keyWindow;
    if (!window) {
        window = [[UIApplication sharedApplication].windows objectAtIndex:0];
    }
    
    return window;
}

+ (void)showBusyHUD {
    [self showBusyHUD:nil];
}

+ (void)showBusyHUD:(UIView*)parentView {
    [self showBusyHUD:parentView withTitle:nil];
}

+ (void)showBusyHUD:(UIView*)parentView withTitle:(NSString *)title {
    if (_hud != nil) {
        [_hud removeFromSuperview];
        _hud.delegate = nil;
        SAFE_RELEASE(_hud);
    }
    
    if (parentView == nil) {
        UIWindow* mainWindow = [UIApplication sharedApplication].mainWindow;
        parentView = mainWindow;
    }
    
    _hud = [[MBProgressHUD alloc] initWithView:parentView];
    _hud.removeFromSuperViewOnHide = YES;
    [parentView addSubview:_hud];
    [parentView bringSubviewToFront:_hud];
    
    if ([title length] > 0)
        _hud.label.text = title;
    
    if ([[NSThread currentThread] isMainThread]) {
        [_hud showAnimated:NO];
    } else {
        dispatch_sync(dispatch_get_main_queue(), ^{
            [_hud showAnimated:NO];
        });
    }
}

+ (void)stopBusyHUD {
    [self stopBusyHUD:YES];
}

+ (void)stopBusyHUD:(BOOL)animated {
    if ([[NSThread currentThread] isMainThread]) {
        [self hideHUD:animated];
    } else {
        dispatch_sync(dispatch_get_main_queue(), ^{
            [self hideHUD:animated];
        });
    }
}

+ (void)hideHUD:(BOOL)animated {
    if (_hud != nil) {
        _hud.delegate = nil;
        [_hud hideAnimated:animated];
    }
}


+ (void)showHUDAddTo:(UIView *)parentView withTip:(NSString *)tip {
    if (_hud != nil) {
        [_hud removeFromSuperview];
        _hud.delegate = nil;
        SAFE_RELEASE(_hud);
    }
    
    if (parentView == nil) {
        UIWindow *mainWindow = [UIApplication sharedApplication].mainWindow;
        parentView = mainWindow;
    }
    
    _hud = [MBProgressHUD showHUDAddedTo:parentView animated:YES];
    _hud.mode = MBProgressHUDModeText;
    _hud.label.text = tip;
    _hud.label.textColor = [UIColor whiteColor];
    _hud.label.numberOfLines = 0;
    _hud.margin = 10.f;
    _hud.bezelView.backgroundColor = [UIColor blackColor];
    _hud.bezelView.alpha = 0.6;
    _hud.backgroundView.style = MBProgressHUDBackgroundStyleSolidColor;
    _hud.userInteractionEnabled = NO;       // hud显示时，操作界面其他按钮仍可响应操作
    
    [parentView bringSubviewToFront:_hud];
    [_hud hideAnimated:YES afterDelay:3.f];
}

@end
