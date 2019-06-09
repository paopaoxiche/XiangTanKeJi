package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.view.WebView.WebViewActivity;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageAdDialog extends FullScreenWithStatusBarDialog {
    @BindView(R.id.ad_image_view)
    ImageView adImageView;
    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.login_button)
    Button loginButton;

    String image;
    String url;

    public ImageAdDialog(Context context, boolean statusBarVisible, String image, String url) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_ad_image);
        ButterKnife.bind(this);
        backArrowImageButton.setOnClickListener(backButtonClickListener);

        this.image = image;
        this.url = url;

        Glide.with(getContext()).load(image).into(adImageView);
    }

    @OnClick(R.id.login_button)
    public void onViewClicked() {
        if (!TextUtils.isEmpty(url)) {
            Intent intent = new Intent(getContext(), WebViewActivity.class);
            intent.putExtra(AppConstant.WEB_NTENT_URL, url);
            getContext().startActivity(intent);
        }
    }
}
