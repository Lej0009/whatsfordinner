package com.whatsfordinner.app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import java.io.Serializable;
import java.util.ArrayList;

@ConfigurationProperties
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe implements Serializable {
    private static final long serialVersionUID = -3913629036907715099L;
    private String title;
    private Number version;
    private String href;
    private ArrayList<Results> results;

    public Recipe() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Number getVersion() {
        return version;
    }

    public void setVersion(Number version) {
        this.version = version;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }

    public ArrayList<Results> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title=" + title +
                "version=" + version +
                ", href=" + href +
                ", results=" + results +
                ", true" +
                "}";
    }

}
