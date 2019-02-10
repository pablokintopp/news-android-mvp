package com.kintopp.pablo.newsandroidmvp.newslist;

public interface NewsListPresenter {

    void loadNewsData();

    void rxJavaUnsubscribe();

    void setView(NewsListView view);
}
