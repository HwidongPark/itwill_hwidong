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
import com.itwill.polyglot.model.Language;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

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
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.WHITE);
        
        frame.getContentPane().setForeground(new Color(255, 255, 255));
        frame.setBounds(100, 100, 540, 457);
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
            
            
            
            if (type == -1) {
                JOptionPane.showMessageDialog(frame, "학습 언어를 선택해 주세요.", "오류", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String languageToStudy = getLanguageToStudy(type);

            Language language = dao.readLanguage(languageToStudy);
            
            LearningSquareFrame.showLearningSquareFrame(frame, language, PolyglotMain.this);
            
            frame.dispose();
        });
        btnStartLeanring.setBounds(183, 303, 173, 71);
        frame.getContentPane().add(btnStartLeanring);
        
        
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
