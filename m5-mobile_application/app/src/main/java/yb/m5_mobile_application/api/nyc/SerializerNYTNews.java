package yb.m5_mobile_application.api.nyc;

import android.util.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import yb.m5_mobile_application.models.Article;

@SuppressWarnings("unused")
public class SerializerNYTNews {

    private String json;

    public SerializerNYTNews(String json) {
        this.json = json;
    }

    private InputStream getStreamFromString(String s) {
        return new ByteArrayInputStream(s.getBytes(Charset.forName("UTF-8")));
    }

    public List<Article> deserialize() throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(getStreamFromString(json), "UTF-8"));
        //noinspection TryFinallyCanBeTryWithResources
        try {
            return readArticlesFromResponse(reader);
        } finally {
            reader.close();
        }
    }

    public List<Article> readArticlesFromResponse(JsonReader reader) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("docs")) {
                return readArticlesFromDocs(reader);
            }
        }
        return new ArrayList<>();
    }

    public List<Article> readArticlesFromDocs(JsonReader reader) throws IOException {
        List<Article> articles = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            articles.add(readArticle(reader));
        }
        reader.endArray();
        return articles;
    }

    private Article readArticle(JsonReader reader) throws IOException {
        Article article = new Article();
        reader.beginObject();
        String name;
        while (reader.hasNext()) {
            name = reader.nextName();
            switch (name) {
                case "_id":
                    article.setId(reader.nextString());
                    break;
                case "headline":
                    article.setTitle(readTitleFromHeadline(reader));
                    break;
                case "lead_paragraph":
                    article.setResume(reader.nextString());
                    break;
                case "pub_date":  //receive example: "2016-03-12T00:00:00Z"
                    article.setDate(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return article;
    }

    private String readTitleFromHeadline(JsonReader reader) throws IOException {
        reader.beginObject();
        String name, title = "";
        while (reader.hasNext()) {
            name = reader.nextName();
            if (name.equals("main")) {
                title = reader.nextString();
            }
        }
        reader.endObject();
        return title;
    }

}
