//
//  CommodityInfoViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/11/18.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CommodityInfoViewController.h"
#import "UIColor+Category.h"
#import "HomeModel.h"
#import <SDWebImage/UIImageView+WebCache.h>

@interface CommodityInfoViewController ()

@property (weak, nonatomic) IBOutlet UIImageView *commodityImageView;
@property (weak, nonatomic) IBOutlet UILabel *currentPriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *orignalPriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *nameLabel;

@end

@implementation CommodityInfoViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    if (self.model.imageUrl) {
        [self.commodityImageView sd_setImageWithURL:[NSURL URLWithString:self.model.imageUrl]];
    }
    self.currentPriceLabel.text = [NSString stringWithFormat:@"¥%.2f", self.model.currentPrice];
    NSMutableAttributedString *attributedText = [[NSMutableAttributedString alloc] initWithString:[NSString stringWithFormat:@"¥%.2f", self.model.originPrice]];
    UIColor *color = [UIColor rgbWithRed:169 green:177 blue:188];
    [attributedText addAttributes:@{
                                    NSFontAttributeName:[UIFont systemFontOfSize:13],
                                    NSForegroundColorAttributeName:color,
                                    NSStrikethroughColorAttributeName:color,
                                    NSStrikethroughStyleAttributeName:@(NSUnderlineStyleSingle|NSUnderlinePatternSolid)
                                    }
                            range:NSMakeRange(0, attributedText.length)];
    self.orignalPriceLabel.attributedText = attributedText;
    self.nameLabel.text = [NSString stringWithFormat:@"%@ %@", self.model.commodityName, self.model.describe];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [self.navigationController setNavigationBarHidden:NO animated:YES];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

@end
