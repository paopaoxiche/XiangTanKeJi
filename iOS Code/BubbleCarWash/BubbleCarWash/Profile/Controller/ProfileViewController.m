//
//  ProfileViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/6/25.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <SDWebImage/UIImageView+WebCache.h>
#import "ProfileViewController.h"
#import "ProfileModel.h"
#import "ProfileCell.h"
#import "IntegralConvertViewController.h"
#import "CouponViewController.h"
#import "UserManager.h"
#import "UserInfoModel.h"
#import "CarWashInfoModel.h"
#import "GlobalMethods.h"
#include "NetworkTools.h"

@interface ProfileViewController () <UITableViewDataSource, UITableViewDelegate>

@property (weak, nonatomic) IBOutlet UIView *headerView;
@property (weak, nonatomic) IBOutlet UIImageView *avatar;
@property (weak, nonatomic) IBOutlet UILabel *userName;
@property (weak, nonatomic) IBOutlet UILabel *telephoneNumber;
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UIImageView *headerPhoneImgView;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *phoneImgLeadingConstraint;
@property (copy, nonatomic) NSArray <NSArray *>*dataSource;
@property (assign, nonatomic) UserType userType;
@property (strong, nonatomic) UserInfoModel *userInfo;

@end

@implementation ProfileViewController

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.title = @"个人";
    
    _avatar.layer.borderColor = [UIColor whiteColor].CGColor;

    UITapGestureRecognizer *singleGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onHeaderViewClicked)];
    [_headerView addGestureRecognizer:singleGesture];
    
    _tableView.tableFooterView = [[UIView alloc] init];
    [_tableView registerNib:[UINib nibWithNibName:@"ProfileCell" bundle:[NSBundle mainBundle]] forCellReuseIdentifier:@"ProfileCellIdentifier"];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(updateUserInfo:) name:@"UpdateUserInfo" object:nil];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [self.navigationController.navigationBar setBackgroundImage:[[UIImage alloc] init]
                                                  forBarMetrics:UIBarMetricsDefault];
    [self.navigationController.navigationBar setShadowImage:[[UIImage alloc] init]];
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName:[UIColor whiteColor]}];
    
    [[UserManager sharedInstance] obtainUserInfo];
    [self updateUserInfo:nil];
}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];

    [self.navigationController.navigationBar setBackgroundImage:nil
                                                  forBarMetrics:UIBarMetricsDefault];
    [self.navigationController.navigationBar setShadowImage:nil];
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName:[UIColor blackColor]}];
}

- (void)updateUserInfo:(NSNotification *)notification {
    BOOL isLogin = [UserManager sharedInstance].isLogin;
    _userInfo = [UserManager sharedInstance].userInfo;
    _userType = isLogin ? _userInfo.type : UserTypeOwner;
    _userName.text = isLogin ? _userInfo.nickName : @"立即登录";
    _telephoneNumber.text = isLogin ? _userInfo.phoneNumber : @"登录后可使用更多功能";
    _headerPhoneImgView.hidden = isLogin ? NO : YES;
    _phoneImgLeadingConstraint.constant = isLogin ? 20 : 5;
    if (isLogin) {
        [_avatar sd_setImageWithURL:[NSURL URLWithString:_userInfo.avatarUrl]
                   placeholderImage:[UIImage imageNamed:_userInfo.type == UserTypeOwner ? @"OwnerAvatar" : @"CarWashAvatar"]];
    } else {
        _avatar.image = [UIImage imageNamed:@"OwnerAvatar"];
    }
    
    [self.tableView reloadData];
}

- (void)onHeaderViewClicked {
    if ([UserManager sharedInstance].isLogin) {
        UIViewController *personalInfoVC = [GlobalMethods viewControllerWithBuddleName:@"PersonalInfo" vcIdentifier:@"PersonalInfoVC"];
        personalInfoVC.hidesBottomBarWhenPushed = YES;
        [self.navigationController pushViewController:personalInfoVC animated:YES];
    } else {
        UIViewController *loginVC = [GlobalMethods viewControllerWithBuddleName:@"Login" vcIdentifier:@"LoginVC"];
        [self presentViewController:loginVC animated:YES completion:nil];
    }
}

#pragma mark - UITableViewDatasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return self.dataSource.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    NSArray *sectionData = self.dataSource[section];
    return sectionData.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    BOOL isOwner = _userType == UserTypeOwner;
    ProfileCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ProfileCellIdentifier"];
    cell.upgradeView.hidden = YES;

    ProfileItem *item = self.dataSource[indexPath.section][indexPath.row];
    cell.cusTextLabel.text = item.text;
    cell.imgView.image = [UIImage imageNamed:item.imageName];
    
    if (indexPath.section == 0 && _userType == UserTypeCarWash) {
        CarWashInfoModel *washInfo = [UserManager sharedInstance].carWashInfo;
        cell.cusDetailTextLabel.hidden = NO;
        cell.cusDetailTextLabel.text = indexPath.row == 0 ? [NSString stringWithFormat:@"%li", washInfo.honor] : [NSString stringWithFormat:@"%li次", washInfo.washCount];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
    } else {
        cell.cusDetailTextLabel.hidden = YES;
        cell.selectionStyle = UITableViewCellSelectionStyleGray;
    }
    
    if (!isOwner && indexPath.section == 0) {
        cell.accessoryArrow.hidden = YES;
    } else {
        cell.accessoryArrow.hidden = NO;
    }
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    ProfileItem *item = self.dataSource[indexPath.section][indexPath.row];
    if (![item.nextPageID isEqualToString:@""] && item.nextPageID) {
        UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Profile" bundle:[NSBundle mainBundle]];
        UIViewController *nextPageVC = [storyboard instantiateViewControllerWithIdentifier:item.nextPageID];
        nextPageVC.hidesBottomBarWhenPushed = YES;
        [self.navigationController pushViewController:nextPageVC animated:YES];
    }
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 52;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 19.9;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    if (section == _dataSource.count - 1) {
        return 20;
    }
    
    return 0.1;
}

- (NSArray *)dataSource {
    _dataSource = [ProfileModel profileDataSourceWithUserType:_userType];
    
    return _dataSource;
}

@end
