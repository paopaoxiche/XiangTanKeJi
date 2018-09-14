//
//  AppDelegate.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/6/25.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <AMapFoundationKit/AMapFoundationKit.h>
#import <AlipaySDK/AlipaySDK.h>

#import "AppDelegate.h"
#import "WXApi.h"
#import "WXApiManager.h"
#import "AlipayManager.h"

@interface AppDelegate ()

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    [AMapServices sharedServices].apiKey = @"c1b284b5a52e845eb2fb14767208fbba";
    [WXApi registerApp:@"wx129f550ad02f9cd2"];
    
    return YES;
}

- (BOOL)application:(UIApplication *)app openURL:(NSURL *)url options:(NSDictionary<NSString *, id> *)options {
    // 微信的支付回调
    if ([url.host isEqualToString:@"pay"]) {
        return [WXApi handleOpenURL:url delegate:[WXApiManager sharedManager]];
    }
    
    // 跳转支付宝钱包进行支付，处理支付结果
    if ([url.host isEqualToString:@"safepay"]) {
        [[AlipaySDK defaultService] processOrderWithPaymentResult:url standbyCallback:^(NSDictionary *resultDic) {
            [[AlipayManager sharedManager] handlePaymentResult:resultDic];
        }];
    }
    
    return YES;
}


@end
