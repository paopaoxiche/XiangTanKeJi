//
//  NetworkTools.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/19.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface NearbyWashListParam : NSObject

@property (nonatomic, assign) CGFloat lng;          // 经度
@property (nonatomic, assign) CGFloat lat;          // 纬度
@property (nonatomic, assign) NSInteger count;      // 个数
@property (nonatomic, assign) BOOL isShowAll;       // 是否显示未营业的洗车场（0-否，1-是），默认0
@property (nonatomic, assign) CGFloat priceLimit;   // 价格限制
@property (nonatomic, assign) NSInteger radius;     // 距离限制(km)，<0不做距离限制

@end

typedef void(^SuccessBlock)(NSDictionary *response, BOOL isSuccess);
typedef void(^FailedBlock)(NSError *error);

@interface NetworkTools : NSObject

#pragma mark - 公告接口

/**
 *  获取验证码
 */
+ (void)obtainVerificationCodeWithPhoneNumber:(NSString *)phoneNumber
                                      success:(SuccessBlock)success
                                       failed:(FailedBlock)failed;
/**
 *  登录
 */
+ (void)loginWithPhoneNumber:(NSString *)phoneNumber
                        code:(NSUInteger)code
                    userType:(NSUInteger)type
                     success:(SuccessBlock)success
                      failed:(FailedBlock)failed;
/**
 *  验证token
 */
+ (void)checkToken:(NSString *)token
            userID:(NSString *)userID
           isOwner:(BOOL)isOwner
           success:(SuccessBlock)success
            failed:(FailedBlock)failed;
/**
 *  获取用户信息
 */
+ (void)obtainUserInfoWithUserID:(NSString *)userID
                         success:(SuccessBlock)success
                          failed:(FailedBlock)failed;

#pragma mark - 车主

/**
 *  获取附近洗车场
 */
+ (void)obtainNearbyWashList:(NearbyWashListParam *)param
                     success:(SuccessBlock)success
                      failed:(FailedBlock)failed;
/**
 *  获取推荐商品
 */
+ (void)obtainRecommendCommodity:(NSInteger)count
                       longitude:(NSString *)longitude
                        latitude:(NSString *)latitude
                         success:(SuccessBlock)success
                          failed:(FailedBlock)failed;

@end
