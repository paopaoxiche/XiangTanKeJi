//
//  WashRecordCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/10/7.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <SDWebImage/UIImageView+WebCache.h>
#import "WashRecordCell.h"

@interface WashRecordContentCell ()

@property (weak, nonatomic) IBOutlet UIImageView *proImgView;
@property (weak, nonatomic) IBOutlet UILabel *ownerNameLabel;
@property (weak, nonatomic) IBOutlet UILabel *timeLabel;
@property (weak, nonatomic) IBOutlet UILabel *proLabel;
@property (weak, nonatomic) IBOutlet UILabel *proPriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *couponTypeLabel;
@property (weak, nonatomic) IBOutlet UILabel *couponNumber;

@end

@implementation WashRecordContentCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (void)setImageUrl:(NSString *)imageUrl {
    if ([imageUrl isEqualToString:@""]) {
        _imageUrl = @"ServiceDefault";
        _proImgView.image = [UIImage imageNamed:@"ServiceDefault"];
    } else {
        _imageUrl = imageUrl;
        [_proImgView sd_setImageWithURL:[NSURL URLWithString:imageUrl]];
    }
}

- (void)setOwnerName:(NSString *)ownerName {
    _ownerName = ownerName;
    _ownerNameLabel.text = ownerName;
}

- (void)setTime:(NSString *)time {
    _time = time;
    _timeLabel.text = time;
}

- (void)setProName:(NSString *)name {
    _proName = name;
    _proLabel.text = name;
}

- (void)setPrice:(NSString *)price {
    _proPrice = price;
    _proPriceLabel.text = price;
}

- (void)setCouponType:(NSString *)couponType {
    _couponType = couponType;
    _couponTypeLabel.text = couponType;
}

- (void)setCouponPrice:(NSString *)couponPrice {
    _couponPrice = couponPrice;
    _couponNumber.text = couponPrice;
}

- (void)setIsShowCoupon:(BOOL)isShowCoupon {
    _isShowCoupon = isShowCoupon;
    _couponNumber.hidden = isShowCoupon;
    _couponTypeLabel.hidden = isShowCoupon;
}

@end

@interface WashRecordTotalCell ()

@property (weak, nonatomic) IBOutlet UILabel *expensesTotalLabel;

@end

@implementation WashRecordTotalCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (void)setTotalPrice:(NSString *)totalPrice {
    _totalPrice = totalPrice;
    _expensesTotalLabel.text = totalPrice;
}

@end
