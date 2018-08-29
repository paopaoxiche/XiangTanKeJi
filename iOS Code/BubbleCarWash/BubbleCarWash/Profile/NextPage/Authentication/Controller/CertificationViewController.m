//
//  CertificationViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/24.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CertificationViewController.h"
#import "CertificationCell.h"
#import "UserManager.h"

@interface CertificationViewController ()

@property (nonatomic, assign) UserType userType;

@end

@implementation CertificationViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    self.navigationItem.leftBarButtonItem = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"BackArrow"] style:UIBarButtonItemStylePlain target:self action:@selector(backToSuperVC)];
    
//    _userType = UserTypeOwner;
    _userType = UserTypeCarWash;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)backToSuperVC {
    [self.navigationController popViewControllerAnimated:YES];
}

#pragma mark - UITableViewDatasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return _userType == UserTypeOwner ? 2 : 3;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    if (_userType == UserTypeOwner) {
        return section == 0 ? (_state == CertificationStateAdd ? 4 : 1) : 3;
    } else {
        return section == 2 ? 3 : 2;
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    if (indexPath.row == 0 && !(indexPath.section == 0 && _userType == UserTypeOwner && _state != CertificationStateAdd)) {
        NSString *titleStr = indexPath.section == 0 ? @"车型选择" : @"行车证信息";
        CertificationTitleCell *cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCellIdentifier"];
        cell.title = titleStr;
        
        return cell;
    }
    
    if (indexPath.section == 0 && _userType == UserTypeOwner) {
        NSString *imageName;
        NSString *desc;
        if (_state == CertificationStateAdd) {
            switch (indexPath.row) {
                case 1:
                    imageName = @"LargeCar_New";
                    desc = @"大型车（九座以上）";
                    break;
                case 2:
                    imageName = @"MediumCar_New";
                    desc = @"中型车（七座以上九座及以下）";
                    break;
                case 3:
                    imageName = @"SmallCar_New";
                    desc = @"小型车（七座以下）";
                    break;
                default:
                    break;
            }
        } else {
            imageName = @"LargeCar_New";
            desc = @"大型车（九座以上）";
        }
        
        CertificationCarTypeCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CarTypeIdentifier"];
        cell.carImgName = imageName;
        cell.carDesc = desc;
        
        if (_state == CertificationStateAdd) {
            cell.selectImgName = indexPath.row == 1 ? @"SingleSelection_Selected" : @"SingleSelection_Normal";
        }
        
        return cell;
    }
    
    NSString *desc;
    NSString *imgName;
    BOOL isAddCer = _state == CertificationStateAdd;
    if (_userType == UserTypeOwner) {
        switch (indexPath.row) {
            case 1:
                desc = isAddCer ? @"上传本车行车证（正面）" : @"行车证（正面）";
                imgName = isAddCer ? @"": @"CommodityManage";
                break;
            case 2:
                desc = isAddCer ? @"上传本车行车证（反面）" : @"行车证（反面）";
                imgName = isAddCer ? @"": @"CommodityManage";
                break;
            default:
                break;
        }
    } else {
        switch (indexPath.section) {
            case 0:
                desc = isAddCer ? @"上传工商营业执照（必填）" : @"营业执照";
                imgName = isAddCer ? @"": @"CommodityManage";
                break;
            case 1:
                desc = isAddCer ? @"上传洗车证（必填）" : @"洗车证";
                imgName = isAddCer ? @"": @"CommodityManage";
                break;
            case 2:
                if (indexPath.row == 1) {
                    desc = isAddCer ? @"上传法人身份证（正面）" : @"法人身份证（正面）";
                } else {
                    desc = isAddCer ? @"上传法人身份证（反面）" : @"法人身份证（反面）";
                }
                imgName = isAddCer ? @"": @"CommodityManage";
                break;
            default:
                break;
        }
    }
    
    CertificationInfoCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CertificationInfoIdentifier"];
    CertificationCellType type;
    if (_state == CertificationCellTypeAdd) {
        type = CertificationCellTypeAdd;
    } else if (_state == CertificationCellTypeIn) {
        type = CertificationCellTypeIn;
    } else {
        type = CertificationCellTypeDone;
    }
    [cell setCerDesc:desc cerImgName:imgName cerType:type];
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    if (indexPath.row == 0) {
        return 58;
    }
    
    if (indexPath.section == 0) {
        return 44;
    }
    
    return 100;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 10;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    return 0.001;
}

@end
