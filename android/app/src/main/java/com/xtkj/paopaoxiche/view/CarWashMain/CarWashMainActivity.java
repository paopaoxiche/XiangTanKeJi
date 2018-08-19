package com.xtkj.paopaoxiche.view.CarWashMain;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.contract.ICarWashContract;
import com.xtkj.paopaoxiche.presenter.CarWashInfoPresenterImpl;
import com.xtkj.paopaoxiche.presenter.CarWashMainPresenterImpl;
import com.xtkj.paopaoxiche.presenter.CarWashMinePresenterImpl;

import java.util.ArrayList;

public class CarWashMainActivity extends BaseActivity implements ICarWashContract.IMainView{

    ICarWashContract.IMainPresenter mainPresenter;

    CarWashHomeFragment homeFragment;
    CarWashMineFragment mineFragment;

    private static final int PERMISSION_REQUEST_CODE = 1; //权限请求码

    private SimpleFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_wash_main);

        initViews();
        initValues();
        initListeners();

        new CarWashMainPresenterImpl(this);
        mainPresenter.onCreate();
    }

    @Override
    protected void initViews() {
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this, initFragment());
        viewPager = (ViewPager) findViewById(R.id.driver_viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout =  findViewById(R.id.driver_tab);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.getTabAt(0).setCustomView(pagerAdapter.getTabView(0,true,null));
        tabLayout.getTabAt(1).setCustomView(pagerAdapter.getTabView(1,false,null));
        viewPager.setCurrentItem(0);
    }

    @Override
    protected void initValues() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE

                },
                PERMISSION_REQUEST_CODE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    0);
        }
    }

    @Override
    protected void initListeners() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                tab.setCustomView(pagerAdapter.getTabView(position,true,tab.getCustomView()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                tab.setCustomView(pagerAdapter.getTabView(position,false,tab.getCustomView()));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private ArrayList<Fragment> initFragment() {
        homeFragment = new CarWashHomeFragment();
        mineFragment = new CarWashMineFragment();

        new CarWashInfoPresenterImpl(homeFragment);
        new CarWashMinePresenterImpl(mineFragment);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(mineFragment);

        return fragments;
    }

    @Override
    public void setPresenter(ICarWashContract.IMainPresenter iMainPresenter) {
        mainPresenter = iMainPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }
}
