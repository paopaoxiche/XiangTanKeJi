package com.xtkj.paopaoxiche.presenter;

import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.bean.UpdateBean;
import com.xtkj.paopaoxiche.contract.IDriverContract;
import com.xtkj.paopaoxiche.model.UserModel;

public class DriverMyInfoPresenterImpl implements IDriverContract.IMyInfoPresenter, UserModel.UserInfoListener{

    IDriverContract.IMyInfoView myInfoView;

    public DriverMyInfoPresenterImpl(IDriverContract.IMyInfoView iMyInfoView) {
        myInfoView = iMyInfoView;
        myInfoView.setPresenter(this);
    }


    @Override
    public void onCreate() {
        UserModel.getInstance().addListener(this);
        UserModel.getInstance().checkUpdate(BaseApplication.getVersionName());
    }

    @Override
    public void onDestroy() {
        UserModel.getInstance().removeListener(this);
    }

    @Override
    public void modifyUserInfo(String modifyType) {
        myInfoView.updateUserInfo(modifyType);
    }

    @Override
    public void checkUpdate(UpdateBean updateBean) {
        if (!updateBean.getData().isHasNewApp()) {
            return;
        }
        myInfoView.hasUpdate();
    }

    @Override
    public void timeOut(String modifyType) {

    }
}
