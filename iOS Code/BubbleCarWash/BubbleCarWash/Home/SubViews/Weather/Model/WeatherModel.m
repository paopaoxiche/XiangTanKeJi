//
//  WeatherModel.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/2.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "WeatherModel.h"
#import "WeatherNetworkTools.h"
#import "GlobalMethods.h"
#import "UIColor+Category.h"

@implementation WeatherModel

+ (void)loadWeatherData:(Location)location result:(ResultBlock)result {
    dispatch_group_t group = dispatch_group_create();
    
    dispatch_group_enter(group);
    __block WeatherRealTimeModel *realTimeModel = [[WeatherRealTimeModel alloc] init];
    [[WeatherNetworkTools sharedInstance] obtainRealTimeWeather:location success:^(NSDictionary *response, BOOL isSuccess) {
        if (response && [response[@"status"] isEqualToString:@"ok"]) {
            WeatherRealTimeModel *model = [[WeatherRealTimeModel alloc] initWithDic:response];
            realTimeModel = model;
            dispatch_group_leave(group);
        } else {
            dispatch_group_leave(group);
        }
    } failed:^(NSError *error) {
        dispatch_group_leave(group);
    }];
    
    dispatch_group_enter(group);
    __block NSArray *forecasts = [[NSArray alloc] init];
    [[WeatherNetworkTools sharedInstance] obtainForeCastWeather:location success:^(NSDictionary *response, BOOL isSuccess) {
        if (response) {
            NSDictionary *dailyDic = [response objectForKey:@"daily"];
            if (dailyDic && [dailyDic[@"status"] isEqualToString:@"ok"]) {
                NSArray *temperatures = dailyDic[@"temperature"];
                NSArray *skycons = dailyDic[@"skycon"];
                
                NSMutableArray *data = [[NSMutableArray alloc] initWithCapacity:0];
                for (int i = 0; i < temperatures.count; i++) {
                    WeatherForeCastModel *model = [[WeatherForeCastModel alloc] init];
                    NSDictionary *temperature = temperatures[i];
                    
                    NSString *date = temperature[@"date"];
                    model.date = [GlobalMethods convertDate:date outputFormat:@"MM/dd"];
                    model.week = i == 0 ? @"今天" : [GlobalMethods dayOfWeekByDateString:date];
                    model.maxTemperature = [NSString stringWithFormat:@"%li", [temperature[@"max"] integerValue]];
                    model.minTemperature = [NSString stringWithFormat:@"%li", [temperature[@"min"] integerValue]];
                    
                    if (i < skycons.count) {
                        NSDictionary *skycon = skycons[i];
                        model.skycon = [self weatherSkycon:skycon[@"value"]];
                        model.imageName = [self weatherStateImageName:model.skycon];
                    }
                    
                    model.hint = response[@"forecast_keypoint"];
                    
                    [data addObject:model];
                }
                
                forecasts = data;
                dispatch_group_leave(group);
            } else {
                dispatch_group_leave(group);
            }
        } else {
            dispatch_group_leave(group);
        }
    } failed:^(NSError *error) {
        dispatch_group_leave(group);
    }];
    
    dispatch_group_notify(group, dispatch_get_main_queue(), ^{
        WeatherForeCastModel *model = forecasts.firstObject;
        realTimeModel.date = model.date;
        
        NSMutableDictionary *data = [[NSMutableDictionary alloc] initWithCapacity:0];
        [data setObject:realTimeModel forKey:@"WeatherRealTimeModel"];
        [data setObject:forecasts forKey:@"WeatherForeCasts"];
        
        result(data);
    });
}

+ (WeatherSkycon)weatherSkycon:(NSString *)skycon {
    if ([skycon isEqualToString:@"CLEAR_DAY"]) {
        return WeatherSkyconClearDay;
    } else if ([skycon isEqualToString:@"CLEAR_NIGHT"]) {
        return WeatherSkyconClearNight;
    } else if ([skycon isEqualToString:@"PARTLY_CLOUDY_DAY"]) {
        return WeatherSkyconPartlyCloudyDay;
    } else if ([skycon isEqualToString:@"PARTLY_CLOUDY_NIGHT"]) {
        return WeatherSkyconPartlyCloudyNight;
    } else if ([skycon isEqualToString:@"CLOUDY"]) {
        return WeatherSkyconCloudy;
    } else if ([skycon isEqualToString:@"RAIN"]) {
        return WeatherSkyconRain;
    } else if ([skycon isEqualToString:@"SNOW"]) {
        return WeatherSkyconSnow;
    } else if ([skycon isEqualToString:@"WIND"]) {
        return WeatherSkyconWind;
    } else {
        return WeatherSkyconHaze;
    }
}

+ (NSString *)weatherStateImageName:(WeatherSkycon)type {
    NSString *imageName = @"SunnyDay_State";
    switch (type) {
        case WeatherSkyconClearDay:
            imageName = @"SunnyDay_State";
            break;
        case WeatherSkyconClearNight:
            imageName = @"SunnyNight_State";
            break;
        case WeatherSkyconPartlyCloudyDay:
            imageName = @"CloudyDay_State";
            break;
        case WeatherSkyconPartlyCloudyNight:
            imageName = @"CloudyNight_State";
            break;
        case WeatherSkyconCloudy:
            imageName = @"Moon_State";
            break;
        case WeatherSkyconRain:
            imageName = @"Rain_State";
            break;
        case WeatherSkyconSnow:
            imageName = @"Snow_State";
            break;
        case WeatherSkyconWind:
            imageName = @"Wind_State";
            break;
        case WeatherSkyconHaze:
            imageName = @"Haze_State";
            break;
        default:
            break;
    }
    
    return imageName;
}

+ (NSArray *)weatherBackgroundColor:(WeatherSkycon)skycon {
    if (skycon == WeatherSkyconClearDay) {
        return @[(id)[UIColor rgbByHexStr:@"2f89e9"].CGColor, (id)[UIColor rgbByHexStr:@"62b0ee"].CGColor];
    } else if (skycon == WeatherSkyconClearNight) {
        return @[(id)[UIColor rgbByHexStr:@"003171"].CGColor, (id)[UIColor rgbByHexStr:@"1b496e"].CGColor];
    } else if (skycon == WeatherSkyconPartlyCloudyDay) {
        return @[(id)[UIColor rgbByHexStr:@"7897c0"].CGColor, (id)[UIColor rgbByHexStr:@"9abcd7"].CGColor];
    } else if (skycon == WeatherSkyconPartlyCloudyNight) {
        return @[(id)[UIColor rgbByHexStr:@"213857"].CGColor, (id)[UIColor rgbByHexStr:@"334655"].CGColor];
    } else if (skycon == WeatherSkyconHaze) {
        return @[(id)[UIColor rgbByHexStr:@"bfb08d"].CGColor, (id)[UIColor rgbByHexStr:@"5f5030"].CGColor];
    } else if (skycon == WeatherSkyconWind) {
        return @[(id)[UIColor rgbByHexStr:@"5b779c"].CGColor, (id)[UIColor rgbByHexStr:@"cbbd9d"].CGColor];
    } else {
        return @[(id)[UIColor rgbByHexStr:@"5b779c"].CGColor, (id)[UIColor rgbByHexStr:@"8aabc5"].CGColor];
    }
}

@end

/// 实时天气
@interface WeatherRealTimeModel ()

@end

@implementation WeatherRealTimeModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _currentTemperature = [NSString stringWithFormat:@"%li˚", [[dic objectForKey:@"temperature"] integerValue]];
        _currentHumidity = [NSString stringWithFormat:@"湿度%.f%%", [[dic objectForKey:@"humidity"] floatValue] * 100];
        _currentwindSpeed = [NSString stringWithFormat:@"风%.fkm/h", [[[dic objectForKey:@"wind"] objectForKey:@"speed"] floatValue]];
        _currentWeatherState = [NSString stringWithFormat:@"%@", [[dic objectForKey:@"comfort"] objectForKey:@"desc"]];
    }
    
    return self;
}

@end

/// 最近五天天气预报
@interface WeatherForeCastModel ()

@end

@implementation WeatherForeCastModel

@end
