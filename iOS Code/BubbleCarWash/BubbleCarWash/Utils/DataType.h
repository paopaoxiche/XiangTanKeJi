//
//  DataType.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/9/9.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#ifndef DataType_h
#define DataType_h

#import "UIKit/UIKit.h"

/// 位置结构体
struct Location {
    /// 经度
    CGFloat lng;
    /// 纬度
    CGFloat lat;
};
typedef struct CG_BOXABLE Location Location;

#endif /* DataType_h */
