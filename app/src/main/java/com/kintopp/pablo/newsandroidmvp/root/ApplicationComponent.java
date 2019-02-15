package com.kintopp.pablo.newsandroidmvp.root;

import com.kintopp.pablo.newsandroidmvp.MainActivity;
import com.kintopp.pablo.newsandroidmvp.http.NewsApiModule;
import com.kintopp.pablo.newsandroidmvp.newsdetails.NewsDetailsActivity;
import com.kintopp.pablo.newsandroidmvp.newsdetails.NewsDetailsModule;
import com.kintopp.pablo.newsandroidmvp.newslist.NewsListModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NewsApiModule.class, NewsListModule.class, NewsDetailsModule.class})
public interface ApplicationComponent {

    void inject(MainActivity target);

    void inject(NewsDetailsActivity newsDetailsActivity);
}
