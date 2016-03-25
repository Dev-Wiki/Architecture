package net.devwiki.architecture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.devwiki.architecture.mvc.MvcActivity;
import net.devwiki.architecture.mvp.MvpActivity;
import net.devwiki.architecture.mvvm.MvvmActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.mvc, R.id.mvp, R.id.mvvm})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.mvc:
                intent = new Intent(MainActivity.this, MvcActivity.class);
                startActivity(intent);
                break;
            case R.id.mvp:
                intent = new Intent(MainActivity.this, MvpActivity.class);
                startActivity(intent);
                break;
            case R.id.mvvm:
                intent = new Intent(MainActivity.this, MvvmActivity.class);
                startActivity(intent);
                break;
        }
    }
}