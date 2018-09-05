package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.bean.RecentWashListBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.service.FeedBackService;
import com.xtkj.paopaoxiche.service.WashService;
import com.xtkj.paopaoxiche.view.CarWashMain.RecentWashAdapter;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WashCarListDialog extends FullScreenWithStatusBarDialog {


    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.wash_car_list_view)
    ListView washCarListView;

    RecentWashAdapter recentWashAdapter;

    public WashCarListDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_wash_car_list);
        ButterKnife.bind(this);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);

        getWashList();
    }

    public void getWashList() {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(WashService.class)
                .getRecentWashList(UserInfo.getWashId(), 50)
                .enqueue(new Callback<RecentWashListBean>() {
                    @Override
                    public void onResponse(Call<RecentWashListBean> call, Response<RecentWashListBean> response) {
                        if (response == null || response.body() == null) {
                            return;
                        }
                        if (response.body().getCode() != 401) {
                            recentWashAdapter = new RecentWashAdapter(getContext(), response.body().getData());
                            washCarListView.setAdapter(recentWashAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<RecentWashListBean> call, Throwable t) {

                    }
                });
    }
}
