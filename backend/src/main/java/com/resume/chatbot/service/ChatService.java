package com.resume.chatbot.service;

import com.resume.chatbot.config.ResumeData;
import com.resume.chatbot.model.Sample;
import com.resume.chatbot.model.Theme;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ResumeData rd;

    public ChatService(ResumeData resumeData) {
        this.rd = resumeData;
    }

    public String getResponse(String input) {
        String normalizedInput = input.trim().toLowerCase();

        for (Sample sample : rd.getSamples()) {
            if (normalizedInput.equals(sample.getInput().toLowerCase())) {
                String themeName = sample.getTheme();
                for (Theme theme : rd.getThemes()) {
                    if (theme.getName().equalsIgnoreCase(themeName)) {
                        return theme.getResponse();
                    }
                }
            }
        }

        for (Theme theme : rd.getThemes()) {
            for (String keyword : theme.getKeywords()) {
                if (normalizedInput.contains(keyword.toLowerCase())) {
                    return theme.getResponse();
                }
            }
        }

        return "I don't have an answer for that :( Can you rephrase?";
    }
}