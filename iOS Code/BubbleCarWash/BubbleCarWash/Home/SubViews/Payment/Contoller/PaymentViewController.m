//
//  PaymentViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/10.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "PaymentViewController.h"
#import "PaymentTypeCell.h"

@interface PaymentViewController () <UITableViewDataSource, PaymentTypeCellDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UILabel *totalAmountLabel;
@property (weak, nonatomic) IBOutlet UIButton *paymentBtn;
@property (nonatomic, strong) NSIndexPath *paymentTypeSelectedIndexPath;

@end

@implementation PaymentViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _paymentTypeSelectedIndexPath = [NSIndexPath indexPathForRow:0 inSection:0];
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 2;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    PaymentTypeCell *cell = [tableView dequeueReusableCellWithIdentifier:@"PaymentTypeIdentifier" forIndexPath:indexPath];
    cell.delegate = self;
    if (indexPath.row == 0) {
//        cell.typeImageName = @"";
        cell.name = @"微信";
        cell.isShowRecommend = YES;
        cell.selectImageName = @"SingleSelection_Selected";
    } else {
//        cell.typeImageName = @"";
        cell.name = @"支付宝";
        cell.isShowRecommend = NO;
        cell.selectImageName = @"SingleSelection_Normal";
    }
    return cell;
}

#pragma mark - PaymentTypeCellDelegate

- (void)selectedPaymentTypeCell:(PaymentTypeCell *)cell {
    cell.selectImageName = @"SingleSelection_Selected";
    
    PaymentTypeCell *selectedCell = [self.tableView cellForRowAtIndexPath:_paymentTypeSelectedIndexPath];
    selectedCell.selectImageName = @"SingleSelection_Normal";
    
    _paymentTypeSelectedIndexPath = [self.tableView indexPathForCell:cell];
}

@end
