//
//  CommodityRecommendationCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/12.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CommodityRecommendationCell.h"
#import "UIColor+Category.h"

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

- (void)setOriginalPrice:(NSString *)originalPrice {
    NSMutableAttributedString *price = [[NSMutableAttributedString alloc] initWithString:originalPrice];
    [price addAttribute:NSStrikethroughStyleAttributeName
                  value:@(NSUnderlineStyleSingle)
                  range:NSMakeRange(0, price.length)];
    [price addAttribute:NSStrikethroughColorAttributeName
                  value:[UIColor rgbWithRed:153 green:153 blue:153]
                  range:NSMakeRange(0, price.length)];
    _originalPriceLabel.attributedText = price;
}

@end
