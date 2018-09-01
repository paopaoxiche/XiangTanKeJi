//
//  WeatherInfoCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface WeatherInfoCell : UITableViewCell

@property (nonatomic, copy) NSString *date;                 // 日期
@property (nonatomic, copy) NSString *week;                 // 周几
@property (nonatomic, copy) NSString *maxTemperature;       // 最高温度
@property (nonatomic, copy) NSString *lowTemperature;       // 最低温度
@property (nonatomic, copy) NSString *imgName;              // 天气状态图名

@end
