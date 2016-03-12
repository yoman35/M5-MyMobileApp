package yb.m5_mobile_application.main_content;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import yb.m5_mobile_application.R;
import yb.m5_mobile_application.models.Article;
import yb.m5_mobile_application.utils.MyApp;

@SuppressLint("ViewConstructor")
public class ArticlePreviewLayout extends RelativeLayout implements View.OnTouchListener {

    private static final int
            favId = R.drawable.ic_favorite_red_48dp,
            wrap_content = ViewGroup.LayoutParams.WRAP_CONTENT,
            match_parent = ViewGroup.LayoutParams.MATCH_PARENT,
            parentLayoutId = R.id.articles_root_layout;

    private Context mContext;
    private List<Article> mArticles;
    private int mPos;
    private GestureDetectorCompat mDetector;

    public ArticlePreviewLayout(Context context, List<Article> articles, int pos) {
        super(context);

        mContext = context;
        mArticles = articles;
        mPos = pos;

        setUpLayout();

        mDetector = new GestureDetectorCompat(context, new MyGestureDetector(this));
        setOnTouchListener(this);
    }

    private void setUpLayout() {
        setLayoutParams(new RelativeLayout.LayoutParams(match_parent, match_parent));
        setPadding(0, 0, 0, 0);

        Article current = mArticles.get(mPos);
        setUpBackground(current.getImage());
        setUpContainer(current);
    }

    private void setUpBackground(Drawable background) {
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(match_parent, match_parent));
        imageView.setImageDrawable(background);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(imageView);
    }

    private void setUpContainer(Article current) {
        RelativeLayout container = new RelativeLayout(mContext);
        RelativeLayout.LayoutParams params = new LayoutParams(match_parent, match_parent);
        int marginV = dpToPx(60);
        int marginH = dpToPx(40);
        params.setMargins(marginH, marginV, marginH, marginV);
        container.setLayoutParams(params);
        int padding = dpToPx(16);
        container.setAlpha(0.8f);
        container.setPadding(padding, padding, padding, padding);
        container.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.white));
        container.addView(createTitleTextView(current.getTitle()));
        container.addView(createDateTextView(current.getDate()));
        container.addView(createResumeTextView(current.getResume()));
        addView(container);
    }

    private TextView createTitleTextView(String title) {
        TextView textView = new TextView(mContext);
        textView.setId(R.id.title_article);
        RelativeLayout.LayoutParams params = new LayoutParams(match_parent, wrap_content);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
        textView.setText(title);
        return textView;
    }

    private TextView createDateTextView(String date) {
        TextView textView = new TextView(mContext);
        textView.setId(R.id.date_article);
        RelativeLayout.LayoutParams params = new LayoutParams(match_parent, wrap_content);
        params.addRule(RelativeLayout.BELOW, R.id.title_article);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTypeface(null, Typeface.ITALIC);
        textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextSecondary));
        textView.setText(date);
        return textView;
    }

    private TextView createResumeTextView(String resume) {
        TextView textView = new TextView(mContext);
        textView.setId(R.id.resume_article);
        RelativeLayout.LayoutParams params = new LayoutParams(match_parent, match_parent);
        params.setMargins(0, dpToPx(24), 0, 0);
        params.addRule(RelativeLayout.BELOW, R.id.date_article);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextPrimary));
        textView.setText(resume);
        return textView;
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

    private void favoriteAnimation() {
        int transparent = ContextCompat.getColor(mContext, android.R.color.transparent);
        int pxPadding = dpToPx(16);

        final ImageView favImage = new ImageView(mContext);
        favImage.setLayoutParams(new ViewGroup.LayoutParams(wrap_content, wrap_content));
        favImage.setImageDrawable(ContextCompat.getDrawable(mContext, favId));

        final RelativeLayout relativeLayout = new RelativeLayout(mContext);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(match_parent, match_parent));
        relativeLayout.setPadding(pxPadding, pxPadding, pxPadding, pxPadding);
        relativeLayout.setGravity(Gravity.END | Gravity.TOP);
        relativeLayout.setBackgroundColor(transparent);
        relativeLayout.addView(favImage);

        addViewToRootLayout(relativeLayout);

        int anim = R.anim.favorite;
        final Animation animation = AnimationUtils.loadAnimation(mContext, anim);
        favImage.startAnimation(animation);
        dismissMe();
    }

    private RelativeLayout getRootLayout() {
        ViewGroup parent = (ViewGroup) getParent();
        return (RelativeLayout) parent.findViewById(parentLayoutId);
    }

    private void addViewToRootLayout(View v) {
        getRootLayout().addView(v);
    }

    private void dismissMe() {
        getRootLayout().removeView(this);
    }

    private int dpToPx(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private void getPrevious() {
        if (mPos >= mArticles.size() - 1) {
            return;
        }
        ArticlePreviewLayout previous = new ArticlePreviewLayout(mContext, mArticles, mPos + 1);
        addViewToRootLayout(previous);
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.from_left);
        previous.startAnimation(animation);
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
            if (Math.abs(diffX) >= Math.abs(diffY)) {
                if (diffX > 0) {
                    // Fling right
                    getPrevious();
                } else {
                    // Fling left
                    flingTo(R.anim.to_left);
                    dismissMe();
                }
            } else {
                if (diffY < 0) {
                    // Fling top
                    flingTo(R.anim.to_favorite);
                    favoriteAnimation();
                }
            }
            return true;
        }

        private void flingTo(int file) {
            Animation animation = AnimationUtils.loadAnimation(mContext, file);
            mView.startAnimation(animation);
        }

    }

}