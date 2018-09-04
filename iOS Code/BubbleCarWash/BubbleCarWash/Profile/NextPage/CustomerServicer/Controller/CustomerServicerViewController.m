//
//  CustomerServicerViewController.m
//  BubbleCarWash
//
//  Created by Rachel on 2018/9/4.
//  Copyright © 2018年 Sunshine Girl. All rights reserved.
//

#import "CustomerServicerViewController.h"
#import "FeedbackTextView.h"
#import "FunctionMacro.h"
#import "UILabel+CreateLabel.h"
#import "NSString+Category.h"
#import <ReactiveObjC/ReactiveObjC.h>

#define NUMBER @"1234567890"

static const CGFloat kHeaderViewHeight = 185;
static const CGFloat kTextViewHeight = 145;
static const NSUInteger kContactMaxLenght = 12;
static const NSUInteger kContactMinLenght = 4;
static const NSUInteger kRandomStrLength = 8;

@interface CustomerServicerViewController () <UITextFieldDelegate, UITextViewDelegate>

@property (nonatomic, strong) FeedbackTextView *tv_feedback;
@property (nonatomic, strong) UIView *headerView;
@property (nonatomic, strong) UIView *contantView;
@property (nonatomic, strong) UITextField *contactTextFiled;
@property (nonatomic, strong) UIBarButtonItem *sendBtn;
@property (nonatomic, strong) UILabel *lengthLabel;

@end

@implementation CustomerServicerViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [self setupUI];
    [self handleSingle];
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(hideKeyboard)];
    [self.view addGestureRecognizer:tap];
}

#pragma mark -

-(void)send {
//    CHECK_INTERNET();
    
    if (![self isValidTextFiledFormat]) {
        // 联系方式格式不对进行提示
        return;
    }
    
    [_sendBtn setEnabled:NO];
    [self hideKeyboard];
    // 开始loading
//    [UIApplication showBusyHUD:nil withTitle:Localstring(@"sending")];
}

/**
 *  判断联系方式格式是否正确
 */
- (BOOL)isValidTextFiledFormat {
    if (_contactTextFiled.hasText && _contactTextFiled.text.length < kContactMinLenght) {
        return NO;
    }
    
    return YES;
}

- (void)retryAlert {
    // 停止loading
//    [self stopAnyLoading];
    
    UIAlertController *alert  = [UIAlertController alertControllerWithTitle:nil message:@"反馈发送失败，请重试" preferredStyle:UIAlertControllerStyleAlert];
    UIAlertAction * cancel = [UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleCancel handler:^(UIAlertAction * _Nonnull action) {
        [self.navigationController popViewControllerAnimated:YES];
    }];
    UIAlertAction *retry = [UIAlertAction actionWithTitle:@"重试" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
        [self send];
    }];
    [alert addAction:cancel];
    [alert addAction:retry];
    [self presentViewController:alert animated:YES completion:nil];
}

- (void)hideKeyboard {
    [_tv_feedback.textView resignFirstResponder];
    [_contactTextFiled resignFirstResponder];
}

- (void)handleSingle {
    @weakify(self);
    [[_contactTextFiled.rac_textSignal
      filter:^BOOL(id value) {
          NSString *text = value;
          return text.length > kContactMaxLenght;
      }]
     subscribeNext:^(id _Nullable x) {
         @strongify(self);
         // 限制长度
         self.contactTextFiled.text = [self.contactTextFiled.text substringToIndex:kContactMaxLenght];
     }];
}

#pragma mark - Setup UI

- (void)setupUI {
    self.view.backgroundColor = RGBColor(237, 240, 244);
    self.title = @"反馈";
    _sendBtn = [[UIBarButtonItem alloc] initWithTitle:@"发送" style:UIBarButtonItemStylePlain target:self  action:@selector(send)];
    [_sendBtn setEnabled:NO];
    self.navigationItem.rightBarButtonItem = _sendBtn;
    
    _headerView = [[UIView alloc] initWithFrame:CGRectMake(0, 20, kScreenWidth, kHeaderViewHeight)];
    _headerView.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:_headerView];
    
    _tv_feedback = [[FeedbackTextView alloc] initWithFrame:CGRectMake(0, 0, kScreenWidth, kTextViewHeight)];
    _tv_feedback.textView.font = Font(18);
    _tv_feedback.backgroundColor = [UIColor whiteColor];
    _tv_feedback.textView.textContainerInset = UIEdgeInsetsMake(10, 8, 8, 8);
    _tv_feedback.textView.delegate = self;
    
    _lengthLabel = [UILabel createLabelWithFontSize:16 textColor:RGBColor(199, 204, 209)];
    _lengthLabel.frame = CGRectMake(kScreenWidth - 120, _headerView.frame.size.height - 40, 100, 30);
    _lengthLabel.backgroundColor = [UIColor clearColor];
    _lengthLabel.text = @"0/400";
    _lengthLabel.textAlignment = NSTextAlignmentRight;
    
    [_headerView addSubview:_tv_feedback];
    [_headerView addSubview:_lengthLabel];
    
    CGFloat headerViewMaxY = CGRectGetMaxY(_headerView.frame);
    _contantView = [[UIView alloc] initWithFrame:CGRectMake(0, headerViewMaxY + 20, kScreenWidth, 46)];
    _contantView.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:_contantView];
    
    _contactTextFiled = [[UITextField alloc] initWithFrame:CGRectMake(12, 8, kScreenWidth - 12 * 2, 30)];
    _contactTextFiled.font = Font(18);
    _contactTextFiled.placeholder = @"手机号码或QQ";
    _contactTextFiled.keyboardType = UIKeyboardTypeNumberPad;
    _contactTextFiled.delegate = self;
    [_contantView addSubview:_contactTextFiled];
}

#pragma mark - UITextView Delegete

- (void)textViewDidChange:(UITextView *)textView {
    _tv_feedback.placeholder_lab.hidden = textView.hasText;
    _sendBtn.enabled = (textView.hasText && ![NSString isBlackString:_tv_feedback.textView.text]);
    if ([textView.text length] >= 400) {
        textView.text = [textView.text substringToIndex:400];
    }
    
    NSUInteger existTextNum = [textView.text length];
    _lengthLabel.text = [NSString stringWithFormat:@"%lu/400", (unsigned long)existTextNum];
    [textView.undoManager disableUndoRegistration];
}

- (BOOL)textView:(UITextView *)textView shouldChangeTextInRange:(NSRange)range replacementText:(NSString *)text {
    [textView.undoManager disableUndoRegistration];
    return range.location >= 400 ? NO : YES;
}

#pragma mark - UITextFiled Delegete

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
    // 限制只能输入数字
    [_contactTextFiled.undoManager disableUndoRegistration];
    NSCharacterSet *cs = [[NSCharacterSet characterSetWithCharactersInString:NUMBER] invertedSet];
    NSString *filtered = [[string componentsSeparatedByCharactersInSet:cs] componentsJoinedByString:@""];
    
    return [string isEqualToString:filtered];
}

@end
