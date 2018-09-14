//
//  WXApiManager.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/11.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "WXApiManager.h"
#import "PaymentViewController.h"

@implementation WXApiManager

#pragma mark - LifeCycle

+ (instancetype)sharedManager {
    static dispatch_once_t onceToken;
    static WXApiManager *instance;
    dispatch_once(&onceToken, ^{
        instance = [[WXApiManager alloc] init];
    });
    
    return instance;
}

#pragma mark - WXApiDelegate

- (void)onResp:(BaseResp *)resp {
    if ([resp isKindOfClass:[PayResp class]]) {
        switch (resp.errCode) {
            case WXSuccess:
                [_paymentVC paymentSuccess];
                break;
            default:
                [_paymentVC messageBox:@"支付失败"];
                break;
        } // switch
    } // if
} // func

- (void)onReq:(BaseReq *)req {
    
}

@end
