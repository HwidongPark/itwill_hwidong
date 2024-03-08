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
        setTitle("Polyglot - Find My Account");
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
        rdbtnPhone.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// 이메일에서 넘어왔을 경우 문자를 포함하거나 20글자가 넘어가면 그냥 초기화시키기
				if (textField.getText().length() > 20) {
					textField.setText("");
				}
				
				// textField가 문자를 포함하고 있을 경우 초기화
				if (!hasOnlyNumber(textField.getText())) {
					textField.setText("");
				}
			}
        	
        });
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
        
        // 이메일 마우스리스너
        rdbtnEmail.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                lblPhoneWarning.setText("");
            }
            
        });
        
        textField = new JTextField();
        textField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
            	char c = e.getKeyChar();
            	
                if (rdbtnPhone.isSelected()) {	// 핸드폰 번호 선택한 경우
                    if (textField.getText().length() < 20) {
                    	if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6'
                                || c == '7' || c == '8' || c == '9' || c == '\b') {
                            textField.setEditable(true);
                            lblPhoneWarning.setText("");
                        } else {
                            textField.setEditable(false);
                            lblPhoneWarning.setText("*전화번호는 숫자만 입력 가능합니다.");
                            e.setKeyChar('\n');
                            textField.setEditable(true);
                        }
                    } else if (textField.getText().length() >= 20) {
                    	JOptionPane.showMessageDialog(FindAccountFrame.this, "20글자 초과 작성 불가", "글자 수 초과", JOptionPane.INFORMATION_MESSAGE);
                    	if (c == '\b') {
                    		textField.setEditable(true);
                    	} else {
                    		textField.setEditable(false);
                    		e.setKeyChar('\n');
                    		textField.setEditable(true);
                    	}
                    }
                    
                } else {	// 이메일 선택한 경우
                    textField.setEditable(true);
                    if (textField.getText().length() >= 35) {
                    	JOptionPane.showMessageDialog(FindAccountFrame.this, "35글자 초과 작성 불가", "글자 수 초과", JOptionPane.INFORMATION_MESSAGE);
                    	if (c == '\b') {
                    		textField.setEditable(true);
                    	} else {
                    		textField.setEditable(false);
                    		e.setKeyChar('\b');
                    		textField.setEditable(true);
                    	}
                    }
                }	// 이메일 선택한 경우 가정 끝
            } // KeyTyped 메서드 끝
            
            
                        
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
           
            // 버튼 선택여부에 따라 다오로 아이디 찾아오기.
            if (rdbtnPhone.isSelected()) {
                foundAccount = dao.findAccountWithPhone(textField.getText());
            }
            
            else if (rdbtnEmail.isSelected()) {
                foundAccount = dao.findAccountWithEmail(textField.getText());
            }
            
            // 찾은 계정이 있을 경우 dialog 로 알려줌
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
    }	// initialize메서드 끝
    
    
    /**
     * 문자열을 입력받아 숫자만으로 구성돼 있는 문자열인지 확인
     * @param text
     * @return 숫자만 있을 경우 true, 아닐 경우 false
     */
    private boolean hasOnlyNumber(String text) {
    	boolean result = true;
    	// 빈 스트링을 입력받아도 오류 안남을 확인함
    	char[] characterArray = text.toCharArray();
    	for (char character : characterArray) {
    		if (!Character.isDigit(character)) {
    			result = false;
    			return result;
    		}
    	}
    	
    	return result;
    }
    
}
