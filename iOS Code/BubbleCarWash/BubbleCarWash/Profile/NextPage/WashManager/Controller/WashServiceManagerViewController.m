//
//  WashServiceManagerViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "WashServiceManagerViewController.h"
#import "CreateServiceViewController.h"
#import "WashManagerCell.h"
#import "NetworkTools.h"
#import "CarWashServiceModel.h"
#import "CarWashInfoModel.h"
#import "UserManager.h"
#import "UIColor+Category.h"
#import "GlobalMethods.h"
#import "UIApplication+HUD.h"

@interface WashServiceManagerViewController () <UITableViewDataSource, UITableViewDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (nonatomic, strong) NSMutableArray *dataSource;

@end

@implementation WashServiceManagerViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.dataSource = [[NSMutableArray alloc] initWithCapacity:0];
    
    self.title = @"洗车服务管理";
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:@selector(addNewWashService)];
    
    _tableView.rowHeight = 68;
    _tableView.tableFooterView = [[UIView alloc] init];
    [_tableView registerNib:[UINib nibWithNibName:@"WashManagerCell" bundle:[NSBundle mainBundle]] forCellReuseIdentifier:@"WashManagerCell"];
    
    [self loadCarWashService];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:NO animated:YES];
}

- (void)loadCarWashService {
    [UIApplication showBusyHUD];
    [[NetworkTools sharedInstance] obtainCarWashServiceList:[UserManager sharedInstance].carWashInfo.washID success:^(NSDictionary *response, BOOL isSuccess) {
        [UIApplication stopBusyHUD];
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSDictionary *dataArr = [response objectForKey:@"data"];
            NSMutableArray *list = [NSMutableArray arrayWithCapacity:dataArr.count];
            for (NSDictionary *dic in dataArr) {
                ServiceModel *model = [[ServiceModel alloc] initWithDic:dic];
                [list addObject:model];
            }
            
            self.dataSource = list;
            if (list.count > 0) {
                [self.tableView reloadData];
            } else {
                // 提示无服务
            }
        } else {
            [self messageBox:@"获取服务列表失败"];
        }
    } failed:^(NSError *error) {
        [UIApplication stopBusyHUD];
        [self messageBox:@"获取服务列表失败"];
    }];
}

- (void)addNewWashService {
    CreateServiceViewController *createServiceVC = (CreateServiceViewController *)[GlobalMethods viewControllerWithBuddleName:@"WashManager" vcIdentifier:@"CreateServiceVC"];
    [self.navigationController pushViewController:createServiceVC animated:YES];
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return _dataSource.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    NSString *avatarUrl = [UserManager sharedInstance].carWashInfo.avatarUrl;
    ServiceModel *model = _dataSource[indexPath.row];
    WashManagerCell *cell = [tableView dequeueReusableCellWithIdentifier:@"WashManagerCell" forIndexPath:indexPath];
    cell.imageUrl = avatarUrl;
    cell.currentPrice = model.price;
    cell.name = model.carWashName;
    cell.desc = model.desc;
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
        ServiceModel *model = self.dataSource[indexPath.row];
        
        [UIApplication showBusyHUD];
        [[NetworkTools sharedInstance] deleteWashService:[UserManager sharedInstance].carWashInfo.washID serviceID:model.dataID success:^(NSDictionary *response, BOOL isSuccess) {
            [UIApplication stopBusyHUD];
            NSInteger code = [response[@"code"] integerValue];
            if (code == 200) {
                [self.dataSource removeObjectAtIndex:indexPath.row];
                [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationAutomatic];
                [self messageBox:@"删除服务成功"];
            } else {
                [self messageBox:@"删除服务失败，请重试"];
            }
        } failure:^(NSError *error) {
            [UIApplication stopBusyHUD];
            [self messageBox:@"删除服务失败，请重试"];
        }];
    }];
    deleteRowAction.backgroundColor = [UIColor rgbWithRed:255 green:105 blue:61];
    
    UITableViewRowAction *editRowAction = [UITableViewRowAction rowActionWithStyle:UITableViewRowActionStyleDestructive title:@"编辑" handler:^(UITableViewRowAction * _Nonnull action, NSIndexPath * _Nonnull indexPath) {
        ServiceModel *model = self.dataSource[indexPath.row];
        CreateServiceViewController *createServiceVC = (CreateServiceViewController *)[GlobalMethods viewControllerWithBuddleName:@"WashManager" vcIdentifier:@"CreateServiceVC"];
        createServiceVC.model = model;
        [self.navigationController pushViewController:createServiceVC animated:YES];
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
