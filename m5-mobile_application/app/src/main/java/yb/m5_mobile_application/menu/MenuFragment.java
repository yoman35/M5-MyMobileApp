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
            layoutId = R.layout.fragment_menu,
            fragmentContainerId = R.id.menu_fragment_container;

    private FragmentManager mFragmentManager;

    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(layoutId, container, false);
        setFragmentManager();
        replaceContainerForMain();

        return v;
    }

    private void setFragmentManager() {
        mFragmentManager = getFragmentManager();
    }

    private void replaceContainerForMain() {
        mFragmentManager.beginTransaction()
                .replace(fragmentContainerId, new MenuMainFragment())
                .commit();
    }
}
