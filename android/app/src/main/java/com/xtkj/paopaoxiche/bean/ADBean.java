package com.xtkj.paopaoxiche.bean;

public class ADBean {

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public DataBean getData() {
        return data;
    }

    public static class DataBean {
        public String getUrl() {
            return url;
        }

        public String getImg() {
            return img;
        }

        public String getMd5() {
            return md5;
        }

        private String url;
        private String md5;
        private String img;
    }
}
