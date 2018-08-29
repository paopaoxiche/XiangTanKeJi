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

@interface CommodityManagerViewController () <UITableViewDataSource, UITableViewDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;

@end

@implementation CommodityManagerViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.title = @"商品管理";
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:@selector(addNewCommodity)];
    
    _tableView.rowHeight = 68;
    _tableView.tableFooterView = [[UIView alloc] init];
    [_tableView registerNib:[UINib nibWithNibName:@"WashManagerCell" bundle:[NSBundle mainBundle]] forCellReuseIdentifier:@"WashManagerCell"];
}

- (void)addNewCommodity {
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"WashManager" bundle:[NSBundle mainBundle]];
    CreateProductViewController *createProductVC = [storyboard instantiateViewControllerWithIdentifier:@"CreateProductVC"];
    createProductVC.proType = ProductTypeCommodity;
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
