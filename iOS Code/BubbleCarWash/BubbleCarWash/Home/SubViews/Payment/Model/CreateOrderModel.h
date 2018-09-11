//
//  CreateOrderModel.h
//  BubbleCarWash
//
//  Created by Rachel on 2018/9/11.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

@class WeiXinPaymentModel;

@interface CreateOrderModel : NSObject

/// 微信调起支付参数
@property (nonatomic, strong) WeiXinPaymentModel *wxPay;
/// 支付宝调起支付参数
@property (nonatomic, copy) NSString *aliPay;
/// 支付类型 1：微信 （使用data.wxPay的参数） 2：支付宝：(使用data.aliPay参数)
@property (nonatomic, assign) NSInteger payType;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end

@interface WeiXinPaymentModel : NSObject

/// 微信支付APPI
@property (nonatomic, copy) NSString *appid;
@property (nonatomic, copy) NSString *noncestr;
/// 订单号
@property (nonatomic, copy) NSString *outTradeNo;
@property (nonatomic, copy) NSString *package;
/// 商户号
@property (nonatomic, copy) NSString *partnerid;
/// 预支付交易会话标识
@property (nonatomic, copy) NSString *prepayid;
/// 签名
@property (nonatomic, copy) NSString *sign;
/// 时间戳
@property (nonatomic, copy) NSString *timestamp;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end
