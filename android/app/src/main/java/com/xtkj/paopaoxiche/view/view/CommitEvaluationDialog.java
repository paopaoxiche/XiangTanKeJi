package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.UserService;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 提交评论的对话框
 * Created by sky on 2018/9/2.
 */
public class CommitEvaluationDialog extends FullScreenWithStatusBarDialog {
    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.ll_stars)
    LinearLayout llStars;
    @BindView(R.id.et_msg)
    EditText etMsg;
    @BindView(R.id.button_commit)
    Button buttonCommit;
    private int serviceId;
    private int curPoint = 1;

    EvaluateCallback evaluateCallback;

    public CommitEvaluationDialog(int serviceId, EvaluateCallback evaluateCallback, Context context) {
        super(context, false);
        this.evaluateCallback = evaluateCallback;
        this.serviceId = serviceId;
        setContentView(R.layout.dialog_commit_evaluation);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backArrowImageButton.setOnClickListener(backButtonClickListener);
        buttonCommit.setOnClickListener(v -> commitEvaluation());
        for (int i = 0; i < llStars.getChildCount(); i++) {
            llStars.getChildAt(i).setOnClickListener(new StarClickListener(i));
        }
    }

    private class StarClickListener implements View.OnClickListener {
        private int index;

        StarClickListener(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            for (int i = 0; i < llStars.getChildCount(); i++) {
                if (i > index) {
                    ((ImageView) llStars.getChildAt(i)).setImageResource(R.drawable.star);
                } else {
                    ((ImageView) llStars.getChildAt(i)).setImageResource(R.drawable.star_select);
                }
            }

            curPoint = index + 1;
        }
    }

    private void commitEvaluation() {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                      .create(UserService.class)
                      .commitEvaluateRecord(serviceId, curPoint, etMsg.getText().toString())
                      .enqueue(new Callback<NoDataBean>() {
                          @Override
                          public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                              if (!isShowing()) {
                                  return;
                              }

                              NoDataBean bean = response.body();
                              if (bean.getCode() != 200) {
                                  Toast.makeText(BaseApplication.getContext(), "提交评价失败！",
                                                 Toast.LENGTH_LONG).show();
                              } else {
                                  Toast.makeText(BaseApplication.getContext(), "评价成功！",
                                                 Toast.LENGTH_LONG).show();
                                  if (evaluateCallback != null) {
                                      evaluateCallback.success(serviceId);
                                  }
                                  dismiss();
                              }

                          }

                          @Override
                          public void onFailure(Call<NoDataBean> call, Throwable t) {
                              if (!isShowing()) {
                                  return;
                              }

                              Toast.makeText(BaseApplication.getContext(), "提交评价失败！",
                                             Toast.LENGTH_LONG).show();
                          }
                      });
    }

    public interface EvaluateCallback {
        void success(int serviceId);
    }
}
