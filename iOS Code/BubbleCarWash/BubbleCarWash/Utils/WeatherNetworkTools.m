
//
//  WeatherNetworkTools.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/17.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "WeatherNetworkTools.h"

static const NSTimeInterval kTimeOutInterval = 6.0f;
static NSString *token = @"kIoCAW4NzT21BFHQ";

@implementation WeatherNetworkTools

+ (WeatherNetworkTools *)sharedInstance {
    static WeatherNetworkTools *instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [[WeatherNetworkTools alloc] initWithBaseURL:[NSURL URLWithString:@"https://api.caiyunapp.com/v2/"]];
        instance.requestSerializer.timeoutInterval = kTimeOutInterval;
    });
    
    return instance;
}

- (void)obtainRealTimeWeather:(Location)location success:(SuccessBlock)success failed:(FailedBlock)failed {
    NSString *url = [NSString stringWithFormat:@"%@/%@,%@/realtime.json", token, [NSNumber numberWithFloat:location.lng], [NSNumber numberWithFloat:location.lat]];
    [self GET:url parameters:nil success:success failure:failed];
}

- (void)obtainForeCastWeather:(Location)location success:(SuccessBlock)success failed:(FailedBlock)failed {
    NSString *url = [NSString stringWithFormat:@"%@/%@,%@/forecast.json", token, [NSNumber numberWithFloat:location.lng], [NSNumber numberWithFloat:location.lat]];
    [self GET:url parameters:nil success:success failure:failed];
}

#pragma mark - Get Method

- (void)GET:(NSString *)url parameters:(NSDictionary *)params success:(SuccessBlock)success failure:(FailedBlock)failed {
    [self GET:url parameters:params progress:nil success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        NSLog(@"responseObject = %@", responseObject);
        NSString *status = [responseObject objectForKey:@"status"];
        if (responseObject && [status isEqualToString:@"ok"]) {
            success([responseObject objectForKey:@"result"], YES);
        } else {
            success(@{@"msg": @"暂无数据"}, NO);
        }
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        failed(error);
    }];
}

@end
