package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class GoodsListDialog extends FullScreenWithStatusBarDialog {

    @BindView(R.id.add_goods_image_button)
    ImageButton addGoodsImageButton;
    @BindView(R.id.goods_list_view)
    ListView goodsListView;

    public GoodsListDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_goods_list);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);
    }

    @OnClick({R.id.add_goods_image_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_goods_image_button:
                break;
        }
    }
}
