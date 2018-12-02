//
//  CarWashServiceCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/9.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CarWashServiceCell.h"
#import "UIColor+Category.h"

@interface CarWashServiceCell ()

@property (weak, nonatomic) IBOutlet UIButton *selectedBtn;
@property (weak, nonatomic) IBOutlet UILabel *serviceNameLabel;
@property (weak, nonatomic) IBOutlet UILabel *originalPriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *couponPriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *couponHintLabel;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *couponPriceLabelTopConstraint;

@end

@implementation CarWashServiceCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (IBAction)onSelectBtnClicked:(id)sender {
    if (_delegate) {
        [_delegate selectedServiceCell:self];
    }
}

- (void)setName:(NSString *)name {
    _name = name;
    NSMutableAttributedString *attributedString = [[NSMutableAttributedString alloc] initWithString:[NSString stringWithFormat:@"%@%@", _name, _desc]];
    [attributedString addAttributes:@{NSFontAttributeName: [UIFont systemFontOfSize:18],
                                      NSForegroundColorAttributeName: [UIColor rgbByHexStr:@"333333"]
                                      }
                              range:NSMakeRange(0, _name.length)];
    [attributedString addAttributes:@{NSFontAttributeName: [UIFont systemFontOfSize:15],
                                      NSForegroundColorAttributeName: [UIColor rgbByHexStr:@"B7C4CB"]
                                      }
                              range:NSMakeRange(_name.length, _desc.length)];
    _serviceNameLabel.attributedText = [[NSAttributedString alloc] initWithAttributedString:attributedString];
}

- (void)setSelectBtnImageName:(NSString *)selectBtnImageName {
    _selectBtnImageName = selectBtnImageName;
    [_selectedBtn setImage:[UIImage imageNamed:selectBtnImageName] forState:UIControlStateNormal];
}

- (void)setOriginalPrice:(CGFloat)originalPrice {
    _originalPrice = originalPrice;
    _originalPriceLabel.text = [NSString stringWithFormat:@"%.2f", originalPrice];
}

- (void)setCouponPrice:(CGFloat)couponPrice {
    _couponPrice = couponPrice;
    _couponPriceLabel.text = [NSString stringWithFormat:@"%.2f", couponPrice];
}

- (void)setCouponNumber:(NSInteger)couponNumber {
    _couponNumber = couponNumber;
    _couponHintLabel.text = [NSString stringWithFormat:@"%li", couponNumber];
}

- (void)setHasCoupon:(BOOL)hasCoupon {
    if (hasCoupon) {
        _originalPriceLabel.hidden = NO;
        _couponHintLabel.hidden = NO;
        _couponPriceLabelTopConstraint.constant = 26;
    } else {
        _originalPriceLabel.hidden = YES;
        _couponHintLabel.hidden = YES;
        _couponPriceLabelTopConstraint.constant = 6;
    }
}

@end
