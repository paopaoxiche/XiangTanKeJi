package com.xtkj.paopaoxiche.application;

public class Authentication {

    private static int user_id ;
    private static String token ;

    public static void setUser_id(int user_id) {
        Authentication.user_id = user_id;
    }

    public static void setToken(String token) {
        Authentication.token = token;
    }

    public static String getAuthentication(){
        return "user_id=" + Authentication.user_id + ",token=" + Authentication.token;
    }

}
