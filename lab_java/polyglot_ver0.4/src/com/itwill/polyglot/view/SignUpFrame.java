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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class SignUpFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblAccount;
    private JLabel lblPassword;
    private JTextField textAccount;
    private JLabel lblEmail;
    private JTextField textPhone;
    private JTextField textEmail;
    private JButton btnCancel;
    private JPasswordField passwordField;
    private JLabel lblPhoneWarning;
    
    private PolyglotDao dao = PolyglotDao.getInstance();
    private Component parent;

    /**
     * Launch the application.
     */
    public static void showSignUpFrame(Component parent) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SignUpFrame frame = new SignUpFrame(parent);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public SignUpFrame(Component parent) {
        this.parent = parent;
        initialize(parent);
    }
    
    /**
     * Create the frame.
     */
    public void initialize(Component parent) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Polyglot - Sign Up");
        setFont(new Font("D2Coding", Font.PLAIN, 12));
        
        int x = 100;
        int y = 100;
        
        if (parent != null) {
            x = parent.getX() + 100;
            y = parent.getY() + 100;
        }
        
        setBounds(x, y, 400, 470);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblSignInTitle = new JLabel("회원가입");
        lblSignInTitle.setFont(new Font("D2Coding", Font.BOLD, 18));
        lblSignInTitle.setBounds(41, 24, 97, 27);
        contentPane.add(lblSignInTitle);
        
        lblAccount = new JLabel("*아이디:");
        lblAccount.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblAccount.setBounds(41, 74, 79, 32);
        contentPane.add(lblAccount);
        
        lblPassword = new JLabel("*비밀번호:");
        lblPassword.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblPassword.setBounds(41, 133, 79, 32);
        contentPane.add(lblPassword);
        
        JLabel lblPhone = new JLabel("전화 번호:");
        lblPhone.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblPhone.setBounds(41, 188, 79, 32);
        contentPane.add(lblPhone);
        
        lblEmail = new JLabel("이 메 일:");
        lblEmail.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblEmail.setBounds(41, 249, 79, 32);
        contentPane.add(lblEmail);
        
        textAccount = new JTextField();
        textAccount.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				// 글자수 20글자로 제한
				char c = e.getKeyChar();
				if (textAccount.getText().length() >= 20) {
					JOptionPane.showMessageDialog(SignUpFrame.this, "20글자 초과로 작성 불가", "작성 불가", JOptionPane.INFORMATION_MESSAGE);
					if (c == '\b') {
						textAccount.setEditable(true);
					} else {
						textAccount.setEditable(false);
						e.setKeyChar('\b');
						textAccount.setEditable(true);
					}
				}
				
			}
        	
        });
        textAccount.setFont(new Font("D2Coding", Font.PLAIN, 14));
        textAccount.setBounds(132, 78, 204, 26);
        contentPane.add(textAccount);
        textAccount.setColumns(10);
        
        textPhone = new JTextField();
        
        textPhone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                

                if (textPhone.getText().length() < 20) {
                    if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '\b') {
                        textPhone.setEditable(true);
                        lblPhoneWarning.setText("");
                    } else {
                        textPhone.setEditable(false);
                        textPhone.setBackground(Color.WHITE);
                        lblPhoneWarning.setText("* 전화번호는 숫자만 입력 가능합니다.");
                    }
                }

                
                // 20글자 초과 작성 불가
				if (textPhone.getText().length() >= 20) {
					JOptionPane.showMessageDialog(SignUpFrame.this, "20글자 초과로 작성 불가", "작성 불가", JOptionPane.INFORMATION_MESSAGE);
					if (c == '\b') {
						textPhone.setEditable(true);
					} else {
						textPhone.setEditable(false);
						e.setKeyChar('\b');
						textPhone.setEditable(true);
					}
					return;
				}
                
            }
        });
        
        textPhone.setFont(new Font("D2Coding", Font.PLAIN, 14));
        textPhone.setColumns(10);
        textPhone.setBounds(132, 189, 204, 26);
        contentPane.add(textPhone);
        
        textEmail = new JTextField();
        textEmail.addKeyListener(new KeyAdapter() {
        	
        	@Override
			public void keyTyped(KeyEvent e) {
				// 글자수 35글자로 제한
				char c = e.getKeyChar();
				if (textEmail.getText().length() >= 35) {
					JOptionPane.showMessageDialog(SignUpFrame.this, "35글자 초과로 작성 불가", "작성 불가", JOptionPane.INFORMATION_MESSAGE);
					if (c == '\b') {
						textEmail.setEditable(true);
					} else {
						textEmail.setEditable(false);
						e.setKeyChar('\b');
						textEmail.setEditable(true);
					}
				}
				
			}
        	
        });
        textEmail.setFont(new Font("D2Coding", Font.PLAIN, 14));
        textEmail.setColumns(10);
        textEmail.setBounds(132, 252, 204, 26);
        contentPane.add(textEmail);
        
        btnCancel = new JButton("취소");
        btnCancel.addActionListener((e) -> {
            dispose();
        });
        btnCancel.setFont(new Font("D2Coding", Font.BOLD, 15));
        btnCancel.setBounds(41, 330, 102, 59);
        contentPane.add(btnCancel);
        
        JButton btnSignIn = new JButton("회원가입");
        btnSignIn.addActionListener((e) -> {    // 회원가입 버튼 ActionListener
            // 회원가입
            String accountToSignUp = textAccount.getText();
            String password = String.valueOf(passwordField.getPassword());
            String phone = textPhone.getText();
            String email = textEmail.getText();

            // 아이디, 비밀번호 무조건 입력 받기
            if (accountToSignUp.equals("")) {
                JOptionPane.showMessageDialog(SignUpFrame.this, "아이디를 입력해 주세요", "회원가입 불가", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (password.equals("")) {
                JOptionPane.showMessageDialog(SignUpFrame.this, "비밀번호를 입력해 주세요", "회원가입 불가", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // 전화번호, 이메일 없을 시 null로 넣기. 순서생각            
            if (phone.equals("")) {
                phone = null;
            }
            
            if (email.equals("")) {
                email = null;
            }
            
            // 중복된 아이디, 전화번호, 이메일주소 있는지 확인
            List<Account> existingAccounts = dao.readAllAccounts();
            for (Account existingAccount : existingAccounts) {
                String existingAccountId = existingAccount.getAccount();
                String existingAccountPhone = existingAccount.getPhone();
                String existingAccountEmail = existingAccount.getEmail();
                
                System.out.println("존재 아이디: " + existingAccountId);
                System.out.println("존재 전화번호: " + existingAccountPhone);
                System.out.println("존재 이메일: " + existingAccountEmail);
                
                if (accountToSignUp.equals(existingAccountId)) {
                    JOptionPane.showMessageDialog(SignUpFrame.this, "이미 존재하는 아이디 입니다.", "회원가입 불가", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (phone != null && existingAccountPhone != null && phone.equals(existingAccountPhone)) {
                    JOptionPane.showMessageDialog(SignUpFrame.this, "입력하신 전화번호의 계정이 이미 존재합니다.", "회원가입 불가",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (email != null && existingAccountEmail != null && email.equals(existingAccountEmail)) {
                    JOptionPane.showMessageDialog(SignUpFrame.this, "입력하신 이메일의 계정이 이미 존재합니다.", "회원가입 불가",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            
            
            Account account = new Account(accountToSignUp, password, phone, email);
            int result = dao.signUp(account);
            
            JOptionPane.showMessageDialog(SignUpFrame.this, "회원가입 성공", "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            
            System.out.println(result + "행이 추가되었습니다.");
        }); // 회원가입 ActionListener끝
        btnSignIn.setFont(new Font("D2Coding", Font.BOLD, 15));
        btnSignIn.setBounds(234, 330, 102, 59);
        contentPane.add(btnSignIn);
        
        passwordField = new JPasswordField();
        
        passwordField.addKeyListener(new KeyAdapter() {
        	// 글자 수 30글자로 제한
			@Override
			public void keyTyped(KeyEvent e) {
				
				String password = String.valueOf(passwordField.getPassword());
				char c = e.getKeyChar();
				
				if (password.length() >= 30) {
					JOptionPane.showMessageDialog(SignUpFrame.this, "30글자 초과로 작성 불가", "작성 불가", JOptionPane.INFORMATION_MESSAGE);
					if (c == '\b') {
						passwordField.setEditable(true);
					} else {
						passwordField.setEditable(false);
						e.setKeyChar('\b');
						passwordField.setEditable(true);
					}
				}
			}
        	
        });
        
        passwordField.setBounds(132, 134, 204, 26);
        contentPane.add(passwordField);
        
        lblPhoneWarning = new JLabel("");
        lblPhoneWarning.setFont(new Font("D2Coding", Font.ITALIC, 10));
        lblPhoneWarning.setBounds(132, 220, 204, 15);
        contentPane.add(lblPhoneWarning);
    }

}
