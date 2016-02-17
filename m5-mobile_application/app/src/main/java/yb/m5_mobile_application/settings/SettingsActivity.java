package yb.m5_mobile_application.settings;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yb.m5_mobile_application.R;
import yb.m5_mobile_application.utils.MyApp;
import yb.m5_mobile_application.utils.MySharedPreferences;

public class SettingsActivity extends AppCompatActivity
        implements CountryChoiceDialog.CountryChoiceDialogListener {

    private static final int
            layoutId = R.layout.settings_activity,
            toolbarId = R.id.toolbar,
            countryChoiceId = R.id.setting_country_choice,
            clearDataInformation = R.id.setting_clear_data_information;

    private ContentValues mContentValues;
    private boolean mDataSetChanged;

    private List<String> mCountryChoices = new ArrayList<>();
    private TextView mCountryChoice;

    public SettingsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        initVariables();
        initCountryChoice();
        checkSavedInstanceState(savedInstanceState);
        initClearData();
        initClearDataInformation();
        setUpToolbar();
        setDefaultOptions();
    }

    private void initVariables() {
        mContentValues = new ContentValues();
        mDataSetChanged = false;
    }

    private void initCountryChoice() {
        mCountryChoices.add(getString(R.string.france));
        mCountryChoices.add(getString(R.string.england));
        mCountryChoice = (TextView) findViewById(countryChoiceId);
        mCountryChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountryChoiceDialog countryDialog = new CountryChoiceDialog();
                countryDialog.setCheckedItem(getChoiceId());
                countryDialog.show(getFragmentManager(), "countryChoice");
            }
        });
    }

    private void initClearData() {
        LinearLayout clearData = (LinearLayout) findViewById(R.id.settings_clear_data);
        clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearDataDialog clearDataDialog = new ClearDataDialog();
                clearDataDialog.show(getFragmentManager(), "clearData");
            }
        });
    }

    private void initClearDataInformation() {
        ImageView clearDataInfo = (ImageView) findViewById(clearDataInformation);
        final Context context = this;
        clearDataInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ClearDataInformationActivity.class));
            }
        });
    }

    private Toolbar setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return toolbar;
    }

    private void setDefaultOptions() {
        mCountryChoice.setText(MyApp.getInstance().getSP().getCountry());
    }

    @Override
    protected void onPause() {
        if (dataHasChanged())
            saveTmpData();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDataSetChanged)
            restoreTmpData();
    }

    private boolean dataHasChanged() {
        String country = mCountryChoice.getText().toString();
        if (!country.equals(MyApp.getInstance().getSP().getCountry())) {
            mDataSetChanged = true;
            return true;
        }
        return false;
    }

    private void saveTmpData() {
        String tmpCountry = mCountryChoice.getText().toString();
        mContentValues.clear();
        mContentValues.put(MySharedPreferences.Key.COUNTRY.getName(), tmpCountry);
    }

    private void restoreTmpData() {
        String countryKey = MySharedPreferences.Key.COUNTRY.getName();
        String tmpCountry = mContentValues.getAsString(countryKey);
        mCountryChoice.setText(tmpCountry);
        mDataSetChanged = false;
    }

    private int getChoiceId() {
        return mCountryChoices.indexOf(mCountryChoice.getText().toString());
    }

    private void setChoiceValue(int choiceId) {
        String country = mCountryChoices.get(choiceId);
        mCountryChoice.setText(country);
        MyApp.getInstance().getSP().setCountry(country);
    }

    @Override
    public void onCountryChoiceDialogPositiveClick(DialogFragment dialog) {
        setChoiceValue(((CountryChoiceDialog) dialog).getItemChecked());
    }

    private void checkSavedInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null)
            mCountryChoice.setText(savedInstanceState.getString(String.valueOf(countryChoiceId)));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(String.valueOf(countryChoiceId), mCountryChoice.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCountryChoice.setText(savedInstanceState.getString(String.valueOf(countryChoiceId)));
    }

    @Override
    protected void onDestroy() {
        String countryChoice = mCountryChoice.getText().toString();
        MyApp.getInstance().getSP().setCountry(countryChoice);
        super.onDestroy();
    }
}
