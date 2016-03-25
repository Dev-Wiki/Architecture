package net.devwiki.architecture.common;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by zyz on 2016/3/25.
 */
public class AppHelper {

    private static class HelperHolder{
        private static AppHelper helper = new AppHelper();
    }

    public static AppHelper getInstance(){
        return HelperHolder.helper;
    }

    private AppHelper(){

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
        };
    }
}
