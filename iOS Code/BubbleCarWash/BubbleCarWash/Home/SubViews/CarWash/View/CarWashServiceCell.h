//
//  CarWashServiceCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/9.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@class CarWashServiceCell;

@protocol CarWashServiceCellDelegate <NSObject>

- (void)selectedServiceCell:(CarWashServiceCell *)cell;

@end

@interface CarWashServiceCell : UITableViewCell

@property (nonatomic, weak) id<CarWashServiceCellDelegate> delegate;
@property (nonatomic, copy) NSString *name;
@property (nonatomic, copy) NSString *desc;
@property (nonatomic, copy) NSString *selectBtnImageName;
@property (nonatomic, assign) CGFloat originalPrice;
@property (nonatomic, assign) CGFloat couponPrice;
@property (nonatomic, assign) NSInteger couponNumber;
@property (nonatomic, assign) BOOL hasCoupon;

@end
