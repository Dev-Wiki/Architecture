package net.devwiki.architecture;

import android.app.Application;
import android.content.Context;

/**
 * Created by Asia on 2016/3/31 0031.
 */
public class ArchitectureApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
