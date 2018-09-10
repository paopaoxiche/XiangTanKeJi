//
//  CommodityCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/9.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@class CommodityCell;

@protocol CommodityCellDelegate <NSObject>

- (void)selectedCommodityCell:(CommodityCell *)cell;

@end

@interface CommodityCell : UITableViewCell

@property (nonatomic, weak) id<CommodityCellDelegate> delegate;
@property (nonatomic, copy) NSString *avatarUrl;
@property (nonatomic, copy) NSString *name;
@property (nonatomic, copy) NSString *selectBtnImageName;
@property (nonatomic, assign) CGFloat price;

@end
