package com.stuwork.app.config;

import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.stuwork.app.controller.AdminController;

/**
 * Created by wukai on 15/12/14.
 */
public class AdminRouter extends Routes {

    @Override
    public void config() {

        add("/admin", AdminController.class);

    }

}
