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
#import "UserManager.h"
#import "NetworkTools.h"
#import "CarWashInfoModel.h"

@interface CommodityRecommendationViewController () <UICollectionViewDataSource, UICollectionViewDelegate>

@property (weak, nonatomic) IBOutlet LYPageControl *pageControl;
@property (weak, nonatomic) IBOutlet UIImageView *washAvatar;
@property (weak, nonatomic) IBOutlet UILabel *recommendNameLabel;
@property (weak, nonatomic) IBOutlet UICollectionView *collectionView;
@property (weak, nonatomic) IBOutlet CommodityHorizontalFlowLayout *flowLayout;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *commodityViewTopConstraint;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *viewHeightConstraint;
@property (weak, nonatomic) IBOutlet UILabel *emptyLabel;

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
    
    _flowLayout.column = 3;
    if ([UserManager sharedInstance].userType == UserTypeOwner) {
        _flowLayout.row = 2;
        _flowLayout.total = _recommendWashCommodity.count * 6;
    } else {
        _flowLayout.row = _recommendWashCommodity.count < 4 ? 1 : 2;
        _flowLayout.total = _flowLayout.row * 3;
        _commodityViewTopConstraint.constant = 0;
        _viewHeightConstraint.constant = _recommendWashCommodity.count < 4 ? 210 : 370;
    }
}

- (void)setRecommendWashCommodity:(NSArray *)recommendWashCommodity {
    _recommendWashCommodity = recommendWashCommodity;
    
    if (_recommendWashCommodity.count <= 0) {
        return;
    }
    
    BOOL isCarWash = [UserManager sharedInstance].userType == UserTypeCarWash;
    if (isCarWash) {
        _pageControl.hidden = YES;
        _recommendNameLabel.text = @"商品展示";
        BOOL isEmpty = _recommendWashCommodity.count == 0;
        _collectionView.hidden = isEmpty;
        _emptyLabel.hidden = !isEmpty;
        if (isEmpty) {
            _emptyLabel.text = @"暂无商品可展示，请添加商品";
        }
    } else {
        RecommendWashModel *model = _recommendWashCommodity[0];
        _recommendNameLabel.text = [NSString stringWithFormat:@"%@商品推荐", model.carWashName];
    }
    
    _pageControl.numberOfPages = recommendWashCommodity.count;
    _collectionView.contentSize = CGSizeMake(([UIScreen mainScreen].bounds.size.width - 12 * 2) * recommendWashCommodity.count, 250);
    [self.view layoutIfNeeded];
    [_collectionView reloadData];
}

#pragma mark - UICollectionViewDataSource

- (NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView {
    return [UserManager sharedInstance].userType ==  UserTypeOwner ? _recommendWashCommodity.count : 1;
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section {
    if ([UserManager sharedInstance].userType == UserTypeOwner) {
        RecommendWashModel *washModel = _recommendWashCommodity[section];
        return washModel.commodityList.count;
    }
    
    return _recommendWashCommodity.count > 6 ? 6 : _recommendWashCommodity.count;
}

- (__kindof UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath {
    CommodityRecommendationCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"Cell" forIndexPath:indexPath];
    RecommendCommodityModel *commodityModel;
    if ([UserManager sharedInstance].userType == UserTypeOwner) {
        RecommendWashModel *washModel = _recommendWashCommodity[indexPath.section];
        commodityModel = washModel.commodityList[indexPath.item];
    } else {
        commodityModel = _recommendWashCommodity[indexPath.item];
    }
    cell.photoUrl = commodityModel.imageUrl;
    cell.currentPrice = commodityModel.currentPrice;
    cell.originalPrice = commodityModel.originPrice;
    
    return cell;
}

#pragma mark - UICollectionViewDelegate

- (void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath {
    if ([UserManager sharedInstance].userType == UserTypeOwner) {
        RecommendWashModel *model = self.recommendWashCommodity[indexPath.section];
        if (indexPath.item >= model.commodityList.count) {
            return;
        }
    } else {
        if (indexPath.item  >= self.recommendWashCommodity.count) {
            return;
        }
    }
    if (_delegate) {
        [_delegate didSelectedCommodityCellWithIndex:indexPath.item section:indexPath.section];
    }
}

#pragma mark - UIScrollViewDelegate

- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView {
    [self setScrollView];
}

- (void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate {
    [self setScrollView];
}

- (void)setScrollView {
    CGFloat distance = self.collectionView.frame.size.width;
    CGFloat offsetX = self.collectionView.contentOffset.x;
    CGFloat width = self.collectionView.contentSize.width;
    int offset = offsetX + distance * 0.5;
    int viewIndex = offset / distance;              // viewIndex滑动后所处的第一个小视频索引
    int totalIndex = width / distance;              // 计算总的店数
    if (viewIndex < 0) {
        viewIndex = 0;
    }
    if (viewIndex > totalIndex) {
        viewIndex = totalIndex;
    }
    offset = viewIndex * distance;
    [self.collectionView setContentOffset:CGPointMake(offset, 0) animated:YES];
    self.pageControl.currentPage = viewIndex;
    RecommendWashModel *model = _recommendWashCommodity[viewIndex];
    _recommendNameLabel.text = [NSString stringWithFormat:@"%@商品推荐", model.carWashName];
}

@end
