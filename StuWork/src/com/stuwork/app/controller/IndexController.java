package com.stuwork.app.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.stuwork.app.config.AdminRouter;

import java.net.URISyntaxException;

/**
 * Created by wukai on 15/11/18.
 *
 */
public class IndexController extends Controller {

    public void index() throws URISyntaxException {
        renderText("hello");
    }

}
