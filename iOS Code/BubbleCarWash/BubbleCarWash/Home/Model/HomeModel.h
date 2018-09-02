//
//  HomeModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef void(^ResultBlock)(NSArray *result);

@interface HomeDataModel : NSObject

+ (void)loadNearbyWashList:(ResultBlock)result;
+ (void)loadRecommendWashCommodity:(ResultBlock)result;

@end

@interface NearbyWashListModel : NSObject

/// 洗车场头像地址
@property (nonatomic, copy) NSString *avatarUrl;
/// 洗车场名称
@property (nonatomic, copy) NSString *carWashName;
/// 洗车场运营状态
@property (nonatomic, assign) NSInteger tradeState;
/// 荣誉值
@property (nonatomic, assign) NSInteger honor;
/// 服务最低价格
@property (nonatomic, assign) CGFloat price;
/// 接洗次数
@property (nonatomic, assign) NSInteger washCount;
/// 与洗车场间距离
@property (nonatomic, assign) NSInteger distance;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end

@class RecommendCommodityModel;

@interface RecommendWashModel : NSObject

/// 洗车场头像地址
@property (nonatomic, copy) NSString *avatarUrl;
/// 洗车场商品列表
@property (nonatomic, copy) NSArray <RecommendCommodityModel *>*commodityList;
/// 洗车场名称
@property (nonatomic, copy) NSString *carWashName;
/// 洗车场id
@property (nonatomic, assign) NSInteger washID;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end

@interface RecommendCommodityModel : NSObject

/// 洗车场id
@property (nonatomic, assign) NSInteger washID;
/// 商品id
@property (nonatomic, assign) NSInteger dataID;
/// 商品现价
@property (nonatomic, assign) CGFloat currentPrice;
/// 商品原价
@property (nonatomic, assign) CGFloat originPrice;
/// 商品图片地址
@property (nonatomic, copy) NSString *imageUrl;
/// 商品名称
@property (nonatomic, copy) NSString *commodityName;
/// 商品描述
@property (nonatomic, copy) NSString *describe;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end


