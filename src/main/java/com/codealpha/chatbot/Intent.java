package com.codealpha.chatbot;

import java.util.List;

public class Intent {
    private final String name;
    private final List<String> keywords;
    private final List<String> examplePhrases;
    private final String response;

    public Intent(String name, List<String> keywords, List<String> examplePhrases, String response) {
        this.name = name;
        this.keywords = List.copyOf(keywords);
        this.examplePhrases = List.copyOf(examplePhrases);
        this.response = response;
    }

    public String getName() { return name; }
    public List<String> getKeywords() { return keywords; }
    public List<String> getExamplePhrases() { return examplePhrases; }
    public String getResponse() { return response; }
}
