package com.kintopp.pablo.newsandroidmvp.newslist;

import io.reactivex.Observable;

public interface NewsListModel {

    Observable<ViewModel> result();
}
