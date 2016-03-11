package yb.m5_mobile_application.api.nyc.nyt_model;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Response {

    private List<Doc> docs = new ArrayList<>();

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

}
