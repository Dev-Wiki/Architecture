package net.devwiki.architecture.mvp2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import net.devwiki.architecture.R;
import net.devwiki.architecture.common.AppAdapter;
import net.devwiki.architecture.common.AppInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 使用第三方的MVP实现
 * Created by zyz on 2016/3/31.
 */
public class AppActivity extends MvpActivity<AppView, AppPresenter> implements AppView{

    @Bind(R.id.app_list)
    RecyclerView appList;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private List<AppInfo> infoList;
    private AppAdapter appAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        ButterKnife.bind(this);

        infoList = new ArrayList<>();
        appAdapter = new AppAdapter(this, infoList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        appList.setLayoutManager(layoutManager);
        appList.setAdapter(appAdapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadApp();
            }
        });
    }

    @Override
    public void startLoadApp() {
        infoList.clear();
        appAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadResult(List<AppInfo> list) {
        infoList.clear();
        infoList.addAll(list);
        appAdapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @NonNull
    @Override
    public AppPresenter createPresenter() {
        return new AppPresenter();
    }

}
