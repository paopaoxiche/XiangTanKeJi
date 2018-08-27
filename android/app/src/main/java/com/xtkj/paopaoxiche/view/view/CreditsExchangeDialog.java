package com.xtkj.paopaoxiche.view.view;

import android.content.Context;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

/**
 * 积分兑换界面
 *
 * @author sky on 2018/8/27
 * @since 1.0
 */
public class CreditsExchangeDialog extends FullScreenWithStatusBarDialog {

    public CreditsExchangeDialog(Context context) {
        super(context, true);
        setContentView(R.layout.dialog_credits_exchange);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);
    }
}
