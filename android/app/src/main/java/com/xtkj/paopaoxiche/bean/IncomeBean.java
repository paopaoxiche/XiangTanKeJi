package com.xtkj.paopaoxiche.bean;

import java.util.List;

public class IncomeBean {


    /**
     * code : 200
     * data : [{"items":[{"carType":2,"carTypeText":"中型车9座及8座","id":27,"money":1228,"title":"lqk洗车服务"},{"carType":0,"carTypeText":"","id":29,"money":1228,"title":""}],"time":1535260540000,"totalMoney":2456},{"items":[{"carType":0,"carTypeText":"","id":28,"money":395,"title":""}],"time":1535564588000,"totalMoney":395}]
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
         * items : [{"carType":2,"carTypeText":"中型车9座及8座","id":27,"money":1228,"title":"lqk洗车服务"},{"carType":0,"carTypeText":"","id":29,"money":1228,"title":""}]
         * time : 1535260540000
         * totalMoney : 2456
         */

        private long time;
        private double totalMoney;
        private List<ItemsBean> items;

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public double getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(double totalMoney) {
            this.totalMoney = totalMoney;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * carType : 2
             * carTypeText : 中型车9座及8座
             * id : 27
             * money : 1228
             * title : lqk洗车服务
             */

            private int carType;
            private String carTypeText;
            private int id;
            private double money;
            private String title;

            public int getCarType() {
                return carType;
            }

            public void setCarType(int carType) {
                this.carType = carType;
            }

            public String getCarTypeText() {
                return carTypeText;
            }

            public void setCarTypeText(String carTypeText) {
                this.carTypeText = carTypeText;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
