package yb.m5_mobile_application.models;

import android.graphics.drawable.Drawable;

public class Article {

    private String id;
    private String title;
    private String resume;
    private String date;
    private Drawable image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Article: \'" + id
                + "\', title=\'" + title
                + "\', date=\'" + date
                + "\', resume=\'" + resume
                + "\', image=\'" + image.toString();
    }
}
