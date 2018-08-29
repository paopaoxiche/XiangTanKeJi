//
//  CouponCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CouponCell.h"

@interface CouponCell ()

@property (weak, nonatomic) IBOutlet UIImageView *avatar;           // 洗车场头像
@property (weak, nonatomic) IBOutlet UILabel *couponDetailLabel;    // 优惠券详细描述
@property (weak, nonatomic) IBOutlet UILabel *carWashName;          // 洗车场名字
@property (weak, nonatomic) IBOutlet UILabel *validityPeroid;       // 券有效期
@property (weak, nonatomic) IBOutlet UILabel *noteSumLabel;         // 券金额

@end

@implementation CouponCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
