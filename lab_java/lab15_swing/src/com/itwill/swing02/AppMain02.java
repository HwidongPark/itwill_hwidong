package com.itwill.swing02;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppMain02 {
    
    // field
    private JFrame frame;
    private JTextField textInput;
    private JLabel lblMessage;
    private JButton btnInput;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AppMain02 window = new AppMain02();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public AppMain02() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null); //absolute layout(절대 레이아웃)
        
        lblMessage = new JLabel("메세지를 입력하세요...");
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblMessage.setFont(new Font("D2Coding", Font.BOLD | Font.ITALIC, 24));
        lblMessage.setBounds(12, 10, 410, 60);
        frame.getContentPane().add(lblMessage);
        
        textInput = new JTextField();
        textInput.setFont(new Font("D2Coding", Font.PLAIN, 24));
        textInput.setBounds(32, 80, 369, 71);
        frame.getContentPane().add(textInput);
        textInput.setColumns(10);
        
        btnInput = new JButton("입력");
        btnInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 버튼을 클릭했을 때 수행할 기능 작성.
                handleButtonClick();
            }
            
        });
        btnInput.setFont(new Font("Dialog", Font.BOLD, 18));
        btnInput.setBounds(149, 200, 140, 51);
        frame.getContentPane().add(btnInput);
    }
    
    
    public void handleButtonClick() {
        // 1. JTextField에 입력된 문자열을 읽음.
        String msg = textInput.getText();
        // 2. 1에서 읽은 문자열을 JLabel에 씀.
        lblMessage.setText(msg);
        // 3. JTextField를 지운다.
        textInput.setText("");
    }
    
}
