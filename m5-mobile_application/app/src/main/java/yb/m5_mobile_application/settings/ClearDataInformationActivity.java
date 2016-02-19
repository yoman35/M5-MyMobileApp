package yb.m5_mobile_application.settings;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import yb.m5_mobile_application.R;
import yb.m5_mobile_application.api.jvc.RequestJVCNews;
import yb.m5_mobile_application.utils.VolleySingleton;

public class ClearDataInformationActivity extends AppCompatActivity {

    private static final int
            layoutId = R.layout.activity_clear_data_information,
            toolbarId = R.id.toolbar,
            homeAsUpId = R.drawable.ic_clear_black_24dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        setUpToolbar();
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable homeAsUpIcon = ContextCompat.getDrawable(this, homeAsUpId);
        getSupportActionBar().setHomeAsUpIndicator(homeAsUpIcon);
    }

}
