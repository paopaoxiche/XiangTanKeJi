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
#import "NetworkTools.h"
#import "UIColor+Category.h"
#import "UIImage+CreateByColor.h"

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
    
    [_tableView registerNib:[UINib nibWithNibName:@"ProfileCell" bundle:[NSBundle mainBundle]] forCellReuseIdentifier:@"ProfileCellIdentifier"];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(updateUserInfo:) name:@"UpdateUserInfo" object:nil];
    
    // 从状态栏开始布局
    self.edgesForExtendedLayout = UIRectEdgeNone;
    self.extendedLayoutIncludesOpaqueBars = NO;
    self.modalPresentationCapturesStatusBarAppearance = NO;
    self.automaticallyAdjustsScrollViewInsets = NO;
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:YES animated:YES];
    
    [[UserManager sharedInstance] obtainUserInfo];
    [self updateUserInfo:nil];
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
        [self.navigationController pushViewController:personalInfoVC animated:NO];
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

- (UIView*)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section {
    if (section == (self.dataSource.count - 1) && [UserManager sharedInstance].isLogin) {
        UIView *bottomView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, tableView.bounds.size.width, 240)];
        UIButton *btnlogOut = [[UIButton alloc] initWithFrame:CGRectMake(0, 40, bottomView.frame.size.width, 40)];
        btnlogOut.titleLabel.font = [UIFont systemFontOfSize:16];
        [btnlogOut addTarget:self action:@selector(logOut) forControlEvents:UIControlEventTouchUpInside];
        [btnlogOut setBackgroundImage:[UIImage fm_imageByColor:[UIColor whiteColor]] forState:UIControlStateNormal];
        [btnlogOut setBackgroundImage:[UIImage fm_imageByColor:[UIColor rgbWithRed:246 green:247 blue:428]] forState:UIControlStateHighlighted];
        [btnlogOut setTitle:@"退出" forState:UIControlStateNormal];
        [btnlogOut.layer setMasksToBounds:YES];
        [btnlogOut setTitleColor:[UIColor rgbWithRed:248 green:131 blue:131] forState:UIControlStateNormal];
        [btnlogOut.layer setBorderWidth:0.5];
        [btnlogOut.layer setBorderColor:[UIColor rgbWithRed:227 green:229 blue:233].CGColor];
        [bottomView addSubview:btnlogOut];
        
        return bottomView;
    }
    
    return [[UIView alloc] initWithFrame:CGRectZero];
}

- (void)logOut {
    // 数据清除
    [[UserManager sharedInstance] logout];
    [UserManager sharedInstance].isLogin = NO;
    // 跳转到登录界面
    [self onHeaderViewClicked];
}

#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    if (![UserManager sharedInstance].isLogin && indexPath.section == 0) {
        [self messageBox:@"请先登录"];
    }
    
    ProfileItem *item = self.dataSource[indexPath.section][indexPath.row];
    if (![item.nextPageID isEqualToString:@""] && item.nextPageID) {
        UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Profile" bundle:[NSBundle mainBundle]];
        UIViewController *nextPageVC = [storyboard instantiateViewControllerWithIdentifier:item.nextPageID];
        nextPageVC.hidesBottomBarWhenPushed = YES;
        [self.navigationController pushViewController:nextPageVC animated:NO];
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
        return 100;
    }
    
    return 0.1;
}

- (NSArray *)dataSource {
    _dataSource = [ProfileModel profileDataSourceWithUserType:_userType];
    
    return _dataSource;
}

@end
