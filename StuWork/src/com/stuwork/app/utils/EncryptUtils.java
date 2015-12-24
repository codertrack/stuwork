package com.stuwork.app.utils;

import java.security.MessageDigest;

/**
 * Created by wukai on 15/12/14.
 */
public class EncryptUtils {
    public static String md5Encode(String content)throws Exception{
        //选择摘要算法
        MessageDigest md = MessageDigest.getInstance("md5");
        //设置要摘要的内容
        md.update(content.getBytes("utf-8"));
        //生成摘要
        byte[] value = md.digest();

        return toHexString(value);

    }


    public static String toHexString(byte[] data){

        StringBuffer strbuf = new StringBuffer();

        for (int i = 0; i < data.length; i++) {
            int value = data[i]&255;
            if (value<16){
                strbuf.append("0");
            }
            strbuf.append(Integer.toHexString(value));
        }

        return strbuf.toString();
    }


}
