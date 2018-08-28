package com.xtkj.paopaoxiche.view.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.base.CustomAdapter;
import com.xtkj.paopaoxiche.base.CustomAdapter.LayoutView;
import com.xtkj.paopaoxiche.bean.CouponListBean;
import com.xtkj.paopaoxiche.bean.CouponListBean.DataBean;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.service.WashService;
import com.xtkj.paopaoxiche.utils.DensityUtil;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 积分兑换界面
 *
 * @author sky on 2018/8/27
 * @since 1.0
 */
public class CreditsExchangeDialog extends FullScreenWithStatusBarDialog
    implements LayoutView {

    @BindView(R.id.back_arrow_image_button)
    ImageButton backButton;
    @BindView(R.id.tv_credits)
    TextView tvCredits;
    @BindView(R.id.iv_credits_icon)
    ImageView ivCreditsIcon;
    @BindView(R.id.lv_my_coupons)
    ListView lvMyCoupons;


    private CustomAdapter<DataBean> adapter;

    public CreditsExchangeDialog(Context context) {
        super(context, true);
        setContentView(R.layout.dialog_credits_exchange);
        ButterKnife.bind(this);
        backButton.setOnClickListener(backButtonClickListener);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        tvCredits.setText(String.valueOf(UserInfo.getScore()));
        showLoadingDialog();
        requestAllCoupons();
    }

    @Override
    public void show() {
        super.show();
        // 请求所有的可兑换列表
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View setView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_coupons, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DataBean bean = adapter.getItem(position);
        holder.tvCouponName.setText(bean.getCouponName());
        holder.tvDetail.setText(bean.getDetail());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        holder.tvStartAndEndTime.setText(sdf.format(new Date(bean.getStartTime()))
            + " - " + sdf.format(new Date(bean.getEndTime())));
        holder.tvCouponCredits.setText(bean.getPoints() + "积分");

        SpannableStringBuilder ssb1 = new SpannableStringBuilder(String.valueOf(bean.getDenomination()));
        ssb1.setSpan(new AbsoluteSizeSpan(DensityUtil.dip2px(getContext(), 30.0f)),
            0, ssb1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        SpannableString ss = new SpannableString("元");
        ss.setSpan(new AbsoluteSizeSpan(DensityUtil.dip2px(getContext(), 18.0f)),
            0, ss.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.tvDenomination.setText(ssb1.append(ss));

        holder.setId(bean.getId());

        return convertView;
    }

    @Override
    public void dismiss() {
        dismissLoadingDialog();
        super.dismiss();
    }

    class ViewHolder {
        @BindView(R.id.tv_denomination)
        TextView tvDenomination;
        @BindView(R.id.tv_coupon_name)
        TextView tvCouponName;
        @BindView(R.id.tv_detail)
        TextView tvDetail;
        @BindView(R.id.tv_start_and_end_time)
        TextView tvStartAndEndTime;
        @BindView(R.id.tv_coupon_credits)
        TextView tvCouponCredits;
        @BindView(R.id.button_exchange)
        Button buttonExchange;
        int id;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            buttonExchange.setOnClickListener(v -> {
                //showLoadingDialog();
                exchangePoint(id);
            });
        }

        public void setId(int id) {
            this.id = id;
        }

        private void exchangePoint(int id) {
            RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                          .create(WashService.class)
                          .exchangePoint(String.valueOf(id))
                          .enqueue(new Callback<NoDataBean>() {
                              @Override
                              public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                                  //dismissLoadingDialog();
                                  NoDataBean bean = response.body();
                                  if (bean.getCode() == 200) {
                                      Toast.makeText(BaseApplication.getContext(), "兑换成功！", Toast.LENGTH_LONG).show();
                                      tvCredits.setText(String.valueOf(bean.getData()));
                                  } else {
                                      Toast.makeText(BaseApplication.getContext(), "兑换失败！", Toast.LENGTH_LONG).show();
                                  }
                              }

                              @Override
                              public void onFailure(Call<NoDataBean> call, Throwable t) {
                                  dismissLoadingDialog();
                                  Toast.makeText(BaseApplication.getContext(), "兑换失败！", Toast.LENGTH_LONG).show();
                              }
                          });
        }
    }

    private void requestAllCoupons() {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                      .create(WashService.class)
                      .getAllCoupons()
                      .enqueue(new Callback<CouponListBean>() {
                          @Override
                          public void onResponse(Call<CouponListBean> call, Response<CouponListBean> response) {
                              dismissLoadingDialog();
                              CouponListBean bean = response.body();
                              if (bean.getCode() != 200) {
                                  Toast.makeText(BaseApplication.getContext(), "获取优惠券失败！", Toast.LENGTH_LONG).show();
                                  return;
                              }

                              List<DataBean> dataBeans = bean.getData();
                              if (dataBeans == null || dataBeans.isEmpty()) {
                                  Toast.makeText(BaseApplication.getContext(), "没有可兑换的优惠券！", Toast.LENGTH_LONG).show();
                                  return;
                              }

                              if (!isShowing()) {
                                  return;
                              }

                              initListView();

                              adapter.updateData(dataBeans);
                              adapter.notifyDataSetChanged();
                          }

                          @Override
                          public void onFailure(Call<CouponListBean> call, Throwable t) {
                              dismissLoadingDialog();
                              Toast.makeText(BaseApplication.getContext(), "获取优惠券失败！", Toast.LENGTH_LONG).show();
                          }
                      });
    }

    private void initListView() {
        if (adapter == null) {
            adapter = new CustomAdapter<>(new ArrayList<>());
            adapter.setLayoutView(this);
            lvMyCoupons.setAdapter(adapter);
        }
    }

}
