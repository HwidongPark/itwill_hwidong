package com.itwill.polyglot.view;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import com.itwill.polyglot.controller.PolyglotDao;
import com.itwill.polyglot.model.Account;
import com.itwill.polyglot.model.Language;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class PolyglotMain {
    
    //Fields
    String IMAGEPATH = "miscellaneous_files/logo_final.png";
    
    private JFrame frame;
    private JLabel lblSetLan;
    private JComboBox<String> comboChooseLan;
    private JButton btnStartLeanring;
    private JButton btnHasNote;
    private String[] languageToStudy = new String[] {"영어", "한국어", "스페인어", "이탈리아어",
            "독일어", "일본어", "중국어(만다린)", "중국어(광둥어)", "스웨덴어", 
            "프랑스어", "히브리어", "아랍어"};
    
    
    PolyglotDao dao = PolyglotDao.getInstance();
    private JTextField textAccount;
    private JPasswordField passwordField;
    private JLabel lblSignUpAsk;
    private JLabel lblFindMy;
    private JLabel lblFindPassword;
    private JLabel lblSignUp;
    private JLabel lblChangePassword;
    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PolyglotMain window = new PolyglotMain();
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
    public PolyglotMain() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setFont(new Font("D2Coding", Font.PLAIN, 12));
        frame.setResizable(false);
        
        frame.getContentPane().setBackground(Color.WHITE);
        
        frame.getContentPane().setForeground(new Color(255, 255, 255));
        frame.setBounds(100, 100, 540, 620);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        // 로고 이미지
        JLabel lblLogo = new JLabel();
        ImageIcon logoBeforeScale = new ImageIcon(IMAGEPATH);
        Image image = logoBeforeScale.getImage();
        Image logoImage = image.getScaledInstance(200, 150, image.SCALE_SMOOTH);
        
        ImageIcon logoAfterScale = new ImageIcon(logoImage);
        
        lblLogo.setIcon(logoAfterScale);
        lblLogo.setBounds(163, 10, 200, 150);
        frame.getContentPane().add(lblLogo);
        
        // 창 타이틀
        JLabel lblTitle = new JLabel("POLYGLOT");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(205, 170, 123, 40);
        frame.getContentPane().add(lblTitle);
        
        
        lblSetLan = new JLabel("학습 언어를 선택하세요:");
        lblSetLan.setFont(new Font("D2Coding", Font.PLAIN, 12));
        lblSetLan.setBounds(125, 240, 147, 27);
        frame.getContentPane().add(lblSetLan);
        
        
        // 언어선택 콤보박스
        comboChooseLan = new JComboBox<String>();
        DefaultComboBoxModel<String> comboChooseLanModel = new DefaultComboBoxModel<>(languageToStudy);
        comboChooseLan.setModel(comboChooseLanModel);
        comboChooseLan.setBounds(297, 242, 109, 23);
        frame.getContentPane().add(comboChooseLan);
        
        
        // 학습 시작 버튼
        btnStartLeanring = new JButton("학습 시작");
        btnStartLeanring.addActionListener((e) -> {     // 학습시작 버튼 ActionListener
            int type = comboChooseLan.getSelectedIndex();
            String userInputAccount = "";
            String userInputPassword = "";
            
            // 유저 입력 아이디 비밀번호 가져오기. 비밀번호는 String으로 바꿔줌
            userInputAccount = textAccount.getText();
            userInputPassword = String.valueOf(passwordField.getPassword());
            
            
            // 아이디 비밀번호 입력 하지 않았을 때 경고문 띄우기
            if (userInputAccount.equals("")) {
                JOptionPane.showMessageDialog(frame,"아이디를 입력해 주세요.", "오류",  JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (userInputPassword.equals("")) {
                JOptionPane.showMessageDialog(frame,"비밀번호를 입력해 주세요.", "오류",  JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // 학습언어 선택안할 시 접속 불가
            if (type == -1) {
                JOptionPane.showMessageDialog(frame, "학습 언어를 선택해 주세요.", "오류", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // TODO: 아아디 비밀번호 맞을 때만 입장 가능하도록 하기
            Account accountToSignIn = dao.hasMatchintAccount(userInputAccount, userInputPassword);
            if (accountToSignIn == null) {
                JOptionPane.showMessageDialog(frame, "아이디/비밀번호가 일치하지 않습니다", "로그인 불가", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            
            
            
            String languageToStudy = getLanguageToStudy(type);

            Language language = dao.readLanguage(languageToStudy);
            
            LearningSquareFrame.showLearningSquareFrame(frame, language, PolyglotMain.this, accountToSignIn);
            
            frame.dispose();
        });
        btnStartLeanring.setBounds(174, 487, 173, 71);
        frame.getContentPane().add(btnStartLeanring);
        
        textAccount = new JTextField();
        textAccount.setFont(new Font("D2Coding", Font.PLAIN, 14));
        textAccount.setBounds(206, 297, 257, 23);
        frame.getContentPane().add(textAccount);
        textAccount.setColumns(10);
        
        JLabel lblAccount = new JLabel("아이디:");
        lblAccount.setFont(new Font("D2Coding", Font.PLAIN, 14));
        lblAccount.setBounds(109, 297, 80, 23);
        frame.getContentPane().add(lblAccount);
        
        JLabel lblPassword = new JLabel("비밀번호:");
        lblPassword.setFont(new Font("D2Coding", Font.PLAIN, 14));
        lblPassword.setBounds(109, 333, 80, 23);
        frame.getContentPane().add(lblPassword);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("D2Coding", Font.PLAIN, 14));
        passwordField.setBounds(206, 333, 257, 23);
        frame.getContentPane().add(passwordField);
        
        lblFindMy = new JLabel("아이디/비밀번호가 기억나지 않으시나요?");
        lblFindMy.setFont(new Font("D2Coding", Font.ITALIC, 10));
        lblFindMy.setBounds(109, 413, 219, 15);
        frame.getContentPane().add(lblFindMy);
        
        lblSignUpAsk = new JLabel("아직 회원이 아니신가요?");
        lblSignUpAsk.setFont(new Font("D2Coding", Font.ITALIC, 10));
        lblSignUpAsk.setBounds(109, 388, 152, 15);
        frame.getContentPane().add(lblSignUpAsk);
        
        
        // 아이디 찾기
        JLabel lblFindAccount = new JLabel("아이디 찾기");
        lblFindAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblFindAccount.addMouseListener(new MouseAdapter() {    // 아이디 찾기 MouseListener

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: 아이디 찾기 창 만들면 돌아오기
                FindAccountFrame.showFindAccountFrame(frame);
            }
            
            
        });
        lblFindAccount.setForeground(Color.BLUE);
        lblFindAccount.setFont(new Font("D2Coding", Font.ITALIC, 10));
        lblFindAccount.setBounds(109, 431, 80, 15);
        frame.getContentPane().add(lblFindAccount);
        
        
        // 비밀번호 찾기
        lblFindPassword = new JLabel("비밀번호 찾기");
        lblFindPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblFindPassword.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                FindPasswordFrame.showFindPasswordFrame(frame);
            }
            
        });
        lblFindPassword.setForeground(Color.BLUE);
        lblFindPassword.setFont(new Font("D2Coding", Font.ITALIC, 10));
        lblFindPassword.setBounds(248, 431, 80, 15);
        frame.getContentPane().add(lblFindPassword);
        
        
        // 회원가입
        lblSignUp = new JLabel("회원가입");
        lblSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblSignUp.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                SignInFrame.showSignInFrame(frame);
            }
            
        });
        lblSignUp.setFont(new Font("D2Coding", Font.ITALIC, 12));
        lblSignUp.setForeground(Color.BLUE);
        lblSignUp.setBounds(406, 380, 57, 15);
        frame.getContentPane().add(lblSignUp);
        
        lblChangePassword = new JLabel("비밀번호 변경");
        lblChangePassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblChangePassword.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                UpdatePasswordFrame.showUpdatePasswordFrame(frame);
            }
            
        });
        lblChangePassword.setForeground(Color.BLUE);
        lblChangePassword.setFont(new Font("D2Coding", Font.ITALIC, 10));
        lblChangePassword.setBounds(389, 430, 74, 15);
        frame.getContentPane().add(lblChangePassword);
        
        
    }   // initialize method 끝
    
    /**
     * 언어 선택 comboBox에 선택되어 있는 language_code를 반환
     * @param type
     * @return language String type의 선택된 언어 반환.
     */
    private String getLanguageToStudy(int type) {
        String result = "";
        
        if(languageToStudy[type] != null) {
            result = getLanguageCode(languageToStudy[type]);
        }
        
        return result;
    }
    
    
    // 이 창을 띄우는 메서드
    public static void showPolyglotMain() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PolyglotMain window = new PolyglotMain();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    
    /**
     * 유저가 선택한 언어의 Code를 반환
     * @param language
     * @return languageCode 유저 선택 언어의 Code
     */
    String getLanguageCode(String language) {
        String result = "";

        if (language.equals("영어")) {
            result = "EN";
        } else if (language.equals("한국어")) {
            result = "KO";
        } else if (language.equals("스페인어")) {
            result = "ES";
        } else if (language.equals("이탈리아어")) {
            result = "IT";
        } else if (language.equals("독일어")) {
            result = "DE";
        } else if (language.equals("일본어")) {
            result = "JA";
        } else if (language.equals("중국어(만다린)")) {
            result = "ZH_CN";
        } else if (language.equals("중국어(광둥어)")) {
            result = "ZH_HK";
        } else if (language.equals("스웨덴어")) {
            result = "SV";
        } else if (language.equals("프랑스어")) {
            result = "FR";
        } else if (language.equals("히브리어")) {
            result = "HE";
        } else if (language.equals("아랍어")) {
            result = "AR";
        } 
        
        return result;
    }
}   // Main Class 끝
