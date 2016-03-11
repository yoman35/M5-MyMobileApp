package yb.m5_mobile_application.api.nyc.nyt_model;

@SuppressWarnings("unused")
public class Keyword {

    private String rank;
    private String is_major;
    private String name;
    private String value;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getIs_major() {
        return is_major;
    }

    public void setIs_major(String is_major) {
        this.is_major = is_major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
