package com.xtkj.paopaoxiche.bean;

import java.util.List;

public class WashShopBean {


    /**
     * code : 200
     * data : [{"currentPrice":7.2,"describe":"汽车用品批发车用伸缩拖把 除尘掸子擦车蜡拖 洗车带包装扁刷","id":2,"image":"http://101.200.63.245:8080/commodity/2018/08/09/11b6104b845c4cc684007a3505de3619.jpg","name":"车用伸缩拖把","originPrice":8.2},{"currentPrice":6,"describe":"凯利来1.1升水蜡洗车液高泡洗车液汽车用品泡沫清洗剂 汽车清洁剂","id":3,"image":"http://101.200.63.245:8080/commodity/2018/08/09/b92d735568b6447aaff818fd83c52f69.jpg","name":"凯利来1.1升水蜡洗车液","originPrice":5},{"currentPrice":242,"describe":"因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，本司不能确保客户收到的货物与商城图片、产地、附件说明完全一致。只能确保为原厂正货！并且保证与当时市场上同样主流新品一致。若本商城没有及时更新，请大家谅解！","id":4,"image":"http://101.200.63.245:8080/commodity/2018/08/09/c7fc731a9559488b91fd8baca0fdf7e9.jpg","name":"东风本田(HONDA)原厂半合成机油/润滑油 0W-20 SN级 4L 思域/CR-V/XR-V/杰德/哥瑞/艾力绅","originPrice":249},{"currentPrice":749,"describe":"本站商品信息均来自于合作方，其真实性、准确性和合法性由信息拥有者（合作方）负责。本站不提供任何保证，并不承担任何法律责任。","id":5,"image":"http://101.200.63.245:8080/commodity/2018/08/09/ee62e69d238d4fff8284ee1083bc9922.jpg","name":"德国马牌(Continental) 轮胎/汽车轮胎 225/50R17 98W UC6 适配奥迪A4L/奔驰C级/雅阁/DS5","originPrice":749}]
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
