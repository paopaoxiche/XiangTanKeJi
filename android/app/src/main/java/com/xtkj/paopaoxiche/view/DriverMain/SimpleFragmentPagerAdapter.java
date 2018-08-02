package com.xtkj.paopaoxiche.view.DriverMain;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;

import java.util.ArrayList;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"首页","我的"};
    private int[] imageTrueResId = {R.drawable.btn_home_true,
            R.drawable.btn_my_true};
    private int[] imageNorResId = {R.drawable.btn_home_nor,
            R.drawable.btn_my_nor};
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context,ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.context = context;
        for(int i  = 0 ; i < fragmentArrayList.size();i ++ ){
            fragments.add(fragmentArrayList.get(i));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        return tabTitles[position];
        return null;
    }

    public View getTabView(int position,Boolean isActive,View view){
        if(null==view)
            view = LayoutInflater.from(context).inflate(R.layout.item_driver_tab, null);
        TextView tv=  view.findViewById(R.id.driver_tab_text);
        tv.setText(tabTitles[position]);
        ImageView img =  view.findViewById(R.id.driver_tab_image);
        if(isActive){
            tv.setTextColor(Color.rgb(51,51,51));
            img.setImageResource(imageTrueResId[position]);
        }else {
            tv.setTextColor(Color.rgb(170,170,170));
            img.setImageResource(imageNorResId[position]);
        }
        return view;
    }

}
