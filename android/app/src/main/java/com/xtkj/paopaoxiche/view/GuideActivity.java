package com.xtkj.paopaoxiche.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.bean.ADBean;
import com.xtkj.paopaoxiche.contract.IGuideContract;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.presenter.GuidePresenterImpl;
import com.xtkj.paopaoxiche.service.AdService;
import com.xtkj.paopaoxiche.utils.PreferUtils;
import com.xtkj.paopaoxiche.view.WebView.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuideActivity extends BaseActivity implements IGuideContract.IGuideView {

    IGuideContract.IGuidePresenter guidePresenter;
    @BindView(R.id.guide_ad_image_view)
    ImageView guideAdImageView;
    @BindView(R.id.original_guide_layout)
    RelativeLayout originalGuideLayout;
    @BindView(R.id.guide_count_down_text_view)
    TextView guideCountDownTextView;
//    @BindView(R.id.guide_button)
//    Button guideButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        new GuidePresenterImpl(this);
        guidePresenter.onCreate();

        guidePresenter.autoLogin();

        String adUrl = PreferUtils.getInstance().getString(AppConstant.GUIDE_AD_FILE_PATH);
        if (adUrl != null && adUrl.length() > 0) {
            originalGuideLayout.setVisibility(View.GONE);
            guideAdImageView.setVisibility(View.VISIBLE);
            Glide.with(this).load(adUrl).into(guideAdImageView);
        }
        checkAdvise();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initValues() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void startActivityForIntent(Intent intent, Class clazz) {
        intent.setClass(this, clazz);
//        intent.setClass(this, CarWashMainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setCountDownText(String text) {
        guideCountDownTextView.setText(text);
    }

    @Override
    public void setPresenter(IGuideContract.IGuidePresenter iGuidePresenter) {
        guidePresenter = iGuidePresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        guidePresenter.onDestroy();
    }

    private void checkAdvise() {
        RetrofitClient.newInstance(ApiField.BASEURL)
                .create(AdService.class)
                .getGuideAd("1")
                .enqueue(new Callback<ADBean>() {
                    @Override
                    public void onResponse(Call<ADBean> call, Response<ADBean> response) {
                        if (response.body().getCode() != 200) {
                            return;
                        }
                        ADBean.DataBean dataBean = response.body().getData();
                        String md5 = PreferUtils.getInstance().getString(AppConstant.GUIDE_AD_MD5);
                        if (md5.equals(dataBean.getMd5())) {
                            return;
                        }
                        if (dataBean.getImg() == null || dataBean.getImg().length() == 0) {
                            return;
                        }
                        PreferUtils.getInstance().putString(AppConstant.GUIDE_AD_MD5, dataBean.getMd5());
                        PreferUtils.getInstance().putString(AppConstant.GUIDE_AD_FILE_PATH, dataBean.getImg());
                        PreferUtils.getInstance().putString(AppConstant.GUIDE_AD_URL, dataBean.getUrl());
                    }

                    @Override
                    public void onFailure(Call<ADBean> call, Throwable t) {

                    }
                });
    }

    private void DownloadImage() {

    }

    @OnClick(R.id.guide_ad_image_view)
    public void onViewClicked() {
        String url = PreferUtils.getInstance().getString(AppConstant.GUIDE_AD_URL);
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(AppConstant.WEB_NTENT_URL,url);
        startActivity(intent);
    }
}
