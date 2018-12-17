//
//  NetworkTools.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/19.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "NetworkTools.h"
#import "NSObject+DictionaryFromModel.h"
#import "UserManager.h"
#import "GlobalMethods.h"

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

@implementation FeedBackParam

- (instancetype)init {
    self = [super init];
    if (self) {
        _systemType = @"1";
        _device = [GlobalMethods deviceModel];
        _versionCode = [GlobalMethods productVersion];
        _systemVersion = [GlobalMethods systemVersion];
    }
    
    return self;
}

@end

@implementation CreateOrderParam : NSObject

- (instancetype)init {
    self = [super init];
    if (self) {
        _couponId = @"-1";
        _payType = @"1";
    }
    
    return self;
}

@end

@implementation RegisterWashParam

- (instancetype)init {
    self = [super init];
    if (self) {
        _license = nil;
        _washCard = nil;
        _idCardBack = nil;
        _idCardPositive = nil;
    }
    
    return self;
}

@end

@implementation CommodityParam

- (instancetype)init {
    self = [super init];
    if (self) {
        _commodityID = @"0";
    }
    
    return self;
}

@end

@implementation ServiceParam

- (instancetype)init {
    self = [super init];
    if (self) {
        _serviceId = @0;
        _carModel = @"1";
    }
    
    return self;
}

@end

#pragma mark - NetworkTools

static const NSTimeInterval kTimeOutInterval = 6.0f;

@interface NetworkTools ()

@end

@implementation NetworkTools

#pragma mark - 公告接口

+ (void)obtainVerificationCodeWithPhoneNumber:(NSString *)phoneNumber
                                      success:(SuccessBlock)success
                                       failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [self GET:manager
          url:@"http://101.200.63.245:8080/user/getMessageCode"
   parameters:@{@"phone": phoneNumber}
      success:success
      failure:failed];
}

+ (void)loginWithPhoneNumber:(NSString *)phoneNumber
                        code:(NSUInteger)code userType:(NSUInteger)type
                     success:(SuccessBlock)success
                      failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    NSDictionary *params = @{@"phone": phoneNumber,
                             @"code": [NSString stringWithFormat:@"%lu", code],
                             @"type": [NSString stringWithFormat:@"%lu", type]};
    [self GET:manager
          url:@"http://101.200.63.245:8080/user/login"
   parameters:params
      success:success
      failure:failed];
}

+ (void)checkToken:(NSString *)token
            userID:(NSString *)userID
           isOwner:(BOOL)isOwner
           success:(SuccessBlock)success
            failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    NSString *authenStr = [NSString stringWithFormat:@"user_id=%@,token=%@", userID, token];
    NSString *url = isOwner ? @"user/checkCarOwner" : @"user/checkCarWash";
    [manager.requestSerializer setValue:authenStr forHTTPHeaderField:@"authentication"];
    [self GET:manager url:[NSString stringWithFormat:@"http://101.200.63.245:8080/%@", url] parameters:nil success:success failure:failed];
}

+ (void)obtainUserInfoWithUserID:(NSString *)userID success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    NSDictionary *params = @{@"id": userID};
    NSString *url = @"http://101.200.63.245:8080/user/info";
    [self GET:manager url:url parameters:params success:success failure:failed];
}

+ (void)updateUserNickName:(NSString *)nickName success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    [self POST:manager url:@"http://101.200.63.245:8080/user/update" parameters:@{@"nickname": nickName} success:success failure:failed];
}

+ (void)updateUserAvatar:(UIImage *)image success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    [self POST:manager url:@"http://101.200.63.245:8080/user/update" parameters:nil images:@[@{@"file": image}] success:success failure:failed];
}

+ (void)obtainUpgradeInfo:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    NSDictionary *params = @{@"systemType": @"1", @"version": [GlobalMethods productVersion]};
    [self GET:manager url:@"http://101.200.63.245:8080/user/getAppVersion" parameters:params success:success failure:failed];
}

+ (void)submitFeedBackInfo:(FeedBackParam *)param success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    NSDictionary *params = [param dictionaryFromModel];
    [self POST:manager url:@"http://101.200.63.245:8080/feedback/submit" parameters:params success:success failure:failed];
}

#pragma mark - 洗车场

#pragma mark - 车主首页

+ (void)obtainNearbyWashList:(NearbyWashListParam *)param
                     success:(SuccessBlock)success
                      failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    NSDictionary *params = [param dictionaryFromModel];
    NSString *url = @"http://101.200.63.245:8080/wash/getNearbyWashServiceList";
    [self GET:manager url:url parameters:params success:success failure:failed];
}

+ (void)obtainRecommendCommodity:(NSInteger)count
                       longitude:(NSNumber *)longitude
                        latitude:(NSNumber *)latitude
                         success:(SuccessBlock)success
                          failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    NSDictionary *params = @{@"count": [NSNumber numberWithInteger:count], @"lng":longitude, @"lat": latitude};
    NSString *url = @"http://101.200.63.245:8080/wash/getRecommendCommodity";
    [self GET:manager url:url parameters:params success:success failure:failed];
}

+ (void)obtainCarWashInfo:(NSInteger)washID success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    NSString *url = @"http://101.200.63.245:8080/wash/getCarWashDetail";
    [self GET:manager url:url parameters:@{@"washId": [NSNumber numberWithInteger:washID]} success:success failure:failed];
}

+ (void)obtainCarWashServiceList:(NSInteger)washID success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    NSDictionary *params = @{@"washId": [NSNumber numberWithInteger:washID],
                             @"pageIndex": [NSNumber numberWithInteger:0],
                             @"pageSize": [NSNumber numberWithInteger:1000]};
    NSString *url = @"http://101.200.63.245:8080/wash/getServiceList";
    [self GET:manager url:url parameters:params success:success failure:failed];
}

+ (void)obtainCarWashCommodityList:(NSInteger)washID success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    NSDictionary *params = @{@"washId": [NSNumber numberWithInteger:washID],
                             @"pageIndex": [NSNumber numberWithInteger:0],
                             @"pageSize": [NSNumber numberWithInteger:1000]};
    NSString *url = @"http://101.200.63.245:8080/wash/getCommodityList";
    [self GET:manager url:url parameters:params success:success failure:failed];
}

#pragma mark - 车主我的

+ (void)obtainMyCouponList:(BOOL)isRedeemable success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    NSString *url = isRedeemable ? @"getAllCoupon" : @"getMyCoupon";
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [self GET:manager url:[NSString stringWithFormat:@"http://101.200.63.245:8080/carOwner/%@", url] parameters:nil success:success failure:failed];
}

+ (void)obtainExpensesRecord:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    NSString *url = @"http://101.200.63.245:8080/carOwner/getUserConsume";
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [self GET:manager url:url parameters:nil success:success failure:failed];
}

+ (void)obtainEvaluateListWithPageIndex:(NSInteger)pageIndex
                               pageSize:(NSInteger)pageSize
                                success:(SuccessBlock)success
                                 failed:(FailedBlock)failed {
    NSDictionary *params = @{@"pageIndex": [NSNumber numberWithInteger:pageIndex], @"pageSize": [NSNumber numberWithInteger:pageSize]};
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    NSString *url = @"http://101.200.63.245:8080/carOwner/getEvaluateList";
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [self GET:manager url:url parameters:params success:success failure:failed];
}

+ (void)submitEvaluate:(NSUInteger)consumeID
                 grade:(NSInteger)grade
               content:(NSString *)content
               success:(SuccessBlock)success
                failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    NSDictionary *params = @{@"consumeId": [NSNumber numberWithUnsignedInteger:consumeID], @"grade": [NSNumber numberWithInteger:grade], @"content": content};
    [self POST:manager url:@"http://101.200.63.245:8080/carOwner/evaluateRecord" parameters:params success:success failure:failed];
}

+ (void)convertIntegralToCouponWithCouponID:(NSString *)couponID
                                    success:(SuccessBlock)success
                                     failed:(FailedBlock)failed {
    NSDictionary *params = @{@"couponId": couponID};
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    NSString *url = @"http://101.200.63.245:8080/carOwner/exchangePoint";
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    [self POST:manager url:url parameters:params success:success failure:failed];
}

+ (void)obtainCarTypeList:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [self GET:manager url:@"http://101.200.63.245:8080/car/models" parameters:nil success:success failure:failed];
}

+ (void)obtainModelReviewList:(NSInteger)status success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    [self GET:manager url:@"http://101.200.63.245:8080/car/list" parameters:@{@"status": [NSNumber numberWithInteger:status]} success:success failure:failed];
}

+ (void)submitModelReview:(NSString *)modelID
                    cover:(UIImage *)cover
                     back:(UIImage *)back
                  success:(SuccessBlock)success
                   failed:(FailedBlock)failed {
    NSMutableArray *images = [[NSMutableArray alloc] initWithCapacity:0];
    if (cover) {
        [images addObject:@{@"cover": cover}];
    }
    if (back) {
        [images addObject:@{@"back": back}];
    }
    
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    if (images.count > 0) {
        [self POST:manager url:@"http://101.200.63.245:8080/car/register" parameters:@{@"model": modelID} images:images success:success failure:failed];
    } else {
        [self POST:manager url:@"http://101.200.63.245:8080/car/register" parameters:@{@"model": modelID} success:success failure:failed];
    }
}

+ (void)obtainModelDetail:(NSInteger)modelID success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    [self GET:manager url:@"http://101.200.63.245:8080/car/detail" parameters:@{@"id": [NSNumber numberWithInteger:modelID]} success:success failure:failed];
}

#pragma mark - 车主支付

+ (void)createOrder:(CreateOrderParam *)param success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    NSDictionary *params = [param dictionaryFromModel];
    [self POST:manager url:@"http://101.200.63.245:8080/carOwner/createConsume" parameters:params success:success failure:failed];
}

+ (void)obtainComsumeStatus:(NSString *)comsumedID success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    NSDictionary *params = @{@"comsumeId" : comsumedID};
    [self GET:manager url:@"http://101.200.63.245:8080/carOwner/getConsumeStatus" parameters:params success:success failure:failed];
}

#pragma mark - 洗车场首页

+ (void)obtainRecentCarWashes:(NSInteger)washID count:(NSInteger)count success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    NSDictionary *params = @{@"washId":[NSNumber numberWithInteger:washID], @"count":[NSNumber numberWithInteger:count]};
    [self GET:manager url:@"http://101.200.63.245:8080/wash/getRecentWashList" parameters:params success:success failure:failed];
}

#pragma mark - 洗车场我的

+ (void)obtainCarWashInfo:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    [self GET:manager url:@"http://101.200.63.245:8080/wash/getWashInfo" parameters:nil success:success failure:failed];
}

+ (void)updateCarWashAddress:(NSInteger)washID
                     address:(NSString *)address
                    latitude:(CGFloat)latitude
                   longitude:(CGFloat)longitude
                     success:(SuccessBlock)success
                      failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    NSDictionary *params = @{
                             @"washId":[NSNumber numberWithInteger:washID],
                             @"address":address,
                             @"lat":[NSString stringWithFormat:@"%.8f", latitude],
                             @"lng":[NSString stringWithFormat:@"%.8f", longitude]
                             };
    [self POST:manager url:@"http://101.200.63.245:8080/wash/updataWashAddress" parameters:params success:success failure:failed];
}

+ (void)obtainCarWashCertificationInfo:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    [self GET:manager url:@"http://101.200.63.245:8080/wash/getAuthenticationInfo" parameters:nil success:success failure:failed];
}

+ (void)registerWash:(RegisterWashParam *)param success:(SuccessBlock)success failed:(FailedBlock)failed {
    NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithCapacity:0];
    if (param.phone && ![param.phone isEqualToString:@""]) {
        [params setObject:param.phone forKey:@"phone"];
    }
    if (param.name && ![param.name isEqualToString:@""]) {
        [params setObject:param.name forKey:@"name"];
    }
    if (param.address && ![param.address isEqualToString:@""]) {
        [params setObject:param.address forKey:@"address"];
    }
    if (param.coordX && ![param.coordX isEqualToString:@""]) {
        [params setObject:param.coordX forKey:@"coordX"];
    }
    if (param.coordY && ![param.coordY isEqualToString:@""]) {
        [params setObject:param.coordY forKey:@"coordY"];
    }
    
    NSMutableArray *images = [[NSMutableArray alloc] initWithCapacity:0];
    if (param.license) {
        [images addObject:@{@"license":param.license}];
    }
    if (param.washCard) {
        [images addObject:@{@"washCard":param.washCard}];
    }
    if (param.idCardPositive) {
        [images addObject:@{@"idCardPositive":param.idCardPositive}];
    }
    if (param.idCardBack) {
        [images addObject:@{@"idCardBack":param.idCardBack}];
    }
    
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [self POST:manager url:@"http://101.200.63.245:8080/wash/registerWash" parameters:params images:images success:success failure:failed];
}

+ (void)obtainIncomeList:(NSInteger)washID month:(NSInteger)month success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    NSDictionary *params = @{
                             @"washId": [NSNumber numberWithInteger:washID],
                             @"month": [NSNumber numberWithInteger:month]
                             };
    [self GET:manager url:@"http://101.200.63.245:8080/wash/getEarningsList" parameters:params success:success failure:failed];
}

+ (void)obtainCarWashComment:(NSInteger)washID pageIndex:(NSInteger)pageIndex pageSize:(NSInteger)pageSize success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    NSDictionary *params = @{
                             @"washId":[NSNumber numberWithInteger:washID],
                             @"pageIndex":[NSNumber numberWithInteger:pageIndex],
                             @"pageSize":[NSNumber numberWithInteger:pageSize]
                             };
    [self GET:manager url:@"http://101.200.63.245:8080/wash/getCarWashEstimate" parameters:params success:success failure:failed];
}

+ (void)updateTradeState:(NSInteger)washID status:(NSString *)status success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    NSDictionary *params = @{@"id": [NSNumber numberWithInteger:washID], @"status": status};
    [self POST:manager url:@"http://101.200.63.245:8080/wash/updateStatus" parameters:params success:success failure:failed];
}

+ (void)addOrModifyService:(ServiceParam *)param success:(SuccessBlock)success failure:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    NSDictionary *params = [param dictionaryFromModel];
    [self POST:manager url:@"http://101.200.63.245:8080/wash/publishService" parameters:params success:success failure:failed];
}

+ (void)deleteWashService:(NSInteger)washID serviceID:(NSInteger)serviceID success:(SuccessBlock)success failure:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    NSDictionary *params = @{@"washId": [NSNumber numberWithInteger:washID], @"serviceId": [NSNumber numberWithInteger:serviceID]};
    [self POST:manager url:@"http://101.200.63.245:8080/wash/deleteService" parameters:params success:success failure:failed];
}

+ (void)addOrModifyCommodity:(CommodityParam *)param success:(SuccessBlock)success failure:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    NSDictionary *params = @{
                             @"id": param.commodityID,
                             @"name": param.name,
                             @"currentPrice": param.currentPrice,
                             @"originalPrice": param.originalPrice,
                             @"describe": param.describe
                             };
    NSArray *images = @[@{@"commodityImg": param.commodityImg}];
    [self POST:manager url:@"http://101.200.63.245:8080/commodity/addCommodity" parameters:params images:images success:success failure:failed];
}

+ (void)deleteCommodity:(NSInteger)commodityID success:(SuccessBlock)success failure:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    NSDictionary *params = @{@"id": [NSNumber numberWithInteger:commodityID]};
    [self POST:manager url:@"http://101.200.63.245:8080/commodity/deleteCommodity" parameters:params success:success failure:failed];
}

+ (void)extractCash:(NSInteger)washID money:(NSString *)money success:(SuccessBlock)success failure:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    NSDictionary *params = @{
                             @"washId": [NSString stringWithFormat:@"%li", washID],
                             @"money": money
                             };
    [self POST:manager url:@"http://101.200.63.245:8080/wash/drawDeposits" parameters:params success:success failure:failed];
}

+ (void)obtainBalance:(NSInteger)washID success:(SuccessBlock)success failure:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.requestSerializer.timeoutInterval = kTimeOutInterval;
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    NSDictionary *params = @{@"washId": [NSString stringWithFormat:@"%li", washID]};
    [self GET:manager url:@"http://101.200.63.245:8080/wash/getBalance" parameters:params success:success failure:failed];
}

#pragma mark - GET Method

+ (void)GET:(AFHTTPSessionManager *)manager url:(NSString *)url parameters:(NSDictionary *)params success:(SuccessBlock)success failure:(FailedBlock)failed {
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

#pragma mark - POST Method

+ (void)POST:(AFHTTPSessionManager *)manager url:(NSString *)url parameters:(NSDictionary *)params success:(SuccessBlock)success failure:(FailedBlock)failed {
    [manager POST:url parameters:params progress:nil success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
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

+ (void)POST:(AFHTTPSessionManager *)manager url:(NSString *)url parameters:(NSDictionary *)params images:(NSArray <NSDictionary <NSString *, UIImage *>*>*)images success:(SuccessBlock)success failure:(FailedBlock)failed {
    [manager POST:url parameters:params constructingBodyWithBlock:^(id<AFMultipartFormData>  _Nonnull formData) {
        for (NSDictionary *dic in images) {
            NSArray *keys = dic.allKeys;
            UIImage *image = dic[keys[0]];
            if (!image) {
                continue;
            }
            NSData *imageData = UIImageJPEGRepresentation(dic[keys[0]], 1.0);
            
            NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
            formatter.dateFormat = @"yyyyMMddHHmmss";
            NSString *str = [formatter stringFromDate:[NSDate date]];
            NSString *fileName = [NSString stringWithFormat:@"%@.jpg", str];
            
            [formData appendPartWithFileData:imageData name:keys[0] fileName:fileName mimeType:@"image/jpg"];
        }
    } progress:nil success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        NSLog(@"responseObject = %@", responseObject);
        if (responseObject) {
            success(responseObject, YES);
        } else {
            success(@{@"msg": @"暂无数据"}, NO);
        }
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        failed(error);
    }];
} // func

@end
