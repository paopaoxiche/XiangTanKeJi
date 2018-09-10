//
//  ModelCertificationCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/24.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "ModelCertificationCell.h"
#import "UIColor+Category.h"

@interface ModelCertificationCell ()

@property (weak, nonatomic) IBOutlet UIImageView *carImgView;
@property (weak, nonatomic) IBOutlet UILabel *carType;
@property (weak, nonatomic) IBOutlet UILabel *carDetail;
@property (weak, nonatomic) IBOutlet UIImageView *verifyImgView;
@property (weak, nonatomic) IBOutlet UILabel *verifyLabel;

@end

@implementation ModelCertificationCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (void)setModel:(NSString *)model {
    _carType.text = model;
    
    NSString *imageName = @"";
    if ([model isEqualToString:@"大型车"]) {
        imageName = @"LargeCar_Cer";
    } else if ([model isEqualToString:@"中型车"]) {
        imageName = @"MediumCar_Cer";
    } else {
        imageName = @"SmallCar_Cer";
    }
    _carImgView.image = [UIImage imageNamed:imageName];
}

- (void)setDesc:(NSString *)desc {
    _carDetail.text = desc;
}

- (void)setStatus:(NSInteger)status {
    _verifyLabel.text = status == 1 ? @"已认证" : @"认证中";
    _verifyLabel.textColor = status == 1 ? [UIColor rgbWithRed:56 green:200 blue:80] : [UIColor rgbWithRed:250 green:196 blue:20];
    _verifyImgView.image = [UIImage imageNamed:(status == 1 ? @"Verified" : @"InCertification")];
}

@end
