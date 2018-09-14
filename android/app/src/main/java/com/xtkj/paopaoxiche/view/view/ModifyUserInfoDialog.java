package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.bean.UpdateBean;
import com.xtkj.paopaoxiche.event.BaseEvent;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.DriverHomeModel;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.model.UserModel;
import com.xtkj.paopaoxiche.model.UserModel.UserInfoListener;
import com.xtkj.paopaoxiche.service.WashService;
import com.xtkj.paopaoxiche.utils.LocationUtils;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;
import com.xtkj.paopaoxiche.widget.SureDialog;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @BindView(R.id.address_image_button)
    ImageButton addressImageButton;
    @BindView(R.id.address_text_view)
    TextView addressTextView;
    @BindView(R.id.address_layout)
    RelativeLayout addressLayout;

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
//            addressTextView.setText(U);
        } else {
            addressLayout.setVisibility(View.GONE);
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

    @OnClick(R.id.address_image_button)
    public void onViewClicked() {
        DriverHomeModel driverHomeModel = DriverHomeModel.getInstance();
        SureDialog sureDialog = new SureDialog(getContext(), R.style.NormalDialog);
        sureDialog.setCancelBtnVisibility(View.VISIBLE);
        sureDialog.setMessage("您确定将洗车场地址修改到以下地址吗？\n" + driverHomeModel.getAddress());
        sureDialog.setClickListener(new SureDialog.ClickListener() {
            @Override
            public void sure(SureDialog dialog) {
                RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                        .create(WashService.class)
                        .updateAddress(UserInfo.getWashId() + "", driverHomeModel.getAddress(), driverHomeModel.getLatitude() + "", driverHomeModel.getLongitude() + "")
                        .enqueue(new Callback<NoDataBean>() {
                            @Override
                            public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                                if (response.body().getCode() == 200) {
                                    Toast.makeText(BaseApplication.getContext(), "修改地址成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(BaseApplication.getContext(), "修改地址失败，请重新登录", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<NoDataBean> call, Throwable t) {
                                Toast.makeText(BaseApplication.getContext(), "修改地址失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                dismiss();
            }
        });
        sureDialog.show();
    }
}
