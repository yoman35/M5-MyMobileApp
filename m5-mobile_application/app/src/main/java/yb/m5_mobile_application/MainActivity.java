package yb.m5_mobile_application;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.crashlytics.android.Crashlytics;

import java.util.Locale;

import io.fabric.sdk.android.Fabric;
import yb.m5_mobile_application.menu.MenuFragment;
import yb.m5_mobile_application.settings.SettingsActivity;
import yb.m5_mobile_application.utils.MyApp;
import yb.m5_mobile_application.utils.MySharedPreferences;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

public class MainActivity extends AppCompatActivity {

    private static final int
            layoutId = R.layout.activity_main,
            toolbarId = R.id.toolbar,
            drawerId = R.id.main_drawer_layout,
            menuLayoutId = R.menu.menu_main,
            drawerOpenId = R.string.drawer_open,
            drawerCloseId = R.string.drawer_close,
            actionSettingsId = R.id.action_settings;

    private ActionBarDrawerToggle mDrawerToggle;

    private ShareButton shareButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(layoutId);

        checkFirstTime();

        Toolbar toolbar = setUpToolbar();
        setUpNavigationDrawer(toolbar);
        setUpShareButton();
    }

    private void checkFirstTime() {
        MySharedPreferences sp = MyApp.getInstance().getSP();
        if (sp.isItFirstTime()) {
            sp.disableFirstTime();
            String fr = new Locale("", "FR").getDisplayCountry();
            if (MyApp.getInstance().getPhoneCountry().equals(fr))
                sp.setCountry(getString(R.string.france));
            else
                sp.setCountry(getString(R.string.england));
        }
    }

    private Toolbar setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private ShareLinkContent setUpSharingModel(String linkUrl,
                                               String title,
                                               String imageUrl,
                                               String description) {
        return new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(linkUrl))
                .setContentTitle(title)
                .setImageUrl(Uri.parse(imageUrl))
                .setContentDescription(description)
                .build();
    }

    private void setUpShareButton() {
        shareButton = (ShareButton)findViewById(R.id.share_btn);
        shareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                shareButton.setShareContent(setUpSharingModel(
                        "http://estcequecestbientotleweekend.fr/",
                        "Week-End",
                        "",
                        "Finis les week-end de 4 jours. :("));
                shareButton.performClick();
            }
        });
    }

    private ActionBarDrawerToggle setUpDrawerToggle(DrawerLayout layout, Toolbar toolbar) {
        return new ActionBarDrawerToggle(this, layout, toolbar, drawerOpenId, drawerCloseId);
    }

    private void setUpNavigationDrawer(Toolbar toolbar) {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(drawerId);
        mDrawerToggle = setUpDrawerToggle(drawerLayout, toolbar);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.navigation_drawer, new MenuFragment())
                .commit();
        drawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else if (item.getItemId() == actionSettingsId) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(menuLayoutId, menu);
        return true;
    }
}
