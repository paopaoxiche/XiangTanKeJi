//
//  CarWashListTableViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/13.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CarWashListTableViewController.h"
#import "CarWashListCell.h"

@interface CarWashListTableViewController ()

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

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 2;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    CarWashListCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CarWashListIdentifier" forIndexPath:indexPath];
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 0.0001;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    return 11.9999;
}

@end
