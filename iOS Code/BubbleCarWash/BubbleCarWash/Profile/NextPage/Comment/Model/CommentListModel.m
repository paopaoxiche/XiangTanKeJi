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

+ (void)loadCommentList:(ResultBlock)block {
    [[NetworkTools sharedInstance] obtainEvaluateListWithAuthentication:[UserManager sharedInstance].authentication pageIndex:0 pageSize:20 success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSDictionary *dataArr = [response objectForKey:@"data"];
            NSMutableArray *commentList = [NSMutableArray arrayWithCapacity:dataArr.count];
            for (NSDictionary *dic in dataArr) {
                CommentModel *model = [[CommentModel alloc] initWithDic:dic];
                    [commentList addObject:model];
            }
            
            block(commentList);
        } else {
            block(@[]);
        }
    } failed:^(NSError *error) {
        block(@[]);
    }];
}

@end

@interface CommentModel ()

@property (nonatomic, assign) NSInteger commentID;

@end

@implementation CommentModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _avatarUrl = [dic objectForKey:@"avatar"];
        _content = [dic objectForKey:@"content"];
        _nickName = [dic objectForKey:@"nickname"];
        _time = [GlobalMethods conversionTimestampToStr:[dic objectForKey:@"time"]
                                             dateFormat:@"yyyy.MM.dd"];
        _commentID = [[dic objectForKey:@"id"] integerValue];
        _score = [[dic objectForKey:@"rating"] integerValue];
    }
    
    return self;
}

@end
