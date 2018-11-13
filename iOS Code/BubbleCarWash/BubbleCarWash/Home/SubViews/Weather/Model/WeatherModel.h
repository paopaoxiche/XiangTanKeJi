//
//  WeatherModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/2.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "DataType.h"

typedef NS_ENUM(NSInteger, WeatherSkycon) {
    WeatherSkyconClearDay,                 // 晴天
    WeatherSkyconClearNight,               // 晴夜
    WeatherSkyconPartlyCloudyDay,          // 多云日
    WeatherSkyconPartlyCloudyNight,        // 多云夜
    WeatherSkyconCloudy,                   // 阴
    WeatherSkyconRain,                     // 雨
    WeatherSkyconSnow,                     // 雪
    WeatherSkyconWind,                     // 风
    WeatherSkyconHaze                      // 雾霾沙尘
};

typedef void(^WeatherBlock)(NSDictionary *result);

@class WeatherRealTimeModel;
@class WeatherForeCastModel;

@interface WeatherModel : NSObject

@property (nonatomic, strong) WeatherRealTimeModel *realTimeModel;
@property (nonatomic, copy) NSArray<WeatherForeCastModel *> *foreCastModel;

+ (void)loadWeatherData:(Location)location result:(WeatherBlock)result;
+ (WeatherSkycon)weatherSkycon:(NSString *)skycon;
+ (NSArray *)weatherBackgroundColor:(WeatherSkycon)skycon;

@end

/// 实时天气
@interface WeatherRealTimeModel : NSObject

/// 日期
@property (nonatomic, copy) NSString *date;
/// 当前温度
@property (nonatomic, copy) NSString *currentTemperature;
/// 当前湿度
@property (nonatomic, copy) NSString *currentHumidity;
/// 当前风速
@property (nonatomic, copy) NSString *currentwindSpeed;
/// 当前天气状况
@property (nonatomic, copy) NSString *currentWeatherState;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end

/// 最近五天天气预报
@interface WeatherForeCastModel : NSObject

/// 日期
@property (nonatomic, copy) NSString *date;
/// 周几
@property (nonatomic, copy) NSString *week;
/// 预警信息
@property (nonatomic, copy) NSString *hint;
/// 最高温度
@property (nonatomic, copy) NSString *maxTemperature;
/// 最低温度
@property (nonatomic, copy) NSString *minTemperature;
@property (nonatomic, copy) NSString *imageName;
/// 天气概况
@property (nonatomic, assign) WeatherSkycon skycon;

@end
