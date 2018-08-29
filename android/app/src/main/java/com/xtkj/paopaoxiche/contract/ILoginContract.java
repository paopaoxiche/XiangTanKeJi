package com.xtkj.paopaoxiche.contract;

import android.content.Context;
import android.content.Intent;

import com.xtkj.paopaoxiche.base.IBasePresenter;
import com.xtkj.paopaoxiche.base.IBaseView;

public class ILoginContract {

    public interface ILoginPresenter extends IBasePresenter {
        void getMessageCode(String phone);
        void doLogin(String name,int roleType, long code);
    }

    public interface ILoginView extends IBaseView<ILoginPresenter> {
        Context getContext();
        void initAccount(String name);
        void showToast(String msg);
        void resetSentMsgButton();
        void login(Intent intent);
        void register();
    }
}
