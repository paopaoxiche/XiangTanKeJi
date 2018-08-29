//
//  UserInfoModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/19.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface UserInfoModel : NSObject

@property (nonatomic, copy) NSString *userID;
@property (nonatomic, copy) NSString *phoneNumber;
@property (nonatomic, copy) NSString *nickName;
@property (nonatomic, copy) NSString *avatarUrl;
@property (nonatomic, copy) NSString *token;
@property (nonatomic, copy) NSString *regTime;
@property (nonatomic, copy) NSString *code;
@property (nonatomic, assign) NSUInteger type;
@property (nonatomic, assign) NSUInteger score;

- (instancetype)initWithDic:(NSDictionary *)dic;


@end
