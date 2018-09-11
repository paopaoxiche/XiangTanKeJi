//
//  CarWashServiceModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/9.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef void(^ServiceResultBlock)(NSArray *result);

@interface CarWashServiceModel : NSObject

+ (void)loadCarWashServiceData:(NSInteger)washID result:(ServiceResultBlock)result;

@end

@interface ServiceModel : NSObject

@property (nonatomic, copy) NSString *carWashName;
@property (nonatomic, copy) NSString *desc;
@property (nonatomic, assign) NSInteger price;
@property (nonatomic, assign) NSInteger dataID;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end

