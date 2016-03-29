package net.devwiki.architecture.mvc;

import android.content.Context;
import android.os.AsyncTask;

import net.devwiki.architecture.common.AppInfo;
import net.devwiki.architecture.common.AppUtil;

import java.util.List;

/**
 * Created by zyz on 2016/3/25.
 */
public class MvcModel {

    private static class ModelHolder {
        private static MvcModel helper = new MvcModel();
    }

    public static MvcModel getInstance(){
        return ModelHolder.helper;
    }

    private MvcModel(){

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
