//
//  CertificationCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/1.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CertificationCell.h"
#import "UIColor+Category.h"

#pragma mark - CertificationTitleCell

@interface CertificationTitleCell ()

@property (weak, nonatomic) IBOutlet UILabel *titleLabel;

@end

@implementation CertificationTitleCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (void)setTitle:(NSString *)title {
    _title = title;
    _titleLabel.text = title;
}

@end

#pragma mark - CertificationCarTypeCell

@interface CertificationCarTypeCell ()

@property (weak, nonatomic) IBOutlet UIButton *selectBtn;
@property (weak, nonatomic) IBOutlet UIImageView *carImgView;
@property (weak, nonatomic) IBOutlet UILabel *carDescLabel;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *carImgViewConstraint;

@end

@implementation CertificationCarTypeCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (void)setCarImgName:(NSString *)carImgName {
    _carImgView.image = [UIImage imageNamed:carImgName];
}

- (void)setCarDesc:(NSString *)carDesc {
    _carDescLabel.text = carDesc;
}

- (void)setSelectImgName:(NSString *)selectImgName {
//    if ([selectImgName isEqualToString:@""]) {
//        _selectBtn.hidden = YES;
//        _carImgViewConstraint.constant = 22;
//    } else {
        _selectBtn.hidden = NO;
        [_selectBtn setImage:[UIImage imageNamed:selectImgName] forState:UIControlStateNormal];
        _carImgViewConstraint.constant = 74;
//    }
}

@end

#pragma mark - CertificationInfoCell

@interface CertificationInfoCell ()

@property (weak, nonatomic) IBOutlet UIView *uploadView;
@property (weak, nonatomic) IBOutlet UIImageView *uploadImageView;
@property (weak, nonatomic) IBOutlet UILabel *uploadHintLabel;
@property (weak, nonatomic) IBOutlet UILabel *uploadDescLabel;
@property (weak, nonatomic) IBOutlet UIImageView *cerImgView;
@property (weak, nonatomic) IBOutlet UILabel *cerStateLabel;

@end

@implementation CertificationInfoCell

- (void)awakeFromNib {
    [super awakeFromNib];
    
    _uploadView.layer.borderWidth = 1;
    _uploadView.layer.borderColor = [UIColor rgbWithRed:221 green:221 blue:221].CGColor;
}

- (void)setCerDesc:(NSString *)desc cerImgName:(NSString *)name cerType:(CertificationCellType)type {
    _uploadDescLabel.text = desc;
    if (![name isEqualToString:@""]) {
        _cerImgView.image = [UIImage imageNamed:name];
    }
    
    switch (type) {
        case CertificationCellTypeAdd:
            break;
        case CertificationCellTypeIn:
            _uploadView.backgroundColor = [UIColor rgbWithRed:0 green:0 blue:0 alpha:0.4];
            _uploadView.layer.borderColor = [UIColor rgbWithRed:221 green:221 blue:221 alpha:0.4].CGColor;
            _uploadView.hidden = NO;
            _uploadImageView.hidden = YES;
            _uploadHintLabel.hidden = YES;
            _cerStateLabel.hidden = NO;
            _uploadView.hidden = NO;
            _cerImgView.hidden = NO;
            break;
        default:
            _uploadView.hidden = YES;
            _uploadImageView.hidden = YES;
            _uploadHintLabel.hidden = YES;
            _cerStateLabel.hidden = YES;
            _uploadView.hidden = YES;
            _cerImgView.hidden = NO;
            break;
    }
}

@end
