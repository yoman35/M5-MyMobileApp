package yb.m5_mobile_application.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yb.m5_mobile_application.R;

public class MenuHeadAdapter
        extends RecyclerView.Adapter<MenuHeadAdapter.MenuHeadViewHolder>
        implements View.OnClickListener {

    private static final int
            layoutId = R.layout.menu_item_head,
            homeLogoId = R.drawable.ic_home_black_36dp,
            mustReadsLogoId = R.drawable.ic_star_border_black_36dp,
            bookedLogoId = R.drawable.ic_bookmark_border_black_36dp,
            homeTitleId = R.string.menu_item_home,
            mustReadsTitleId = R.string.menu_item_star,
            bookedTitleId = R.string.menu_item_book;

    private Context mContext = null;
    private List<MenuItem> mData = new ArrayList<>();

    public MenuHeadAdapter(Context context) {
        mContext = context;
        Drawable homeLogo = ContextCompat.getDrawable(mContext, homeLogoId),
                mustReadsLogo = ContextCompat.getDrawable(mContext, mustReadsLogoId),
                bookedLogo = ContextCompat.getDrawable(mContext, bookedLogoId);
        mData.add(new MenuItem(homeLogo, mContext.getString(homeTitleId)));
        mData.add(new MenuItem(mustReadsLogo, mContext.getString(mustReadsTitleId)));
        mData.add(new MenuItem(bookedLogo, mContext.getString(bookedTitleId)));
    }

    @Override
    public MenuHeadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new MenuHeadViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuHeadViewHolder holder, int position) {
        MenuItem current = mData.get(position);
        holder.logo.setImageDrawable(current.getLogo());
        holder.title.setText(current.getTitle());
        if (current.getCounter() <= 0)
            holder.counter.setText("");
        else
            holder.counter.setText(String.valueOf(current.getCounter()));
        holder.layout.setOnClickListener(this);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onClick(View v) {
        //TODO: menu item click listener
    }

    public class MenuHeadViewHolder extends RecyclerView.ViewHolder {
        private static final int
                layoutId = R.id.menu_item_head,
                logoId = R.id.menu_item_head_logo,
                titleId = R.id.menu_item_head_title,
                counterId = R.id.menu_item_head_counter;
        LinearLayout layout;
        ImageView logo;
        TextView title, counter;

        public MenuHeadViewHolder(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(layoutId);
            logo = (ImageView) itemView.findViewById(logoId);
            title = (TextView) itemView.findViewById(titleId);
            counter = (TextView) itemView.findViewById(counterId);
        }
    }
}
