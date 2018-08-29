//
//  CommentCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CommentCell.h"

@interface CommentCell ()

@property (weak, nonatomic) IBOutlet UIImageView *avatar;           // 车主头像
@property (weak, nonatomic) IBOutlet UILabel *userName;             // 用户名
@property (weak, nonatomic) IBOutlet UIImageView *start1;
@property (weak, nonatomic) IBOutlet UIImageView *start2;
@property (weak, nonatomic) IBOutlet UIImageView *start3;
@property (weak, nonatomic) IBOutlet UIImageView *start4;
@property (weak, nonatomic) IBOutlet UIImageView *start5;
@property (weak, nonatomic) IBOutlet UILabel *scoreLabel;           // 评分
@property (weak, nonatomic) IBOutlet UILabel *evaluationTime;       // 评价时间
@property (weak, nonatomic) IBOutlet UILabel *evaluationContent;    // 评价内容


@end

@implementation CommentCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
