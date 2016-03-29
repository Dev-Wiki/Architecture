package net.devwiki.architecture.mvp;

import android.content.Context;

import net.devwiki.architecture.common.AppInfo;

import java.util.List;

/**
 * Created by zyz on 2016/3/26.
 */
public class MvpPresenter {

    private MvpView mvpView;
    private MvpModel mvpModule;

    public MvpPresenter(MvpView mvpView){
        this.mvpView = mvpView;
        mvpModule = MvpModelImpl.getInstance();
    }

    public void loadApp(Context context){
        mvpView.startLoad();
        mvpModule.loadApp(context, new MvpModel.OnLoadListener() {
            @Override
            public void onComplete(List<AppInfo> list) {
                mvpView.loadComplete(list);
            }
        });
    }

}
