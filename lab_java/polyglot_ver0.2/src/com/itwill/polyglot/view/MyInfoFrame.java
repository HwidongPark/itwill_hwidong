package com.itwill.polyglot.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.polyglot.controller.PolyglotDao;
import com.itwill.polyglot.model.Account;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class MyInfoFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblTitle;
    private JLabel lblAccount;
    private JLabel lblPhone;
    private JLabel lblEmail;
    private JTextField textAccount;
    private JTextField textPhone;
    private JTextField textEmail;
    private JLabel lblPassword;
    private JPasswordField passwordField;
    private JPasswordField passwordConfirmField;
    private JButton btnUpdate;
    
    private Account accountSignedIn;
    private PolyglotDao dao = PolyglotDao.getInstance();
    private Component parent;
    
    /**
     * Launch the application.
     */
    public static void showMyInfoFrame(Component parent, Account accountSignedIn) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyInfoFrame frame = new MyInfoFrame(parent, accountSignedIn);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public MyInfoFrame(Component parent, Account accountSignedIn) {
        this.accountSignedIn = accountSignedIn;
        initialize(parent, accountSignedIn);
    }
    
    /**
     * Create the frame.
     */
    public void initialize(Component parent, Account accountSignedIn) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        int x = 100;
        int y = 100;
        if (parent != null) {
            x = parent.getX() + 50;
            y = parent.getY() + 50;
        }
        
        setBounds(x, y, 450, 500);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblTitle = new JLabel("내 정보");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("D2Coding", Font.BOLD, 20));
        lblTitle.setBounds(160, 23, 99, 37);
        contentPane.add(lblTitle);
        
        lblAccount = new JLabel("아이디:");
        lblAccount.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblAccount.setBounds(30, 83, 69, 25);
        contentPane.add(lblAccount);
        
        lblPhone = new JLabel("전화번호:");
        lblPhone.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblPhone.setBounds(30, 131, 80, 25);
        contentPane.add(lblPhone);
        
        lblEmail = new JLabel("이메일:");
        lblEmail.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblEmail.setBounds(30, 177, 69, 25);
        contentPane.add(lblEmail);
        
        textAccount = new JTextField();
        textAccount.setEditable(false);
        textAccount.setText(accountSignedIn.getAccount());
        textAccount.setFont(new Font("D2Coding", Font.PLAIN, 14));
        textAccount.setBounds(149, 82, 222, 26);
        contentPane.add(textAccount);
        textAccount.setColumns(10);
        
        textPhone = new JTextField();
        textPhone.setText(accountSignedIn.getPhone());
        textPhone.setFont(new Font("D2Coding", Font.PLAIN, 14));
        textPhone.setColumns(10);
        textPhone.setBounds(149, 131, 222, 26);
        contentPane.add(textPhone);
        
        textEmail = new JTextField();
        textEmail.setText(accountSignedIn.getEmail());
        textEmail.setFont(new Font("D2Coding", Font.PLAIN, 14));
        textEmail.setColumns(10);
        textEmail.setBounds(149, 177, 222, 26);
        contentPane.add(textEmail);
        
        JLabel lblNewLabel = new JLabel("*정보 수정을 원하시면 비밀번호를 입력해 주세요");
        lblNewLabel.setFont(new Font("D2Coding", Font.ITALIC, 10));
        lblNewLabel.setBounds(30, 246, 322, 15);
        contentPane.add(lblNewLabel);
        
        lblPassword = new JLabel("비밀번호:");
        lblPassword.setFont(new Font("D2Coding", Font.PLAIN, 13));
        lblPassword.setBounds(30, 271, 69, 25);
        contentPane.add(lblPassword);
        
        JLabel lblPassword_1 = new JLabel("비밀번호 확인:");
        lblPassword_1.setFont(new Font("D2Coding", Font.PLAIN, 13));
        lblPassword_1.setBounds(30, 317, 99, 25);
        contentPane.add(lblPassword_1);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("D2Coding", Font.PLAIN, 14));
        passwordField.setBounds(149, 271, 222, 26);
        contentPane.add(passwordField);
        
        passwordConfirmField = new JPasswordField();
        passwordConfirmField.setFont(new Font("D2Coding", Font.PLAIN, 14));
        passwordConfirmField.setBounds(149, 316, 222, 26);
        contentPane.add(passwordConfirmField);
        
        btnUpdate = new JButton("수정");
        btnUpdate.addActionListener((e) -> {
            String password = String.valueOf(passwordField.getPassword());
            String passwordConfirm = String.valueOf(passwordConfirmField.getPassword());
            String phoneToUpdate = textPhone.getText();
            String emailToUpdate = textEmail.getText();
            
            // 이미 존재여부 확인하기 위한 전체유져 리스트
            List<Account> accounts = dao.readAllAccounts();
            
            // 비밀번호 입력하지 않았을 경우 경고문
            if (password.equals("") || passwordConfirm.equals("")) {
                JOptionPane.showMessageDialog(MyInfoFrame.this, "비밀번호를 입력해 주세요", "업데이트 불가", JOptionPane.WARNING_MESSAGE);
                
                return;
            }
            
            // 비밀번호 일치하는지 확인
            if (!password.equals(passwordConfirm)) {
                JOptionPane.showMessageDialog(MyInfoFrame.this, "비밀번호가 서로 일치하지 않습니다.", "업데이트 불가", JOptionPane.WARNING_MESSAGE);
                
                return;
            }
            
            // 비밀번호가 일치하면 로그인 된 계정의 비밀번호와 일치하는지 검사
            if (!accountSignedIn.getPasssword().equals(password)) {
                JOptionPane.showMessageDialog(MyInfoFrame.this, "잘못된 비밀번호 입니다.", "업데이트 불가", JOptionPane.WARNING_MESSAGE);
                
                return;
            }
            
            // 전화번호/이메일 공백일 경우 null로 입력.
            if (phoneToUpdate.equals("")) {
                phoneToUpdate = null;
            }
            
            if (emailToUpdate.equals("")) {
                emailToUpdate = null;
            }
            
            // 전화번호/이메일 이미 존재하는지 확인
            // 내 계정 빼고 검사
            if (phoneToUpdate == null) {
                // 업데이트 할 전화번호가 null이라면 그냥 건너감
            } else {
                for (Account account : accounts) {  // 전화번호 검사
                    if (account.getPhone() != null && !accountSignedIn.getAccount().equals(account.getAccount())) {   // null이 아닌경우, 내 계정은 빼고 검사
                        if (account.getPhone().equals(phoneToUpdate)) { // 이미 존재하는 전화번호인 경우
                            JOptionPane.showMessageDialog(MyInfoFrame.this, "이미 존재하는 전화번호입니다.", "업데이트 불가", 
                                    JOptionPane.WARNING_MESSAGE);
                            
                            return;
                        }
                    }
                }
            }
            
            
            if (emailToUpdate == null) {
                // 업데이트 할 이메일이 null이라면 그냥 건너감
            } else {
                for (Account account : accounts) {  // 이메일 검사
                    if (account.getEmail() != null && !accountSignedIn.getAccount().equals(account.getAccount())) {   // null이 아닌 경우, 내 계정은 빼고 검사
                        if (account.getEmail().equals(emailToUpdate)) { // 이미 존재하는 이메일인 경우
                            JOptionPane.showMessageDialog(MyInfoFrame.this, "이미 존재하는 이메일입니다.", "업데이트 불가", 
                                    JOptionPane.WARNING_MESSAGE);
                            
                            return;
                        }
                    }
                }
            }
            
            // 전화번호/이메일 업데이트
            int result = dao.updatePhoneEmail(accountSignedIn, phoneToUpdate, emailToUpdate);
            System.out.println(result + "행 업데이트 성공");
            
            passwordField.setText("");
            passwordConfirmField.setText("");
            
        }); // 수정 버튼 action listener 끝
        btnUpdate.setFont(new Font("D2Coding", Font.BOLD, 15));
        btnUpdate.setBounds(274, 376, 99, 58);
        contentPane.add(btnUpdate);
    }
}