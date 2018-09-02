//
//  CouponViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CouponViewController.h"
#import "CouponCell.h"
#import "CouponListModel.h"

@interface CouponViewController () <UITableViewDataSource, UITableViewDelegate>

@property (nonatomic, weak) IBOutlet UITableView *tableView;
@property (nonatomic, copy) NSArray *myCouponList;

@end

@implementation CouponViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.title = @"优惠券";
    [CouponListModel loadMyCouponList:^(NSArray *result) {
        self.myCouponList = [result copy];
        [self.tableView reloadData];
    }];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - UITableViewDatasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return _myCouponList.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    MyCouponModel *model = [_myCouponList objectAtIndex:indexPath.section];
    CouponCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CouponCell"];
    cell.avatarUrl = model.avatarUrl;
    cell.couponDesc = model.title;
    cell.washName = model.washName;
    cell.validityPeroid = model.validityPeroid;
    cell.noteSum = model.price;
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 112;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 15.999999;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    return 0.000001;
}

@end
