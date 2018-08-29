//
//  ModelCertificationCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/24.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ModelCertificationCell : UITableViewCell

@property (weak, nonatomic) IBOutlet UIImageView *carImgView;
@property (weak, nonatomic) IBOutlet UILabel *carType;
@property (weak, nonatomic) IBOutlet UILabel *carDetail;
@property (weak, nonatomic) IBOutlet UIImageView *verifyImgView;
@property (weak, nonatomic) IBOutlet UILabel *verifyLabel;

@end
