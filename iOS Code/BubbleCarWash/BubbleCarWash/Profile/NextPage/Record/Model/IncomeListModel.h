//
//  IncomeListModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/24.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@class IncomeRecordModel;

@interface IncomeModel : NSObject

@property (nonatomic, copy) NSString *date;
@property (nonatomic, copy) NSArray <IncomeRecordModel *>*dayIncomeList;
@property (nonatomic, assign) CGFloat totalMoney;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end

@interface IncomeRecordModel : NSObject

@property (nonatomic, assign) NSInteger recordID;
@property (nonatomic, assign) CGFloat money;
@property (nonatomic, copy) NSString *carType;
@property (nonatomic, copy) NSString *carTypeDesc;
@property (nonatomic, copy) NSString *title;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end
