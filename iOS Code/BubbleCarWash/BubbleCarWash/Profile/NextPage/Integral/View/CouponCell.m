//
//  CouponCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CouponCell.h"
#import <SDWebImage/UIImageView+WebCache.h>

@interface CouponCell ()

@property (weak, nonatomic) IBOutlet UIImageView *avatarImageView;           // 洗车场头像
@property (weak, nonatomic) IBOutlet UILabel *couponDetailLabel;    // 优惠券详细描述
@property (weak, nonatomic) IBOutlet UILabel *carWashNameLabel;     // 洗车场名字
@property (weak, nonatomic) IBOutlet UILabel *validityPeroidLabel;  // 券有效期
@property (weak, nonatomic) IBOutlet UILabel *noteSumLabel;         // 券金额

@end

@implementation CouponCell

- (void)setAvatarUrl:(NSString *)avatarUrl {
    [_avatarImageView sd_setImageWithURL:[NSURL URLWithString:avatarUrl]
                        placeholderImage:[UIImage imageNamed:@"CarWashAvatar"]];
}

- (void)setCouponDesc:(NSString *)couponDesc {
    _couponDetailLabel.text = couponDesc;
}

- (void)setWashName:(NSString *)washName {
    _carWashNameLabel.text = washName;
}

- (void)setValidityPeroid:(NSString *)validityPeroid {
    _validityPeroidLabel.text = validityPeroid;
}

- (void)setNoteSum:(NSString *)noteSum {
    _noteSumLabel.text = noteSum;
}

@end
