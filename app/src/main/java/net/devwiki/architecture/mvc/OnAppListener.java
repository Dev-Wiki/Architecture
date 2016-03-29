package net.devwiki.architecture.mvc;

import net.devwiki.architecture.common.AppInfo;

import java.util.List;

/**
 * Created by zyz on 2016/3/25.
 */
public interface OnAppListener {

    void onComplete(List<AppInfo> list);
}
