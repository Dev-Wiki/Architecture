package net.devwiki.architecture.mvp;

import android.content.Context;

import net.devwiki.architecture.common.AppInfo;

import java.util.List;

/**
 * Created by zyz on 2016/3/26.
 */
public interface Presenter {

    interface OnLoadAppListener{

        void onStart();

        void onComplete(List<AppInfo> list);
    }

    void loadApps(Context context, OnLoadAppListener listener);
}
