package com.whatsfordinner.app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Results extends Recipe {

    @NestedConfigurationProperty
    private String resultsTitle;

    @NestedConfigurationProperty
    private String resultsHref;

    @NestedConfigurationProperty
    private String resultsIngredients;

    @NestedConfigurationProperty
    private String resultsThumbnail;

    public Results() {
    }

    public String getResultsTitle() {
        return resultsTitle;
    }

    public void setTitle(String resultsTitle) {
        this.resultsTitle = resultsTitle;
    }

    public String getResultsHref() {
        return resultsHref;
    }

    public void setHref(String resultsHref) {
        this.resultsHref = resultsHref;
    }

    public String getResultsIngredients() {
        return resultsIngredients;
    }

    public void setIngredients(String resultsIngredients) {
        this.resultsIngredients = resultsIngredients;
    }

    public String getResultsThumbnail() {
        return resultsThumbnail;
    }

    public void setThumbnail(String resultsThumbnail) {
        this.resultsThumbnail = resultsThumbnail;
    }

    @Override
    public String toString() {
        return "Results{" +
                "title=" + resultsTitle +
                ", href=" + resultsHref +
                ", ingredients=" + resultsIngredients +
                ", thumbnail=" + resultsThumbnail +
                "}";
    }

}
