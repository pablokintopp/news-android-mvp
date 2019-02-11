package com.kintopp.pablo.newsandroidmvp.newslist;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NewListPresenterImpl implements NewsListPresenter {

    private NewsListView view;
    private NewsListModel model;

    private Disposable subscription = null;

    public NewListPresenterImpl(NewsListModel model) {
        this.model = model;
    }

    @Override
    public void loadNewsData() {

        subscription = model.result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ViewModel>() {
                    @Override
                    public void onNext(ViewModel viewModel) {
                        if (view != null)
                            view.showNews(viewModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (view != null)
                            view.loadingFailed("Error downloading news");

                    }

                    @Override
                    public void onComplete() {
                        if (view != null)
                            view.loadingSuccess("Data downloaded with success!");

                    }
                });

    }

    @Override
    public void rxJavaUnsubscribe() {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }

    @Override
    public void setView(NewsListView view) {
        this.view = view;

    }
}
