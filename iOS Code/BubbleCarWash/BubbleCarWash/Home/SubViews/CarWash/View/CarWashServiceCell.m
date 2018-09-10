//
//  CarWashServiceCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/9.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CarWashServiceCell.h"

@interface CarWashServiceCell ()

@property (weak, nonatomic) IBOutlet UIButton *selectedBtn;
@property (weak, nonatomic) IBOutlet UILabel *serviceNameLabel;
@property (weak, nonatomic) IBOutlet UILabel *serviceDescLabel;
@property (weak, nonatomic) IBOutlet UILabel *originalPriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *couponPriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *couponHintLabel;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *couponPriceLabelTopConstraint;

@end

@implementation CarWashServiceCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (void)setName:(NSString *)name {
    _name = name;
    _serviceNameLabel.text = name;
}

- (void)setDesc:(NSString *)desc {
    _desc = desc;
    _serviceDescLabel.text = desc;
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
