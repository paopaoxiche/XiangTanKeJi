//
//  WashManagerCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "WashManagerCell.h"

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
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
