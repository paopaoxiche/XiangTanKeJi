package com.xtkj.paopaoxiche.view.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.model.UserModel;
import com.xtkj.paopaoxiche.service.UserService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessStateDialog extends Dialog {
    @BindView(R.id.open_business_button)
    Button openBusinessButton;
    @BindView(R.id.pause_business_button)
    Button pauseBusinessButton;
    @BindView(R.id.stop_business_button)
    Button stopBusinessButton;

    TextView stateTextView;
    String stateString = "营业";

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
                stateString = "营业";
                updateState(UserInfo.getWashId(), String.valueOf(1));
                dismiss();
                break;
            case R.id.pause_business_button:
                stateString = "歇业";
                updateState(UserInfo.getWashId(), String.valueOf(2));
                dismiss();
                break;
            case R.id.stop_business_button:
                stateString = "停业";
                updateState(UserInfo.getWashId(), String.valueOf(3));
                dismiss();
                break;
        }
    }

    private void updateState(int washId, String status) {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(UserService.class)
                .updateBusinessState(washId, status)
                .enqueue(new Callback<NoDataBean>() {
                    @Override
                    public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                        if (stateTextView == null) {
                            return;
                        }
                        if (response.body().getCode() != 401) {
                            stateTextView.setText(stateString);
                        } else {
                            Toast.makeText(BaseApplication.getContext(), "修改洗车状态失败，请重新登录", Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onFailure(Call<NoDataBean> call, Throwable t) {
                        Toast.makeText(BaseApplication.getContext(), "修改洗车状态失败", Toast.LENGTH_SHORT);
                    }
                });
    }
}
