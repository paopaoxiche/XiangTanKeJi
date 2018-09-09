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

@interface CarWashServiceViewController ()

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (nonatomic, strong) NSMutableArray *dataSource;

@end

@implementation CarWashServiceViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    _dataSource = [[NSMutableArray alloc] initWithCapacity:0];
}

- (void)setWashID:(NSInteger)washID {
    _washID = washID;
    [CarWashServiceModel loadCarWashServiceData:washID result:^(NSArray *result) {
        NSArray *modelCertificationList = result[0][@"ModelCertificationList"];
        if (modelCertificationList.count <= 0) {
            [self messageBox:@"请先进行车型认证再进行下单" handle:^{
                [self.navigationController popViewControllerAnimated:YES];
            }];
            return;
        }
        
        NSArray *serviceList = result[0][@"ServiceList"];
        if (serviceList.count <= 0) {
            [self messageBox:@"该洗车场无洗车服务" handle:^{
                [self.navigationController popViewControllerAnimated:YES];
            }];
            return;
        }
        
        [self.dataSource addObject:serviceList];
        [self.dataSource addObject:modelCertificationList];
        
        NSArray *commodityList = result[2][@"CommodityList"];
        if (commodityList.count > 0) {
            [self.dataSource addObject:commodityList];
        }
    }];
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
        CarWashServiceCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CommentCell"];

        cell.name = model.carWashName;
        cell.desc = model.desc;
        cell.couponPrice = model.price;
        cell.hasCoupon = NO;
        
        return cell;
    } else if (indexPath.section == 1) {
        ModelCertificationModel *model = list[indexPath.row];
        CertificationCarTypeCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CommentCell"];
        cell.carImgName = model.imageName;
        cell.carDesc = [NSString stringWithFormat:@"%@(%@)", model.model, model.desc];
        
        return cell;
    } else {
        RecommendCommodityModel *model = list[indexPath.row];
        CommodityCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CommentCell"];
        cell.avatarUrl = model.imageUrl;
        cell.name = model.commodityName;
        cell.price = model.currentPrice;
        
        return cell;
    }
}

@end
