//
//  NetworkTools.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/19.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "AFNetworking.h"

@interface NearbyWashListParam : NSObject

@property (nonatomic, strong) NSNumber *lng;        // 经度
@property (nonatomic, strong) NSNumber *lat;        // 纬度
@property (nonatomic, assign) NSInteger count;      // 个数
@property (nonatomic, assign) BOOL showAll;         // 是否显示未营业的洗车场（0-否，1-是），默认0
@property (nonatomic, assign) CGFloat priceLimit;   // 价格限制(传0不限制)
@property (nonatomic, assign) NSInteger radius;     // 距离限制(km)，<0不做距离限制

@end

typedef void(^SuccessBlock)(NSDictionary *response, BOOL isSuccess);
typedef void(^FailedBlock)(NSError *error);

@interface NetworkTools : AFHTTPSessionManager

+ (NetworkTools *)sharedInstance;

#pragma mark - 公告接口

/**
 *  获取验证码
 */
- (void)obtainVerificationCodeWithPhoneNumber:(NSString *)phoneNumber
                                      success:(SuccessBlock)success
                                       failed:(FailedBlock)failed;
/**
 *  登录
 */
- (void)loginWithPhoneNumber:(NSString *)phoneNumber
                        code:(NSUInteger)code
                    userType:(NSUInteger)type
                     success:(SuccessBlock)success
                      failed:(FailedBlock)failed;
/**
 *  验证token
 */
- (void)checkToken:(NSString *)token
            userID:(NSString *)userID
           isOwner:(BOOL)isOwner
           success:(SuccessBlock)success
            failed:(FailedBlock)failed;
/**
 *  获取用户信息
 */
- (void)obtainUserInfoWithUserID:(NSString *)userID
                         success:(SuccessBlock)success
                          failed:(FailedBlock)failed;

/**
 *  获取升级
 */


#pragma mark - 车主首页

/**
 *  获取附近洗车场
 */
- (void)obtainNearbyWashList:(NearbyWashListParam *)param
                     success:(SuccessBlock)success
                      failed:(FailedBlock)failed;
/**
 *  获取推荐商品
 */
- (void)obtainRecommendCommodity:(NSInteger)count
                       longitude:(NSNumber *)longitude
                        latitude:(NSNumber *)latitude
                         success:(SuccessBlock)success
                          failed:(FailedBlock)failed;

#pragma mark - 车主我的

/**
 *  获取优惠券列表
 *  @param isRedeemable 1-获取我可兑换的优惠券列表 0-获取我已兑换的优惠券列表
 */
- (void)obtainMyCouponListWithAuthentication:(NSString *)authentication
                                isRedeemable:(BOOL)isRedeemable
                                     success:(SuccessBlock)success
                                      failed:(FailedBlock)failed;

/**
 *  获取消费记录
 */
- (void)obtainExpensesRecordWithAuthentication:(NSString *)authentication
                                       success:(SuccessBlock)success
                                        failed:(FailedBlock)failed;

/**
 *  获取评价列表
 */
- (void)obtainEvaluateListWithAuthentication:(NSString *)authentication
                                   pageIndex:(NSInteger)pageIndex
                                    pageSize:(NSInteger)pageSize
                                     success:(SuccessBlock)success
                                      failed:(FailedBlock)failed;

/**
 *  兑换优惠券
 */
- (void)convertIntegralToCouponWithAuthentication:(NSString *)authentication
                                         couponID:(NSString *)couponID
                                          success:(SuccessBlock)success
                                           failed:(FailedBlock)failed;

#pragma mark - 天气

#pragma mark - 地图

- (void)GET:(NSString *)url parameters:(NSDictionary *)params success:(SuccessBlock)success failure:(FailedBlock)failed;

@end
