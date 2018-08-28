//
//  ExpensesRecordViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "ExpensesRecordViewController.h"
#import "ExpensesRecordCell.h"

@interface ExpensesRecordViewController () <UITableViewDataSource, UITableViewDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;

@end

@implementation ExpensesRecordViewController

- (void)viewDidLoad {
    [super viewDidLoad];
}

#pragma mark - UITableViewDatasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 2;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return section == 0 ? 5 : 3;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    if (indexPath.row == 0) {
        ExpensesRecordTitleCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ExpensesRecordTitleCell" forIndexPath:indexPath];
        
        return cell;
    }
    
    if ((indexPath.section == 0 && indexPath.row == 4) || (indexPath.section == 1 && indexPath.row == 2)) {
        TotalConsumptionCell *cell = [tableView dequeueReusableCellWithIdentifier:@"TotalConsumptionCell" forIndexPath:indexPath];
        
        return cell;
    }
    
    ExpensesRecordContentCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ExpensesRecordContentCell" forIndexPath:indexPath];
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
}

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
