package com.kintopp.pablo.newsandroidmvp.newsdetails;

import com.kintopp.pablo.newsandroidmvp.http.apimodel.Article;

public interface NewsDetailsPresenter {

    void showDetails(Article article);

    void setView(NewsDetailsView view);

    void destroy();
}
