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
#import "ModelCertificationListViewController.h"
#import "UserManager.h"
#import "UserInfoModel.h"
#import <SDWebImage/UIImageView+WebCache.h>
#import "GlobalMethods.h"
#import "UIImage+ScaleImage.h"
#import "NetworkTools.h"
#import "CarWashInfoModel.h"
#import "AuthenticationModel.h"
#import "CertificationViewController.h"

@interface PersonalInfoViewController () <UIImagePickerControllerDelegate, UINavigationControllerDelegate>

@property (nonatomic, strong) UIImagePickerController *imagePicker;
@property (nonatomic, copy) NSArray *modelCertificationList;

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
    
    _imagePicker = [[UIImagePickerController alloc] init];
    _imagePicker.delegate = self;
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(updateUserInfo:) name:@"UpdateUserInfo" object:nil];
    
    if ([UserManager sharedInstance].userType == UserTypeOwner) {
        [AuthenticationModel loadModelCertificationList:-1 result:^(NSArray *result) {
            self.modelCertificationList = result;
            [self.tableView reloadData];
        }];
    }
}

- (void)updateUserInfo:(NSNotification *)notification {
    [self.tableView reloadData];
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
                cell.cusDetailLabel.text = [UserManager sharedInstance].userInfo.nickName;
                break;
            case 1:
                cell.cusTextLable.text = @"头像";
                cell.cusDetailLabel.hidden = YES;
                cell.imgView.hidden = NO;
                [cell.imgView sd_setImageWithURL:[NSURL URLWithString:[UserManager sharedInstance].userInfo.avatarUrl]
                                placeholderImage:[UIImage imageNamed:@"CarWashAvatar"]];
                break;
            case 2:
                cell.cusTextLable.text = @"帐号";
                cell.cusDetailLabel.text = [UserManager sharedInstance].userInfo.phoneNumber;
                cell.arrow.hidden = YES;
                break;
            case 3:
                cell.cusTextLable.text = @"加入时间";
                cell.cusDetailLabel.text = [UserManager sharedInstance].userInfo.regTime;
                cell.arrow.hidden = YES;
                break;
            default:
                break;
        }
    } else {
        BOOL isOwner = [UserManager sharedInstance].userType == UserTypeOwner;
        cell.cusTextLable.text = isOwner ? @"车型认证" : @"工商认证";
        cell.cusDetailLabel.textColor = [UIColor rgbWithRed:248 green:10 blue:10];
        
        if (isOwner) {
            cell.cusDetailLabel.text = self.modelCertificationList.count > 0 ? @"" : @"未认证";
        } else {
            CertificationState state = [UserManager sharedInstance].carWashInfo.authStatus;
            switch (state) {
                case CertificationStateIn:
                    cell.cusDetailLabel.text = @"认证中";
                    break;
                case CertificationStateDone:
                    cell.cusDetailLabel.text = @"已认证";
                    break;
                case CertificationStateFailed:
                    cell.cusDetailLabel.text = @"认证失败";
                    break;
                default:
                    cell.cusDetailLabel.text = @"未认证";
                    break;
            }
        }
    }
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    if (indexPath.section == 0) {
        if (indexPath.row == 0) {
            NSString *vcIdentifier = @"EditNicknameVC";
            UIViewController *vc = [GlobalMethods viewControllerWithBuddleName:@"PersonalInfo" vcIdentifier:vcIdentifier];
            [self presentViewController:vc animated:NO completion:nil];
        }
        
        if (indexPath.row == 1) {
            [self presentImagePicker];
        }
    }
    
    BOOL isOwner = [UserManager sharedInstance].userType ==  UserTypeOwner;
    if (indexPath.section == 1) {
        if (isOwner) {
            ModelCertificationListViewController *vc = (ModelCertificationListViewController *)[GlobalMethods viewControllerWithBuddleName:@"Certification" vcIdentifier:@"ModeCertificationVC"];
            vc.modelCertificationList = self.modelCertificationList;
            [self.navigationController pushViewController:vc animated:YES];
        } else {
            CertificationViewController *vc = (CertificationViewController *)[GlobalMethods viewControllerWithBuddleName:@"Certification" vcIdentifier:@"CertificationVC"];
            vc.state = [UserManager sharedInstance].carWashInfo.authStatus;
            [self.navigationController pushViewController:vc animated:YES];
        }
    }
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 11.999999;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    return 0.000001;
}

#pragma mark - UIImagePickerController

- (BOOL)isValidCamera {
    return [UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypeCamera];
}

- (BOOL)isValidPhotoLibrary {
    return [UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypePhotoLibrary];
}

- (void)presentImagePicker {
    UIAlertController *actionSheet = [UIAlertController alertControllerWithTitle:nil message:nil preferredStyle:UIAlertControllerStyleActionSheet];
    UIAlertAction *photoAction = [UIAlertAction actionWithTitle:@"从相册选择" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
        if (![self isValidPhotoLibrary]) {
            [self messageBox:@"不能打开相册"];
        }
        
        self.imagePicker.sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
        [self presentViewController:self.imagePicker animated:YES completion:nil];
    }];
    UIAlertAction *cameraAction = [UIAlertAction actionWithTitle:@"拍摄" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
        if (![self isValidCamera]) {
            [self messageBox:@"不能打开相机"];
        }
        
        self.imagePicker.sourceType = UIImagePickerControllerSourceTypeCamera;
        [self presentViewController:self.imagePicker animated:YES completion:nil];
    }];
    UIAlertAction *cancel = [UIAlertAction actionWithTitle:@"" style:UIAlertActionStyleCancel handler:nil];
    
    [actionSheet addAction:photoAction];
    [actionSheet addAction:cameraAction];
    [actionSheet addAction:cancel];
    
    [self presentViewController:actionSheet animated:YES completion:nil];
}

/**
 *  选中相片之后调用
 */
- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary<NSString *,id> *)info {
    UIImage *image = [[info objectForKey:UIImagePickerControllerOriginalImage] imageWithScale:320];
    
    // 上传头像
    [[NetworkTools sharedInstance] updateUserAvatar:image success:^(NSDictionary *response, BOOL isSuccess) {
        if ([[response objectForKey:@"code"] integerValue] == 200) {
            // 更新头像
            PersonalInfoCell *cell = [self.tableView cellForRowAtIndexPath:[NSIndexPath indexPathForRow:1 inSection:0]];
            cell.imgView.image = image;
            [self messageBox:@"修改头像成功"];
            [UserManager sharedInstance].isUpdateUserInfo = YES;
            [[UserManager sharedInstance] obtainUserInfo];
        }
    } failed:^(NSError *error) {
        [self messageBox:@"头像上传失败"];
    }];
    
    [picker dismissViewControllerAnimated:YES completion:nil];
}

#pragma mark -

- (void)messageBox:(NSString *)lpszMessage {
    UIAlertController *alert = [UIAlertController alertControllerWithTitle:@"提示"
                                                                   message:lpszMessage
                                                            preferredStyle:UIAlertControllerStyleAlert];
    UIAlertAction *iKnow = [UIAlertAction actionWithTitle:@"我知道了"
                                                    style:UIAlertActionStyleCancel
                                                  handler:nil];
    [alert addAction:iKnow];
    [self presentViewController:alert animated:YES completion:nil];
}

@end
