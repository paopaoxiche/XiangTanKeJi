package com.xtkj.paopaoxiche.presenter;

import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.bean.UpdateBean;
import com.xtkj.paopaoxiche.contract.ICarWashContract;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.model.UserModel;
import com.xtkj.paopaoxiche.model.update.DownloadAPKCallback;
import com.xtkj.paopaoxiche.model.update.DownloadManager;
import com.xtkj.paopaoxiche.view.CarWashMain.CarWashMineFragment;

public class CarWashMinePresenterImpl implements ICarWashContract.IMinePresenter, UserModel.UserInfoListener {

    ICarWashContract.IMineView mineView;

    public CarWashMinePresenterImpl(ICarWashContract.IMineView iMineView) {
        mineView = iMineView;
        mineView.setPresenter(this);
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
        mineView.updateUserInfo(modifyType);
    }

    @Override
    public void checkUpdate(UpdateBean updateBean) {
        if (!updateBean.getData().isHasNewApp()) {
            return;
        }
        mineView.hasUpdate();
    }

    @Override
    public void timeOut(String modifyType) {

    }

    @Override
    public void update() {

    }
}
