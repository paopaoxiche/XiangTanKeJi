package com.xtkj.paopaoxiche.bean;

import java.util.List;

public class WashShopBean {


    /**
     * code : 200
     * data : [{"avatar":"http://localhost:8080/avatars/2018/08/08/51e6034111a44aa0aca5cf5cdb288e73.jpeg","list":[{"currentPrice":7.2,"describe":"汽车用品批发车用伸缩拖把 除尘掸子擦车蜡拖 洗车带包装扁刷","id":2,"image":"http://localhost:8080/commodity/2018/08/09/11b6104b845c4cc684007a3505de3619.jpg","name":"车用伸缩拖把","originPrice":8.2,"washId":5}],"name":"老艾洗车场","washId":5}]
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
         * avatar : http://localhost:8080/avatars/2018/08/08/51e6034111a44aa0aca5cf5cdb288e73.jpeg
         * list : [{"currentPrice":7.2,"describe":"汽车用品批发车用伸缩拖把 除尘掸子擦车蜡拖 洗车带包装扁刷","id":2,"image":"http://localhost:8080/commodity/2018/08/09/11b6104b845c4cc684007a3505de3619.jpg","name":"车用伸缩拖把","originPrice":8.2,"washId":5}]
         * name : 老艾洗车场
         * washId : 5
         */

        private String avatar;
        private String name;
        private int washId;
        private List<ListBean> list;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWashId() {
            return washId;
        }

        public void setWashId(int washId) {
            this.washId = washId;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * currentPrice : 7.2
             * describe : 汽车用品批发车用伸缩拖把 除尘掸子擦车蜡拖 洗车带包装扁刷
             * id : 2
             * image : http://localhost:8080/commodity/2018/08/09/11b6104b845c4cc684007a3505de3619.jpg
             * name : 车用伸缩拖把
             * originPrice : 8.2
             * washId : 5
             */

            private double currentPrice;
            private String describe;
            private int id;
            private String image;
            private String name;
            private double originPrice;
            private int washId;

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

            public int getWashId() {
                return washId;
            }

            public void setWashId(int washId) {
                this.washId = washId;
            }
        }
    }
}
