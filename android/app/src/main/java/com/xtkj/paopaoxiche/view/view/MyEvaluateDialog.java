package com.xtkj.paopaoxiche.view.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.base.CustomAdapter;
import com.xtkj.paopaoxiche.base.CustomAdapter.LayoutView;
import com.xtkj.paopaoxiche.bean.EvaluateListBean;
import com.xtkj.paopaoxiche.bean.EvaluateListBean.DataBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.UserService;
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

public class MyEvaluateDialog extends FullScreenWithStatusBarDialog implements LayoutView {

    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.lv_my_evaluate)
    ListView lvMyEvaluate;

    private CustomAdapter<DataBean> adapter;

    public MyEvaluateDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_my_evaluate);
        ButterKnife.bind(this);
        backArrowImageButton.setOnClickListener(backButtonClickListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        new Handler(Looper.getMainLooper()).postDelayed(this::getEvaluateList, 0);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View setView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user_evaluate, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DataBean bean = adapter.getItem(position);

        holder.tvName.setText(bean.getNickname());
        holder.tvTime.setText(getDateFormat().format(new Date(bean.getTime())));
        holder.tvContent.setText(bean.getContent());

        // 加载头像图片
        holder.ivHeader.setImageDrawable(null);
        Glide.with(getContext())
             .load(bean.getAvatar())
             .into(holder.ivHeader);

        setStars(holder.llStars, bean.getRating());
        holder.tvStarPoint.setText(Integer.valueOf(bean.getRating()).doubleValue() + "分");

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.iv_header)
        CircleImageView ivHeader;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.ll_stars)
        LinearLayout llStars;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_star_point)
        TextView tvStarPoint;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private SimpleDateFormat sdf;

    private SimpleDateFormat getDateFormat() {
        if (sdf == null) {
            sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        }

        return sdf;
    }

    private void setStars(ViewGroup viewGroup, int starIndex) {
        int childCount = viewGroup.getChildCount();
        int starPoint = starIndex - 1;
        for (int i = 0; i < childCount; i++) {
            if (i > starPoint) {
                ((ImageView) viewGroup.getChildAt(i)).setImageResource(R.drawable.star);
            } else {
                ((ImageView) viewGroup.getChildAt(i)).setImageResource(R.drawable.star_select);
            }
        }
    }

    private void initListView() {
        if (adapter == null) {
            adapter = new CustomAdapter<>(new ArrayList<>());
            adapter.setLayoutView(this);
        }
        lvMyEvaluate.setAdapter(adapter);
    }

    private void getEvaluateList() {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
            .create(UserService.class)
            .getMyEvaluateList(0, 1000)
            .enqueue(new Callback<EvaluateListBean>() {
                @Override
                public void onResponse(Call<EvaluateListBean> call, Response<EvaluateListBean> response) {
                    if(!isShowing()){
                        return;
                    }

                    EvaluateListBean bean = response.body();
                    if(bean.getCode() != 200){
                        Toast.makeText(BaseApplication.getContext(), "获取评论列表失败！", Toast.LENGTH_LONG).show();
                        return;
                    }

                    List<DataBean> dataBeans = bean.getData();
                    if(dataBeans == null || dataBeans.isEmpty()){
                        Toast.makeText(BaseApplication.getContext(), "没有任何评论！", Toast.LENGTH_LONG).show();
                        return;
                    }

                    adapter.updateDataAndNotifyDataChanged(dataBeans);
                }

                @Override
                public void onFailure(Call<EvaluateListBean> call, Throwable t) {
                    Toast.makeText(BaseApplication.getContext(), "获取评论列表失败！", Toast.LENGTH_LONG).show();
                }
            });
    }
}
