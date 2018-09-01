//
//  NSObject+DictionaryFromModel.h
//  BubbleCarWash
//
//  Created by Rachel on 2018/8/29.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSObject (DictionaryFromModel)

/**
 *  模型转字典
 *
 *  @return 字典
 */
- (NSDictionary *)dictionaryFromModel;

/**
 *  带model的数组或字典转字典
 *
 *  @param object 带model的数组或字典转
 *
 *  @return 字典
 */
- (id)idFromObject:(nonnull id)object;

@end
