//
//  LYKeyChainStore.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/20.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "LYKeyChainStore.h"

@implementation LYKeyChainStore

+ (NSMutableDictionary *)getKeychainQuery:(NSString *)service {
    return [NSMutableDictionary dictionaryWithObjectsAndKeys:
            (id)kSecClassGenericPassword,(id)kSecClass,
            service, (id)kSecAttrService,
            service, (id)kSecAttrAccount,
            nil];
}

+ (BOOL)saveAccount:(NSString *)account password:(NSString *)password service:(NSString *)service {
    return [self saveAccount:account service:service] && [self savePassword:password service:service];
}

+ (BOOL)saveAccount:(NSString *)account service:(NSString *)service {
    NSString *serviceStr = [NSString stringWithFormat:@"%@account", service];
    return [self saveData:account service:serviceStr];
}

+ (BOOL)savePassword:(NSString *)password service:(NSString *)service {
    NSString *serviceStr = [NSString stringWithFormat:@"%@password", service];
    return [self saveData:password service:serviceStr];
}

+ (BOOL)saveData:(NSString *)str service:(NSString *)service {
    NSMutableDictionary *keychainQuery = [self getKeychainQuery:service];
    SecItemDelete((__bridge CFDictionaryRef)keychainQuery);
    [keychainQuery setObject:[NSKeyedArchiver archivedDataWithRootObject:str] forKey:(id)kSecValueData];
    OSStatus status = SecItemAdd((__bridge CFDictionaryRef)keychainQuery, NULL);
    if (status != errSecSuccess) {
        return NO;
    }
    
    return YES;
}

+ (id)passwordWithService:(NSString *)service {
    NSString *serviceStr = [NSString stringWithFormat:@"%@password", service];
    return [self dataWithService:serviceStr];
}

+ (id)accountWithService:(NSString *)service {
    NSString *serviceStr = [NSString stringWithFormat:@"%@account", service];
    return [self dataWithService:serviceStr];
}

+ (NSString *)dataWithService:(NSString *)service {
    id result = nil;
    NSMutableDictionary *keychainQuery = [self getKeychainQuery:service];
    [keychainQuery setObject:(id)kSecMatchLimitOne forKey:(id)kSecMatchLimit];
    [keychainQuery setObject:(id)kCFBooleanTrue forKey:(id)kSecReturnData];
    CFDataRef keyData = NULL;
    OSStatus status = SecItemCopyMatching((CFDictionaryRef)keychainQuery, (CFTypeRef *)&keyData);
    if (status == errSecSuccess) {
        result = [NSKeyedUnarchiver unarchiveObjectWithData:(__bridge NSData *)keyData];
    }
    
    return result;
}

@end
