//
//  PaymentViewController.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/10.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ParentViewController.h"

@interface PayTypeModel : NSObject

@property (nonatomic, copy) NSString *typeImageName;
@property (nonatomic, copy) NSString *name;
@property (nonatomic, assign) BOOL isShowRecommend;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end

@interface PaymentViewController : ParentViewController

@property (nonatomic, copy) NSString *serviceID;
@property (nonatomic, copy) NSString *commoditys;
@property (nonatomic, copy) NSString *totalAmount;
@property (nonatomic, assign) NSInteger couponID;

- (void)paymentSuccess;

@end
