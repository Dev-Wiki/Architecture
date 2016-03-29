package net.devwiki.architecture.mvp;

import net.devwiki.architecture.common.AppInfo;

import java.util.List;

/**
 * Created by zyz on 2016/3/26.
 */
public interface MvpView {

    void startLoad();

    void loadComplete(List<AppInfo> list);
}
