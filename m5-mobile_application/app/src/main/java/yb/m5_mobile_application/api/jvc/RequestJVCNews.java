package yb.m5_mobile_application.api.jvc;

import com.android.volley.Response;

public class RequestJVCNews extends RequestJVC {
    public RequestJVCNews(Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.GET, "02.flux_news.xml", listener, errorListener);
    }
}
