//
//  CarWashListTableViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/13.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CarWashListTableViewController.h"
#import "CarWashListCell.h"
#import "HomeModel.h"
#import "CarWashInfoViewController.h"
#import "CarWashServiceViewController.h"
#import "GlobalMethods.h"

@interface CarWashListTableViewController () <CarWashListCellDelegate>

@end

@implementation CarWashListTableViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tableView.rowHeight = 100;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)setDataSource:(NSArray *)dataSource {
    _dataSource = dataSource;
    [self.tableView reloadData];
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return _dataSource.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    NearbyWashListModel *model = _dataSource[indexPath.section];
    CarWashListCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CarWashListIdentifier" forIndexPath:indexPath];
    cell.delegate = self;
    cell.avatarUrl = model.avatarUrl;
    cell.name = model.carWashName;
    cell.address = model.address;
    cell.price = model.price;
    cell.distance = model.distance;
    
    return cell;
}

#pragma mark - CarWashListCellDelegate

- (void)titleViewCarWashListCell:(CarWashListCell *)cell {
    NSIndexPath *indexPath = [self.tableView indexPathForCell:cell];
    NearbyWashListModel *model = _dataSource[indexPath.section];
    CarWashInfoViewController *vc = (CarWashInfoViewController *)[GlobalMethods viewControllerWithBuddleName:@"CarWash" vcIdentifier:@"CarWashInfoVC"];
    vc.washID = model.washID;
    [self.navigationController pushViewController:vc animated:YES];
}

- (void)detailViewCarWashListCell:(CarWashListCell *)cell {
    NSIndexPath *indexPath = [self.tableView indexPathForCell:cell];
    NearbyWashListModel *model = _dataSource[indexPath.section];
    CarWashServiceViewController *vc = (CarWashServiceViewController *)[GlobalMethods viewControllerWithBuddleName:@"CarWash" vcIdentifier:@"CarWashServiceVC"];
    vc.washID = model.washID;
    [self.navigationController pushViewController:vc animated:YES];
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 0.0001;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    return 11.9999;
}

@end
