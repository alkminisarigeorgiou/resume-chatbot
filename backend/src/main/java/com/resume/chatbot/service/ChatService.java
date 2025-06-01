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

            if (rd.getSamples() == null || rd.getThemes() == null) {
                return "Configuration not loaded. Please check application.yml.";
            }

        String normalizedInput = input.trim().toLowerCase().replaceAll("[^a-z0-9 ]", "");
        System.out.println("ChatService received input: '" + input + "'");
        System.out.println("Normalized: '" + normalizedInput + "'");
        System.out.println("\n--- Loaded Samples ---");
        for (Sample sample : rd.getSamples()) {
            System.out.println("Trying to match against sample: '" + sample.getInput() + "'");
            if (normalizedInput.equals(sample.getInput().toLowerCase())) {
                System.out.println("Matched with theme: " + sample.getTheme());
                String themeName = sample.getTheme();
                System.out.println("\n--- Loaded Samples ---");
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