package com.xtkj.paopaoxiche.view.CarWashMain;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xtkj.paopaoxiche.bean.WashRecordBean;
import com.xtkj.paopaoxiche.bean.WashServicesBean;

import java.util.ArrayList;
import java.util.List;

public class RecentWashAdapter extends BaseAdapter{

    List<WashRecordBean.DataBean> washRecordBeanList = new ArrayList<>();

    @Override
    public int getCount() {
        return washRecordBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        if (washRecordBeanList.size() > position + 1) {
            return null;
        }
        return washRecordBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (washRecordBeanList.size() > position + 1) {
            return -1;
        }
        return washRecordBeanList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


}
