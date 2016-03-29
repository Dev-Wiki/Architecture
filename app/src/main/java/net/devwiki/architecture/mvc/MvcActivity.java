package net.devwiki.architecture.mvc;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.devwiki.architecture.R;
import net.devwiki.architecture.common.AppAdapter;
import net.devwiki.architecture.common.AppInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MvcActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, OnAppListener{

    @Bind(R.id.app_list)
    RecyclerView appList;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private List<AppInfo> infoList;
    private AppAdapter appAdapter;

    private MvcModel mvcModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        ButterKnife.bind(this);

        infoList = new ArrayList<>();
        appAdapter = new AppAdapter(this, infoList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        appList.setLayoutManager(layoutManager);
        appList.setAdapter(appAdapter);

        refreshLayout.setOnRefreshListener(this);

        mvcModel = MvcModel.getInstance();
    }

    /**
     * 1.Activity(Controller)持有appHelper(Model)模型对象
     * 2.用户操作SwipeRefreshLayout(View),下拉刷新
     * 3.Activity(Controller)向appHelper(Model)发起请求,即调用getAppList()方法.
     * 4.appHelper(Model)通过接口OnAppListener通知RecyclerView(View)更新界面
     */
    @Override
    public void onRefresh() {
        infoList.clear();
        appAdapter.notifyDataSetChanged();
        mvcModel.getAppList(this, this);
    }

    @Override
    public void onComplete(List<AppInfo> list) {
        displayResult(list);
    }

    private void displayResult(List<AppInfo> list){
        if (list != null){
            infoList.addAll(list);
            appAdapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        }
    }
}
