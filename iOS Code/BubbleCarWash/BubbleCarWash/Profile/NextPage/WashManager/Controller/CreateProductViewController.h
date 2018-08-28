//
//  CreateProductViewController.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef NS_ENUM(NSUInteger, ProductType) {
    ProductTypeCarWash,         // 洗车服务产品
    ProductTypeCommodity        // 商品产品
};

@interface CreateProductViewController : UIViewController

@property (nonatomic, assign) ProductType proType;

@end
