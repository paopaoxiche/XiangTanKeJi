//
//  CertificationViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/24.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CertificationViewController.h"
#import "CertificationCell.h"
#import "AuthenticationModel.h"
#import "NetworkTools.h"
#import "UIImage+ScaleImage.h"
#import "GlobalMethods.h"
#import "SeeLargePictureViewController.h"
#import "UserInfoModel.h"

@interface CertificationViewController () <CertificationCellDelegate, UIImagePickerControllerDelegate, UINavigationControllerDelegate, UITableViewDelegate, UITableViewDataSource>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UIView *headerView;
@property (weak, nonatomic) IBOutlet UIView *backView;
@property (weak, nonatomic) IBOutlet UILabel *locationLabel;
@property (weak, nonatomic) IBOutlet UITextField *washNameTextField;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *tableViewTopConstraint;
@property (nonatomic, copy) NSArray *dataSource;
@property (nonatomic, assign) UserType userType;
@property (nonatomic, assign) NSIndexPath *carTypeIndexPath;
@property (nonatomic, assign) NSIndexPath *imageIndexPath;
@property (nonatomic, strong) UIImage *coverImage;
@property (nonatomic, strong) UIImage *backImage;
@property (nonatomic, strong) CarModelDetailModel *modelDetail;
@property (nonatomic, strong) UIImagePickerController *imagePicker;
@property (nonatomic, strong) RegisterWashParam *registerWash;

@end

@implementation CertificationViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    self.navigationItem.leftBarButtonItem = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"BackArrowBlack"] style:UIBarButtonItemStylePlain target:self action:@selector(backToSuperVC)];
    [self.tableView registerNib:[UINib nibWithNibName:@"CertificationCell" bundle:[NSBundle mainBundle]]
         forCellReuseIdentifier:@"CarTypeIdentifier"];
    
    _userType = [[UserManager sharedInstance] userType];
    _imagePicker = [[UIImagePickerController alloc] init];
    _imagePicker.delegate = self;
    
    if (_userType == UserTypeOwner) {
        if (_state == CertificationStateAdd) {
            [AuthenticationModel loadCarTypeList:^(NSArray *result) {
                self.dataSource = result;
                [self.tableView reloadData];
            }];
            self.carTypeIndexPath = [NSIndexPath indexPathForRow:1 inSection:0];
            self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithTitle:@"提交" style:UIBarButtonItemStylePlain target:self action:@selector(onSubmitBtnClicked:)];
        } else {
            [AuthenticationModel loadCarModelDetailList:_dataID result:^(CarModelDetailModel *result) {
                self.modelDetail = result;
                if (self.modelDetail) {
                    [self.tableView reloadData];
                } else {
                    // 进行提示
                }
            }]; // block
        } // else
    } else {
        if (_state == CertificationStateAdd) {
            _tableViewTopConstraint.constant = 135;
            _headerView.hidden = NO;
            _registerWash = [[RegisterWashParam alloc] init];
            
            UITapGestureRecognizer *singleGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(backToSuperVC)];
            [_backView addGestureRecognizer:singleGesture];
            
            UITapGestureRecognizer *hideKeyboard = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(hideKeyboard)];
            [self.view addGestureRecognizer:hideKeyboard];
            
            NSString *address = [UserManager sharedInstance].address;
            if (address && ![address isEqualToString:@""]) {
                self.locationLabel.text = address;
            } else {
                [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(updateLocation:) name:@"UpdateLocation" object:nil];
            }
        } else {
            
        }
    }
}

- (void)updateLocation:(NSNotification *)notification {
    self.locationLabel.text = [UserManager sharedInstance].address;
}

- (void)hideKeyboard {
    if (self.washNameTextField.isFirstResponder) {
        [self.washNameTextField resignFirstResponder];
    }
}

- (void)backToSuperVC {
    [self.navigationController popViewControllerAnimated:YES];
}

- (IBAction)onSubmitBtnClicked:(id)sender {
    if (_userType == UserTypeOwner) {
        CarTypeModel *model = _dataSource[_carTypeIndexPath.row - 1];
        NSString *idStr = [NSString stringWithFormat:@"%li", model.dataID];
        [[NetworkTools sharedInstance] submitModelReview:idStr cover:_coverImage back:_backImage success:^(NSDictionary *response, BOOL isSuccess) {
            NSInteger code = [[response objectForKey:@"code"] integerValue];
            if (code == 200) {
                [self messageBox:@"车型提交成功，请等待审核" handle:^{
                    [self backToSuperVC];
                }];
            } else {
                [self messageBox:@"提交失败，请稍后重试"];
            }
        } failed:^(NSError *error) {
            [self messageBox:@"提交失败，请稍后重试"];
        }];
    } else {
        _registerWash.phone = [UserManager sharedInstance].userInfo.phoneNumber;
        _registerWash.name = self.washNameTextField.text;
        _registerWash.address = [UserManager sharedInstance].address;
        _registerWash.coordX = [NSString stringWithFormat:@"%.6f", [UserManager sharedInstance].location.lat];
        _registerWash.coordY = [NSString stringWithFormat:@"%.6f", [UserManager sharedInstance].location.lng];
        
        [[NetworkTools sharedInstance] registerWash:_registerWash success:^(NSDictionary *response, BOOL isSuccess) {
            NSInteger code = [[response objectForKey:@"code"] integerValue];
            if (code == 200) {
                NSString *code = [UserManager sharedInstance].userInfo.code;
                UserInfoModel *userInfo = [[UserInfoModel alloc] initWithDic:response[@"data"]];
                [UserManager sharedInstance].userInfo = userInfo;
                [UserManager sharedInstance].isLogin = YES;
                [[UserManager sharedInstance] savaUserInfoWithPassword:code];
                UIViewController *mainVC = [GlobalMethods viewControllerWithBuddleName:@"Main" vcIdentifier:@"MainVC"];
                [self presentViewController:mainVC animated:YES completion:nil];
            } else {
                [self messageBox:@"提交失败，请稍后重试"];
            }
        } failed:^(NSError *error) {
            [self messageBox:@"提交失败，请稍后重试"];
        }];
    }
}

#pragma mark - UITableViewDatasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return _userType == UserTypeOwner ? 2 : 3;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    if (_userType == UserTypeOwner) {
        if (_state == CertificationStateAdd) {
            return section == 0 ? _dataSource.count + 1 : 3;
        } else {
            return _modelDetail ? (section == 0 ? 1 : 3) : 0;
        }
    } else {
        return section == 2 ? 3 : 2;
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    if (indexPath.row == 0 && !(indexPath.section == 0 && _userType == UserTypeOwner && _state != CertificationStateAdd)) {
        NSString *titleStr = @"";
        if (_userType == UserTypeOwner) {
            titleStr = indexPath.section == 0 ? @"车型选择" : @"行车证信息";
        } else {
            if (indexPath.section == 0) {
                titleStr = @"工商营业执照";
            } else if (indexPath.section == 1) {
                titleStr = @"洗车证";
            } else {
                titleStr = @"法人身份证";
            }
        }
        
        CertificationTitleCell *cell = [tableView dequeueReusableCellWithIdentifier:@"TitleCellIdentifier"];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.title = titleStr;
        
        return cell;
    }
    
    if (indexPath.section == 0 && _userType == UserTypeOwner) {
        NSString *imageName;
        NSString *desc;
        if (_state == CertificationStateAdd) {
            CarTypeModel *model = _dataSource[indexPath.row - 1];
            imageName = model.imageName;
            desc = model.detail;
        } else {
            imageName = _modelDetail.imageName;
            desc = _modelDetail.model;
        }
        
        CertificationCarTypeCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CarTypeIdentifier"];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.delegate = self;
        cell.carImgName = imageName;
        cell.carDesc = desc;
        
        if (_state == CertificationStateAdd) {
            cell.selectImgName = indexPath.row == 1 ? @"SingleSelection_Selected" : @"SingleSelection_Normal";
        }
        
        return cell;
    }
    
    NSString *desc = @"";
    NSString *imgName = @"";
    BOOL isAddCer = _state == CertificationStateAdd;
    if (_userType == UserTypeOwner) {
        switch (indexPath.row) {
            case 1:
                desc = isAddCer ? @"上传本车行车证（正面）" : @"行车证（正面）";
                imgName = isAddCer ? @"": _modelDetail.coverUrl;
                break;
            case 2:
                desc = isAddCer ? @"上传本车行车证（反面）" : @"行车证（反面）";
                imgName = isAddCer ? @"": _modelDetail.backUrl;
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
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    cell.delegate = self;
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

#pragma mark -

- (void)onSelectedBtnClicked:(CertificationCarTypeCell *)cell {
    cell.selectImgName = @"SingleSelection_Selected";
    
    CertificationCarTypeCell *selectedCell = [self.tableView cellForRowAtIndexPath:_carTypeIndexPath];
    selectedCell.selectImgName = @"SingleSelection_Normal";
    
    self.carTypeIndexPath = [self.tableView indexPathForCell:cell];
}

- (void)onUploadViewClicked:(CertificationInfoCell *)cell {
    self.imageIndexPath = [self.tableView indexPathForCell:cell];
    if (_state == CertificationCellTypeAdd) {
        [self presentImagePicker];
    } else {
        if (!cell.cerDetailImage) {
            return;
        }
        
        SeeLargePictureViewController *vc = [[SeeLargePictureViewController alloc] init];
        vc.image = cell.cerDetailImage;
        [self presentViewController:vc animated:YES completion:nil];
    }
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
    
    if (_userType == UserTypeOwner) {
        if (_imageIndexPath.row == 1) {
            self.coverImage = image;
        } else {
            self.backImage = image;
        }
    } else {
        if (_imageIndexPath.section == 0) {
            self.registerWash.license = image;
        } else if (_imageIndexPath.section == 1) {
            self.registerWash.washCard = image;
        } else {
            if (_imageIndexPath.row == 1) {
                self.registerWash.idCardPositive = image;
            }
            
            if (_imageIndexPath.row == 2) {
                self.registerWash.idCardBack = image;
            }
        }
    }
    
    CertificationInfoCell *cell = [self.tableView cellForRowAtIndexPath:_imageIndexPath];
    cell.cerImage = image;
    
    [picker dismissViewControllerAnimated:YES completion:nil];
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    if (indexPath.row == 0) {
        return 58;
    }
    
    if (indexPath.section == 0 && _userType == UserTypeOwner) {
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
