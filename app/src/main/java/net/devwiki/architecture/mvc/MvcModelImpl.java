package net.devwiki.architecture.mvc;

import android.content.Context;
import android.os.AsyncTask;

import net.devwiki.architecture.common.AppInfo;
import net.devwiki.architecture.common.AppUtil;

import java.util.List;

/**
 * Created by zyz on 2016/3/25.
 */
public class MvcModelImpl implements MvcModel{

    private static class ModelHolder {
        private static MvcModelImpl helper = new MvcModelImpl();
    }

    public static MvcModelImpl getInstance(){
        return ModelHolder.helper;
    }

    private MvcModelImpl(){

    }

    public void getAppList(final Context context, final OnAppListener listener){
        new AsyncTask<Void, Void, List<AppInfo>>(){

            @Override
            protected List<AppInfo> doInBackground(Void... params) {
                return AppUtil.getAppList(context);
            }

            @Override
            protected void onPostExecute(List<AppInfo> list) {
                if (listener != null){
                    listener.onComplete(list);
                }
            }
        }.execute();
    }
}
