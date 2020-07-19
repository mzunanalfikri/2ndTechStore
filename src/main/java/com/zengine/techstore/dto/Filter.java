package com.zengine.techstore.dto;

public class Filter {
    private String key;
    private String category;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "key='" + key + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
