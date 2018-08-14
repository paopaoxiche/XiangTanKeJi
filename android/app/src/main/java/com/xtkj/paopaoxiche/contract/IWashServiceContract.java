package com.xtkj.paopaoxiche.contract;

import android.content.Context;

import com.xtkj.paopaoxiche.base.IBasePresenter;
import com.xtkj.paopaoxiche.base.IBaseView;

public class IWashServiceContract {

    public interface IWashServicePresenter extends IBasePresenter {

    }

    public interface IWashServiceView extends IBaseView<IWashServicePresenter> {
        Context getActivityContext();
    }
}
