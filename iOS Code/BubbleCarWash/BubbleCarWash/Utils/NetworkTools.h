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

@interface FeedBackParam : NSObject

@property (nonatomic, copy) NSString *systemType;
@property (nonatomic, copy) NSString *device;
@property (nonatomic, copy) NSString *versionCode;
@property (nonatomic, copy) NSString *systemVersion;
@property (nonatomic, copy) NSString *content;
@property (nonatomic, copy) NSString *contactInformation;

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
- (void)obtainUserInfoWithUserID:(NSString *)userID success:(SuccessBlock)success failed:(FailedBlock)failed;
/**
 *  更新用户昵称
 */
- (void)updateUserNickName:(NSString *)nickName success:(SuccessBlock)success failed:(FailedBlock)failed;
/**
 *  更新用户头像
 */
- (void)updateUserAvatar:(UIImage *)image success:(SuccessBlock)success failed:(FailedBlock)failed;
/**
 *  获取升级
 */
- (void)obtainUpgradeInfo:(SuccessBlock)success failed:(FailedBlock)failed;

/**
 *  上传反馈信息
 */
- (void)submitFeedBackInfo:(FeedBackParam *)param success:(SuccessBlock)success failed:(FailedBlock)failed;

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
/**
 *  获取洗车场信息
 */
- (void)obtainCarWashInfo:(NSInteger)washID success:(SuccessBlock)success failed:(FailedBlock)failed;
/**
 *  获取洗车场服务列表
 */
- (void)obtainCarWashServiceList:(NSInteger)washID success:(SuccessBlock)success failed:(FailedBlock)failed;
/**
 *  获取洗车场商品列表
 */
- (void)obtainCarWashCommodityList:(NSInteger)washID success:(SuccessBlock)success failed:(FailedBlock)failed;

#pragma mark - 车主我的

/**
 *  获取优惠券列表
 *  @param isRedeemable 1-获取我可兑换的优惠券列表 0-获取我已兑换的优惠券列表
 */
- (void)obtainMyCouponList:(BOOL)isRedeemable success:(SuccessBlock)success failed:(FailedBlock)failed;

/**
 *  获取消费记录
 */
- (void)obtainExpensesRecord:(SuccessBlock)success failed:(FailedBlock)failed;

/**
 *  获取评价列表
 */
- (void)obtainEvaluateListWithPageIndex:(NSInteger)pageIndex
                               pageSize:(NSInteger)pageSize
                                success:(SuccessBlock)success
                                 failed:(FailedBlock)failed;

/**
 *  兑换优惠券
 */
- (void)convertIntegralToCouponWithCouponID:(NSString *)couponID
                                    success:(SuccessBlock)success
                                     failed:(FailedBlock)failed;
/**
 *  获取车型列表
 */
- (void)obtainCarTypeList:(SuccessBlock)success failed:(FailedBlock)failed;
/**
 *  获取车型审核列表
 */
- (void)obtainModelReviewList:(NSInteger)status success:(SuccessBlock)success failed:(FailedBlock)failed;
/**
 *  提交车型审核
 */
- (void)submitModelReview:(NSString *)modelID
                    cover:(UIImage *)cover
                     back:(UIImage *)back
                  success:(SuccessBlock)success
                   failed:(FailedBlock)failed;
/**
 *  获取车型登记详情
 */
- (void)obtainModelDetail:(NSInteger)modelID success:(SuccessBlock)success failed:(FailedBlock)failed;

#pragma mark - 天气

#pragma mark - 地图

- (void)GET:(NSString *)url parameters:(NSDictionary *)params success:(SuccessBlock)success failure:(FailedBlock)failed;
- (void)POST:(NSString *)url parameters:(NSDictionary *)params success:(SuccessBlock)success failure:(FailedBlock)failed;

@end
