package com.kintopp.pablo.newsandroidmvp.newslist;

import io.reactivex.Observable;

public class NewsListModelImpl implements NewsListModel {

    private NewsListRepo repo;

    public NewsListModelImpl(NewsListRepo repo) {
        this.repo = repo;
    }



    @Override
    public Observable<ViewModel> result() {
        return repo.getArticleData().map(article -> new ViewModel(article.getTitle(), article.getUrlToImage()));
    }
}
