//
//  SeeLargePictureViewController.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/9.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "SeeLargePictureViewController.h"
#import "FunctionMacro.h"

@interface SeeLargePictureViewController () <UIScrollViewDelegate>

@property (strong, nonatomic) UIScrollView *scrollView;
@property (strong, nonatomic) UIImageView *imageView;

@end

@implementation SeeLargePictureViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    if (!_scrollView) {
        _scrollView = [[UIScrollView alloc] init];
        [self.view addSubview:_scrollView];
    }
    
    if (!_imageView) {
        _imageView = [[UIImageView alloc] init];
        [_scrollView addSubview:_imageView];
    }
    
    self.imageView.image = self.image;
    _scrollView.contentSize = self.image.size;
    _scrollView.delegate = self;
    _scrollView.maximumZoomScale = 3.0;  //设置最大伸缩比例
    _scrollView.minimumZoomScale = 1.0;  //设置最小伸缩比例
    
    UITapGestureRecognizer *singleTap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(back2SuperViewController)];
    [_imageView addGestureRecognizer:singleTap];
}

- (void)back2SuperViewController {
    [self dismissViewControllerAnimated:NO completion:nil];
}

/**
 *  告诉scrollview要缩放的是哪个子控件
 */
- (UIView *)viewForZoomingInScrollView:(UIScrollView *)scrollView {
    return _imageView;
}

- (void)viewWillLayoutSubviews {
    [super viewWillLayoutSubviews];
    
    _scrollView.frame = CGRectMake(0, 0, kScreenWidth, kScreenHeight);
    _imageView.frame = CGRectMake(0, 0, kScreenWidth, kScreenHeight);
}

@end
