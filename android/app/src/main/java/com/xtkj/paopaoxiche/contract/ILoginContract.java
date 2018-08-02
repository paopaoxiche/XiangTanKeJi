package com.xtkj.paopaoxiche.contract;

import android.content.Context;

import com.xtkj.paopaoxiche.base.IBasePresenter;
import com.xtkj.paopaoxiche.base.IBaseView;

public class ILoginContract {

    public interface ILoginPresenter extends IBasePresenter {
        void getMessageCode(String phone);
        void doLogin(String name,long code,int type);
    }

    public interface ILoginView extends IBaseView<ILoginPresenter> {
        Context getContext();
        void initAccount(String name);
        void showToast(String msg);
        void resetSentMsgButton();
        void login();
    }
}
