package yb.m5_mobile_application.menu;

import android.graphics.drawable.Drawable;

@SuppressWarnings("unused")
public class MenuItem {

    private Drawable mLogo;
    private String mTitle;
    private int mCounter;

    public MenuItem() {
        mLogo = null;
        mTitle = "";
        mCounter = 0;
    }

    public MenuItem(Drawable logo, String title) {
        mLogo = logo;
        mTitle = title;
        mCounter = 0;
    }

    public int getCounter() {
        return mCounter;
    }

    public void setCounter(int mCounter) {
        this.mCounter = mCounter;
    }

    public Drawable getLogo() {
        return mLogo;
    }

    public void setLogo(Drawable mLogo) {
        this.mLogo = mLogo;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

}
