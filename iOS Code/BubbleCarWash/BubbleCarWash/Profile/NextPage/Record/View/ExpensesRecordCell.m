//
//  ExpensesRecordCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "ExpensesRecordCell.h"
#import "UIColor+Category.h"

#pragma mark - ExpensesRecordTitleCell

@interface ExpensesRecordTitleCell ()

@property (weak, nonatomic) IBOutlet UIImageView *carWashAvatar;
@property (weak, nonatomic) IBOutlet UILabel *carWashName;
@property (weak, nonatomic) IBOutlet UILabel *orderTime;

@end

@implementation ExpensesRecordTitleCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

@end

#pragma mark - ExpensesRecordContentCell

@interface ExpensesRecordContentCell ()

@property (weak, nonatomic) IBOutlet UIImageView *proImgView;
@property (weak, nonatomic) IBOutlet UILabel *proNameLabel;
@property (weak, nonatomic) IBOutlet UILabel *proPriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *generalCouponLabel;
@property (weak, nonatomic) IBOutlet UILabel *businessCouponLabel;
@property (weak, nonatomic) IBOutlet UILabel *couponNumOne;
@property (weak, nonatomic) IBOutlet UILabel *couponNumTwo;

@end

@implementation ExpensesRecordContentCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

@end

#pragma mark - TotalConsumptionCell

@interface TotalConsumptionCell ()
@property (weak, nonatomic) IBOutlet UILabel *expensesTotalLabel;
@property (weak, nonatomic) IBOutlet UIButton *evaluationBtn;

@end

@implementation TotalConsumptionCell

- (void)awakeFromNib {
    [super awakeFromNib];
    
    _evaluationBtn.layer.borderColor = [UIColor rgbWithRed:248 green:155 blue:10].CGColor;
}

@end


