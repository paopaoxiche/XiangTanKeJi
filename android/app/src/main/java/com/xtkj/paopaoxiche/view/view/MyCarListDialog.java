package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.base.CustomAdapter;
import com.xtkj.paopaoxiche.base.CustomAdapter.LayoutView;
import com.xtkj.paopaoxiche.bean.CarAuthStateBean;
import com.xtkj.paopaoxiche.bean.CarAuthStateBean.DataBean;
import com.xtkj.paopaoxiche.bean.UserConsumeBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.UserService;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 个人车型认证列表
 * Created by sky on 2018/9/1.
 */
public class MyCarListDialog extends FullScreenWithStatusBarDialog implements LayoutView {
    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.tv_add_car)
    TextView tvAddCar;
    @BindView(R.id.tv_car_number)
    TextView tvCarNumber;
    @BindView(R.id.lv_my_cars)
    ListView lvMyCars;


    private CustomAdapter<DataBean> adapter;

    public MyCarListDialog(Context context) {
        super(context, true);
        setContentView(R.layout.layout_dialog_my_car_list);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        backArrowImageButton.setOnClickListener(backButtonClickListener);
        tvAddCar.setOnClickListener((View) -> new AddCarDialog(getContext()).show());
        new Handler().post(this::getAuthCars);
    }

    private void getAuthCars() {
        if (!isShowing()) {
            return;
        }

        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                      .create(UserService.class)
                      .getAuthCarByStatus(1).enqueue(new Callback<CarAuthStateBean>() {
            @Override
            public void onResponse(Call<CarAuthStateBean> call, Response<CarAuthStateBean> response) {
                if (!isShowing()) {
                    return;
                }

                CarAuthStateBean bean = response.body();
                if (bean.getCode() != 200) {
                    Toast.makeText(BaseApplication.getContext(), "获取车辆认证信息失败！", Toast.LENGTH_LONG).show();
                    return;
                }

                List<DataBean> dataBeans = bean.getData();
                if (dataBeans == null || dataBeans.isEmpty()) {
                    Toast.makeText(BaseApplication.getContext(), "没有任何车辆认证记录！", Toast.LENGTH_LONG).show();
                    return;
                }

                adapter.addDatas(dataBeans);
                adapter.notifyDataSetChanged();

                tvCarNumber.setText(String.valueOf(Integer.valueOf(tvCarNumber.getText().toString()) + bean.getData().size()));
            }

            @Override
            public void onFailure(Call<CarAuthStateBean> call, Throwable t) {
                if (!isShowing()) {
                    return;
                }

                Toast.makeText(BaseApplication.getContext(), "获取车辆认证信息失败！", Toast.LENGTH_LONG).show();
            }
        });

        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                      .create(UserService.class)
                      .getAuthCarByStatus(0).enqueue(new Callback<CarAuthStateBean>() {
            @Override
            public void onResponse(Call<CarAuthStateBean> call, Response<CarAuthStateBean> response) {
                if (!isShowing()) {
                    return;
                }

                CarAuthStateBean bean = response.body();
                if (bean.getCode() != 200) {
                    Toast.makeText(BaseApplication.getContext(), "获取车辆认证信息失败！", Toast.LENGTH_LONG).show();
                    return;
                }

                List<DataBean> dataBeans = bean.getData();
                if (dataBeans == null || dataBeans.isEmpty()) {
                    Toast.makeText(BaseApplication.getContext(), "没有任何车辆认证记录！", Toast.LENGTH_LONG).show();
                    return;
                }

                adapter.addDatas(dataBeans);
                adapter.notifyDataSetChanged();

                tvCarNumber.setText(String.valueOf(Integer.valueOf(tvCarNumber.getText().toString()) + bean.getData().size()));
            }

            @Override
            public void onFailure(Call<CarAuthStateBean> call, Throwable t) {
                if (!isShowing()) {
                    return;
                }

                Toast.makeText(BaseApplication.getContext(), "获取车辆认证信息失败！", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initListView() {
        if (adapter == null) {
            adapter = new CustomAdapter<>(new ArrayList<>());
            adapter.setLayoutView(this);
        }
        lvMyCars.setAdapter(adapter);
    }

    @Override
    public View setView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_my_car_info, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DataBean bean = adapter.getItem(position);
        setCarTypeAndIcon(bean, holder);
        setCarAuthSateAndIcon(bean, holder);

        return convertView;
    }

    private void setCarAuthSateAndIcon(DataBean bean, ViewHolder holder) {
        if (bean.getStatus() == 0) {
            holder.ivAuthenticationIcon.setImageResource(R.drawable.img_ident_no);
            holder.tvCarAuthenticationStatus.setText("认证中");
            holder.tvCarAuthenticationStatus.setTextColor(getContext().getResources().getColor(R.color.orange));
        } else {
            holder.ivAuthenticationIcon.setImageResource(R.drawable.img_ident_yes);
            holder.tvCarAuthenticationStatus.setText("已认证");
            holder.tvCarAuthenticationStatus.setTextColor(getContext().getResources().getColor(R.color.green));
        }
    }

    private void setCarTypeAndIcon(DataBean bean, ViewHolder holder) {
        String carDetail = bean.getModel();
        holder.tvCarTypeDetail.setText(carDetail);
        if (carDetail.contains("九座以上")) {
            holder.tvCarType.setText("大型车");
            holder.ivCarIcon.setImageResource(R.drawable.img_car3);
        } else if (carDetail.contains("九座及以下")) {
            holder.tvCarType.setText("中型车");
            holder.ivCarIcon.setImageResource(R.drawable.img_car2);
        } else {
            holder.tvCarType.setText("小型车");
            holder.ivCarIcon.setImageResource(R.drawable.img_car1);
        }
    }

    class ViewHolder {
        @BindView(R.id.iv_car_icon)
        ImageView ivCarIcon;
        @BindView(R.id.tv_car_type)
        TextView tvCarType;
        @BindView(R.id.tv_car_type_detail)
        TextView tvCarTypeDetail;
        @BindView(R.id.tv_car_authentication_status)
        TextView tvCarAuthenticationStatus;
        @BindView(R.id.iv_authentication_icon)
        ImageView ivAuthenticationIcon;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
