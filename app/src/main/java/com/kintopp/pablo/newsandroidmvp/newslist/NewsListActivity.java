package com.kintopp.pablo.newsandroidmvp.newslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.kintopp.pablo.newsandroidmvp.R;
import com.kintopp.pablo.newsandroidmvp.http.apimodel.Article;
import com.kintopp.pablo.newsandroidmvp.newsdetails.NewsDetailsActivity;
import com.kintopp.pablo.newsandroidmvp.root.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NewsListActivity extends AppCompatActivity implements NewsListView {

    private final String TAG = NewsListActivity.class.getName();

    @BindView(R.id.news_list_activity_root)
    ViewGroup rootView;

    @BindView(R.id.recycler_view_news)
    RecyclerView recyclerView;

    @Inject
    NewsListPresenter presenter;

    private ListAdapter listAdapter;
    private List<ViewModel> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ((App) getApplication()).getComponent().inject(this);

        listAdapter = new ListAdapter(newsList, this);

        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void showNews(ViewModel viewModel) {
        newsList.add(viewModel);
        listAdapter.notifyItemInserted(newsList.size() - 1);
        Log.d(TAG, "News Added: " + viewModel.getTitle());

    }

    @Override
    public void loadingFailed(String errorMessage) {
        Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loadingSuccess(String successMessage) {
        Snackbar.make(rootView, successMessage, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onArticleClicked(int articlePosition) {
        Log.d(TAG, "Article Clicked: " + this.newsList.get(articlePosition).getTitle());

        ViewModel vm = this.newsList.get(articlePosition);
        Article article = new Article();
        article.setTitle(vm.getTitle());
        article.setDescription(vm.getTitle());
        article.setUrlToImage(vm.getImageUrl());


        Intent intent = new Intent(this, NewsDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable("com.kintopp.pablo.newsandroidmvp.article123456", article);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadNewsData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxJavaUnsubscribe();
        newsList.clear();
        listAdapter.notifyDataSetChanged();
    }


}
