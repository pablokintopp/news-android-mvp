package com.kintopp.pablo.newsandroidmvp.newslist;

import com.kintopp.pablo.newsandroidmvp.http.apimodel.Article;

import io.reactivex.Observable;

public interface NewsListRepo {

    Observable<Article> getArticleFromNetwork();
    Observable<Article> getArticleFromCache();
    Observable<Article> getArticleData();
}
