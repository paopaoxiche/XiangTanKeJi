//
//  WeatherNetworkTools.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/17.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "AFNetworking.h"
#import "DataType.h"

typedef void(^SuccessBlock)(NSDictionary *response, BOOL isSuccess);
typedef void(^FailedBlock)(NSError *error);

@interface WeatherNetworkTools : AFHTTPSessionManager

+ (WeatherNetworkTools *)sharedInstance;

/*
 *  获取实时天气状况
 */
- (void)obtainRealTimeWeather:(Location)location success:(SuccessBlock)success failed:(FailedBlock)failed;
/*
 *  获取最近五天天气预报
 */
- (void)obtainForeCastWeather:(Location)location success:(SuccessBlock)success failed:(FailedBlock)failed;

- (void)GET:(NSString *)url parameters:(NSDictionary *)params success:(SuccessBlock)success failure:(FailedBlock)failed;

@end
