package com.itwill.ver05.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.ver04.controller.ContactDaoImpl;
import com.itwill.ver04.model.Contact;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ContactUpdateFrame extends JFrame {

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
    private ContactMain05 app;
    private int index;
    private ContactDaoImpl dao = ContactDaoImpl.getInstance();
    

    /**
     * Launch the application.
     */
    public static void showContactUpdateFrame(Component parent, ContactMain05 app, int index) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ContactUpdateFrame frame = new ContactUpdateFrame(parent, app, index);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    // 생성자
    public ContactUpdateFrame(Component parent, ContactMain05 app, int index) {
        this.parent = parent;   // 필드 초기화
        this.app = app; // main app 객체 가져옴
        this.index = index;
        initialize();   // Swing 컴포넌트 생성 & 초기화
        initTextField();
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
        
        JButton btnUpdate = new JButton("업데이트");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 연락처 업데이트 할 메서드
                updateContactFrame();
            }
        });
        btnUpdate.setFont(new Font("D2Coding", Font.PLAIN, 18));
        buttonPanel.add(btnUpdate);
        
        JButton btnCancel = new JButton("취소");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancel.setFont(new Font("D2Coding", Font.PLAIN, 18));
        buttonPanel.add(btnCancel);
    }
    
    private void updateContactFrame() {
        // 1. 이름, 연락처, 이메일 정보를 모음
        String name = textName.getText();
        String phone = textPhone.getText();
        String email = textEmail.getText();
        
        // 2. Contact 객체 생성
        Contact contact = new Contact(name, phone, email);
        
        // 3. 해당 인덱스 파일 업데이트
        dao.update(index, contact);
        
        // 4.  알리기
        app.notifyContactUpdated();
        
        // 5. 창 끄기
        dispose();
        
        
    }
    
    private void initTextField() {
        Contact contact = dao.read(index);
        textName.setText(contact.getName());
        textPhone.setText(contact.getPhone());
        textEmail.setText(contact.getEmail());
    }
    
}
