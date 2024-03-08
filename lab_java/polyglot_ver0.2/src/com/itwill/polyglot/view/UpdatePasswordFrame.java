package com.itwill.polyglot.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.polyglot.controller.PolyglotDao;
import com.itwill.polyglot.model.Account;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;

public class UpdatePasswordFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblTitle;
    private JTextField textAccount;
    private JLabel lblAccount;
    private JPasswordField passwordField;
    private JPasswordField passwordConfirmField;
    private JButton btnChangePassword;
    private JPasswordField passwordToUpdateField;
    private JLabel lblPasswordToUpdateConfirm;
    private JPasswordField passwordToUpdateConfirmField;
    
    private PolyglotDao dao = PolyglotDao.getInstance();
    Component parent;

    /**
     * Launch the application.
     */
    public static void showUpdatePasswordFrame(Component parent) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdatePasswordFrame frame = new UpdatePasswordFrame(parent);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public UpdatePasswordFrame(Component parent) {
        this.parent = parent;
        initialize(parent);
    }
    
    /**
     * Create the frame.
     */
    public void initialize(Component parent) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setResizable(false);
        
        int x = 100;
        int y = 100;
        
        if (parent != null) {
            x = parent.getX() + 50;
            y = parent.getY() + 50;
        }
        
        setBounds(x, y, 450, 530);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblTitle = new JLabel("비밀번호 변경");
        lblTitle.setFont(new Font("D2Coding", Font.BOLD, 20));
        lblTitle.setBounds(143, 10, 138, 40);
        contentPane.add(lblTitle);
        
        lblAccount = new JLabel("아이디:");
        lblAccount.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblAccount.setBounds(55, 95, 81, 22);
        contentPane.add(lblAccount);
        
        textAccount = new JTextField();
        textAccount.setFont(new Font("D2Coding", Font.PLAIN, 14));
        textAccount.setBounds(185, 96, 210, 26);
        contentPane.add(textAccount);
        textAccount.setColumns(10);
        
        JLabel lblPassword = new JLabel("비밀번호:");
        lblPassword.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblPassword.setBounds(55, 152, 81, 22);
        contentPane.add(lblPassword);
        
        JLabel lblPasswordConfirm = new JLabel("비밀번호 확인:");
        lblPasswordConfirm.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblPasswordConfirm.setBounds(55, 211, 106, 22);
        contentPane.add(lblPasswordConfirm);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("D2Coding", Font.PLAIN, 14));
        passwordField.setBounds(185, 148, 210, 26);
        contentPane.add(passwordField);
        
        passwordConfirmField = new JPasswordField();
        passwordConfirmField.setFont(new Font("D2Coding", Font.PLAIN, 14));
        passwordConfirmField.setBounds(185, 208, 210, 26);
        contentPane.add(passwordConfirmField);
        
        // 비밀번호 변경 버튼
        btnChangePassword = new JButton("비밀번호 변경");
        btnChangePassword.addActionListener((e) -> {    // 비밀번호 변경 버튼 ActionListeer
            // 채우지 않은 곳이 있는지 확인
            String accountUserInput = textAccount.getText();
            String passwordCurrent = String.valueOf(passwordConfirmField.getPassword());
            String passwordCurrentConfirm = String.valueOf(passwordConfirmField.getPassword());
            String passwordToUpdate = String.valueOf(passwordToUpdateField.getPassword());
            String passwordToUpdateConfirm = String.valueOf(passwordToUpdateConfirmField.getPassword());
            
            if (accountUserInput.equals("") || passwordCurrent.equals("") || passwordCurrentConfirm.equals("") 
                    || passwordToUpdate.equals("") || passwordToUpdateConfirm.equals("")) {
                JOptionPane.showMessageDialog(UpdatePasswordFrame.this, "입력하지 않은 란이 존재합니다.", "비밀번호 변경 불가",
                        JOptionPane.WARNING_MESSAGE);
            }
            
            
            // 비밀번호가 서로 일치하는지 확인
            if (!passwordCurrent.equals(passwordCurrentConfirm)) {
                JOptionPane.showMessageDialog(UpdatePasswordFrame.this, "비밀번호가 서로 일치하지 않습니다.", "비밀번호 변경 불가",
                        JOptionPane.WARNING_MESSAGE);
                
                return;
            }
            
            // 유저가 입력한 계정이 존재하는지 확인
            Account accountMatchingId = null;
            accountMatchingId = dao.findAccountWithId(textAccount.getText());
            
            if (accountMatchingId == null) {
                JOptionPane.showMessageDialog(UpdatePasswordFrame.this, "계정 정보를 찾을 수 없습니다.",
                        "비밀번호 변경 불가", JOptionPane.WARNING_MESSAGE);
                
                return;
            }
            
            // 존재한다면 비밀번호가 일치하는지 확인
            if (!accountMatchingId.getPasssword().equals(passwordCurrent)) {    // 비밀번호가 일치하지 않는 경우
                JOptionPane.showMessageDialog(UpdatePasswordFrame.this, "계정 정보를 찾을 수 없습니다.",
                        "비밀번호 변경 불가", JOptionPane.WARNING_MESSAGE);
                
                return;
            }
            
            // 변경할 비밀번호 서로 일치하는지 확인
            if (!passwordToUpdate.equals(passwordToUpdateConfirm)) {
                JOptionPane.showMessageDialog(UpdatePasswordFrame.this, "변경할 비밀번호가 서로 일치하지 않습니다.",
                        "비밀번호 변경 불가", JOptionPane.WARNING_MESSAGE);
                
                return;
            }
            
            // 일치할 경우 변경 여부 물어보고 업데이트
            int option = JOptionPane.showConfirmDialog(UpdatePasswordFrame.this, "비밀번호를 변경 하시겠습니까?", 
                    "비밀번호 변경 확인", JOptionPane.YES_NO_OPTION);
            
            // 유저의 답변에 따른 동작
            if (option == JOptionPane.YES_OPTION) {     // 변경하길 희망하는 경우
                // TODO: 비밀번호 변경 다오 만들고 돌아오기
                dao.updatePassword(accountMatchingId, passwordToUpdateConfirm);
                dispose();
            } else if (option == JOptionPane.NO_OPTION) {   // 변경 희망하지 않는 경우
                return;
            }
            
        }); // 비밀번호 변경 버튼 ActionListener 끝
        btnChangePassword.setFont(new Font("D2Coding", Font.BOLD, 16));
        btnChangePassword.setBounds(143, 415, 149, 50);
        contentPane.add(btnChangePassword);
        
        JLabel lblPasswordToUpdate = new JLabel("변경할 비밀번호:");
        lblPasswordToUpdate.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblPasswordToUpdate.setBounds(55, 294, 121, 22);
        contentPane.add(lblPasswordToUpdate);
        
        passwordToUpdateField = new JPasswordField();
        passwordToUpdateField.setFont(new Font("D2Coding", Font.PLAIN, 14));
        passwordToUpdateField.setBounds(185, 290, 210, 26);
        contentPane.add(passwordToUpdateField);
        
        lblPasswordToUpdateConfirm = new JLabel("변경 비밀번호 확인:");
        lblPasswordToUpdateConfirm.setFont(new Font("D2Coding", Font.BOLD, 13));
        lblPasswordToUpdateConfirm.setBounds(55, 338, 170, 22);
        contentPane.add(lblPasswordToUpdateConfirm);
        
        passwordToUpdateConfirmField = new JPasswordField();
        passwordToUpdateConfirmField.setFont(new Font("D2Coding", Font.PLAIN, 14));
        passwordToUpdateConfirmField.setBounds(185, 335, 210, 26);
        contentPane.add(passwordToUpdateConfirmField);
    }
}
