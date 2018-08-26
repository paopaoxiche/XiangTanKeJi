package com.xtkj.paopaoxiche.bean;

import java.util.List;

public class WashCommodityBean {


    /**
     * code : 200
     * data : [{"currentPrice":7.2,"describe":"汽车用品批发车用伸缩拖把 除尘掸子擦车蜡拖 洗车带包装扁刷","id":2,"image":"http://101.200.63.245:8080/commodity/2018/08/09/11b6104b845c4cc684007a3505de3619.jpg","name":"车用伸缩拖把","originPrice":8.2}]
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
         * currentPrice : 7.2
         * describe : 汽车用品批发车用伸缩拖把 除尘掸子擦车蜡拖 洗车带包装扁刷
         * id : 2
         * image : http://101.200.63.245:8080/commodity/2018/08/09/11b6104b845c4cc684007a3505de3619.jpg
         * name : 车用伸缩拖把
         * originPrice : 8.2
         */

        private double currentPrice;
        private String describe;
        private int id;
        private String image;
        private String name;
        private double originPrice;

        public double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getOriginPrice() {
            return originPrice;
        }

        public void setOriginPrice(double originPrice) {
            this.originPrice = originPrice;
        }
    }
}
