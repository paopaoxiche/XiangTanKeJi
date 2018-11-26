//
//  WebViewController.m
//  BubbleCarWash
//
//  Created by Rachel on 2018/11/26.
//  Copyright © 2018 Sunshine Girl. All rights reserved.
//

#import "WebViewController.h"

@interface WebViewController ()

@property (weak, nonatomic) IBOutlet UIView *backView;
@property (weak, nonatomic) IBOutlet UIWebView *webView;

@end

@implementation WebViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    UITapGestureRecognizer *single = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(backToSuperVC)];
    [self.backView addGestureRecognizer:single];
    
    NSURL *url = [[NSBundle mainBundle] URLForResource:@"Privacy.html" withExtension:nil];
    if (url) {
        [_webView loadRequest:[NSURLRequest requestWithURL:url]];
    } else {
        [self messageBox:@"协议获取失败"];
    }
}

- (void)backToSuperVC {
    [self dismissViewControllerAnimated:YES completion:nil];
}

@end
