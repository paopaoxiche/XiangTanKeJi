//
//  IncomeListCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "IncomeListCell.h"

#pragma mark - IncomeListTitleCell

@interface IncomeListTitleCell ()

@property (weak, nonatomic) IBOutlet UILabel *dateLabel;
@property (weak, nonatomic) IBOutlet UILabel *curDayIncome;

@end

@implementation IncomeListTitleCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (void)setDate:(NSString *)date dayIncome:(NSString *)dayIncome {
    _dateLabel.text = date;
    _curDayIncome.text = [NSString stringWithFormat:@"共计收入%@元", dayIncome];
}

@end

#pragma mark - IncomeListContentCell

@interface IncomeListContentCell ()

@property (weak, nonatomic) IBOutlet UIImageView *onwerAvatar;
@property (weak, nonatomic) IBOutlet UILabel *carTypeLabel;
@property (weak, nonatomic) IBOutlet UILabel *washFeeLabel;

@end

@implementation IncomeListContentCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (void)setAvatar:(NSString *)name carWashType:(NSString *)type washFee:(NSString *)washFee {
    _onwerAvatar.image = [UIImage imageNamed:name];
    _carTypeLabel.text = type;
    _washFeeLabel.text = washFee;
}

@end
