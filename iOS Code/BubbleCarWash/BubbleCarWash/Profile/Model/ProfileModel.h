//
//  ProfileModel.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/7/21.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ProfileModel : NSObject

+ (NSArray *)profileDataSourceWithUserType:(NSInteger)userType;

@end

@interface ProfileItem : NSObject

@property (nonatomic, copy) NSString *text;
@property (nonatomic, copy) NSString *imageName;
@property (nonatomic, copy) NSString *nextPageID;

- (instancetype)initWithDic:(NSDictionary *)dic;

@end
