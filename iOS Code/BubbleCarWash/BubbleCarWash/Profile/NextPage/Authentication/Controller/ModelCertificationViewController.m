//
//  ModelCertificationViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/24.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "ModelCertificationViewController.h"
#import "ModelCertificationCell.h"
#import "CertificationViewController.h"

@interface ModelCertificationViewController ()

@property (weak, nonatomic) IBOutlet UILabel *carNumberLabel;

@end

@implementation ModelCertificationViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.tableView.rowHeight = 80;
    self.tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    self.navigationItem.leftBarButtonItem = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"BackArrow"] style:UIBarButtonItemStylePlain target:self action:@selector(backToSuperVC)];
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd target:self action:@selector(pushToNewCarModelVC)];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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
    return 3;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    ModelCertificationCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ModelCertificationIdentifier"];
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Certification" bundle:[NSBundle mainBundle]];
    CertificationViewController *certificationVC = [storyboard instantiateViewControllerWithIdentifier:@"CertificationVC"];
    certificationVC.state = indexPath.row == 2 ? CertificationStateIn : CertificationStateDone;
    [self.navigationController pushViewController:certificationVC animated:YES];
}

@end
