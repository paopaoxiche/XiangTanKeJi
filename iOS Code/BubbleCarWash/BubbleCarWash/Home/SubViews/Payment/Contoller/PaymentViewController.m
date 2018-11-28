//
//  PaymentViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/10.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <AlipaySDK/AlipaySDK.h>

#import "PaymentViewController.h"
#import "PaymentTypeCell.h"
#import "NetworkTools.h"
#import "CreateOrderModel.h"
#import "GlobalMethods.h"
#import "WXApi.h"
#import "WXApiManager.h"
#import "AlipayManager.h"
#import "UIApplication+HUD.h"

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
    _totalAmountLabel.text = self.totalAmount;
}

- (IBAction)createOrder:(id)sender {
    [UIApplication showBusyHUD:nil withTitle:@"重新生成订单中"];
    CreateOrderParam *params = [[CreateOrderParam alloc] init];
    params.washServiceId = _serviceID;
    params.commoditys = _commoditys;
    params.payType = [NSString stringWithFormat:@"%li", (_paymentTypeSelectedIndexPath.row + 1)];
    [NetworkTools createOrder:params success:^(NSDictionary *response, BOOL isSuccess) {
        [UIApplication stopBusyHUD];
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSDictionary *data = [response objectForKey:@"data"];
            self.orderModel = [[CreateOrderModel alloc] initWithDic:data];
            
            if (self.paymentTypeSelectedIndexPath.row == 0) {
                PayReq *request = [[PayReq alloc] init];
                request.partnerId = self.orderModel.wxPay.partnerid;
                request.prepayId = self.orderModel.wxPay.prepayid;
                request.package = self.orderModel.wxPay.package;
                request.nonceStr = self.orderModel.wxPay.noncestr;
                request.timeStamp = self.orderModel.wxPay.timestamp;
                request.sign = self.orderModel.wxPay.sign;
                [WXApiManager sharedManager].paymentVC = self;
                [WXApi sendReq:request];
            } else {
                [AlipayManager sharedManager].paymentVC = self;
                [[AlipaySDK defaultService] payOrder:self.orderModel.aliPay fromScheme:@"2018072560779353" callback:^(NSDictionary *resultDic) {
                    NSLog(@"resultDic = %@", resultDic);
                }];
            }
        } else {
            NSString *hint = (code == 60002) ? @"创建订单失败" : (code == 60001 ? @"支付类型错误" : @"创建订单失败");
            [self messageBox:hint];
        }
    } failed:^(NSError *error) {
        [UIApplication stopBusyHUD];
        [self messageBox:@"创建订单失败，请稍后重试"];
    }];
}

- (void)paymentSuccess {
    UIViewController *vc = [GlobalMethods viewControllerWithBuddleName:@"Payment" vcIdentifier:@"PaymentSuccessVC"];
    [self.navigationController pushViewController:vc animated:YES];
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 2;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    PaymentTypeCell *cell = [tableView dequeueReusableCellWithIdentifier:@"PaymentTypeIdentifier" forIndexPath:indexPath];
    cell.delegate = self;
    if (indexPath.row == 0) {
        cell.typeImageName = @"WeiXin";
        cell.name = @"微信";
        cell.isShowRecommend = YES;
        cell.selectImageName = @"SingleSelection_Selected";
    } else {
        cell.typeImageName = @"Alipay";
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
