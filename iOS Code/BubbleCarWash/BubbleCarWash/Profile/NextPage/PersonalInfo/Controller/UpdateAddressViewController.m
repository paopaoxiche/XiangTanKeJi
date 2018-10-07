//
//  UpdateAddressViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/10/7.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "UpdateAddressViewController.h"
#import "UserManager.h"
#import "CarWashInfoModel.h"
#import "NetworkTools.h"

@interface UpdateAddressViewController ()

@property (weak, nonatomic) IBOutlet UILabel *locationLabel;

@end

@implementation UpdateAddressViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _locationLabel.text = [UserManager sharedInstance].address;
    
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithTitle:@"确定" style:UIBarButtonItemStylePlain target:self action:@selector(updateAddress)];
}

- (void)updateAddress {
    [[NetworkTools sharedInstance] updateCarWashAddress:[UserManager sharedInstance].carWashInfo.washID address:[UserManager sharedInstance].address latitude:[UserManager sharedInstance].location.lat longitude:[UserManager sharedInstance].location.lng success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [response[@"code"] integerValue];
        if (code == 200) {
            [self messageBox:@"修改地址成功" handle:^{
                [self.navigationController popToRootViewControllerAnimated:YES];
            }];
        } else {
            [self messageBox:@"修改地址失败，请重试"];
        }
    } failed:^(NSError *error) {
        [self messageBox:@"修改地址失败，请重试"];
    }];
}

@end
