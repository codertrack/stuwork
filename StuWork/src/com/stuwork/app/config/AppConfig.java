package com.stuwork.app.config;

import com.jfinal.config.*;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.Sqlite3Dialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.stuwork.app.controller.IndexController;
import com.stuwork.app.model.UserModel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by wukai on 15/12/2.
 */
public class AppConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants cons) {
        cons.setDevMode(true);
        //cons.setEncoding("utf-8");
        loadPropertyFile("a_little_config.txt");

    }

    @Override
    public void configRoute(Routes router) {
        //学生前端
        router.add(new StudentRouter());
        //老师页面
        router.add(new TeacherRouter());
        //后台管理
        router.add(new AdminRouter());
        //首页
        router.add("/", IndexController.class);
    }


    @Override
    public void configPlugin(Plugins me) {
        String  jdbc_url = null;
        try {
            jdbc_url = getClass().getResource("/").toURI().getPath()+"../db/study.db";
            jdbc_url = new File(jdbc_url).getCanonicalPath();
            System.out.println("jdbc Url->"+jdbc_url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        C3p0Plugin c3p0Plugin = new C3p0Plugin(
                "jdbc:sqlite:"+jdbc_url,
                getProperty("user"),
                getProperty("password"));
        c3p0Plugin.setDriverClass("org.sqlite.JDBC");

        me.add(c3p0Plugin);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
        arp.setShowSql(true);
        arp.setDialect(new Sqlite3Dialect());
        //映射用户表到 Usermodel管理
        arp.addMapping("user", UserModel.class);

        me.add(arp);

    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }


}
