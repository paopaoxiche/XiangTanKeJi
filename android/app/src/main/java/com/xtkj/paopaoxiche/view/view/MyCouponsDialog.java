package com.xtkj.paopaoxiche.view.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.base.CustomAdapter;
import com.xtkj.paopaoxiche.base.CustomAdapter.LayoutView;
import com.xtkj.paopaoxiche.bean.CouponListBean;
import com.xtkj.paopaoxiche.bean.MyCouponListBean;
import com.xtkj.paopaoxiche.bean.MyCouponListBean.DataBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.UserService;
import com.xtkj.paopaoxiche.utils.DensityUtil;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCouponsDialog extends FullScreenWithStatusBarDialog implements LayoutView {
    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.lv_my_coupons)
    ListView lvMyCoupons;

    private CustomAdapter<DataBean> adapter;

    public MyCouponsDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_my_coupons);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backArrowImageButton.setOnClickListener(backButtonClickListener);

        initListView();

        new Handler(Looper.getMainLooper()).post(() -> {
            if (!isShowing()) {
                return;
            }
            showLoadingDialog();
            getMyCoupons();
        });
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View setView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_my_coupon, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DataBean bean = adapter.getItem(position);

        holder.tvTitle.setText(bean.getTitle());
        holder.tvWashName.setText(bean.getWashName());
        holder.tvEndTime.setText("有效期至 " + getDateFormat().format(new Date(Long.valueOf(bean.getEndDate()))));

        Glide.with(getContext())
             .load(bean.getWashHeader())
             .into(holder.ivHeader);

        // 设置值
        SpannableStringBuilder ssb1 = new SpannableStringBuilder(
            String.valueOf(Double.valueOf(bean.getPrice()).intValue()));
        ssb1.setSpan(new AbsoluteSizeSpan(DensityUtil.dip2px(getContext(), 36.0f)),
            0, ssb1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        SpannableString ss = new SpannableString("元");
        ss.setSpan(new AbsoluteSizeSpan(DensityUtil.dip2px(getContext(), 14.0f)),
            0, ss.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.tvDenomination.setText(ssb1.append(ss));

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_header)
        CircleImageView ivHeader;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_wash_name)
        TextView tvWashName;
        @BindView(R.id.tv_denomination)
        TextView tvDenomination;
        @BindView(R.id.tv_end_time)
        TextView tvEndTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private void initListView() {
        if (adapter == null) {
            adapter = new CustomAdapter<>(new ArrayList<>());
            adapter.setLayoutView(this);
        }
        lvMyCoupons.setAdapter(adapter);
    }

    private void getMyCoupons() {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                      .create(UserService.class)
                      .getMyCoupon()
                      .enqueue(new Callback<MyCouponListBean>() {
                          @Override
                          public void onResponse(Call<MyCouponListBean> call, Response<MyCouponListBean> response) {
                              if (!isShowing()) {
                                  return;
                              }

                              dismissLoadingDialog();
                              MyCouponListBean bean = response.body();
                              if (bean.getCode() != 200) {
                                  Toast.makeText(BaseApplication.getContext(),
                                      "获取个人优惠劵数据失败！", Toast.LENGTH_LONG).show();
                                  return;
                              }

                              List<DataBean> dataBeans = bean.getData();
                              if (dataBeans == null || dataBeans.isEmpty()) {
                                  Toast.makeText(BaseApplication.getContext(), "没有可用的优惠券！", Toast.LENGTH_LONG)
                                       .show();
                                  return;
                              }

                              adapter.updateDataAndNotifyDataChanged(dataBeans);
                          }

                          @Override
                          public void onFailure(Call<MyCouponListBean> call, Throwable t) {
                              if (!isShowing()) {
                                  return;
                              }

                              dismissLoadingDialog();
                              Toast.makeText(BaseApplication.getContext(),
                                  "获取个人优惠劵数据失败！", Toast.LENGTH_LONG).show();
                          }
                      });
    }

    private SimpleDateFormat sdf;
    private SimpleDateFormat getDateFormat() {
        if (sdf == null) {
            sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        }

        return sdf;
    }

}
