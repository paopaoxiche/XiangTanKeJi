//
//  TradeStateCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "TradeStateCell.h"

@interface TradeStateCell ()

@property (weak, nonatomic) IBOutlet UIButton *selectBtn;
@property (weak, nonatomic) IBOutlet UILabel *tradeStateLabel;

@end

@implementation TradeStateCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (IBAction)onselectedBtnClicked:(id)sender {
    if (_delegate) {
        [_delegate selectedTradeStateCell:self];
    }
}

- (void)setImgName:(NSString *)imgName {
    [_selectBtn setImage:[UIImage imageNamed:imgName] forState:UIControlStateNormal];
}

- (void)setTradeState:(NSString *)tradeState {
    _tradeStateLabel.text = tradeState;
}

@end
