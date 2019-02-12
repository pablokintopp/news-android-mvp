package com.kintopp.pablo.newsandroidmvp.root;

import android.app.Application;

import com.kintopp.pablo.newsandroidmvp.http.NewsApiModule;
import com.kintopp.pablo.newsandroidmvp.newslist.NewsListModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .newsApiModule(new NewsApiModule())
                .newsListModule(new NewsListModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
