package yb.m5_mobile_application.models;

import android.graphics.drawable.Drawable;

public class Article {

    private String id;
    private String title;
    private String resume;
    private String date;
    private String idMachine;
    private String urlVignette;
    private String urlVignetteBig;
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

    public String getIdMachine() {
        return idMachine;
    }

    public void setIdMachine(String id) {
        this.idMachine = id;
    }

    public String getUrlVignette() {
        return urlVignette;
    }

    public void setUrlVignette(String url) {
        this.urlVignette = url;
    }

    public String getUrlVignetteBig() {
        return urlVignetteBig;
    }

    public void setUrlVignetteBig(String url) {
        this.urlVignetteBig = url;
    }

    public Drawable getImage() {
        return null;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
