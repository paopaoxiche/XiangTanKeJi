package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.bean.WashServiceListBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.event.BaseEvent;
import com.xtkj.paopaoxiche.model.GoodsModel;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.model.WashServerModel;
import com.xtkj.paopaoxiche.utils.BitmapUtil;
import com.xtkj.paopaoxiche.widget.CashierInputFilter;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyWashServiceDialog extends FullScreenWithStatusBarDialog implements RadioGroup.OnCheckedChangeListener{

    int id = 0;

    int carModel = 3;

    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.complete_button)
    Button completeButton;
    @BindView(R.id.goods_name)
    EditText goodsName;
    @BindView(R.id.goods_current_price)
    EditText goodsCurrentPrice;
    @BindView(R.id.describe_edit_text)
    EditText describeEditText;
    @BindView(R.id.car_model_radio_group)
    RadioGroup carModelRadioGrpoup;

    public ModifyWashServiceDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_modify_wash_service);
        ButterKnife.bind(this);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);
        InputFilter[] filters = {new CashierInputFilter()};
        goodsCurrentPrice.setFilters(filters);
        carModelRadioGrpoup.setOnCheckedChangeListener(this);
    }

    public ModifyWashServiceDialog(Context context, boolean statusBarVisible, WashServiceListBean.DataBean dataBean) {
        this(context, statusBarVisible);
        id = dataBean.getId();
        goodsName.setText(dataBean.getName());
        goodsCurrentPrice.setText(dataBean.getPrice() + "");
        describeEditText.setText(dataBean.getDescribe());
        carModel = dataBean.getCarModel();
        if (carModel == 1) {
            carModelRadioGrpoup.check(R.id.large_car_radio_button);
        }
        if (carModel == 2) {
            carModelRadioGrpoup.check(R.id.middle_car_radio_button);
        }
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @OnClick({R.id.complete_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.complete_button:
                if (!TextUtils.isEmpty(describeEditText.getText().toString())
                        && !TextUtils.isEmpty(goodsName.getText().toString())
                        && !TextUtils.isEmpty(goodsCurrentPrice.getText().toString())) {
                    WashServerModel.getInstance().addWashService(UserInfo.getWashId(), id, goodsName.getText().toString(),
                            describeEditText.getText().toString(), goodsCurrentPrice.getText().toString(), carModel);
                    dismiss();
                }
                break;
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.small_car_radio_button:
                carModel = 3;
                break;
            case R.id.middle_car_radio_button:
                carModel = 2;
                break;
            case R.id.large_car_radio_button:
                carModel = 1;
                break;
        }
    }
}
