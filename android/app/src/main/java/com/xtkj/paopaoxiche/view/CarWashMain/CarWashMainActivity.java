package com.xtkj.paopaoxiche.view.CarWashMain;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseGaodeActivity;
import com.xtkj.paopaoxiche.contract.ICarWashContract;
import com.xtkj.paopaoxiche.event.BaseEvent;
import com.xtkj.paopaoxiche.model.UserModel;
import com.xtkj.paopaoxiche.presenter.CarWashInfoPresenterImpl;
import com.xtkj.paopaoxiche.presenter.CarWashMainPresenterImpl;
import com.xtkj.paopaoxiche.presenter.CarWashMinePresenterImpl;
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
                pickImage(BaseEvent.CAR_WASH_PICK_AVATAR);
                break;
            case BaseEvent.GOODS_IMAGE:
                pickImage(BaseEvent.GOODS_IMAGE);
                break;
            case BaseEvent.LOGOUT:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
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
            String path = UriUtils.getImagePath(this, data.getData());
            Bitmap bitmap = BitmapUtil.decodeSampledBitmapFromFileForRect(path, 640, 640);
            String targetPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    + File.separator + "MyTestImage" + File.separator
                    + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg";
            UserModel.getInstance().updateUserInfo(compressImage(bitmap, targetPath));

        }
        if(requestCode == BaseEvent.GOODS_IMAGE) {
            if (data == null) {
                return;
            }
            String path = UriUtils.getImagePath(this, data.getData());
            EventBus.getDefault().post(new BaseEvent(BaseEvent.SET_GOODS_IMAGE, path));
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
