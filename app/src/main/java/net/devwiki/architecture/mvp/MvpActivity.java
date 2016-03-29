package net.devwiki.architecture.mvp;

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

public class MvpActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, MvpView{

    @Bind(R.id.app_list)
    RecyclerView appList;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private List<AppInfo> infoList;
    private AppAdapter appAdapter;

    private MvpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);

        infoList = new ArrayList<>();
        appAdapter = new AppAdapter(this, infoList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        appList.setLayoutManager(layoutManager);
        appList.setAdapter(appAdapter);

        refreshLayout.setOnRefreshListener(this);

        presenter = new MvpPresenter(this);
    }

    /**
     * 1.Activity(View)持有Presenter对象
     * 2.Activity(View)接收用户指令,执行下拉刷新操作
     * 3.Activity(View)调用Presenter的loadApp方法
     */


    @Override
    public void onRefresh() {
        presenter.loadApp(this);
    }

    @Override
    public void startLoad() {
        infoList.clear();
        appAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadComplete(List<AppInfo> list) {
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
