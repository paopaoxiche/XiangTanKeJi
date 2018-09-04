//
//  FeedbackTextView.h
//  BubbleCarWash
//
//  Created by Rachel on 2018/9/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "LYEdgeInsetsLabel.h"

@interface FeedbackTextView : UIView

@property (nonatomic, strong) LYEdgeInsetsLabel *placeholder_lab;
@property (strong, nonatomic) UITextView *textView;

@end
