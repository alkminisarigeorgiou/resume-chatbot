package com.resume.chatbot.config;

import com.resume.chatbot.model.Sample;
import com.resume.chatbot.model.Theme;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "resume")
public class ResumeData {
    private List<Sample> samples  = List.of();
    private List<Theme> themes = List.of();

    public List<Sample> getSamples() {
        return samples;
    }

    public void setSamples(List<Sample> samples) {
        this.samples= samples;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }
}