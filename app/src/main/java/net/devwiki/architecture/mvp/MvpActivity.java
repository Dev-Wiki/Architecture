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

        infoList = new ArrayList<>();
        appAdapter = new AppAdapter(this, infoList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        appList.setLayoutManager(layoutManager);
        appList.setAdapter(appAdapter);

        refreshLayout.setOnRefreshListener(this);

        presenter = new MvpPresenter(this);
    }

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
        infoList.addAll(list);
        appAdapter.notifyDataSetChanged();
    }
}
