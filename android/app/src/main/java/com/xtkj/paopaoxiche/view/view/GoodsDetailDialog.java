package com.xtkj.paopaoxiche.view.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.bean.WashShopBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsDetailDialog extends Dialog {


    @BindView(R.id.goods_image_view)
    ImageView goodsImageView;
    @BindView(R.id.original_price_text_view)
    TextView originalPriceTextView;
    @BindView(R.id.current_price_text_view)
    TextView currentPriceTextView;
    @BindView(R.id.describe_text_view)
    TextView describeTextView;
    @BindView(R.id.goods_name_text_view)
    TextView goodsNameTextView;

    public GoodsDetailDialog(@NonNull Context context, WashShopBean.DataBean.ListBean dataBean) {
        super(context, R.style.NoTitleBar);
        Window window = this.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_goods_detail);
        } else {
            setContentView(R.layout.dialog_goods_detail);
        }
        ButterKnife.bind(this);

        Glide.with(context).load(dataBean.getImage()).into(goodsImageView);
        goodsNameTextView.setText(dataBean.getName());
        originalPriceTextView.setText(String.format("原价:¥%s", dataBean.getOriginPrice()));
        currentPriceTextView.setText(String.format("现价:¥%s", dataBean.getCurrentPrice()));
        describeTextView.setText(dataBean.getDescribe());
    }
}
