package net.devwiki.architecture.mvp;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.devwiki.architecture.R;
import net.devwiki.architecture.common.AppAdapter;
import net.devwiki.architecture.common.AppInfo;
import net.devwiki.architecture.mvc.MvcActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MvpActivity extends AppCompatActivity implements MvpView{

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

        /**
         * 1.Activity(View)持有Presenter对象
         * 2.Activity(View)接收用户指令,执行下拉刷新操作
         * 3.Activity(View)调用Presenter的{@link MvpPresenter#loadApp(Context)}方法
         */
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadApp(MvpActivity.this);
            }
        });

        presenter = new MvpPresenter(this);
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
