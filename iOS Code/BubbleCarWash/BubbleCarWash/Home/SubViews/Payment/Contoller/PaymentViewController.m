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
#import "PaymentSuccessViewController.h"

@implementation PayTypeModel

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _typeImageName = [dic objectForKey:@"typeImageName"];
        _name = [dic objectForKey:@"name"];
        _isShowRecommend = [[dic objectForKey:@"isShowRecommend"] boolValue];
    }
    
    return self;
}

@end

@interface PaymentViewController () <UITableViewDataSource, PaymentTypeCellDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UILabel *totalAmountLabel;
@property (weak, nonatomic) IBOutlet UIButton *paymentBtn;
@property (nonatomic, strong) NSIndexPath *paymentTypeSelectedIndexPath;
@property (nonatomic, strong) CreateOrderModel *orderModel;
@property (nonatomic, strong) NSMutableArray *dataSource;

@end

@implementation PaymentViewController

- (instancetype)init {
    self = [super init];
    if (self) {
        _couponID = -1;
    }
    
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _tableView.tableFooterView = [[UIView alloc] init];
    _paymentTypeSelectedIndexPath = [NSIndexPath indexPathForRow:0 inSection:0];
    _totalAmountLabel.text = [NSString stringWithFormat:@"¥%@", self.totalAmount];
    _dataSource = [[NSMutableArray alloc] initWithCapacity:0];
    
    // 判断是否安装微信
    if ([WXApi isWXAppInstalled]) {
        PayTypeModel *model = [[PayTypeModel alloc] initWithDic:@{@"typeImageName": @"WeiXin", @"name": @"微信", @"isShowRecommend": @1}];
        [_dataSource addObject:model];
    }
    
    // 判断是否安装支付宝
    NSURL *url = [NSURL URLWithString:@"alipay:"];
    if ([[UIApplication sharedApplication] canOpenURL:url]) {
        PayTypeModel *model = [[PayTypeModel alloc] initWithDic:@{@"typeImageName": @"Alipay", @"name": @"支付宝", @"isShowRecommend": @0}];
        [_dataSource addObject:model];
    }
    
    if (_dataSource.count < 1) {
        self.paymentBtn.enabled = NO;
        [self.paymentBtn setTitle:@"未下载支付软件，不能进行支付"
                         forState:UIControlStateNormal];
    } else {
        self.paymentBtn.enabled = YES;
        [self.paymentBtn setTitle:[NSString stringWithFormat:@"确认支付 ¥%@", self.totalAmount]
                         forState:UIControlStateNormal];
    }
}

- (IBAction)createOrder:(id)sender {
    PayTypeModel *model = _dataSource[_paymentTypeSelectedIndexPath.row];
    BOOL isWeiXin = model.isShowRecommend;
    [UIApplication showBusyHUD:nil withTitle:@"重新生成订单中"];
    CreateOrderParam *params = [[CreateOrderParam alloc] init];
    params.washServiceId = _serviceID;
    params.commoditys = _commoditys;
    params.payType = [NSString stringWithFormat:@"%i", (isWeiXin ? 1 : 2)];
    
    if (self.couponID != -1) {
        params.couponId = [NSString stringWithFormat:@"%li", self.couponID];
    }
    
    [NetworkTools createOrder:params success:^(NSDictionary *response, BOOL isSuccess) {
        [UIApplication stopBusyHUD];
        NSInteger code = [[response objectForKey:@"code"] integerValue];
        if (code == 200 && [response objectForKey:@"data"] != [NSNull null]) {
            NSDictionary *data = [response objectForKey:@"data"];
            self.orderModel = [[CreateOrderModel alloc] initWithDic:data];
            
            if (isWeiXin) {
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
                [[AlipaySDK defaultService] payOrder:self.orderModel.aliPay fromScheme:@"xiangtan2018072560779353" callback:^(NSDictionary *resultDic) {
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
    PaymentSuccessViewController *vc = (PaymentSuccessViewController *)[GlobalMethods viewControllerWithBuddleName:@"Payment" vcIdentifier:@"PaymentSuccessVC"];
    vc.consumeID = self.orderModel.consumeId;
    [self.navigationController pushViewController:vc animated:YES];
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return _dataSource.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    PaymentTypeCell *cell = [tableView dequeueReusableCellWithIdentifier:@"PaymentTypeIdentifier" forIndexPath:indexPath];
    PayTypeModel *model = _dataSource[indexPath.row];
    cell.typeImageName = model.typeImageName;
    cell.name = model.name;
    cell.isShowRecommend = model.isShowRecommend;
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    cell.delegate = self;
    if (indexPath.row == 0) {
        cell.selectImageName = @"SingleSelection_Selected";
    } else {
        cell.selectImageName = @"SingleSelection_Normal";
    }
    return cell;
}

#pragma mark - PaymentTypeCellDelegate

- (void)selectedPaymentTypeCell:(PaymentTypeCell *)cell {
    NSIndexPath *currentIndexPath = [self.tableView indexPathForCell:cell];
    if (currentIndexPath.row == _paymentTypeSelectedIndexPath.row) {
        return;
    }
    
    cell.selectImageName = @"SingleSelection_Selected";
    PaymentTypeCell *selectedCell = [self.tableView cellForRowAtIndexPath:_paymentTypeSelectedIndexPath];
    selectedCell.selectImageName = @"SingleSelection_Normal";
    
    _paymentTypeSelectedIndexPath = currentIndexPath;
}

@end
