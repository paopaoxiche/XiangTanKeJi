//
//  AlipayManager.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/11.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

@class PaymentViewController;

@interface AlipayManager : NSObject

@property (nonatomic, strong) PaymentViewController *paymentVC;

+ (instancetype)sharedManager;

- (void)handlePaymentResult:(NSDictionary *)result;

@end
