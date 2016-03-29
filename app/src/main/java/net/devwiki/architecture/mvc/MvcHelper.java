package net.devwiki.architecture.mvc;

import android.content.Context;

import net.devwiki.architecture.common.AppInfo;
import net.devwiki.architecture.common.AppUtil;
import net.devwiki.architecture.mvp.Presenter;

import java.util.List;

/**
 * Created by zyz on 2016/3/25.
 */
public class MvcHelper implements Presenter {


    @Override
    public void loadApps(final Context context, final OnLoadAppListener listener) {
        if (listener != null){
            listener.onStart();
        }
        new Thread(){
            @Override
            public void run() {
                List<AppInfo> list = AppUtil.getAppList(context);
                if (listener != null){
                    listener.onComplete(list);
                }
            }
        }.start();
    }
}
