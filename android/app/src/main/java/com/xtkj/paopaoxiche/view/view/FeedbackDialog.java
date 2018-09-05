package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.service.FeedBackService;
import com.xtkj.paopaoxiche.service.WashService;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackDialog extends FullScreenWithStatusBarDialog {


    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.complete_button)
    Button completeButton;
    @BindView(R.id.describe_edit_text)
    EditText describeEditText;

    public FeedbackDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_feedback);
        ButterKnife.bind(this);
        backArrowImageButton.setOnClickListener(backButtonClickListener);
    }

    @OnClick(R.id.complete_button)
    public void onViewClicked() {
        String feedback = describeEditText.getText().toString();
        if (feedback != null && feedback.length() > 0) {
            feedbackSubmit(feedback);
            dismiss();
        }
    }

    private void feedbackSubmit(String feedback) {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(FeedBackService.class)
                .submitFeedback("0", Build.DEVICE, BaseApplication.getVersionName(), Build.VERSION.RELEASE, feedback, UserInfo.getUserPhone())
                .enqueue(new Callback<NoDataBean>() {
                    @Override
                    public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                        if (response.body() != null && response.body().getCode() != 401) {
                            Toast.makeText(BaseApplication.getContext(), "反馈成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BaseApplication.getContext(), "反馈失败，请重新登录", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NoDataBean> call, Throwable t) {
                        Toast.makeText(BaseApplication.getContext(), "反馈失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
