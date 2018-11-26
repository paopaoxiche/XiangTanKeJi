//
//  IncomeListViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "IncomeListViewController.h"
#import "IncomeListCell.h"
#import "NetworkTools.h"
#import "IncomeListModel.h"
#import "UserManager.h"
#import "CarWashInfoModel.h"
#import "GlobalMethods.h"
#import "UIApplication+HUD.h"
#import "UIColor+Category.h"

@interface IncomeListViewController () <UITableViewDataSource, UITableViewDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UILabel *yearLabel;
@property (weak, nonatomic) IBOutlet UILabel *monthLabel;
@property (weak, nonatomic) IBOutlet UILabel *totalIncomeLabel;
@property (copy, nonatomic) NSArray *dataSource;

@end

@implementation IncomeListViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.title = @"收入列表";
    _tableView.rowHeight = 52;
    _tableView.tableFooterView = [[UIView alloc] init];
    _tableView.bounces = NO;
    [UIApplication showBusyHUD];
    [self.navigationController.navigationBar setTintColor:[UIColor whiteColor]];
    self.navigationController.navigationBar.barTintColor = [UIColor rgbWithRed:248 green:157 blue:14];
    self.navigationController.navigationBar.subviews[0].subviews[0].hidden = YES;
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName : [UIColor whiteColor]}];
    NSInteger washID = [UserManager sharedInstance].carWashInfo.washID;
    NSInteger month = [GlobalMethods currentMonth];
    [NetworkTools obtainIncomeList:washID month:month success:^(NSDictionary *response, BOOL isSuccess) {
        [UIApplication stopBusyHUD];
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200) {
            NSDictionary *dataArr = [response objectForKey:@"data"];
            NSMutableArray *recordList = [NSMutableArray arrayWithCapacity:dataArr.count];
            for (NSDictionary *dic in dataArr) {
                IncomeModel *model = [[IncomeModel alloc] initWithDic:dic];
                [recordList addObject:model];
            }
            
            self.dataSource = recordList;
            if (recordList.count > 0) {
                [self.tableView reloadData];
            } else {
                //   提示无数据
            }
            [self setupHeaderView];
        } else {
            [self messageBox:@"获取收入列表失败"];
            [self setupHeaderView];
        }
    } failed:^(NSError *error) {
        [UIApplication stopBusyHUD];
        [self messageBox:@"获取收入列表失败"];
        [self setupHeaderView];
    }];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:NO animated:YES];
}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    
    [self.navigationController.navigationBar setTintColor:[UIColor blackColor]];
    self.navigationController.navigationBar.barTintColor = [UIColor whiteColor];
    self.navigationController.navigationBar.subviews[0].subviews[0].hidden = NO;
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName : [UIColor blackColor]}];
}

- (void)setupHeaderView {
    self.yearLabel.text = [NSString stringWithFormat:@"%li年", [GlobalMethods currentYear]];
    self.monthLabel.text = [NSString stringWithFormat:@"%li", [GlobalMethods currentMonth]];
    
    CGFloat total = 0;
    for (IncomeModel *model in _dataSource) {
        total += model.totalMoney;
    }
    
    self.totalIncomeLabel.text = [NSString stringWithFormat:@"¥%.2f", total];
}

#pragma mark - UITableViewDatasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return _dataSource.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    IncomeModel *model = _dataSource[section];
    return model.dayIncomeList.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    IncomeModel *model = _dataSource[indexPath.section];
    if (indexPath.row == 0) {
        IncomeListTitleCell *cell = [tableView dequeueReusableCellWithIdentifier:@"IncomeListTitleCell" forIndexPath:indexPath];
        [cell setDate:model.date dayIncome:model.totalMoney];
        
        return cell;
    }
    
    IncomeRecordModel *record = model.dayIncomeList[indexPath.row];
    IncomeListContentCell *cell = [tableView dequeueReusableCellWithIdentifier:@"IncomeListContentCell" forIndexPath:indexPath];
    [cell setCarType:record.carType money:record.money];
    
    return cell;
}

@end
