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
    CertificationStateAdd,          // 添加认证
    CertificationStateIn,           // 认证中
    CertificationStateDone          // 认证完成
};

typedef void(^CodeResultBlock)(NSInteger code);

@class UserInfoModel;

@interface UserManager : NSObject

@property (nonatomic, strong) UserInfoModel *userInfo;
@property (nonatomic, assign) BOOL isLogin;
@property (nonatomic, assign) BOOL isUpdateUserInfo;
@property (nonatomic, copy) NSString *authentication;
@property (nonatomic, assign) Location location;

+ (instancetype)sharedInstance;
- (UserType)userType;
- (void)savaUserInfoWithPassword:(NSString *)password;
- (BOOL)isAutoLogin;
- (void)autoLogin:(CodeResultBlock)block;
- (void)obtainUserInfo;

@end
