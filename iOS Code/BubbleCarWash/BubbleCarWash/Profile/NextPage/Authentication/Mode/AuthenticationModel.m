//
//  AuthenticationModel.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/8.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "AuthenticationModel.h"
#import "NetworkTools.h"

@implementation AuthenticationModel

+ (void)loadCarTypeList:(ResultBlock)result {
    [[NetworkTools sharedInstance] obtainCarTypeList:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSArray *dataArr = [response objectForKey:@"data"];
            NSMutableArray *carTypeList = [NSMutableArray arrayWithCapacity:dataArr.count];
            for (NSDictionary *dic in dataArr) {
                CarTypeModel *model = [[CarTypeModel alloc] initWithDic:dic];
                [carTypeList addObject:model];
            }
            
            result(carTypeList);
        } else {
            result(@[]);
        }
    } failed:^(NSError *error) {
        result(@[]);
    }];
}

+ (void)loadModelCertificationList:(NSInteger)status result:(ResultBlock)result {
    [[NetworkTools sharedInstance] obtainModelReviewList:status success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSArray *dataArr = [response objectForKey:@"data"];
            NSMutableArray *modelReviewList = [NSMutableArray arrayWithCapacity:dataArr.count];
            for (NSDictionary *dic in dataArr) {
                ModelCertificationModel *model = [[ModelCertificationModel alloc] initWithDic:dic];
                [modelReviewList addObject:model];
            }
            
            result(modelReviewList);
        } else {
            result(@[]);
        }
    } failed:^(NSError *error) {
        result(@[]);
    }];
}

+ (void)loadCarModelDetailList:(NSInteger)dateID result:(ResultBlock)result {
    [[NetworkTools sharedInstance] obtainModelDetail:dateID success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSDictionary *dataDic = [response objectForKey:@"data"];
            CarModelDetailModel *model = [[CarModelDetailModel alloc] initWithDic:dataDic];
            
            result(model);
        } else {
            result(nil);
        }
    } failed:^(NSError *error) {
        result(nil);
    }];
}

+ (void)loadCarWashCertificationInfo:(ResultBlock)result {
    [[NetworkTools sharedInstance] obtainCarWashCertificationInfo:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSDictionary *dataDic = [response objectForKey:@"data"];
            CarWashCertificationModel *model = [[CarWashCertificationModel alloc] initWithDic:dataDic];
            
            result(model);
        } else {
            result(nil);
        }
    } failed:^(NSError *error) {
        result(nil);
    }];
}

@end

#pragma mark - 车型模型

@interface CarTypeModel ()

/// 车型
@property (nonatomic, copy) NSString *model;
/// 车型描述
@property (nonatomic, copy) NSString *desc;

@end

@implementation CarTypeModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _dataID = [[dic objectForKey:@"id"] integerValue];
        _model = [dic objectForKey:@"typeString"];
        _desc = [dic objectForKey:@"typeDesc"];
        _detail = [NSString stringWithFormat:@"%@(%@)", _model, _desc];
        
        if ([_model isEqualToString:@"大型车"]) {
            _imageName = @"LargeCar_New";
        } else if ([_model isEqualToString:@"中型车"]) {
            _imageName = @"MediumCar_New";
        } else {
            _imageName = @"SmallCar_New";
        }
    }
    
    return self;
}

@end

#pragma mark - 车型审核模型

@interface ModelCertificationModel ()

@end

@implementation ModelCertificationModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _dataID = [[dic objectForKey:@"id"] integerValue];
        _status = [[dic objectForKey:@"status"] integerValue];
        _model = [dic objectForKey:@"model"];
        _desc = [dic objectForKey:@"desc"];
        
        if ([_model isEqualToString:@"大型车"]) {
            _imageName = @"LargeCar_New";
        } else if ([_model isEqualToString:@"中型车"]) {
            _imageName = @"MediumCar_New";
        } else {
            _imageName = @"SmallCar_New";
        }
    }
    
    return self;
}

@end

#pragma mark - 车型登记详情

@interface CarModelDetailModel ()

/// 车型id
@property (nonatomic, assign) NSInteger modelID;
/// 用户id
@property (nonatomic, assign) NSInteger userID;
/// 记录id
@property (nonatomic, assign) NSInteger dataID;

@end

@implementation CarModelDetailModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _coverUrl = [dic objectForKey:@"cover"];
        _backUrl = [dic objectForKey:@"back"];
        _model = [dic objectForKey:@"modelText"];
        _modelID = [[dic objectForKey:@"modelId"] integerValue];
        _status = [[dic objectForKey:@"checkStatus"] integerValue];
        _userID = [[dic objectForKey:@"userId"] integerValue];
        _dataID = [[dic objectForKey:@"id"] integerValue];

        if ([_model isEqualToString:@"大型车"]) {
            _imageName = @"LargeCar_New";
        } else if ([_model isEqualToString:@"中型车"]) {
            _imageName = @"MediumCar_New";
        } else {
            _imageName = @"SmallCar_New";
        }
    }
    
    return self;
}

@end

#pragma mark - 工商认证信息

@implementation CarWashCertificationModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _status = [dic objectForKey:@"authStatus"];
        _licenseUrl = [dic objectForKey:@"licenseImg"];
        _washCardUrl = [dic objectForKey:@"washCardImg"];
        _cardCoverUrl = [dic objectForKey:@"idCardCoverImg"];
        _cardBackUrl = [dic objectForKey:@"idCardBackImg"];
    }
    
    return self;
}

@end
