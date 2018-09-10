//
//  ModelCertificationCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/24.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ModelCertificationCell : UITableViewCell

/// 车型
@property (nonatomic, copy) NSString *model;
/// 车型描述
@property (nonatomic, copy) NSString *desc;
/// 认证状态
@property (nonatomic, assign) NSInteger status;

@end
