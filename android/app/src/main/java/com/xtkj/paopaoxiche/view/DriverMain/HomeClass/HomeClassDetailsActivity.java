package com.xtkj.paopaoxiche.view.DriverMain.HomeClass;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.bean.ClassificationCommodityBean;
import com.xtkj.paopaoxiche.model.HomeClassModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeClassDetailsActivity extends BaseActivity {

    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.class_details_img)
    ImageView classDetailsImg;
    @BindView(R.id.class_price1)
    TextView classPrice1;
    @BindView(R.id.class_price2)
    TextView classPrice2;
    @BindView(R.id.class_details_name)
    TextView classDetailsName;
    @BindView(R.id.class_details_location)
    TextView classDetailsLocation;
    @BindView(R.id.class_details_phone)
    TextView classDetailsPhone;
    @BindView(R.id.class_details_button)
    ImageButton classDetailsButton;
    @BindView(R.id.class_details_text)
    TextView classDetailsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_class_details);
        ButterKnife.bind(this);

        initViews();
        initValues();
        initListeners();
    }

    @Override
    protected void initViews() {
        ClassificationCommodityBean.DataBean dataBean = HomeClassModel.getInstance().getDetailsData();
        Glide.with(this).load(dataBean.getImg()).into(classDetailsImg);
        classDetailsText.setText(dataBean.getDetail());
        classPrice1.setText(String.format("￥%s", dataBean.getDiscountPrice()));
        if(dataBean.getOriginalPrice()!=null && !dataBean.getOriginalPrice().equals("")){
            classPrice2.setText(String.format("￥%s", dataBean.getOriginalPrice()));
            classPrice2.setPaintFlags(classPrice2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else classPrice2.setVisibility(View.GONE);
        classDetailsName.setText("你的名字");
        classDetailsLocation.setText(dataBean.getAddress());
        classDetailsPhone.setText(String.format("服务热线：%s", dataBean.getPhone()));

        classDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataBean.getUrl()==null){
                    Toast.makeText(getApplicationContext(),"商品没有上线，敬请期待！",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    protected void initValues() {

    }

    @Override
    protected void initListeners() {

    }

    @OnClick({R.id.back_button, R.id.class_details_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                finish();
                break;
            case R.id.class_details_button:
                break;
        }
    }
}
