//
//  HomeViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/6/25.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <AMapFoundationKit/AMapFoundationKit.h>
#import <AMapLocationKit/AMapLocationKit.h>

#import "HomeViewController.h"
#import "NearWashInfoCell.h"
#import "CommodityRecommendationViewController.h"
#import "GlobalMethods.h"
#import "UserManager.h"
#import "UserInfoModel.h"
#import "NetworkTools.h"

@interface HomeViewController () <UITableViewDataSource, UITableViewDelegate, AMapLocationManagerDelegate>

@property (weak, nonatomic) IBOutlet UIView *weatherView;
@property (weak, nonatomic) IBOutlet UITableView *nearWashTableView;
@property (weak, nonatomic) IBOutlet UIView *scrollSubView;
@property (strong, nonatomic) CommodityRecommendationViewController *commodityRecommendationVC;
@property (strong, nonatomic) AMapLocationManager *locationManager;

@end

@implementation HomeViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    _nearWashTableView.layer.cornerRadius = 4;
    
    UITapGestureRecognizer *singleGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(touchWeatherView:)];
    [_weatherView addGestureRecognizer:singleGesture];
    
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Home" bundle:[NSBundle mainBundle]];
    _commodityRecommendationVC = [storyboard instantiateViewControllerWithIdentifier:@"CommodityRecommendationVC"];
    [self addChildViewController:_commodityRecommendationVC];
    [self.scrollSubView addSubview:_commodityRecommendationVC.view];
    
    [[UserManager sharedInstance] autoLogin];
    
    self.locationManager = [[AMapLocationManager alloc] init];
    self.locationManager.delegate = self;
    self.locationManager.distanceFilter = 200;
    [self.locationManager startUpdatingLocation];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [self.navigationController setNavigationBarHidden:YES animated:YES];
}

- (void)viewDidLayoutSubviews {
    [super viewDidLayoutSubviews];
    _commodityRecommendationVC.view.frame = CGRectMake(0, 434, [UIScreen mainScreen].bounds.size.width, 350);
}

- (void)touchWeatherView:(UIGestureRecognizer *)gesture {
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Home" bundle:[NSBundle mainBundle]];
    UIViewController *weatherInfoVC = [storyboard instantiateViewControllerWithIdentifier:@"WeatherInfoVC"];
    [self presentViewController:weatherInfoVC animated:NO completion:nil];
}

- (IBAction)push2Map:(id)sender {
    UIViewController *mapVC = [GlobalMethods viewControllerWithBuddleName:@"Home" vcIdentifier:@"MapVC"];
    [self.navigationController pushViewController:mapVC animated:YES];
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 4;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    NearWashInfoCell *cell = [tableView dequeueReusableCellWithIdentifier:@"NearWashInfoIdentifier"];
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 62;
}

#pragma mark - AMapLocationManagerDelegate

- (void)amapLocationManager:(AMapLocationManager *)manager didUpdateLocation:(CLLocation *)location {
    NSLog(@"location:{lat:%f; lon:%f; accuracy:%f}", location.coordinate.latitude, location.coordinate.longitude, location.horizontalAccuracy);
}

@end
