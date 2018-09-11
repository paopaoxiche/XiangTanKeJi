//
//  AlipayManager.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/11.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

/**
 *  resultStatus结果码含义
 *
 *  9000    订单支付成功
 *  8000    正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
 *  4000    订单支付失败
 *  5000    重复请求
 *  6001    用户中途取消
 *  6002    网络连接出错
 *  6004    支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
 *  其它     其它支付错误
 */

#import "AlipayManager.h"
#import "PaymentViewController.h"

@implementation AlipayManager

+ (instancetype)sharedManager {
    static dispatch_once_t onceToken;
    static AlipayManager *instance;
    dispatch_once(&onceToken, ^{
        instance = [[AlipayManager alloc] init];
    });
    
    return instance;
}

- (void)handlePaymentResult:(NSDictionary *)result {
    NSInteger resultStatus = [result[@"resultStatus"] integerValue];
    switch (resultStatus) {
        case 9000:
            [_paymentVC paymentSuccess];
            break;
        case 8000:
            [_paymentVC messageBox:@"正在处理中，支付结果未知，请咨询商家"];
            break;
        case 4000:
            [_paymentVC messageBox:@"订单支付失败"];
            break;
        case 6004:
            [_paymentVC messageBox:@"支付结果未知，请咨询商家"];
            break;
        default:
            [_paymentVC messageBox:@"其它支付错误"];
            break;
    }
}

@end
