//
//  IntegralConvertViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "IntegralConvertViewController.h"
#import "IntegralConvertCell.h"
#import "CouponListModel.h"
#import "UIApplication+HUD.h"
#import "UserManager.h"
#import "UserInfoModel.h"
#import "FunctionMacro.h"
#import "GlobalMethods.h"

@interface IntegralConvertViewController () <UITableViewDataSource, UITableViewDelegate, IntegralConvertCellDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UILabel *currentIntegralLabel;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *headerHeightConstraint;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *integralTopConstraint;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *tableViewTopConstraint;
@property (nonatomic, copy) NSArray *redeemableCouponList;

@end

@implementation IntegralConvertViewController

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.title = @"积分兑换";
    [self.navigationController.navigationBar setBackgroundImage:[[UIImage alloc] init] forBarMetrics:UIBarMetricsDefault];
    [self.navigationController.navigationBar setShadowImage:[[UIImage alloc] init]];
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName:[UIColor whiteColor]}];
    [self.navigationController.navigationBar setTintColor:[UIColor whiteColor]];
    self.navigationController.navigationBar.barTintColor = [UIColor clearColor];
    self.navigationController.navigationBar.backgroundColor= [UIColor clearColor];
    
    // 保证视图从界面顶部开始布局
    self.extendedLayoutIncludesOpaqueBars = NO;
    self.modalPresentationCapturesStatusBarAppearance = NO;
    self.automaticallyAdjustsScrollViewInsets = NO;
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(updateScore) name:@"UpdateUserInfo" object:nil];
    
    [UserManager sharedInstance].isUpdateUserInfo = YES;
    [[UserManager sharedInstance] obtainUserInfo];
    self.currentIntegralLabel.text = [NSString stringWithFormat:@"%li", [UserManager sharedInstance].userInfo.score];
    
    [CouponListModel loadRedeemableCouponList:^(NSArray *result) {
        self.redeemableCouponList = [result copy];
        [self.tableView reloadData];
    }];
    
    if ([GlobalMethods isiPhoneX]) {
        _headerHeightConstraint.constant = 200;
        _integralTopConstraint.constant = 86;
        _tableViewTopConstraint.constant = 170;
    }
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:NO animated:YES];
}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    
    // 不透明
    [self.navigationController.navigationBar setBackgroundImage:nil forBarMetrics:0];
    [self.navigationController.navigationBar setShadowImage:nil];
    [self.navigationController.navigationBar setTintColor:[UIColor blackColor]];
    self.navigationController.navigationBar.barTintColor = [UIColor whiteColor];
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName : [UIColor blackColor]}];
    self.navigationController.navigationBar.backgroundColor= [UIColor whiteColor];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)updateScore {
    self.currentIntegralLabel.text = [NSString stringWithFormat:@"%li", [UserManager sharedInstance].userInfo.score];
}

#pragma mark - UITableViewDatasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return _redeemableCouponList.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    RedeemableCouponModel *model = [_redeemableCouponList objectAtIndex:indexPath.section];
    IntegralConvertCell *cell = [tableView dequeueReusableCellWithIdentifier:@"IntegralIdentifier"];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    cell.noteSum = model.price;
    cell.couponType = model.couponType;
    cell.couponDesc = model.name;
    cell.validityPeroid = model.validityPeroid;
    cell.integral = model.integral;
    cell.index = indexPath.section;
    cell.delegate = self;
    
    return cell;
}

#pragma mark - IntegralConvertCellDelegate

- (void)onClickedConvertButtonnAtIndex:(NSInteger)index {
    RedeemableCouponModel *model = [_redeemableCouponList objectAtIndex:index];
    [UIApplication showBusyHUD];
    [model convertIntegralToCoupon:^(NSInteger code) {
        [UIApplication stopBusyHUD];
        if (code == 200) {
            [self messageBox:@"积分兑换成功" handle:^{
                [self.navigationController popViewControllerAnimated:YES];
            }];
        } else {
            [self messageBox:@"兑换积分失败"];
        }
    }];
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 92;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 0.000001;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    return 15.999999;
}

@end
