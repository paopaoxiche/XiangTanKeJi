//
//  WashManagerCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "WashManagerCell.h"
#include <SDWebImage/UIImageView+WebCache.h>
#import "UIColor+Category.h"
#import "UserManager.h"

@interface WashManagerCell ()

@property (weak, nonatomic) IBOutlet UIImageView *proImgView;
@property (weak, nonatomic) IBOutlet UILabel *proNameLabel;
@property (weak, nonatomic) IBOutlet UILabel *proDescLabel;
@property (weak, nonatomic) IBOutlet UILabel *proCurrentPriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *proOriginalPriceLabel;

@end

@implementation WashManagerCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (void)setImageUrl:(NSString *)imageUrl {
    _imageUrl = imageUrl;
    if (imageUrl && ![imageUrl isEqualToString:@""]) {
        [_proImgView sd_setImageWithURL:[NSURL URLWithString:imageUrl]];
    } else if ([UserManager sharedInstance].userType == UserTypeCarWash ) {
        _proImgView.image = [UIImage imageNamed:@"CarWashAvatar"];
    }
}

- (void)setName:(NSString *)name {
    _name = name;
    _proNameLabel.text = name;
}

- (void)setDesc:(NSString *)desc {
    _desc = desc;
    _proDescLabel.text = desc;
}

- (void)setCurrentPrice:(CGFloat)currentPrice {
    _currentPrice = currentPrice;
    _proCurrentPriceLabel.text = [NSString stringWithFormat:@"%.2f", currentPrice];
}

- (void)setOriginalPrice:(CGFloat)originalPrice {
    _originalPrice = originalPrice;
    NSString *original = [NSString stringWithFormat:@"%.2f", originalPrice];
    NSMutableAttributedString *price = [[NSMutableAttributedString alloc] initWithString:original];
    [price addAttribute:NSStrikethroughStyleAttributeName
                  value:@(NSUnderlineStyleSingle)
                  range:NSMakeRange(0, price.length)];
    [price addAttribute:NSStrikethroughColorAttributeName
                  value:[UIColor rgbWithRed:169 green:177 blue:188]
                  range:NSMakeRange(0, price.length)];
    _proOriginalPriceLabel.attributedText = price;
    _proOriginalPriceLabel.hidden = NO;
}
@end
