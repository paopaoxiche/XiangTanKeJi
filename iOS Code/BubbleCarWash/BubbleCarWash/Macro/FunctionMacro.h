//
//  FunctionMacro.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/22.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#ifndef FunctionMacro_h
#define FunctionMacro_h

// 颜色定义
#define RGBColor(r, g, b) ([UIColor colorWithRed:(r)/255.0f green:(g)/255.0f blue:(b)/255.0f alpha:1.0])

#define kScreenWidth [UIScreen mainScreen].bounds.size.width
#define kScreenHeight [UIScreen mainScreen].bounds.size.height

// 字体
#define Font(s) ([UIFont systemFontOfSize:s])
#define BFont(s) ([UIFont boldSystemFontOfSize:s])

// 是否iPhone
#define TARGET_IPHONE ([[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPhone)
// 是否是iPhone X
#define IS_IPHONE_X (TARGET_IPHONE && ((fabs((double)[[UIScreen mainScreen] bounds].size.height - (double)812) < DBL_EPSILON) || (fabs((double)[[UIScreen mainScreen] bounds].size.width - (double)812) < DBL_EPSILON)))

#define SAFE_RELEASE(p) do { if(p) {;p=nil; } } while (false)

#endif /* FunctionMacro_h */
