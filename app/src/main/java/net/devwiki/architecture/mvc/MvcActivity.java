package net.devwiki.architecture.mvc;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.devwiki.architecture.ArchitectureApp;
import net.devwiki.architecture.R;
import net.devwiki.architecture.common.AppAdapter;
import net.devwiki.architecture.common.AppInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MvcActivity extends AppCompatActivity {

    @Bind(R.id.app_list)
    RecyclerView appList;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private List<AppInfo> infoList;
    private AppAdapter appAdapter;

    private MvcModelImpl mvcModel;

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
                loadApp();
            }
        });

        mvcModel = MvcModelImpl.getInstance();
    }

    /**
     * 1.Activity(Controller)持有appHelper(Model)模型对象
     * 2.用户操作SwipeRefreshLayout(View),下拉刷新
     * 3.Activity(Controller)向appHelper(Model)发起请求,即调用getAppList()方法.
     * 4.appHelper(Model)通过接口OnAppListener通知RecyclerView(View)更新界面
     */

    private void loadApp(){
        infoList.clear();
        appAdapter.notifyDataSetChanged();
        mvcModel.getAppList(ArchitectureApp.getContext(), new MvcModel.OnAppListener() {
            @Override
            public void onComplete(List<AppInfo> list) {
                displayResult(list);
            }
        });
    }

    private void displayResult(List<AppInfo> list){
        refreshLayout.setRefreshing(false);
        if (list != null){
            infoList.addAll(list);
            appAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
