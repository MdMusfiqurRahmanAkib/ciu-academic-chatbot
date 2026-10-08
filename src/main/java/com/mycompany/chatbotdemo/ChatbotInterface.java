package com.mycompany.chatbotdemo;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatbotInterface extends JFrame {
    private JButton startButton = new JButton("Start Chatbot");
    private ImageIcon backgroundImage;

    public ChatbotInterface() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Chatbot Interface");
        setSize(600, 600);
        setLocationRelativeTo(null);


        backgroundImage = new ImageIcon(ChatbotInterface.class.getResource("/images/welcome.png"));

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel.setLayout(new BorderLayout());

        //JLabel titleLabel = new JLabel("Welcome to the Chatbot");
        //titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        //titleLabel.setHorizontalAlignment(JLabel.CENTER);

        startButton.setFont(new Font("Roboto", Font.BOLD, 18));
        startButton.setBackground(new Color(52, 152, 219));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openChatbot();
            }
        });

       // panel.add(titleLabel, BorderLayout.CENTER);
        panel.add(startButton, BorderLayout.SOUTH);

        add(panel);


        startButton.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    openChatbot();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        setVisible(true);
    }

    private void openChatbot() {
        dispose();
        new Chatbot();
    }


}
