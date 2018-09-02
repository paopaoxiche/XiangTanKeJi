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
    
    [CommentListModel loadCommentList:^(NSArray *result) {
        self.commentList = result;
        [self.tableView reloadData];
    }];
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
