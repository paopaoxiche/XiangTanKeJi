//
//  EditTradeStateViewController.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/5.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef NS_ENUM(NSUInteger, TradeState) {
    TradeStateOperate,      // 营业
    TradeStateRest,         // 歇业
    TradeStateClosed        // 停业
};

@interface EditTradeStateViewController : UITableViewController

@end
