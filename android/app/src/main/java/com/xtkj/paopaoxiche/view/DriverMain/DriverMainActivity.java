package com.xtkj.paopaoxiche.view.DriverMain;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.view.DriverMain.SimpleFragmentPagerAdapter;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.bean.WeatherLiveBean;
import com.xtkj.paopaoxiche.contract.IDriverContract;
import com.xtkj.paopaoxiche.presenter.DriverPresenterImpl;


public class DriverMainActivity extends BaseActivity implements IDriverContract.IDriverView,BaseFragmemt.OnFragmentInteractionListener {

    private final String TAG = "DriverMainActivity";

    private static final int PERMISSION_REQUEST_CODE = 1; //权限请求码

    IDriverContract.IDriverPresenter driverPresenter;
    private SimpleFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE

                },
                PERMISSION_REQUEST_CODE);

        initViews();
        initValues();
        initListeners();

        new DriverPresenterImpl(this);
        driverPresenter.onCreate();
    }

    @Override
    protected void initViews() {
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
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

    @Override
    public void setWeather(WeatherLiveBean weatherLiveBean) {

    }

    @Override
    public void setPresenter(IDriverContract.IDriverPresenter iDriverPresenter) {
        this.driverPresenter = iDriverPresenter;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.e(TAG,"onFragmentInteraction");
    }
}
