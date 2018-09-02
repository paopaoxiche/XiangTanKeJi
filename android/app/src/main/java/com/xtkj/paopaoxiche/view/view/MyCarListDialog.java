package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 个人车型认证列表
 * Created by sky on 2018/9/1.
 */
public class MyCarListDialog extends FullScreenWithStatusBarDialog {
    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.tv_add_car)
    TextView tvAddCar;
    @BindView(R.id.tv_car_number)
    TextView tvCarNumber;
    @BindView(R.id.lv_my_cars)
    ListView lvMyCars;

    public MyCarListDialog(Context context) {
        super(context, true);
        setContentView(R.layout.layout_dialog_my_car_list);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backArrowImageButton.setOnClickListener(backButtonClickListener);
        tvAddCar.setOnClickListener((View)-> new AddCarDialog(getContext()).show());
    }

}
