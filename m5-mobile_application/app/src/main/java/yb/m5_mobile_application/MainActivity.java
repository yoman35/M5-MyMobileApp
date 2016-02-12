package yb.m5_mobile_application;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import yb.m5_mobile_application.navigationDrawer.NavigationDrawerFragment;

public class MainActivity extends AppCompatActivity {

    private static final int
            layoutId = R.layout.main_activity,
            toolbarId = R.id.toolbar,
            drawerId = R.id.main_drawer_layout,
            menuLayoutId = R.menu.menu_main,
            drawerOpenId = R.string.drawer_open,
            drawerCloseId = R.string.drawer_close;

    private ActionBarDrawerToggle mDrawerToggle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(layoutId);

        Toolbar toolbar = setUpToolbar();
        setUpNavigationDrawer(toolbar);
    }

    private Toolbar setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private ActionBarDrawerToggle setUpDrawerToggle(DrawerLayout layout, Toolbar toolbar) {
        return new ActionBarDrawerToggle(this, layout, toolbar, drawerOpenId, drawerCloseId);
    }

    private void setUpNavigationDrawer(Toolbar toolbar) {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(drawerId);
        mDrawerToggle = setUpDrawerToggle(drawerLayout, toolbar);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.navigation_drawer, new NavigationDrawerFragment())
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
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(menuLayoutId, menu);
        return true;
    }
}
