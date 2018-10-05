//
//  CarWashInfoModel.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/23.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CarWashInfoModel.h"

@implementation CarWashInfoModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _washID = [[dic objectForKey:@"id"] integerValue];
        _phoneNumber = [dic objectForKey:@"account"];
        _nickName = [dic objectForKey:@"name"];
        _avatarUrl = [dic objectForKey:@"avatars"];
        _washCount = [[dic objectForKey:@"washCount"] integerValue];
        _authStatus = [[dic objectForKey:@"authStatus"] integerValue];
        _honor = [[dic objectForKey:@"honor"] integerValue];
        _tradeState = [[dic objectForKey:@"businessState"] integerValue];
    }
    
    return self;
}

@end
