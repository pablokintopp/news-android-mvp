package com.kintopp.pablo.newsandroidmvp.newslist;

import com.kintopp.pablo.newsandroidmvp.http.NewsApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsListModule {

    @Provides
    public NewsListPresenter provideNewsListPresenter(NewsListModel model) {
        return new NewListPresenterImpl(model);
    }

    @Provides
    NewsListModel provideNewsListModel(NewsListRepo repo) {
        return new NewsListModelImpl(repo);
    }

    @Singleton
    @Provides
    NewsListRepo provideNewListRepo(NewsApiService apiService) {
        return new NewsListRepoImpl(apiService);
    }


}
