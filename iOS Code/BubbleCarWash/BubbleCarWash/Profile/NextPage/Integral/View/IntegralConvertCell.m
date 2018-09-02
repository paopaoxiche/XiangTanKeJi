//
//  IntegralConvertCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "IntegralConvertCell.h"
#import "UIColor+Category.h"

@interface IntegralConvertCell ()

@property (weak, nonatomic) IBOutlet UILabel *noteSumLabel;             // 兑换后券金额
@property (weak, nonatomic) IBOutlet UILabel *noteTypeLabel;            // 兑换券类型
@property (weak, nonatomic) IBOutlet UILabel *detailNoteLabel;          // 券详细描述
@property (weak, nonatomic) IBOutlet UILabel *validityPeriodLabel;      // 券有效期
@property (weak, nonatomic) IBOutlet UILabel *IntegralLabel;            // 兑换该券所需积分
@property (weak, nonatomic) IBOutlet UIButton *convertBtn;              // 兑换按钮

@end

@implementation IntegralConvertCell

- (void)awakeFromNib {
    [super awakeFromNib];
    
    _convertBtn.layer.cornerRadius = 4.0;
    _convertBtn.layer.borderWidth = 1;
    _convertBtn.layer.borderColor = [UIColor rgbWithRed:248 green:155 blue:10].CGColor;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

- (IBAction)onClickedConvertBtn:(id)sender {
    if (_delegate) {
        [_delegate onClickedConvertButtonnAtIndex:_index];
    }
}

- (void)setNoteSum:(NSString *)noteSum {
    _noteSum = noteSum;
    _noteSumLabel.text = noteSum;
}

- (void)setCouponType:(NSString *)couponType {
    _couponType = couponType;
    _noteTypeLabel.text = couponType;
}

- (void)setCouponDesc:(NSString *)couponDesc {
    _couponDesc = couponDesc;
    _detailNoteLabel.text = couponDesc;
}

- (void)setValidityPeroid:(NSString *)validityPeroid {
    _validityPeroid = validityPeroid;
    _validityPeriodLabel.text = validityPeroid;
}

- (void)setIntegral:(NSString *)integral {
    _integral = integral;
    _IntegralLabel.text = integral;
}

@end
