package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageButton;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.bean.WashServiceListBean;
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

public class WashListManagerDialog extends FullScreenWithStatusBarDialog implements WashServerModel.WashServiceListener {

    @BindView(R.id.add_goods_image_button)
    ImageButton addGoodsImageButton;
    @BindView(R.id.goods_list_view)
    SwipeMenuListView goodsListView;

    WashServerListAdapter washServerListAdapter;

    public WashListManagerDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_wash_manager_list);
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

        goodsListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // 修改
                        new ModifyWashServiceDialog(getContext(), true, washServerListAdapter.washServerList.get(position)).show();
                        dismiss();
                        break;
                    case 1:
                        // 删除
                        SureDialog sureDialog = new SureDialog(getContext(), R.style.NormalDialog);
                        sureDialog.setCancelBtnVisibility(View.VISIBLE);
                        sureDialog.setClickListener(new SureDialog.ClickListener() {
                            @Override
                            public void sure(SureDialog dialog) {
                                WashServerModel.getInstance().deleteWashService(UserInfo.getWashId(), washServerListAdapter.washServerList.get(position).getId());
                                dismiss();
                            }
                        });
                        sureDialog.show();
                        break;
                }
                return false;
            }
        });

        WashServerModel.getInstance().getWashServerList(UserInfo.getWashId(), 0, 20);
    }

    @Override
    public void show() {
        super.show();
        WashServerModel.getInstance().addListener(this);
        WashServerModel.getInstance().getWashServerList(UserInfo.getWashId(), 0, 20);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        WashServerModel.getInstance().removeListener(this);
    }

    @OnClick({R.id.add_goods_image_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_goods_image_button:
                new ModifyWashServiceDialog(getContext(), true).show();
                dismiss();
                break;
        }
    }

    @Override
    public void getWashServerListSuccess(WashServiceListBean washServiceListBean) {
        washServerListAdapter = new WashServerListAdapter(getContext(), washServiceListBean.getData());
        goodsListView.setAdapter(washServerListAdapter);

        if (washServiceListBean.getData() != null && washServiceListBean.getData().size() > 0) {
            goodsListView.setVisibility(View.VISIBLE);
        }
    }
}
