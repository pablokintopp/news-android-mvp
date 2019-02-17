package com.kintopp.pablo.newsandroidmvp.newsdetails;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.kintopp.pablo.newsandroidmvp.R;
import com.kintopp.pablo.newsandroidmvp.http.apimodel.Article;
import com.kintopp.pablo.newsandroidmvp.root.App;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @BindView(R.id.news_date)
    TextView date;
    @BindView(R.id.news_source)
    TextView source;
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
            if (extras != null && extras.containsKey("com.kintopp.pablo.newsandroidmvp.article")) {
                Article article = extras.getParcelable("com.kintopp.pablo.newsandroidmvp.article");
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
        description.setText(article.getContent());
        source.setText(article.getSource().getName());
        date.setText(getFormatedDate(article.getPublishedAt()));

    }

    private String getFormatedDate(String date){
        if(date != null)
            date = date.substring(0,"yyyy-MM-ddTHH:mm:ss".length());

        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy hh:mm");
        String dateFormated = "";
        try {
            Date result = df1.parse(date);
            dateFormated = formatter.format(result);
        } catch (ParseException e) {
            e.printStackTrace();        }

        return dateFormated;
    }
}
