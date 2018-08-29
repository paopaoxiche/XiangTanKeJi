//
//  CreateProductCell.m
//  BubbleCarWash
//
//  Created by paomoliu on 2018/8/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CreateProductCell.h"
#import "UIColor+Category.h"

#pragma mark - CreateProductNameCell

@interface CreateProductNameCell ()

@property (weak, nonatomic) IBOutlet UILabel *proTextLabel;
@property (weak, nonatomic) IBOutlet UILabel *proValueUnitLabel;
@property (weak, nonatomic) IBOutlet UITextField *proValueTextField;

@end

@implementation CreateProductNameCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

- (void)setTitle:(NSString *)title {
    _proTextLabel.text = title;
    _proValueTextField.placeholder = [NSString stringWithFormat:@"请输入%@", title];
}

- (void)setIsPrice:(BOOL)isPrice {
    if (!isPrice) {
        _proValueUnitLabel.hidden = YES;
    }
}

@end

#pragma mark - CreateProductNameCell

@interface CreateProductDescCell ()

@property (weak, nonatomic) IBOutlet UILabel *proDescLabel;
@property (weak, nonatomic) IBOutlet UITextView *proDescTextView;
@property (weak, nonatomic) IBOutlet UILabel *placeholderLabel;

@end

@implementation CreateProductDescCell

- (void)awakeFromNib {
    [super awakeFromNib];
}

@end

#pragma mark - CreateProductNameCell

@interface CreateProductPictureCell ()

@property (weak, nonatomic) IBOutlet UIImageView *proPictureImgView;
@property (weak, nonatomic) IBOutlet UIView *uploadView;

@end

@implementation CreateProductPictureCell

- (void)awakeFromNib {
    [super awakeFromNib];
    
    _uploadView.layer.borderWidth = 1;
    _uploadView.layer.borderColor = [UIColor rgbWithRed:221 green:221 blue:221].CGColor;
}

@end
