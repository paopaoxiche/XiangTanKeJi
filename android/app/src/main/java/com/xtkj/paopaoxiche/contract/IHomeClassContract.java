package com.xtkj.paopaoxiche.contract;

import android.content.Context;
import android.content.Intent;

import com.xtkj.paopaoxiche.base.IBasePresenter;
import com.xtkj.paopaoxiche.base.IBaseView;

public class IHomeClassContract {

    public interface IHomeClassPresenter extends IBasePresenter {

    }

    public interface IHomeClassView extends IBaseView<IHomeClassPresenter> {

        Context getContext();
        void updateList();
    }
}
