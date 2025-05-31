package com.resume.chatbot.model;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Theme {
    private String name;
    private List<String> keywords;
    private String response;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
