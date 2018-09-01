//
//  ProfileModel.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/21.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "ProfileModel.h"

@implementation ProfileModel

+ (NSArray *)profileDataSourceWithUserType:(NSInteger)userType {
    NSString *path = [[NSBundle mainBundle] pathForResource:@"Profile.json" ofType:nil];
    NSData *data = [NSData dataWithContentsOfFile:path];
    NSArray *array = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingAllowFragments error:nil];
    NSArray *dataArr = [[NSArray alloc] init];
    for (NSDictionary *dic in array) {
        if ([[dic objectForKey:@"type"] integerValue] == userType) {
            dataArr = [dic objectForKey:@"dataSource"];
            break;
        }
    }
    
    NSMutableArray *dataSource = [[NSMutableArray alloc] initWithCapacity:0];
    for (NSDictionary *dic in dataArr) {
        NSInteger section = [[dic objectForKey:@"section"] integerValue];
        NSMutableArray *arr = [[NSMutableArray alloc] initWithCapacity:0];
        for (NSDictionary *dataDic in [dic objectForKey:@"data"]) {
            ProfileItem *item = [[ProfileItem alloc] initWithDic:dataDic];
            [arr addObject:item];
        }
        
        [dataSource insertObject:arr atIndex:section];
    }
    
    return dataSource;
}

@end

@implementation ProfileItem

- (instancetype)initWithDic:(NSDictionary *)dic {
    self = [super init];
    if (self) {
        _text = [dic objectForKey:@"text"];
        _imageName = [dic objectForKey:@"imageName"];
        _nextPageID = [dic objectForKey:@"nextPageID"];
    }
    
    return self;
}

@end

