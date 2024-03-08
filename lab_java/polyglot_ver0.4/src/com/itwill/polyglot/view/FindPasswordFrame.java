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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

public class FindPasswordFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblTitle;
    private JTextField textAccount;
    private JRadioButton rdbtnPhone;
    private JRadioButton rdbtnEmail;
    private JTextField textOptionChosen;
    private JLabel lblOptionChosen;
    private JLabel lblNewLabel;
    private JButton btnFindPassword;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JLabel lblPhoneWarning;
    
    private PolyglotDao dao = PolyglotDao.getInstance();
    Component parent;

    /**
     * Launch the application.
     */
    public static void showFindPasswordFrame(Component parent) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FindPasswordFrame frame = new FindPasswordFrame(parent);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    FindPasswordFrame(Component parent) {
        this.parent = parent;
        initialize(parent);
    }
    
    /**
     * Create the frame.
     */
    public void initialize(Component parent) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Polyglot - Fine my password");
        setBackground(Color.WHITE);
        setResizable(false);
        
        int x = 100;
        int y = 100;
        
        if (parent != null) {
            x = parent.getX() + 80;
            y = parent.getY() + 80;
        }
        
        setBounds(x, y, 426, 395);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblTitle = new JLabel("비밀번호 찾기");
        lblTitle.setFont(new Font("D2Coding", Font.BOLD, 20));
        lblTitle.setBounds(124, 10, 156, 44);
        contentPane.add(lblTitle);
        
        JLabel lblAccount = new JLabel("아이디:");
        lblAccount.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblAccount.setBounds(27, 98, 71, 27);
        contentPane.add(lblAccount);
        
        textAccount = new JTextField();
        textAccount.addKeyListener(new KeyAdapter() {
        	// 아이디 20글자로 입력 제한
        	@Override
        	public void keyTyped(KeyEvent e) {
				// 글자수 20글자로 제한
				char c = e.getKeyChar();
				if (textAccount.getText().length() >= 20) {
					JOptionPane.showMessageDialog(FindPasswordFrame.this, "20글자 초과로 작성 불가", "작성 불가", JOptionPane.INFORMATION_MESSAGE);
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
        textAccount.setBounds(110, 93, 242, 34);
        contentPane.add(textAccount);
        textAccount.setColumns(10);
        
        // 전화번호/이메일 선택여부에 따라 달라지는 label
        lblOptionChosen = new JLabel("전화번호");
        lblOptionChosen.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblOptionChosen.setBounds(27, 191, 71, 27);
        contentPane.add(lblOptionChosen);
        
        // 라디오버튼, 전화
        rdbtnPhone = new JRadioButton("전화번호");

        buttonGroup.add(rdbtnPhone);
        rdbtnPhone.setSelected(true);
        rdbtnPhone.addMouseListener(new MouseAdapter() {    // 핸드폰번호 rdbtn 마우스리스너 추가

            @Override
            public void mouseClicked(MouseEvent e) {
                lblOptionChosen.setText("전화번호");
                
				// 이메일에서 넘어왔을 경우 문자를 포함하거나 20글자가 넘어가면 그냥 초기화시키기
				if (textOptionChosen.getText().length() > 20) {
					textOptionChosen.setText("");
				}
				
				// textField가 문자를 포함하고 있을 경우 초기화
				if (!hasOnlyNumber(textOptionChosen.getText())) {
					textOptionChosen.setText("");
				}
                
            }    
            
            
        });
        
        rdbtnPhone.setBackground(new Color(255, 255, 255));
        rdbtnPhone.setFont(new Font("D2Coding", Font.PLAIN, 10));
        rdbtnPhone.setBounds(194, 147, 90, 23);
        contentPane.add(rdbtnPhone);
        
        // 라디오버튼, 이메일
        rdbtnEmail = new JRadioButton("이메일");
        buttonGroup.add(rdbtnEmail);
        rdbtnEmail.addMouseListener(new MouseAdapter() {    // 이메일 rdbtn 마우스리스너 추가

            @Override
            public void mouseClicked(MouseEvent e) {
                lblOptionChosen.setText("이메일");
                lblPhoneWarning.setText("");
            }
            
        });
        rdbtnEmail.setFont(new Font("D2Coding", Font.PLAIN, 10));
        rdbtnEmail.setBackground(Color.WHITE);
        rdbtnEmail.setBounds(288, 147, 64, 23);
        contentPane.add(rdbtnEmail);
        
        lblNewLabel = new JLabel("비밀번호 찾기 옵션:");
        lblNewLabel.setFont(new Font("D2Coding", Font.ITALIC, 12));
        lblNewLabel.setBounds(55, 150, 128, 15);
        contentPane.add(lblNewLabel);
        
        textOptionChosen = new JTextField();
        textOptionChosen.setBackground(Color.WHITE);
        textOptionChosen.setFont(new Font("D2Coding", Font.PLAIN, 14));
        textOptionChosen.addKeyListener(new KeyAdapter() {  // 선택정보 텍스트필드 keyListener

            @Override
            public void keyTyped(KeyEvent e) {    // 키 눌렸을 때 행동
                char c = e.getKeyChar();
                if (rdbtnPhone.isSelected()) {	// 전화번호 선택됐을 때
                	if (textOptionChosen.getText().length() < 20) {
                		if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6'
                                || c == '7' || c == '8' || c == '9' || c == '\b') {
                            textOptionChosen.setEditable(true);
                            lblPhoneWarning.setText("");
                        } else {
                            textOptionChosen.setEditable(false);
                            lblPhoneWarning.setText("*전화번호는 숫자만 입력 가능합니다.");
                            e.setKeyChar('\n');
                            textOptionChosen.setEditable(true);
                        }	
                	} else if (textOptionChosen.getText().length() >= 20) {	// 전화번호 20글자 초과 못하도록 함
                		JOptionPane.showMessageDialog(FindPasswordFrame.this, "20글자 초과 작성 불가", "글자 수 초과", JOptionPane.INFORMATION_MESSAGE);
                		if (e.getKeyChar() == '\b') {
                			textOptionChosen.setEditable(true);
                		} else {
                			textOptionChosen.setEditable(false);
                			e.setKeyChar('\b');
                			textOptionChosen.setEditable(true);
                		}
                	}
                    
                }	// 전화번호 선택됐을 떄 끝
                
                else if(rdbtnEmail.isSelected()) {	// 이메일 선택됐을 때
                    if (textOptionChosen.getText().length() >= 35) {
                    	JOptionPane.showMessageDialog(FindPasswordFrame.this, "35글자 초과 작성 불가", "글자 수 초과", JOptionPane.INFORMATION_MESSAGE);
                    	if (e.getKeyChar() == '\n') {
                    		textOptionChosen.setEditable(true);
                    	} else {
                    		textOptionChosen.setEditable(false);
                			e.setKeyChar('\b');
                			textOptionChosen.setEditable(true);
                    	}
                    }
                }
                
            }
            
        }); // key listener끝
        textOptionChosen.setColumns(10);
        textOptionChosen.setBounds(110, 184, 242, 34);
        contentPane.add(textOptionChosen);
        
        
        // 비밀번호 찾는 버튼
        btnFindPassword = new JButton("비밀번호 찾기");
        btnFindPassword.addActionListener((e) -> {      // 비밀번호 찾기 ActionListener.. 유저 입력 정보와 일치하는 계정 비밀번호 0000으로 초기화
            String userInputAccount = textAccount.getText();
            String userInputOption = textOptionChosen.getText();
            
            Account accountMatchingId = null;
            Account accountMatchingOption = null;
            
            if (userInputAccount.equals("") || userInputOption.equals("")) {
                JOptionPane.showMessageDialog(FindPasswordFrame.this, "아이디와 전화번호/이메일을 모두 입력해 주세요.", "비밀번호 찾기 불가",
                        JOptionPane.WARNING_MESSAGE);
                
                return;
            }
            
            // 아이디 일치하는 계정 객체 가져오기
            accountMatchingId = dao.findAccountWithId(textAccount.getText());
            // 아이디 일치하는 계정 객체와 이메일/전화번호 일치하는 계정 객체를 비교
            if (rdbtnPhone.isSelected()) {
                accountMatchingOption = dao.findAccountWithPhone(userInputOption);

            }
            
            else if (rdbtnEmail.isSelected()) {
                accountMatchingOption = dao.findAccountWithEmail(userInputOption);

            }
            
            
            // 아이디 또는 전화번호/이메일 일치하는 계정이 존재하지 않을 경우 경고메세지
            if (accountMatchingId == null || accountMatchingOption == null) {
                JOptionPane.showMessageDialog(FindPasswordFrame.this, "입력하신 정보와 일치하는 계정을 찾을 수 없습니다.",
                        "비밀번호 변경 실패", JOptionPane.INFORMATION_MESSAGE);
                
                return;
            }
            
            // account가 primary key이므로 이 둘이 똑같으면 두 계정은 같은 계정이라 봐도 된다.
            if (accountMatchingId.getAccount().equals(accountMatchingOption.getAccount())) {
                int willInitPassword = JOptionPane.showConfirmDialog(FindPasswordFrame.this, "비밀번호를 0000으로 초기화 합니다.", "비밀번호 초기화",
                        JOptionPane.YES_NO_OPTION);
                
                if (willInitPassword == JOptionPane.YES_OPTION) {
                    // 비밀번호 초기화할지 물어보고 초기화
                    dao.updatePasswordTo0000(accountMatchingId);
                    JOptionPane.showMessageDialog(FindPasswordFrame.this, "비밀번호가 0000으로 초기화 되었습니다.", "비밀번호 초기화 완료",
                            JOptionPane.INFORMATION_MESSAGE);
                } else if (willInitPassword == JOptionPane.NO_OPTION) {
                    return;
                }
                
                
            } else {
                JOptionPane.showMessageDialog(FindPasswordFrame.this, "입력하신 정보와 일치하는 계정을 찾을 수 없습니다.",
                        "비밀번호 변경 실패", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            
        });
        btnFindPassword.setBackground(new Color(255, 255, 255));
        btnFindPassword.setBounds(139, 266, 115, 44);
        contentPane.add(btnFindPassword);
        
        // 핸드폰번호 경고 label
        lblPhoneWarning = new JLabel("");
        lblPhoneWarning.setFont(new Font("D2Coding", Font.ITALIC, 10));
        lblPhoneWarning.setBounds(110, 220, 242, 15);
        contentPane.add(lblPhoneWarning);
        
    }	// initialize 메서드 끝
    
    
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
