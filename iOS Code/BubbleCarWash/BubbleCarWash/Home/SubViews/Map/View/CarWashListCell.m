//
//  CarWashListCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/13.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CarWashListCell.h"
#import <SDWebImage/UIImageView+WebCache.h>

@interface CarWashListCell ()

@property (weak, nonatomic) IBOutlet UIImageView *avatarImageView;
@property (weak, nonatomic) IBOutlet UILabel *nameLabel;
@property (weak, nonatomic) IBOutlet UILabel *addressLabel;
@property (weak, nonatomic) IBOutlet UILabel *priceLabel;
@property (weak, nonatomic) IBOutlet UILabel *distanceLabel;
@property (weak, nonatomic) IBOutlet UIView *carWashTitleView;
@property (weak, nonatomic) IBOutlet UIView *carWashDetailView;

@end

@implementation CarWashListCell

- (void)awakeFromNib {
    [super awakeFromNib];
    
    UITapGestureRecognizer *titleSingle = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onTitleViewClicked:)];
    [_carWashTitleView addGestureRecognizer:titleSingle];
    
    UITapGestureRecognizer *detailSingle = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onDetailViewClicked:)];
    [_carWashDetailView addGestureRecognizer:detailSingle];
}

- (void)onTitleViewClicked:(UITapGestureRecognizer *)gesture {
    if (_delegate) {
        [_delegate titleViewCarWashListCell:self];
    }
}

- (void)onDetailViewClicked:(UITapGestureRecognizer *)gesture {
    if (_delegate) {
        [_delegate detailViewCarWashListCell:self];
    }
}

- (void)setAvatarUrl:(NSString *)avatarUrl {
    _avatarUrl = avatarUrl;
    [_avatarImageView sd_setImageWithURL:[NSURL URLWithString:avatarUrl]
                        placeholderImage:[UIImage imageNamed:@"CarWashAvatar"]];
}

- (void)setName:(NSString *)name {
    _name = name;
    _nameLabel.text = name;
}

- (void)setAddress:(NSString *)address {
    _address = address;
    _addressLabel.text = address;
}

- (void)setPrice:(CGFloat)price {
    _price = price;
    _priceLabel.text = [NSString stringWithFormat:@"¥%.2f", price];
}

- (void)setDistance:(NSUInteger)distance {
    _distance = distance;
    _distanceLabel.text = [NSString stringWithFormat:@"%lum", distance];
}

@end
