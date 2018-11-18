//
//  EditTradeStateViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "EditTradeStateViewController.h"
#import "TradeStateCell.h"
#import "UIColor+Category.h"
#import "NetworkTools.h"
#import "UserManager.h"
#import "CarWashInfoModel.h"
#import "UIApplication+HUD.h"

@interface EditTradeStateViewController () <TradeStateCellDelegate>

@property (nonatomic, strong) NSIndexPath *indexPath;

@end

@implementation EditTradeStateViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.indexPath = [NSIndexPath indexPathForRow:[self selectedIndex] inSection:0];
    self.tableView.backgroundColor = [UIColor rgbWithRed:244 green:244 blue:244];
    self.tableView.tableFooterView = [[UIView alloc] init];
    self.tableView.bounces = NO;
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithTitle:@"确定" style:UIBarButtonItemStylePlain target:self action:@selector(updateTradeState)];
}

- (void)updateTradeState {
    TradeState state = [self tradeState];
    if (state == [UserManager sharedInstance].carWashInfo.tradeState) {
        [self messageBox:@"营业状态未改变"];
        return;
    }
    
    [UIApplication showBusyHUD];
    NSString *status = [NSString stringWithFormat:@"%li", state];
    [[NetworkTools sharedInstance] updateTradeState:[UserManager sharedInstance].carWashInfo.washID status:status success:^(NSDictionary *response, BOOL isSuccess) {
        [UIApplication stopBusyHUD];
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200) {
            [self messageBox:@"营业状态更新成功" handle:^{
                [UserManager sharedInstance].carWashInfo.tradeState = state;
                [self.navigationController popViewControllerAnimated:YES];
            }];
        } else {
            [self messageBox:@"营业状态更新失败"];
        }
    } failed:^(NSError *error) {
        [UIApplication stopBusyHUD];
        [self messageBox:@"营业状态更新失败"];
    }];
}

- (NSInteger)selectedIndex {
    TradeState state = [UserManager sharedInstance].carWashInfo.tradeState;
    NSInteger index = 0;
    switch (state) {
        case TradeStateClosed:
            index = 2;
            break;
        case TradeStateRest:
            index = 1;
            break;
        default:
            index = 0;
            break;
    }
    
    return index;
}

- (TradeState)tradeState {
    TradeState state;
    switch (_indexPath.row) {
        case 0:
            state = TradeStateOperate;
            break;
        case 1:
            state = TradeStateRest;
            break;
        default:
            state = TradeStateClosed;
            break;
    }
    
    return state;
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 3;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    TradeStateCell *cell = [tableView dequeueReusableCellWithIdentifier:@"TradeStateIdentifier" forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    cell.imgName = indexPath.row == [self selectedIndex] ? @"SingleSelection_Selected" : @"SingleSelection_Normal";
    cell.tradeState = indexPath.row == 0 ? @"营业" : (indexPath.row == 1 ? @"歇业" : @"停业");
    cell.delegate = self;
    
    return cell;
}

- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    UIView *headerView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 200, 40)];
    UILabel *headerLabel = [[UILabel alloc] initWithFrame:CGRectMake(15, 10, 200, 20)];
    headerLabel.backgroundColor = [UIColor clearColor];
    headerLabel.font = [UIFont systemFontOfSize:15];
    headerLabel.textColor = [UIColor rgbWithRed:153 green:153 blue:153];
    headerLabel.text = @"当前洗车场营业状态";
    [headerView addSubview:headerLabel];
    
    return headerView;
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 40;
}

#pragma mark - TradeStateCellDelegate

- (void)selectedTradeStateCell:(TradeStateCell *)cell {
    NSIndexPath *selectedIndexPath = [self.tableView indexPathForCell:cell];
    if (selectedIndexPath.row != _indexPath.row) {
        cell.imgName = @"SingleSelection_Selected";
        
        TradeStateCell *oldCell = [self.tableView cellForRowAtIndexPath:_indexPath];
        oldCell.imgName = @"SingleSelection_Normal";
        
        _indexPath = selectedIndexPath;
    }
}

@end
