package com.xtkj.paopaoxiche.bean;

public class UpdateBean {

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public DataBean getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public class DataBean {
        int buildCode;
        String downloadUrl;
        boolean hasNewApp;
        int mandatoryUpdata;
        int systemType;
        String updataLog;
        String versionCode;

        public int getBuildCode() {
            return buildCode;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public boolean isHasNewApp() {
            return hasNewApp;
        }

        public int getMandatoryUpdata() {
            return mandatoryUpdata;
        }

        public int getSystemType() {
            return systemType;
        }

        public String getUpdataLog() {
            return updataLog;
        }

        public String getVersionCode() {
            return versionCode;
        }
    }

}
