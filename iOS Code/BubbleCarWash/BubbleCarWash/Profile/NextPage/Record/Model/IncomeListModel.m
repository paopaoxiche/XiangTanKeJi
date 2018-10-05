//
//  IncomeListModel.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/24.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "IncomeListModel.h"
#import "GlobalMethods.h"

@implementation IncomeModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _date = [GlobalMethods convertDate:dic[@"time"] outputFormat:@"MM月dd日"];
        _totalMoney = [dic[@"totalMoney"] floatValue];
        
        NSMutableArray *list = [[NSMutableArray alloc] init];
        for (NSDictionary *temp in dic[@"items"]) {
            IncomeRecordModel *model = [[IncomeRecordModel alloc] initWithDic:temp];
            [list addObject:model];
        }
        self.dayIncomeList = list;
    }
    
    return self;
}

@end

@implementation IncomeRecordModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _recordID = [[dic objectForKey:@"id"] integerValue];
        _money = [[dic objectForKey:@"money"] floatValue];
        _carType = [dic objectForKey:@"carType"];
        _carTypeDesc = [dic objectForKey:@"carTypeText"];
        _title = [dic objectForKey:@"title"];
    }
    
    return self;
}

@end
