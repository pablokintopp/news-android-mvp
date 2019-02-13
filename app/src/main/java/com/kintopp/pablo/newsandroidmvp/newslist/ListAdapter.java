package com.kintopp.pablo.newsandroidmvp.newslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kintopp.pablo.newsandroidmvp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemViewHolder> {

    private List<ViewModel> list;
    private Context context;
    private NewsListView newsListView;

    public ListAdapter(List<ViewModel> list, NewsListView newsListView) {
        this.list = list;
        this.newsListView = newsListView;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false);
        this.context = parent.getContext();
        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        holder.newsTitle.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImageUrl()).into(holder.newsImage);
        holder.viewPosition = position;
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public  class ListItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view_news)
        public ImageView newsImage;

        @BindView(R.id.text_view_news_title)
        public TextView newsTitle;

        public int viewPosition;

        public ListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListAdapter.this.newsListView.onArticleClicked(viewPosition);
                }
            });
        }


    }
}
