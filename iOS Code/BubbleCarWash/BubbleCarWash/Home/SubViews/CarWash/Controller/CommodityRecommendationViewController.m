//
//  CommodityRecommendationViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/7.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CommodityRecommendationViewController.h"
#import "LYPageControl.h"
#import "CommodityRecommendationCell.h"
#import "CommodityHorizontalFlowLayout.h"
#import "HomeModel.h"

@interface CommodityRecommendationViewController () <UICollectionViewDataSource, UICollectionViewDelegate>

@property (weak, nonatomic) IBOutlet LYPageControl *pageControl;
@property (weak, nonatomic) IBOutlet UIImageView *washAvatar;
@property (weak, nonatomic) IBOutlet UILabel *recommendNameLabel;
@property (weak, nonatomic) IBOutlet UICollectionView *collectionView;
@property (weak, nonatomic) IBOutlet CommodityHorizontalFlowLayout *flowLayout;

@end

@implementation CommodityRecommendationViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _collectionView.contentSize = CGSizeMake(([UIScreen mainScreen].bounds.size.width - 12 * 2) * 3, 250);
    
    // 将点设为图片
    [_pageControl setValue:[UIImage imageNamed:@"CurrentPage"] forKey:@"_currentPageImage"];
    [_pageControl setValue:[UIImage imageNamed:@"NormalPage"] forKey:@"_pageImage"];
}

- (void)viewWillLayoutSubviews {
    [super viewWillLayoutSubviews];
    
    _flowLayout.row = 2;
    _flowLayout.column = 3;
    _flowLayout.total = 18;
}

- (void)setRecommendWashCommodity:(NSArray *)recommendWashCommodity {
    _recommendWashCommodity = recommendWashCommodity;
    _pageControl.numberOfPages = recommendWashCommodity.count;
//    _collectionView.contentSize = CGSizeMake(([UIScreen mainScreen].bounds.size.width - 12 * 2) * recommendWashCommodity.count, 250);
//    [self.view layoutIfNeeded];
    [_collectionView reloadData];
}

#pragma mark - UICollectionViewDataSource

- (NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView {
    return _recommendWashCommodity.count;
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section {
    return 6;
}

- (__kindof UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath {
    CommodityRecommendationCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"Cell" forIndexPath:indexPath];
    RecommendWashModel *washModel = _recommendWashCommodity[indexPath.section];
    RecommendCommodityModel *commodityModel = washModel.commodityList[indexPath.item];
    cell.photoUrl = commodityModel.imageUrl;
    cell.currentPrice = commodityModel.currentPrice;
    cell.originalPrice = commodityModel.originPrice;
    
    return cell;
}

@end
