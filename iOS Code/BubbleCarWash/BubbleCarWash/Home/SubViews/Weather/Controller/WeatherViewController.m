//
//  WeatherViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "WeatherViewController.h"
#import "WeatherInfoCell.h"
#import "UIColor+Category.h"
#import "GlobalMethods.h"
#import "WeatherModel.h"
#import "UserManager.h"

@interface WeatherViewController () <UITableViewDataSource, UITableViewDelegate>

@property (nonatomic, weak) IBOutlet UIImageView *weatherBgImgView;
@property (nonatomic, weak) IBOutlet UITableView *tableView;
@property (nonatomic, weak) IBOutlet UIView *backView;
@property (nonatomic, weak) IBOutlet UILabel *locationLabel;
@property (nonatomic, weak) IBOutlet UILabel *curTemperature;
@property (nonatomic, weak) IBOutlet UILabel *maximumTemperature;
@property (nonatomic, weak) IBOutlet UILabel *lowestTemperature;
@property (nonatomic, weak) IBOutlet UILabel *windLevelLabel;
@property (nonatomic, weak) IBOutlet UILabel *humidityLabel;
@property (nonatomic, weak) IBOutlet UILabel *curWeatherStateLabel;
@property (nonatomic, weak) IBOutlet UILabel *weatherDescLabel;
@property (nonatomic, copy) NSArray *dataSource;
@property (nonatomic, copy) NSArray *backgroundImageNames;
@property (nonatomic, strong) WeatherRealTimeModel *realTimeModel;

@end

@implementation WeatherViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    UITapGestureRecognizer *singleGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(backToSuperView)];
    [_backView addGestureRecognizer:singleGesture];
    
    self.realTimeModel = _weatherInfos[@"WeatherRealTimeModel"];
    self.dataSource = [[NSArray alloc] initWithArray:_weatherInfos[@"WeatherForeCasts"]];
    WeatherForeCastModel *model = _dataSource[0];
    [GlobalMethods setGradientColor:[WeatherModel weatherBackgroundColor:model.skycon]
                         startPoint:CGPointMake(0, 0)
                           endPoint:CGPointMake(0, 1)
                               view:self.view];
    self.weatherBgImgView.image = [UIImage imageNamed:self.backgroundImageNames[model.skycon]];
    
    [self setWeatherRealTimeInfo];
    self.locationLabel.text = [UserManager sharedInstance].address;
    
    _tableView.scrollEnabled = NO;
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [self.navigationController setNavigationBarHidden:YES animated:YES];
}

- (void)backToSuperView {
    [self dismissViewControllerAnimated:NO completion:nil];
}

- (void)setWeatherRealTimeInfo {
    self.curTemperature.text = _realTimeModel.currentTemperature;
    self.curWeatherStateLabel.text = _realTimeModel.currentWeatherState;
    self.windLevelLabel.text = _realTimeModel.currentwindSpeed;
    self.humidityLabel.text = _realTimeModel.currentHumidity;
    
    if (_dataSource.count > 0) {
        WeatherForeCastModel *forecastModel = _dataSource[0];
        self.weatherDescLabel.text = forecastModel.hint;
        self.maximumTemperature.text = [NSString stringWithFormat:@"%@˚", forecastModel.maxTemperature];
        self.lowestTemperature.text = [NSString stringWithFormat:@"%@˚", forecastModel.minTemperature];
    }
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return _dataSource.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    WeatherForeCastModel *model = _dataSource[indexPath.row];
    WeatherInfoCell *cell = [tableView dequeueReusableCellWithIdentifier:@"WeatherInfoIdentifier" forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    cell.date = model.date;
    cell.week = model.week;
    cell.maxTemperature = model.maxTemperature;
    cell.lowTemperature = model.minTemperature;
    cell.imgName = model.imageName;
    
    return cell;
}

#pragma mark - Lazy loading

- (NSArray *)backgroundImageNames {
    if (!_backgroundImageNames) {
        _backgroundImageNames = @[@"SunnyDay_Bg", @"SunnyNight_Bg", @"PartlyCloudy_Bg", @"PartlyCloudy_Bg", @"", @"Rain_Bg", @"Snow_Bg", @"Wind_Bg", @""];
    }
    
    return _backgroundImageNames;
}

@end
