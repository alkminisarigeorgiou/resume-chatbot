package com.resume.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ResumeChatbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeChatbotApplication.class, args);
	}

}
