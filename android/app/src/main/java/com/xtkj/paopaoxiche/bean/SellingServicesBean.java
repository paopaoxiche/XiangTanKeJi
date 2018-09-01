package com.xtkj.paopaoxiche.bean;

import java.util.List;

public class SellingServicesBean {


    /**
     * code : 200
     * data : [{"carModel":0,"carWashId":4,"describe":"这是测试洗车服务的描述","id":5,"isActive":true,"name":"测试洗车","price":23.49}]
     * msg : 请求成功
     * pageIndex : 0
     * pageSize : 20
     * totalCount : 0
     */

    private int code;
    private String msg;
    private int pageIndex;
    private int pageSize;
    private int totalCount;
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

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * carModel : 0
         * carWashId : 4
         * describe : 这是测试洗车服务的描述
         * id : 5
         * isActive : true
         * name : 测试洗车
         * price : 23.49
         */

        private int carModel;
        private int carWashId;
        private String describe;
        private int id;
        private boolean isActive;
        private String name;
        private double price;

        public int getCarModel() {
            return carModel;
        }

        public void setCarModel(int carModel) {
            this.carModel = carModel;
        }

        public int getCarWashId() {
            return carWashId;
        }

        public void setCarWashId(int carWashId) {
            this.carWashId = carWashId;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsActive() {
            return isActive;
        }

        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
