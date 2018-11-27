//
//  RecentWashRecordCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/10/7.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <SDWebImage/UIImageView+WebCache.h>
#import "RecentWashRecordCell.h"
#import "UIColor+Category.h"

@interface RecentWashRecordCell ()

@property (weak, nonatomic) IBOutlet UIImageView *ownerAvatarImageView;
@property (weak, nonatomic) IBOutlet UILabel *ownerNameLabel;
@property (weak, nonatomic) IBOutlet UILabel *priceLabel;
@property (weak, nonatomic) IBOutlet UILabel *timeLabel;
@property (weak, nonatomic) IBOutlet UILabel *typeLabel;

@end

@implementation RecentWashRecordCell

- (void)awakeFromNib {
    [super awakeFromNib];
    
    _ownerAvatarImageView.clipsToBounds = YES;
    _ownerAvatarImageView.layer.cornerRadius = 24;
    _ownerAvatarImageView.layer.borderWidth = 2;
    _ownerAvatarImageView.layer.borderColor = [UIColor rgbWithRed:229 green:229 blue:229].CGColor;
}

- (void)setImageName:(NSString *)imageName {
    if ([imageName isEqualToString:@""]) {
        _imageName = @"OwnerAvatar";
        _ownerAvatarImageView.image = [UIImage imageNamed:@"OwnerAvatar"];
    } else {
        _imageName = imageName;
        [_ownerAvatarImageView sd_setImageWithURL:[NSURL URLWithString:imageName]];
    }
}

- (void)setName:(NSString *)name {
    _name = name;
    _ownerNameLabel.text = name;
}

- (void)setPrice:(NSString *)price {
    _price = price;
    _priceLabel.text = [NSString stringWithFormat:@"￥%.2f", [price floatValue]];
}

- (void)setTime:(NSString *)time {
    _time = time;
    _timeLabel.text = time;
}

- (void)setType:(NSString *)type {
    _type = type;
    _typeLabel.text = type;
}

@end
