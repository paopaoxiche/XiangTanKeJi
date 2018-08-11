package com.xtkj.paopaoxiche.contract;

import android.content.Context;
import android.content.Intent;

import com.xtkj.paopaoxiche.base.IBasePresenter;
import com.xtkj.paopaoxiche.base.IBaseView;

public class IGuideContract {

    public interface IGuidePresenter extends IBasePresenter {
        void autoLogin();
    }

    public interface IGuideView extends IBaseView<IGuidePresenter> {
        void startActivityForIntent(Intent intent, Class clazz);
        Context getContext();
    }
}
