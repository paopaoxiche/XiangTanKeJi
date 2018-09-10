//
//  CertificationCell.h
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/1.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef NS_ENUM(NSInteger, CertificationCellType) {
    CertificationCellTypeAdd,          // 添加认证
    CertificationCellTypeIn,           // 认证中
    CertificationCellTypeDone          // 认证完成
};

@class CertificationCarTypeCell;
@class CertificationInfoCell;

@protocol CertificationCellDelegate <NSObject>

@optional

- (void)onSelectedBtnClicked:(CertificationCarTypeCell *)cell;
- (void)onUploadViewClicked:(CertificationInfoCell *)cell;

@end

@interface CertificationTitleCell : UITableViewCell

@property (nonatomic, copy) NSString *title;

@end

@interface CertificationCarTypeCell : UITableViewCell

@property (nonatomic, weak) id<CertificationCellDelegate> delegate;
@property (nonatomic, copy) NSString *carImgName;
@property (nonatomic, copy) NSString *carDesc;
@property (nonatomic, copy) NSString *selectImgName;

@end

@interface CertificationInfoCell : UITableViewCell

@property (nonatomic, weak) id<CertificationCellDelegate> delegate;
@property (nonatomic, strong) UIImage *cerDetailImage;
@property (nonatomic, strong) UIImage *cerImage;

- (void)setCerDesc:(NSString *)desc cerImgName:(NSString *)name cerType:(CertificationCellType)type;

@end
