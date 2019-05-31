package com.xtkj.paopaoxiche.view.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.bean.AvatarBean;

import java.util.ArrayList;
import java.util.List;

public class AvatarAdapter extends BaseAdapter {

    private Context context;

    List<AvatarBean.DataBean> avatarList = new ArrayList<>();

    public AvatarAdapter(Context context, List<AvatarBean.DataBean> dataBeans) {
        avatarList.addAll(dataBeans);
        this.context = context;
    }

    @Override
    public int getCount() {
        return avatarList.size();
    }

    @Override
    public Object getItem(int position) {
        return avatarList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return avatarList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_avatar, null);
            holder = new ViewHolder();
            holder.avatarImageView = convertView.findViewById(R.id.choose_avatar_image_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(avatarList.get(position).getImg()).into(holder.avatarImageView);

        return convertView;
    }

    private class ViewHolder {
        ImageView avatarImageView;
    }
}
