//
//  CreateOrderModel.m
//  BubbleCarWash
//
//  Created by Rachel on 2018/9/11.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CreateOrderModel.h"

@implementation CreateOrderModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _aliPay = [dic objectForKey:@"aliPay"];
        _payType = [[dic objectForKey:@"payType"] integerValue];
        NSDictionary *wxpay = [dic objectForKey:@"wxPay"];
        _wxPay = (wxpay == [NSNull null]) ? [[WeiXinPaymentModel alloc] init] : [[WeiXinPaymentModel alloc] initWithDic:wxpay];
    }
    
    return self;
}

@end

@implementation WeiXinPaymentModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _appid = [dic objectForKey:@"appid"];
        _noncestr = [dic objectForKey:@"noncestr"];
        _outTradeNo = [dic objectForKey:@"outTradeNo"];
        _package = [dic objectForKey:@"package"];;
        _partnerid = [dic objectForKey:@"partnerid"];
        _prepayid = [dic objectForKey:@"prepayid"];
        _sign = [dic objectForKey:@"sign"];
        _timestamp = [[dic objectForKey:@"timestamp"] intValue];
    }
    
    return self;
}

@end
