package com.xtkj.paopaoxiche.view.CarWashMain;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.bean.UpdateBean;
import com.xtkj.paopaoxiche.contract.ICarWashContract;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.DriverHomeModel;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.model.update.DownloadAPKCallback;
import com.xtkj.paopaoxiche.model.update.DownloadManager;
import com.xtkj.paopaoxiche.service.WashService;
import com.xtkj.paopaoxiche.view.view.BusinessStateDialog;
import com.xtkj.paopaoxiche.view.view.ExtensionDialog;
import com.xtkj.paopaoxiche.view.view.FeedbackDialog;
import com.xtkj.paopaoxiche.view.view.GoodsListDialog;
import com.xtkj.paopaoxiche.view.view.IncomeListDialog;
import com.xtkj.paopaoxiche.view.view.ModifyUserInfoDialog;
import com.xtkj.paopaoxiche.view.view.MyEvaluateDialog;
import com.xtkj.paopaoxiche.view.view.WashListManagerDialog;
import com.xtkj.paopaoxiche.widget.SureDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarWashMineFragment extends BaseFragmemt implements ICarWashContract.IMineView {

    ICarWashContract.IMinePresenter minePresenter;

    Unbinder unbinder;

    @BindView(R.id.portrait_image_view)
    ImageView portraitImageView;
    @BindView(R.id.username_text_view)
    TextView usernameTextView;
    @BindView(R.id.phone_image_view)
    ImageView phoneImageView;
    @BindView(R.id.phone_number_text_view)
    TextView phoneNumberTextView;
    @BindView(R.id.my_honor_text_view)
    TextView myHonorTextView;
    @BindView(R.id.my_honor_manager_linear_layout)
    LinearLayout myHonorManagerLinearLayout;
    @BindView(R.id.wash_count_text_view)
    TextView washCountTextView;
    @BindView(R.id.wash_count_linear_layout)
    LinearLayout washCountLinearLayout;
    @BindView(R.id.car_wash_manager_linear_layout)
    LinearLayout carWashManagerLinearLayout;
    @BindView(R.id.goods_manager_linear_layout)
    LinearLayout goodsManagerLinearLayout;
    @BindView(R.id.income_linear_layout)
    LinearLayout incomeLinearLayout;
    @BindView(R.id.my_evaluation)
    LinearLayout myEvaluation;
    @BindView(R.id.state_text_view)
    TextView stateTextView;
    @BindView(R.id.state_linear_layout)
    LinearLayout stateLinearLayout;
    @BindView(R.id.my_updatecf)
    LinearLayout myUpdatecf;
    @BindView(R.id.my_customer_service)
    LinearLayout myCustomerService;
    @BindView(R.id.my_promote)
    LinearLayout myPromote;
    @BindView(R.id.wash_modify_user_image_button)
    ImageButton modifyUserImageButton;
    @BindView(R.id.remind_update_text_view)
    TextView remindUpdateTextView;
    @BindView(R.id.money_text_view)
    TextView moneyTextView;
    @BindView(R.id.money_button)
    TextView moneyButton;

    String remain = "0";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_wash_mine, null);
        unbinder = ButterKnife.bind(this, view);

        initView(view);

        minePresenter.onCreate();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getRemain();
            }
        },500);


        return view;
    }

    private void initView(View view) {
        phoneNumberTextView.setText(UserInfo.getUserPhone());
        usernameTextView.setText(UserInfo.getNickName());
        washCountTextView.setText(UserInfo.getWashCount() + "次");
        myHonorTextView.setText(UserInfo.getHonor());
        if (UserInfo.avatarNotNull()) {
            Glide.with(getContext()).load(UserInfo.getAvatar()).into(portraitImageView);
        }
        switch (UserInfo.getAuthStatus()) {
            case -1:
                stateTextView.setText("停业");
                break;
            case 0:
                stateTextView.setText("歇业");
                break;
            case 1:
                stateTextView.setText("营业");
                break;
        }
    }

    @Override
    public void setPresenter(ICarWashContract.IMinePresenter iMinePresenter) {
        minePresenter = iMinePresenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }

        minePresenter.onDestroy();
    }

    long time = 0;

    @OnClick({R.id.car_wash_manager_linear_layout, R.id.goods_manager_linear_layout, R.id.income_linear_layout,
            R.id.my_evaluation, R.id.state_linear_layout, R.id.my_updatecf, R.id.my_customer_service,
            R.id.my_promote, R.id.wash_modify_user_image_button})
    public void onViewClicked(View view) {
        if (System.currentTimeMillis() - time < 500) {
            return;
        }
        time = System.currentTimeMillis();
        switch (view.getId()) {
            case R.id.car_wash_manager_linear_layout:
                new WashListManagerDialog(getContext(), true).show();
                break;
            case R.id.goods_manager_linear_layout:
                new GoodsListDialog(getContext(), true).show();
                break;
            case R.id.income_linear_layout:
                new IncomeListDialog(getContext(), true).show();
                break;
            case R.id.my_evaluation:
                new MyEvaluateDialog(getContext(), true).show();
                break;
            case R.id.state_linear_layout:
                new BusinessStateDialog(getContext(), stateTextView).show();
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
            case R.id.wash_modify_user_image_button:
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

    @OnClick(R.id.money_button)
    public void onViewClicked() {
        SureDialog sureDialog = new SureDialog(getContext(), R.style.NormalDialog);
        sureDialog.setCancelBtnVisibility(View.VISIBLE);
        sureDialog.setMessage("您确定要提取账户余额吗？");
        sureDialog.setClickListener(new SureDialog.ClickListener() {
            @Override
            public void sure(SureDialog dialog) {
                if (remain.equals("0") || remain.equals("0.0") || remain.equals("0.00")) {
                    Toast.makeText(BaseApplication.getContext(), "账户无余额", Toast.LENGTH_SHORT).show();
                    return;
                }
                RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                        .create(WashService.class)
                        .drawDeposits(UserInfo.getWashId() + "", remain)
                        .enqueue(new Callback<NoDataBean>() {
                            @Override
                            public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                                if (response.body() != null && response.body().getCode() == 200) {
                                    Toast.makeText(BaseApplication.getContext(), "申请提现成功", Toast.LENGTH_SHORT).show();
                                    moneyTextView.setText("账户余额 0元");
                                    getRemain();
                                } else {
                                    if (response != null) {
                                        Toast.makeText(BaseApplication.getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(BaseApplication.getContext(), "申请提现失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<NoDataBean> call, Throwable t) {
                                Toast.makeText(BaseApplication.getContext(), "申请提现失败", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        sureDialog.show();
    }

    private void getRemain() {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(WashService.class)
                .getBalance(UserInfo.getWashId())
                .enqueue(new Callback<NoDataBean>() {
                    @Override
                    public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                        if (response.body() != null && response.body().getCode() == 200) {
                            remain = response.body().getData().toString();
                            moneyTextView.setText("账户余额 " + remain + "元");
                        } else {
                            Toast.makeText(BaseApplication.getContext(), "获取余额失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NoDataBean> call, Throwable t) {
                        Toast.makeText(BaseApplication.getContext(), "获取余额失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
