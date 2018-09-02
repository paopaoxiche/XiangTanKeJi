//
//  CommentListModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/2.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef void(^ResultBlock)(NSArray *result);

@interface CommentListModel : NSObject

+ (void)loadCommentList:(ResultBlock)block;

@end

@interface CommentModel : NSObject

@property (nonatomic, copy) NSString *avatarUrl;
@property (nonatomic, copy) NSString *content;
@property (nonatomic, copy) NSString *nickName;
@property (nonatomic, copy) NSString *time;
@property (nonatomic, assign) NSInteger score;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end
