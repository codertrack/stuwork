package com.stuwork.app.config;

import com.jfinal.config.Routes;
import com.stuwork.app.controller.AdminController;
import com.stuwork.app.controller.UserController;

/**
 * Created by wukai on 15/11/18.
 */
public class TeacherRouter extends Routes {

    public  void config(){
        add("/user", UserController.class);
        add("/admin", AdminController.class);

    }


}
