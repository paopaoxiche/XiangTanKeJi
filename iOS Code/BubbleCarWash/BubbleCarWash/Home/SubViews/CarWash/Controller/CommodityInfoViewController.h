//
//  CommodityInfoViewController.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/11/18.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@class RecommendCommodityModel;
@class RecommendWashModel;

@interface CommodityInfoViewController : UIViewController

@property (nonatomic, strong) RecommendCommodityModel *model;
@property (nonatomic, strong) RecommendWashModel *washModel;

@end
