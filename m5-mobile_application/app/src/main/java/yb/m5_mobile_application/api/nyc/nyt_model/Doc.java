package yb.m5_mobile_application.api.nyc.nyt_model;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Doc {

    private String web_url;
    private String snippet;
    private String lead_paragraph;
    private Object _abstract;
    private String print_page;
    private List<Object> blog = new ArrayList<>();
    private String source;
    private List<Multimedia> multimedia = new ArrayList<>();
    private Headline headline;
    private List<Keyword> keywords = new ArrayList<>();
    private String pub_date;
    private String document_type;
    private String news_desk;
    private String section_name;
    private String subsection_name;
    private String type_of_material;
    private String _id;
    private String word_count;
    private Object slideshow_credits;

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getLead_paragraph() {
        return lead_paragraph;
    }

    public void setLead_paragraph(String lead_paragraph) {
        this.lead_paragraph = lead_paragraph;
    }

    public Object getAbstract() {
        return _abstract;
    }

    public void setAbstract(Object _abstract) {
        this._abstract = _abstract;
    }

    public String getPrint_page() {
        return print_page;
    }

    public void setPrint_page(String print_page) {
        this.print_page = print_page;
    }

    public List<Object> getBlog() {
        return blog;
    }

    public void setBlog(List<Object> blog) {
        this.blog = blog;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public String getPub_date() {
        return pub_date;
    }

    public void setPub_date(String pubDate) {
        this.pub_date = pubDate;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public String getNews_desk() {
        return news_desk;
    }

    public void setNews_desk(String news_desk) {
        this.news_desk = news_desk;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getSubsection_name() {
        return subsection_name;
    }

    public void setSubsection_name(String subsection_name) {
        this.subsection_name = subsection_name;
    }

    public String getType_of_material() {
        return type_of_material;
    }

    public void setType_of_material(String type_of_material) {
        this.type_of_material = type_of_material;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String Id) {
        this._id = Id;
    }

    public String getWord_count() {
        return word_count;
    }

    public void setWord_count(String word_count) {
        this.word_count = word_count;
    }

    public Object getSlideshow_credits() {
        return slideshow_credits;
    }

    public void setSlideshow_credits(Object slideshow_credits) {
        this.slideshow_credits = slideshow_credits;
    }

}
