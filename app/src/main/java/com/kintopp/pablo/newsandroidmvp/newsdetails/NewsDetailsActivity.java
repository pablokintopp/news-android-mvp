package com.kintopp.pablo.newsandroidmvp.newsdetails;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.kintopp.pablo.newsandroidmvp.R;
import com.kintopp.pablo.newsandroidmvp.http.apimodel.Article;
import com.kintopp.pablo.newsandroidmvp.root.App;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailsActivity extends AppCompatActivity implements NewsDetailsView {

    private final String TAG = NewsDetailsActivity.class.getName();

    @BindView(R.id.news_image)
    ImageView image;
    @BindView(R.id.news_title)
    TextView title;
    @BindView(R.id.news_description)
    TextView description;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar)
    @Nullable
    Toolbar toolbar;

    private Article article;

    @Inject
    NewsDetailsPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_details);

        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);
        setToolbar();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.containsKey("com.kintopp.pablo.newsandroidmvp.article123456")) {
                Article article = extras.getParcelable("com.kintopp.pablo.newsandroidmvp.article123456");
                if (article != null) {
                    presenter.setView(this);
                    presenter.showDetails(article);
                }
            }
        }
    }

    private void setToolbar() {
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        collapsingToolbar.setTitle(getString(R.string.news_detail_title));
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsingToolbar.setTitleEnabled(true);

        if (toolbar != null) {
            this.setSupportActionBar(toolbar);


            ActionBar actionBar = this.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        } else {
            // landscape
        }
    }

    @Override
    public void showDetails(Article article) {
        Glide.with(this).load(article.getUrlToImage()).into(image);
        title.setText(article.getTitle());
        description.setText(article.getDescription());

    }
}
