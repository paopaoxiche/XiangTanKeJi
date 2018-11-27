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
        _servicePrice = [dic objectForKey:@"payment"];
        _totalPrice = [dic objectForKey:@"totalPrice"];
        _isEvaluation = [[dic objectForKey:@"isEvaluation"] integerValue];
        
        NSString *avatarUrl = [dic objectForKey:@"carWashImg"];
        if (avatarUrl) {
            _avatarUrl = avatarUrl;
        }
        NSString *washName = [dic objectForKey:@"carWashName"];
        if (washName) {
            _washName = washName;
        }
        NSString *time = [dic objectForKey:@"time"];
        if (time) {
            _time = time;
        }
        
        NSDictionary *coupons = [dic objectForKey:@"coupons"];
        _coupon = (coupons && coupons != [NSNull null]) ? [[ExpensesCouponModel alloc] initWithDic:coupons] : nil;
        
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
        _price = [NSString stringWithFormat:@"%lu", [[dic objectForKey:@"payPrice"] longValue]];;
        _desc = [dic objectForKey:@"carDesc"];
    }
    
    return self;
}

@end
