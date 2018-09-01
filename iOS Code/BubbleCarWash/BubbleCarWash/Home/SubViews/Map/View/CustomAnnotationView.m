//
//  CustomAnnotationView.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/27.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CustomAnnotationView.h"
#import "UIColor+Category.h"
#import <Masonry/Masonry.h>

#define kMinWidth  100
#define kMaxWidth  160
#define kHeight    52

#define kFontSize   13

#define kArrorHeight        8
#define kBackgroundColor    [UIColor whiteColor]

@interface CustomAnnotationView ()

@property (nonatomic, strong) UIView *tradeStateView;
@property (nonatomic, strong) UIImageView *tradeStateImageView;
@property (nonatomic, strong) UILabel *lowestServicePriceLabel;
@property (nonatomic, strong) UILabel *carWashNameLabel;

@end

@implementation CustomAnnotationView

#pragma mark - Life Cycle

- (id)initWithAnnotation:(id<MAAnnotation>)annotation reuseIdentifier:(NSString *)reuseIdentifier {
    self = [super initWithAnnotation:annotation reuseIdentifier:reuseIdentifier];
    
    if (self) {
        self.backgroundColor = [UIColor clearColor];
        self.bounds = CGRectMake(0, 0, kMinWidth, kHeight);
        self.centerOffset = CGPointMake(0, - kHeight / 2.0);
        
        _tradeStateView = [[UIView alloc] init];
        [self addSubview:_tradeStateView];
        
        _tradeStateImageView = [[UIImageView alloc] init];
        [_tradeStateView addSubview:_tradeStateImageView];
        
        _lowestServicePriceLabel = [[UILabel alloc] init];
        _lowestServicePriceLabel.textColor = [UIColor rgbWithRed:51 green:51 blue:51];
        _lowestServicePriceLabel.font = [UIFont systemFontOfSize:kFontSize];
        [self addSubview:_lowestServicePriceLabel];
        
        _carWashNameLabel = [[UILabel alloc] init];
        _carWashNameLabel.textColor = [UIColor rgbWithRed:153 green:153 blue:153];
        _carWashNameLabel.font = [UIFont systemFontOfSize:kFontSize];
        [self addSubview:_carWashNameLabel];
        
        __weak typeof(self) weakSelf = self;
        [_tradeStateView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(@0);
            make.top.equalTo(@0);
            make.size.mas_equalTo(CGSizeMake(44, 44));
        }];
        
        [_tradeStateImageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.center.equalTo(weakSelf.tradeStateView);
            make.size.mas_equalTo(CGSizeMake(26, 22));
        }];
        
        [_lowestServicePriceLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(weakSelf.tradeStateView.mas_right).offset(8);
            make.top.equalTo(@6);
            make.right.equalTo(weakSelf).offset(-8);
        }];
        
        [_carWashNameLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(weakSelf.lowestServicePriceLabel);
            make.top.equalTo(weakSelf.lowestServicePriceLabel.mas_bottom).offset(2);
            make.right.equalTo(weakSelf).offset(-8);
        }];
    }
    
    return self;
}

#pragma mark - Draw Rect

- (void)drawRect:(CGRect)rect {
    [self drawInContext:UIGraphicsGetCurrentContext()];
    
    self.layer.shadowColor = [[UIColor grayColor] CGColor];
    self.layer.shadowOpacity = 1.0;
    self.layer.shadowOffset = CGSizeMake(0.0f, 0.0f);
}

- (void)drawInContext:(CGContextRef)context {
    CGContextSetLineWidth(context, 1.0);
    CGContextSetFillColorWithColor(context, kBackgroundColor.CGColor);
    [self getDrawPath:context];
    CGContextFillPath(context);
}

- (void)getDrawPath:(CGContextRef)context {
    CGRect rrect = self.bounds;
    CGFloat radius = 4.0;
    CGFloat minx = CGRectGetMinX(rrect),
    midx = CGRectGetMidX(rrect),
    maxx = CGRectGetMaxX(rrect);
    CGFloat miny = CGRectGetMinY(rrect),
    maxy = CGRectGetMaxY(rrect) - kArrorHeight;
    
    CGContextMoveToPoint(context, midx + kArrorHeight, maxy);
    CGContextAddLineToPoint(context, midx, maxy + kArrorHeight);
    CGContextAddLineToPoint(context, midx - kArrorHeight, maxy);
    
    CGContextAddArcToPoint(context, minx, maxy, minx, miny, radius);
    CGContextAddArcToPoint(context, minx, minx, maxx, miny, radius);
    CGContextAddArcToPoint(context, maxx, miny, maxx, maxx, radius);
    CGContextAddArcToPoint(context, maxx, maxy, midx, maxy, radius);
    CGContextClosePath(context);
}

#pragma mark - Setter and Getter

- (void)setTradeStateColor:(UIColor *)tradeStateColor {
    _tradeStateView.backgroundColor = tradeStateColor;
}

- (void)setTradeStateImgName:(NSString *)tradeStateImgName {
    _tradeStateImageView.image = [UIImage imageNamed:tradeStateImgName];
}

- (void)setLowestServicePrice:(NSString *)lowestServicePrice {
    _lowestServicePriceLabel.text = lowestServicePrice;
    [_lowestServicePriceLabel sizeToFit];
}

- (void)setCarWashName:(NSString *)carWashName {
    _carWashNameLabel.text = carWashName;
    [_carWashNameLabel sizeToFit];
    
    CGFloat width = _carWashNameLabel.frame.size.width;
    self.bounds = CGRectMake(0, 0, (width > kMaxWidth - 44 - 16 ? kMaxWidth : width + 44 + 16), kHeight);
}

@end
