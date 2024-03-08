package com.itwill.swing05;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class AppMain05 {

    private JFrame frame;
    private JRadioButton rdPrivate;
    private JRadioButton rdPackage;
    private JRadioButton rdProtected;
    private JRadioButton rdPublic;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JCheckBox cbFinal;
    private JCheckBox cbStatic;
    private JComboBox<String> comboBox;
    private JCheckBox cbAbstract;
    private JScrollPane scrollPane;
    private JTextArea textArea;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AppMain05 window = new AppMain05();
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
    public AppMain05() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 601, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        rdPrivate = new JRadioButton("private");
        rdPrivate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleRadioButtonClick(e);
            }
        });
        rdPrivate.setSelected(true);
        buttonGroup.add(rdPrivate);
        rdPrivate.setFont(new Font("D2Coding", Font.PLAIN, 15));
        rdPrivate.setBounds(8, 6, 132, 40);
        frame.getContentPane().add(rdPrivate);
        
        rdPackage = new JRadioButton("package");
        rdPackage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                handleRadioButtonClick(e);
                
            }
            
        });
        buttonGroup.add(rdPackage);
        rdPackage.setFont(new Font("D2Coding", Font.PLAIN, 15));
        rdPackage.setBounds(146, 6, 132, 40);
        frame.getContentPane().add(rdPackage);
        
        rdProtected = new JRadioButton("protected");
        rdProtected.addActionListener((e) -> handleRadioButtonClick(e));
        buttonGroup.add(rdProtected);
        rdProtected.setFont(new Font("D2Coding", Font.PLAIN, 15));
        rdProtected.setBounds(287, 6, 132, 40);
        frame.getContentPane().add(rdProtected);
        
        rdPublic = new JRadioButton("public");
        rdPublic.addActionListener(this::handleRadioButtonClick);
        buttonGroup.add(rdPublic);
        rdPublic.setFont(new Font("D2Coding", Font.PLAIN, 15));
        rdPublic.setBounds(425, 6, 132, 40);
        frame.getContentPane().add(rdPublic);
        
        cbAbstract = new JCheckBox("abstract");
        cbAbstract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckBoxClick(e);
            }
        });
        cbAbstract.setFont(new Font("D2Coding", Font.PLAIN, 15));
        cbAbstract.setBounds(8, 54, 132, 40);
        frame.getContentPane().add(cbAbstract);
        
        cbFinal = new JCheckBox("final");
        cbFinal.addActionListener((e) -> handleCheckBoxClick(e));   // 람다
        cbFinal.setFont(new Font("D2Coding", Font.PLAIN, 15));
        cbFinal.setBounds(146, 54, 132, 40);
        frame.getContentPane().add(cbFinal);
        
        cbStatic = new JCheckBox("static");
        cbStatic.addActionListener(this::handleCheckBoxClick);      // 메서드 참조
        cbStatic.setFont(new Font("D2Coding", Font.PLAIN, 15));
        cbStatic.setBounds(282, 54, 132, 40);
        frame.getContentPane().add(cbStatic);
        
        comboBox = new JComboBox<>();
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleComboBoxChange(e);
            }
        });
        comboBox.setFont(new Font("D2Coding", Font.PLAIN, 16));
        
        // 콤보박스에서 사용할 문자열들의 배열:
        final String[] emails = {"naver.com", "gmail.com", "yahoo.com", "outlook.com", "kakao.com"};
        // 콤보박스 모델 객체 생성:
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(emails);
        // 콤보박스에 모델을 설정:
        comboBox.setModel(model);
        comboBox.setBounds(8, 110, 349, 23);
        frame.getContentPane().add(comboBox);
        
        JButton btnInfo = new JButton("확인");
        btnInfo.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                handleInfoButtonClick();
                
            }
        });
        btnInfo.setBounds(402, 111, 97, 23);
        frame.getContentPane().add(btnInfo);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(22, 161, 535, 146);
        frame.getContentPane().add(scrollPane);
        
        // 보통 JTextArea는 JScrollPane안에 집어 넣는다.
        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);

    }
    
    private void handleCheckBoxClick(ActionEvent e) {
//        textArea.setText(e.getSource().toString());
        
        // 클릭한 체크박스 문자열 선택여부를 JTextArea에 출력.
        JCheckBox cb = (JCheckBox) e.getSource();
        String cbName = cb.getText();
        boolean selected = cb.isSelected();
        textArea.setText(cbName + " : " + selected);
    }
    
    
    private void handleRadioButtonClick(ActionEvent e) {
//        textArea.setText(e.getSource().toString());
        
        // 클릭한 라디오버튼 이름과 선택여부를 JTextArea에 출력.
//        textArea.setText(((JRadioButton) e.getSource()).getText()); // 이벤트가 발생한 컴포넌트
        JRadioButton rb = (JRadioButton) e.getSource();     // 이벤트가 발생한 컴포넌트
        String rbName = rb.getText();   // 버튼 텍스트
        boolean selected = rb.isSelected();    // 라디오 버튼 선택 여부
        textArea.setText(rbName + " : " + selected);
        
        
    }
    
    
    private void handleComboBoxChange(ActionEvent e) {
//        textArea.setText(e.getSource().toString());
        
        // 콤보박스에서 선택된 아이템(문자열)을 JTextArea에 출력.
        JComboBox<String> combo = (JComboBox<String>) e.getSource();
        String selected = combo.getSelectedItem().toString();
        textArea.setText(selected + " 선택됨");
        
        
    }
    
    
    private void handleInfoButtonClick() {
        // 라디오 버튼의 선택 상태, 체크박스의 선택 상태, 콤보박스의 선택 상태를 JTextArea에 출력
        // StringBuffer는 문자열들을 만들어줄 수 있는 객체
        StringBuffer buffer = new StringBuffer();
        
        // 어떤 라디오버튼이 선택됐는 지를 체크:
        if (rdPrivate.isSelected()) {
            buffer.append(rdPrivate.getText());
        } else if (rdPackage.isSelected()) {
            buffer.append(rdPackage.getText());
        } else if(rdProtected.isSelected()) {
            buffer.append(rdProtected.getText());
        } else {
            buffer.append(rdPublic.getText());
        }
        buffer.append(" 라디오버튼 선택됨.\n");
        
        
        // 어떤 체크박스가 선택되어 있는 지를 체크.
        if(cbFinal.isSelected()) {
            buffer.append(cbFinal.getText()).append(" ");
        }    
        if (cbAbstract.isSelected()) {
            buffer.append(cbAbstract.getText()).append(" ");
        }
        if(cbStatic.isSelected()) {
            buffer.append(cbStatic.getText()).append(" ");
        }
        buffer.append("체크박스 선택됨.\n");
        
        
        // 콤보박스에서 선택된 아이템:
        buffer.append(comboBox.getSelectedItem()).append(" 콤보박스 아이템 선택됨");
        
        textArea.setText(buffer.toString());
        
        
        
        
    }
}   // AppMain05 클래스 끝
