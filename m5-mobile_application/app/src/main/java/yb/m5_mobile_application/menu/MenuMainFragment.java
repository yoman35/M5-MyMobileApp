package yb.m5_mobile_application.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yb.m5_mobile_application.R;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MenuMainFragment extends Fragment {

    private static final int
            menuHeadId = R.id.nd_menu_list_head,
            menuBodyId = R.id.nd_menu_list_body,
            categoryIconId = R.drawable.ic_style_black_36dp,
            categoryTitleId = R.string.menu_item_category,
            favoriteIconId = R.drawable.ic_favorite_border_black_36dp,
            favoriteTitleId = R.string.menu_item_favorites;

    private CallbackManager callbackManager;
    private LoginButton authButton;

    public MenuMainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu_main, container, false);
        setUpRVHead(v);
        setUpRVBody(v);
        authButton = (LoginButton) v.findViewById(R.id.login_button);
        authButton.setFragment(this);
        authButton.setPublishPermissions(Arrays.asList("publish_actions"));
        callbackManager = CallbackManager.Factory.create();
        authButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.v("INFO", "Login success");
            }

            @Override
            public void onCancel() {
                Log.v("INFO", "Login cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.v("INFO", "Login error", exception);
            }
        });
        return v;
    }

    private void setUpRVHead(View v) {
        RecyclerView menuHead = (RecyclerView) v.findViewById(menuHeadId);
        int orientation = LinearLayoutManager.VERTICAL;
        menuHead.setLayoutManager(new LinearLayoutManager(getActivity(), orientation, false));
        menuHead.setAdapter(new MenuHeadAdapter(getActivity()));
    }

    private void setUpRVBody(View v) {
        RecyclerView menuListBody = (RecyclerView) v.findViewById(menuBodyId);
        int orientation = LinearLayoutManager.VERTICAL;
        menuListBody.setLayoutManager(new LinearLayoutManager(getActivity(), orientation, false));
        menuListBody.setAdapter(new MenuBodyAdapter(getDataBody()));
    }

    private List<MenuItem> getDataBody() {
        List<MenuItem> items = new ArrayList<>();
        Context context = getActivity();
        Drawable categoryIcon = ContextCompat.getDrawable(context, categoryIconId);
        Drawable favoriteIcon = ContextCompat.getDrawable(context, favoriteIconId);
        String categoryTitle = getString(categoryTitleId);
        String favoriteTitle = getString(favoriteTitleId);
        MenuItem categoryItem = new MenuItem(categoryIcon, categoryTitle);
        MenuItem favoriteItem = new MenuItem(favoriteIcon, favoriteTitle);
        items.add(categoryItem);
        items.add(favoriteItem);
        return items;
    }
}
