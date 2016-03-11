package yb.m5_mobile_application.api.nyc;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Request using Volley to retrieve all the articles
 * from the New York Times with their API
 * help: http://developer.nytimes.com/docs/read/article_search_api_v2
 */
public class RequestNYT extends StringRequest {

    // Api key given by http://developer.nytimes.com
    private static final String key = "d339aad0fc95e34a6150da38b392fea5:6:74550951";

    /**
     * @param date      format YYYYMMDD
     * @param newest    if true, order by newest. Oldest otherwise.
     * @param pageIndex 0 is for page 0-9, 1 for 10-19, ...
     * @param listener  listener     to handle success request in your context
     * @param error     listener to handle failure in your context
     */
    public RequestNYT(String date,
                      boolean newest,
                      int pageIndex,
                      Response.Listener<String> listener,
                      Response.ErrorListener error) {
        super(
                Method.GET,
                "http://api.nytimes.com/svc/search/v2/articlesearch.json"
                        + "?api-key=" + key
                        + "&response-format=.jsonp"
                        + "&begin_date=" + date
                        + "&sort=" + (newest ? "newest" : "oldest")
                        + "&page=" + String.valueOf(pageIndex),
                listener,
                error
        );
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null)
            volleyError = new VolleyError(new String(volleyError.networkResponse.data));
        return volleyError;
    }
}
