package com.pragyamutluru.news;

/**
 * Created by suresh on 13/2/18.
 */

public class News {

    private String title;
    private String description;
    private String imageUrl;
    private String webUrl;
    private String source;
    private String date;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {

        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getSource() {
        return source;
    }

    public String getDate() {
        return date;
    }
}
