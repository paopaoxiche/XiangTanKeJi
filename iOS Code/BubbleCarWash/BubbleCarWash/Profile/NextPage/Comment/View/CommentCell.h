//
//  CommentCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CommentCell : UITableViewCell

@property (nonatomic, copy) NSString *avatarUrl;
@property (nonatomic, copy) NSString *nickName;
@property (nonatomic, copy) NSString *commentTime;
@property (nonatomic, copy) NSString *content;
@property (nonatomic, assign) NSInteger score;

@end
