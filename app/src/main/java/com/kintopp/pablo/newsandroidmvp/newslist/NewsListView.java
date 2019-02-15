package com.kintopp.pablo.newsandroidmvp.newslist;

import com.kintopp.pablo.newsandroidmvp.http.apimodel.Article;

public interface NewsListView {

    void showNews(Article article);

    void loadingFailed(String errorMessage);

    void loadingSuccess(String successMessage);

    void onArticleClicked(Article article);
}
