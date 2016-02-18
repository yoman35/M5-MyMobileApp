package yb.m5_mobile_application.menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yb.m5_mobile_application.R;

public class MenuFragment extends Fragment {

    private static final int
            layoutId = R.layout.fragment_menu;

    private FragmentManager mManager;

    public MenuFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(layoutId, container, false);
        mManager = getFragmentManager();
        changeContainerToMain();
        return v;
    }

    private void changeContainerToMain() {
        MenuMainFragment fragment = new MenuMainFragment();
        mManager.beginTransaction().replace(R.id.fragment_menu_container, fragment).commit();
    }

}
