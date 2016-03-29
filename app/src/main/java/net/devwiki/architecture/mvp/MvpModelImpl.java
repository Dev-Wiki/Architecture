package net.devwiki.architecture.mvp;

import android.content.Context;
import android.os.AsyncTask;

import net.devwiki.architecture.common.AppInfo;
import net.devwiki.architecture.common.AppUtil;

import java.util.List;


/**
 * Created by zyz on 2016/3/28.
 */
public class MvpModelImpl implements MvpModel {

    @Override
    public void loadApp(final Context context, final OnLoadListener listener) {
        new AsyncTask<Void, Void, List<AppInfo>>(){
            @Override
            protected List<AppInfo> doInBackground(Void... params) {
                List<AppInfo> list = AppUtil.getAppList(context);
                return list;
            }

            @Override
            protected void onPostExecute(List<AppInfo> list) {
                if (listener != null){
                    listener.onComplete(list);
                }
            }
        };
    }
}
