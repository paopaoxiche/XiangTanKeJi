//
//  NearWashInfoCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <SDWebImage/UIImageView+WebCache.h>
#import "NearWashInfoCell.h"
#import "UIColor+Category.h"

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
    
    _washAvatar.clipsToBounds = YES;
    _washAvatar.layer.cornerRadius = 24;
    _washAvatar.layer.borderWidth = 2;
    _washAvatar.layer.borderColor = [UIColor rgbWithRed:229 green:229 blue:229].CGColor;
}

- (void)setAvatarUrl:(NSString *)avatarUrl {
    [_washAvatar sd_setImageWithURL:[NSURL URLWithString:avatarUrl]
                   placeholderImage:[UIImage imageNamed:@"CarWashAvatar"]];
}

- (void)setName:(NSString *)name {
    _name = name;
    _washNameLabel.text = name;
}

- (void)setLowestPrice:(CGFloat)lowestPrice {
    _lowestPrice = lowestPrice;
    _lowestPriceLabel.text = [NSString stringWithFormat:@"¥%.2f", lowestPrice];
}

- (void)setHonor:(NSInteger)honor {
    _honor = honor;
    _honorLabel.text = [NSString stringWithFormat:@"%li", honor];
}

- (void)setWashNumber:(NSInteger)washNumber {
    _washNumber = washNumber;
    _washNumberLabel.text = [NSString stringWithFormat:@"%li", washNumber];
}

- (void)setDistance:(NSString *)distance {
    _distance = distance;
    _distanceLabel.text = distance;
}

@end
