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
    
    self.title = [UserManager sharedInstance].userType == UserTypeOwner ? @"我的评价" : @"评价";
    self.tableView.tableFooterView = [[UIView alloc] init];
    _commentList = [[NSArray alloc] init];
    
    self.navigationItem.leftBarButtonItem = [[UIBarButtonItem alloc] initWithImage:[UIImage imageNamed:@"BackArrowBlack"] style:UIBarButtonItemStylePlain target:self action:@selector(backToSuperVC)];
    [self.navigationController.navigationBar setTintColor:[UIColor blackColor]];
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName : [UIColor blackColor]}];
}

- (void)backToSuperVC {
    [self.navigationController popViewControllerAnimated:YES];
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    
    [self.navigationController setNavigationBarHidden:NO animated:YES];
    
    [UIApplication showBusyHUD];
    BOOL isOwner = [UserManager sharedInstance].userType == UserTypeOwner;
    if (isOwner) {
        [CommentListModel loadCommentList:0 pageSize:1000 result:^(NSArray *result, BOOL isSuccess) {
            [UIApplication stopBusyHUD];
            self.commentList = result;
            if (isSuccess) {
                if (result.count > 0) {
                    [self.tableView reloadData];
                } else {
                    [self messageBox:@"暂无评价"];
                }
            }
            
            if (!isSuccess) {
                [self messageBox:@"评价请求失败"];
            }
        }];
    } else {
        NSInteger washID = [UserManager sharedInstance].carWashInfo.washID;
        [CommentListModel loadCommentList:washID pageIndex:0 pageSize:1000 result:^(NSArray *result, BOOL isSuccess) {
            [UIApplication stopBusyHUD];
            self.commentList = result;
            if (isSuccess) {
                if (result.count > 0) {
                    [self.tableView reloadData];
                } else {
                    [self messageBox:@"暂无评价"];
                }
            }
            
            if (!isSuccess) {
                [self messageBox:@"评价请求失败"];
            }
        }];
    }
}

- (CGSize)contentSize:(NSString *)content {
    UILabel *contentLabel = [[UILabel alloc] init];
    contentLabel.font = [UIFont systemFontOfSize:16];
    contentLabel.lineBreakMode = NSLineBreakByWordWrapping;
    contentLabel.numberOfLines = 0;
    contentLabel.text = content;
    
    return [contentLabel sizeThatFits:CGSizeMake(self.tableView.frame.size.width - 100, 1000)];
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return _commentList.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    CommentModel *model = [_commentList objectAtIndex:indexPath.row];
    CommentCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CommentCell"];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    cell.avatarUrl = model.avatarUrl;
    cell.nickName = model.nickName;
    cell.commentTime = model.time;
    cell.content = model.content;
    cell.score = model.score;
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    CommentModel *model = [_commentList objectAtIndex:indexPath.row];
    CGSize size = [self contentSize:model.content];
    return 80 + size.height + 16;
}

@end
