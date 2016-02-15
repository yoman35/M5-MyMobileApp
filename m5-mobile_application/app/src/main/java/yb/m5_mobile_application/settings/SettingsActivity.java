package yb.m5_mobile_application.settings;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import yb.m5_mobile_application.R;
import yb.m5_mobile_application.utils.MyApp;
import yb.m5_mobile_application.utils.MySharedPreferences;

public class SettingsActivity extends AppCompatActivity {

    private static final int
            layoutId = R.layout.settings_activity,
            toolbarId = R.id.toolbar,
            languageChoiceId = R.id.setting_language_choice,
            countryChoiceId = R.id.setting_country_choice;

    private ContentValues mContentValues;
    private boolean mDataSetChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        mContentValues = new ContentValues();
        mDataSetChanged = false;
        setUpToolbar();
        setDefaultOptions();
    }

    private Toolbar setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return toolbar;
    }

    @Override
    protected void onResume() {
        Log.d("SETTINGS", "RESUME");
        super.onResume();
        if (mDataSetChanged)
            restoreTmpData();
    }

    @Override
    protected void onPause() {
        Log.d("SETTINGS", "PAUSE");
        if (dataHasChanged())
            saveTmpData();
        super.onPause();
    }

    @Override
    protected void onStart() {
        Log.d("SETTINGS", "START");
        super.onStart();
        if (mDataSetChanged)
            restoreTmpData();
    }

    @Override
    protected void onStop() {
        Log.d("SETTINGS", "STOP");
        if (dataHasChanged())
            saveTmpData();
        super.onStop();
    }

    private boolean dataHasChanged() {
        TextView language = (TextView) findViewById(languageChoiceId);
        TextView country = (TextView) findViewById(countryChoiceId);
        String tmpLanguage = language.getText().toString();
        String tmpCountry = country.getText().toString();
        if (!tmpLanguage.equals(MyApp.getInstance().getSP().getLanguage()) ||
                !tmpCountry.equals(MyApp.getInstance().getSP().getCountry())) {
            mDataSetChanged = true;
            return true;
        }
        return false;
    }

    private void setDefaultOptions() {
        TextView language = (TextView) findViewById(languageChoiceId);
        TextView country = (TextView) findViewById(countryChoiceId);
        language.setText(MyApp.getInstance().getSP().getLanguage());
        country.setText(MyApp.getInstance().getSP().getCountry());
    }

    private void saveTmpData() {
        TextView language = (TextView) findViewById(languageChoiceId);
        TextView country = (TextView) findViewById(countryChoiceId);
        String tmpLanguage = language.getText().toString();
        String tmpCountry = country.getText().toString();
        mContentValues.clear();
        mContentValues.put(MySharedPreferences.Key.LANGUAGE.getName(), tmpLanguage);
        mContentValues.put(MySharedPreferences.Key.COUNTRY.getName(), tmpCountry);
    }

    private void restoreTmpData() {
        TextView language = (TextView) findViewById(languageChoiceId);
        TextView country = (TextView) findViewById(countryChoiceId);
        String tmpLanguage = mContentValues.getAsString(MySharedPreferences.Key.LANGUAGE.getName());
        String tmpCountry = mContentValues.getAsString(MySharedPreferences.Key.COUNTRY.getName());
        language.setText(tmpLanguage);
        country.setText(tmpCountry);
        mDataSetChanged = false;
    }
}
