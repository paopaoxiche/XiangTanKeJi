//
//  HomeModel.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "HomeModel.h"
#import "NetworkTools.h"
#import "GlobalMethods.h"
#import "UserManager.h"

@implementation HomeDataModel

+ (void)loadNearbyWashList:(Location)location isMap:(BOOL)isMap result:(HomeResultBlock)result {
    NearbyWashListParam *param = [[NearbyWashListParam alloc] init];
    param.lng = [NSNumber numberWithFloat:location.lng];
    param.lat = [NSNumber numberWithFloat:location.lat];
    
    if (isMap) {
        param.count = 0;
        param.showAll = 1;
        param.radius = 2;
    }
    
    [[NetworkTools sharedInstance] obtainNearbyWashList:param success:^(NSDictionary *response, BOOL isSuccess) {
        if ([response objectForKey:@"data"] == [NSNull null]) {
            return result(@[]);
        }
        
        NSArray *list = response[@"data"];
        if (isSuccess && list && list.count <= 0) {
            result(@[]);
        }
        
        NSMutableArray *washList = [NSMutableArray arrayWithCapacity:list.count];
        for (NSDictionary *dic in list) {
            NearbyWashListModel *model = [[NearbyWashListModel alloc] initWithDic:dic];
            [washList addObject:model];
        }
        result(washList);
    } failed:^(NSError *error) {
        result(@[]);
    }];
}

+ (void)loadRecommendWashCommodity:(HomeResultBlock)result {
    [[NetworkTools sharedInstance] obtainRecommendCommodity:6 longitude:[NSNumber numberWithFloat:113.89] latitude:[NSNumber numberWithFloat:22.567] success:^(NSDictionary *response, BOOL isSuccess) {
        if ([response objectForKey:@"data"] == [NSNull null]) {
            return result(@[]);
        }
        
        NSArray *data = response[@"data"];
        if (!isSuccess || !data || data.count <= 0) {
            result(@[]);
        }
        
        NSMutableArray *washDatas = [NSMutableArray arrayWithCapacity:data.count];
        for (NSDictionary *dic in data) {
            RecommendWashModel *model = [[RecommendWashModel alloc] initWithDic:dic];
            if (model.washID != 0) {
                [washDatas addObject:model];
            }
        }
        result(washDatas);
    } failed:^(NSError *error) {
        result(@[]);
    }];
}

@end

@interface NearbyWashListModel ()

/// id
@property (nonatomic, assign) NSInteger dataID;

@end

@implementation NearbyWashListModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _address = [dic objectForKey:@"address"];
        _tradeState = [[dic objectForKey:@"businessStatus"] integerValue];
        _distance = [[dic objectForKey:@"distance"] integerValue];
        _honor = [[dic objectForKey:@"honor"] integerValue];
        _dataID = [[dic objectForKey:@"id"] integerValue];
        _avatarUrl = [dic objectForKey:@"image"];
        _location.lat = [[dic objectForKey:@"lat"] floatValue];
        _location.lng = [[dic objectForKey:@"lng"] floatValue];
        _carWashName = [dic objectForKey:@"name"];
        _price = [[dic objectForKey:@"price"] floatValue];
        _washCount = [[dic objectForKey:@"washCount"] integerValue];
        _washID = [[dic objectForKey:@"washId"] integerValue];
    }
    
    return self;
}

- (NSUInteger)distance {
    return [GlobalMethods calculateDistanceWithLocation:_location localLocation:[UserManager sharedInstance].location];
}

@end

#pragma mark - 商品推荐的洗车场

@interface RecommendWashModel ()

@end

@implementation RecommendWashModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        NSArray *list = [dic objectForKey:@"list"];
        if (list.count > 0) {
            _avatarUrl = [dic objectForKey:@"avatar"];
            _carWashName = [dic objectForKey:@"name"];
            
            if ([dic objectForKey:@"washId"]) {
                _washID = [[dic objectForKey:@"washId"] integerValue];
            }
            
            NSMutableArray *commoditys = [NSMutableArray arrayWithCapacity:list.count];
            for (NSDictionary *tempDic in list) {
                RecommendCommodityModel *commodityModel = [[RecommendCommodityModel alloc] initWithDic:tempDic];
                [commoditys addObject:commodityModel];
            }
            _commodityList = [commoditys copy];
        }
    }
    
    return self;
}

@end

#pragma mark - 洗车场推荐的商品

@interface RecommendCommodityModel ()

@end

@implementation RecommendCommodityModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _currentPrice = [[dic objectForKey:@"currentPrice"] floatValue];
        _describe = [dic objectForKey:@"describe"];
        _dataID = [[dic objectForKey:@"id"] integerValue];
        _imageUrl = [dic objectForKey:@"image"];
        _commodityName = [dic objectForKey:@"name"];
        _originPrice = [[dic objectForKey:@"originPrice"] floatValue];
        
        if ([dic objectForKey:@"washId"]) {
            _washID = [[dic objectForKey:@"washId"] integerValue];
        }
        _isSelected = NO;
    }
    
    return self;
}

@end
