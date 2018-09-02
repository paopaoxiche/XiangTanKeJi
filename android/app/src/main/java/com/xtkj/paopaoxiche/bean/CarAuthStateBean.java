package com.xtkj.paopaoxiche.bean;

import java.util.List;

/**
 * 指定认证状态的车数据
 * Created by sky on 2018/9/2.
 */
public class CarAuthStateBean {
    /**
     * code : 200
     * data : [{"id":3,"model":"小型车7座及以下","status":1}]
     * msg : 请求成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3
         * model : 小型车7座及以下
         * status : 1
         */

        private int id;
        private String model;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
