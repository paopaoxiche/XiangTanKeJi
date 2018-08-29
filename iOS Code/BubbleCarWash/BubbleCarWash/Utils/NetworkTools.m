//
//  NetworkTools.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/19.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "NetworkTools.h"
#import "AFNetworking.h"
#import "NSObject+DictionaryFromModel.h"

@implementation NearbyWashListParam

- (instancetype)init {
    self = [super init];
    if (self) {
        _count = 4;
        _isShowAll = 0;
        _priceLimit = 5.0;
        _radius = -1;
    }
    
    return self;
}

@end

static const NSTimeInterval kTimeOutInterval = 6.0f;

@implementation NetworkTools

+ (AFHTTPSessionManager *)manager {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    
    return manager;
}

+ (void)obtainVerificationCodeWithPhoneNumber:(NSString *)phoneNumber
                                      success:(SuccessBlock)success
                                       failed:(FailedBlock)failed
{
    [self GET:@"http://101.200.63.245:8080/user/getMessageCode"
   parameters:@{@"phone": phoneNumber}
      success:success
      failure:failed];
}

+ (void)loginWithPhoneNumber:(NSString *)phoneNumber
                        code:(NSUInteger)code userType:(NSUInteger)type
                     success:(SuccessBlock)success
                      failed:(FailedBlock)failed
{
    NSDictionary *params = @{@"phone": phoneNumber,
                             @"code": [NSNumber numberWithUnsignedInteger:code],
                             @"type": [NSNumber numberWithUnsignedInteger:type]};
    [self GET:@"http://101.200.63.245:8080/user/login"
   parameters:params
      success:success
      failure:failed];
}

+ (void)checkToken:(NSString *)token
            userID:(NSString *)userID
           isOwner:(BOOL)isOwner
           success:(SuccessBlock)success
            failed:(FailedBlock)failed
{
    NSDictionary *params = @{@"authentication": [NSString stringWithFormat:@"user_id=%@,token=%@", userID, token]};
    NSString *url = isOwner ? @"http://101.200.63.245:8080/user/checkCarOwner" : @"http://101.200.63.245:8080/user/checkCarWash";
    [self GET:url parameters:params success:success failure:failed];
}

+ (void)obtainUserInfoWithUserID:(NSString *)userID
                         success:(SuccessBlock)success
                          failed:(FailedBlock)failed
{
    NSDictionary *params = @{@"id": userID};
    NSString *url = @"http://101.200.63.245:8080/user/info";
    [self GET:url parameters:params success:success failure:failed];
}

+ (void)obtainNearbyWashList:(NearbyWashListParam *)param
                     success:(SuccessBlock)success
                      failed:(FailedBlock)failed
{
    NSDictionary *params = [param dictionaryFromModel];
    NSString *url = @"http://101.200.63.245:8080/wash/getNearbyWashServiceList";
    [self GET:url parameters:params success:success failure:failed];
}

+ (void)obtainRecommendCommodity:(NSInteger)count
                       longitude:(NSString *)longitude
                        latitude:(NSString *)latitude
                         success:(SuccessBlock)success
                          failed:(FailedBlock)failed
{
    
}

+ (void)GET:(NSString *)url parameters:(NSDictionary *)params success:(SuccessBlock)success failure:(FailedBlock)failed {
    [[self manager] GET:url parameters:params progress:nil success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        NSLog(@"responseObject = %@", responseObject);
        if (responseObject) {
            success(responseObject, YES);
        } else {
            success(@{@"msg": @"暂无数据"}, NO);
        }
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        failed(error);
    }];
}

@end
