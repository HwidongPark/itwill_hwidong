package com.itwill.ver05.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTextPaneUI;

import com.itwill.ver04.controller.ContactDaoImpl;
import com.itwill.ver04.model.Contact;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ContactCreateFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel mainPanel;
    private JLabel lblName;
    private JLabel lblPhone;
    private JTextField textName;
    private JTextField textPhone;
    private JTextField textEmail;
    private JLabel lblEmail;
    
    private Component parent;
    private ContactDaoImpl dao = ContactDaoImpl.getInstance();
    private ContactMain05 app;
    

    /**
     * Launch the application.
     */
    public static void showContactCreateFrame(Component parent, ContactMain05 app) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ContactCreateFrame frame = new ContactCreateFrame(parent, app);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    // 생성자
    public ContactCreateFrame(Component parent, ContactMain05 app) {
        this.parent = parent;   // 필드 초기화
        this.app = app; // ContactMain05 객체 생성
        initialize();   // Swing 컴포넌트 생성 & 초기화
    }
    
    /**
     * Create the frame.
     */
    public void initialize() {
        setTitle("새 연락처");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        int x = 100;
        int y = 100;
        if (parent != null) {
            x = parent.getX() + parent.getWidth();
            y = parent.getY();
        }
        setBounds(x, y, 450, 300);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        mainPanel = new JPanel();
        contentPane.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(null);
        
        lblName = new JLabel("이름");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setFont(new Font("D2Coding", Font.PLAIN, 18));
        lblName.setBounds(12, 10, 70, 49);
        mainPanel.add(lblName);
        
        lblPhone = new JLabel("전화번호");
        lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
        lblPhone.setFont(new Font("D2Coding", Font.PLAIN, 15));
        lblPhone.setBounds(12, 69, 70, 49);
        mainPanel.add(lblPhone);
        
        lblEmail = new JLabel("이메일");
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmail.setFont(new Font("D2Coding", Font.PLAIN, 18));
        lblEmail.setBounds(12, 128, 70, 49);
        mainPanel.add(lblEmail);
        
        textName = new JTextField();
        textName.setFont(new Font("D2Coding", Font.PLAIN, 18));
        textName.setBounds(100, 10, 276, 49);
        mainPanel.add(textName);
        textName.setColumns(10);
        
        textPhone = new JTextField();
        textPhone.setFont(new Font("D2Coding", Font.PLAIN, 18));
        textPhone.setColumns(10);
        textPhone.setBounds(100, 68, 276, 49);
        mainPanel.add(textPhone);
        
        textEmail = new JTextField();
        textEmail.setFont(new Font("D2Coding", Font.PLAIN, 18));
        textEmail.setColumns(10);
        textEmail.setBounds(100, 128, 276, 49);
        mainPanel.add(textEmail);
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        JButton btnSave = new JButton("저장");
        btnSave.addActionListener((e) -> {
            createNewContact();
        });
        btnSave.setFont(new Font("D2Coding", Font.PLAIN, 18));
        buttonPanel.add(btnSave);
        
        JButton btnCancel = new JButton("취소");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancel.setFont(new Font("D2Coding", Font.PLAIN, 18));
        buttonPanel.add(btnCancel);
    }

    private void createNewContact() {
        // 1. JTextField에서 이름, 전화번호, 이메일을 읽음.
        String name = textName.getText();
        String phone = textPhone.getText();
        String email = textEmail.getText();
        
        // 2. Contact 타입의 객체 생성.
        Contact contact = new Contact(name, phone, email);
        
        
        // 3. 연락처 데이터 File에 저장.
        dao.create(contact);
        
        
        // 4. ContactMain 윈도우에게 연락처 저장이 성공했음을 알려줌.
        app.notifyContactCreated();
        
        
        // 현재 창 닫기
        dispose();
         
        
    }
}
