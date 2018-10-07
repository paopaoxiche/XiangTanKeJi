//
//  HomeViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/6/25.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <AMapFoundationKit/AMapFoundationKit.h>
#import <AMapLocationKit/AMapLocationKit.h>
#import <AMapSearchKit/AMapSearchKit.h>

#import "HomeViewController.h"
#import "NearWashInfoCell.h"
#import "CommodityRecommendationViewController.h"
#import "GlobalMethods.h"
#import "UserManager.h"
#import "UserInfoModel.h"
#import "NetworkTools.h"
#import "HomeModel.h"
#import "DataType.h"
#import "WeatherModel.h"
#import "WeatherViewController.h"
#import "GlobalMethods.h"
#import "ExpensesRecordListModel.h"
#import "RecentWashRecordCell.h"
#import "CarWashInfoModel.h"

@interface HomeViewController () <UITableViewDataSource, UITableViewDelegate, AMapLocationManagerDelegate, AMapSearchDelegate>

@property (nonatomic, weak) IBOutlet UIView *weatherView;
@property (weak, nonatomic) IBOutlet UIImageView *weatherBg;
@property (nonatomic, weak) IBOutlet UILabel *currentTemperatureLabel;
@property (nonatomic, weak) IBOutlet UILabel *locationLabel;
@property (nonatomic, weak) IBOutlet UILabel *maximumTemperature;
@property (nonatomic, weak) IBOutlet UILabel *lowestTemperature;
@property (nonatomic, weak) IBOutlet UILabel *windLevelLabel;
@property (nonatomic, weak) IBOutlet UILabel *humidityLabel;
@property (nonatomic, weak) IBOutlet UILabel *curWeatherStateLabel;
@property (nonatomic, weak) IBOutlet UILabel *weatherDescLabel;
@property (nonatomic, weak) IBOutlet UITableView *nearWashTableView;
@property (nonatomic, weak) IBOutlet UIView *scrollSubView;
@property (weak, nonatomic) IBOutlet UILabel *hintLabel;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *scrollViewHeightConstraint;
@property (weak, nonatomic) IBOutlet UILabel *emptyLabel;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *washViewHeightConstraint;
@property (nonatomic, strong) CommodityRecommendationViewController *commodityRecommendationVC;
@property (nonatomic, strong) AMapLocationManager *locationManager;
@property (nonatomic, strong) AMapSearchAPI *search;
@property (nonatomic, assign) BOOL isUpdateMearByWashList;
@property (nonatomic, copy) NSArray *nearbyWashList;
@property (nonatomic, copy) NSDictionary *weatherInfos;
@property (nonatomic, copy) NSArray *backgroundImageNames;

@end

@implementation HomeViewController

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    _nearWashTableView.layer.cornerRadius = 4;
    _isUpdateMearByWashList = YES;
    _nearWashTableView.rowHeight = 68;
    
    UITapGestureRecognizer *singleGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(touchWeatherView:)];
    [_weatherView addGestureRecognizer:singleGesture];
    
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Home" bundle:[NSBundle mainBundle]];
    _commodityRecommendationVC = [storyboard instantiateViewControllerWithIdentifier:@"CommodityRecommendationVC"];
    [self addChildViewController:_commodityRecommendationVC];
    [self.scrollSubView addSubview:_commodityRecommendationVC.view];
    
    [[UserManager sharedInstance] autoLogin:^(NSInteger code) {
        if (code == 401) {
            UIViewController *loginVC = [GlobalMethods viewControllerWithBuddleName:@"Login" vcIdentifier:@"LoginVC"];
            [self presentViewController:loginVC animated:YES completion:nil];
        } else {
            if ([UserManager sharedInstance].userType ==  UserTypeOwner) {
                [HomeDataModel loadRecommendWashCommodity:^(NSArray *result) {
                    self.commodityRecommendationVC.recommendWashCommodity = result;
                }];
            }
        }
    }];
    
    self.locationManager = [[AMapLocationManager alloc] init];
    self.locationManager.delegate = self;
    self.locationManager.distanceFilter = 200;
    [self.locationManager startUpdatingLocation];
    
    self.search = [[AMapSearchAPI alloc] init];
    self.search.delegate = self;
    
    // 从状态栏开始布局
    self.edgesForExtendedLayout = UIRectEdgeNone;
    self.extendedLayoutIncludesOpaqueBars = NO;
    self.modalPresentationCapturesStatusBarAppearance = NO;
    self.automaticallyAdjustsScrollViewInsets = NO;
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(loadRecentWashRecord) name:@"UpdateUserInfo" object:nil];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [self.navigationController setNavigationBarHidden:YES animated:YES];
    
    BOOL isOwner = [UserManager sharedInstance].userType == UserTypeOwner;
    if (isOwner && [UserManager sharedInstance].location.lat != 0) {
        [self loadNearByWashList];
    }
    
    if (!isOwner && [UserManager sharedInstance].carWashInfo.washID != 0) {
        [self loadRecentWashRecord];
    }
}

- (void)viewDidLayoutSubviews {
    [super viewDidLayoutSubviews];
    
    BOOL isOwner = [UserManager sharedInstance].userType == UserTypeOwner;
    
    // 根据数据个数设置列表高度
    if (self.nearbyWashList.count > 0 && self.nearbyWashList.count < 4) {
        _washViewHeightConstraint.constant = 322 - (4 - self.nearbyWashList.count) * 68;
    }
    
    CGFloat heigth = isOwner ? 410 : 370;
    NSInteger count = self.commodityRecommendationVC.recommendWashCommodity.count;
    if (count > 0 && count < 4 && !isOwner) {
        heigth -= 160;
    }
    if (count == 0 && isOwner) {
        heigth = 0;
        _commodityRecommendationVC.view.hidden = YES;
    } else {
        _commodityRecommendationVC.view.hidden = NO;
        _commodityRecommendationVC.view.frame = CGRectMake(0, 120 + _washViewHeightConstraint.constant + 16, [UIScreen mainScreen].bounds.size.width, heigth);
    }
    _scrollViewHeightConstraint.constant = heigth + 472 + 10;
}

- (void)loadNearByWashList {
    [HomeDataModel loadNearbyWashList:[UserManager sharedInstance].location isMap:NO result:^(NSArray *result) {
        self.nearbyWashList = [result copy];
        if (result.count > 0) {
            [self.nearWashTableView reloadData];
            self.nearWashTableView.hidden = NO;
            self.emptyLabel.hidden = YES;
        } else {
            self.nearWashTableView.hidden = YES;
            self.emptyLabel.text = @"附近暂无洗车场";
            self.emptyLabel.hidden = NO;
        }
    }];
}

- (void)loadRecentWashRecord {
    _hintLabel.text = @"近期洗车列表";
    [self loadCarWashCommodityList];
    
    [ExpensesRecordListModel loadRecentCarWashList:4 result:^(NSArray *result) {
        self.nearbyWashList = [result copy];
        if (result.count > 0) {
            [self.nearWashTableView reloadData];
            self.nearWashTableView.hidden = NO;
            self.emptyLabel.hidden = YES;
        } else {
            self.nearWashTableView.hidden = YES;
            self.emptyLabel.text = @"近期暂无洗车记录";
            self.emptyLabel.hidden = NO;
        }
    }];
}

- (void)loadCarWashCommodityList {
    [[NetworkTools sharedInstance] obtainCarWashCommodityList:[UserManager sharedInstance].carWashInfo.washID success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSDictionary *dataArr = [response objectForKey:@"data"];
            NSMutableArray *list = [NSMutableArray arrayWithCapacity:dataArr.count];
            for (NSDictionary *dic in dataArr) {
                RecommendCommodityModel *model = [[RecommendCommodityModel alloc] initWithDic:dic];
                [list addObject:model];
            }
            
            self.commodityRecommendationVC.recommendWashCommodity = list;
        } else {
            self.commodityRecommendationVC.recommendWashCommodity = @[];
        }
    } failed:^(NSError *error) {
        self.commodityRecommendationVC.recommendWashCommodity = @[];
    }];
}

- (void)loadWeatherInfos {
    [WeatherModel loadWeatherData:[UserManager sharedInstance].location result:^(NSDictionary *result) {
        self.weatherInfos = result;
        
        WeatherRealTimeModel *realTimeModel = result[@"WeatherRealTimeModel"];
        self.currentTemperatureLabel.text = realTimeModel.currentTemperature;
        self.curWeatherStateLabel.text = realTimeModel.currentWeatherState;
        self.windLevelLabel.text = realTimeModel.currentwindSpeed;
        self.humidityLabel.text = realTimeModel.currentHumidity;
        
        NSArray *forecasts = result[@"WeatherForeCasts"];
        if (forecasts.count > 0) {
            WeatherForeCastModel *forecastModel = forecasts[0];
            self.weatherDescLabel.text = forecastModel.hint;
            self.maximumTemperature.text = forecastModel.maxTemperature;
            self.lowestTemperature.text = forecastModel.minTemperature;
            
            [GlobalMethods setGradientColor:[WeatherModel weatherBackgroundColor:forecastModel.skycon]
                                 startPoint:CGPointMake(0, 0)
                                   endPoint:CGPointMake(0, 1)
                                       view:self.view];
            self.weatherBg.image = [UIImage imageNamed:self.backgroundImageNames[forecastModel.skycon]];
        }
    }];
}

- (void)searchReGeocode {
    // 设置逆地理编码查询参数
    AMapReGeocodeSearchRequest *regeo = [[AMapReGeocodeSearchRequest alloc] init];
    regeo.location = [AMapGeoPoint locationWithLatitude:[UserManager sharedInstance].location.lat
                                              longitude:[UserManager sharedInstance].location.lng];
    regeo.requireExtension = YES;
    
    // 发起逆地理编码查询
    [self.search AMapReGoecodeSearch:regeo];
}

- (void)touchWeatherView:(UIGestureRecognizer *)gesture {
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Home" bundle:[NSBundle mainBundle]];
    WeatherViewController *weatherInfoVC = [storyboard instantiateViewControllerWithIdentifier:@"WeatherInfoVC"];
    weatherInfoVC.weatherInfos = _weatherInfos;
    [self presentViewController:weatherInfoVC animated:NO completion:nil];
}

- (IBAction)push2Map:(id)sender {
    NSString *identifier = [UserManager sharedInstance].userType == UserTypeOwner ? @"MapVC" : @"WashRecordVC";
    UIViewController *vc = [GlobalMethods viewControllerWithBuddleName:@"Home" vcIdentifier:identifier];
    vc.hidesBottomBarWhenPushed = YES;
    [self.navigationController pushViewController:vc animated:NO];
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return _nearbyWashList.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    if ([UserManager sharedInstance].userType == UserTypeOwner) {
        NearbyWashListModel *model = _nearbyWashList[indexPath.row];
        NearWashInfoCell *cell = [tableView dequeueReusableCellWithIdentifier:@"NearWashInfoIdentifier"];
        cell.avatarUrl = model.avatarUrl;
        cell.name = model.carWashName;
        cell.lowestPrice = model.price;
        cell.honor = model.honor;
        cell.washNumber = model.washCount;
        cell.distance = model.distance;
        
        return cell;
    }
    
//    ExpensesRecordModel *model = _nearbyWashList[indexPath.row];
    RecentWashRecordCell *cell = [tableView dequeueReusableCellWithIdentifier:@"RecentWashRecordIdentifier"];
//    cell.imageName =
    
    return cell;
}

#pragma mark - AMapLocationManagerDelegate

- (void)amapLocationManager:(AMapLocationManager *)manager didUpdateLocation:(CLLocation *)location {
    NSLog(@"location:{lat:%f; lon:%f; accuracy:%f}", location.coordinate.latitude, location.coordinate.longitude, location.horizontalAccuracy);
    Location userLocation;
    userLocation.lat = location.coordinate.latitude;
    userLocation.lng = location.coordinate.longitude;
    [UserManager sharedInstance].location = userLocation;
    if (_isUpdateMearByWashList) {
        _isUpdateMearByWashList = NO;
        if ([UserManager sharedInstance].userType ==  UserTypeOwner) {
            [self loadNearByWashList];
        }
        
        [self loadWeatherInfos];
    }
    
    [self searchReGeocode];
}

#pragma mark - AMapSearchDelegate

/**
 *  逆地理编码回调
 */
- (void)onReGeocodeSearchDone:(AMapReGeocodeSearchRequest *)request response:(AMapReGeocodeSearchResponse *)response {
    if (response.regeocode) {
        [UserManager sharedInstance].address = [NSString stringWithFormat:@"%@%@", response.regeocode.formattedAddress, response.regeocode.pois.firstObject.name];
        self.locationLabel.text = [UserManager sharedInstance].address;
        
        NSString *province = response.regeocode.addressComponent.province;
        NSString *city = response.regeocode.addressComponent.city;
        NSString *district = response.regeocode.addressComponent.district;
        [UserManager sharedInstance].province = province;
        [UserManager sharedInstance].city = city;
        [UserManager sharedInstance].district = district;
        
        [[NSNotificationCenter defaultCenter] postNotificationName:@"UpdateLocation" object:nil];
    }
}

/**
 *  检索失败回调
 */
- (void)AMapSearchRequest:(id)request didFailWithError:(NSError *)error {
    NSLog(@"Error: %@", error);
}

#pragma mark - Lazy loading

- (NSArray *)backgroundImageNames {
    if (!_backgroundImageNames) {
        _backgroundImageNames = @[@"SunnyDay_HomeBg", @"SunnyNight_HomeBg", @"PartlyCloudy_HomeBg", @"PartlyCloudy_HomeBg", @"", @"Rain_HomeBg", @"Snow_HomeBg", @"Wind_HomeBg", @""];
    }
    
    return _backgroundImageNames;
}

@end
