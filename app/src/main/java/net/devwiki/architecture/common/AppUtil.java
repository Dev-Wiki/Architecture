package net.devwiki.architecture.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zyz on 2016/3/22.
 */
public class AppUtil {

    /**
     * 获取已安装的APP的列表
     * @param context 上下文
     * @return AppInfo列表
     */
    public static List<AppInfo> getAppList(Context context){
        List<AppInfo> appInfoList = new ArrayList<>();
        List<PackageInfo> packages = context.getPackageManager()
                .getInstalledPackages(PackageManager.GET_ACTIVITIES);
        for (PackageInfo packageInfo : packages) {
            AppInfo appInfo = new AppInfo();
            appInfo.setName(packageInfo.applicationInfo
                    .loadLabel(context.getPackageManager())
                    .toString());
            appInfo.setIcon(packageInfo.applicationInfo
                    .loadIcon(context.getPackageManager()));
            appInfo.setInstallTime(getFormatTime(packageInfo.firstInstallTime));
            appInfo.setVersionCode(packageInfo.versionCode);
            appInfo.setVersionName(packageInfo.versionName);
            appInfoList.add(appInfo);
        }
        return appInfoList;
    }

    public static String getFormatTime(long time){
        if (time <= 0){
            return "";
        }
        return SimpleDateFormat.getDateInstance(DateFormat.FULL).format(new Date(time));
    }
}
