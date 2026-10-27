package com.codealpha.chatbot;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ChatbotEngine {
    private final List<Intent> intents;
    private static final double MIN_SCORE = 0.20;

    public ChatbotEngine() {
        this.intents = FaqKnowledgeBase.createIntents();
    }

    public String getResponse(String message) {
        if (message == null || message.isBlank()) {
            return "Please type a message so I can try to help.";
        }

        Set<String> inputTokens = NlpProcessor.uniqueTokens(message);
        if (inputTokens.isEmpty()) {
            return "I couldn't find meaningful words in that message. Could you rephrase it?";
        }

        Intent bestIntent = null;
        double bestScore = 0.0;

        for (Intent intent : intents) {
            double score = scoreIntent(inputTokens, intent);
            if (score > bestScore) {
                bestScore = score;
                bestIntent = intent;
            }
        }

        if (bestIntent != null && bestScore >= MIN_SCORE) {
            return bestIntent.getResponse();
        }

        return "I'm not sure I understood that yet. Try asking about Java, OOP, NLP, machine learning, chatbots, or this project.";
    }

    private double scoreIntent(Set<String> inputTokens, Intent intent) {
        Set<String> expected = new java.util.HashSet<>(intent.getKeywords());
        for (String phrase : intent.getExamplePhrases()) {
            expected.addAll(NlpProcessor.uniqueTokens(phrase));
        }

        long matches = inputTokens.stream().filter(expected::contains).count();
        if (matches == 0) return 0.0;

        // Reward coverage while avoiding a high score for one word in a long message.
        double inputCoverage = (double) matches / inputTokens.size();
        double intentCoverage = (double) matches / Math.max(1, expected.size());
        return (0.75 * inputCoverage) + (0.25 * intentCoverage);
    }
}
