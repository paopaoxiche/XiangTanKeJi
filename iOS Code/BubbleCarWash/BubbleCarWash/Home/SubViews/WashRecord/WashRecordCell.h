//
//  WashRecordCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/10/7.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface WashRecordContentCell : UITableViewCell

@property (nonatomic, copy) NSString *imageUrl;
@property (nonatomic, copy) NSString *ownerName;
@property (nonatomic, copy) NSString *time;
@property (nonatomic, copy) NSString *proName;
@property (nonatomic, copy) NSString *proPrice;
@property (nonatomic, copy) NSString *couponType;
@property (nonatomic, copy) NSString *couponPrice;
@property (nonatomic, assign) BOOL isShowCoupon;

@end

@interface WashRecordTotalCell : UITableViewCell

@property (nonatomic, copy) NSString *totalPrice;

@end
