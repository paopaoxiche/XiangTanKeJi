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

#endif /* FunctionMacro_h */
