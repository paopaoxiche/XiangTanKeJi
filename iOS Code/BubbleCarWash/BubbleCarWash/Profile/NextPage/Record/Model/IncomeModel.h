//
//  IncomeModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface IncomeModel : NSObject

@property (nonatomic, copy) NSString *currentDate;
@property (nonatomic, copy) NSArray *dayIncomeList;

@end

@interface IncomeRecordModel : NSObject

@property (nonatomic, copy) NSString *avatarName;
@property (nonatomic, copy) NSString *carWashType;
@property (nonatomic, copy) NSString *washFee;

@end
