//
//  WashServiceManagerViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "WashServiceManagerViewController.h"
#import "CreateProductViewController.h"
#import "WashManagerCell.h"

@interface WashServiceManagerViewController () <UITableViewDataSource, UITableViewDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;

@end

@implementation WashServiceManagerViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.title = @"洗车服务管理";
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:@selector(addNewWashService)];
    
    _tableView.rowHeight = 68;
    _tableView.tableFooterView = [[UIView alloc] init];
    [_tableView registerNib:[UINib nibWithNibName:@"WashManagerCell" bundle:[NSBundle mainBundle]] forCellReuseIdentifier:@"WashManagerCell"];
}

- (void)addNewWashService {
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"WashManager" bundle:[NSBundle mainBundle]];
    CreateProductViewController *createProductVC = [storyboard instantiateViewControllerWithIdentifier:@"CreateProductVC"];
    createProductVC.proType = ProductTypeCarWash;
    [self.navigationController pushViewController:createProductVC animated:YES];
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 3;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    WashManagerCell *cell = [tableView dequeueReusableCellWithIdentifier:@"WashManagerCell" forIndexPath:indexPath];
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 12;
}
@end
