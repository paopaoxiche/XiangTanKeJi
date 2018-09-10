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

@interface WeatherViewController () <UITableViewDataSource, UITableViewDelegate>

@property (weak, nonatomic) IBOutlet UIImageView *weatherBgImgView;
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UIView *backView;
@property (weak, nonatomic) IBOutlet UILabel *locationLabel;
@property (weak, nonatomic) IBOutlet UILabel *curTemperature;
@property (weak, nonatomic) IBOutlet UILabel *maximumTemperature;
@property (weak, nonatomic) IBOutlet UILabel *lowestTemperature;
@property (weak, nonatomic) IBOutlet UILabel *windLevelLabel;
@property (weak, nonatomic) IBOutlet UILabel *humidityLabel;
@property (weak, nonatomic) IBOutlet UILabel *curWeatherStateLabel;
@property (weak, nonatomic) IBOutlet UILabel *weatherDescLabel;

@end

@implementation WeatherViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    UITapGestureRecognizer *singleGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(backToSuperView)];
    [_backView addGestureRecognizer:singleGesture];
    
    [GlobalMethods setGradientColor:@[(id)[UIColor rgbByHexStr:@"5b779c"].CGColor, (id)[UIColor rgbByHexStr:@"cbbd9d"].CGColor]
                         startPoint:CGPointMake(0, 0)
                           endPoint:CGPointMake(0, 1)
                               view:self.view];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [self.navigationController setNavigationBarHidden:YES animated:YES];
}

- (void)backToSuperView {
    [self dismissViewControllerAnimated:NO completion:nil];
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 5;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    WeatherInfoCell *cell = [tableView dequeueReusableCellWithIdentifier:@"WeatherInfoIdentifier" forIndexPath:indexPath];
    
    return cell;
}

#pragma mark - UITableViewDelegate

@end
