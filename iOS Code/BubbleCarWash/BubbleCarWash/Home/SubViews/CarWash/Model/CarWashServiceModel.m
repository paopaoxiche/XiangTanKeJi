//
//  CarWashServiceModel.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/9.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CarWashServiceModel.h"
#import "NetworkTools.h"
#import "AuthenticationModel.h"
#import "HomeModel.h"

@interface CarWashServiceModel ()

@end

@implementation CarWashServiceModel

+ (void)loadCarWashServiceData:(NSInteger)washID result:(ServiceResultBlock)result {
    dispatch_group_t group = dispatch_group_create();
    
    dispatch_group_enter(group);
    __block NSArray *modelCertificationList = @[];
    [AuthenticationModel loadModelCertificationList:1 result:^(NSArray *result) {
        modelCertificationList = result;
        dispatch_group_leave(group);
    }];
    
    dispatch_group_enter(group);
    __block NSArray *commodityList = @[];
    [[NetworkTools sharedInstance] obtainCarWashCommodityList:washID success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSDictionary *dataArr = [response objectForKey:@"data"];
            NSMutableArray *list = [NSMutableArray arrayWithCapacity:dataArr.count];
            for (NSDictionary *dic in dataArr) {
                RecommendCommodityModel *model = [[RecommendCommodityModel alloc] initWithDic:dic];
                [list addObject:model];
            }
            
            commodityList = list;
            dispatch_group_leave(group);
        } else {
            dispatch_group_leave(group);
        }
    } failed:^(NSError *error) {
        dispatch_group_leave(group);
    }];
    
    dispatch_group_enter(group);
    __block NSArray *serviceList = @[];
    [[NetworkTools sharedInstance] obtainCarWashServiceList:washID success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSDictionary *dataArr = [response objectForKey:@"data"];
            NSMutableArray *list = [NSMutableArray arrayWithCapacity:dataArr.count];
            for (NSDictionary *dic in dataArr) {
                ServiceModel *model = [[ServiceModel alloc] initWithDic:dic];
                [list addObject:model];
            }
            
            serviceList = list;
            dispatch_group_leave(group);
        } else {
            dispatch_group_leave(group);
        }
    } failed:^(NSError *error) {
        dispatch_group_leave(group);
    }];
    
    dispatch_group_notify(group, dispatch_get_main_queue(), ^{
        NSMutableArray *dataSource = [[NSMutableArray alloc] initWithCapacity:3];
        [dataSource addObject:@{@"ServiceList": (serviceList.count > 0 ? serviceList : @[])}];
        [dataSource addObject:@{@"ModelCertificationList": (modelCertificationList.count > 0 ? modelCertificationList : @[])}];
        [dataSource addObject:@{@"CommodityList": (commodityList.count > 0 ? commodityList : @[])}];
        result(dataSource);
    });
}

@end

@interface ServiceModel ()

@property (nonatomic, assign) NSInteger carModel;
@property (nonatomic, assign) NSInteger carWashID;
@property (nonatomic, assign) BOOL isActive;

@end

@implementation ServiceModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _carWashName = [dic objectForKey:@"name"];
        _desc = [dic objectForKey:@"describe"];
        _price = [[dic objectForKey:@"price"] floatValue];
        _carModel = [[dic objectForKey:@"carModel"] integerValue];
        _carWashID = [[dic objectForKey:@"carWashId"] integerValue];
        _dataID = [[dic objectForKey:@"id"] integerValue];
        _isActive = [[dic objectForKey:@"isActive"] boolValue];
    }
    
    return self;
}

@end
