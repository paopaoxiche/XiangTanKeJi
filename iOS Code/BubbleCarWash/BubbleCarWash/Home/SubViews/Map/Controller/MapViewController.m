//
//  MapViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <MAMapKit/MAMapKit.h>
#import <AMapFoundationKit/AMapFoundationKit.h>
#import "MapViewController.h"
#import "CarWashListTableViewController.h"
#import "GlobalMethods.h"
#import "FunctionMacro.h"
#import "CustomAnnotationView.h"
#import "HomeModel.h"
#import "UserManager.h"
#import "UIApplication+HUD.h"

@interface MapViewController () <MAMapViewDelegate>

@property (nonatomic, weak) IBOutlet UIView *containerView;
@property (nonatomic, strong) MAMapView *mapView;
@property (nonatomic, strong) CarWashListTableViewController *carWashListVC;
@property (nonatomic, copy) NSArray *nearbyWashList;
@property (nonatomic, strong) UIButton *fiveCarWashBtn;

@end

@implementation MapViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self.navigationController.navigationBar setTranslucent:NO];
    self.fiveCarWashBtn = [[UIButton alloc] init];
    [self.fiveCarWashBtn setImage:[UIImage imageNamed:@"SingleSelection_Normal"]
                         forState:UIControlStateNormal];
    [self.fiveCarWashBtn setTitle:@"五元洗车" forState:UIControlStateNormal];
    [self.fiveCarWashBtn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [self.fiveCarWashBtn addTarget:self
                            action:@selector(filterCarWashList)
                  forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *fiveCarWashItem = [[UIBarButtonItem alloc] initWithCustomView:self.fiveCarWashBtn];
    self.navigationItem.rightBarButtonItem = fiveCarWashItem;
    
    _mapView = [[MAMapView alloc] initWithFrame:_containerView.frame];
    _mapView.delegate = self;
    [_containerView addSubview:_mapView];
    
    _mapView.zoomLevel = 13;
    _mapView.showsUserLocation = YES;
    _mapView.userTrackingMode = MAUserTrackingModeFollow;
    
    MAUserLocationRepresentation *r = [[MAUserLocationRepresentation alloc] init];
    [_mapView updateUserLocationRepresentation:r];
    
    _carWashListVC = (CarWashListTableViewController *)[GlobalMethods viewControllerWithBuddleName:@"Map" vcIdentifier:@"CarWashListVC"];
    [self addChildViewController:_carWashListVC];
    [_mapView addSubview:_carWashListVC.view];
    
    [UIApplication showBusyHUD];
    [HomeDataModel loadNearbyWashList:[UserManager sharedInstance].location isMap:YES isSearch:NO result:^(NSArray *result) {
        [UIApplication stopBusyHUD];
        self.nearbyWashList = [result copy];
        self.carWashListVC.dataSource = self.nearbyWashList;
        [self addOrRemovePointAnnotation:YES];
    }];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [self.navigationController setNavigationBarHidden:NO animated:YES];
}

- (void)viewWillLayoutSubviews {
    [super viewWillLayoutSubviews];
    
    _mapView.frame = CGRectMake(0, 0, _containerView.frame.size.width, _containerView.frame.size.height);
    CGFloat height = self.nearbyWashList.count < 3 ? 112 * self.nearbyWashList.count : 300;
    self.carWashListVC.view.frame = CGRectMake(12, self.containerView.frame.size.height - height, self.mapView.frame.size.width - 24, height);
}

#pragma mark - Action Methods

- (void)filterCarWashList {
    BOOL isSelected = !self.fiveCarWashBtn.selected;
    self.fiveCarWashBtn.selected = isSelected;
    [self.fiveCarWashBtn setImage:[UIImage imageNamed:(isSelected ? @"SingleSelection_Normal" : @"SingleSelection_Selected")]
                         forState:UIControlStateNormal];
    [UIApplication showBusyHUD];
    // 移除之前的Point
    [self addOrRemovePointAnnotation:NO];
    
    [HomeDataModel loadNearbyWashList:[UserManager sharedInstance].location isMap:YES isSearch:YES result:^(NSArray *result) {
        [UIApplication stopBusyHUD];
        self.nearbyWashList = [result copy];
        self.carWashListVC.dataSource = self.nearbyWashList;
        [self addOrRemovePointAnnotation:YES];
        
        CGFloat height = self.nearbyWashList.count < 3 ? 112 * self.nearbyWashList.count : 300;
        self.carWashListVC.view.frame = CGRectMake(12, self.containerView.frame.size.height - height, self.mapView.frame.size.width - 24, height);
    }];
}

#pragma mark - Map Methods

- (void)addOrRemovePointAnnotation:(BOOL)isAdd {
    for (NearbyWashListModel *model in _nearbyWashList) {
        MAPointAnnotation *pointAnnotation = [[MAPointAnnotation alloc] init];
        pointAnnotation.coordinate = CLLocationCoordinate2DMake(model.location.lat, model.location.lng);
        pointAnnotation.title = model.carWashName;
        pointAnnotation.subtitle = [NSString stringWithFormat:@"%.2f", model.price];
        isAdd ? [_mapView addAnnotation:pointAnnotation] : [_mapView removeAnnotation:pointAnnotation];
    }
}

- (MAAnnotationView *)mapView:(MAMapView *)mapView viewForAnnotation:(id <MAAnnotation>)annotation {
    if ([annotation isKindOfClass:[MAPointAnnotation class]]) {
        static NSString *pointReuseIndentifier = @"pointReuseIndentifier";
        CustomAnnotationView *poiAnnotationView = (CustomAnnotationView *)[self.mapView dequeueReusableAnnotationViewWithIdentifier:pointReuseIndentifier];
        if (!poiAnnotationView)
        {
            poiAnnotationView = [[CustomAnnotationView alloc] initWithAnnotation:annotation reuseIdentifier:pointReuseIndentifier];
            
            // 屏蔽默认的calloutView
            poiAnnotationView.canShowCallout = NO;
        }
        
        poiAnnotationView.tradeStateColor = [UIColor brownColor];
        poiAnnotationView.tradeStateImgName = @"TradeStateOperate";
        poiAnnotationView.lowestServicePrice = annotation.subtitle;
        poiAnnotationView.carWashName = annotation.title;
        
        return poiAnnotationView;
    }
    
    return nil;
    
    return nil;
}

@end
