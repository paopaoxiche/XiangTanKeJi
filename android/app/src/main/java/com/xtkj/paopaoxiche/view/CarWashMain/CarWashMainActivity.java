package com.xtkj.paopaoxiche.view.CarWashMain;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.contract.ICarWashContract;
import com.xtkj.paopaoxiche.presenter.CarWashInfoPresenterImpl;
import com.xtkj.paopaoxiche.presenter.CarWashMainPresenterImpl;
import com.xtkj.paopaoxiche.presenter.CarWashMinePresenterImpl;

public class CarWashMainActivity extends BaseActivity implements BaseFragmemt.OnFragmentInteractionListener,
        ICarWashContract.IMainView, RadioGroup.OnCheckedChangeListener{

    ICarWashContract.IMainPresenter mainPresenter;

    CarWashInfoFragment infoFragment;
    CarWashMineFragment mineFragment;

    RadioGroup changeFragmentRadioGroup;
    RadioButton mainRadioButton;
    RadioButton mineRadioButton;

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
        changeFragmentRadioGroup = findViewById(R.id.check_radio_group);
        mainRadioButton = findViewById(R.id.main_radio_button);
        mineRadioButton = findViewById(R.id.mine_radio_button);
    }

    @Override
    protected void initValues() {
        initFragment();
    }

    @Override
    protected void initListeners() {
        changeFragmentRadioGroup.setOnCheckedChangeListener(this);
    }

    private void initFragment() {
        infoFragment = new CarWashInfoFragment();
        mineFragment = new CarWashMineFragment();

        new CarWashInfoPresenterImpl(infoFragment);
        new CarWashMinePresenterImpl(mineFragment);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_frame_layout, mineFragment).hide(mineFragment);
        transaction.add(R.id.fragment_frame_layout, infoFragment);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_radio_button:
                changeFragment(mineFragment, infoFragment);
                mainRadioButton.setTextColor(Color.BLACK);
                mineRadioButton.setTextColor(Color.DKGRAY);
                break;

            case R.id.mine_radio_button:
                changeFragment(infoFragment, mineFragment);
                mainRadioButton.setTextColor(Color.DKGRAY);
                mineRadioButton.setTextColor(Color.BLACK);
                break;

            default:
                break;
        }
    }

    private void changeFragment(Fragment hideFragment, Fragment showFragment) {
        if (!showFragment.isHidden() && hideFragment.isHidden()) {
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(hideFragment);
        transaction.show(showFragment).commitAllowingStateLoss(); // 显示新的Fragment
    }

    @Override
    public void setPresenter(ICarWashContract.IMainPresenter iMainPresenter) {
        mainPresenter = iMainPresenter;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
