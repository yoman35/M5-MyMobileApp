package yb.m5_mobile_application.main_content;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import yb.m5_mobile_application.R;
import yb.m5_mobile_application.models.Article;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends Fragment implements View.OnTouchListener {

    private static final int
            layoutId = R.layout.fragment_articles,
            rootLayoutId = R.id.articles_root_layout;

    private RelativeLayout mLayout;
    private GestureDetectorCompat mDetector;
    private boolean first_time;
    private View mView;
    private List<Article> mArticles;

    public ArticlesFragment() {
        first_time = true;
        mArticles = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(layoutId, container, false);
        setUpLayout();

        if (first_time) {
            requestArticles();
            first_time = false;
        } else {
            displayArticles();
        }

        return mView;
    }

    private void setUpLayout() {
        mLayout = (RelativeLayout) mView.findViewById(rootLayoutId);
        mDetector = new GestureDetectorCompat(getActivity(), new MyGestureDetector(mLayout));
        mView.setOnTouchListener(this);
    }

    private void requestArticles() {

        for (int i = 0; i < 10; ++i) {
            mArticles.add(getDefaultArticle());
        }
        displayArticles();
    }

    private void displayArticles() {
        int i = 0;
        for (Article ignored : mArticles) {
            mLayout.addView(createArticlePreviewLayout(i));
            ++i;
        }
    }

    private RelativeLayout createArticlePreviewLayout(int pos) {
        return new ArticlePreviewLayout(getActivity(), mArticles, pos);
    }

    private Article getDefaultArticle() {
        Article a = new Article();
        a.setTitle("Title Lorem ipsum title!");
        a.setId("00000000");
        a.setResume("Lorem ipsum dolor sit amet, consectetur" +
                " adipiscing elit, sed do eiusmod tempor incididunt ut labore " +
                "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud " +
                "exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat....");
        a.setDate("2016-03-10");
        a.setImage(ContextCompat.getDrawable(getActivity(), R.drawable.test_image));
        return a;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_CANCEL:
                return true;
            case MotionEvent.ACTION_OUTSIDE:
                return false;
            case MotionEvent.ACTION_UP:
                return true;
            default:
                return true;
        }
    }

    private class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

        private View mView;

        public MyGestureDetector(View v) {
            mView = v;
        }

        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float e1x = e1.getX(0);
            float e2x = e2.getX(0);
            float diffX = e2x - e1x;
            float e1y = e1.getY(0);
            float e2y = e2.getY(0);
            float diffY = e2y - e1y;
            if (Math.abs(diffX) >= Math.abs(diffY) && diffX > 0) {
                getLastPreview();
            }
            return true;
        }

    }

    private void getLastPreview() {
        if (mArticles.size() > 0) {
            ArticlePreviewLayout previous = new ArticlePreviewLayout(getActivity(), mArticles, 0);
            mLayout.addView(previous);
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.from_left);
            previous.startAnimation(animation);
        }
    }

}
