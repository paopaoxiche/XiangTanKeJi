package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.bean.WashServiceListBean;

import java.util.ArrayList;
import java.util.List;

public class WashServerListAdapter extends BaseAdapter{

    private Context context;

    List<WashServiceListBean.DataBean> washServerList = new ArrayList<>();

    public WashServerListAdapter(Context context, List<WashServiceListBean.DataBean> dataBeans) {
        washServerList.addAll(dataBeans);
        this.context = context;
    }

    @Override
    public int getCount() {
        return washServerList.size();
    }

    @Override
    public Object getItem(int position) {
        return washServerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return washServerList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.goodsNameTextView = convertView.findViewById(R.id.goods_name_text_view);
            holder.goodsImageView = (ImageView) convertView.findViewById(R.id.goods_image_view);
            holder.describeTextView = (TextView) convertView.findViewById(R.id.goods_describe_text_view);
            holder.currentPriceTextView = (TextView) convertView.findViewById(R.id.current_price_text_view);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.describeTextView.setText(washServerList.get(position).getDescribe());
        holder.currentPriceTextView.setText(String.format("¥%s", washServerList.get(position).getPrice()));
//        Glide.with(context).load(washServerList.get(position).get()).into(holder.goodsImageView);
        holder.goodsNameTextView.setText(washServerList.get(position).getName());

        return convertView;
    }

    private class ViewHolder {
        private TextView  goodsNameTextView;
        private ImageView goodsImageView;
        private TextView  describeTextView;
        private TextView  currentPriceTextView;
    }
}
