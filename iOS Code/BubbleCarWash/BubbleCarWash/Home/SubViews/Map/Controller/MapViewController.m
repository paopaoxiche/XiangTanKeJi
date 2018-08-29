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

@interface MapViewController () <MAMapViewDelegate>

@property (weak, nonatomic) IBOutlet UIView *containerView;
@property (nonatomic, strong) MAMapView *mapView;
@property (nonatomic, strong) CarWashListTableViewController *carWashListVC;

@end

@implementation MapViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self.navigationController.navigationBar setTranslucent:NO];
    
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
    
    MAPointAnnotation *pointAnnotation = [[MAPointAnnotation alloc] init];
    pointAnnotation.coordinate = CLLocationCoordinate2DMake(22.567, 113.89);
    [_mapView addAnnotation:pointAnnotation];
    
    MAPointAnnotation *pointAnnotation1 = [[MAPointAnnotation alloc] init];
    pointAnnotation1.coordinate = CLLocationCoordinate2DMake(22.569, 113.91);
    [_mapView addAnnotation:pointAnnotation1];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [self.navigationController setNavigationBarHidden:NO animated:YES];
}

- (void)viewWillLayoutSubviews {
    [super viewWillLayoutSubviews];
    
    _mapView.frame = CGRectMake(0, 0, _containerView.frame.size.width, _containerView.frame.size.height);
    _carWashListVC.view.frame = CGRectMake(12, _containerView.frame.size.height - 300, _mapView.frame.size.width - 24, 300);
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
        poiAnnotationView.lowestServicePrice = @"¥20.00";
        poiAnnotationView.carWashName = @"宝安中心洗车场";
        
        return poiAnnotationView;
    }
    
    return nil;
    
    return nil;
}

@end
