package com.xtkj.paopaoxiche.view.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessStateDialog extends Dialog {
    @BindView(R.id.open_business_button)
    Button openBusinessButton;
    @BindView(R.id.pause_business_button)
    Button pauseBusinessButton;
    @BindView(R.id.stop_business_button)
    Button stopBusinessButton;

    TextView stateTextView;

    public BusinessStateDialog(@NonNull Context context, TextView textView) {
        super(context);
        setContentView(R.layout.dialog_business_state);
        ButterKnife.bind(this);
        stateTextView = textView;
    }

    public BusinessStateDialog(@NonNull Context context, int themeResId, TextView textView) {
        super(context, themeResId);
        setContentView(R.layout.dialog_business_state);
        ButterKnife.bind(this);
        stateTextView = textView;
    }


    @OnClick({R.id.open_business_button, R.id.pause_business_button, R.id.stop_business_button})
    public void onViewClicked(View view) {
        if (stateTextView == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.open_business_button:
                stateTextView.setText("开业");
                dismiss();
                break;
            case R.id.pause_business_button:
                stateTextView.setText("歇业");
                dismiss();
                break;
            case R.id.stop_business_button:
                stateTextView.setText("停业");
                dismiss();
                break;
        }
    }
}
