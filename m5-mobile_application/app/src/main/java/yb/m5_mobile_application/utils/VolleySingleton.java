package yb.m5_mobile_application.utils;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    public static VolleySingleton instance_ = null;
    private RequestQueue requestQueue_;

    private VolleySingleton(Context context) {
        requestQueue_ = Volley.newRequestQueue(context);
    }

    public static VolleySingleton getInstance(Context context) {
        if (instance_ == null) instance_ = new VolleySingleton(context);
        return instance_;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue_;
    }

}
