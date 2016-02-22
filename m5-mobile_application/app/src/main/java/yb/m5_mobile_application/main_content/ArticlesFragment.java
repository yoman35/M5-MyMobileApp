package yb.m5_mobile_application.main_content;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yb.m5_mobile_application.R;
import yb.m5_mobile_application.models.Article;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends Fragment {

    private static final int
            layoutId = R.layout.fragment_articles,
            recyclerViewId = R.id.articles_recycler_view;

    public ArticlesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(layoutId, container, false);

        setUpRecyclerView(v);

        return v;
    }

    private void setUpRecyclerView(View v) {
        RecyclerView recyclerView = (RecyclerView) v.findViewById(recyclerViewId);
        int orientation = LinearLayoutManager.VERTICAL;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), orientation, false));
        recyclerView.setAdapter(new ArticlesAdapter(getData()));
    }

    private List<Article> getData() {
        List<Article> articles = new ArrayList<>();

        Article art1 = new Article();
        art1.setTitle("Article1");
        art1.setId("0000");
        art1.setResume("Article 1 parle de ....");
        art1.setImage(ContextCompat.getDrawable(getActivity(), R.drawable.ic_language_black_36dp));
        art1.setIdMachine("01");
        art1.setUrlVignette("http://www.jeuxvideo.com");
        articles.add(art1);
        Article art2 = new Article();
        art2.setTitle("Article1");
        art2.setId("0000");
        art2.setResume("Article 1 parle de ....");
        art2.setImage(ContextCompat.getDrawable(getActivity(), R.drawable.ic_language_black_36dp));
        art2.setIdMachine("01");
        art2.setUrlVignette("http://www.jeuxvideo.com");
        articles.add(art1);

        return articles;
    }

}
