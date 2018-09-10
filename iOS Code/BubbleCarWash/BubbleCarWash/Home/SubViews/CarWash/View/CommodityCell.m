//
//  CommodityCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/9.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CommodityCell.h"
#import <SDWebImage/UIImageView+WebCache.h>

@interface CommodityCell ()

@property (weak, nonatomic) IBOutlet UIButton *selectedBtn;
@property (weak, nonatomic) IBOutlet UIImageView *avatarImageView;
@property (weak, nonatomic) IBOutlet UILabel *nameLabel;
@property (weak, nonatomic) IBOutlet UILabel *priceLabel;

@end

@implementation CommodityCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (IBAction)onSelectBtnClicked:(id)sender {
    if (_delegate) {
        [_delegate selectedCommodityCell:self];
    }
}

- (void)setAvatarUrl:(NSString *)avatarUrl {
    _avatarUrl = avatarUrl;
    [_avatarImageView sd_setImageWithURL:[NSURL URLWithString:avatarUrl]];
}

- (void)setName:(NSString *)name {
    _name = name;
    _nameLabel.text = name;
}

- (void)setSelectBtnImageName:(NSString *)selectBtnImageName {
    _selectBtnImageName = selectBtnImageName;
    [_selectedBtn setImage:[UIImage imageNamed:selectBtnImageName] forState:UIControlStateNormal];
}

- (void)setPrice:(CGFloat)price {
    _price = price;
    _priceLabel.text = [NSString stringWithFormat:@"%.2f", price];
}

@end
