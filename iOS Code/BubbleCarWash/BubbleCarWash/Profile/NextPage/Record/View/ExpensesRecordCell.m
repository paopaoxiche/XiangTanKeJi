//
//  ExpensesRecordCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "ExpensesRecordCell.h"
#import "UIColor+Category.h"
#import <SDWebImage/UIImageView+WebCache.h>

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

- (void)setAvatarUrl:(NSString *)avatarUrl {
    [_carWashAvatar sd_setImageWithURL:[NSURL URLWithString:avatarUrl]
                      placeholderImage:[UIImage imageNamed:@"OwnerAvatar"]];
}

- (void)setWashName:(NSString *)washName {
    _carWashName.text = washName;
}

- (void)setTime:(NSString *)time {
    _orderTime.text = time;
}

@end

#pragma mark - ExpensesRecordContentCell

@interface ExpensesRecordContentCell ()

@property (weak, nonatomic) IBOutlet UIImageView *proImgView;
@property (weak, nonatomic) IBOutlet UILabel *proNameLabel;
@property (weak, nonatomic) IBOutlet UILabel *proPriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *couponTypeLabel;
@property (weak, nonatomic) IBOutlet UILabel *couponNumber;

@end

@implementation ExpensesRecordContentCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (void)setImageUrl:(NSString *)imageUrl {
    [_proImgView sd_setImageWithURL:[NSURL URLWithString:imageUrl]];
}

- (void)setName:(NSString *)name {
    _proNameLabel.text = name;
}

- (void)setPrice:(NSString *)price {
    _proPriceLabel.text = price;
}

- (void)setCouponType:(NSString *)couponType {
    _couponTypeLabel.text = couponType;
}

- (void)setCouponPrice:(NSString *)couponPrice {
    _couponNumber.text = couponPrice;
}

- (void)setIsShowCoupon:(BOOL)isShowCoupon {
    _couponNumber.hidden = isShowCoupon;
    _couponTypeLabel.hidden = isShowCoupon;
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
- (IBAction)onEvaluationButtonClicked:(id)sender {
    if (_delegate) {
        [_delegate onEvaluationButtonClicked:self];
    }
}

- (void)setTotalPrice:(NSString *)totalPrice {
    _expensesTotalLabel.text = totalPrice;
}

- (void)setIsEvaluation:(BOOL)isEvaluation {
    [_evaluationBtn setTitle:isEvaluation ? @"已评价" : @"未评价"
                    forState:UIControlStateNormal];
}

@end


