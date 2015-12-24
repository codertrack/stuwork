package com.stuwork.app;

import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by wukai on 15/12/24.
 */
public final class AppStart {

    public static void main(String[] args) {
        String webroot = null;

        try {
            webroot = AppStart.class.getResource("/").toURI().getPath()+"../../";
            webroot = new File(webroot).getCanonicalPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(webroot);
        JFinal.start(webroot,8080,"/",5);
    }

}
