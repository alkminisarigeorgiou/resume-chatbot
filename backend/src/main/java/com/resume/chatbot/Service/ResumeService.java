package com.resume.chatbot.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class ResumeService {
    private Map<String, String> samples;
    private List<Map<String, Object>> themes;
    @Autowired
    private ObjectMapper mapper;

    @PostConstruct
    public void loadResumeData() {
        try {
            InputStream json = getClass().getClassLoader()
                    .getResourceAsStream("ResumeData.json");
            JsonNode node = mapper.readTree(json);

            this.themes = mapper.readValue(node.get("themes").toString(),
                    new TypeReference<List<Map<String, Object>>>() {});


            this.samples = mapper.readValue(node.get("samples").toString(),
                    new TypeReference<Map<String, String>>() {});
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Map<String, String> getSamples() {
        return samples;
    }

    public List<Map<String, Object>> getThemes() {
        return themes;
    }
}