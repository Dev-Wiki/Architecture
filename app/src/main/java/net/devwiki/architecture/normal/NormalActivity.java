package net.devwiki.architecture.normal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.devwiki.architecture.ArchitectureApp;
import net.devwiki.architecture.R;
import net.devwiki.architecture.common.AppAdapter;
import net.devwiki.architecture.common.AppInfo;
import net.devwiki.architecture.common.AppUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NormalActivity extends AppCompatActivity {

    @Bind(R.id.app_list)
    RecyclerView appList;
    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private List<AppInfo> infoList;
    private AppAdapter appAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
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
    }

    private void loadApp(){
        refreshLayout.setRefreshing(true);
        infoList.clear();
        appAdapter.notifyDataSetChanged();
        new AsyncTask<Void, Void, List<AppInfo>>(){
            @Override
            protected List<AppInfo> doInBackground(Void... params) {
                return AppUtil.getAppList(ArchitectureApp.getContext());
            }

            @Override
            protected void onPostExecute(List<AppInfo> appInfos) {
                infoList.addAll(appInfos);
                appAdapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        }.execute();
    }
}
