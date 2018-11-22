//
//  CarWashServiceViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/9.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CarWashServiceViewController.h"
#import "CarWashServiceModel.h"
#import "AuthenticationModel.h"
#import "HomeModel.h"
#import "CarWashServiceCell.h"
#import "CommodityCell.h"
#import "CertificationCell.h"
#import "GlobalMethods.h"
#import "UIColor+Category.h"
#import "PaymentViewController.h"
#import "UIApplication+HUD.h"

@interface CarWashServiceViewController () <CarWashServiceCellDelegate, CommodityCellDelegate,
    CertificationCellDelegate, UITableViewDataSource, UITableViewDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UIView *bottomView;
@property (weak, nonatomic) IBOutlet UIButton *payButton;
@property (nonatomic, strong) NSMutableArray *dataSource;
@property (nonatomic, strong) NSIndexPath *serviceSelectedIndexPath;
@property (nonatomic, strong) NSIndexPath *carTypeSelectedIndexPath;
@property (nonatomic, assign) CGFloat totalAmount;
@property (nonatomic, copy) NSMutableArray *commoditys;

@end

@implementation CarWashServiceViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    _dataSource = [[NSMutableArray alloc] initWithCapacity:0];
    [_tableView registerNib:[UINib nibWithNibName:@"CertificationCell" bundle:[NSBundle mainBundle]]
     forCellReuseIdentifier:@"CarTypeIdentifier"];
    
    _serviceSelectedIndexPath = [NSIndexPath indexPathForRow:0 inSection:0];
    _carTypeSelectedIndexPath = [NSIndexPath indexPathForRow:0 inSection:1];
    _commoditys = [[NSMutableArray alloc] initWithCapacity:0];
    _totalAmount = 0;
    
    NSArray *colors = @[(id)[UIColor rgbByHexStr:@"ffffff" alpha:0.0].CGColor, (id)[UIColor rgbByHexStr:@"ffffff" alpha:1.0].CGColor];
    [GlobalMethods setTransparentGradientColor:colors
                                    startPoint:CGPointMake(0, 0)
                                      endPoint:CGPointMake(0, 1)
                                          view:_bottomView];
}

- (void)setWashID:(NSInteger)washID {
    _washID = washID;
    [UIApplication showBusyHUD];
    [CarWashServiceModel loadCarWashServiceData:washID result:^(NSArray *result) {
        NSArray *modelCertificationList = [[NSArray alloc] init];
        modelCertificationList = result[1][@"ModelCertificationList"];
        if (modelCertificationList.count <= 0) {
            [UIApplication stopBusyHUD];
            [self messageBox:@"请先进行车型认证再进行下单" handle:^{
                [self.navigationController popViewControllerAnimated:YES];
            }];
            return;
        }
        
        NSArray *serviceList = [[NSArray alloc] init];
        serviceList = result[0][@"ServiceList"];
        if (serviceList.count <= 0) {
            [UIApplication stopBusyHUD];
            [self messageBox:@"该洗车场无洗车服务" handle:^{
                [self.navigationController popViewControllerAnimated:YES];
            }];
            return;
        }
        
        [self.dataSource addObject:serviceList];
        [self.dataSource addObject:modelCertificationList];
        
        NSArray *commodityList = [[NSArray alloc] init];
        commodityList = result[2][@"CommodityList"];
        if (commodityList.count > 0) {
            [self.dataSource addObject:commodityList];
        }
        
        [self calculatePaymentAmount];
        [UIApplication stopBusyHUD];
        [self.tableView reloadData];
    }];
}

- (IBAction)onPayButtonClicked:(id)sender {
    // loading提示创建订单，去支付（美团），然后跳转到支付选择界面
    [UIApplication showBusyHUD:nil withTitle:@"创建订单中..."];
    
    ServiceModel *serviceModel = [_dataSource[0] objectAtIndex:_serviceSelectedIndexPath.row];
    
    NSMutableString *commoditys = [[NSMutableString alloc] initWithCapacity:0];
    if (_commoditys.count > 0) {
        for (RecommendCommodityModel *model in _commoditys) {
            [commoditys appendString:[NSString stringWithFormat:@"%li,", model.dataID]];
        }
        
        [commoditys replaceCharactersInRange:NSMakeRange(commoditys.length - 1, 1) withString:@""];
    }
    
    PaymentViewController *paymentVC = (PaymentViewController *)[GlobalMethods viewControllerWithBuddleName:@"Payment"
                                                                                               vcIdentifier:@"PaymentViC"];
    paymentVC.serviceID = [NSString stringWithFormat:@"%li", serviceModel.dataID];
    paymentVC.commoditys = commoditys;
    [UIApplication stopBusyHUD];
    [self.navigationController pushViewController:paymentVC animated:YES];
}

- (void)calculatePaymentAmount {
    if (_dataSource.count < 2) {
        return;
    }
    
    NSArray *servicelist = _dataSource[0];
    ServiceModel *model =  servicelist[_serviceSelectedIndexPath.row];
    _totalAmount = model.price;
    if (_dataSource.count > 2 && _commoditys.count > 0) {
        for (RecommendCommodityModel *model in _commoditys) {
            _totalAmount += model.currentPrice;
        }
    }
    
    [_payButton setTitle:[NSString stringWithFormat:@"支付 (¥%.2f)", _totalAmount]
                forState:UIControlStateNormal];
}

#pragma mark - UITableViewDatasource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return _dataSource.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    NSArray *list = _dataSource[section];
    return list.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    NSArray *list = _dataSource[indexPath.section];
    if (indexPath.section == 0) {
        ServiceModel *model =  list[indexPath.row];
        CarWashServiceCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CarWashServiceIdentifier" forIndexPath:indexPath];
        cell.delegate = self;
        cell.name = model.carWashName;
        cell.desc = model.desc;
        cell.couponPrice = model.price;
        cell.hasCoupon = NO;
        cell.selectBtnImageName =  indexPath.row == 0 ? @"SingleSelection_Selected" : @"SingleSelection_Normal";
        
        return cell;
    } else if (indexPath.section == 1) {
        ModelCertificationModel *model = list[indexPath.row];
        CertificationCarTypeCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CarTypeIdentifier" forIndexPath:indexPath];
        cell.delegate = self;
        cell.carImgName = model.imageName;
        cell.carDesc = [NSString stringWithFormat:@"%@(%@)", model.model, model.desc];
        cell.selectImgName =  indexPath.row == 0 ? @"SingleSelection_Selected" : @"SingleSelection_Normal";
        cell.btnLeadingConstraint = 0;

        return cell;
    } else {
        RecommendCommodityModel *model = list[indexPath.row];
        CommodityCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CommodityIdentifier" forIndexPath:indexPath];
        cell.delegate = self;
        cell.avatarUrl = model.imageUrl;
        cell.name = model.commodityName;
        cell.price = model.currentPrice;
        cell.selectBtnImageName = model.isSelected ? @"SingleSelection_Selected" : @"SingleSelection_Normal";

        return cell;
    }
}

- (void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath {
    if ([cell respondsToSelector:@selector(tintColor)]) {
        if (tableView == _tableView) {
            CGFloat cornerRadius = 10.f;
            cell.backgroundColor = UIColor.clearColor;
            
            CAShapeLayer *layer = [[CAShapeLayer alloc] init];
            CGMutablePathRef pathRef = CGPathCreateMutable();
            CGRect bounds = CGRectInset(cell.bounds, 0, 0);
            
            BOOL addLine = NO;
            //只有一行
            if ([tableView numberOfRowsInSection:indexPath.section] == 1) {
                CGPathAddRoundedRect(pathRef, nil, bounds, cornerRadius, cornerRadius);
            } else {
                if (indexPath.row == 0) {
                    // 初始起点为cell的左下角坐标
                    CGPathMoveToPoint(pathRef, nil, CGRectGetMinX(bounds), CGRectGetMaxY(bounds));
                    CGPathAddArcToPoint(pathRef, nil, CGRectGetMinX(bounds), CGRectGetMinY(bounds), CGRectGetMidX(bounds), CGRectGetMinY(bounds), cornerRadius);
                    CGPathAddArcToPoint(pathRef, nil, CGRectGetMaxX(bounds), CGRectGetMinY(bounds), CGRectGetMaxX(bounds), CGRectGetMidY(bounds), cornerRadius);
                    CGPathAddLineToPoint(pathRef, nil, CGRectGetMaxX(bounds), CGRectGetMaxY(bounds));
                    addLine = YES;
                } else if (indexPath.row == [tableView numberOfRowsInSection:indexPath.section] - 1) {
                    CGPathMoveToPoint(pathRef, nil, CGRectGetMinX(bounds), CGRectGetMinY(bounds));
                    CGPathAddArcToPoint(pathRef, nil, CGRectGetMinX(bounds), CGRectGetMaxY(bounds), CGRectGetMidX(bounds), CGRectGetMaxY(bounds), cornerRadius);
                    CGPathAddArcToPoint(pathRef, nil, CGRectGetMaxX(bounds), CGRectGetMaxY(bounds), CGRectGetMaxX(bounds), CGRectGetMidY(bounds), cornerRadius);
                    CGPathAddLineToPoint(pathRef, nil, CGRectGetMaxX(bounds), CGRectGetMinY(bounds));
                } else {
                    CGPathAddRect(pathRef, nil, bounds);
                    addLine = YES;
                }
            }
            
            // 把已经绘制好的可变图像路径赋值给图层
            layer.path = pathRef;
            // 注意：但凡通过Quartz2D中带有creat/copy/retain方法创建出来的值都必须要释放
            CFRelease(pathRef);
            // 按照shape layer的path填充颜色，类似于渲染render
            layer.fillColor = [UIColor colorWithWhite:1.f alpha:0.8f].CGColor;
            
            // 添加分隔线图层
            if (addLine == YES) {
                CALayer *lineLayer = [[CALayer alloc] init];
                CGFloat lineHeight = 1;
                lineLayer.frame = CGRectMake(12, bounds.size.height - lineHeight, bounds.size.width, lineHeight);
                lineLayer.backgroundColor = [UIColor rgbWithRed:244 green:244 blue:244].CGColor;
                [layer addSublayer:lineLayer];
            }
            
            UIView *roundView = [[UIView alloc] initWithFrame:bounds];
            [roundView.layer insertSublayer:layer atIndex:0];
            roundView.backgroundColor = UIColor.clearColor;
            cell.backgroundView = roundView;
        }
    }
}

- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section {
    UIView *headerView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 200, 40)];
    UILabel *headerLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, 12, 200, 20)];
    headerLabel.backgroundColor = [UIColor clearColor];
    headerLabel.font = [UIFont systemFontOfSize:15];
    headerLabel.textColor = [UIColor rgbWithRed:153 green:153 blue:153];
    headerLabel.text = (section == 0 ? @"洗车服务" : (section == 1 ? @"选择你要洗的车型" : @"商品"));
    [headerView addSubview:headerLabel];
    
    return headerView;
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    if (indexPath.section == 0) {
        return 78;
    }
    
    if (indexPath.section == 1) {
        return 44;
    }
    
    return 75;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section {
    return 39.999999;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section {
    if (section == _dataSource.count - 1) {
        return 58;
    }
    
    return 0.000001;
}

#pragma mark - CarWashServiceCellDelegate

- (void)selectedServiceCell:(CarWashServiceCell *)cell {
    cell.selectBtnImageName = @"SingleSelection_Selected";
    
    CarWashServiceCell *serviceCell = [self.tableView cellForRowAtIndexPath:_serviceSelectedIndexPath];
    serviceCell.selectBtnImageName = @"SingleSelection_Normal";
    
    _serviceSelectedIndexPath = [self.tableView indexPathForCell:cell];
    
    [self calculatePaymentAmount];
}

#pragma mark - CommodityCellDelegate

- (void)selectedCommodityCell:(CommodityCell *)cell {
    NSIndexPath *indexPath = [self.tableView indexPathForCell:cell];
    RecommendCommodityModel *model = _dataSource[indexPath.section][indexPath.row];
    model.isSelected = !model.isSelected;
    cell.selectBtnImageName = model.isSelected ? @"SingleSelection_Selected" : @"SingleSelection_Normal";
    
    [_dataSource[indexPath.section] replaceObjectAtIndex:indexPath.row withObject:model];
    if (model.isSelected) {
        [_commoditys addObject:model];
    } else {
        [_commoditys removeObject:model];
    }
    
    [self calculatePaymentAmount];
}

#pragma mark - CertificationCellDelegate

- (void)onSelectedBtnClicked:(CertificationCarTypeCell *)cell {
    cell.selectImgName = @"SingleSelection_Selected";
    
    CertificationCarTypeCell *selectedCell = [self.tableView cellForRowAtIndexPath:_carTypeSelectedIndexPath];
    selectedCell.selectImgName = @"SingleSelection_Normal";
    
    _carTypeSelectedIndexPath = [self.tableView indexPathForCell:cell];
}

@end
