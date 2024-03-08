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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class FindAccountFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JButton btnFindAccount;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JLabel lblPhoneWarning;
    private JRadioButton rdbtnPhone;
    private JRadioButton rdbtnEmail;
    
    private PolyglotDao dao = PolyglotDao.getInstance();
    Component parent;
    
    /**
     * Launch the application.
     */
    public static void showFindAccountFrame(Component parent) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FindAccountFrame frame = new FindAccountFrame(parent);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public FindAccountFrame(Component parent) {
        this.parent = parent;
        initialize(parent);
    };
    
    /**
     * Create the frame.
     */
    public void initialize(Component parent) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        int x = 100;
        int y = 100;
        
        if (parent != null) {
            x = parent.getX() + 80;
            y = parent.getY() + 80;
        }
        
        setBounds(x, y, 389, 402);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitle = new JLabel("아이디 찾기");
        lblTitle.setFont(new Font("D2Coding", Font.BOLD, 20));
        lblTitle.setBounds(128, 10, 129, 44);
        contentPane.add(lblTitle);
        
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("D2Coding", Font.ITALIC, 14));
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        textArea.setText("전화번호, 이메일 중 하나를 선택한 후,\r\n\t   입력해 주세요");
        textArea.setBounds(54, 64, 284, 54);
        contentPane.add(textArea);
        
        rdbtnPhone = new JRadioButton("전화번호");
        rdbtnPhone.setSelected(true);
        buttonGroup.add(rdbtnPhone);
        rdbtnPhone.setBackground(new Color(255, 255, 255));
        rdbtnPhone.setBounds(54, 113, 95, 23);
        contentPane.add(rdbtnPhone);
        
        rdbtnEmail = new JRadioButton("이메일");
        buttonGroup.add(rdbtnEmail);
        rdbtnEmail.setBackground(Color.WHITE);
        rdbtnEmail.setBounds(225, 113, 95, 23);
        contentPane.add(rdbtnEmail);
        
        // 버튼그룹 마우스리스너
        rdbtnEmail.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                lblPhoneWarning.setText("");
            }
            
        });
        
        textField = new JTextField();
        textField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (rdbtnPhone.isSelected()) {
                    char c = e.getKeyChar();
                    if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6'
                            || c == '7' || c == '8' || c == '9' || c == '\b') {
                        textField.setEditable(true);
                        lblPhoneWarning.setText("");
                    } else {
                        textField.setEditable(false);
                        lblPhoneWarning.setText("*전화번호는 숫자만 입력 가능합니다.");
                    }
                } else {
                    textField.setEditable(true);
                    lblPhoneWarning.setText("");
                }
            }
            
            
                        
        });
            
        
        textField.setBackground(Color.WHITE);
        textField.setFont(new Font("D2Coding", Font.PLAIN, 14));
        textField.setBounds(54, 179, 266, 36);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JLabel lblInput = new JLabel("입력:");
        lblInput.setFont(new Font("D2Coding", Font.ITALIC, 12));
        lblInput.setBounds(54, 162, 95, 15);
        contentPane.add(lblInput);
        
        btnFindAccount = new JButton("아이디 찾기");
        btnFindAccount.addActionListener((e) -> {   // 아이디 찾기 action listener
           Account foundAccount = null;
           
           if (textField.getText().equals("")) {
               JOptionPane.showMessageDialog(FindAccountFrame.this, "전화번호/이메일을 입력해 주세요.");
               
               return;
           }
           
            // TODO: 버튼 선택여부에 따라 다오로 아이디 찾아오기. 다오 만든 후 돌아오자
            if (rdbtnPhone.isSelected()) {
                foundAccount = dao.findAccountWithPhone(textField.getText());
            }
            
            else if (rdbtnEmail.isSelected()) {
                foundAccount = dao.findAccountWithEmail(textField.getText());
            }
            
            // TODO: 찾은 계정이 있을 경우 dialog 로 알려주기.. 없을경우 없다는 경고
            if (foundAccount == null) {
                JOptionPane.showMessageDialog(FindAccountFrame.this, "아이디를 찾을 수 없습니다.",
                        "해당 아이디 없음", JOptionPane.INFORMATION_MESSAGE);
                
                return;
            } else if (foundAccount != null) {
                String idFound = foundAccount.getAccount();
                JOptionPane.showMessageDialog(FindAccountFrame.this, "아이디: " + idFound, "아이디 찾음", JOptionPane.PLAIN_MESSAGE);
                
            }
            
        }); // 아이디 찾기 action listener 버튼 끝
        
        btnFindAccount.setBackground(UIManager.getColor("CheckBox.background"));
        btnFindAccount.setFont(new Font("D2Coding", Font.PLAIN, 14));
        btnFindAccount.setBounds(105, 251, 168, 54);
        contentPane.add(btnFindAccount);
        
        lblPhoneWarning = new JLabel("");
        lblPhoneWarning.setFont(new Font("D2Coding", Font.ITALIC, 9));
        lblPhoneWarning.setBounds(54, 215, 266, 15);
        contentPane.add(lblPhoneWarning);
    }
}