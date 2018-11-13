package com.xtkj.paopaoxiche.view.DriverMain;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseGaodeActivity;
import com.xtkj.paopaoxiche.event.BaseEvent;
import com.xtkj.paopaoxiche.model.UserModel;
import com.xtkj.paopaoxiche.presenter.DriverHomePresenterImpl;
import com.xtkj.paopaoxiche.presenter.DriverMyInfoPresenterImpl;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.contract.IDriverContract;
import com.xtkj.paopaoxiche.presenter.DriverPresenterImpl;
import com.xtkj.paopaoxiche.utils.BitmapUtil;
import com.xtkj.paopaoxiche.utils.UriUtils;
import com.xtkj.paopaoxiche.view.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DriverMainActivity extends BaseGaodeActivity implements IDriverContract.IDriverView {

    private final String TAG = "DriverMainActivity";
    private long firstPressedTime;
    private static final int PERMISSION_REQUEST_CODE = 1; //权限请求码

    IDriverContract.IDriverPresenter driverPresenter;
    private SimpleFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private HomeFragment homeFragment ;
    private MyInfoFragment myInfoFragment;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            savedInstanceState.remove("android:fragments");  //注意：基类是Activity时参数为android:fragments， 一定要在super.onCreate函数前执行！！！
            savedInstanceState.remove("android:support:fragments");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE

                },
                PERMISSION_REQUEST_CODE);

        EventBus.getDefault().register(this);

        initViews();
        initValues();
        initListeners();

        new DriverPresenterImpl(this);
        driverPresenter.onCreate();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initViews() {

        homeFragment = HomeFragment.newInstance();
        myInfoFragment = MyInfoFragment.newInstance();
        new DriverMyInfoPresenterImpl(myInfoFragment);
        new DriverHomePresenterImpl(homeFragment);
        fragmentArrayList.add(homeFragment);
        fragmentArrayList.add(myInfoFragment);
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this,fragmentArrayList);

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
    public void setWeather(WeatherRealTimeBean weatherRealTimeBean) {

    }

    @Override
    public void setPresenter(IDriverContract.IDriverPresenter iDriverPresenter) {
        this.driverPresenter = iDriverPresenter;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstPressedTime < 2000) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            firstPressedTime = System.currentTimeMillis();
        }
    }

    private void pickImage(int type) {
        Intent intent = new Intent();
        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        } else {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, type);
    }

    @Subscribe
    public void onEventMainThread(BaseEvent baseEvent) {
        switch (baseEvent.getType()) {
            case BaseEvent.CAR_WASH_PICK_AVATAR:
            case BaseEvent.CAR_PHOTO_BACK:
            case BaseEvent.CAR_PHOTO_CONER:
                pickImage(baseEvent.getType());
                break;
            case BaseEvent.LOGOUT:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String path = UriUtils.getImagePath(this, data.getData());
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == BaseEvent.CAR_WASH_PICK_AVATAR) {
            Bitmap bitmap = BitmapUtil.decodeSampledBitmapFromFileForRect(path, 640, 640);
            String targetPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    + File.separator + "MyTestImage" + File.separator
                    + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg";
            UserModel.getInstance().updateUserInfo(compressImage(bitmap, targetPath));

        }
        if(requestCode == BaseEvent.CAR_PHOTO_CONER) {
            EventBus.getDefault().post(new BaseEvent(BaseEvent.SET_CAR_COVER, path));
        }
        if(requestCode == BaseEvent.CAR_PHOTO_BACK) {
            EventBus.getDefault().post(new BaseEvent(BaseEvent.SET_CAR_BACK, path));
        }
    }

    private File compressImage(Bitmap bm, String targetPath) {

        int quality = 80;//压缩比例0-100

        File outputFile = new File(targetPath);
        try {
            if (!outputFile.exists()) {
                outputFile.getParentFile().mkdirs();
                //outputFile.createNewFile();
            } else {
                outputFile.delete();
            }
            FileOutputStream out = new FileOutputStream(outputFile);
            bm.compress(Bitmap.CompressFormat.JPEG, quality, out);
            out.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return outputFile;
    }
}
