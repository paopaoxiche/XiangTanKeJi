//
//  PaymentTypeCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/11.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@class PaymentTypeCell;

@protocol PaymentTypeCellDelegate <NSObject>

- (void)selectedPaymentTypeCell:(PaymentTypeCell *)cell;

@end

@interface PaymentTypeCell : UITableViewCell

@property (nonatomic, weak) id<PaymentTypeCellDelegate> delegate;
@property (nonatomic, copy) NSString *typeImageName;
@property (nonatomic, copy) NSString *name;
@property (nonatomic, copy) NSString *selectImageName;
@property (nonatomic, assign) BOOL isShowRecommend;

@end
