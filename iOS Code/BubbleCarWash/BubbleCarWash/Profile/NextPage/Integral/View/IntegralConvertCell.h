//
//  IntegralConvertCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@class IntegralConvertCell;

@protocol IntegralConvertCellDelegate <NSObject>

- (void)onClickedConvertButtonnAtIndex:(NSInteger)index;

@end

@interface IntegralConvertCell : UITableViewCell

@property (nonatomic, weak) id<IntegralConvertCellDelegate> delegate;
@property (nonatomic, copy) NSString *noteSum;
@property (nonatomic, copy) NSString *couponType;
@property (nonatomic, copy) NSString *couponDesc;
@property (nonatomic, copy) NSString *validityPeroid;
@property (nonatomic, copy) NSString *integral;
@property (nonatomic, assign) NSInteger index;

@end
