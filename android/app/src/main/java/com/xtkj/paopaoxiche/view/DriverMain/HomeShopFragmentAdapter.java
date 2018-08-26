package com.xtkj.paopaoxiche.view.DriverMain;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;

import java.util.ArrayList;
import java.util.List;

public class HomeShopFragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<WashShopBean.DataBean> washServiceData = null;
    private ArrayList<ShopFragment> shopFragments = new ArrayList<>();

    public HomeShopFragmentAdapter(FragmentManager fm,List<WashShopBean.DataBean> data) {
        super(fm);
        washServiceData = (ArrayList<WashShopBean.DataBean>) data;
        for(WashShopBean.DataBean d:data){
            shopFragments.add(ShopFragment.newInstance(d));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return shopFragments.get(position);

    }

    @Override
    public int getCount() {
        return shopFragments.size();
    }
}
