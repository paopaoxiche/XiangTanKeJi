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
    
    [self setGradientColor];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [self.navigationController setNavigationBarHidden:YES animated:YES];
}

- (void)backToSuperView {
    [self dismissViewControllerAnimated:NO completion:nil];
}

/**
 *  设置渐变背景
 */
- (void)setGradientColor {
    CAGradientLayer *layer = [CAGradientLayer layer];
    layer.startPoint = CGPointMake(0, 0);   // (0,0)代表左上角，(0,1)代表左下角
    layer.endPoint = CGPointMake(0, 1);     // (0,0)(0,1)结合起来就是竖直方向渐变
    layer.colors = @[(id)[UIColor rgbByHexStr:@"5b779c"].CGColor, (id)[UIColor rgbByHexStr:@"cbbd9d"].CGColor];
    layer.frame = self.view.layer.bounds;
    [self.view.layer insertSublayer:layer atIndex:0];
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
