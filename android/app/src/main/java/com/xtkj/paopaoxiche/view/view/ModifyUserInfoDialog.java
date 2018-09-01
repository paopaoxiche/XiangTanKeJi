package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.bean.UpdateBean;
import com.xtkj.paopaoxiche.event.BaseEvent;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.model.UserModel;
import com.xtkj.paopaoxiche.model.UserModel.UserInfoListener;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ModifyUserInfoDialog extends FullScreenWithStatusBarDialog implements UserInfoListener {
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
    @BindView(R.id.ib_enter_car_type_list)
    ImageButton ibEnterCarTypeList;
    @BindView(R.id.enter_car_layout)
    RelativeLayout enterCarlayout;

    public ModifyUserInfoDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_modify_user_info);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);
        ButterKnife.bind(this);

        nickNameTextView.setText(UserInfo.getNickName());
        if (UserInfo.avatarNotNull()) {
            Glide.with(getContext()).load(UserInfo.getAvatar()).into(avatarImageView);
        }
        accountTextView.setText(UserInfo.getUserPhone());
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date(UserInfo.getRegTime()));
        joinTimeTextView.setText(time);
        if (!UserInfo.isDriver()) {
            enterCarlayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void show() {
        super.show();
        UserModel.getInstance().addListener(this);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        UserModel.getInstance().removeListener(this);
    }

    @OnClick({R.id.back_arrow_image_button, R.id.modify_nick_name_image_button, R.id.modify_avatar_image_button,
            R.id.ib_enter_car_type_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_arrow_image_button:
                break;
            case R.id.modify_nick_name_image_button:
                new ModifyNickNameDialog(getContext(), true).show();
                break;
            case R.id.modify_avatar_image_button:
                EventBus.getDefault().post(new BaseEvent(BaseEvent.CAR_WASH_PICK_AVATAR));
                break;
            case R.id.ib_enter_car_type_list:
                new MyCarListDialog(getContext()).show();
                break;
        }
        dismiss();
    }

    @Override
    public void modifyUserInfo(String modifyType) {
        if (modifyType.equals(AppConstant.NICK_NAME)) {
            nickNameTextView.setText(UserInfo.getNickName());
        }
        if (modifyType.equals(AppConstant.AVATAR)) {
            Glide.with(getContext()).load(UserInfo.getAvatar()).into(avatarImageView);
        }
    }

    @Override
    public void checkUpdate(UpdateBean updateBean) {

    }

    @Override
    public void timeOut(String modifyType) {

    }
}
