//
//  UserManager.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/1.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef NS_ENUM(NSInteger, UserType) {
    UserTypeOwner,
    UserTypeCarWash
};

typedef NS_ENUM(NSInteger, CertificationState) {
    CertificationStateAdd,          // 添加认证
    CertificationStateIn,           // 认证中
    CertificationStateDone          // 认证完成
};

@class UserInfoModel;

@interface UserManager : NSObject

@property (nonatomic, strong) UserInfoModel *userInfo;
@property (nonatomic, assign) BOOL isLogin;

+ (instancetype)sharedInstance;
+ (UserType)userType;
- (void)savaUserInfoWithPassword:(NSString *)password;
- (BOOL)isAutoLogin;
- (void)autoLogin;
- (void)obtainUserInfo;

@end
