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
#import "CommodityInfoViewController.h"
#import "TXScrollLabelView.h"
#import "FunctionMacro.h"

@interface HomeViewController () <UITableViewDataSource, UITableViewDelegate, AMapLocationManagerDelegate, AMapSearchDelegate, CommodityCellProtocol>

@property (nonatomic, weak) IBOutlet UIView *weatherView;
@property (weak, nonatomic) IBOutlet UIImageView *weatherBg;
@property (nonatomic, weak) IBOutlet UILabel *currentTemperatureLabel;
@property (nonatomic, weak) IBOutlet UILabel *maximumTemperature;
@property (nonatomic, weak) IBOutlet UILabel *lowestTemperature;
@property (nonatomic, weak) IBOutlet UILabel *windLevelLabel;
@property (nonatomic, weak) IBOutlet UILabel *humidityLabel;
@property (nonatomic, weak) IBOutlet UILabel *curWeatherStateLabel;
@property (nonatomic, weak) IBOutlet UITableView *nearWashTableView;
@property (nonatomic, weak) IBOutlet UIView *scrollSubView;
@property (weak, nonatomic) IBOutlet UILabel *hintLabel;
@property (weak, nonatomic) IBOutlet UIView *locationView;
@property (weak, nonatomic) IBOutlet UIView *weatherDescView;
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
@property (nonatomic, strong) TXScrollLabelView *locationScrollView;
@property (nonatomic, strong) TXScrollLabelView *weatherDescScrollView;

@end

@implementation HomeViewController

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    
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
    
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(loadRecentWashRecord)
                                                 name:@"UpdateUserInfo"
                                               object:nil];
    
    _nearWashTableView.layer.cornerRadius = 4;
    _isUpdateMearByWashList = YES;
    _nearWashTableView.rowHeight = 68;
    
    [self setScrollView];
    [self.nearWashTableView registerNib:[UINib nibWithNibName:@"RecentWashRecordCell" bundle:[NSBundle mainBundle]] forCellReuseIdentifier:@"RecentWashRecordIdentifier"];
    
    UITapGestureRecognizer *singleGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(touchWeatherView:)];
    [_weatherView addGestureRecognizer:singleGesture];
    
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Home" bundle:[NSBundle mainBundle]];
    _commodityRecommendationVC = [storyboard instantiateViewControllerWithIdentifier:@"CommodityRecommendationVC"];
    _commodityRecommendationVC.delegate = self;
    [self addChildViewController:_commodityRecommendationVC];
    [self.scrollSubView addSubview:_commodityRecommendationVC.view];
    
    [[UserManager sharedInstance] autoLogin:^(NSInteger code) {
        if (code == 401) {
            UIViewController *loginVC = [GlobalMethods viewControllerWithBuddleName:@"Login" vcIdentifier:@"LoginVC"];
            [self presentViewController:loginVC animated:YES completion:nil];
        }
        
        [self loadRecommendWashCommodity];
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
    
    _locationScrollView.frame = CGRectMake(0, 0, self.locationView.frame.size.width, self.locationView.frame.size.height);
    _weatherDescScrollView.frame = CGRectMake(0, 0, self.weatherDescView.frame.size.width, self.weatherDescView.frame.size.height);
    BOOL isOwner = [UserManager sharedInstance].userType == UserTypeOwner;
    
    // 根据数据个数设置列表高度
    if (self.nearbyWashList.count > 0 && self.nearbyWashList.count < 4) {
        _washViewHeightConstraint.constant = 322 - (4 - self.nearbyWashList.count) * 68;
    }
    
    CGFloat itemWidth = (kScreenWidth - 2 * 16 - (3 - 1) * 18) / 3;
    CGFloat itemHeight = itemWidth + 50;
    CGFloat height = 18 * 3 + itemHeight * 2;
    CGFloat viewHeigth = isOwner ? height + 40 : height;
    NSInteger count = self.commodityRecommendationVC.recommendWashCommodity.count;
    if (count > 0 && count < 4 && !isOwner) {
        viewHeigth -= height * 0.5;
    }
    if (count == 0 && isOwner) {
        viewHeigth = 0;
        _commodityRecommendationVC.view.hidden = YES;
    } else {
        _commodityRecommendationVC.view.hidden = NO;
        _commodityRecommendationVC.view.frame = CGRectMake(0, 120 + _washViewHeightConstraint.constant + 16, [UIScreen mainScreen].bounds.size.width, viewHeigth);
    }
    _scrollViewHeightConstraint.constant = viewHeigth + 472 + 10;
}

- (void)loadRecommendWashCommodity {
    if ([UserManager sharedInstance].userType == UserTypeOwner) {
        [HomeDataModel loadRecommendWashCommodity:^(NSArray *result) {
            self.commodityRecommendationVC.recommendWashCommodity = result;
            CGFloat itemWidth = (kScreenWidth - 2 * 16 - (3 - 1) * 18) / 3;
            CGFloat itemHeight = itemWidth + 50;
            CGFloat height = 18 * 3 + itemHeight * 2;
            CGFloat viewHeigth = height + 40;
            NSInteger count = self.commodityRecommendationVC.recommendWashCommodity.count;
            if (count == 0) {
                viewHeigth = 0;
                self.commodityRecommendationVC.view.hidden = YES;
            } else {
                self.commodityRecommendationVC.view.hidden = NO;
                self.commodityRecommendationVC.view.frame = CGRectMake(0, 120 + self.washViewHeightConstraint.constant + 16, [UIScreen mainScreen].bounds.size.width, viewHeigth);
            }
            self.scrollViewHeightConstraint.constant = viewHeigth + 472 + 10;
        }];
    }
}

- (void)loadNearByWashList {
    if ([UserManager sharedInstance].userType == UserTypeCarWash) {
        return;
    }
    
    [HomeDataModel loadNearbyWashList:[UserManager sharedInstance].location isMap:NO isSearch:NO result:^(NSArray *result) {
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
    if ([UserManager sharedInstance].userType == UserTypeOwner) {
        return;
    }
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
    [NetworkTools obtainCarWashCommodityList:[UserManager sharedInstance].carWashInfo.washID success:^(NSDictionary *response, BOOL isSuccess) {
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
        
        [self.view layoutIfNeeded];
    } failed:^(NSError *error) {
        self.commodityRecommendationVC.recommendWashCommodity = @[];
        [self.view layoutIfNeeded];
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
            self.maximumTemperature.text = forecastModel.maxTemperature;
            self.lowestTemperature.text = forecastModel.minTemperature;
            [self setWeather:forecastModel.hint];
            
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
    BOOL isUserTypeOwner = [UserManager sharedInstance].userType == UserTypeOwner;
    NSString *identifier = isUserTypeOwner ? @"MapVC" : @"WashRecordVC";
    UIViewController *vc = [GlobalMethods viewControllerWithBuddleName:@"Home" vcIdentifier:identifier];
    vc.hidesBottomBarWhenPushed = YES;
    [self.navigationController pushViewController:vc animated:NO];
}

- (void)setScrollView {
    _locationScrollView = [TXScrollLabelView scrollWithTitle:@""
                                                        type:TXScrollLabelViewTypeLeftRight
                                                    velocity:1
                                                     options:UIViewAnimationOptionCurveEaseInOut];
    _locationScrollView.frame = CGRectMake(0, 0, self.locationView.frame.size.width, self.locationView.frame.size.height);
    _locationScrollView.backgroundColor = [UIColor clearColor];
    _locationScrollView.font = [UIFont systemFontOfSize:12];
    [self.locationView addSubview:_locationScrollView];
    
    _weatherDescScrollView = [TXScrollLabelView scrollWithTitle:@""
                                                           type:TXScrollLabelViewTypeLeftRight
                                                       velocity:1
                                                        options:UIViewAnimationOptionCurveEaseInOut];
    _weatherDescScrollView.frame = CGRectMake(0, 0, self.weatherDescView.frame.size.width, self.weatherDescView.frame.size.height);
    _weatherDescScrollView.backgroundColor = [UIColor clearColor];
    _weatherDescScrollView.font = [UIFont systemFontOfSize:12];
    [self.weatherDescView addSubview:_weatherDescScrollView];
}

- (void)setLocation {
    self.locationScrollView.scrollTitle = [UserManager sharedInstance].address;
    [self.locationScrollView beginScrolling];
}

- (void)setWeather:(NSString *)str {
    self.weatherDescScrollView.scrollTitle = str;
    [self.weatherDescScrollView beginScrolling];
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return _nearbyWashList.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    if ([UserManager sharedInstance].userType == UserTypeOwner) {
        NearbyWashListModel *model = _nearbyWashList[indexPath.row];
        NearWashInfoCell *cell = [tableView dequeueReusableCellWithIdentifier:@"NearWashInfoIdentifier"];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.avatarUrl = model.avatarUrl;
        cell.name = model.carWashName;
        cell.lowestPrice = model.price;
        cell.honor = model.honor;
        cell.washNumber = model.washCount;
        cell.distance = model.distance;
        
        return cell;
    }
    
    CarWashRecordModel *model = _nearbyWashList[indexPath.row];
    RecentWashRecordCell *cell = [tableView dequeueReusableCellWithIdentifier:@"RecentWashRecordIdentifier"];
    cell.imageName = model.avatarUrl;
    cell.name = model.nickName;
    cell.type = model.carType;
    cell.price = model.price;
    cell.time = model.time;
    
    return cell;
}

#pragma mark - CommodityCellProtocol

- (void)didSelectedCommodityCellWithIndex:(NSInteger)index section:(NSInteger)section {
    CommodityInfoViewController *infoVC = (CommodityInfoViewController *)[GlobalMethods viewControllerWithBuddleName:@"CarWash" vcIdentifier:@"CommodityInfoVC"];
    if ([UserManager sharedInstance].userType == UserTypeOwner) {
        RecommendWashModel *washModel = self.commodityRecommendationVC.recommendWashCommodity[section];
        RecommendCommodityModel *model = washModel.commodityList[index];
        infoVC.model = model;
    } else {
        RecommendCommodityModel *model = self.commodityRecommendationVC.recommendWashCommodity[index];
        infoVC.model = model;
    }
    infoVC.hidesBottomBarWhenPushed = YES;
    [self.navigationController pushViewController:infoVC animated:NO];
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
            [self loadRecommendWashCommodity];
        }
    }
    
    [[NSNotificationCenter defaultCenter] postNotificationName:@"UpdateWeatherInfo" object:nil];
    [self loadWeatherInfos];
    [self searchReGeocode];
}

#pragma mark - AMapSearchDelegate

/**
 *  逆地理编码回调
 */
- (void)onReGeocodeSearchDone:(AMapReGeocodeSearchRequest *)request response:(AMapReGeocodeSearchResponse *)response {
    if (response.regeocode) {
        [UserManager sharedInstance].address = [NSString stringWithFormat:@"%@", response.regeocode.formattedAddress];
        [self setLocation];
        
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
