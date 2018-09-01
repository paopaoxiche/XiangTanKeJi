//
//  PersonalInfoViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "PersonalInfoViewController.h"
#import "PersonalInfoCell.h"
#import "UIColor+Category.h"
#import "ModelCertificationViewController.h"

@interface PersonalInfoViewController ()


@end

@implementation PersonalInfoViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.title = @"个人信息";
    [self.navigationController.navigationBar setBackgroundImage:nil forBarMetrics:UIBarMetricsDefault];
    [self.navigationController.navigationBar setShadowImage:nil];
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName:[UIColor blackColor]}];
    self.tableView.rowHeight = 50;
    self.tableView.separatorColor = [UIColor rgbWithRed:235 green:235 blue:241];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - UITableViewDatasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 2;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    if (section == 0) {
        return 4;
    } else {
        return 1;
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    PersonalInfoCell *cell = [tableView dequeueReusableCellWithIdentifier:@"PersonalInfoIdentifier"];
    
    if (indexPath.section == 0) {
        switch (indexPath.row) {
            case 0:
                cell.cusTextLable.text = @"昵称";
                cell.cusDetailLabel.text = @"爱洗车";
                break;
            case 1:
                cell.cusTextLable.text = @"头像";
                cell.cusDetailLabel.hidden = YES;
                cell.imgView.hidden = NO;
                cell.imgView.image = [UIImage imageNamed:@"AppIcon"];
                break;
            case 2:
                cell.cusTextLable.text = @"帐号";
                cell.cusDetailLabel.text = @"12345678909";
                cell.arrow.hidden = YES;
                break;
            case 3:
                cell.cusTextLable.text = @"加入时间";
                cell.cusDetailLabel.text = @"2018-07-21";
                cell.arrow.hidden = YES;
                break;
            default:
                break;
        }
    } else {
        cell.cusTextLable.text = @"车型认证";
        cell.cusDetailLabel.text = @"未认证";
        cell.cusDetailLabel.textColor = [UIColor rgbWithRed:248 green:10 blue:10];
    }
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    if (indexPath.section == 1) {
        UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Certification" bundle:[NSBundle mainBundle]];
        ModelCertificationViewController *modelCertificationVC = [storyboard instantiateViewControllerWithIdentifier:@"ModeCertificationVC"];
        [self.navigationController pushViewController:modelCertificationVC animated:YES];
    }
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 11.999999;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    return 0.000001;
}

@end
