package net.devwiki.architecture.mvp2;

import com.hannesdorfmann.mosby.mvp.MvpView;

import net.devwiki.architecture.common.AppInfo;

import java.util.List;

/**
 * App列表的View接口
 * Created by zyz on 2016/3/31.
 */
public interface AppView extends MvpView {

    void startLoadApp();

    void showLoadResult(List<AppInfo> list);
}
