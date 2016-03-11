package yb.m5_mobile_application.api.jvc;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import yb.m5_mobile_application.models.Article;

public class SerializerJVCNews {

    private String xml;

    public SerializerJVCNews(String xml) {
        this.xml = xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getXml() {
        return this.xml;
    }

    public List<Article> deserialize() throws XmlPullParserException {
        XmlPullParserFactory factory;
        XmlPullParser parser;
        List<Article> articles = new ArrayList<>();
        String text = "";
        Article article = new Article();
        InputStream stream = new ByteArrayInputStream(this.xml.getBytes(Charset.forName("UTF-8")));
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();
            parser.setInput(stream, null);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tag = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tag.equalsIgnoreCase("news")) {
                            article = new Article();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tag.equalsIgnoreCase("news")) {
                            articles.add(article);
                        } else if (tag.equalsIgnoreCase("id")) {
                            article.setId(text);
                        } else if (tag.equalsIgnoreCase("titre")) {
                            article.setTitle(text);
                        } else if (tag.equalsIgnoreCase("resume")) {
                            article.setResume(text);
                        } else if (tag.equalsIgnoreCase("date")) {
                            article.setDate(text);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            Log.d("EXCEPTION", "XmlPullParser: " + e.getMessage());
        } catch (IOException e) {
            Log.d("EXCEPTION", "IO: " + e.getMessage());
        }
        return articles;
    }

}
