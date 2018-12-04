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
#import "CarWashInfoModel.h"
#import "GlobalMethods.h"

@interface ExpensesRecordListModel ()

@end

@implementation ExpensesRecordListModel

+ (void)loadExpensesRecordList:(ResultBlock)block {
    [NetworkTools obtainExpensesRecord:^(NSDictionary *response, BOOL isSuccess) {
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

+ (void)loadRecentCarWashList:(NSInteger)count result:(ResultBlock)result {
    [NetworkTools obtainRecentCarWashes:[UserManager sharedInstance].carWashInfo.washID count:count success:^(NSDictionary *response, BOOL isSuccess) {
        NSLog(@"respons = %@", response);
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSDictionary *dataArr = [response objectForKey:@"data"];
            NSMutableArray *recordList = [NSMutableArray arrayWithCapacity:dataArr.count];
            for (NSDictionary *dic in dataArr) {
                CarWashRecordModel *model = [[CarWashRecordModel alloc] initWithDic:dic];
                [recordList addObject:model];
            }
            
            result(recordList);
        } else {
            result(@[]);
        }
    } failed:^(NSError *error) {
        result(@[]);
    }];
}

@end

#pragma mark -

@interface ExpensesRecordModel ()

@property (nonatomic, assign) NSUInteger carType;

@end

@implementation ExpensesRecordModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _recordID = [[dic objectForKey:@"id"] integerValue];
        _carType = [[dic objectForKey:@"carType"] integerValue];
        _serviceName = [dic objectForKey:@"serviceName"];
        _servicePrice = [NSString stringWithFormat:@"￥%.2f", [[dic objectForKey:@"payment"] floatValue]];
        _totalPrice = [NSString stringWithFormat:@"￥%.2f", [[dic objectForKey:@"totalPrice"] floatValue]];
        _isEvaluation = [[dic objectForKey:@"isEvaluation"] integerValue];
        
        NSString *avatarUrl = [dic objectForKey:@"carWashImg"];
        if (avatarUrl) {
            _avatarUrl = avatarUrl;
        }
        NSString *washName = [dic objectForKey:@"carWashName"];
        if (washName) {
            _washName = washName;
        }
        long time = [[dic objectForKey:@"time"] longValue];
        if (time) {
            _time = [GlobalMethods conversionTimestampToStr:time dateFormat:@"MM.dd HH:mm"];
        }
        
        NSArray *coupons = [dic objectForKey:@"coupons"];
        _coupon = (coupons && coupons != [NSNull null] && coupons.count > 0) ? [[ExpensesCouponModel alloc] initWithDic:coupons.firstObject] : nil;
        
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
        _couponID = [[dic objectForKey:@"couponId"] integerValue];
        _couponName = [dic objectForKey:@"couponName"];
        _couponType = [[dic objectForKey:@"couponType"] integerValue] == 0 ? @"通用券" : @"商家券";
        _price = [NSString stringWithFormat:@"￥%.2f", [[dic objectForKey:@"couponPrice"] floatValue]];
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
        _currentPrice = [NSString stringWithFormat:@"￥%.2f", [[dic objectForKey:@"price"] floatValue]];
        //_originalPrice = [dic objectForKey:@""];
    }
    
    return self;
}

@end

@interface CarWashRecordModel ()

@end

@implementation CarWashRecordModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _recordID = [[dic objectForKey:@"id"] integerValue];
        _time = [GlobalMethods conversionTimestampToStr:[[dic objectForKey:@"time"] longValue] dateFormat:@"yyyy-MM-dd"];
        _avatarUrl = [dic objectForKey:@"avatar"];
        _carType = [dic objectForKey:@"carType"];
        _nickName = [dic objectForKey:@"nickname"];
        _price = [NSString stringWithFormat:@"￥%.2f", [[dic objectForKey:@"payPrice"] floatValue]];;
        _desc = [dic objectForKey:@"carDesc"];
    }
    
    return self;
}

@end
