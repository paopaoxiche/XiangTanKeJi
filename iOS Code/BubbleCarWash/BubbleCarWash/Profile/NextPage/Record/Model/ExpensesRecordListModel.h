//
//  ExpensesRecordListModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/2.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef NS_ENUM(NSInteger, ExpensesRecordEvaluationStatus) {
    ExpensesRecordEvaluationStatusNOT,      // 未评价
    ExpensesRecordEvaluationStatusOn        // 已评价
};

typedef void(^ResultBlock)(NSArray *result);

@interface ExpensesRecordListModel : NSObject

+ (void)loadExpensesRecordList:(ResultBlock)block;
+ (void)loadRecentCarWashList:(NSInteger)count result:(ResultBlock)result;

@end

@class ExpensesCouponModel;

@interface ExpensesRecordModel : NSObject

@property (nonatomic, copy) NSString *avatarUrl;
@property (nonatomic, copy) NSString *washName;
@property (nonatomic, copy) NSString *servicePrice;
@property (nonatomic, copy) NSString *serviceName;
@property (nonatomic, copy) NSString *time;
@property (nonatomic, copy) NSString *totalPrice;
@property (nonatomic, copy) NSArray *commodities;
@property (nonatomic, strong) ExpensesCouponModel *coupon;
@property (nonatomic, assign) NSInteger isEvaluation;
@property (nonatomic, assign) NSInteger recordID;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end

@interface ExpensesCouponModel : NSObject

@property (nonatomic, copy) NSString *couponName;
@property (nonatomic, copy) NSString *price;
@property (nonatomic, copy) NSString *couponType;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end


@interface CommodityExpensesModel : NSObject

@property (nonatomic, copy) NSString *imageUrl;
@property (nonatomic, copy) NSString *name;
@property (nonatomic, copy) NSString *currentPrice;
//@property (nonatomic, copy) NSString *originalPrice;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end
