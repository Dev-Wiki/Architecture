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

    /**
     * 跳到{@link MvpActivity#onRefresh()}
     * 4.Presenter调用View(Activity实现MvpView)的方法startLoad(),开始加在动画
     * 5.Presenter调用Model的loadApp方法,开始获取数据
     * 6.Model获取完数据回调给Presenter
     * 7.Presenter调用View(Activity实现MvpView)的方法onComplete()更新界面
     * @param context
     */
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
