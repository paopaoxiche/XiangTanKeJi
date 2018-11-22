//
//  UserManager.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/1.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "DataType.h"

typedef NS_ENUM(NSInteger, UserType) {
    UserTypeOwner,
    UserTypeCarWash
};

typedef NS_ENUM(NSInteger, CertificationState) {
    CertificationStateAdd = -1,             // 添加认证
    CertificationStateIn = 0,               // 认证中 
    CertificationStateDone = 1,             // 认证完成
    CertificationStateFailed = 2,           // 认证失败
};

typedef void(^CodeResultBlock)(NSInteger code);

@class UserInfoModel;
@class CarWashInfoModel;

@interface UserManager : NSObject

@property (nonatomic, strong) UserInfoModel *userInfo;
@property (nonatomic, strong) CarWashInfoModel *carWashInfo;
@property (nonatomic, assign) BOOL isLogin;
@property (nonatomic, assign) BOOL isUpdateUserInfo;
@property (nonatomic, assign) Location location;
/// 省
@property (nonatomic, copy) NSString *province;
/// 市
@property (nonatomic, copy) NSString *city;
/// 区
@property (nonatomic, copy) NSString *district;
@property (nonatomic, copy) NSString *authentication;
@property (nonatomic, copy) NSString *address;

+ (instancetype)sharedInstance;
- (UserType)userType;
- (void)savaUserInfoWithPassword:(NSString *)password;
- (BOOL)isAutoLogin;
- (void)autoLogin:(CodeResultBlock)block;
- (void)obtainUserInfo;
- (void)logout;

@end
