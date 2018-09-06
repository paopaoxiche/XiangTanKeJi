//
//  UpgradeReminderModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

@class UpgradeInfoModel;

typedef void(^Success)(id upgradeInfo, BOOL isSuccess);
typedef void(^Failed)(NSError *error);

@interface UpgradeReminderModel : NSObject

+ (void)loadUpgradeInfo:(Success)success failed:(Failed)failed;

@end

@interface UpgradeInfoModel : NSObject

/// 下载地址
@property (nonatomic, copy) NSString *downloadUrl;
/// 是否有新版本
@property (nonatomic, copy) NSString *hasNewVersion;
/// 版本号
@property (nonatomic, copy) NSString *versionCode;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end
