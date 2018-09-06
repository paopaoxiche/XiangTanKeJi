//
//  ParentViewController.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/19.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef void(^ActionHandle)(void);

@interface ParentViewController : UIViewController

- (void)messageBox:(NSString *)lpszMessage;
- (void)messageBox:(NSString *)lpszMessage handle:(ActionHandle)handle;

@end
