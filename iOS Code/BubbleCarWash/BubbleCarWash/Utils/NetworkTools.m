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
        _showAll = 0;
        _priceLimit = 0;
        _radius = -1;
    }
    
    return self;
}

@end

static const NSTimeInterval kTimeOutInterval = 6.0f;

@interface NetworkTools ()

@end

@implementation NetworkTools

+ (AFHTTPSessionManager *)manager {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    
    return manager;
}

#pragma mark - 公告接口

+ (void)obtainVerificationCodeWithPhoneNumber:(NSString *)phoneNumber
                                      success:(SuccessBlock)success
                                       failed:(FailedBlock)failed
{
    [self GET:@"http://101.200.63.245:8080/user/getMessageCode"
   parameters:@{@"phone": phoneNumber}
     manager:[self manager]
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
     manager:[self manager]
      success:success
      failure:failed];
}

+ (void)checkToken:(NSString *)token
            userID:(NSString *)userID
           isOwner:(BOOL)isOwner
           success:(SuccessBlock)success
            failed:(FailedBlock)failed
{
    AFHTTPSessionManager *manager = [self manager];
    NSString *authenStr = [NSString stringWithFormat:@"user_id=%@,token=%@", userID, token];
    NSString *url = isOwner ? @"http://101.200.63.245:8080/user/checkCarOwner" : @"http://101.200.63.245:8080/user/checkCarWash";
    [manager.requestSerializer setValue:authenStr forHTTPHeaderField:@"authentication"];
    [self GET:url parameters:nil manager:manager success:success failure:failed];
}

+ (void)obtainUserInfoWithUserID:(NSString *)userID
                         success:(SuccessBlock)success
                          failed:(FailedBlock)failed
{
    NSDictionary *params = @{@"id": userID};
    NSString *url = @"http://101.200.63.245:8080/user/info";
    [self GET:url parameters:params manager:[self manager] success:success failure:failed];
}

#pragma mark - 车主首页

+ (void)obtainNearbyWashList:(NearbyWashListParam *)param
                     success:(SuccessBlock)success
                      failed:(FailedBlock)failed
{
    NSDictionary *params = [param dictionaryFromModel];
    NSString *url = @"http://101.200.63.245:8080/wash/getNearbyWashServiceList";
    [self GET:url parameters:params manager:[self manager] success:success failure:failed];
}

+ (void)obtainRecommendCommodity:(NSInteger)count
                       longitude:(NSNumber *)longitude
                        latitude:(NSNumber *)latitude
                         success:(SuccessBlock)success
                          failed:(FailedBlock)failed
{
    NSDictionary *params = @{@"count": [NSNumber numberWithInteger:count], @"lng":longitude, @"lat": latitude};
    NSString *url = @"http://101.200.63.245:8080/wash/getRecommendCommodity";
    [self GET:url parameters:params manager:[self manager] success:success failure:failed];
}

#pragma mark - 车主我的

+ (void)obtainMyCouponListWithAuthentication:(NSString *)authentication
                                isRedeemable:(BOOL)isRedeemable
                                     success:(SuccessBlock)success
                                      failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [self manager];
    NSString *url = isRedeemable ? @"http://101.200.63.245:8080/carOwner/getAllCoupon" : @"http://101.200.63.245:8080/carOwner/getMyCoupon";
    [manager.requestSerializer setValue:authentication forHTTPHeaderField:@"authentication"];
    [self GET:url parameters:nil manager:manager success:success failure:failed];
}

+ (void)obtainExpensesRecordWithAuthentication:(NSString *)authentication
                                       success:(SuccessBlock)success
                                        failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [self manager];
    NSString *url = @"http://localhost:8080/carOwner/getUserConsume";
    [manager.requestSerializer setValue:authentication forHTTPHeaderField:@"authentication"];
    [self GET:url parameters:nil manager:manager success:success failure:failed];
}

+ (void)obtainEvaluateListWithAuthentication:(NSString *)authentication
                                   pageIndex:(NSInteger)pageIndex
                                    pageSize:(NSInteger)pageSize
                                     success:(SuccessBlock)success
                                      failed:(FailedBlock)failed {
    NSDictionary *params = @{@"pageIndex": [NSNumber numberWithInteger:pageIndex], @"pageSize": [NSNumber numberWithInteger:pageSize]};
    AFHTTPSessionManager *manager = [self manager];
    NSString *url = @"http://localhost:8080/carOwner/getEvaluateList";
    [manager.requestSerializer setValue:authentication forHTTPHeaderField:@"authentication"];
    [self GET:url parameters:params manager:manager success:success failure:failed];
}

+ (void)convertIntegralToCouponWithAuthentication:(NSString *)authentication
                                         couponID:(NSString *)couponID
                                          success:(SuccessBlock)success
                                           failed:(FailedBlock)failed {
    NSDictionary *params = @{@"couponId": couponID};
    AFHTTPSessionManager *manager = [self manager];
    NSString *url = @"http://101.200.63.245:8080/carOwner/exchangePoint";
    [manager.requestSerializer setValue:authentication forHTTPHeaderField:@"authentication"];
    [self GET:url parameters:params manager:manager success:success failure:failed];
}

#pragma mark - Get Method

+ (void)GET:(NSString *)url parameters:(NSDictionary *)params manager:(AFHTTPSessionManager *)manager success:(SuccessBlock)success failure:(FailedBlock)failed {
    [manager GET:url parameters:params progress:nil success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
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
