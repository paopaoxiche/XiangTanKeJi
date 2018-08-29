//
//  LYKeyChainStore.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/20.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface LYKeyChainStore : NSObject

+ (BOOL)saveAccount:(NSString *)account password:(NSString *)password service:(NSString *)service;
+ (NSString *)passwordWithService:(NSString *)service;
+ (NSString *)accountWithService:(NSString *)service;

@end
