//
//  UpgradeReminderViewController.m
//  BubbleCarWash
//
//  Created by Rachel on 2018/9/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "UpgradeReminderViewController.h"
#import "UpgradeReminderModel.h"

@interface UpgradeReminderViewController ()

@property (weak, nonatomic) IBOutlet UIImageView *upgradeImageView;
@property (weak, nonatomic) IBOutlet UILabel *upgradeLabel;

@end

@implementation UpgradeReminderViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [UpgradeReminderModel loadUpgradeInfo:^(id upgradeInfo, BOOL isSuccess) {
        UpgradeInfoModel *model = (UpgradeInfoModel *)upgradeInfo;
        if (isSuccess && model.hasNewVersion) {
            self.upgradeImageView.image = [UIImage imageNamed:@"NewVersion"];
            self.upgradeLabel.text = [NSString stringWithFormat:@"有新版本%@可供升级", model.versionCode];
        } else {
            self.upgradeImageView.image = [UIImage imageNamed:@"NoNewVersion"];
            self.upgradeLabel.text = @"当前已是最新版本";
        }
    } failed:^(NSError *error) {
        self.upgradeImageView.image = [UIImage imageNamed:@"NoNewVersion"];
        self.upgradeLabel.text = @"当前已是最新版本";
    }];
}

@end
