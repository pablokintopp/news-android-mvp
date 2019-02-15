package com.kintopp.pablo.newsandroidmvp.newslist;

import com.kintopp.pablo.newsandroidmvp.http.apimodel.Article;

import io.reactivex.Observable;

public interface NewsListModel {

    Observable<Article> result();
}
