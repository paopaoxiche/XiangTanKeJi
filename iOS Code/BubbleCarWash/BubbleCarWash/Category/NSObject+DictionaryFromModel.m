//
//  NSObject+DictionaryFromModel.m
//  BubbleCarWash
//
//  Created by Rachel on 2018/8/29.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "NSObject+DictionaryFromModel.h"
#import <objc/runtime.h>

@implementation NSObject (DictionaryFromModel)

- (NSDictionary *)dictionaryFromModel {
    unsigned int count = 0;
    objc_property_t *properties = class_copyPropertyList([self class], &count);
    NSMutableDictionary *dict = [NSMutableDictionary dictionaryWithCapacity:count];
    
    for (int i = 0; i < count; i++) {
        NSString *key = [NSString stringWithUTF8String:property_getName(properties[i])];
        id value = [self valueForKey:key];
        
        if (key && value) {
            if ([value isKindOfClass:[NSString class]] || [value isKindOfClass:[NSNumber class]]) {
                // 普通类型的直接变成字典的值
                [dict setObject:value forKey:key];
            } else if ([value isKindOfClass:[NSArray class]] || [value isKindOfClass:[NSDictionary class]]) {
                // 数组类型或字典类型
                [dict setObject:[self idFromObject:value] forKey:key];
            } else {
                // 如果model里有其他自定义模型，则递归将其转换为字典
                [dict setObject:[value dictionaryFromModel] forKey:key];
            }
        } else if (key && value == nil) {
            [dict setObject:[NSNull null] forKey:key];
        } // else if
    } // for
    
    free(properties);
    return dict;
}

- (id)idFromObject:(nonnull id)object {
    if ([object isKindOfClass:[NSArray class]]) {
        if (object && [object count] > 0) {
            NSMutableArray *array = [NSMutableArray array];
            for (id obj in object) {
                if ([obj isKindOfClass:[NSString class]] || [obj isKindOfClass:[NSNumber class]]) {
                    // 基本类型直接添加
                    [array addObject:obj];
                } else if ([obj isKindOfClass:[NSDictionary class]] || [obj isKindOfClass:[NSArray class]]) {
                    // 字典或数组需递归处理
                    [array addObject:[self idFromObject:obj]];
                } else {
                    // model转化为字典
                    [array addObject:[obj dictionaryFromModel]];
                } // else
            } // for
            
            return array;
        } else {
            return object ? : [NSNull null];
        }
    } else if ([object isKindOfClass:[NSDictionary class]]) {
        if (object && [[object allKeys] count] > 0) {
            NSMutableDictionary *dic = [NSMutableDictionary dictionary];
            for (NSString *key in [object allKeys]) {
                if ([object[key] isKindOfClass:[NSNumber class]] || [object[key] isKindOfClass:[NSString class]]) {
                    [dic setObject:object[key] forKey:key];
                } else if ([object[key] isKindOfClass:[NSArray class]] || [object[key] isKindOfClass:[NSDictionary class]]) {
                    [dic setObject:[self idFromObject:object[key]] forKey:key];
                } else {
                    [dic setObject:[object[key] dictionaryFromModel] forKey:key];
                } // else
            } // for
            
            return dic;
        } else {
            return object ? : [NSNull null];
        } // else
    } // else if
    
    return [NSNull null];
}

@end
