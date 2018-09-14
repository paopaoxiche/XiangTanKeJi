package com.xtkj.paopaoxiche.bean;

import java.util.List;

/**
* 用户消费记录列表
 * Created by sky on 2018/9/2.
 */
public class UserConsumeBean {
    /**
     * code : 200
     * data : [{"carType":"1","carWashImg":"http://localhost:8080/avatars/2018/08/08/51e6034111a44aa0aca5cf5cdb288e73.jpeg",
     * "carWashName":"老艾洗车场","commodities":[{"commodityId":5,
     * "commodityImg":"http://localhost:8080/commodity/2018/08/09/ee62e69d238d4fff8284ee1083bc9922.jpg","commodityName":"德国马牌
     * (Continental) 轮胎/汽车轮胎 225/50R17 98W UC6 适配奥迪A4L/奔驰C级/雅阁/DS5","price":749},{"commodityId":6,
     * "commodityImg":"http://localhost:8080/commodity/2018/08/09/1a089889c82d4547b2aaabd63cb73a58.jpg","commodityName":"亿力
     * YILI 家用洗车机 高压清洗机 YLQ4650C-100C 220v","price":419}],"coupons":[{"couponId":1,"couponName":"满20减5","couponPrice":5,
     * "couponType":1}],"id":23,"isEvaluation":0,"payment":60,"serviceName":"汽车精洗服务","time":1535093757,"totalPrice":1228}]
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
         * carType : 1
         * carWashImg : http://localhost:8080/avatars/2018/08/08/51e6034111a44aa0aca5cf5cdb288e73.jpeg
         * carWashName : 老艾洗车场
         * commodities : [{"commodityId":5,
         * "commodityImg":"http://localhost:8080/commodity/2018/08/09/ee62e69d238d4fff8284ee1083bc9922.jpg",
         * "commodityName":"德国马牌(Continental) 轮胎/汽车轮胎 225/50R17 98W UC6 适配奥迪A4L/奔驰C级/雅阁/DS5","price":749},{"commodityId":6,
         * "commodityImg":"http://localhost:8080/commodity/2018/08/09/1a089889c82d4547b2aaabd63cb73a58.jpg","commodityName":"亿力
         * YILI 家用洗车机 高压清洗机 YLQ4650C-100C 220v","price":419}]
         * coupons : [{"couponId":1,"couponName":"满20减5","couponPrice":5,"couponType":1}]
         * id : 23
         * isEvaluation : 0
         * payment : 60
         * serviceName : 汽车精洗服务
         * time : 1535093757
         * totalPrice : 1228
         */

        private String carType;
        private String carWashImg;
        private String carWashName;
        private int id;
        private int isEvaluation;
        private double payment;
        private String serviceName;
        private long time;
        private double totalPrice;
        private List<CommoditiesBean> commodities;
        private List<CouponsBean> coupons;

        public String getCarType() {
            return carType;
        }

        public void setCarType(String carType) {
            this.carType = carType;
        }

        public String getCarWashImg() {
            return carWashImg;
        }

        public void setCarWashImg(String carWashImg) {
            this.carWashImg = carWashImg;
        }

        public String getCarWashName() {
            return carWashName;
        }

        public void setCarWashName(String carWashName) {
            this.carWashName = carWashName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsEvaluation() {
            return isEvaluation;
        }

        public void setIsEvaluation(int isEvaluation) {
            this.isEvaluation = isEvaluation;
        }

        public double getPayment() {
            return payment;
        }

        public void setPayment(double payment) {
            this.payment = payment;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public List<CommoditiesBean> getCommodities() {
            return commodities;
        }

        public void setCommodities(List<CommoditiesBean> commodities) {
            this.commodities = commodities;
        }

        public List<CouponsBean> getCoupons() {
            return coupons;
        }

        public void setCoupons(List<CouponsBean> coupons) {
            this.coupons = coupons;
        }

        public static class CommoditiesBean {
            /**
             * commodityId : 5
             * commodityImg : http://localhost:8080/commodity/2018/08/09/ee62e69d238d4fff8284ee1083bc9922.jpg
             * commodityName : 德国马牌(Continental) 轮胎/汽车轮胎 225/50R17 98W UC6 适配奥迪A4L/奔驰C级/雅阁/DS5
             * price : 749
             */

            private int commodityId;
            private String commodityImg;
            private String commodityName;
            private double price;

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityImg() {
                return commodityImg;
            }

            public void setCommodityImg(String commodityImg) {
                this.commodityImg = commodityImg;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }
        }

        public static class CouponsBean {
            /**
             * couponId : 1
             * couponName : 满20减5
             * couponPrice : 5
             * couponType : 1
             */

            private int couponId;
            private String couponName;
            private int couponPrice;
            private int couponType;

            public int getCouponId() {
                return couponId;
            }

            public void setCouponId(int couponId) {
                this.couponId = couponId;
            }

            public String getCouponName() {
                return couponName;
            }

            public void setCouponName(String couponName) {
                this.couponName = couponName;
            }

            public int getCouponPrice() {
                return couponPrice;
            }

            public void setCouponPrice(int couponPrice) {
                this.couponPrice = couponPrice;
            }

            public int getCouponType() {
                return couponType;
            }

            public void setCouponType(int couponType) {
                this.couponType = couponType;
            }
        }
    }
}
