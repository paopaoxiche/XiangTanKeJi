package com.xtkj.paopaoxiche.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.xtkj.paopaoxiche.R;

public class LoadingUtil {
    private static Dialog dialog;

    public static void showDialogForLoading(final Context context, String tipContext){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        dialog = builder.create();
        builder.setTitle("");
        builder.setMessage(tipContext);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("確定",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    public static void hideDialogForLoading(){
        if(dialog != null){
            dialog.dismiss();
        }
    }
}
