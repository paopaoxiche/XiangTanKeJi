//
//  NetworkTools.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/19.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "AFNetworking.h"

/// 附近洗车场列表
@interface NearbyWashListParam : NSObject

@property (nonatomic, strong) NSNumber *lng;        // 经度
@property (nonatomic, strong) NSNumber *lat;        // 纬度
@property (nonatomic, assign) NSInteger count;      // 个数
@property (nonatomic, assign) BOOL showAll;         // 是否显示未营业的洗车场（0-否，1-是），默认0
@property (nonatomic, assign) CGFloat priceLimit;   // 价格限制(传0不限制)
@property (nonatomic, assign) NSInteger radius;     // 距离限制(km)，<0不做距离限制

@end

/// 反馈
@interface FeedBackParam : NSObject

@property (nonatomic, copy) NSString *systemType;
@property (nonatomic, copy) NSString *device;
@property (nonatomic, copy) NSString *versionCode;
@property (nonatomic, copy) NSString *systemVersion;
@property (nonatomic, copy) NSString *content;
@property (nonatomic, copy) NSString *contactInformation;

@end

/// 创建订单
@interface CreateOrderParam : NSObject

@property (nonatomic, copy) NSString *washServiceId;
@property (nonatomic, copy) NSString *commoditys;
@property (nonatomic, copy) NSString *couponId;
@property (nonatomic, copy) NSString *payType;

@end

/// 注册洗车场（工商认证）
@interface RegisterWashParam : NSObject

@property (nonatomic, copy) NSString *phone;            // 手机号码
@property (nonatomic, copy) NSString *name;             // 洗车场名称
@property (nonatomic, copy) NSString *address;          // 地址
@property (nonatomic, copy) NSString *coordX;           // 纬度
@property (nonatomic, copy) NSString *coordY;           // 经度
@property (nonatomic, copy) NSString *province;         // 省
@property (nonatomic, copy) NSString *city;            // 市
@property (nonatomic, copy) NSString *district;         // 区
@property (nonatomic, strong) UIImage *license;         // 营业执照文件
@property (nonatomic, strong) UIImage *washCard;        // 洗车证
@property (nonatomic, strong) UIImage *idCardPositive;  // 身份证正面
@property (nonatomic, strong) UIImage *idCardBack;      // 身份证背面

@end

@interface CommodityParam : NSObject

/// 0 - 新增商品，具体商品id - 修改商品
@property (nonatomic, copy) NSString *commodityID;
@property (nonatomic, copy) NSString *name;
@property (nonatomic, copy) NSString *currentPrice;
@property (nonatomic, copy) NSString *originalPrice;
@property (nonatomic, copy) NSString *describe;
@property (nonatomic, strong) UIImage *commodityImg;

@end

@interface ServiceParam : NSObject

@property (nonatomic, strong) NSNumber *washId;
/// 0 - 新增服务，具体服务id - 修改服务
@property (nonatomic, strong) NSNumber *serviceId;
@property (nonatomic, copy) NSString *name;
@property (nonatomic, copy) NSString *describe;
@property (nonatomic, copy) NSString *price;
@property (nonatomic, copy) NSString *carModel;

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
 *  提交评价
 */
- (void)submitEvaluate:(NSUInteger)consumeID
                 grade:(NSInteger)grade
               content:(NSString *)content
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

#pragma mark - 车主支付

/**
 *  创建订单
 */
- (void)createOrder:(CreateOrderParam *)param success:(SuccessBlock)success failed:(FailedBlock)failed;

#pragma mark - 洗车场首页

/**
 *  获取近期洗车列表
 */
- (void)obtainRecentCarWashes:(NSInteger)washID count:(NSInteger)count success:(SuccessBlock)success failed:(FailedBlock)failed;

#pragma mark - 洗车场我的

/**
 *  获取洗车场详细信息
 */
//- (void)obtainCarWashDetail:(NSInteger)washID success:(SuccessBlock)success failed:(FailedBlock)failed;
/**
 *  获取洗车场信息
 */
- (void)obtainCarWashInfo:(SuccessBlock)success failed:(FailedBlock)failed;
/**
 *  更新洗车场地址
 */
- (void)updateCarWashAddress:(NSInteger)washID
                     address:(NSString *)address
                    latitude:(CGFloat)latitude
                   longitude:(CGFloat)longitude
                     success:(SuccessBlock)success
                      failed:(FailedBlock)failed;
/**
 *  获取工商认证状态
 */
- (void)obtainCarWashCertificationInfo:(SuccessBlock)success failed:(FailedBlock)failed;
/**
 *  提交工商认证(洗车场注册)
 */
- (void)registerWash:(RegisterWashParam *)param success:(SuccessBlock)success failed:(FailedBlock)failed;
/**
 *  获取收入列表
 */
- (void)obtainIncomeList:(NSInteger)washID month:(NSInteger)month success:(SuccessBlock)success failed:(FailedBlock)failed;
/**
 *  获取洗车场评价列表
 */
- (void)obtainCarWashComment:(NSInteger)washID pageIndex:(NSInteger)pageIndex pageSize:(NSInteger)pageSize success:(SuccessBlock)success failed:(FailedBlock)failed;
/**
 *  更新洗车场营业状态
 */
- (void)updateTradeState:(NSInteger)washID status:(NSString *)status success:(SuccessBlock)success failed:(FailedBlock)failed;
/**
 *  发布洗车服务
 */
- (void)addOrModifyService:(ServiceParam *)param success:(SuccessBlock)success failure:(FailedBlock)failed;
/**
 *  删除洗车服务
 */
- (void)deleteWashService:(NSInteger)washID serviceID:(NSInteger)serviceID success:(SuccessBlock)success failure:(FailedBlock)failed;
/**
 *  增加/修改商品
 */
- (void)addOrModifyCommodity:(CommodityParam *)param success:(SuccessBlock)success failure:(FailedBlock)failed;
/**
 *  删除商品
 */
- (void)deleteCommodity:(NSInteger)commodityID success:(SuccessBlock)success failure:(FailedBlock)failed;
/**
 *  提取现金
 */
- (void)extractCash:(NSInteger)washID money:(NSString *)money success:(SuccessBlock)success failure:(FailedBlock)failed;
/**
 *  获取余额
 */
- (void)obtainBalance:(NSInteger)washID success:(SuccessBlock)success failure:(FailedBlock)failed;

- (void)GET:(NSString *)url parameters:(NSDictionary *)params success:(SuccessBlock)success failure:(FailedBlock)failed;
- (void)POST:(NSString *)url parameters:(NSDictionary *)params success:(SuccessBlock)success failure:(FailedBlock)failed;

@end
