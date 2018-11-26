//
//  UpgradeReminderModel.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "UpgradeReminderModel.h"
#import "NetworkTools.h"

@implementation UpgradeReminderModel

+ (void)loadUpgradeInfo:(Success)success failed:(Failed)failed {
    [NetworkTools obtainUpgradeInfo:^(NSDictionary *response, BOOL isSuccess) {
        if ([response[@"code"] integerValue] == 200) {
            UpgradeInfoModel *model = [[UpgradeInfoModel alloc] initWithDic:response[@"data"]];
            success(model, YES);
        } else {
            success(nil, NO);
        }
    } failed:^(NSError *error) {
        failed(error);
    }];
}

@end

@interface UpgradeInfoModel ()

/// 编译号
@property (nonatomic, copy) NSString *buildCode;
/// 是否强制更新
@property (nonatomic, copy) NSString *mandatoryUpdata;
/// 更新日志
@property (nonatomic, copy) NSString *updataLog;
/// 系统类型 0-安卓 1-iOS
@property (nonatomic, assign) NSInteger systemType;

@end

@implementation UpgradeInfoModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _downloadUrl = [dic objectForKey:@"downloadUrl"];
        _hasNewVersion = [dic objectForKey:@"hasNewApp"];
        _versionCode = [dic objectForKey:@"versionCode"];
        _buildCode = [dic objectForKey:@"buildCode"];
        _mandatoryUpdata = [dic objectForKey:@"mandatoryUpdata"];
        _updataLog = [dic objectForKey:@"updataLog"];
        _systemType = [[dic objectForKey:@"systemType"] integerValue];
    }
    
    return self;
}

@end

