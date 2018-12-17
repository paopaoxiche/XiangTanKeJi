//
//  UserInfoModel.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/19.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "UserInfoModel.h"
#import "GlobalMethods.h"

@implementation UserInfoModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _userID = [dic objectForKey:@"id"];
        _phoneNumber = [dic objectForKey:@"userPhone"];
        _nickName = [dic objectForKey:@"nickName"];
        _avatarUrl = [dic objectForKey:@"avatar"];
        _token = [dic objectForKey:@"token"];
        _regTime = [GlobalMethods conversionTimestampToStr:[[dic objectForKey:@"regTime"] longValue]
                                                dateFormat:@"yyyy-MM-dd"];
        _type = [[dic objectForKey:@"type"] unsignedIntegerValue];
        _score = [[dic objectForKey:@"score"] unsignedIntegerValue];
    }
    
    return self;
}

@end
