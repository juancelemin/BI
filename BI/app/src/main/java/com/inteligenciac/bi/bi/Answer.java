package com.inteligenciac.bi.bi;

/**
 * Created by jguil on 2/04/2017.
 */

public class Answer {
    private String description = "BI";
    private String category = "none";

    public Answer(String description, String category) {
        this.description = description;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
