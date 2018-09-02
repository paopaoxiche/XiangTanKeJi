//
//  CommentCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CommentCell.h"
#import <SDWebImage/UIImageView+WebCache.h>

@interface CommentCell ()

@property (weak, nonatomic) IBOutlet UIImageView *avatar;           // 车主头像
@property (weak, nonatomic) IBOutlet UILabel *userName;             // 用户名
@property (weak, nonatomic) IBOutlet UIImageView *star1;
@property (weak, nonatomic) IBOutlet UIImageView *star2;
@property (weak, nonatomic) IBOutlet UIImageView *star3;
@property (weak, nonatomic) IBOutlet UIImageView *star4;
@property (weak, nonatomic) IBOutlet UIImageView *star5;
@property (weak, nonatomic) IBOutlet UILabel *scoreLabel;           // 评分
@property (weak, nonatomic) IBOutlet UILabel *evaluationTime;       // 评价时间
@property (weak, nonatomic) IBOutlet UILabel *evaluationContent;    // 评价内容


@end

@implementation CommentCell

- (void)setAvatarUrl:(NSString *)avatarUrl {
    [_avatar sd_setImageWithURL:[NSURL URLWithString:avatarUrl]
               placeholderImage:[UIImage imageNamed:@"CarWashAvatar"]];
}

- (void)setNickName:(NSString *)nickName {
    _userName.text = nickName;
}

- (void)setCommentTime:(NSString *)commentTime {
    _evaluationTime.text = commentTime;
}

- (void)setContent:(NSString *)content {
    _evaluationContent.text = content;
    
}

- (void)setScore:(NSInteger)score {
    _scoreLabel.text = [NSString stringWithFormat:@"%li分", score];
    for (int i = 0; i < score; i++) {
        UIImageView *star = [self viewWithTag:i + 100];
        star.image = [UIImage imageNamed:@"StartHighlighted"];
    }
    
    for (int i = 4; i >= score; i--) {
        UIImageView *star = [self viewWithTag:i + 100];
        star.image = [UIImage imageNamed:@"StartNormal"];
    }
}

@end
