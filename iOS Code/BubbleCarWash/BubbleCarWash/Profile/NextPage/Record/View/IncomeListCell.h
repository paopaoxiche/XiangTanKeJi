//
//  IncomeListCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface IncomeListTitleCell : UITableViewCell

- (void)setDate:(NSString *)date dayIncome:(NSString *)dayIncome;

@end

@interface IncomeListContentCell : UITableViewCell

- (void)setAvatar:(NSString *)name carWashType:(NSString *)type washFee:(NSString *)washFee;

@end
