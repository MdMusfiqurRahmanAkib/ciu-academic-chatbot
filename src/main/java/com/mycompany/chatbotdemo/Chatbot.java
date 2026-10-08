package com.mycompany.chatbotdemo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.Desktop;
import java.io.File;
import javax.swing.JOptionPane;
//import java.util.Map;
//import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.List;
//import java.io.IOException;

public class Chatbot extends JFrame {

    private JTextArea chatArea = new JTextArea();
    private JScrollPane scrollPane;
    private JTextField inputField = new JTextField();
    private JButton sendButton = new JButton("SEND");
    private JButton exitButton = new JButton("EXIT");
    //JButton faqButton = new JButton("FAQ");

     private boolean isTypingAnimationRunning = false;

    Chatbot() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("CIU SSE Academic Chatbot");
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(23, 32, 42));


        JPanel titlePanel = new JPanel();
        titlePanel.setBounds(0, 0, getWidth(), 100);
        titlePanel.setBackground(new Color(23, 32, 42));
        titlePanel.setLayout(new BorderLayout());


        ImageIcon originalLogoIcon = new ImageIcon(Chatbot.class.getResource("/images/ciu_logo.png"));
        Image resizedLogoImage = originalLogoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedLogoIcon = new ImageIcon(resizedLogoImage);
        JLabel logoLabel = new JLabel(resizedLogoIcon);
        logoLabel.setBounds(90, 10, 60, 80);
        titlePanel.add(logoLabel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("             CIU SSE Academic Chatbot");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 42));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        titlePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        add(titlePanel);

        chatArea.setBackground(new Color(44, 62, 80));
        chatArea.setForeground(Color.WHITE);
        chatArea.setCaretColor(Color.WHITE);
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
        scrollPane = new JScrollPane(chatArea);
        scrollPane.setBounds(50, 120, 690, 350);
        add(scrollPane);

        inputField.setBounds(50, 490, 416, 50);
        inputField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        inputField.setText("typing here...");
        inputField.setForeground(Color.GRAY);


        sendButton.setBounds(660, 490, 80, 50);
        sendButton.setBackground(new Color(34, 67, 100));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
        exitButton.setBounds(570, 490, 80, 50);
        exitButton.setBackground(new Color(214, 69, 65));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
        JButton faqButton = new JButton("FAQ");
        faqButton.setBounds(480, 490, 80, 50);
        faqButton.setBackground(new Color(0, 0, 0));
        faqButton.setForeground(Color.WHITE);
        faqButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(Chatbot.this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    showRatingDialog();
                    System.exit(0);
                }
            }
        });
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        inputField.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            if (inputField.getText().equals("typing here...")) {
                inputField.setText("");
                inputField.setForeground(Color.BLACK);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (inputField.getText().isEmpty()) {
                inputField.setText("typing here...");
                inputField.setForeground(Color.GRAY);
            }
        }

    });



        faqButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayFAQCategories();
        }
    });

        add(faqButton);

        setLayout(null);
        add(inputField);
        add(sendButton);
        add(exitButton);
        //add(faqButton);
        setVisible(true);


    }


private void displayFAQCategories() {
    String[] categoryOptions = {"General", "Programming", "Other Categories"};

    JPanel categoryPanel = new JPanel();
    categoryPanel.setBackground(Color.WHITE);
    categoryPanel.setLayout(new GridLayout(0, 1, 0, 10));

    for (String category : categoryOptions) {
        JButton categoryButton = new JButton(category);
        categoryButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
        categoryButton.setForeground(Color.BLACK);
        categoryButton.setBackground(new Color(240, 240, 240));
        categoryButton.setPreferredSize(new Dimension(220, 40));
        categoryButton.addActionListener(e -> displayCategoryQuestions(category));
        categoryPanel.add(categoryButton);
    }

    JScrollPane scrollPane = new JScrollPane(categoryPanel);
    scrollPane.setPreferredSize(new Dimension(260, 300));

    UIManager.put("OptionPane.background", Color.WHITE);
    UIManager.put("OptionPane.messageForeground", Color.BLACK);
    UIManager.put("Panel.background", Color.WHITE);
    UIManager.put("OptionPane.messageFont", new Font("Yu Gothic UI", Font.BOLD, 18));

    JOptionPane.showMessageDialog(this, scrollPane, "FAQ Categories", JOptionPane.PLAIN_MESSAGE);
}





private void displayCategoryQuestions(String category) {
    JPanel questionPanel = new JPanel();
    questionPanel.setBackground(Color.WHITE);
    questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));

    if (category.equals("General"))
    {
        addQuestionAnswer(questionPanel, "What is the purpose of this chatbot?", "The purpose of this chatbot is to assist users with common questions and provide information about CIU SSE.");
        addQuestionAnswer(questionPanel, "How do I access the course offer list?", "You can access the course offer list by visiting the official CIU SSE website and navigating to the relevant section.");
    }
    else if (category.equals("Programming"))
    {
        addQuestionAnswer(questionPanel, "What is Object-Oriented Programming?", "Object-Oriented Programming (OOP) is a programming paradigm that uses objects to represent and manipulate data and behavior.");
        addQuestionAnswer(questionPanel, "How do I declare a variable in Java?", "You can declare a variable in Java using the following syntax: \n\n" +
                                                      "```java\n" +
                                                      "data_type variableName;\n" +
                                                      "```");
    }
    else if (category.equals("Other Categories"))
    {

    }

    JScrollPane scrollPane = new JScrollPane(questionPanel);
    scrollPane.setPreferredSize(new Dimension(600, 500)); // Adjust dimensions

    JOptionPane.showMessageDialog(this, scrollPane, "FAQ Questions", JOptionPane.PLAIN_MESSAGE);
}

private void addQuestionAnswer(JPanel panel, String question, String answer) {
    JTextArea questionArea = new JTextArea(question);
    questionArea.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
    questionArea.setWrapStyleWord(true);
    questionArea.setLineWrap(true);
    questionArea.setEditable(false);
    questionArea.setBackground(Color.WHITE);
    questionArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    panel.add(questionArea);

    JTextArea answerArea = new JTextArea(answer);
    answerArea.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
    answerArea.setWrapStyleWord(true);
    answerArea.setLineWrap(true);
    answerArea.setEditable(false);
    answerArea.setBackground(Color.WHITE);
    answerArea.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 5));
    panel.add(answerArea);
}
    private void sendMessage() {
        String text = inputField.getText();
        chatArea.append("You --> " + text + "\n");
        inputField.setText("");

        String reply = generateReply(text);
        replyMeth(reply);
    }

    private String generateReply(String userMessage) {
        String reply = "    Sorry, I cannot understand you at the moment.\n\n"
                + "\tI'm currently under training and not yet fully functional.";


        /*
        else if (containsKeyword(userMessage, "habib"))
        {
            reply = "I'm the CIU SSE Academic Chatbot.";
        }
        */
        if (containsKeyword(userMessage, "scholarship"))
        {
            reply = "CIU also offers Merit Scholarship/ Tuition fees scholarship in the following categories:\n" +
            "\n" +
            "Academic Board Merit Scholarship (10% to 100%)\n" +
            "For National Curriculum Students:\n" +
            "Assessment Bar for the Scholarships based on their academic board results (SSC & HSC examination) are as follows:\n" +
            "\n" +
            "GPA total in SSC & HSC	Scholarship on tuition fees\n" +
            "10	    100%\n" +
            "9  and above	50%\n" +
            "8  and above	25%\n" +
            "7  and above	10%\n" +
            "\n" +
            "For English Medium Curriculum Students:\n" +
            "\n" +
            "Students who have earned at least 3 (Three) “A” grades together in O’& A’ level examinations are eligible to get 100% tuition fees Scholarship.\n" +
            "\n" +
            "Academic Board Merit Scholarship will be awarded only for the first semester/ term.\n" +
            "\n" +
            "Merit based Scholarship (for CIU students based on CGPA):\n" +
            "\n" +
            "Scholarships based on CIU CGPA is mentioned below:\n" +
            "CGPA	Scholarship on tuition fees\n" +
            "3.95 – 4.0	100%\n" +
            "3.85 – 3.94	75%\n" +
            "3.75 – 3.84	50%\n" +
            "3.65 - 3.74	25%\n" +
            "\n" +
            "Need cum merit based financial aid (10% to 75%). * Conditions apply\n" +
            "\n" +
            " 100% Offspring of Freedom Fighter Scholarship.\n" +
            "\n" +
            " 100% Scholarship of Ethnic Minorities/Students from Remote Areas.\n" +
            "\n" +
            " 50% Siblings tuition fees scholarship\n" +
            "\n" +
            " 50% Scholarship of CIU employee/dependent category.\n" +
            "";
        }
        else if (containsKeyword(userMessage, "hi"))
        {
            reply = "Hello! Assalamualaikum!! I'm the CIU SSE Academic Chatbot.\n\n"
                    + "\tI'm currently under training and not yet fully functional.";
        }
        else if (containsKeyword(userMessage, "habib"))
        {
            reply = "\tIntroducing HABIBUR RAHAMAN Sir!\n\n\t\tOUR PROGRAMMING GURU :)\n\n\"Guiding us through the intricacies of programming, our coding sage, Habib Sir, leads us to new heights of knowledge and skill.\"\n\n";
            openPDFFile("documents/faculty-habibur-rahaman.pdf");
        }

        else if (containsKeyword(userMessage, "hello"))
        {
            reply = "Hello! Assalamualaikum!! I'm the CIU SSE Academic Chatbot.\n\n"
                    + "\tI'm currently under training and not yet fully functional.";
        }
        /*
        if (containsKeyword(userMessage, "habib"))
        {
            reply = "\tIntroducing Habibur Rahman Sir!\nyour go-to programming guru, ready to unravel the world of code and guide you through the exciting journey of programming.\n\n"
                    + "Habibur Rahman\n" +
            "Lecturer\n" +
            "Department of Computer Science and Engineering\nContact: +88-02333352926, 02333351262; Ext: 320 +88 01839341794 habibcuetcse@ciu.edu.bd Room No# 2502, 4th Floor, School of Science and Engineering (SSE), Roksana Manzil, Chittagong Independent University\n" +
            "Degree Name	\tInstitute Name	\tYear\n" +
            "M.Sc. Engg(pursuing)	Chittagong University of Engineering and Technology (CUET)	------\n" +
            "B.Sc. Engg(Computer Science and Engineering)	Chittagong University of Engineering and Technology (CUET)	2017\n" +
            "\nSubject Taught: Structured Programming, Data Structure, Discrete Mathematics, Object-Oriented Programming, Algorithm, Numerical Analysis, Theory of Computation, Compiler Design, Computer Graphics.\n" +
            "\n" +
            "Academic Experience:\n" +
            "Lecturer, Computer Science and Engineering, Chittagong Independent University (CIU). September 2017 – Present.\n" +
            "\n" +
            "Interested Reasearch Area: Data mining, Machine learning, stream data analysis, block chain, Web and Apps application.\n" +
            "\n" +
            " Training & Certification: \nNational Mobile Application Trainer and Innovative Application Development Program, Govt. ICT ministry.\n" +
            "\n" +
            " Top-Up IT Training on Dot Net offer by Govt. ICT ministry.\n" +
            "\n" +
            " Web development from IICT, CUET.\n" +
            "\n" +
            "SL.No	Publication / Presentation title	Author(s) name	Name of journal/ conference	P. month and year\n" +
            "1.	An Empirical Framework for Spatial Diseases Analysis and Patient Care Recommendation.	Mohammad Obaidur Rahman, Md. Shafiul Alam Forhad, Md. Sabir Hossain, Habibur Rahman	5th International Conference on Natural Sciences and Technology (ICNST’18), Asian University for Women, Chittagong, Bangladesh	March 30 - 31, 2018\n" +
            "2.	An Overview of Blockchain to Sort Society.(Vol.5, Issue 2)	Sritha zith Dey Babu, Habibur Rahaman,Digvijay Pandey	International Jounal of Business Educaton and Management studies (IJBEMS)	2020 (May)\n" +
            "2.	Acting tools of ICT to tackle Covid19 (Vol.10, Issue 1)	Sritha zith dey babu, Habibur Rahaman, Forkan Uddin Ahmed, Digvijay Pandey	OmniScience: A Multi-disciplinary Journal	STM Journals 2020\n" +
            "2.	An Overview of A Crime Detection System using the Art of Data Mining (Vol.65, Issue 89)	SrithaZithDey Babu, Digvijay Pandey, Habibur Rahman, Ismail Sheik	International Jounal of Business Educaton and Management studies (IJBEMS)	May 2020\n" +
            "\n" +
            "\n" +
            "   ";
        }
*/

        /*
        else if (containsKeyword(userMessage, "hi"))
        {
            reply = "Hello! Assalamualaikum!! I'm the CIU SSE Academic Chatbot.\n\n"
                    + "\tI'm currently under training and not yet fully functional.";
        }
        */
        /*
        else if (containsKeyword(userMessage, "course offer list"))
        {
            reply = "Here is the PDF file you requested!";
            openPDFFile("documents/course-offer-list.pdf");
        }
        */
        else if (containsKeyword(userMessage, "cse 101"))
        {
            reply = "HERE IS THE COURSE OUTLINE";
            openDocxFile("documents/cse-101.docx");
        }
        else if (containsKeyword(userMessage, "course"))
        {
            reply = "Here is the PDF file you requested!";
            openPDFFile("documents/course-offer-list.pdf");
        }
        else if (containsKeyword(userMessage, "faculty"))
        {
            reply = "LIST OF FACULTY MEMBER";
            openPDFFile("documents/sse-faculty-list.pdf");
        }
        else if (containsKeyword(userMessage, "tution"))
        {
            reply = "TUTION FEES DETAILS";
            openPDFFile("documents/tuition-fees.pdf");
        }

        else if (containsKeyword(userMessage, "cse 225"))
        {
            reply = "\tOBJECT ORIENTED PROGRAMMING LAB REPORT TEMPLATE\n\n\t DOWNLOAD & EDIT AS YOU WANT";
            openDocxFile("documents/oop-lab-report-template.docx");
        }
        else if (containsKeyword(userMessage, "grading"))
        {
            reply = "\tHERE IS THE GRADING POLICY";
            openPDFFile("documents/grading-policy.pdf");
        }
        else if (containsKeyword(userMessage, "rezaul"))
        {
            reply = "\tDr. Mohammad Rezaul Karim Sir: A visionary leader who turns challenges into opportunities with grace and determination.";
            openPDFFile("documents/faculty-dean.pdf");
        }
        else if (containsKeyword(userMessage, "dean"))
        {
            reply = "\tDr. Mohammad Rezaul Karim Sir: A visionary leader who turns challenges into opportunities with grace and determination.";
            openPDFFile("documents/faculty-dean.pdf");
        }
        else if (containsKeyword(userMessage, "samia"))
        {
            reply = "\tSamia Muntaha Mam: A visionary leader who turns challenges into opportunities with grace and determination.";
            openPDFFile("documents/faculty-samia.pdf");
        }
        else if (containsKeyword(userMessage, "statistics"))
        {
            reply = "\tHERE IS THE BOOK";
            openPDFFile("documents/statistics.pdf");
        }

        else if (containsKeyword(userMessage, "risul"))
        {
            reply = "\tRisul Islam Rasel Sir: A visionary leader who turns challenges into opportunities with grace and determination.";
            openPDFFile("documents/faculty-risul.pdf");
        }
        else if (containsKeyword(userMessage, "rubel"))
        {
            reply = "\tDr. Rubell Sen Goopta Sir: A visionary leader who turns challenges into opportunities with grace and determination.";
            openPDFFile("documents/faculty-rubel.pdf");
        }
        else if (containsKeyword(userMessage, "aseef"))
        {
            reply = "\tDr. Aseef Iqbal Sir: A visionary leader who turns challenges into opportunities with grace and determination.";
            openPDFFile("documents/faculty-aseef.pdf");
        }
        else if (containsKeyword(userMessage, "atiq"))
        {
            reply = "\tAtiqur Rahman Sir: A visionary leader who turns challenges into opportunities with grace and determination.";
            openPDFFile("documents/faculty-atiq.pdf");
        }
        else if (containsKeyword(userMessage, "sajjat"))
        {
            reply = "\tSajjatul Islam Sir: A visionary leader who turns challenges into opportunities with grace and determination.";
            openPDFFile("documents/faculty-sajjat.pdf");
        }
        else if (containsKeyword(userMessage, "rakayet"))
        {
            reply = "\tRakayet Rafi Sir: A visionary leader who turns challenges into opportunities with grace and determination.";
            openPDFFile("documents/faculty-rakayet.pdf");
        }
        else if (containsKeyword(userMessage, "head"))
        {
            reply = "\tRisul Islam Rasel Sir: A visionary leader who turns challenges into opportunities with grace and determination.";
            openPDFFile("documents/faculty-risul.pdf");
        }
        else if (containsKeyword(userMessage, "moumita"))
        {
            reply = "\tHere is the details";
            openPDFFile("documents/faculty-moumita.pdf");
        }
        else if (containsKeyword(userMessage, "manager"))
        {
            reply = "\tHere is the details";
            openPDFFile("documents/faculty-moumita.pdf");
        }
        else if (containsKeyword(userMessage, "project report"))
        {
            reply = "\tHERE IS THE PROJECT REPORT FORMAT";
            openPDFFile("documents/project-report-format.pdf");
        }
        else if (containsKeyword(userMessage, "project"))
        {
            reply = "\tHERE IS THE PROJECT PROPOSAL FORMAT";
            openDocxFile("documents/project-proposal.docx");
        }
        return reply;
    }
    private void openPDFFile(String filePath) {
    try {
        File pdfFile = new File(filePath);
        if (pdfFile.exists()) {
            Desktop.getDesktop().open(pdfFile);
        } else {
            System.out.println("PDF file not found at: " + filePath);
        }
    } catch (Exception e) {
        System.out.println("Error opening PDF file: " + e.getMessage());
    }
}
    private void openDocxFile(String filePath) {
    try {
        File docxFile = new File(filePath);
        if (docxFile.exists()) {
            Desktop.getDesktop().open(docxFile);
        } else {
            System.out.println("DOCX file not found at: " + filePath);
        }
    } catch (Exception e) {
        System.out.println("Error opening DOCX file: " + e.getMessage());
    }
}

    /*
    private boolean containsKeywordWithDigits(String input, String keyword) {
        // Regular expression to match the keyword with optional digits
        String pattern = "\\b" + keyword + "\\s*\\d*\\b";
        return input.matches("(?i)" + pattern);
    }
*/

    private boolean containsKeyword(String text, String keyword) {
        return text.toLowerCase().contains(keyword);
    }

    /*  ... 3 times - LIKE MESSANGER
    public void replyMeth(String s) {
    chatArea.append("Chatbot --> ");

    Timer timer = new Timer(250, new ActionListener() {
        int dots = 1;

        @Override
        public void actionPerformed(ActionEvent e) {
            chatArea.setText(chatArea.getText() + ".");
            dots++;

            if (dots > 3) {
                ((Timer) e.getSource()).stop();
                //chatArea.append(s + "\n\n");
                chatArea.setText(chatArea.getText().replace("...", s));
            }
        }
    });

    timer.start();
}
*/
    public void replyMeth(String s) {
    //Font italicFont = new Font("Tahoma", Font.ITALIC, 18);
    String typingMessage = "\t\tChatbot is typing...";
    String replyMessage = "\nChatbot --> " + s + "\n\n";

    // Display "Bot is typing" message
    chatArea.append(typingMessage);

    Timer typingTimer = new Timer(700, new ActionListener() {
        boolean isTypingDisplayed = true;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isTypingDisplayed) {
                chatArea.setText(chatArea.getText().replace(typingMessage, ""));
            } else {
                chatArea.append(replyMessage);
                ((Timer) e.getSource()).stop();
            }
            isTypingDisplayed = !isTypingDisplayed;
        }
    });

    typingTimer.start();
}

    private void showRatingDialog() {
        String[] options = {"1 Star", "2 Stars", "3 Stars", "4 Stars", "5 Stars"};
        int choice = JOptionPane.showOptionDialog(Chatbot.this, "Please rate your experience:", "Rate Chatbot", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[4]);
        if (choice >= 0) {
            int rating = choice + 1;
            JOptionPane.showMessageDialog(Chatbot.this, "Thank you for rating " + rating + " stars!");
        }
    }
     private void startTypingAnimation() {
        isTypingAnimationRunning = true;

        Timer animationTimer = new Timer(10, new ActionListener() {
            int position = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                position += 5;

                if (position >= inputField.getWidth()) {
                    ((Timer) e.getSource()).stop();
                    inputField.setText("");
                    inputField.setForeground(Color.BLACK);
                    isTypingAnimationRunning = false;
                } else {
                    String placeholder = "typing here...";
                    inputField.setText("<html><div style='text-align: left; width: " + inputField.getWidth() + "px; padding-left: " + position + "px;'>"
                            + "<font color='gray'>" + placeholder.substring(0, position / 10) + "</font>"
                            + "<font color='black'>" + placeholder.substring(position / 10) + "</font></div></html>");
                }
            }
        });

        animationTimer.start();
    }
    /* For POPUP WINDOW
    public void replyMeth(String s) {
    chatArea.append("Chatbot --> " + s + "\n\n");

    // Show a popup message with the bot's reply
    JOptionPane.showMessageDialog(this, s, "Chatbot Reply", JOptionPane.INFORMATION_MESSAGE);
}

    */
}


