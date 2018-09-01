//
//  EditTradeStateViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "EditTradeStateViewController.h"
#import "TradeStateCell.h"

@interface EditTradeStateViewController ()

@end

@implementation EditTradeStateViewController

- (void)viewDidLoad {
    [super viewDidLoad];
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 3;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    TradeStateCell *cell = [tableView dequeueReusableCellWithIdentifier:@"TradeStateIdentifier" forIndexPath:indexPath];
    cell.imgName = indexPath.row == 0 ? @"SingleSelection_Selected" : @"SingleSelection_Normal";
    cell.tradeState = indexPath.row == 0 ? @"营业" : (indexPath.row == 1 ? @"歇业" : @"停业");
    
    return cell;
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section {
    return @"当前洗车场营业状态";
}

#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
}

@end
