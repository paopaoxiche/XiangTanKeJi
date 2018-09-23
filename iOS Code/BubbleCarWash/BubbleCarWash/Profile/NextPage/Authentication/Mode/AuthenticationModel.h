//
//  AuthenticationModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/8.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef NS_ENUM(NSInteger, CheckStatus) {
    CheckStatusPending,         // 待审核/审核中
    CheckStatusPassed,          // 审核通过
    CheckStatusNotPassed        // 审核不通过
};

typedef void(^ResultBlock)(id result);

@interface AuthenticationModel : NSObject

+ (void)loadCarTypeList:(ResultBlock)result;
+ (void)loadModelCertificationList:(NSInteger)status result:(ResultBlock)result;
+ (void)loadCarModelDetailList:(NSInteger)dateID result:(ResultBlock)result;
+ (void)loadCarWashCertificationInfo:(ResultBlock)result;

@end

@interface CarTypeModel : NSObject

@property (nonatomic, assign) NSInteger dataID;
@property (nonatomic, copy) NSString *imageName;
@property (nonatomic, copy) NSString *detail;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end

@interface ModelCertificationModel : NSObject

/// 车型
@property (nonatomic, copy) NSString *model;
/// 车型描述
@property (nonatomic, copy) NSString *desc;
/// 认证状态
@property (nonatomic, assign) NSInteger status;
@property (nonatomic, assign) NSInteger dataID;
@property (nonatomic, copy) NSString *imageName;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end

@interface CarModelDetailModel : NSObject

@property (nonatomic, copy) NSString *imageName;
/// 车型文本描述
@property (nonatomic, copy) NSString *model;
/// 行车证正面url
@property (nonatomic, copy) NSString *coverUrl;
/// 行车证反面url
@property (nonatomic, copy) NSString *backUrl;
/// 认证状态 0待审核 1审核通过 2审核不通过
@property (nonatomic, assign) NSInteger status;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end

@interface CarWashCertificationModel : NSObject

/// 认证状态
@property (nonatomic, copy) NSString *status;
/// 营业执照
@property (nonatomic, copy) NSString *licenseUrl;
/// 洗车证
@property (nonatomic, copy) NSString *washCardUrl;
/// 身份证正面
@property (nonatomic, copy) NSString *cardCoverUrl;
/// 身份证背面
@property (nonatomic, copy) NSString *cardBackUrl;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end
