//
//  UserInfoModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/19.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface UserInfoModel : NSObject

/// 用户id
@property (nonatomic, copy) NSString *userID;
/// 电话号码
@property (nonatomic, copy) NSString *phoneNumber;
/// 昵称
@property (nonatomic, copy) NSString *nickName;
/// 头像url
@property (nonatomic, copy) NSString *avatarUrl;
/// token
@property (nonatomic, copy) NSString *token;
/// 注册时间
@property (nonatomic, copy) NSString *regTime;
/// 验证码
@property (nonatomic, copy) NSString *code;
/// 用户类型（0 - 车主，1 - 洗车场）
@property (nonatomic, assign) NSUInteger type;
/// 积分
@property (nonatomic, assign) NSUInteger score;

- (instancetype)initWithDic:(NSDictionary *)dic;


@end
