//
//  ExpensesRecordCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@class TotalConsumptionCell;

@protocol TotalConsumptionCellDelegate <NSObject>

- (void)onEvaluationButtonClicked:(TotalConsumptionCell *)cell;

@end

@interface ExpensesRecordTitleCell : UITableViewCell

@property (nonatomic, copy) NSString *avatarUrl;
@property (nonatomic, copy) NSString *washName;
@property (nonatomic, copy) NSString *time;

@end

@interface ExpensesRecordContentCell : UITableViewCell

@property (nonatomic, copy) NSString *imageUrl;
@property (nonatomic, copy) NSString *name;
@property (nonatomic, copy) NSString *price;
@property (nonatomic, copy) NSString *couponType;
@property (nonatomic, copy) NSString *couponPrice;
@property (nonatomic, assign) BOOL isShowCoupon;

@end

@interface TotalConsumptionCell : UITableViewCell

@property (nonatomic, weak) id<TotalConsumptionCellDelegate> delegate;
@property (nonatomic, copy) NSString *totalPrice;
@property (nonatomic, assign) BOOL isEvaluation;

@end
