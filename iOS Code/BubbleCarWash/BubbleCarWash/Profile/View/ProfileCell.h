//
//  ProfileCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ProfileCell : UITableViewCell

@property (weak, nonatomic) IBOutlet UIImageView *imgView;
@property (weak, nonatomic) IBOutlet UILabel *cusTextLabel;
@property (weak, nonatomic) IBOutlet UIView *upgradeView;
@property (weak, nonatomic) IBOutlet UILabel *cusDetailTextLabel;
@property (weak, nonatomic) IBOutlet UIImageView *accessoryArrow;

@end
