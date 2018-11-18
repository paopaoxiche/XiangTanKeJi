//
//  CommentViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CommentViewController.h"
#import "CommentCell.h"
#import "CommentListModel.h"
#import "UserManager.h"
#import "CarWashInfoModel.h"
#import "UIApplication+HUD.h"

@interface CommentViewController ()

@property (nonatomic, copy) NSArray *commentList;

@end

@implementation CommentViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _commentList = [[NSArray alloc] init];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [UIApplication showBusyHUD];
    BOOL isOwner = [UserManager sharedInstance].userType == UserTypeOwner;
    if (isOwner) {
        [CommentListModel loadCommentList:0 pageSize:20 result:^(NSArray *result, BOOL isSuccess) {
            [UIApplication stopBusyHUD];
            self.commentList = result;
            if (isSuccess) {
                if (result.count > 0) {
                    [self.tableView reloadData];
                } else {
                    // 提示无评价
                }
            }
            
            if (!isSuccess) {
                [self messageBox:@"评价请求失败"];
            }
        }];
    } else {
        NSInteger washID = [UserManager sharedInstance].carWashInfo.washID;
        [CommentListModel loadCommentList:washID pageIndex:0 pageSize:20 result:^(NSArray *result, BOOL isSuccess) {
            [UIApplication stopBusyHUD];
            self.commentList = result;
            if (isSuccess) {
                if (result.count > 0) {
                    [self.tableView reloadData];
                } else {
                    // 提示无评价
                }
            }
            
            if (!isSuccess) {
                [self messageBox:@"评价请求失败"];
            }
        }];
    }
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return _commentList.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    CommentModel *model = [_commentList objectAtIndex:indexPath.row];
    CommentCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CommentCell"];
    cell.avatarUrl = model.avatarUrl;
    cell.nickName = model.nickName;
    cell.commentTime = model.time;
    cell.content = model.content;
    cell.score = model.score;
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 140;
}

@end
