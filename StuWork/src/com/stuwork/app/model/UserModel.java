package com.stuwork.app.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

/**
 * Created by wukai on 15/12/22.
 * <p/>
 * id name age avart  account friends
 */

public class UserModel extends Model<UserModel> {

    public static UserModel DAO = new UserModel();

    public UserModel getUserByAccount(String account) {

        return DAO.findFirst("select * from user where account=?", account);
    }

    public long getUserCount(){
        return DAO.findFirst("select count(id) as count from user").getInt("count");
    }

    //获取好友列表当前获取所有的服务器用户
    public List<UserModel> getUserFriends(){
        return DAO.find("select * from user ");
    }

}
