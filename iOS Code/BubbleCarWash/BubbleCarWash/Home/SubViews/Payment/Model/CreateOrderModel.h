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

@property (nonatomic, assign) NSInteger consumeId;
/// 微信调起支付参数
@property (nonatomic, strong) WeiXinPaymentModel *wxPay;
/// 支付宝调起支付参数
@property (nonatomic, copy) NSString *aliPay;
/// 支付类型 1：微信(使用wxPay的参数) 2：支付宝(使用aliPay参数)
@property (nonatomic, assign) NSInteger payType;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end

@interface WeiXinPaymentModel : NSObject

/// 微信支付APPID
@property (nonatomic, copy) NSString *appid;
/// 随机串，防重发
@property (nonatomic, copy) NSString *noncestr;
/// 订单号
@property (nonatomic, copy) NSString *outTradeNo;
/// 商家根据财付通文档填写的数据和签名
@property (nonatomic, copy) NSString *package;
/// 商家向财付通申请的商家id
@property (nonatomic, copy) NSString *partnerid;
/// 预支付交易会话标识
@property (nonatomic, copy) NSString *prepayid;
/// 商家根据微信开放平台文档对数据做的签名
@property (nonatomic, copy) NSString *sign;
/// 时间戳，防重发
@property (nonatomic, assign) UInt32 timestamp;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end
