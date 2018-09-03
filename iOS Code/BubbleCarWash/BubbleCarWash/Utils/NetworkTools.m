//
//  NetworkTools.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/19.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "NetworkTools.h"
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

#pragma mark - NetworkTools

static const NSTimeInterval kTimeOutInterval = 6.0f;

@interface NetworkTools ()

@end

@implementation NetworkTools

+ (NetworkTools *)sharedInstance {
    static NetworkTools *instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [[NetworkTools alloc] initWithBaseURL:[NSURL URLWithString:@"http://101.200.63.245:8080/"]];
        instance.requestSerializer.timeoutInterval = kTimeOutInterval;
    });
    
    return instance;
}

#pragma mark - 公告接口

- (void)obtainVerificationCodeWithPhoneNumber:(NSString *)phoneNumber
                                      success:(SuccessBlock)success
                                       failed:(FailedBlock)failed
{
    [self GET:@"user/getMessageCode" parameters:@{@"phone": phoneNumber} success:success failure:failed];
}

- (void)loginWithPhoneNumber:(NSString *)phoneNumber
                        code:(NSUInteger)code userType:(NSUInteger)type
                     success:(SuccessBlock)success
                      failed:(FailedBlock)failed
{
    NSDictionary *params = @{@"phone": phoneNumber,
                             @"code": [NSNumber numberWithUnsignedInteger:code],
                             @"type": [NSNumber numberWithUnsignedInteger:type]};
    [self GET:@"user/login" parameters:params success:success failure:failed];
}

- (void)checkToken:(NSString *)token
            userID:(NSString *)userID
           isOwner:(BOOL)isOwner
           success:(SuccessBlock)success
            failed:(FailedBlock)failed
{
    AFHTTPSessionManager *manager = [NetworkTools sharedInstance];
    NSString *authenStr = [NSString stringWithFormat:@"user_id=%@,token=%@", userID, token];
    NSString *url = isOwner ? @"user/checkCarOwner" : @"user/checkCarWash";
    [manager.requestSerializer setValue:authenStr forHTTPHeaderField:@"authentication"];
    [self GET:url parameters:nil success:success failure:failed];
}

- (void)obtainUserInfoWithUserID:(NSString *)userID
                         success:(SuccessBlock)success
                          failed:(FailedBlock)failed
{
    NSDictionary *params = @{@"id": userID};
    NSString *url = @"user/info";
    [self GET:url parameters:params success:success failure:failed];
}

#pragma mark - 车主首页

- (void)obtainNearbyWashList:(NearbyWashListParam *)param
                     success:(SuccessBlock)success
                      failed:(FailedBlock)failed
{
    NSDictionary *params = [param dictionaryFromModel];
    NSString *url = @"wash/getNearbyWashServiceList";
    [self GET:url parameters:params success:success failure:failed];
}

- (void)obtainRecommendCommodity:(NSInteger)count
                       longitude:(NSNumber *)longitude
                        latitude:(NSNumber *)latitude
                         success:(SuccessBlock)success
                          failed:(FailedBlock)failed
{
    NSDictionary *params = @{@"count": [NSNumber numberWithInteger:count], @"lng":longitude, @"lat": latitude};
    NSString *url = @"wash/getRecommendCommodity";
    [self GET:url parameters:params success:success failure:failed];
}

#pragma mark - 车主我的

- (void)obtainMyCouponListWithAuthentication:(NSString *)authentication
                                isRedeemable:(BOOL)isRedeemable
                                     success:(SuccessBlock)success
                                      failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [NetworkTools sharedInstance];
    NSString *url = isRedeemable ? @"http://101.200.63.245:8080/carOwner/getAllCoupon" : @"carOwner/getMyCoupon";
    [manager.requestSerializer setValue:authentication forHTTPHeaderField:@"authentication"];
    [self GET:url parameters:nil success:success failure:failed];
}

- (void)obtainExpensesRecordWithAuthentication:(NSString *)authentication
                                       success:(SuccessBlock)success
                                        failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [NetworkTools sharedInstance];
    NSString *url = @"carOwner/getUserConsume";
    [manager.requestSerializer setValue:authentication forHTTPHeaderField:@"authentication"];
    [self GET:url parameters:nil success:success failure:failed];
}

- (void)obtainEvaluateListWithAuthentication:(NSString *)authentication
                                   pageIndex:(NSInteger)pageIndex
                                    pageSize:(NSInteger)pageSize
                                     success:(SuccessBlock)success
                                      failed:(FailedBlock)failed {
    NSDictionary *params = @{@"pageIndex": [NSNumber numberWithInteger:pageIndex], @"pageSize": [NSNumber numberWithInteger:pageSize]};
    AFHTTPSessionManager *manager = [NetworkTools sharedInstance];;
    NSString *url = @"carOwner/getEvaluateList";
    [manager.requestSerializer setValue:authentication forHTTPHeaderField:@"authentication"];
    [self GET:url parameters:params success:success failure:failed];
}

- (void)convertIntegralToCouponWithAuthentication:(NSString *)authentication
                                         couponID:(NSString *)couponID
                                          success:(SuccessBlock)success
                                           failed:(FailedBlock)failed {
    NSDictionary *params = @{@"couponId": couponID};
    AFHTTPSessionManager *manager = [NetworkTools sharedInstance];;
    NSString *url = @"carOwner/exchangePoint";
    [manager.requestSerializer setValue:authentication forHTTPHeaderField:@"authentication"];
    [self GET:url parameters:params success:success failure:failed];
}

#pragma mark - Get Method

- (void)GET:(NSString *)url parameters:(NSDictionary *)params success:(SuccessBlock)success failure:(FailedBlock)failed {
    [self GET:url parameters:params progress:nil success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
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
