# CodeAlpha Artificial Intelligence Chatbot (Java, 2026)

A complete beginner-friendly Java desktop chatbot project aligned with the five requirements in the CodeAlpha Java Programming task:
1. Java-based interactive chatbot
2. NLP-style text preprocessing
3. Rule-based response engine (with a simple intent-scoring approach)
4. Built-in FAQ knowledge base
5. Swing GUI for real-time interaction

## Requirements
- JDK 17 or newer (JDK 26 is also fine)
- IntelliJ IDEA, Eclipse, or another Java IDE
- No third-party dependencies required

## Run in IntelliJ IDEA
1. Extract the ZIP.
2. Open the extracted `CodeAlpha_AI_Chatbot_2026` folder in IntelliJ IDEA.
3. Ensure the project SDK is set to JDK 17+.
4. Open `src/main/java/com/codealpha/chatbot/Main.java`.
5. Run `Main.main()`.

## Run from Terminal
From the project root:

```bash
javac -d out src/main/java/com/codealpha/chatbot/*.java
java -cp out com.codealpha.chatbot.Main
```

## Features
- Swing chat window with scrollable conversation
- Welcome message and message timestamps
- NLP-style preprocessing: lowercase normalization, punctuation cleanup, tokenization, and stop-word filtering
- Rule-based intent matching using keyword overlap and scores
- FAQ answers for greetings, capabilities, Java, NLP, machine learning, college/project guidance, and help
- Fallback response for unknown questions
- Clear chat button and Enter-to-send
- No API key, internet connection, database, or external model needed

## Project structure
```text
CodeAlpha_AI_Chatbot_2026/
├── src/main/java/com/codealpha/chatbot/
│   ├── Main.java
│   ├── ChatbotEngine.java
│   ├── NlpProcessor.java
│   ├── Intent.java
│   ├── FaqKnowledgeBase.java
│   └── ChatWindow.java
├── README.md
└── TASK_CHECKLIST.md
```

## How the "AI" logic works
This is a local rule-based chatbot with lightweight NLP preprocessing and intent scoring. It is not a trained neural network or a large language model. The task PDF allows "machine learning logic or rule-based answers," so the rule-based option is implemented. The FAQ knowledge base is hand-authored and can be extended in `FaqKnowledgeBase.java`.

## Customization
Add new FAQ entries in `FaqKnowledgeBase.java` with:
- an intent name
- keywords / example phrases
- a response

The intent matcher normalizes the user's message, scores keyword overlap, and selects the best matching intent above a minimum threshold.
