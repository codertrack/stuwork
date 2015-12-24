package com.stuwork.app.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by wukai on 15/12/22.
 */
public class ResponseUtils {

    public static JSONObject createResponseContent(int code,String message,JSONObject content){
        JSONObject res = new JSONObject();
        res.put("code",code);
        res.put("msg",message);
        res.put("result",content);
        return res;
    }

}
