package com.xtkj.paopaoxiche.view.WashService;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.bingo.wxpay.Constants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.bean.CreateConsumeBean;
import com.xtkj.paopaoxiche.bean.PostWashServiceBean;
import com.xtkj.paopaoxiche.bean.SellingServicesBean;
import com.xtkj.paopaoxiche.bean.WashCommodityBean;
import com.xtkj.paopaoxiche.contract.IWashServiceContract;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.presenter.WashServicePresenterImpl;
import com.xtkj.paopaoxiche.service.CarOwnerService;
import com.xtkj.paopaoxiche.service.WashService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.tencent.mm.opensdk.openapi.IWXAPI;

public class WashServiceActivity extends BaseActivity implements IWashServiceContract.IWashServiceView,IWXAPIEventHandler {

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


    private IWXAPI api;

    IWashServiceContract.IWashServicePresenter presenter ;
    int washId;


    private PostWashServiceBean postWashServiceBean;

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
        initValues();
        initListeners();
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
                    if(index ==j){
                        postWashServiceBean.setWashServiceId(d.getId());
                        continue;
                    }
                    RadioButton r  = serviceItems.getChildAt(j).findViewById(R.id.radio);
                    r.setChecked(false);
                }
            }
        });

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
                    if(index ==j){
                        postWashServiceBean.setCommoditys(dataBean.getId());
                        continue;
                    }
                    RadioButton r  = shopList.getChildAt(j).findViewById(R.id.radio);
                    r.setChecked(false);
                }
            }
        });
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,16,0,16);
        shopList.addView(linearLayout,layoutParams);
    }

    @Override
    protected void initViews() {



    }

    @Override
    protected void initValues() {

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
        api.handleIntent(getIntent(), this);
        postWashServiceBean = new PostWashServiceBean();
        postWashServiceBean.setCommoditys(0);
        postWashServiceBean.setCouponId(0);
        postWashServiceBean.setPayType(AppConstant.PAY_WX);
        postWashServiceBean.setWashServiceId(0);

        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(WashService.class)
                .getServiceList(washId)
                .enqueue(new Callback<SellingServicesBean>() {
                    @Override
                    public void onResponse(Call<SellingServicesBean> call, Response<SellingServicesBean> response) {
                        if( response.body()==null)return;
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
                        if( response.body()==null)return;
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
    protected void initListeners() {

        backButton.setOnClickListener(view -> finish());
        payButton.setOnClickListener(e ->{
            callPay();
        });
    }



    private void callPay(){
        RetrofitClient.newInstance(ApiField.BASEURL,Authentication.getAuthentication())
                .create(CarOwnerService.class)
                .createConsume(postWashServiceBean.getWashServiceId(),postWashServiceBean.getCommoditys(),postWashServiceBean.getCouponId(),postWashServiceBean.getPayType())
                .enqueue(new Callback<CreateConsumeBean>() {
                    @Override
                    public void onResponse(Call<CreateConsumeBean> call, Response<CreateConsumeBean> response) {

                        if(response.body().getData()==null)return;
                        PayReq req = new PayReq();
                        //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                        req.appId			= response.body().getData().getWxPay().getAppid();
                        req.partnerId		= response.body().getData().getWxPay().getPartnerid();
                        req.prepayId		= response.body().getData().getWxPay().getPrepayid();
                        req.nonceStr		= response.body().getData().getWxPay().getNoncestr();
                        req.timeStamp		= response.body().getData().getWxPay().getTimestamp();
                        req.packageValue	= response.body().getData().getWxPay().getPackageX();
                        req.sign			= response.body().getData().getWxPay().getSign();
                        req.extData			= "app data"; // optional
                        Toast.makeText(getActivityContext(), "正常调起支付", Toast.LENGTH_SHORT).show();
                        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                        api.sendReq(req);
                    }

                    @Override
                    public void onFailure(Call<CreateConsumeBean> call, Throwable t) {

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

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d("支付反馈", "onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(com.bingo.wxpay.R.string.app_tip);
            builder.setMessage(getString(com.bingo.wxpay.R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
            builder.show();
        }
    }
}
