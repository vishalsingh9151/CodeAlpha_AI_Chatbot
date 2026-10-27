package com.codealpha.chatbot;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Swing's default look and feel will be used if the system one is unavailable.
            }

            ChatWindow window = new ChatWindow();
            window.setVisible(true);
        });
    }
}
