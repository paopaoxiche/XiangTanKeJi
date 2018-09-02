//
//  CommodityRecommendationCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/12.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CommodityRecommendationCell.h"
#import "UIColor+Category.h"
#import <SDWebImage/UIImageView+WebCache.h>

@interface CommodityRecommendationCell ()

@property (weak, nonatomic) IBOutlet UIImageView *commodityImgView;
@property (weak, nonatomic) IBOutlet UILabel *currentPriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *originalPriceLabel;

@end

@implementation CommodityRecommendationCell

- (void)awakeFromNib {
    [super awakeFromNib];
    
    _commodityImgView.layer.cornerRadius = 5;
    _commodityImgView.layer.masksToBounds = YES;
}

- (void)setPhotoUrl:(NSString *)photoUrl {
    [_commodityImgView sd_setImageWithURL:[NSURL URLWithString:photoUrl]
                         placeholderImage:[UIImage imageNamed:@"CarWashAvatar"]];
}

- (void)setCurrentPrice:(CGFloat)currentPrice {
    _currentPriceLabel.text = [NSString stringWithFormat:@"%.2f", currentPrice];;
}

- (void)setOriginalPrice:(CGFloat)originalPrice {
    NSString *original = [NSString stringWithFormat:@"%.2f", originalPrice];
    NSMutableAttributedString *price = [[NSMutableAttributedString alloc] initWithString:original];
    [price addAttribute:NSStrikethroughStyleAttributeName
                  value:@(NSUnderlineStyleSingle)
                  range:NSMakeRange(0, price.length)];
    [price addAttribute:NSStrikethroughColorAttributeName
                  value:[UIColor rgbWithRed:153 green:153 blue:153]
                  range:NSMakeRange(0, price.length)];
    _originalPriceLabel.attributedText = price;
}

@end
