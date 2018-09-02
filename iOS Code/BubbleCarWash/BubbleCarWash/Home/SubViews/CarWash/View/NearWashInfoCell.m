//
//  NearWashInfoCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "NearWashInfoCell.h"
#import <SDWebImage/UIImageView+WebCache.h>

@interface NearWashInfoCell ()

@property (weak, nonatomic) IBOutlet UIImageView *washAvatar;
@property (weak, nonatomic) IBOutlet UILabel *washNameLabel;
@property (weak, nonatomic) IBOutlet UILabel *lowestPriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *honorLabel;
@property (weak, nonatomic) IBOutlet UILabel *washNumberLabel;
@property (weak, nonatomic) IBOutlet UILabel *distanceLabel;

@end

@implementation NearWashInfoCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (void)setAvatarUrl:(NSString *)avatarUrl {
    [_washAvatar sd_setImageWithURL:[NSURL URLWithString:avatarUrl]
                   placeholderImage:[UIImage imageNamed:@"CarWashAvatar"]];
}

- (void)setName:(NSString *)name {
    _washNameLabel.text = name;
}

- (void)setLowestPrice:(CGFloat)lowestPrice {
    _lowestPriceLabel.text = [NSString stringWithFormat:@"¥%.2f", lowestPrice];
}

- (void)setHonor:(NSInteger)honor {
    _honorLabel.text = [NSString stringWithFormat:@"%li", honor];
}

- (void)setWashNumber:(NSInteger)washNumber {
    _washNumberLabel.text = [NSString stringWithFormat:@"%li", washNumber];
}

- (void)setDistance:(NSInteger)distance {
    _distanceLabel.text = [NSString stringWithFormat:@"%li", distance];
}

@end
