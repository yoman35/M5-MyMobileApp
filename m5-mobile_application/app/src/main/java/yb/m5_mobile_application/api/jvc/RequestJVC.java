package yb.m5_mobile_application.api.jvc;

import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import yb.m5_mobile_application.R;
import yb.m5_mobile_application.utils.MyApp;

public class RequestJVC extends StringRequest {
    public RequestJVC(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, "http://ws.jeuxvideo.com/" + url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();
        String login = MyApp.getInstance().getString(R.string.jvc_login);
        String pass = MyApp.getInstance().getString(R.string.jvc_pass);
        params.put(
                "Authorization",
                String.format("Basic %s", Base64.encodeToString(
                        String.format("%s:%s", login, pass).getBytes(), Base64.DEFAULT)));
        return params;
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError){
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null)
            volleyError = new VolleyError(new String(volleyError.networkResponse.data));
        return volleyError;
    }
}
