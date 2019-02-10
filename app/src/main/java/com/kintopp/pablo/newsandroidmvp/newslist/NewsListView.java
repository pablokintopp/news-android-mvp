package com.kintopp.pablo.newsandroidmvp.newslist;

public interface NewsListView {

    void showNews(ViewModel viewModel);

    void loadingFailed(String errorMessage);

    void onNewsClicked(News news); // ToDo create News POJO
}
