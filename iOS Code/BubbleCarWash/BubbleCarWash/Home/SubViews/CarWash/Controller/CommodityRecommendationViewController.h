//
//  CommodityRecommendationViewController.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/7.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol CommodityCellProtocol <NSObject>

- (void)didSelectedCommodityCellWithIndex:(NSInteger)index;

@end

@interface CommodityRecommendationViewController : UIViewController

@property (nonatomic, copy) NSArray *recommendWashCommodity;
@property (nonatomic, weak) id<CommodityCellProtocol> delegate;

@end
