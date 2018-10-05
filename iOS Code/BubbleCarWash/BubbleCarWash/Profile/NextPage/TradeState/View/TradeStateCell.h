//
//  TradeStateCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@class TradeStateCell;

@protocol TradeStateCellDelegate <NSObject>

- (void)selectedTradeStateCell:(TradeStateCell *)cell;

@end

@interface TradeStateCell : UITableViewCell

@property (nonatomic, weak) id<TradeStateCellDelegate> delegate;
@property (nonatomic, copy) NSString *imgName;
@property (nonatomic, copy) NSString *tradeState;

@end
