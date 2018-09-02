//
//  CouponCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CouponCell : UITableViewCell

@property (nonatomic, copy) NSString *avatarUrl;
@property (nonatomic, copy) NSString *couponDesc;
@property (nonatomic, copy) NSString *washName;
@property (nonatomic, copy) NSString *validityPeroid;
@property (nonatomic, copy) NSString *noteSum;

@end
