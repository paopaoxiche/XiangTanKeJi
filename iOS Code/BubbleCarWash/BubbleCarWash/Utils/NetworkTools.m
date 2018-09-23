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
        _couponId = @"0";
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

- (void)setRequestHeader {
    AFHTTPSessionManager *manager = [NetworkTools sharedInstance];
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
}

#pragma mark - 公告接口

- (void)obtainVerificationCodeWithPhoneNumber:(NSString *)phoneNumber
                                      success:(SuccessBlock)success
                                       failed:(FailedBlock)failed {
    [self GET:@"user/getMessageCode" parameters:@{@"phone": phoneNumber} success:success failure:failed];
}

- (void)loginWithPhoneNumber:(NSString *)phoneNumber
                        code:(NSUInteger)code userType:(NSUInteger)type
                     success:(SuccessBlock)success
                      failed:(FailedBlock)failed {
    NSDictionary *params = @{@"phone": phoneNumber,
                             @"code": [NSNumber numberWithUnsignedInteger:code],
                             @"type": [NSNumber numberWithUnsignedInteger:type]};
    [self GET:@"user/login" parameters:params success:success failure:failed];
}

- (void)checkToken:(NSString *)token
            userID:(NSString *)userID
           isOwner:(BOOL)isOwner
           success:(SuccessBlock)success
            failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [NetworkTools sharedInstance];
    NSString *authenStr = [NSString stringWithFormat:@"user_id=%@,token=%@", userID, token];
    NSString *url = isOwner ? @"user/checkCarOwner" : @"user/checkCarWash";
    [manager.requestSerializer setValue:authenStr forHTTPHeaderField:@"authentication"];
    [self GET:url parameters:nil success:success failure:failed];
}

- (void)obtainUserInfoWithUserID:(NSString *)userID success:(SuccessBlock)success failed:(FailedBlock)failed {
    NSDictionary *params = @{@"id": userID};
    NSString *url = @"user/info";
    [self GET:url parameters:params success:success failure:failed];
}

- (void)updateUserNickName:(NSString *)nickName success:(SuccessBlock)success failed:(FailedBlock)failed {
    [self setRequestHeader];
    [self POST:@"user/update" parameters:@{@"nickname": nickName} success:success failure:failed];
}

- (void)updateUserAvatar:(UIImage *)image success:(SuccessBlock)success failed:(FailedBlock)failed {
    [self setRequestHeader];
    [self POST:@"user/update" parameters:nil images:@[@{@"file": image}] success:success failure:failed];
}

- (void)obtainUpgradeInfo:(SuccessBlock)success failed:(FailedBlock)failed {
    NSDictionary *params = @{@"systemType": @"1", @"version": [GlobalMethods productVersion]};
    [self GET:@"user/getAppVersion" parameters:params success:success failure:failed];
}

- (void)submitFeedBackInfo:(FeedBackParam *)param success:(SuccessBlock)success failed:(FailedBlock)failed {
    NSDictionary *params = [param dictionaryFromModel];
    [self POST:@"feedback/submit" parameters:params success:success failure:failed];
}

#pragma mark - 洗车场

#pragma mark - 车主首页

- (void)obtainNearbyWashList:(NearbyWashListParam *)param
                     success:(SuccessBlock)success
                      failed:(FailedBlock)failed {
    NSDictionary *params = [param dictionaryFromModel];
    NSString *url = @"wash/getNearbyWashServiceList";
    [self GET:url parameters:params success:success failure:failed];
}

- (void)obtainRecommendCommodity:(NSInteger)count
                       longitude:(NSNumber *)longitude
                        latitude:(NSNumber *)latitude
                         success:(SuccessBlock)success
                          failed:(FailedBlock)failed {
    NSDictionary *params = @{@"count": [NSNumber numberWithInteger:count], @"lng":longitude, @"lat": latitude};
    NSString *url = @"wash/getRecommendCommodity";
    [self GET:url parameters:params success:success failure:failed];
}

- (void)obtainCarWashInfo:(NSInteger)washID success:(SuccessBlock)success failed:(FailedBlock)failed {
    NSString *url = @"wash/getCarWashDetail";
    [self GET:url parameters:@{@"washId": [NSNumber numberWithInteger:washID]} success:success failure:failed];
}

- (void)obtainCarWashServiceList:(NSInteger)washID success:(SuccessBlock)success failed:(FailedBlock)failed {
    NSDictionary *params = @{@"washId": [NSNumber numberWithInteger:washID],
                             @"pageIndex": [NSNumber numberWithInteger:0],
                             @"pageSize": [NSNumber numberWithInteger:20]};
    NSString *url = @"wash/getServiceList";
    [self GET:url parameters:params success:success failure:failed];
}

- (void)obtainCarWashCommodityList:(NSInteger)washID success:(SuccessBlock)success failed:(FailedBlock)failed {
    NSDictionary *params = @{@"washId": [NSNumber numberWithInteger:washID],
                             @"pageIndex": [NSNumber numberWithInteger:0],
                             @"pageSize": [NSNumber numberWithInteger:20]};
    NSString *url = @"wash/getCommodityList";
    [self GET:url parameters:params success:success failure:failed];
}

#pragma mark - 车主我的

- (void)obtainMyCouponList:(BOOL)isRedeemable success:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [NetworkTools sharedInstance];
    NSString *url = isRedeemable ? @"http://101.200.63.245:8080/carOwner/getAllCoupon" : @"carOwner/getMyCoupon";
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    [self GET:url parameters:nil success:success failure:failed];
}

- (void)obtainExpensesRecord:(SuccessBlock)success failed:(FailedBlock)failed {
    AFHTTPSessionManager *manager = [NetworkTools sharedInstance];
    NSString *url = @"carOwner/getUserConsume";
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    [self GET:url parameters:nil success:success failure:failed];
}

- (void)obtainEvaluateListWithPageIndex:(NSInteger)pageIndex
                               pageSize:(NSInteger)pageSize
                                success:(SuccessBlock)success
                                 failed:(FailedBlock)failed {
    NSDictionary *params = @{@"pageIndex": [NSNumber numberWithInteger:pageIndex], @"pageSize": [NSNumber numberWithInteger:pageSize]};
    AFHTTPSessionManager *manager = [NetworkTools sharedInstance];;
    NSString *url = @"carOwner/getEvaluateList";
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    [self GET:url parameters:params success:success failure:failed];
}

- (void)submitEvaluate:(NSUInteger)consumeID
                 grade:(NSInteger)grade
               content:(NSString *)content
               success:(SuccessBlock)success
                failed:(FailedBlock)failed {
    NSDictionary *params = @{@"consumeId": [NSNumber numberWithUnsignedInteger:consumeID], @"grade": [NSNumber numberWithInteger:grade], @"content": content};
    [self setRequestHeader];
    [self POST:@"carOwner/evaluateRecord" parameters:params success:success failure:failed];
}

- (void)convertIntegralToCouponWithCouponID:(NSString *)couponID
                                    success:(SuccessBlock)success
                                     failed:(FailedBlock)failed {
    NSDictionary *params = @{@"couponId": couponID};
    AFHTTPSessionManager *manager = [NetworkTools sharedInstance];
    NSString *url = @"carOwner/exchangePoint";
    [manager.requestSerializer setValue:[UserManager sharedInstance].authentication
                     forHTTPHeaderField:@"authentication"];
    [self GET:url parameters:params success:success failure:failed];
}

- (void)obtainCarTypeList:(SuccessBlock)success failed:(FailedBlock)failed {
    [self GET:@"car/models" parameters:nil success:success failure:failed];
}

- (void)obtainModelReviewList:(NSInteger)status success:(SuccessBlock)success failed:(FailedBlock)failed {
    [self setRequestHeader];
    [self GET:@"car/list" parameters:@{@"status": [NSNumber numberWithInteger:status]} success:success failure:failed];
}

- (void)submitModelReview:(NSString *)modelID
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
    
    [self setRequestHeader];
    if (images.count > 0) {
        [self POST:@"car/register" parameters:@{@"model": modelID} images:images success:success failure:failed];
    } else {
        [self POST:@"car/register" parameters:@{@"model": modelID} success:success failure:failed];
    }
}

- (void)obtainModelDetail:(NSInteger)modelID success:(SuccessBlock)success failed:(FailedBlock)failed {
    [self setRequestHeader];
    [self GET:@"car/detail" parameters:@{@"id": [NSNumber numberWithInteger:modelID]} success:success failure:failed];
}

#pragma mark - 车主支付

- (void)createOrder:(CreateOrderParam *)param success:(SuccessBlock)success failed:(FailedBlock)failed {
    [self setRequestHeader];
    NSDictionary *params = [param dictionaryFromModel];
    [self POST:@"carOwner/createConsume" parameters:params success:success failure:failed];
}

#pragma mark - 洗车场首页

- (void)obtainRecentCarWashList:(NSInteger)washID count:(NSInteger)count success:(SuccessBlock)success failed:(FailedBlock)failed {
    
}

#pragma mark - 洗车场我的

- (void)registerWash:(RegisterWashParam *)param success:(SuccessBlock)success failed:(FailedBlock)failed {
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
    
    [self POST:@"wash/registerWash" parameters:params images:images success:success failure:failed];
}

#pragma mark - GET Method

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

#pragma mark - POST Method

- (void)POST:(NSString *)url parameters:(NSDictionary *)params success:(SuccessBlock)success failure:(FailedBlock)failed {
    [self POST:url parameters:params progress:nil success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
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

- (void)POST:(NSString *)url parameters:(NSDictionary *)params images:(NSArray <NSDictionary <NSString *, UIImage *>*>*)images success:(SuccessBlock)success failure:(FailedBlock)failed {
    [self POST:url parameters:params constructingBodyWithBlock:^(id<AFMultipartFormData>  _Nonnull formData) {
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
