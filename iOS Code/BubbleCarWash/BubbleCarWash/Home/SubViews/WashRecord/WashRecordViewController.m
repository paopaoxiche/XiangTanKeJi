//
//  WashRecordViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/10/7.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "WashRecordViewController.h"
#import "ExpensesRecordListModel.h"
#import "WashRecordCell.h"
#import "ExpensesRecordCell.h"

@interface WashRecordViewController () <UITableViewDataSource, UITableViewDelegate>

@property (nonatomic, weak) IBOutlet UITableView *tableView;
@property (nonatomic, copy) NSArray *dataSource;

@end

@implementation WashRecordViewController

- (void)viewDidLoad {
    [super viewDidLoad];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [self.navigationController setNavigationBarHidden:NO animated:YES];
}

#pragma mark - UITableViewDatasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return _dataSource.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    ExpensesRecordModel *model = [_dataSource objectAtIndex:section];
    return model.commodities.count + 2; // service + totalPrice + commodities count
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    ExpensesRecordModel *model = [_dataSource objectAtIndex:indexPath.section];
    
    if (indexPath.row == (model.commodities.count + 1)) {
        WashRecordTotalCell *cell = [tableView dequeueReusableCellWithIdentifier:@"WashRecordTotalCell" forIndexPath:indexPath];
        cell.totalPrice = model.totalPrice;
        
        return cell;
    }
    
    if (indexPath.row == 0) {
        WashRecordContentCell *cell = [tableView dequeueReusableCellWithIdentifier:@"WashRecordContentCell" forIndexPath:indexPath];
        cell.imageUrl = @"";
//        cell.ownerName =
        cell.time = model.time;
        cell.proName = model.serviceName;
        cell.couponType = model.coupon ? model.coupon.couponType : @"";
        cell.proPrice =  model.coupon ? model.servicePrice : @"";
        cell.isShowCoupon = YES;
        
        return cell;
    } else {
        ExpensesRecordContentCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ExpensesRecordContentCell" forIndexPath:indexPath];
        CommodityExpensesModel *commodity = model.commodities[indexPath.row - 1];
        cell.imageUrl = commodity.imageUrl;
        cell.name = commodity.name;
        cell.isShowCoupon = NO;
        
        return cell;
    }
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    if (indexPath.row == 0) {
        return 40;
    }
    
    if ((indexPath.section == 0 && indexPath.row == 4) || (indexPath.section == 1 && indexPath.row == 2)) {
        return 96;
    }
    
    return 92;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 0.0001;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    return 11.9999;
}

@end
