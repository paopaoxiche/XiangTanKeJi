package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.model.GoodsModel;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.io.File;

public class ModifyGoodsDialog extends FullScreenWithStatusBarDialog {

    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.complete_button)
    Button completeButton;
    @BindView(R.id.goods_name)
    EditText goodsName;
    @BindView(R.id.goods_current_price)
    EditText goodsCurrentPrice;
    @BindView(R.id.goods_original_price)
    EditText goodsOriginalPrice;
    @BindView(R.id.describe_edit_text)
    EditText describeEditText;
    @BindView(R.id.upload_button)
    Button uploadButton;
    @BindView(R.id.goods_image_view)
    ImageView goodsImageView;

    int id = 0;

    File imageFile;

    public ModifyGoodsDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_modify_goods);
        ButterKnife.bind(this);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);
    }

    @OnClick({R.id.complete_button, R.id.upload_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.complete_button:
                if (TextUtils.isEmpty(describeEditText.getText().toString())
                        && TextUtils.isEmpty(goodsName.getText().toString())
                        && TextUtils.isEmpty(goodsCurrentPrice.getText().toString())
                        && TextUtils.isEmpty(goodsOriginalPrice.getText().toString())) {
                    GoodsModel.getInstance().addGoods(id, goodsName.getText().toString(),
                            goodsCurrentPrice.getText().toString(),
                            goodsCurrentPrice.getText().toString(),
                            goodsOriginalPrice.getText().toString(),
                            imageFile);
                }
                break;
            case R.id.upload_button:
                break;
        }
    }
}
