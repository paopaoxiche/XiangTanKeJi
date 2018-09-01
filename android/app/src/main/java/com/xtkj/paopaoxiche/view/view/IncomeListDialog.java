package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.bean.IncomeBean;
import com.xtkj.paopaoxiche.bean.LoginBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.service.WashService;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncomeListDialog extends FullScreenWithStatusBarDialog {
    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.year_income_text_view)
    TextView yearIncomeTextView;
    @BindView(R.id.month_income_text_view)
    TextView monthIncomeTextView;
    @BindView(R.id.tip_income_text_view)
    TextView tipIncomeTextView;
    @BindView(R.id.total_income_text_view)
    TextView totalIncomeTextView;
    @BindView(R.id.income_list_view)
    ListView incomeListView;

    public IncomeListDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_income_list);
        ButterKnife.bind(this);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        yearIncomeTextView.setText(year + "年");
        monthIncomeTextView.setText(month + "月");

        getIncome(month);
    }

    private void getIncome(int month) {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(WashService.class)
                .getIncome(UserInfo.getWashId(), month)
                .enqueue(new Callback<IncomeBean>() {
                    @Override
                    public void onResponse(Call<IncomeBean> call, Response<IncomeBean> response) {
                        if (response == null || response.body() == null) {
                            return;
                        }
                        if (response.body().getCode() != 401) {
                            IncomeAdapter incomeAdapter = new IncomeAdapter(getContext(), response.body().getData());
                            incomeListView.setAdapter(incomeAdapter);
                            int income = 0;
                            for (IncomeBean.DataBean dataBean : response.body().getData()) {
                                income += dataBean.getTotalMoney();
                            }
                            totalIncomeTextView.setText(income + "元");
                        } else {
                            Toast.makeText(getContext(), "获取收入列表失败, 请重新登录", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<IncomeBean> call, Throwable t) {
                        Toast.makeText(getContext(), "获取收入列表失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

}
