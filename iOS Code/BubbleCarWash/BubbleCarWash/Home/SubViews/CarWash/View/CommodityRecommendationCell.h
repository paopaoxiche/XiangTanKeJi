//
//  CommodityRecommendationCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/12.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CommodityRecommendationCell : UICollectionViewCell

@property (nonatomic, copy) NSString *photoUrl;
@property (nonatomic, assign) CGFloat currentPrice;
@property (nonatomic, assign) CGFloat originalPrice;

@end
