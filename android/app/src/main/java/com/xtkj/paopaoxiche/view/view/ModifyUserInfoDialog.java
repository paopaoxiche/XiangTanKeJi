package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.UserInfo;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ModifyUserInfoDialog extends FullScreenWithStatusBarDialog {
    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.modify_nick_name_image_button)
    ImageButton modifyNickNameImageButton;
    @BindView(R.id.nick_name_text_view)
    TextView nickNameTextView;
    @BindView(R.id.modify_avatar_image_button)
    ImageButton modifyAvatarImageButton;
    @BindView(R.id.avatar_image_view)
    CircleImageView avatarImageView;
    @BindView(R.id.account_text_view)
    TextView accountTextView;
    @BindView(R.id.join_time_text_view)
    TextView joinTimeTextView;

    public ModifyUserInfoDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_modify_user_info);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);
        ButterKnife.bind(this);

        nickNameTextView.setText(UserInfo.getNickName());
    }

    @OnClick({R.id.back_arrow_image_button, R.id.modify_nick_name_image_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_arrow_image_button:
                break;
            case R.id.modify_nick_name_image_button:
                new ModifyNickNameDialog(getContext(), true).show();
                break;
        }
    }
}
