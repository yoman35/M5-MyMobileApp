package yb.m5_mobile_application.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.Locale;

import yb.m5_mobile_application.R;

public class SettingsActivity extends AppCompatActivity {

    private static final int
            layoutId = R.layout.settings_activity,
            toolbarId = R.id.toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        setUpToolbar();
        setLanguageChoice();
        setCountryChoice();
    }

    private Toolbar setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return toolbar;
    }

    private void setLanguageChoice() {
        TextView language = (TextView) findViewById(R.id.setting_language_choice);
        String locale = Locale.getDefault().getDisplayLanguage();
        //TODO: Replace with -> String locale = MyApp.getInstance().getSP().getLanguage();
        language.setText(locale);
    }

    private void setCountryChoice() {
        TextView country = (TextView) findViewById(R.id.setting_country_choice);
        String locale = Locale.getDefault().getDisplayCountry();
        //TODO: Replace with -> String locale = MyApp.getInstance().getSP().getCountry();
        country.setText(locale);
    }
}
