package com.ghareeb.myloginregisterapp.dao;

public class UserFactory {

    // the job of userFactory is to have one object only of UserDao

    private static UserDao userDao = new UserDao();

    public UserFactory(){

    }

    public static UserDao getUserDao(){
        return userDao;
    }
}
