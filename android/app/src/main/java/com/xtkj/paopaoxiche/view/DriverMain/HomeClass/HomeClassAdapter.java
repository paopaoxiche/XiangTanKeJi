package com.xtkj.paopaoxiche.view.DriverMain.HomeClass;


import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.bean.ClassificationCommodityBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeClassAdapter extends RecyclerView.Adapter<HomeClassAdapter.ViewHolder> {


    private ArrayList<ClassificationCommodityBean.DataBean> dataBeans = null;
    public Context mContext;


    public HomeClassAdapter(ClassificationCommodityBean classificationCommodityBean, Context context) {
        this.dataBeans = (ArrayList<ClassificationCommodityBean.DataBean>) classificationCommodityBean.getData();
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_class, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(mContext).load(dataBeans.get(position).getImg()).into(holder.itemClassImg);
        holder.itemClassText.setText(dataBeans.get(position).getDetail());
        holder.classPrice1.setText(dataBeans.get(position).getDiscountPrice());
        if(dataBeans.get(position).getOriginalPrice()!=null && !dataBeans.get(position).getOriginalPrice().equals("")){
            holder.classPrice2.setText(dataBeans.get(position).getOriginalPrice());
            holder.classPrice2.setPaintFlags(holder.classPrice2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else holder.classPrice2.setVisibility(View.GONE);

    }


    @Override
    public int getItemCount() {
        if (dataBeans != null)
            return dataBeans.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_class_img)
        ImageView itemClassImg;
        @BindView(R.id.item_class_text)
        TextView itemClassText;
        @BindView(R.id.class_price1)
        TextView classPrice1;
        @BindView(R.id.class_price2)
        TextView classPrice2;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
