package yb.m5_mobile_application;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;

import java.util.Locale;

import io.fabric.sdk.android.Fabric;
import yb.m5_mobile_application.main_content.ArticlesFragment;
import yb.m5_mobile_application.menu.MenuFragment;
import yb.m5_mobile_application.settings.SettingsActivity;
import yb.m5_mobile_application.utils.MyApp;
import yb.m5_mobile_application.utils.MySharedPreferences;

public class MainActivity extends AppCompatActivity {

    private static final int
            layoutId = R.layout.activity_main,
            toolbarId = R.id.toolbar,
            contentId = R.id.main_content,
            drawerId = R.id.main_drawer_layout,
            menuLayoutId = R.menu.menu_main,
            drawerOpenId = R.string.drawer_open,
            drawerCloseId = R.string.drawer_close,
            actionSettingsId = R.id.action_settings,
            navigationDrawerId = R.id.navigation_drawer;

    private ActionBarDrawerToggle mDrawerToggle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(layoutId);

        checkFirstTime();

        Toolbar toolbar = setUpToolbar();
        setUpNavigationDrawer(toolbar);
        showToolbarLogo();

        setContent();
    }

    private void setContent() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(contentId, new ArticlesFragment())
                .commit();
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

    private ActionBarDrawerToggle setUpDrawerToggle(DrawerLayout layout, Toolbar toolbar) {
        return new ActionBarDrawerToggle(this, layout, toolbar, drawerOpenId, drawerCloseId);
    }

    private void setUpNavigationDrawer(Toolbar toolbar) {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(drawerId);
        mDrawerToggle = setUpDrawerToggle(drawerLayout, toolbar);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(navigationDrawerId, new MenuFragment())
                .commit();
        drawerLayout.addDrawerListener(mDrawerToggle);
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

    private void showToolbarLogo() {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }
}
