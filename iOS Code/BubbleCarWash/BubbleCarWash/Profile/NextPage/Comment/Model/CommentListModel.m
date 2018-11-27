//
//  CommentListModel.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/2.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CommentListModel.h"
#import "GlobalMethods.h"
#import "UserManager.h"
#import "NetworkTools.h"

@interface CommentListModel ()

@end

@implementation CommentListModel

+ (void)loadCommentList:(NSInteger)pageIndex pageSize:(NSInteger)pageSize result:(ResultBlock)block {
    [NetworkTools obtainEvaluateListWithPageIndex:pageIndex pageSize:pageSize success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200) {
            if ([response objectForKey:@"data"] != [NSNull null]) {
                NSDictionary *dataArr = [response objectForKey:@"data"];
                NSMutableArray *commentList = [NSMutableArray arrayWithCapacity:dataArr.count];
                for (NSDictionary *dic in dataArr) {
                    CommentModel *model = [[CommentModel alloc] initWithDic:dic];
                    [commentList addObject:model];
                }
                
                block(commentList, YES);
            } else {
                block(@[], YES);
            }
        } else {
            block(@[], NO);
        }
    } failed:^(NSError *error) {
        block(@[], NO);
    }];
}

+ (void)loadCommentList:(NSInteger)washID pageIndex:(NSInteger)pageIndex pageSize:(NSInteger)pageSize result:(ResultBlock)block {
    [NetworkTools obtainCarWashComment:washID pageIndex:pageIndex pageSize:pageSize success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200) {
            if ([response objectForKey:@"data"] != [NSNull null]) {
                NSDictionary *dataArr = [response objectForKey:@"data"];
                NSMutableArray *commentList = [NSMutableArray arrayWithCapacity:dataArr.count];
                for (NSDictionary *dic in dataArr) {
                    CommentModel *model = [[CommentModel alloc] initWithDic:dic];
                    [commentList addObject:model];
                }
                
                block(commentList, YES);
            } else {
                block(@[], YES);
            }
        } else {
            block(@[], NO);
        }
    } failed:^(NSError *error) {
        block(@[], NO);
    }];
}

@end

@interface CommentModel ()

@end

@implementation CommentModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _avatarUrl = [dic objectForKey:@"avatar"];
        _content = [dic objectForKey:@"content"];
        _nickName = [dic objectForKey:@"nickname"];
        _time = [GlobalMethods conversionTimestampToStr:[[dic objectForKey:@"time"] longValue]
                                             dateFormat:@"yyyy.MM.dd"];
        _commentID = [[dic objectForKey:@"id"] integerValue];
        _score = [[dic objectForKey:@"rating"] integerValue];
    }
    
    return self;
}

@end
