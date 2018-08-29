//
//  CreateProductViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CreateProductViewController.h"
#import "CreateProductCell.h"

@interface CreateProductViewController () <UITableViewDataSource, UITableViewDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableView;

@end

@implementation CreateProductViewController

- (void)viewDidLoad {
    [super viewDidLoad];
}

#pragma mark - UITableViewDatasource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return _proType == ProductTypeCarWash ? 3 : 5;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    BOOL isCarWashService = _proType == ProductTypeCarWash;
    if (indexPath.row == 4 && !isCarWashService) {
        CreateProductPictureCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ProductPictureIdentifier" forIndexPath:indexPath];
        
        return cell;
    }
    
    if (indexPath.row == 3 || (indexPath.row == 2 && isCarWashService)) {
        CreateProductDescCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ProductDescIdentifier" forIndexPath:indexPath];
        
        return cell;
    }
    
    CreateProductNameCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ProductNameIdentifier" forIndexPath:indexPath];
    cell.title = !isCarWashService ? (indexPath.row == 0 ? @"商品名称" : (indexPath.row == 1 ? @"商品现价" : @"商品原价")) : (indexPath.row == 0 ? @"服务名称" : @"服务价格");
    cell.isPrice = indexPath.row == 0 ? NO : YES;
    
    return cell;
}

#pragma mark - UITableViewDelegate

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    BOOL isCarWashService = _proType == ProductTypeCarWash;
    if (indexPath.row == 4 && !isCarWashService) {
        return 130;
    }
    
    if (indexPath.row == 3 || (indexPath.row == 2 && isCarWashService)) {
        return 186;
    }
    
    return 48;
}

@end
