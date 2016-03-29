package net.devwiki.architecture.mvc;

import android.content.Context;

import net.devwiki.architecture.common.AppInfo;

import java.util.List;

/**
 * Created by zyz on 2016/3/29.
 */
public interface MvcModel {
    /**
     * Created by zyz on 2016/3/25.
     */
    interface OnAppListener {

        void onComplete(List<AppInfo> list);
    }

    void getAppList(final Context context, final MvcModel.OnAppListener listener);
}
