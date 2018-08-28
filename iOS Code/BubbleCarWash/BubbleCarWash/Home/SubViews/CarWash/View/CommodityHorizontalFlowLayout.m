//
//  CommodityHorizontalFlowLayout.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/12.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CommodityHorizontalFlowLayout.h"

static CGFloat kTopInset = 16;
static CGFloat kLeftInset = 16;
static CGFloat kItemWidth = 95;
static CGFloat kItemHeight = 145;

@interface CommodityHorizontalFlowLayout ()

@property (nonatomic, strong) NSMutableArray *allAttributes;    // 存储所有item的布局属性

@end

@implementation CommodityHorizontalFlowLayout

- (void)prepareLayout {
    [super prepareLayout];
    
    if (self.allAttributes.count) {
        [_allAttributes removeAllObjects];
    }
    
    CGRect frame = self.collectionView.frame;
    // 计算行、列间距
    kItemWidth = (frame.size.width - 2 * kLeftInset - (_column - 1) * 18) / _column;
    kItemHeight = kItemWidth + 50;
    
    self.scrollDirection = UICollectionViewScrollDirectionHorizontal;
    self.minimumLineSpacing = 18;
    self.minimumInteritemSpacing = 16;
    self.itemSize = CGSizeMake(kItemWidth, kItemHeight);
    
    NSUInteger section = [self.collectionView numberOfSections];
    for (int i = 0; i < section; i++) {
        NSUInteger items = [self.collectionView numberOfItemsInSection:i];
        for (int item = 0; item < items; item++) {
            // 获取每一个item的布局属性
            NSIndexPath *indexPath = [NSIndexPath indexPathForItem:item inSection:i];
            UICollectionViewLayoutAttributes *itemAttribute = [self layoutAttributesForItemAtIndexPath:indexPath];
            [_allAttributes addObject:itemAttribute];
        }
    }
}

- (CGSize)collectionViewContentSize {
    if (_row > 0 && _column > 0 && _total > 0) {
        CGFloat page = ceilf(_total / (_row * _column));
        CGSize size = self.collectionView.frame.size;
        return CGSizeMake(size.width * page, size.height);
    }
    
    return [super collectionViewContentSize];
}

- (nullable UICollectionViewLayoutAttributes *)layoutAttributesForItemAtIndexPath:(NSIndexPath *)indexPath {
    UICollectionViewLayoutAttributes *attribute = [super layoutAttributesForItemAtIndexPath:indexPath];
    CGFloat collectionViewWidth = self.collectionView.frame.size.width;
    NSUInteger item = indexPath.item;
    NSUInteger row = item / _column;
    NSUInteger cloumn = item % _column;
    // 每个item的 x = leftInset + cloumn *（列间 + itemW）+ 每页宽度
    CGFloat x = kLeftInset + cloumn * (self.minimumInteritemSpacing + kItemWidth) + indexPath.section * collectionViewWidth;
    // 每个item的 y = topInset + row *（行间 + itemW）
    CGFloat y = kTopInset + row * (self.minimumLineSpacing + kItemHeight);
    attribute.frame = CGRectMake(x, y, kItemWidth, kItemHeight);
    
    return attribute;
}

// 返回可视视图的rect
- (nullable NSArray<__kindof UICollectionViewLayoutAttributes *> *)layoutAttributesForElementsInRect:(CGRect)rect {
    return _allAttributes;
}

- (BOOL)shouldInvalidateLayoutForBoundsChange:(CGRect)newBounds {
    return YES;
}

- (NSMutableArray *)allAttributes {
    if (!_allAttributes) {
        _allAttributes = [[NSMutableArray alloc] initWithCapacity:0];
    }
    
    return _allAttributes;
}

@end
