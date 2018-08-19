package com.xtkj.paopaoxiche.model;


public class UserInfo {
    private static String avatar;
    private static int id;
    private static String nickName;
    private static long regTime;
    private static int score;
    private static String token = "";
    private static int type;
    private static String userPhone;

    private static int authStatus;
    private static int washId;
    private static int washCount;

    private static boolean driver = true;

    public static boolean isDriver() {
        return driver;
    }

    protected static void setDriver(boolean isDriver) {
        UserInfo.driver = isDriver;
    }

    public static String getAvatar() {
        return avatar;
    }

    protected static void setAvatar(String avatar) {
        UserInfo.avatar = avatar;
    }

    public static int getId() {
        return id;
    }

    protected static void setId(int id) {
        UserInfo.id = id;
    }

    public static String getNickName() {
        return nickName;
    }

    protected static void setNickName(String nickName) {
        UserInfo.nickName = nickName;
    }

    public static long getRegTime() {
        return regTime;
    }

    protected static void setRegTime(long regTime) {
        UserInfo.regTime = regTime;
    }

    public static int getScore() {
        return score;
    }

    protected static void setScore(int score) {
        UserInfo.score = score;
    }

    public static String getToken() {
        return token;
    }

    protected static void setToken(String token) {
        UserInfo.token = token;
    }

    public static int getType() {
        return type;
    }

    protected static void setType(int type) {
        UserInfo.type = type;
    }

    public static String getUserPhone() {
        return userPhone;
    }

    protected static void setUserPhone(String userPhone) {
        UserInfo.userPhone = userPhone;
    }

    public static int getAuthStatus() {
        return authStatus;
    }

    public static void setAuthStatus(int authStatus) {
        UserInfo.authStatus = authStatus;
    }

    public static int getWashId() {
        return washId;
    }

    public static void setWashId(int washId) {
        UserInfo.washId = washId;
    }

    public static int getWashCount() {
        return washCount;
    }

    public static void setWashCount(int washCount) {
        UserInfo.washCount = washCount;
    }
}

