//
//  GlobalMethods.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/8.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "GlobalMethods.h"

@implementation GlobalMethods

+ (UIViewController *)viewControllerWithBuddleName:(NSString *)name vcIdentifier:(NSString *)identifier {
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:name bundle:[NSBundle mainBundle]];
    return [storyboard instantiateViewControllerWithIdentifier:identifier];
}

+ (BOOL)checkPhoneNumberValid:(NSString *)phoneNumberString {
    /**
     *  手机号码
     *  移动：134[0-8],135,136,137,138,139,150,151,152,157[0-79],158,159,178,182,183,184,187,188
     *  联通：130,131,132,155,156,176,185,186
     *  电信：133,153,177,180,181,189
     */
    NSString *CM = @"^1(34[0-8]|57[0-79]|(3[5-9]|5[0-289]|78|8[2-478])\\d)\\d{7}$";     //中国移动：China Mobile
    NSString *CU = @"^1(3[0-2]|5[56]|76|8[56])\\d{8}$";                                 //中国联通：China Unicom
    NSString *CT = @"^1(33|53|77|8[019])\\d{8}$";                                     //中国电信：China Telecom
    
    NSPredicate *predicateCM = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", CM];
    NSPredicate *predicateCU = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", CU];
    NSPredicate *predicateCT = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", CT];
    
    BOOL resultCM = [predicateCM evaluateWithObject:phoneNumberString];
    BOOL resultCU = [predicateCU evaluateWithObject:phoneNumberString];
    BOOL resultCT = [predicateCT evaluateWithObject:phoneNumberString];
    
    if (resultCM || resultCU || resultCT) {
        return YES;
    }
    
    return NO;
}

+ (NSString *)conversionTimestampToStr:(NSString *)timestamp {
    long long time = [timestamp longLongValue] / 1000;
    NSDate *date = [[NSDate alloc] initWithTimeIntervalSince1970:time];
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    formatter.dateFormat = @"yyyy-MM-dd";
    return [formatter stringFromDate:date];
}

@end
