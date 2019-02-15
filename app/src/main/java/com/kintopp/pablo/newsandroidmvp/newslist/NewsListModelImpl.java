package com.kintopp.pablo.newsandroidmvp.newslist;

import com.kintopp.pablo.newsandroidmvp.http.apimodel.Article;

import io.reactivex.Observable;

public class NewsListModelImpl implements NewsListModel {

    private NewsListRepo repo;

    public NewsListModelImpl(NewsListRepo repo) {
        this.repo = repo;
    }



    @Override
    public Observable<Article> result() {
        return repo.getArticleData();
    }
}
