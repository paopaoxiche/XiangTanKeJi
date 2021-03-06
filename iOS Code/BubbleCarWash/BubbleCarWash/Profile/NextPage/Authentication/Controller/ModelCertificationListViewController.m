//
//  ModelCertificationListViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/23.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "ModelCertificationListViewController.h"
#import "ModelCertificationCell.h"
#import "CertificationViewController.h"
#import "AuthenticationModel.h"

@interface ModelCertificationListViewController ()

@property (nonatomic, weak) IBOutlet UILabel *carNumberLabel;

@end

@implementation ModelCertificationListViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.title = @"车型认证";
    self.tableView.rowHeight = 80;
    self.tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    self.navigationItem.leftBarButtonItem = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"BackArrowBlack"] style:UIBarButtonItemStylePlain target:self action:@selector(backToSuperVC)];
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:@selector(pushToNewCarModelVC)];
    [self.navigationController.navigationBar setTintColor:[UIColor blackColor]];
    
    if (_modelCertificationList.count > 0) {
        self.carNumberLabel.text = [NSString stringWithFormat:@"名下车辆数:%li", _modelCertificationList.count];
    } else {
        self.carNumberLabel.text = @"暂无车型认证，请添加";
    }
}

- (void)backToSuperVC {
    [self.navigationController popViewControllerAnimated:YES];
}

- (void)pushToNewCarModelVC {
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Certification" bundle:[NSBundle mainBundle]];
    CertificationViewController *certificationVC = [storyboard instantiateViewControllerWithIdentifier:@"CertificationVC"];
    certificationVC.state = CertificationStateAdd;
    [self.navigationController pushViewController:certificationVC animated:YES];
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return _modelCertificationList.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    ModelCertificationModel *model = [_modelCertificationList objectAtIndex:indexPath.row];
    ModelCertificationCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ModelCertificationIdentifier"];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    cell.model = model.model;
    cell.desc = model.desc;
    cell.status = model.status;
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    ModelCertificationModel *model = [_modelCertificationList objectAtIndex:indexPath.row];
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Certification" bundle:[NSBundle mainBundle]];
    CertificationViewController *certificationVC = [storyboard instantiateViewControllerWithIdentifier:@"CertificationVC"];
    certificationVC.state = model.status == 1 ? CertificationStateDone : CertificationStateIn;
    certificationVC.dataID = model.dataID;
    [self.navigationController pushViewController:certificationVC animated:YES];
}

@end
