package com.stuwork.app.config;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.server.ServerFactory;
import com.sun.codemodel.internal.JFieldVar;

/**
 * Created by wukai on 15/12/2.
 */
public class AppConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants cons) {
        cons.setDevMode(true);

    }

    @Override
    public void configRoute(Routes me) {

    }

    @Override
    public void configPlugin(Plugins me) {

    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }


}
