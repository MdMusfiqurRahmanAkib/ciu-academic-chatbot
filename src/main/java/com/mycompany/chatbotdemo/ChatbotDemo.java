
package com.mycompany.chatbotdemo;

import javax.swing.SwingUtilities;


public class ChatbotDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatbotInterface();
            }
        });
    }
}

