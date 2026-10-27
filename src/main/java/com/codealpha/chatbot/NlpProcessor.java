package com.codealpha.chatbot;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class NlpProcessor {
    private static final Set<String> STOP_WORDS = Set.of(
            "a", "an", "the", "is", "are", "am", "was", "were", "to", "of", "in",
            "on", "at", "for", "and", "or", "but", "i", "you", "me", "my", "your",
            "it", "this", "that", "please", "can", "could", "would", "should", "do",
            "does", "did", "what", "how", "when", "where", "who", "why", "tell"
    );

    private NlpProcessor() {}

    public static String normalize(String input) {
        if (input == null) return "";
        return input.toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9\\s']", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    public static List<String> tokenize(String input) {
        String normalized = normalize(input);
        if (normalized.isBlank()) return List.of();
        List<String> tokens = new ArrayList<>();
        for (String token : normalized.split("\\s+")) {
            if (!STOP_WORDS.contains(token) && token.length() > 1) {
                tokens.add(token);
            }
        }
        return tokens;
    }

    public static Set<String> uniqueTokens(String input) {
        return new LinkedHashSet<>(tokenize(input));
    }

    public static String friendlyFormat(String input) {
        if (input == null || input.isBlank()) return "";
        String clean = input.strip();
        return clean.substring(0, 1).toUpperCase(Locale.ROOT) + clean.substring(1);
    }
}
