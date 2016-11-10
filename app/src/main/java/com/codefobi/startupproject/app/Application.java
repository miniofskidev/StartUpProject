package com.codefobi.startupproject.app;

import com.codefobi.startupproject.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by tosantechnolocal on 11/10/2016.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
