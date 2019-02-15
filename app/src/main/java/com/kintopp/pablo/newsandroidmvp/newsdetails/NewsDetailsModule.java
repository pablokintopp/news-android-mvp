package com.kintopp.pablo.newsandroidmvp.newsdetails;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsDetailsModule {

    @Provides
    public NewsDetailsPresenter provideNewsDetailsPresenter(){
        return new NewsDetailsPresenterImpl();
    }
}
