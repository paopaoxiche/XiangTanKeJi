//
//  CommentListModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/2.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef void(^ResultBlock)(NSArray *result, BOOL isSuccess);

@interface CommentListModel : NSObject

+ (void)loadCommentList:(NSInteger)pageIndex pageSize:(NSInteger)pageSize result:(ResultBlock)block;
+ (void)loadCommentList:(NSInteger)washID pageIndex:(NSInteger)pageIndex pageSize:(NSInteger)pageSize result:(ResultBlock)block;

@end

@interface CommentModel : NSObject

/// 车主头像
@property (nonatomic, copy) NSString *avatarUrl;
/// 评价内容
@property (nonatomic, copy) NSString *content;
/// 车主昵称
@property (nonatomic, copy) NSString *nickName;
/// 评价时间
@property (nonatomic, copy) NSString *time;
/// 评分
@property (nonatomic, assign) NSInteger score;
/// 该条记录id
@property (nonatomic, assign) NSInteger commentID;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end
