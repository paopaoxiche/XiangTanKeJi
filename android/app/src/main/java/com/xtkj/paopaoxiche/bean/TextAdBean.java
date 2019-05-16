package com.xtkj.paopaoxiche.bean;

import java.util.List;

public class TextAdBean {


    /**
     * code : 200
     * data : ["这是广告内容","文字广告内容\n1. blabla\n2. wulawula"]
     * msg : 请求成功
     */

    private int code;
    private String msg;
    private List<String> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
