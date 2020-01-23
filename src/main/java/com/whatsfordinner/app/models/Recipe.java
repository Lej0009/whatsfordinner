package com.whatsfordinner.app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Array;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {

    private String title;
    private Float version;
    private String href;
    private Array results;

    public Recipe() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getVersion() {
        return version;
    }

    public void setVersion(Float version) {
        this.version = version;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Array getResults() {
        return results;
    }

    public void setResults(Array results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", version=" + version +
                ", href=" + href +
                ", results=" + results +
                '}';
    }

}
