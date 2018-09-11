//
//  PaymentViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/10.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "PaymentViewController.h"
#import "PaymentTypeCell.h"
#import "NetworkTools.h"
#import "CreateOrderModel.h"
#import "GlobalMethods.h"

@interface PaymentViewController () <UITableViewDataSource, PaymentTypeCellDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UILabel *totalAmountLabel;
@property (weak, nonatomic) IBOutlet UIButton *paymentBtn;
@property (nonatomic, strong) NSIndexPath *paymentTypeSelectedIndexPath;
@property (nonatomic, strong) CreateOrderModel *orderModel;

@end

@implementation PaymentViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _paymentTypeSelectedIndexPath = [NSIndexPath indexPathForRow:0 inSection:0];
}

- (IBAction)createOrder:(id)sender {
    CreateOrderParam *params = [[CreateOrderParam alloc] init];
    params.washServiceId = _serviceID;
    params.commoditys = _commoditys;
    params.payType = [NSString stringWithFormat:@"%li", (_paymentTypeSelectedIndexPath.row + 1)];
    [[NetworkTools sharedInstance] createOrder:params success:^(NSDictionary *response, BOOL isSuccess) {
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSDictionary *data = [response objectForKey:@"data"];
            self.orderModel = [[CreateOrderModel alloc] initWithDic:data];
            
            UIViewController *vc = [GlobalMethods viewControllerWithBuddleName:@"Payment" vcIdentifier:@"PaymentSuccessVC"];
            [self.navigationController pushViewController:vc animated:YES];
        } else {
            [self messageBox:@"创建订单失败，请稍后重试"];
        }
    } failed:^(NSError *error) {
        [self messageBox:@"创建订单失败，请稍后重试"];
    }];
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
