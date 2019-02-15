package com.kintopp.pablo.newsandroidmvp.newsdetails;

import com.kintopp.pablo.newsandroidmvp.http.apimodel.Article;

public class NewsDetailsPresenterImpl implements NewsDetailsPresenter {

    private NewsDetailsView view;

    public NewsDetailsPresenterImpl() {
    }

    @Override
    public void showDetails(Article article) {

        if(view != null){
            view.showDetails(article);
        }

    }

    @Override
    public void setView(NewsDetailsView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        view = null;
    }
}
