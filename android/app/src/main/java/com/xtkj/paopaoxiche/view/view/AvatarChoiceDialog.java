package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tencent.mm.opensdk.utils.Log;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.bean.AvatarBean;
import com.xtkj.paopaoxiche.bean.CarWashInfoBean;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.bean.UpdateBean;
import com.xtkj.paopaoxiche.event.BaseEvent;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.model.UserModel;
import com.xtkj.paopaoxiche.service.UserService;
import com.xtkj.paopaoxiche.utils.PreferUtils;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvatarChoiceDialog extends FullScreenWithStatusBarDialog implements AdapterView.OnItemClickListener {


    @BindView(R.id.choose_avatar_text_view)
    TextView chooseAvatarTextView;
    @BindView(R.id.avatar_confirm_button)
    Button avatarConfirmButton;
    @BindView(R.id.choose_picture_linear_layout)
    LinearLayout choosePictureLinearLayout;
    @BindView(R.id.avatar_grid_view)
    GridView gridView;
    @BindView(R.id.avatar_choose_user_image_view)
    ImageView avatarImageView;

    AvatarAdapter avatarAdapter;
    AvatarBean.DataBean dataBean = null;

    public AvatarChoiceDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_modify_avatar);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);
        ButterKnife.bind(this);

        Glide.with(getContext()).load(UserInfo.getAvatar()).into(avatarImageView);

        requestAvatarList();
    }

    @OnClick({R.id.avatar_confirm_button, R.id.choose_picture_linear_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.avatar_confirm_button:
                if (dataBean == null) {
                    break;
                }
                UserModel.getInstance().updateDefaultAvatar(dataBean.getId(), dataBean.getImg());
                break;
            case R.id.choose_picture_linear_layout:
                EventBus.getDefault().post(new BaseEvent(BaseEvent.CAR_WASH_PICK_AVATAR));
                break;
        }
        dismiss();
    }

    private void requestAvatarList() {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(UserService.class)
                .getAvatarList()
                .enqueue(new Callback<AvatarBean>() {
                    @Override
                    public void onResponse(Call<AvatarBean> call, Response<AvatarBean> response) {
                        if (response.body().getCode() == 200) {
                            avatarAdapter  = new AvatarAdapter(AvatarChoiceDialog.this.getContext(), response.body().getData());
                            gridView.setAdapter(avatarAdapter);
                            gridView.setOnItemClickListener(AvatarChoiceDialog.this);
                        } else {
                            Toast.makeText(AvatarChoiceDialog.this.getContext(), "获取头像列表失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AvatarBean> call, Throwable t) {
                        Toast.makeText(AvatarChoiceDialog.this.getContext(), "获取头像列表失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        CircleImageView circleImageView = (CircleImageView) view.findViewById(R.id.choose_avatar_image_view);
//        if (circleImageView != null && circleImageView instanceof CircleImageView) {
//            circleImageView.setBorderWidth(4);
//            circleImageView.setBorderColor(this.getContext().getResources().getColor(R.color.orange));
//        }
        dataBean = ((AvatarBean.DataBean)avatarAdapter.getItem(position));
        Glide.with(getContext()).load(((AvatarBean.DataBean)avatarAdapter.getItem(position)).getImg()).into(avatarImageView);
    }
}
