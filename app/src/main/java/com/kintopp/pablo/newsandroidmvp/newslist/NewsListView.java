package com.kintopp.pablo.newsandroidmvp.newslist;

import com.kintopp.pablo.newsandroidmvp.http.apimodel.Article;

public interface NewsListView {

    void showNews(ViewModel viewModel);

    void loadingFailed(String errorMessage);

    void onArticleClicked(Article article); // ToDo create News POJO
}
