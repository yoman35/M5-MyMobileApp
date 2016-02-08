package yb.m5_mobile_application.navigationDrawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yb.m5_mobile_application.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        setUpRVHead(v);
        //setUpRVBody(v);

        return v;
    }

    private void setUpRVHead(View v) {
        RecyclerView menuHead = (RecyclerView) v.findViewById(R.id.nd_menu_list_head);
        menuHead.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        menuHead.setAdapter(new MenuHeadAdapter(getActivity()));
    }

    private void setUpRVBody(View v) {
        RecyclerView menuListBody = (RecyclerView) v.findViewById(R.id.nd_menu_list_body);
        menuListBody.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        menuListBody.setAdapter(new MenuBodyAdapter(getDataBody()));
    }

    private List<MenuItem> getDataBody() {
        if (dataBodyExists()) {
            return recoverFromExistingBodyData();
        } else {
            //TODO: See what to do when it's empty
            return new ArrayList<>();
        }
    }

    private List<MenuItem> recoverFromExistingBodyData() {
        //TODO: MyApplication singleton + get SP body data
        return null;
    }

    private boolean dataBodyExists() {
        //TODO: MyApplication.getInstance().doesBodyDataExists()
        return false;
    }
}
