package yb.m5_mobile_application.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

public class MenuMainFragment extends Fragment {

    private static final int
            menuHeadId = R.id.nd_menu_list_head,
            menuBodyId = R.id.nd_menu_list_body,
            categoryIconId = R.drawable.ic_style_black_36dp,
            categoryTitleId = R.string.menu_item_categories,
            favoritesIconId = R.drawable.ic_favorite_border_black_36dp,
            favoritesTitleId = R.string.menu_item_favorites;

    public MenuMainFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu_fragment_main, container, false);

        setUpRVHead(v);
        setUpRVBody(v);

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
        items.add(new MenuItem(categoryIcon, getString(categoryTitleId)));
        Drawable favoritesIcon = ContextCompat.getDrawable(context, favoritesIconId);
        items.add(new MenuItem(favoritesIcon, getString(favoritesTitleId)));
        return items;
    }

}
