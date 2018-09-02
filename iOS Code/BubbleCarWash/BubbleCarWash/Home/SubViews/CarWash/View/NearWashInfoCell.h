//
//  NearWashInfoCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface NearWashInfoCell : UITableViewCell

@property (nonatomic, copy) NSString *avatarUrl;
@property (nonatomic, copy) NSString *name;
@property (nonatomic, assign) CGFloat lowestPrice;
@property (nonatomic, assign) NSInteger honor;
@property (nonatomic, assign) NSInteger washNumber;
@property (nonatomic, assign) NSInteger distance;

@end
