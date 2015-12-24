package com.stuwork.app.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.stuwork.app.config.RandomAvart;
import com.stuwork.app.model.UserModel;
import com.stuwork.app.utils.EncryptUtils;
import com.stuwork.app.utils.ResponseUtils;

import java.util.List;

/**
 * Created by wukai on 15/12/14.
 *
 */
public class UserController extends Controller {

    private static final int BASE_SEQUNCE = 10000;

    public void login(){
        //获取账户号码
        String account = getPara("account");
        String password = getPara("password");

        UserModel um = UserModel.DAO.getUserByAccount(account);

        if (um == null){
            renderJson(ResponseUtils.createResponseContent(1,"用户不存在",null));
            return;
        }

        String username = um.getStr("name");

        String md5 = null;
        try {
            md5 = EncryptUtils.md5Encode(username+password+account);
            System.out.println(md5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String md5pwd = um.get("passwd");
        System.out.println(md5pwd);
        //判断用户名和密码是否正确
        if (md5pwd.equals(md5)){
            JSONObject response = new JSONObject();
            //返回好友列表
            response.put("friends",getfriends());

            renderJson(ResponseUtils.createResponseContent(0,"login sucess",response));
        }else {
            renderJson(ResponseUtils.createResponseContent(1,"login Failed",null));
        }
    }


    //加载好友 当前返回系统所有用户
    public JSONArray getfriends() {
        JSONArray userarray = null;
        List<UserModel> userlist = UserModel.DAO.getUserFriends();
        if (userlist != null && userlist.size() != 0){
            userarray = new JSONArray();
            for (UserModel user:userlist){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("account",user.getStr("account"));
                jsonObject.put("username",user.getStr("name"));
                String avart = user.getStr("avart");
                if (avart == null ||avart.length() ==0){
                    avart = RandomAvart.generatorAvart();
                    UserModel.DAO.getUserByAccount(user.getStr("account")).
                            set("avart",avart).update();
                }
                jsonObject.put("avart",avart);
                jsonObject.put("age",18);
                jsonObject.put("shuoshuo","天晴了雨停了,感觉自己又行了....");
                userarray.add(jsonObject);
            }
        }
        return userarray;
    }


    //用户注册
    public void register(){

        String username = getPara("username");
        String password = getPara("password");

        if (username == null || password == null){
            renderJson(ResponseUtils.createResponseContent(1,"username,null",null));
        }

        UserModel user = getModel(UserModel.class);
        user.set("name",username);
        //user.set("age",18);
        //生成账号
        long size = UserModel.DAO.getUserCount();
        long account = BASE_SEQUNCE+size;
        user.set("account",""+account);
        try {
            user.set("passwd",EncryptUtils.md5Encode(username+password+account));

        } catch (Exception e) {
            e.printStackTrace();
            //注册失败
            renderJson(ResponseUtils.createResponseContent(1,"register failed",null));
            return;
        }

        //生成一个用户头像地址
        user.set("avart", RandomAvart.generatorAvart());
        boolean flag = user.save();

        if (flag){
            //注册成功
            JSONObject res = new JSONObject();
            res.put("account",""+account);
            res.put("friends",getfriends());
            renderJson(ResponseUtils.createResponseContent(0,"register sucess",res));

        }else {
            renderJson(ResponseUtils.createResponseContent(1,"register failed",null));
        }
    }



    public void friends(){
        String account = getPara("account");
        JSONArray jsonArray = getfriends();
        JSONObject response = null;
        if (jsonArray != null && jsonArray.size() !=0){
            response = new JSONObject();
            response.put("friends",jsonArray);
        }

        renderJson(ResponseUtils.createResponseContent(0,"success",response));
    }
}
