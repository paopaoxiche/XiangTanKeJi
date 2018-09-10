//
//  PaymentTypeCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/11.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "PaymentTypeCell.h"

@interface PaymentTypeCell ()

@property (weak, nonatomic) IBOutlet UIImageView *typeImageView;
@property (weak, nonatomic) IBOutlet UILabel *nameLabel;
@property (weak, nonatomic) IBOutlet UILabel *recommendLabel;
@property (weak, nonatomic) IBOutlet UIButton *selectedBtn;

@end

@implementation PaymentTypeCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (IBAction)onSelectBtnClicked:(id)sender {
    if (_delegate) {
        [_delegate selectedPaymentTypeCell:self];
    }
}

- (void)setTypeImageName:(NSString *)typeImageName {
    _typeImageName = typeImageName;
    _typeImageView.image = [UIImage imageNamed:typeImageName];
}

- (void)setName:(NSString *)name {
    _name = name;
    _nameLabel.text = name;
}

- (void)setSelectImageName:(NSString *)selectImageName {
    _selectImageName = selectImageName;
    [_selectedBtn setImage:[UIImage imageNamed:selectImageName]
                  forState:UIControlStateNormal];
}

- (void)setIsShowRecommend:(BOOL)isShowRecommend {
    _isShowRecommend = isShowRecommend;
    _recommendLabel.hidden = !isShowRecommend;
}

@end
