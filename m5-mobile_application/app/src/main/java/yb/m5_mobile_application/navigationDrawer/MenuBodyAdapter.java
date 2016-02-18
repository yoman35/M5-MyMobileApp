package yb.m5_mobile_application.navigationDrawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yb.m5_mobile_application.R;

public class MenuBodyAdapter extends RecyclerView.Adapter<MenuBodyAdapter.MenuBodyViewHolder> {

    private static final int
            layoutId = R.layout.menu_item_body;

    private List<MenuItem> mData = new ArrayList<>();

    public MenuBodyAdapter(List<MenuItem> data) {
        mData = data;
    }

    @Override
    public MenuBodyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new MenuBodyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuBodyViewHolder holder, int position) {
        MenuItem current = mData.get(position);
        holder.logo.setImageDrawable(current.getLogo());
        holder.title.setText(current.getTitle());
        holder.counter.setText(String.valueOf(current.getCounter()));
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: menu body click listener
            }
        };
        holder.logo.setOnClickListener(clickListener);
        holder.title.setOnClickListener(clickListener);
        holder.counter.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MenuBodyViewHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView title, counter;
        public MenuBodyViewHolder(View v) {
            super(v);
            logo = (ImageView) v.findViewById(R.id.menu_item_logo);
            title = (TextView) v.findViewById(R.id.menu_item_title);
            counter = (TextView) v.findViewById(R.id.menu_item_counter);
        }
    }

}
