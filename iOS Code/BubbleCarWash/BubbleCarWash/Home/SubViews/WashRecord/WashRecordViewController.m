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
//#import "ExpensesRecordCell.h"
#import "RecentWashRecordCell.h"

@interface WashRecordViewController () <UITableViewDataSource, UITableViewDelegate>

@property (nonatomic, weak) IBOutlet UITableView *tableView;
@property (nonatomic, copy) NSArray *dataSource;

@end

@implementation WashRecordViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.title = @"洗车记录";
    self.tableView.tableFooterView = [[UIView alloc] init];
    self.tableView.rowHeight = 68;
    [self.tableView registerNib:[UINib nibWithNibName:@"RecentWashRecordCell" bundle:[NSBundle mainBundle]] forCellReuseIdentifier:@"RecentWashRecordIdentifier"];
    [ExpensesRecordListModel loadRecentCarWashList:4 result:^(NSArray *result) {
        self.dataSource = [result copy];
        if (result.count > 0) {
            [self.tableView reloadData];
        }
    }];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [self.navigationController setNavigationBarHidden:NO animated:YES];
}

#pragma mark - UITableViewDatasource

//- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
//    return _dataSource.count;
//}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return _dataSource.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    if (_dataSource.count <= indexPath.row) {
        return nil;
    }
    
    CarWashRecordModel *model = _dataSource[indexPath.row];
    RecentWashRecordCell *cell = [tableView dequeueReusableCellWithIdentifier:@"RecentWashRecordIdentifier"];
    cell.imageName = model.avatarUrl;
    cell.name = model.nickName;
    cell.type = model.carType;
    cell.price = model.price;
    cell.time = model.time;
    
    return cell;
//    ExpensesRecordModel *model = [_dataSource objectAtIndex:indexPath.section];
//
//    if (indexPath.row == (model.commodities.count + 1)) {
//        WashRecordTotalCell *cell = [tableView dequeueReusableCellWithIdentifier:@"WashRecordTotalCell" forIndexPath:indexPath];
//        cell.totalPrice = model.totalPrice;
//
//        return cell;
//    }
//
//    if (indexPath.row == 0) {
//        WashRecordContentCell *cell = [tableView dequeueReusableCellWithIdentifier:@"WashRecordContentCell" forIndexPath:indexPath];
//        cell.imageUrl = @"";
////        cell.ownerName =
//        cell.time = model.time;
//        cell.proName = model.serviceName;
//        cell.couponType = model.coupon ? model.coupon.couponType : @"";
//        cell.proPrice =  model.coupon ? model.servicePrice : @"";
//        cell.isShowCoupon = YES;
//
//        return cell;
//    } else {
//        ExpensesRecordContentCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ExpensesRecordContentCell" forIndexPath:indexPath];
//        CommodityExpensesModel *commodity = model.commodities[indexPath.row - 1];
//        cell.imageUrl = commodity.imageUrl;
//        cell.name = commodity.name;
//        cell.isShowCoupon = NO;
//
//        return cell;
//    }
}

//#pragma mark - UITableViewDelegate
//
//- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
//    if (indexPath.row == 0) {
//        return 40;
//    }
//
//    if ((indexPath.section == 0 && indexPath.row == 4) || (indexPath.section == 1 && indexPath.row == 2)) {
//        return 96;
//    }
//
//    return 92;
//}
//
- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 12;
}
//
//- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
//    return 11.9999;
//}

@end
