//
//  ExpensesRecordListModel.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/2.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "ExpensesRecordListModel.h"
#import "UserManager.h"
#import "NetworkTools.h"

@interface ExpensesRecordListModel ()

@end

@implementation ExpensesRecordListModel

+ (void)loadExpensesRecordList:(ResultBlock)block {
    [[NetworkTools sharedInstance] obtainExpensesRecord:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSDictionary *dataArr = [response objectForKey:@"data"];
            NSMutableArray *recordList = [NSMutableArray arrayWithCapacity:dataArr.count];
            for (NSDictionary *dic in dataArr) {
                ExpensesRecordModel *model = [[ExpensesRecordModel alloc] initWithDic:dic];
                [recordList addObject:model];
            }
            
            block(recordList);
        } else {
            block(@[]);
        }
    } failed:^(NSError *error) {
        block(@[]);
    }];
}

@end

#pragma mark -

@interface ExpensesRecordModel ()

@property (nonatomic, assign) NSUInteger carType;
@property (nonatomic, assign) NSInteger recordID;

@end

@implementation ExpensesRecordModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _recordID = [[dic objectForKey:@"id"] integerValue];
        _carType = [[dic objectForKey:@"carType"] integerValue];
        _avatarUrl = [dic objectForKey:@"carWashImg"];
        _washName = [dic objectForKey:@"carWashName"];
        _serviceName = [dic objectForKey:@"serviceName"];
        _servicePrice = [dic objectForKey:@"payment"];
        _time = [dic objectForKey:@"time"];
        _totalPrice = [dic objectForKey:@"totalPrice"];
        _isEvaluation = [[dic objectForKey:@"isEvaluation"] integerValue];
        _coupon = [[ExpensesCouponModel alloc] initWithDic:[dic objectForKey:@"coupons"]];
        
        NSArray *dataArr = [dic objectForKey:@"commodities"];
        NSMutableArray *commodities = [NSMutableArray arrayWithCapacity:dataArr.count];
        for (NSDictionary *tempDic in dataArr) {
            CommodityExpensesModel *model = [[CommodityExpensesModel alloc] initWithDic:tempDic];
            [commodities addObject:model];
        }
        _commodities = commodities;
    }
    
    return self;
}

@end

#pragma mark -

@interface ExpensesCouponModel ()

@property (nonatomic, assign) NSInteger couponID;

@end

@implementation ExpensesCouponModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _couponID = [[dic objectForKey:@"couponId"] unsignedIntegerValue];
        _couponName = [dic objectForKey:@"couponName"];
        _couponType = [[dic objectForKey:@"couponType"] integerValue] == 0 ? @"通用券" : @"商家券";
        _price = [dic objectForKey:@"couponPrice"];
    }
    
    return self;
}

@end

#pragma mark -

@interface CommodityExpensesModel ()

@property (nonatomic, assign) NSUInteger commodityID;

@end

@implementation CommodityExpensesModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _commodityID = [[dic objectForKey:@"commodityId"] unsignedIntegerValue];
        _imageUrl = [dic objectForKey:@"commodityImg"];
        _name = [dic objectForKey:@"commodityName"];
        _currentPrice = [dic objectForKey:@"price"];
        //_originalPrice = [dic objectForKey:@""];
    }
    
    return self;
}

@end
