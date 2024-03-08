package com.itwill.polyglot.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itwill.polyglot.controller.PolyglotDao;
import com.itwill.polyglot.model.Note;
import com.itwill.polyglot.model.Word;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class AddWordFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblWord;
    private JTextField textWord;
    private JScrollPane scrollPane;
    private JButton btnAdd;
    private JButton btnCancel;
    private JTextArea textMeaning;
    private JLabel lblWordsCount;
    
    private Component parent;
    private Note note;
    private PolyglotDao dao = PolyglotDao.getInstance();
    
    
    // TODO: 아마 note필요 없는 듯 나중에 시간나면 note 필드에서 지우는 작업하기
    
    /**
     * Launch the application.
     */
    public static void showAddWordFrame(Component parent, Note note) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddWordFrame frame = new AddWordFrame(parent, note);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    AddWordFrame(Component parent, Note note) {
        this.parent = parent;
        this.note = note;
        initialize(parent, note);
    }
    
    /**
     * Create the frame.
     */
    public void initialize(Component parent, Note note) {
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Polyglot - Add a new word");
        int x = 100;
        int y = 100;
        
        if (parent != null) {
            x = parent.getX() + 30;
            y = parent.getY() + 30;
        }
        
        setBounds(x, y, 450, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblWord = new JLabel("단어");
        lblWord.setFont(new Font("D2Coding", Font.BOLD, 18));
        lblWord.setBounds(26, 23, 75, 33);
        contentPane.add(lblWord);
        
        JLabel lblWord_1 = new JLabel("뜻");
        lblWord_1.setFont(new Font("D2Coding", Font.BOLD, 18));
        lblWord_1.setBounds(26, 99, 75, 33);
        contentPane.add(lblWord_1);
        
        textWord = new JTextField();
        // 100자로 제한
        textWord.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                
                char c = e.getKeyChar();
                if (textWord.getText().length() >= 100) {
                    JOptionPane.showMessageDialog(AddWordFrame.this, "100자 초과 작성 불가", "경고", JOptionPane.INFORMATION_MESSAGE);
                    if (c == '\b') {
                        textWord.setEditable(true);
                    } else {
                        textWord.setEditable(false);
                        e.setKeyChar('\b');
                        textWord.setEditable(true);
                    }
                }
            }
            
        });
        
        
        textWord.setFont(new Font("D2Coding", Font.PLAIN, 16));
        textWord.setBounds(26, 56, 374, 33);
        contentPane.add(textWord);
        textWord.setColumns(10);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(26, 132, 374, 244);
        contentPane.add(scrollPane);
        
        textMeaning = new JTextArea();
        // 500자로 제한
        textMeaning.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                
                char c = e.getKeyChar();
                if (textMeaning.getText().length() < 500) {
                    lblWordsCount.setText(String.format("%d / 500자", textMeaning.getText().length() + 1));
                }
                
                else if (textMeaning.getText().length() >= 500) {
                    if (c == '\b') {
                        textMeaning.setEditable(true);
                    } else {
                        textMeaning.setEditable(false);
                        e.setKeyChar('\b');
                        textMeaning.setEditable(true);
                    }
                }
            }
            
        });
        
        textMeaning.setFont(new Font("D2Coding", Font.PLAIN, 16));
        scrollPane.setViewportView(textMeaning);
        
        
        // TODO: meaning에 글자 쓸때마다 더 쓸 수 있는 글자들 수 label만들기
        lblWordsCount = new JLabel(String.format("%d / 500자", textMeaning.getText().length()));
        lblWordsCount.setFont(new Font("D2Coding", Font.ITALIC, 12));
        lblWordsCount.setHorizontalAlignment(SwingConstants.TRAILING);
        lblWordsCount.setBounds(323, 376, 75, 15);
        contentPane.add(lblWordsCount);
        
        
        // 추가 버튼
        btnAdd = new JButton("추가");
        btnAdd.addActionListener((e) -> {
            String word = textWord.getText();
            
            String meaning = textMeaning.getText();
            
            if (word.equals("")) {
                JOptionPane.showMessageDialog(AddWordFrame.this, "추가할 단어를 입력해 주세요.");
                
                return;
            } 
            
            
            dao.addWord(note, word, meaning);
            // 테이블 초기화
            ((SeeNoteFrame) parent).initWordsSeeNoteTable();
            
            dispose();
        });
        btnAdd.setBounds(26, 401, 104, 50);
        contentPane.add(btnAdd);
        
        
        // 취소 버튼
        btnCancel = new JButton("취소");
        btnCancel.addActionListener((e) -> {
            dispose();
        });
        btnCancel.setBounds(296, 401, 104, 50);
        contentPane.add(btnCancel);
        
    }
    

    
}
