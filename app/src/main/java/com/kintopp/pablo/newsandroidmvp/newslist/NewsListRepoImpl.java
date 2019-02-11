package com.kintopp.pablo.newsandroidmvp.newslist;

import com.kintopp.pablo.newsandroidmvp.http.NewsApiService;
import com.kintopp.pablo.newsandroidmvp.http.apimodel.Article;
import com.kintopp.pablo.newsandroidmvp.http.apimodel.NewsApiResult;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class NewsListRepoImpl implements NewsListRepo {

    private NewsApiService newsApiService;
    private List<Article> articles;
    private long lastTimestamp;

    private static final long CACHE_LIFETIME = 20 * 1000; //20s

    public NewsListRepoImpl(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
        this.articles = new ArrayList<>();
        this.lastTimestamp = System.currentTimeMillis();
    }

    public boolean isUpdated() {
        return (System.currentTimeMillis() - lastTimestamp) < CACHE_LIFETIME;
    }

    @Override
    public Observable<Article> getArticleFromNetwork() {
        Observable<NewsApiResult> topHeadlinesObservable = newsApiService.getTopHeadlines("en", 1);

        return topHeadlinesObservable
                .concatMap(topHeadlines -> Observable.fromIterable(topHeadlines.getArticles()))
                .doOnNext(article -> articles.add(article));
    }

    @Override
    public Observable<Article> getArticleFromCache() {
        if (isUpdated()) {
            return Observable.fromIterable(articles);
        } else {
            lastTimestamp = System.currentTimeMillis();
            articles.clear();
            return Observable.empty();
        }

    }

    @Override
    public Observable<Article> getArticleData() {
        return getArticleFromCache().switchIfEmpty(getArticleFromNetwork());
    }
}
