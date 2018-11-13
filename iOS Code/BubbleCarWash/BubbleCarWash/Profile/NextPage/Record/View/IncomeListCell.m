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

- (void)setDate:(NSString *)date dayIncome:(CGFloat)dayIncome {
    
    _dateLabel.text = date;
    _curDayIncome.text = [NSString stringWithFormat:@"共计收入%.2f元", dayIncome];
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

- (void)setCarType:(NSString *)carType money:(CGFloat)money {
    NSString *imageName = @"";
    NSString *desc = @"";
    switch ([carType integerValue]) {
        case 0:
            imageName = @"MediumCar_Income";
            desc = @"中型车洗车";
            break;
        case 1:
            imageName = @"LargeCar_Income";
            desc = @"大型车洗车";
            break;
        default:
            imageName = @"SmallCar_Income";
            desc = @"小型车洗车";
            break;
    }
    _onwerAvatar.image = [UIImage imageNamed:imageName];
    _carTypeLabel.text = desc;
    _washFeeLabel.text = [NSString stringWithFormat:@"%.2f", money];;
}

@end
