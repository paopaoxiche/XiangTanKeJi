//
//  CouponListModel.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/2.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CouponListModel.h"
#import "UserManager.h"
#import "UserInfoModel.h"
#import "NetworkTools.h"
#import "GlobalMethods.h"

@interface CouponListModel ()

@end

@implementation CouponListModel

+ (void)loadMyCouponList:(CouponResultBlock)result {
    [self loadCouponList:NO reslut:result];
}

+ (void)loadRedeemableCouponList:(CouponResultBlock)result {
    [self loadCouponList:YES reslut:result];
}

+ (void)loadCouponList:(BOOL)isRedeemable reslut:(CouponResultBlock)result {
    [NetworkTools obtainMyCouponList:isRedeemable success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSDictionary *dataArr = [response objectForKey:@"data"];
            NSMutableArray *couponList = [NSMutableArray arrayWithCapacity:dataArr.count];
            for (NSDictionary *dic in dataArr) {
                if (isRedeemable) {
                    RedeemableCouponModel *model = [[RedeemableCouponModel alloc] initWithDic:dic];
                    [couponList addObject:model];
                } else {
                    MyCouponModel *model = [[MyCouponModel alloc] initWithDic:dic];
                    [couponList addObject:model];
                } // else
            } // for
            
            result(couponList);
        } else {
            result(@[]);
        }
    } failed:^(NSError *error) {
        result(@[]);
    }];
}

@end

@interface RedeemableCouponModel ()

/// 优惠券详情
@property (nonatomic, copy) NSString *detail;
/// 优惠券开始时间
@property (nonatomic, copy) NSString *startTime;
/// 优惠券结束时间
@property (nonatomic, copy) NSString *endTime;

@end

@implementation RedeemableCouponModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _name = [dic objectForKey:@"couponName"];
        _price = [NSString stringWithFormat:@"%@", [dic objectForKey:@"denomination"]];
        _detail = [dic objectForKey:@"detail"];
        _startTime = [GlobalMethods conversionTimestampToStr:[dic objectForKey:@"startTime"]
                                                  dateFormat:@"yyyy.MM.dd"];
        _endTime = [GlobalMethods conversionTimestampToStr:[dic objectForKey:@"endTime"]
                                                dateFormat:@"yyyy.MM.dd"];
        _couponID = [[dic objectForKey:@"id"] unsignedIntegerValue];
        _type = [[dic objectForKey:@"issuer"] integerValue];
        _integral = [NSString stringWithFormat:@"%@", [dic objectForKey:@"points"]];
        _validityPeroid = [NSString stringWithFormat:@"%@ - %@", _startTime, _endTime];
        _couponType = _type == CouponPlatformTypeGeneral ? @"通用券" : @"商家券";
    }
    
    return self;
}

- (void)convertIntegralToCoupon:(CodeResultBlock)result {
    NSString *couponid = [NSString stringWithFormat:@"%li", _couponID];
    [NetworkTools convertIntegralToCouponWithCouponID:couponid success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        result(code);
    } failed:^(NSError *error) {
        result(-1);
    }];
}

@end

@interface MyCouponModel ()

/// 优惠券id
@property (nonatomic, assign) NSUInteger couponID;
/// 优惠券类型 1减免 2折扣
@property (nonatomic, assign) NSUInteger couponType;
/// 状态 1有效 2失效 3过期
@property (nonatomic, assign) NSUInteger status;
/// 折扣
@property (nonatomic, copy) NSString *discount;

@end

@implementation MyCouponModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _couponType = [[dic objectForKey:@"couponType"] unsignedIntegerValue];
        _discount = [dic objectForKey:@"discount"];
        _endTime = [[dic objectForKey:@"endDate"] longLongValue];
        _validityPeroid = [NSString stringWithFormat:@"有效期至 %@", [GlobalMethods conversionTimestampToStr:[[dic objectForKey:@"endDate"] longValue] dateFormat:@"yyyy-MM-dd"]];
        _couponID = [[dic objectForKey:@"id"] unsignedIntegerValue];
        _price = [NSString stringWithFormat:@"%@", [dic objectForKey:@"price"]];
        _status = [[dic objectForKey:@"status"] unsignedIntegerValue];
        _title = [dic objectForKey:@"title"];
        _avatarUrl = [dic objectForKey:@"washHeader"];
        _washName = [dic objectForKey:@"washName"];
        _limitPrice = [[dic objectForKey:@"minPrice"] floatValue];
    }
    
    return self;
}

@end
