package com.xtkj.paopaoxiche.view.WashService;

import android.content.Context;
import android.content.Intent;
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
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.bean.SellingServicesBean;
import com.xtkj.paopaoxiche.bean.WashCommodityBean;
import com.xtkj.paopaoxiche.contract.IWashServiceContract;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.presenter.WashServicePresenterImpl;
import com.xtkj.paopaoxiche.service.WashService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    int washId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_service);
        ButterKnife.bind(this);
        Intent i = getIntent();
        washId = i.getIntExtra("washId",0);

        new WashServicePresenterImpl(this);
        presenter.onCreate();
        initViews();
    }

    private void buildServiceLayout(SellingServicesBean.DataBean d,int index){
        LinearLayout linearLayout = (LinearLayout) View.inflate(this,R.layout.item_wash_service_details,null);
        TextView service_name = linearLayout.findViewById(R.id.service_name);
        service_name.setText(d.getName());
        TextView describe = linearLayout.findViewById(R.id.describe);
        describe.setText(d.getDescribe());
        TextView old_price = linearLayout.findViewById(R.id.old_price);

        old_price.setText(String.format("￥%s", d.getPrice()));
        old_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);

        TextView price = linearLayout.findViewById(R.id.price);
        price.setText(String.format("￥%s", d.getPrice()));

        RadioButton radio =linearLayout.findViewById(R.id.radio);

        radio.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                int count = serviceItems.getChildCount();
                for(int j = 0 ; j < count ; j ++){
                    if(index ==j)continue;
                    RadioButton r  = serviceItems.getChildAt(j).findViewById(R.id.radio);
                    r.setChecked(false);
                }
            }
        });

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
        layoutParams.setMargins(0,16,0,16);
        serviceItems.addView(linearLayout,layoutParams);
    }

    private void buildGoodsLayout(WashCommodityBean.DataBean dataBean,int index){
        LinearLayout linearLayout = (LinearLayout) View.inflate(this,R.layout.item_wash_service_shop,null);

        TextView shop_name = linearLayout.findViewById(R.id.shop_name);
        shop_name.setText(dataBean.getName());
        TextView sell_num = linearLayout.findViewById(R.id.sell_num);
        sell_num.setText("销量" + index * 100);
        TextView old_price = linearLayout.findViewById(R.id.old_price);

        old_price.setText(String.format("￥%s", dataBean.getOriginPrice()));
        old_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);

        old_price.setVisibility(View.GONE);

        TextView price = linearLayout.findViewById(R.id.price);
        price.setText(String.format("￥%s", dataBean.getCurrentPrice()));
        RadioButton radio =linearLayout.findViewById(R.id.radio);
        radio.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                int count = shopList.getChildCount();
                for(int j = 0 ; j < count ; j ++ ){
                    if(index ==j)continue;
                    RadioButton r  = shopList.getChildAt(j).findViewById(R.id.radio);
                    r.setChecked(false);
                }
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,150);
        layoutParams.setMargins(0,16,0,16);
        shopList.addView(linearLayout,layoutParams);
    }

    @Override
    protected void initViews() {


        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(WashService.class)
                .getServiceList(washId,0,20)
                .enqueue(new Callback<SellingServicesBean>() {
                    @Override
                    public void onResponse(Call<SellingServicesBean> call, Response<SellingServicesBean> response) {
                        if( response.body()==null)return;;
                        ArrayList<SellingServicesBean.DataBean> dataBeans = (ArrayList<SellingServicesBean.DataBean>) response.body().getData();
                        if(dataBeans==null)return;
                        for(int i  = 0 ; i < dataBeans.size() ; i ++){
                            buildServiceLayout(dataBeans.get(i),i);
                        }
                    }

                    @Override
                    public void onFailure(Call<SellingServicesBean> call, Throwable t) {

                    }
                });

        RetrofitClient.newInstance(ApiField.BASEURL,Authentication.getAuthentication())
                .create(WashService.class)
                .getGoodsList(washId,0,20)
                .enqueue(new Callback<WashCommodityBean>() {
                    @Override
                    public void onResponse(Call<WashCommodityBean> call, Response<WashCommodityBean> response) {
                        if( response.body()==null)return;;
                        ArrayList<WashCommodityBean.DataBean> dataBeans = (ArrayList<WashCommodityBean.DataBean>) response.body().getData();
                        if(dataBeans==null)return;
                        for(int i  = 0 ; i < dataBeans.size() ; i ++){
                            buildGoodsLayout(dataBeans.get(i),i);
                        }
                    }

                    @Override
                    public void onFailure(Call<WashCommodityBean> call, Throwable t) {

                    }
                });

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
