package com.xtkj.paopaoxiche.view.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.bean.WashCommodityBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.model.GoodsModel;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.model.WashServerModel;
import com.xtkj.paopaoxiche.utils.DensityUtil;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;
import com.xtkj.paopaoxiche.widget.SureDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsListDialog extends FullScreenWithStatusBarDialog {

    @BindView(R.id.add_goods_image_button)
    ImageButton addGoodsImageButton;
    @BindView(R.id.goods_list_view)
    SwipeMenuListView goodsListView;

    GoodsListAdapter goodsListAdapter;

    public GoodsListDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_goods_list);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);
        ButterKnife.bind(this);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getContext());
                // set item background
                openItem.setBackground(new ColorDrawable(getContext().getResources().getColor(R.color.yellow)));
                // set item width
                openItem.setWidth(DensityUtil.dip2px(getContext(),48));
                // set item title
                openItem.setTitle("修改");
                // set item title fontsize
                openItem.setTitleSize(15);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(getContext().getResources().getColor(R.color.orange)));
                // set item width
                deleteItem.setWidth(DensityUtil.dip2px(getContext(),48));
                // set a icon
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(15);
                deleteItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        goodsListView.setMenuCreator(creator);
        goodsListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        goodsListView.setOnMenuItemClickListener((position, menu, index) -> {
            switch (index) {
                case 0:
                    // 修改
                    new ModifyGoodsDialog(context, true, goodsListAdapter.goodsList.get(position)).show();
                    break;
                case 1:
                    // 删除
                    SureDialog sureDialog = new SureDialog(getContext(), R.style.NormalDialog);
                    sureDialog.setCancelBtnVisibility(View.VISIBLE);
                    sureDialog.setClickListener(new SureDialog.ClickListener() {
                        @Override
                        public void sure(SureDialog dialog) {
                            GoodsModel.getInstance().deleteGoods(goodsListAdapter.goodsList.get(position).getId());
                            dismiss();
                        }
                    });
                    sureDialog.show();
                    break;
            }
            return false;
        });

        List<WashCommodityBean.DataBean> goodsList = GoodsModel.getInstance().getGoodsList();

        goodsListAdapter = new GoodsListAdapter(getContext(), goodsList);
        goodsListView.setAdapter(goodsListAdapter);

        if (goodsList != null && goodsList.size() > 0) {

            goodsListView.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.add_goods_image_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_goods_image_button:
                new ModifyGoodsDialog(getContext(), true).show();
                break;
        }
    }
}
