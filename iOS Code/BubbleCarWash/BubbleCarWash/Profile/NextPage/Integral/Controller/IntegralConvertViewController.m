//
//  IntegralConvertViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "IntegralConvertViewController.h"
#import "IntegralConvertCell.h"
#import "CouponListModel.h"

@interface IntegralConvertViewController () <UITableViewDataSource, UITableViewDelegate, IntegralConvertCellDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UILabel *currentIntegralLabel;
@property (nonatomic, copy) NSArray *redeemableCouponList;

@end

@implementation IntegralConvertViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.title = @"积分兑换";
    [self.navigationController.navigationBar setBackgroundImage:[[UIImage alloc] init] forBarMetrics:UIBarMetricsDefault];
    [self.navigationController.navigationBar setShadowImage:[[UIImage alloc] init]];
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName:[UIColor whiteColor]}];
    
    // 保证视图从界面顶部开始布局
    self.extendedLayoutIncludesOpaqueBars = NO;
    self.modalPresentationCapturesStatusBarAppearance = NO;
    self.automaticallyAdjustsScrollViewInsets = NO;
    
    [CouponListModel loadRedeemableCouponList:^(NSArray *result) {
        self.redeemableCouponList = [result copy];
        [self.tableView reloadData];
    }];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - UITableViewDatasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return _redeemableCouponList.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    RedeemableCouponModel *model = [_redeemableCouponList objectAtIndex:indexPath.section];
    IntegralConvertCell *cell = [tableView dequeueReusableCellWithIdentifier:@"IntegralIdentifier"];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    cell.noteSum = model.price;
    cell.couponType = model.couponType;
    cell.couponDesc = model.name;
    cell.validityPeroid = model.validityPeroid;
    cell.integral = model.integral;
    cell.index = indexPath.row;
    cell.delegate = self;
    
    return cell;
}

#pragma mark - IntegralConvertCellDelegate

- (void)onClickedConvertButtonnAtIndex:(NSInteger)index {
    RedeemableCouponModel *model = [_redeemableCouponList objectAtIndex:index];
    [model convertIntegralToCoupon:^(NSInteger code) {
//        if (code == -1) {
//            <#statements#>
//        }
    }];
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 92;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 0.000001;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    return 15.999999;
}

@end
