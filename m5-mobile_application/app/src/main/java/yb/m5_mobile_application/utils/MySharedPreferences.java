package yb.m5_mobile_application.utils;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import yb.m5_mobile_application.R;
import yb.m5_mobile_application.models.Article;
import yb.m5_mobile_application.models.User;

public class MySharedPreferences {

    public static final String
            SP_KEY = "yb.m5_mobile_application.sp";

    private SharedPreferences sharedPreferences;

    public MySharedPreferences() {
    }

    public User getUser() {
        String jsonUser = this.sharedPreferences.getString(Key.USER.getName(), "");
        return new Gson().fromJson(jsonUser, User.class);
    }

    public void setUser(User user) {
        String jsonUser = new Gson().toJson(user);
        this.sharedPreferences
                .edit()
                .putString(Key.USER.getName(), jsonUser)
                .apply();
    }

    public boolean isItFirstTime() {
        return this.sharedPreferences.getBoolean(Key.FIRST_LAUNCH.getName(), true);
    }

    public void disableFirstTime() {
        this.sharedPreferences
                .edit()
                .putBoolean(Key.FIRST_LAUNCH.getName(), false)
                .apply();
        setUser(null);
    }

    public String getCountry() {
        return this.sharedPreferences.getString(Key.COUNTRY.getName(), "");
    }

    public void setCountry(String country) {
        this.sharedPreferences
                .edit()
                .putString(Key.COUNTRY.getName(), country)
                .apply();
    }

    public Set<String> getCategories() {
        Set<String> defValue = new HashSet<>();
        return this.sharedPreferences.getStringSet(Key.CATEGORIES.getName(), defValue);
    }

    public void setCategories(Set<Category> categories) {
        Set<String> tmp = new HashSet<>();
        for (Category category : categories) {
            tmp.add(category.getName());
        }
        this.sharedPreferences
                .edit()
                .putStringSet(Key.CATEGORIES.getName(), tmp)
                .apply();
    }

    public Set<Article> getBookedArticles() {
        String key = Key.BOOKED_ARTICLES.getName();
        Set<String> defValue = new HashSet<>();
        Set<String> jsonArticles = this.sharedPreferences.getStringSet(key, defValue);
        Set<Article> articles = new HashSet<>();
        for (String jsonArticle : jsonArticles)
            articles.add(new Gson().fromJson(jsonArticle, Article.class));
        return articles;
    }

    public void setBookedArticles(List<Article> bookedArticles) {
        Set<String> jsonArticles = new HashSet<>();
        for (Article article : bookedArticles)
            jsonArticles.add(new Gson().toJson(article));
        this.sharedPreferences
                .edit()
                .putStringSet(Key.BOOKED_ARTICLES.getName(), jsonArticles)
                .apply();
    }

    public void setSP(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public enum Key {
        USER("__user__"),
        FIRST_LAUNCH("__first_launch__"),
        COUNTRY("__country__"),
        CATEGORIES("__categories__"),
        BOOKED_ARTICLES("__booked_articles__");
        private String name;

        Key(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public enum Category {
        TECHNOLOGY("__technology__"),
        HEALTH("__health__"),
        ENVIRONMENT("__environment__"),
        AUTOMOBILE("__automobile__"),
        SPORT("__sport__");
        private String name;

        Category(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public void setDefaultSharedPreferences() {
        String fr = new Locale("", "FR").getDisplayCountry();
        if (MyApp.getInstance().getPhoneCountry().equals(fr))
            setCountry(MyApp.getInstance().getString(R.string.france));
        else
            setCountry(MyApp.getInstance().getString(R.string.england));
        setUser(null);
        setCategories(new HashSet<Category>());
        setBookedArticles(new ArrayList<Article>());
    }

}
