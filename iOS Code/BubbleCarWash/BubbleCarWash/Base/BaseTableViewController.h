//
//  BaseTableViewController.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/9.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef void(^ActionHandle)(void);

@interface BaseTableViewController : UITableViewController

- (void)messageBox:(NSString *)lpszMessage;
- (void)messageBox:(NSString *)lpszMessage handle:(ActionHandle)handle;

@end
