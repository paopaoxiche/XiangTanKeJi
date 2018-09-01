//
//  CustomAnnotationView.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/27.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MAMapKit/MAMapKit.h>

@class CustomCalloutView;

@interface CustomAnnotationView : MAAnnotationView

@property (nonatomic, strong) UIColor *tradeStateColor;
@property (nonatomic, copy) NSString *tradeStateImgName;
@property (nonatomic, copy) NSString *lowestServicePrice;
@property (nonatomic, copy) NSString *carWashName;

@end
