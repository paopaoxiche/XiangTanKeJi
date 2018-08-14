package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.UserInfo;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyNickNameDialog extends FullScreenWithStatusBarDialog {
    @BindView(R.id.complete_button)
    Button completeButton;
    @BindView(R.id.nick_name_text_view)
    EditText nickNameTextView;

    public ModifyNickNameDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_modify_nick_name);
        ButterKnife.bind(this);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);
        nickNameTextView.setText(UserInfo.getNickName());
    }

    @OnClick({R.id.complete_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.complete_button:
                if (nickNameTextView.getText().toString() != null) {
                    UserInfo.setNickName(nickNameTextView.getText().toString());
                }
                dismiss();
                break;
        }
    }
}
