package net.devwiki.architecture.mvp2;

import android.os.AsyncTask;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import net.devwiki.architecture.ArchitectureApp;
import net.devwiki.architecture.common.AppInfo;
import net.devwiki.architecture.common.AppUtil;

import java.util.List;

/**
 * App列表的Presenter
 * Created by zyz on 2016/3/31.
 */
public class AppPresenter extends MvpBasePresenter<AppView> {

    public AppPresenter(){
    }

    public void loadApp(){
        if (isViewAttached()) {
            getView().startLoadApp();
        }
        new AsyncTask<Void, Void, List<AppInfo>>(){
            @Override
            protected List<AppInfo> doInBackground(Void... params) {
                return AppUtil.getAppList(ArchitectureApp.getContext());
            }

            @Override
            protected void onPostExecute(List<AppInfo> list) {
                if (isViewAttached()) {
                    getView().showLoadResult(list);
                }
            }
        }.execute();
    }
}
