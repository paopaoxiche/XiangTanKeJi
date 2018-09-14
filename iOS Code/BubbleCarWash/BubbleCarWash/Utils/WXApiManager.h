//
//  WXApiManager.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/11.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "WXApi.h"

@protocol WXApiManagerDelegate <NSObject>

@optional

@end

@class PaymentViewController;

@interface WXApiManager : NSObject<WXApiDelegate>

@property (nonatomic, assign) id<WXApiManagerDelegate> delegate;
@property (nonatomic, strong) PaymentViewController *paymentVC;

+ (instancetype)sharedManager;

@end
