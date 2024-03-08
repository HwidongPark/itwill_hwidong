package com.itwill.swingpractice;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppMainPractice {

    private JFrame frame;
    private JTextField textFieldId;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AppMainPractice window = new AppMainPractice();
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
    public AppMainPractice() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblId = new JLabel("아이디");
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        lblId.setFont(new Font("D2Coding", Font.PLAIN, 15));
        lblId.setBounds(145, 160, 76, 24);
        frame.getContentPane().add(lblId);
        
        JLabel lblPassword = new JLabel("비밀번호");
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setFont(new Font("D2Coding", Font.PLAIN, 15));
        lblPassword.setBounds(145, 194, 76, 24);
        frame.getContentPane().add(lblPassword);
        
        textFieldId = new JTextField();
        textFieldId.setBounds(247, 157, 152, 24);
        frame.getContentPane().add(textFieldId);
        textFieldId.setColumns(10);
        
        JLabel lblLogo = new JLabel("가짜 인스타");
        lblLogo.setFont(new Font("궁서체", Font.BOLD, 30));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setBounds(160, 48, 232, 69);
        frame.getContentPane().add(lblLogo);
        
        JButton btnSignIn = new JButton("Sign In");
        btnSignIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 로그인 버튼 기능
                String signInID = textFieldId.getText();
//                char[] signInPassword = passwordField.getPassword();
                
            }
        });
        btnSignIn.setBounds(234, 259, 97, 23);
        frame.getContentPane().add(btnSignIn);
        
        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 회원가입 버튼 기능
                
            }
        });
        btnSignUp.setBounds(125, 365, 97, 23);
        frame.getContentPane().add(btnSignUp);
        
        JButton btnFindIdPassword = new JButton("Find ID / Password");
        btnFindIdPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 아이디/비밀번호 찾기 기능
                
            }
        });
        btnFindIdPassword.setBounds(247, 365, 152, 23);
        frame.getContentPane().add(btnFindIdPassword);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(247, 196, 152, 21);
        frame.getContentPane().add(passwordField);
        
        
    }
}
