package com.xtkj.paopaoxiche.bean;

public class LoginBean {

    /**
     * code : 200
     * data : {"avatar":"http://101.200.63.245:8080/avatars/avatars/2018/07/14/bc394a3632c045339ee71d6bd2a91b53.jpg?1531586075883","id":100000020,"nickName":"酷酷酷","regTime":1531526400000,"score":0,"token":"bb685711d4cf4e11b5890bdf7479006c","type":0,"userPhone":"15814496601"}
     * msg : 请求成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * avatar : http://101.200.63.245:8080/avatars/avatars/2018/07/14/bc394a3632c045339ee71d6bd2a91b53.jpg?1531586075883
         * id : 100000020
         * nickName : 酷酷酷
         * regTime : 1531526400000
         * score : 0
         * token : bb685711d4cf4e11b5890bdf7479006c
         * type : 0
         * userPhone : 15814496601
         */

        private String avatar;
        private int id;
        private String nickName;
        private long regTime;
        private int score;
        private String token;
        private int type;
        private String userPhone;
        private int washCount;
        private int userCount;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public long getRegTime() {
            return regTime;
        }

        public void setRegTime(long regTime) {
            this.regTime = regTime;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public int getWashCount() {
            return washCount;
        }

        public void setWashCount(int washCount) {
            this.washCount = washCount;
        }

        public int getUserCount() {
            return userCount;
        }

        public void setUserCount(int userCount) {
            this.userCount = userCount;
        }
    }
}
