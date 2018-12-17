//
//  ExpensesRecordViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "ExpensesRecordViewController.h"
#import "ExpensesRecordCell.h"
#import "ExpensesRecordListModel.h"
#import "DiscussViewController.h"
#import "GlobalMethods.h"
#import "UIApplication+HUD.h"

@interface ExpensesRecordViewController () <UITableViewDataSource, UITableViewDelegate, TotalConsumptionCellDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (nonatomic, copy) NSArray *recordList;

@end

@implementation ExpensesRecordViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.title = @"消费记录";
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName : [UIColor blackColor]}];
    [UIApplication showBusyHUD];
    [ExpensesRecordListModel loadExpensesRecordList:^(NSArray *result) {
        [UIApplication stopBusyHUD];
        self.recordList = [result copy];
        [self.tableView reloadData];
    }];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:NO animated:YES];
}

#pragma mark - UITableViewDatasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return _recordList.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    ExpensesRecordModel *model = [_recordList objectAtIndex:section];
    return model.commodities.count + 3; // title + service + totalPrice + commodities count
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    ExpensesRecordModel *model = [_recordList objectAtIndex:indexPath.section];
    if (indexPath.row == 0) {
        ExpensesRecordTitleCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ExpensesRecordTitleCell" forIndexPath:indexPath];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.avatarUrl = model.avatarUrl;
        cell.washName = model.washName;
        cell.time = model.time;
        
        return cell;
    }
    
    if (indexPath.row == (model.commodities.count + 2)) {
        TotalConsumptionCell *cell = [tableView dequeueReusableCellWithIdentifier:@"TotalConsumptionCell" forIndexPath:indexPath];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.totalPrice = model.totalPrice;
        cell.isEvaluation = model.isEvaluation == ExpensesRecordEvaluationStatusOn;
        cell.delegate = self;
        
        return cell;
    }
    
    ExpensesRecordContentCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ExpensesRecordContentCell" forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    if (indexPath.row == 1) {
        cell.imageUrl = @"";
        cell.name = model.serviceName;
        cell.price = model.servicePrice;
        cell.couponType = model.coupon ? model.coupon.couponType : @"";
        cell.couponPrice =  model.coupon ? model.coupon.price : @"";
        cell.isShowCoupon = model.coupon ? YES : NO;
    } else {
        CommodityExpensesModel *commodity = model.commodities[indexPath.row - 2];
        cell.imageUrl = commodity.imageUrl;
        cell.price = commodity.currentPrice;
        cell.name = commodity.name;
        cell.isShowCoupon = NO;
    }
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    if (indexPath.row == 0) {
        return 40;
    }
    
    ExpensesRecordModel *model = [_recordList objectAtIndex:indexPath.section];
    if (indexPath.row == (model.commodities.count + 2)) {
        return 96;
    }
    
    return 92;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    if (section == 0) {
        return 11.9999;
    }
    
    return 0.0001;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    return 11.9999;
}

#pragma mark - TotalConsumptionCellDelegate

- (void)onEvaluationButtonClicked:(TotalConsumptionCell *)cell {
    NSIndexPath *indexPath = [self.tableView indexPathForCell:cell];
    ExpensesRecordModel *model = [_recordList objectAtIndex:indexPath.section];
    if (model.isEvaluation == ExpensesRecordEvaluationStatusNOT) {
        DiscussViewController *discussVC = (DiscussViewController *)[GlobalMethods viewControllerWithBuddleName:@"Profile"
                                                                                                   vcIdentifier:@"DiscussVC"];
        discussVC.consumeID = model.recordID;
        [self.navigationController pushViewController:discussVC animated:YES];
    }
}

@end
