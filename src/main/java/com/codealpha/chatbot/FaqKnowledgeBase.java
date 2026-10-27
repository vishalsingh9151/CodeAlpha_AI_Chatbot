package com.codealpha.chatbot;

import java.util.ArrayList;
import java.util.List;

public final class FaqKnowledgeBase {
    private FaqKnowledgeBase() {}

    public static List<Intent> createIntents() {
        List<Intent> intents = new ArrayList<>();

        intents.add(new Intent("greeting",
                List.of("hello", "hi", "hey", "greetings", "namaste"),
                List.of("hello there", "hi bot", "hey chatbot"),
                "Hello! 😊 I'm CodeAlpha Bot. What would you like to know?"));

        intents.add(new Intent("goodbye",
                List.of("bye", "goodbye", "exit", "quit", "see"),
                List.of("bye bye", "see you later"),
                "Goodbye! 👋 Thanks for chatting. Have a great day!"));

        intents.add(new Intent("thanks",
                List.of("thanks", "thank", "appreciate", "helpful"),
                List.of("thank you", "thanks a lot"),
                "You're welcome! I'm glad I could help."));

        intents.add(new Intent("capabilities",
                List.of("help", "capabilities", "features", "questions", "support"),
                List.of("what can you do", "how can you help me"),
                "I can answer common questions about Java, NLP, machine learning, and this project. Try asking: 'What is Java?' or 'Explain NLP.'"));

        intents.add(new Intent("java",
                List.of("java", "programming", "language", "jvm", "object", "oriented"),
                List.of("what is java", "explain java programming"),
                "Java is a high-level, class-based programming language designed to be portable across platforms through the Java Virtual Machine (JVM). It supports object-oriented programming."));

        intents.add(new Intent("oop",
                List.of("oop", "class", "object", "encapsulation", "inheritance", "polymorphism", "abstraction"),
                List.of("what is object oriented programming", "explain encapsulation"),
                "OOP organizes software around classes and objects. Its four commonly taught principles are encapsulation, inheritance, polymorphism, and abstraction."));

        intents.add(new Intent("nlp",
                List.of("nlp", "natural", "language", "processing", "tokenization", "tokens", "text"),
                List.of("what is nlp", "explain natural language processing"),
                "Natural Language Processing (NLP) helps computers process human language. This chatbot uses basic NLP-style steps: lowercase normalization, punctuation cleanup, tokenization, and stop-word filtering."));

        intents.add(new Intent("machine_learning",
                List.of("machine", "learning", "model", "training", "train", "dataset", "prediction"),
                List.of("what is machine learning", "how is a model trained"),
                "Machine learning uses data to learn patterns and make predictions. This version uses a rule-based intent matcher rather than a trained machine-learning model, which satisfies the task's rule-based option."));

        intents.add(new Intent("chatbot",
                List.of("chatbot", "bot", "assistant", "conversation", "chat"),
                List.of("what is a chatbot", "how does this chatbot work"),
                "A chatbot is software that communicates with people through text or speech. I preprocess your message, compare its words with known intent keywords, and return the best matching response."));

        intents.add(new Intent("project",
                List.of("project", "codealpha", "internship", "task", "submission"),
                List.of("tell me about this project", "what is the codealpha task"),
                "This is the CodeAlpha Java Artificial Intelligence Chatbot project. It demonstrates interactive communication, basic NLP preprocessing, rule-based FAQ responses, and a Java Swing GUI."));

        intents.add(new Intent("creator",
                List.of("name", "created", "creator", "developed", "developer"),
                List.of("who created you", "who are you"),
                "I'm CodeAlpha Bot, a Java desktop chatbot built as an educational project."));

        intents.add(new Intent("time",
                List.of("time", "date", "today", "clock"),
                List.of("what time is it", "what is today's date"),
                "The current local date and time shown by your computer is " + java.time.LocalDateTime.now()
                        .format(java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a"))
                        + "."));

        intents.add(new Intent("motivation",
                List.of("motivate", "motivation", "study", "focus", "exam"),
                List.of("motivate me", "how can i focus on study"),
                "Keep going, one step at a time! Set a small goal, work for 25 minutes, take a short break, and repeat. Consistency beats cramming. 💪"));

        return List.copyOf(intents);
    }
}
