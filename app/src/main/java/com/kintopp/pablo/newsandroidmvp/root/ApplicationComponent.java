package com.kintopp.pablo.newsandroidmvp.root;

import com.kintopp.pablo.newsandroidmvp.MainActivity;
import com.kintopp.pablo.newsandroidmvp.http.NewsApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NewsApiModule.class})
public interface ApplicationComponent {

    void inject(MainActivity target);
}
