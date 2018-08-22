package com.xtkj.paopaoxiche.view.CarWashMain;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.base.BaseGaodeActivity;
import com.xtkj.paopaoxiche.contract.ICarWashContract;
import com.xtkj.paopaoxiche.event.BaseEvent;
import com.xtkj.paopaoxiche.presenter.CarWashInfoPresenterImpl;
import com.xtkj.paopaoxiche.presenter.CarWashMainPresenterImpl;
import com.xtkj.paopaoxiche.presenter.CarWashMinePresenterImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class CarWashMainActivity extends BaseGaodeActivity implements ICarWashContract.IMainView{

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
        EventBus.getDefault().register(this);
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
        EventBus.getDefault().unregister(this);
    }

    private void pickAvatar() {
        Intent intent = new Intent();
        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        } else {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, BaseEvent.CAR_WASH_PICK_AVATAR);
    }

    @Subscribe
    public void onEventMainThread(BaseEvent baseEvent) {
        switch (baseEvent.getType()) {
            case BaseEvent.CAR_WASH_PICK_AVATAR:
                pickAvatar();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == BaseEvent.CAR_WASH_PICK_AVATAR) {
            if (data == null) {
                return;
            }
                // Todo BUG-20580，获取图片时要进行压缩处理，并旋转之后保存到 Hst/cache 文件夹下
//                String path = MediaUtils.getImagePath(this, data.getData());
//                final BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inJustDecodeBounds = true;
//                BitmapFactory.decodeFile(path, options);
//                EventBus.getDefault().post(
//                        new LocalFileDto(BaseDto.LOCAL_FILE_PATH, path, options.outWidth, options.outHeight));
        }
    }
}
