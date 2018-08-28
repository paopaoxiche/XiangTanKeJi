//
//  IncomeListViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "IncomeListViewController.h"
#import "IncomeListCell.h"

@interface IncomeListViewController () <UITableViewDataSource, UITableViewDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UILabel *yearLabel;
@property (weak, nonatomic) IBOutlet UILabel *monthLabel;
@property (weak, nonatomic) IBOutlet UILabel *totalIncomeLabel;
@property (copy, nonatomic) NSArray *incomeData;

@end

@implementation IncomeListViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _tableView.rowHeight = 52;
}

#pragma mark - UITableViewDatasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 3;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 4;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    if (indexPath.row == 0) {
        IncomeListTitleCell *cell = [tableView dequeueReusableCellWithIdentifier:@"IncomeListTitleCell" forIndexPath:indexPath];
        
        return cell;
    }
    
    IncomeListContentCell *cell = [tableView dequeueReusableCellWithIdentifier:@"IncomeListContentCell" forIndexPath:indexPath];
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
}

@end
