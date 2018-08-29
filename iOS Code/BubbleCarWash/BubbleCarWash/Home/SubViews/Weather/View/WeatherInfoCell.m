//
//  WeatherInfoCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "WeatherInfoCell.h"

@interface WeatherInfoCell ()

@property (weak, nonatomic) IBOutlet UILabel *dateLabel;
@property (weak, nonatomic) IBOutlet UILabel *weekLabel;
@property (weak, nonatomic) IBOutlet UILabel *maximumTemperatureLabel;
@property (weak, nonatomic) IBOutlet UILabel *lowestTemperatureLabel;
@property (weak, nonatomic) IBOutlet UIImageView *weatherStateImgView;

@end

@implementation WeatherInfoCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (void)setDate:(NSString *)date {
    _dateLabel.text = date;
}

- (void)setWeek:(NSString *)week {
    _weekLabel.text = week;
}

- (void)setMaxTemperature:(NSString *)maxTemperature {
    _maximumTemperatureLabel.text = maxTemperature;
}

- (void)setLowTemperature:(NSString *)lowTemperature {
    _lowestTemperatureLabel.text = lowTemperature;
}

- (void)setImgName:(NSString *)imgName {
    _weatherStateImgView.image = [UIImage imageNamed:imgName];
}

@end
