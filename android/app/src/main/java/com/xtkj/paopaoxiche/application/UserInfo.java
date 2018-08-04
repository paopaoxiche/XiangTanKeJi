package com.xtkj.paopaoxiche.application;


public class UserInfo {
    private static String avatar;
    private static String id = "";
    private static String nickName;
    private static long regTime;
    private static int score;
    private static String token = "";
    private static int type;
    private static String userPhone;

    public static String getAvatar() {
        return avatar;
    }

    public static void setAvatar(String avatar) {
        UserInfo.avatar = avatar;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        UserInfo.id = id;
    }

    public static String getNickName() {
        return nickName;
    }

    public static void setNickName(String nickName) {
        UserInfo.nickName = nickName;
    }

    public static long getRegTime() {
        return regTime;
    }

    public static void setRegTime(long regTime) {
        UserInfo.regTime = regTime;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        UserInfo.score = score;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        UserInfo.token = token;
    }

    public static int getType() {
        return type;
    }

    public static void setType(int type) {
        UserInfo.type = type;
    }

    public static String getUserPhone() {
        return userPhone;
    }

    public static void setUserPhone(String userPhone) {
        UserInfo.userPhone = userPhone;
    }
}

