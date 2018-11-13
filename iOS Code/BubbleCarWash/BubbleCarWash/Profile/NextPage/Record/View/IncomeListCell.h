//
//  IncomeListCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface IncomeListTitleCell : UITableViewCell

- (void)setDate:(NSString *)date dayIncome:(CGFloat)dayIncome;

@end

@interface IncomeListContentCell : UITableViewCell

- (void)setCarType:(NSString *)carType money:(CGFloat)money;

@end
