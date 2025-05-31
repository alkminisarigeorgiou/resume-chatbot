package com.resume.chatbot.model;

import org.springframework.stereotype.Component;

@Component
public class Sample {
    private String input;
    private String theme;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
