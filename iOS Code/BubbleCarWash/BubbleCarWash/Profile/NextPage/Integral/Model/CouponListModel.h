//
//  CouponListModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/2.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef NS_ENUM(NSInteger, CouponPlatformType) {
    CouponPlatformTypeGeneral,      // 平台通用券
    CouponPlatformTypeMerchant      // 商家券
};

typedef void(^CouponResultBlock)(NSArray *result);

@interface CouponListModel : NSObject

/**
 *  获取我的优惠券
 */
+ (void)loadMyCouponList:(CouponResultBlock)result;
/**
 *  获取我可兑换的优惠券
 */
+ (void)loadRedeemableCouponList:(CouponResultBlock)result;

@end

typedef void(^CodeResultBlock)(NSInteger code);

@interface RedeemableCouponModel : NSObject

/// 有效期
@property (nonatomic, copy) NSString *validityPeroid;
/// 优惠券金额
@property (nonatomic, copy) NSString *price;
/// 优惠券名称
@property (nonatomic, copy) NSString *name;
/// 所需积分
@property (nonatomic, copy) NSString *integral;
/// 优惠券类型字符串
@property (nonatomic, copy) NSString *couponType;
/// 优惠券id
@property (nonatomic, assign) NSUInteger couponID;
/// 优惠券类型 0-平台券 1-商家券
@property (nonatomic, assign) CouponPlatformType type;

- (instancetype)initWithDic:(NSDictionary *)dic;
/**
 *  使用积分兑换优惠券
 */
- (void)convertIntegralToCoupon:(CodeResultBlock)result;

@end

@interface MyCouponModel : NSObject

/// 有效期
@property (nonatomic, copy) NSString *validityPeroid;
/// 优惠券金额
@property (nonatomic, copy) NSString *price;
/// 优惠券标题
@property (nonatomic, copy) NSString *title;
/// 头像
@property (nonatomic, copy) NSString *avatarUrl;
/// 洗车场名称
@property (nonatomic, copy) NSString *washName;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end
