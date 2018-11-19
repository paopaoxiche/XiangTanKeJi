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
#import "TXScrollLabelView.h"

@interface WeatherViewController () <UITableViewDataSource, UITableViewDelegate>

@property (nonatomic, weak) IBOutlet UIImageView *weatherBgImgView;
@property (nonatomic, weak) IBOutlet UITableView *tableView;
@property (nonatomic, weak) IBOutlet UIView *backView;
@property (nonatomic, weak) IBOutlet UILabel *curTemperature;
@property (nonatomic, weak) IBOutlet UILabel *maximumTemperature;
@property (nonatomic, weak) IBOutlet UILabel *lowestTemperature;
@property (nonatomic, weak) IBOutlet UILabel *windLevelLabel;
@property (nonatomic, weak) IBOutlet UILabel *humidityLabel;
@property (nonatomic, weak) IBOutlet UILabel *curWeatherStateLabel;
@property (nonatomic, weak) IBOutlet UIView *weatherDescView;
@property (nonatomic, weak) IBOutlet UIView *locationView;
@property (nonatomic, copy) NSArray *dataSource;
@property (nonatomic, copy) NSArray *backgroundImageNames;
@property (nonatomic, strong) WeatherRealTimeModel *realTimeModel;
@property (nonatomic, strong) TXScrollLabelView *locationScrollView;
@property (nonatomic, strong) TXScrollLabelView *weatherDescScrollView;

@end

@implementation WeatherViewController

- (void)dealloc {
    if (_locationScrollView) {
        [_locationScrollView endScrolling];
        _locationScrollView = nil;
    }
    
    if (_weatherDescScrollView) {
        [_weatherDescScrollView endScrolling];
        _weatherDescScrollView = nil;
    }
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self setScrollView];
    
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
    [self setLocation];
    
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
        [self setWeatherDesc:forecastModel.hint];
        self.maximumTemperature.text = [NSString stringWithFormat:@"%@˚", forecastModel.maxTemperature];
        self.lowestTemperature.text = [NSString stringWithFormat:@"%@˚", forecastModel.minTemperature];
    }
}

- (void)setScrollView {
    _locationScrollView = [TXScrollLabelView scrollWithTitle:@""
                                                        type:TXScrollLabelViewTypeLeftRight
                                                    velocity:1
                                                     options:UIViewAnimationOptionCurveEaseInOut];
    _locationScrollView.frame = CGRectMake(0, 0, self.locationView.frame.size.width, self.locationView.frame.size.height);
    _locationScrollView.backgroundColor = [UIColor clearColor];
    _locationScrollView.font = [UIFont systemFontOfSize:18];
    [self.locationView addSubview:_locationScrollView];
    
    _weatherDescScrollView = [TXScrollLabelView scrollWithTitle:@""
                                                           type:TXScrollLabelViewTypeLeftRight
                                                       velocity:1
                                                        options:UIViewAnimationOptionCurveEaseInOut];
    _weatherDescScrollView.frame = CGRectMake(0, 0, self.weatherDescView.frame.size.width, self.weatherDescView.frame.size.height);
    _weatherDescScrollView.backgroundColor = [UIColor clearColor];
    _weatherDescScrollView.font = [UIFont systemFontOfSize:16];
    [self.weatherDescView addSubview:_weatherDescScrollView];
}

- (void)setLocation {
    self.locationScrollView.scrollTitle = [UserManager sharedInstance].address;
    [self.locationScrollView beginScrolling];
}

- (void)setWeatherDesc:(NSString *)str {
    self.weatherDescScrollView.scrollTitle = str;
    [self.weatherDescScrollView beginScrolling];
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
