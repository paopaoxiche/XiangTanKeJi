//
//  CommodityManagerViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CommodityManagerViewController.h"
#import "CreateProductViewController.h"
#import "WashManagerCell.h"
#import "HomeModel.h"
#import "NetworkTools.h"
#import "UserManager.h"
#include "CarWashInfoModel.h"
#import "UIColor+Category.h"
#import "GlobalMethods.h"
#import "UIApplication+HUD.h"

@interface CommodityManagerViewController () <UITableViewDataSource, UITableViewDelegate>

@property (nonatomic, weak) IBOutlet UITableView *tableView;
@property (nonatomic, strong) NSMutableArray *dataSource;

@end

@implementation CommodityManagerViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.title = @"商品管理";
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:@selector(addNewCommodity)];
    
    self.dataSource = [[NSMutableArray alloc] initWithCapacity:0];
    
    _tableView.rowHeight = 68;
    _tableView.tableFooterView = [[UIView alloc] init];
    [_tableView registerNib:[UINib nibWithNibName:@"WashManagerCell" bundle:[NSBundle mainBundle]] forCellReuseIdentifier:@"WashManagerCell"];
    
    [self loadCarWashCommodityList];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:NO animated:YES];
}

- (void)loadCarWashCommodityList {
    [UIApplication showBusyHUD];
    [NetworkTools obtainCarWashCommodityList:[UserManager sharedInstance].carWashInfo.washID success:^(NSDictionary *response, BOOL isSuccess) {
        [UIApplication stopBusyHUD];
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSDictionary *dataArr = [response objectForKey:@"data"];
            NSMutableArray *list = [NSMutableArray arrayWithCapacity:dataArr.count];
            for (NSDictionary *dic in dataArr) {
                RecommendCommodityModel *model = [[RecommendCommodityModel alloc] initWithDic:dic];
                [list addObject:model];
            }
            
            self.dataSource = list;
            if (list.count > 0) {
                [self.tableView reloadData];
            } else {
                // 提示无商品
            }
        } else {
            [self messageBox:@"获取商品列表失败"];
        }
    } failed:^(NSError *error) {
        [UIApplication stopBusyHUD];
        [self messageBox:@"获取商品列表失败"];
    }];
}

- (void)addNewCommodity {
    CreateProductViewController *createProductVC = (CreateProductViewController *)[GlobalMethods viewControllerWithBuddleName:@"WashManager" vcIdentifier:@"CreateProductVC"];
    [self.navigationController pushViewController:createProductVC animated:YES];
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.dataSource.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    RecommendCommodityModel *model = _dataSource[indexPath.row];
    WashManagerCell *cell = [tableView dequeueReusableCellWithIdentifier:@"WashManagerCell" forIndexPath:indexPath];
    cell.imageUrl = model.imageUrl;
    cell.name = model.commodityName;
    cell.desc = model.describe;
    cell.currentPrice = model.currentPrice;
    cell.originalPrice = model.originPrice;
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    
    return cell;
}

- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {
    return YES;
}

#pragma mark - UITableViewDelegate

- (nullable NSArray<UITableViewRowAction *> *)tableView:(UITableView *)tableView editActionsForRowAtIndexPath:(NSIndexPath *)indexPath {
    NSMutableArray *buttons = [[NSMutableArray alloc] initWithCapacity:0];
    
    UITableViewRowAction *deleteRowAction = [UITableViewRowAction rowActionWithStyle:UITableViewRowActionStyleDestructive title:@"删除" handler:^(UITableViewRowAction * _Nonnull action, NSIndexPath * _Nonnull indexPath) {
        RecommendCommodityModel *model = self.dataSource[indexPath.row];
        
        [UIApplication showBusyHUD];
        [NetworkTools deleteCommodity:model.dataID success:^(NSDictionary *response, BOOL isSuccess) {
            [UIApplication stopBusyHUD];
            NSInteger code = [response[@"code"] integerValue];
            if (code == 200) {
                [self.dataSource removeObjectAtIndex:indexPath.row];
                [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationAutomatic];
                [self messageBox:@"删除商品成功"];
            } else {
                [self messageBox:@"删除商品失败，请重试"];
            }
        } failure:^(NSError *error) {
            [UIApplication stopBusyHUD];
            [self messageBox:@"删除商品失败，请重试"];
        }];
    }];
    deleteRowAction.backgroundColor = [UIColor rgbWithRed:255 green:105 blue:61];
    
    UITableViewRowAction *editRowAction = [UITableViewRowAction rowActionWithStyle:UITableViewRowActionStyleDestructive title:@"编辑" handler:^(UITableViewRowAction * _Nonnull action, NSIndexPath * _Nonnull indexPath) {
        RecommendCommodityModel *model = self.dataSource[indexPath.row];
        CreateProductViewController *createProductVC = (CreateProductViewController *)[GlobalMethods viewControllerWithBuddleName:@"WashManager" vcIdentifier:@"CreateProductVC"];
        createProductVC.model = model;
        [self.navigationController pushViewController:createProductVC animated:YES];
    }];
    editRowAction.backgroundColor = [UIColor rgbWithRed:250 green:196 blue:20];
    
    [buttons addObject:deleteRowAction];
    [buttons addObject:editRowAction];
    
    return buttons;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 12;
}

@end
