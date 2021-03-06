package com.xtkj.paopaoxiche.view.DriverMain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.bean.UpdateBean;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.contract.IDriverContract;
import com.xtkj.paopaoxiche.model.update.DownloadAPKCallback;
import com.xtkj.paopaoxiche.model.update.DownloadManager;
import com.xtkj.paopaoxiche.view.view.CreditsExchangeDialog;
import com.xtkj.paopaoxiche.view.view.ExtensionDialog;
import com.xtkj.paopaoxiche.view.view.FeedbackDialog;
import com.xtkj.paopaoxiche.view.view.ModifyUserInfoDialog;
import com.xtkj.paopaoxiche.view.view.MyCouponsDialog;
import com.xtkj.paopaoxiche.view.view.MyCustomDialog;
import com.xtkj.paopaoxiche.view.view.MyEvaluateDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyInfoFragment extends BaseFragmemt implements IDriverContract.IMyInfoView {

    @BindView(R.id.portrait_image_view)
    ImageView portraitImageView;
    @BindView(R.id.username_text_view)
    TextView usernameTextView;
    @BindView(R.id.phone_image_view)
    ImageView phoneImageView;
    @BindView(R.id.phone_number_text_view)
    TextView phoneNumberTextView;
    @BindView(R.id.modify_user_image_button)
    ImageButton modifyUserImageButton;
    @BindView(R.id.my_score)
    LinearLayout myScore;
    @BindView(R.id.my_coupons)
    LinearLayout myCoupons;
    @BindView(R.id.my_comsumption)
    LinearLayout myComsumption;
    @BindView(R.id.my_evaluation)
    LinearLayout myEvaluation;
    @BindView(R.id.my_updatecf)
    LinearLayout myUpdatecf;
    @BindView(R.id.my_customer_service)
    LinearLayout myCustomerService;
    @BindView(R.id.my_promote)
    LinearLayout myPromote;
    @BindView(R.id.remind_update_text_view)
    TextView remindUpdateTextView;
    Unbinder unbinder;
    private IDriverContract.IMyInfoPresenter myInfoPresenter;

    public MyInfoFragment() {
        // Required empty public constructor
    }

    public static MyInfoFragment newInstance() {
        MyInfoFragment fragment = new MyInfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_info, container, false);
        unbinder = ButterKnife.bind(this, view);

        phoneNumberTextView.setText(UserInfo.getUserPhone());
        usernameTextView.setText(UserInfo.getNickName());

        if (UserInfo.avatarNotNull()) {
            Glide.with(getContext()).load(UserInfo.getAvatar()).into(portraitImageView);
        }

        myInfoPresenter.onCreate();

        return view;
    }


    @Override
    public void setPresenter(IDriverContract.IMyInfoPresenter iMyInfoPresenter) {
        myInfoPresenter = iMyInfoPresenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        myInfoPresenter.onDestroy();
    }

    long time = 0;
    @OnClick({R.id.my_score, R.id.my_coupons, R.id.my_comsumption, R.id.my_evaluation,
            R.id.my_updatecf, R.id.my_customer_service, R.id.my_promote, R.id.modify_user_image_button,
            R.id.portrait_image_view})
    public void onViewClicked(View view) {
        if (System.currentTimeMillis() - time < 500) {
            return;
        }
        time = System.currentTimeMillis();
        switch (view.getId()) {
            case R.id.my_score:
                new CreditsExchangeDialog(getContext()).show();
                break;
            case R.id.my_coupons:
                new MyCouponsDialog(getContext(), true).show();
                break;
            case R.id.my_comsumption:
                new MyCustomDialog(getContext(), true).show();
                break;
            case R.id.my_evaluation:
                new MyEvaluateDialog(getContext(), true).show();
                break;
            case R.id.my_updatecf:
                if (UserInfo.getUpdateBean() == null) {
                    Toast.makeText(getContext(), "当前已是最新版本", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (!UserInfo.getUpdateBean().getData().isHasNewApp()) {
                    Toast.makeText(getContext(), "当前已是最新版本", Toast.LENGTH_SHORT).show();
                } else {
                    UpdateBean.DataBean updateBean = UserInfo.getUpdateBean().getData();
                    DownloadManager.downloadAPK(updateBean.getDownloadUrl(), new DownloadAPKCallback(getActivity(), false));
                }
                break;
            case R.id.my_customer_service:
                new FeedbackDialog(getContext(), true).show();
                break;
            case R.id.my_promote:
                new ExtensionDialog(getContext(), true).show();
                break;
            case R.id.portrait_image_view:
            case R.id.modify_user_image_button:
                new ModifyUserInfoDialog(getContext(), true).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void updateUserInfo(String modifyType) {
        if (modifyType.equals(AppConstant.NICK_NAME)) {
            usernameTextView.setText(UserInfo.getNickName());
        }
        if (modifyType.equals(AppConstant.AVATAR)) {
            Glide.with(getContext()).load(UserInfo.getAvatar()).into(portraitImageView);
        }
    }

    @Override
    public void hasUpdate() {
        remindUpdateTextView.setText("请升级！");
    }
}
