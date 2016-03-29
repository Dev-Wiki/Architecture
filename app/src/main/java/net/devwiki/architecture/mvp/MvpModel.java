package net.devwiki.architecture.mvp;

import android.content.Context;

import net.devwiki.architecture.common.AppInfo;

import java.util.List;

/**
 * Created by zyz on 2016/3/28.
 */
public interface MvpModel {

    interface OnLoadListener{
        void onComplete(List<AppInfo> list);
    }

    void loadApp(Context context, OnLoadListener listener);
}
