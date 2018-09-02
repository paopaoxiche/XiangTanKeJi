//
//  UserManager.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/1.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "UserManager.h"
#import "UserInfoModel.h"
#import "LYKeyChainStore.h"
#import "NetworkTools.h"
#import "AFNetworking.h"
#import "LoginViewController.h"

@interface UserManager ()

@end

@implementation UserManager

+ (instancetype)sharedInstance {
    static UserManager *instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [[UserManager alloc] init];
    });
    
    return instance;
}

+ (UserType)userType {
    return [UserManager sharedInstance].userInfo.type;
}

- (instancetype)init {
    self = [super init];
    if (self) {
        _isLogin = NO;
    }
    
    return self;
}

- (void)savaUserInfoWithPassword:(NSString *)password {
    NSString *bundleID = [[NSBundle mainBundle] bundleIdentifier];
    NSString *passwordStr = [NSString stringWithFormat:@"%lu/%@/%@", _userInfo.type, password, _userInfo.token];
    [LYKeyChainStore saveAccount:_userInfo.userID
                        password:passwordStr
                         service:bundleID];
}

- (BOOL)isAutoLogin {
    NSString *bundleID = [[NSBundle mainBundle] bundleIdentifier];
    NSString *account = [LYKeyChainStore accountWithService:bundleID];
    NSString *password = [LYKeyChainStore passwordWithService:bundleID];
    
    if (!account || !password) {
        return NO;
    }
    
    if (!_userInfo) {
        NSArray *data = [password componentsSeparatedByString:@"/"];
        if (data.count < 3) {
            return NO;
        }
        
        _userInfo = [[UserInfoModel alloc] init];
        _userInfo.type = [data[0] integerValue];
        _userInfo.code = data[1];
        _userInfo.token = data[2];
        _userInfo.userID = account;
    }
    
    return YES;
}

- (void)autoLogin:(CodeResultBlock)block {
    if (!_isLogin && [self isAutoLogin]) {
//        AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
//        NSString *authenStr = [NSString stringWithFormat:@"user_id=%@,token=%@", _userInfo.userID, _userInfo.token];
//        NSString *url = @"http://101.200.63.245:8080/user/checkCarOwner";
//        [manager.requestSerializer setValue:authenStr forHTTPHeaderField:@"authentication"];
//        [manager GET:url parameters:nil progress:nil success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
//            NSLog(@"%@", responseObject);
//            NSInteger code = [responseObject[@"code"] integerValue];
//            self.isLogin = code == 200 ? YES : NO;
//        } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
//            self.isLogin = NO;
//        }];
        
        [NetworkTools checkToken:_userInfo.token userID:_userInfo.userID isOwner:_userInfo.type == UserTypeOwner success:^(NSDictionary *response, BOOL isSuccess) {
            NSInteger code = [response[@"code"] integerValue];
            self.isLogin = code == 200 ? YES : NO;
            block(code);
        } failed:^(NSError *error) {
            self.isLogin = NO;
        }]; // block
    } // if
} // func

- (void)obtainUserInfo {
    if (_isLogin && (!_userInfo.phoneNumber || [_userInfo.phoneNumber isEqualToString:@""])) {
        [NetworkTools obtainUserInfoWithUserID:_userInfo.userID success:^(NSDictionary *response, BOOL isSuccess) {
            NSInteger code = [response[@"code"] integerValue];
            if (code == 200) {
                UserInfoModel *model = [[UserInfoModel alloc] initWithDic:response[@"data"]];
//                self.userInfo.avatarUrl = model.avatarUrl;
//                self.userInfo.nickName = model.nickName;
//                self.userInfo.regTime = model.regTime;
//                self.userInfo.score = model.score;
//                self.userInfo.phoneNumber = model.phoneNumber;
                model.token = self.userInfo.token;
                self.userInfo = model;
                self.authentication = [NSString stringWithFormat:@"user_id=%@,token=%@", self.userInfo.userID, self.userInfo.token];
                [[NSNotificationCenter defaultCenter] postNotificationName:@"UpdateUserInfo" object:nil];
            }
        } failed:^(NSError *error) {
            
        }];
    }
}

@end
