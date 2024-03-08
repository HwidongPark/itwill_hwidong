package com.itwill.swing06;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MyFrame extends JFrame {
    
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    
    private Component parentComponent;  // 부모 컴포넌트를 저장할 필드
    private AppMain06 app;  // 메인 객체를 저장할 필드

    /**
     * Launch the application.
     */
    public static void showMyFrame(Component parentComponent, AppMain06 app) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyFrame frame = new MyFrame(parentComponent, app);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MyFrame(Component frame, AppMain06 app) {
        initialize();
        this.parentComponent = frame;
        this.app = app;
    }
    
    public void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // -> 기본 닫기(x버튼) 동작을 설정: 현재 창만 닫고, 프로그램은 계속 실행.
        setTitle("MyFrame");
        
        
        int x = 100;    // parentCompoenent가 null일 때 사용할 x좌표
        int y = 100;    // parentCompoenent가 null일 때 사용할 y좌표
        
        if (parentComponent != null) {
            x = parentComponent.getX();
            y = parentComponent.getY();
        } 
        
        setBounds(x, y, 450, 300);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        textField = new JTextField();
        textField.setFont(new Font("D2Coding", Font.PLAIN, 20));
        textField.setBounds(12, 10, 410, 61);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JButton btn = new JButton("메세지 보내기");
        btn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // AppMain06에게 메세지를 보냄.
                app.notify(textField.getText());
                
                // 현재 창을 닫는다.
                dispose();
                
            }
        });
        btn.setFont(new Font("D2Coding", Font.PLAIN, 25));
        btn.setBounds(12, 155, 410, 71);
        contentPane.add(btn);
    }
}

// 중요 개념.. 객체끼리 서로 메서드를 사용하여 아규먼트로 본인의 객체 정보를 넘겨줄 수 있음..
// 아규먼트로 넘겨받은 객체의 정보를 활용해서 다른 객체에서 본인의 객체 메서드나 정보를 사용할 수 있도록 함.
