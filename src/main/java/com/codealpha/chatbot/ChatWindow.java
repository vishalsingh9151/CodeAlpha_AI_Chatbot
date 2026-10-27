package com.codealpha.chatbot;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatWindow extends JFrame {
    private final ChatbotEngine chatbot = new ChatbotEngine();
    private final JPanel messagesPanel = new JPanel();
    private final JScrollPane scrollPane;
    private final JTextField inputField = new JTextField();
    private final JButton sendButton = new JButton("Send");
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");

    public ChatWindow() {
        super("CodeAlpha AI Chatbot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(760, 620);
        setMinimumSize(new Dimension(560, 440));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);

        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        messagesPanel.setBackground(new Color(245, 247, 250));
        messagesPanel.setBorder(new EmptyBorder(14, 14, 14, 14));

        scrollPane = new JScrollPane(messagesPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
        add(createComposer(), BorderLayout.SOUTH);

        appendMessage("CodeAlpha Bot",
                "Hello! 👋 I'm your Java chatbot. Ask me about Java, OOP, NLP, machine learning, or this project.",
                false);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout(12, 4));
        header.setBackground(new Color(35, 47, 75));
        header.setBorder(new EmptyBorder(16, 20, 16, 20));

        JLabel title = new JLabel("CodeAlpha AI Chatbot");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 21));

        JLabel subtitle = new JLabel("Java • Basic NLP • Rule-based FAQ");
        subtitle.setForeground(new Color(215, 223, 238));
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));

        JPanel labels = new JPanel();
        labels.setOpaque(false);
        labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
        labels.add(title);
        labels.add(Box.createVerticalStrut(4));
        labels.add(subtitle);

        JButton clearButton = new JButton("Clear chat");
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(e -> {
            messagesPanel.removeAll();
            messagesPanel.revalidate();
            messagesPanel.repaint();
            appendMessage("CodeAlpha Bot", "Chat cleared. What would you like to ask?", false);
        });

        header.add(labels, BorderLayout.CENTER);
        header.add(clearButton, BorderLayout.EAST);
        return header;
    }

    private JPanel createComposer() {
        JPanel composer = new JPanel(new BorderLayout(10, 0));
        composer.setBorder(new EmptyBorder(12, 14, 14, 14));
        composer.setBackground(Color.WHITE);

        inputField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(205, 211, 220)),
                new EmptyBorder(10, 12, 10, 12)));

        sendButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        sendButton.setForeground(Color.WHITE);
        sendButton.setBackground(new Color(49, 91, 170));
        sendButton.setFocusPainted(false);
        sendButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        sendButton.setOpaque(true);

        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());

        composer.add(inputField, BorderLayout.CENTER);
        composer.add(sendButton, BorderLayout.EAST);
        return composer;
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (message.isEmpty()) return;

        inputField.setText("");
        appendMessage("You", message, true);

        // Keep UI responsive if response logic becomes more complex in the future.
        sendButton.setEnabled(false);
        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() {
                return chatbot.getResponse(message);
            }

            @Override
            protected void done() {
                try {
                    appendMessage("CodeAlpha Bot", get(), false);
                } catch (Exception ex) {
                    appendMessage("CodeAlpha Bot", "Sorry, something went wrong. Please try again.", false);
                } finally {
                    sendButton.setEnabled(true);
                    inputField.requestFocusInWindow();
                }
            }
        };
        worker.execute();
    }

    private void appendMessage(String sender, String text, boolean fromUser) {
        JPanel row = new JPanel(new FlowLayout(fromUser ? FlowLayout.RIGHT : FlowLayout.LEFT, 0, 5));
        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel bubble = new JPanel();
        bubble.setLayout(new BoxLayout(bubble, BoxLayout.Y_AXIS));
        bubble.setBorder(new EmptyBorder(9, 12, 9, 12));
        bubble.setBackground(fromUser ? new Color(218, 232, 255) : Color.WHITE);
        bubble.setMaximumSize(new Dimension(500, Integer.MAX_VALUE));

        JLabel name = new JLabel(sender + "  ·  " + LocalDateTime.now().format(timeFormat));
        name.setFont(new Font("SansSerif", Font.BOLD, 11));
        name.setForeground(new Color(85, 94, 110));

        JTextArea body = new JTextArea(text);
        body.setEditable(false);
        body.setFocusable(false);
        body.setLineWrap(true);
        body.setWrapStyleWord(true);
        body.setOpaque(false);
        body.setFont(new Font("SansSerif", Font.PLAIN, 14));
        body.setForeground(new Color(35, 40, 50));
        body.setColumns(42);
        body.setRows(Math.max(1, (int) Math.ceil(text.length() / 52.0)));
        body.setMaximumSize(new Dimension(470, Integer.MAX_VALUE));

        bubble.add(name);
        bubble.add(Box.createVerticalStrut(5));
        bubble.add(body);
        row.add(bubble);
        messagesPanel.add(row);
        messagesPanel.add(Box.createVerticalStrut(3));
        messagesPanel.revalidate();
        messagesPanel.repaint();

        SwingUtilities.invokeLater(() -> {
            JScrollBar bar = scrollPane.getVerticalScrollBar();
            bar.setValue(bar.getMaximum());
        });
    }
}
