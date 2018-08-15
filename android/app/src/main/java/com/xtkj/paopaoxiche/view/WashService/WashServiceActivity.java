package com.xtkj.paopaoxiche.view.WashService;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.contract.IWashServiceContract;
import com.xtkj.paopaoxiche.presenter.WashServicePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WashServiceActivity extends BaseActivity implements IWashServiceContract.IWashServiceView {

    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.service_items)
    LinearLayout serviceItems;
    @BindView(R.id.shop_list)
    LinearLayout shopList;
    @BindView(R.id.pay_button)
    Button payButton;


    IWashServiceContract.IWashServicePresenter presenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_service);
        ButterKnife.bind(this);

        new WashServicePresenterImpl(this);
        presenter.onCreate();
        initViews();
    }

    @Override
    protected void initViews() {

        for(int i = 0  ; i < 3 ; i ++){
            LinearLayout linearLayout = (LinearLayout) View.inflate(this,R.layout.item_wash_service_details,null);
            TextView service_name = linearLayout.findViewById(R.id.service_name);
            service_name.setText("洗车套餐" + i);
            TextView describe = linearLayout.findViewById(R.id.describe);
            describe.setText("套餐详细信息" + i);
            TextView old_price = linearLayout.findViewById(R.id.old_price);
            if(i!=1) {
                old_price.setText("￥15.0");
                old_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
            }else {
                old_price.setVisibility(View.GONE);
            }
            TextView price = linearLayout.findViewById(R.id.price);
            price.setText("￥12.0");

            RadioButton radio =linearLayout.findViewById(R.id.radio);
            int finalI = i;
            radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        int count = serviceItems.getChildCount();
                        for(int j = 0 ; j < count ; j ++){
                            if(finalI ==j)continue;
                            RadioButton r  = serviceItems.getChildAt(j).findViewById(R.id.radio);
                            r.setChecked(false);
                        }
                    }
                }
            });

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
            layoutParams.setMargins(0,16,0,16);
            serviceItems.addView(linearLayout,layoutParams);
        }

        for(int i = 0  ; i < 4 ; i ++){
            LinearLayout linearLayout = (LinearLayout) View.inflate(this,R.layout.item_wash_service_shop,null);

            TextView shop_name = linearLayout.findViewById(R.id.shop_name);
            shop_name.setText("商品名" + i);
            TextView sell_num = linearLayout.findViewById(R.id.sell_num);
            sell_num.setText("销量" + i * 100);
            TextView old_price = linearLayout.findViewById(R.id.old_price);
            if(i!=1) {
                old_price.setText("￥15.0");
                old_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
            }else {
                old_price.setVisibility(View.GONE);
            }
            TextView price = linearLayout.findViewById(R.id.price);
            price.setText("￥12.0");
            RadioButton radio =linearLayout.findViewById(R.id.radio);
            int finalI = i;
            radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        int count = shopList.getChildCount();
                        for(int j = 0 ; j < count ; j ++ ){
                            if(finalI ==j)continue;
                            RadioButton r  = shopList.getChildAt(j).findViewById(R.id.radio);
                            r.setChecked(false);
                        }
                    }
                }
            });
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,150);
            layoutParams.setMargins(0,16,0,16);
            shopList.addView(linearLayout,layoutParams);
        }


    }

    @Override
    protected void initValues() {

    }

    @Override
    protected void initListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void setPresenter(IWashServiceContract.IWashServicePresenter iWashServicePresenter) {
        this.presenter = iWashServicePresenter ;

    }

    @Override
    public Context getActivityContext() {
        return this;
    }
}
