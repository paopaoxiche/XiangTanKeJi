//
//  GlobalMethods.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/8.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "GlobalMethods.h"
#import <CoreLocation/CoreLocation.h>
#import <mach/mach_host.h>
#import <sys/utsname.h>

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
    NSString *CT = @"^1(33|53|77|73|8[019])\\d{8}$";                                     //中国电信：China Telecom
    
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

+ (NSString *)conversionTimestampToStr:(NSString *)timestamp dateFormat:(NSString *)dateFormat {
    long long time = [timestamp longLongValue] / 1000;
    NSDate *date = [[NSDate alloc] initWithTimeIntervalSince1970:time];
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    formatter.dateFormat = dateFormat;
    return [formatter stringFromDate:date];
}

+ (NSInteger)calculateDistanceWithLocation:(Location)location localLocation:(Location)localLocation {
    CLLocation *local = [[CLLocation alloc] initWithLatitude:localLocation.lat longitude:localLocation.lng];
    CLLocation *other = [[CLLocation alloc] initWithLatitude:location.lat longitude:location.lng];
    NSInteger distance = ceil([local distanceFromLocation:other]);
    
    return distance;
}

/**
 *  获取设备型号
 */
+ (NSString *)deviceModel {
    NSDictionary *deviceModel =  @{@"iPhone4,1": @"iPhone 4S", @"iPhone4,2": @"iPhone 4S", @"iPhone4,3": @"iPhone 4S",@"iPhone5,1": @"iPhone 5", @"iPhone5,2": @"iPhone 5",
                                   @"iPhone5,3": @"iPhone 5C", @"iPhone5,4": @"iPhone 5C", @"iPhone6,1": @"iPhone 5S", @"iPhone6,2": @"iPhone 5S", @"iPhone7,1": @"iPhone 6 Plus",
                                   @"iPhone7,2": @"iPhone 6", @"iPhone8,1": @"iPhone 6s", @"iPhone8,2": @"iPhone 6s Plus", @"iPhone8,4": @"iPhone SE", @"iPhone9,1": @"iPhone 7",
                                   @"iPhone9,2": @"iPhone 7 Plus", @"iPhone9,3": @"iPhone 7", @"iPhone9,4": @"iPhone 7 Plus", @"iPhone10,1": @"iPhone 8", @"iPhone10,2": @"iPhone 8 Plus",
                                   @"iPhone10,3": @"iPhone X", @"iPhone10,4": @"iPhone 8", @"iPhone10,5": @"iPhone 8 Plus", @"iPhone10,6" : @"iPhone X",
                                   @"iPad1,1": @"iPad 1", @"iPad2,1": @"iPad 2", @"iPad2,2": @"iPad 2", @"iPad2,3": @"iPad 2", @"iPad2,4": @"iPad 2", @"iPad2,5": @"iPad mini",
                                   @"iPad2,6": @"iPad mini", @"iPad2,7": @"iPad mini", @"iPad3,1": @"iPad 3", @"iPad3,2": @"iPad 3", @"iPad3,3": @"iPad 3",
                                   @"iPad3,4": @"iPad 4", @"iPad3,5": @"iPad 4", @"iPad3,6": @"iPad 4", @"iPad4,1": @"iPad Air", @"iPad4,2": @"iPad Air", @"iPad4,3":@"iPad Air",
                                   @"iPad4,4": @"iPad mini 2", @"iPad4,5": @"iPad mini 2", @"iPad4,6": @"iPad mini 2", @"iPad4,7": @"iPad mini 3", @"iPad4,8": @"iPad mini 3",
                                   @"iPad4,9": @"iPad mini 3", @"iPad5,1": @"iPad mini 4", @"iPad5,2": @"iPad mini 4", @"iPad5,3": @"iPad Air 2", @"iPad5,4": @"iPad Air 2",
                                   @"iPad6,3": @"iPad Pro", @"iPad6,4": @"iPad Pro", @"iPad6,7": @"iPad Pro", @"iPad6,8": @"iPad Pro", @"iPad6,11": @"iPad 5", @"iPad6,12": @"iPad 5",
                                   @"iPad7,1": @"iPad Pro 2", @"iPad7,2": @"iPad Pro 2", @"iPad7,3": @"iPad Pro", @"iPad7,4": @"iPad Pro"};
    
    NSString *machineName = [self machineName];
    NSString *modelStr = [deviceModel objectForKey:machineName];
    if (!modelStr) {
        return machineName;
    }
    
    return modelStr;
}

/**
 *  获取设备Identifier
 */
+ (NSString *)machineName {
    struct utsname systemInfo;
    uname(&systemInfo);
    NSString *machineName = @"";
    machineName = [NSString stringWithCString:systemInfo.machine
                                     encoding:NSUTF8StringEncoding];

    return  machineName;
}

+ (NSString *)productVersion {
    NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
    return [infoDictionary objectForKey:@"CFBundleShortVersionString"];
}

+ (NSString *)systemVersion {
    return [NSString stringWithFormat:@"iOS %@", [[UIDevice currentDevice] systemVersion]];
}

+ (void)setGradientColor:(NSArray *)colors startPoint:(CGPoint)startPoint endPoint:(CGPoint)endPoint view:(UIView *)view {
    CAGradientLayer *layer = [CAGradientLayer layer];
    layer.startPoint = startPoint;      // (0,0)代表左上角，(0,1)代表左下角
    layer.endPoint = endPoint;          // (0,0)(0,1)结合起来就是竖直方向渐变
    layer.colors = colors;
    layer.frame = view.layer.bounds;
    [view.layer insertSublayer:layer atIndex:0];
}

+ (void)setTransparentGradientColor:(NSArray *)colors startPoint:(CGPoint)startPoint endPoint:(CGPoint)endPoint view:(UIView *)view {
    CAGradientLayer *layer = [CAGradientLayer layer];
    layer.startPoint = startPoint;      // (0,0)代表左上角，(0,1)代表左下角
    layer.endPoint = endPoint;          // (0,0)(0,1)结合起来就是竖直方向渐变
    layer.colors = colors;
    layer.frame = view.layer.bounds;
    [view.layer setMask:layer];
}

+ (NSString *)convertDate:(NSString *)dateString outputFormat:(NSString *)outputFormat {
    NSDateFormatter *inputFormatter = [[NSDateFormatter alloc] init];
    [inputFormatter setDateFormat:@"yyyy-MM-dd"];
    NSDate *inputDate = [inputFormatter dateFromString:dateString];
    
    NSDateFormatter *outputFormatter = [[NSDateFormatter alloc] init];
    [outputFormatter setDateFormat:outputFormat];
    NSString *date = [outputFormatter stringFromDate:inputDate];
    
    return date;
}

+ (NSString *)dayOfWeekByDateString:(NSString *)dateString {
    NSDateFormatter *inputFormatter = [[NSDateFormatter alloc] init];
    [inputFormatter setDateFormat:@"yyyy-MM-dd"];
    NSDate *inputDate = [inputFormatter dateFromString:dateString];

    NSArray *weekdays = @[@"星期日", @"星期一", @"星期二", @"星期三", @"星期四", @"星期五", @"星期六"];
    
    NSCalendar *calendar = [[NSCalendar alloc] initWithCalendarIdentifier:NSCalendarIdentifierChinese];
    NSDateComponents *component = [[NSDateComponents alloc] init];
    NSCalendarUnit calendarUnit = NSCalendarUnitWeekday;
    component = [calendar components:calendarUnit fromDate:inputDate];
    
    return [weekdays objectAtIndex:([component weekday] - 1)];
}

+ (NSInteger)currentYear {
    NSCalendar *calendar = [NSCalendar currentCalendar];
    NSDate *date = [NSDate date];
    NSCalendarUnit calendarUnit = NSCalendarUnitYear;
    NSDateComponents *component = [calendar components:calendarUnit
                                              fromDate:date];
    
    return component.year;
}

+ (NSInteger)currentMonth {
    NSCalendar *calendar = [NSCalendar currentCalendar];
    NSDate *date = [NSDate date];
    NSCalendarUnit calendarUnit = NSCalendarUnitMonth;
    NSDateComponents *component = [calendar components:calendarUnit
                                              fromDate:date];
    
    return component.month;
}

+ (BOOL)isFloat:(NSString *)string {
    NSScanner *scan = [NSScanner scannerWithString:string];
    float val;
    return [scan scanFloat:&val] && [scan isAtEnd];
}

@end
