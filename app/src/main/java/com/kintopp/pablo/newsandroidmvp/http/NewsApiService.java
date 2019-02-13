package com.kintopp.pablo.newsandroidmvp.http;

import com.kintopp.pablo.newsandroidmvp.http.apimodel.NewsApiResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("top-headlines")
    Observable<NewsApiResult> getTopHeadlines(@Query("language") String language, @Query("page") Integer page );
}
