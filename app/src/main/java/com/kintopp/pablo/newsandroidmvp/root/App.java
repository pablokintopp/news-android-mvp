package com.kintopp.pablo.newsandroidmvp.root;

import android.app.Application;

import com.kintopp.pablo.newsandroidmvp.http.NewsApiModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .newsApiModule(new NewsApiModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
