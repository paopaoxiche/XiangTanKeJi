//
//  CarWashInfoModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/23.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef NS_ENUM(NSInteger, TradeState) {
    TradeStateClosed = -1,      // 停业
    TradeStateRest = 0,         // 歇业
    TradeStateOperate = 1       // 营业
};

@interface CarWashInfoModel : NSObject

/// 手机号
@property (nonatomic, copy) NSString *phoneNumber;
/// 洗车场名称
@property (nonatomic, copy) NSString *nickName;
/// 头像url
@property (nonatomic, copy) NSString *avatarUrl;
/// 荣誉
@property (nonatomic, assign) NSInteger honor;
/// 接洗次数
@property (nonatomic, assign) NSInteger washCount;
/// 认证状态
@property (nonatomic, assign) NSInteger authStatus;
/// 洗车场ID
@property (nonatomic, assign) NSInteger washID;
/// 洗车场运营状态
@property (nonatomic, assign) TradeState tradeState;
/// 余额
@property (nonatomic, assign) CGFloat balance;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end
