//
//  CarWashListCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/13.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@class CarWashListCell;

@protocol CarWashListCellDelegate <NSObject>

- (void)titleViewCarWashListCell:(CarWashListCell *)cell;
- (void)detailViewCarWashListCell:(CarWashListCell *)cell;

@end

@interface CarWashListCell : UITableViewCell

@property (nonatomic, weak) id<CarWashListCellDelegate> delegate;
@property (nonatomic, copy) NSString *avatarUrl;
@property (nonatomic, copy) NSString *name;
@property (nonatomic, copy) NSString *address;
@property (nonatomic, assign) CGFloat price;
@property (nonatomic, copy) NSString *distance;

@end
