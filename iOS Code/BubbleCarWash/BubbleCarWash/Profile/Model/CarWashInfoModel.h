//
//  CarWashInfoModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/23.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

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

- (instancetype)initWithDic:(NSDictionary *)dic;

@end
