package yb.m5_mobile_application.main_content;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yb.m5_mobile_application.R;
import yb.m5_mobile_application.models.Article;


public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder> {

    private static final int
            layoutId = R.layout.article_item;

    private List<Article> mArticles = new ArrayList<>();

    public ArticlesAdapter(List<Article> data) {
        mArticles = data;
    }

    @Override
    public ArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new ArticlesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ArticlesViewHolder holder, int position) {
        Article current = mArticles.get(position);
        holder.title.setText(current.getTitle());
        holder.date.setText(current.getDate());
        holder.resume.setText(current.getResume());
        holder.image.setImageDrawable(current.getImage());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public class ArticlesViewHolder extends RecyclerView.ViewHolder {

        private final int
                titleId = R.id.article_item_title,
                resumeId = R.id.article_item_resume,
                dateId = R.id.article_item_date,
                imageId = R.id.article_item_image;
        TextView title, resume, date;
        ImageView image;
        public ArticlesViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(titleId);
            resume = (TextView) itemView.findViewById(resumeId);
            date = (TextView) itemView.findViewById(dateId);
            image = (ImageView) itemView.findViewById(imageId);
        }
    }
}
