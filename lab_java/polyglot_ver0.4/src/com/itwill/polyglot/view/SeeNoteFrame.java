package com.itwill.polyglot.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itwill.polyglot.controller.PolyglotDao;
import com.itwill.polyglot.model.Note;
import com.itwill.polyglot.model.Word;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class SeeNoteFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JScrollPane scrollPane;
    private JLabel lblNoteName;
    private JTable tableWords;
    private JButton btnDelete;
    private JButton btnShowDetail;
    
    private String[] COLUMN_NAME = {"단어", "뜻"};
    private JButton btnAddWord;
    
    private PolyglotDao dao = PolyglotDao.getInstance();
    private Note note;
    private Component parent;
    private List<Word> words;
    private JButton btnUpdate;
    private JButton btnAddWord_1;
    private JTextField textSearch;
    private JButton btnSearch;
    private JButton btnHome;
    
    
    

    /**
     * Launch the application.
     */
    public static void showSeeNote(Component parent, Note note) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SeeNoteFrame frame = new SeeNoteFrame(parent, note);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public SeeNoteFrame(Component parent, Note note) {
        this.parent = parent;
        this.note = note;
        initialize(parent, note);
    }
    
    /**
     * Create the frame.
     */
    public void initialize(Component parnet, Note note) {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Polyglot - Note");
        
        int x = 100;
        int y = 100;
        
        if (parent != null) {
        	x = parent.getX() + 20;
        	y = parent.getY() + 20;
        }
        
        setBounds(x, y, 600, 650);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.WHITE);

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblNoteName = new JLabel(String.format("%s (%s)", note.getNoteName(), note.getLanguageInKorean()));
        lblNoteName.setFont(new Font("D2Coding", Font.BOLD, 20));
        lblNoteName.setBounds(12, 25, 326, 41);
        contentPane.add(lblNoteName);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 128, 560, 378);
        contentPane.add(scrollPane);
        
        // 단어 테이블
        tableWords = new JTable();
        tableWords.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableWords.getTableHeader().setResizingAllowed(false);
        tableWords.setFont(new Font("D2Coding", Font.PLAIN, 18));
        tableWords.setRowHeight(40);
        tableWords.getTableHeader().setReorderingAllowed(false);
        DefaultTableModel tableModel = new DefaultTableModel(null, COLUMN_NAME) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        initWordsSeeNoteTable();
        
        
        scrollPane.setViewportView(tableWords);
        
        // 단어 삭제 버튼
        btnDelete = new JButton("삭제");

        btnDelete.addActionListener((e) -> {    // 삭제버튼 Action Listener
            int selectedWordIndex = tableWords.getSelectedRow();
            if (selectedWordIndex == -1) {   //  선택된 단어 없을 시 빠져나감
                JOptionPane.showMessageDialog(SeeNoteFrame.this, "삭제할 단어를 선택해 주세요",
                        "삭제 불가", JOptionPane.INFORMATION_MESSAGE);
                
                return;
            }
            
            Word selectedWord = words.get(selectedWordIndex);
            int confirmDelete= 
                    JOptionPane.showConfirmDialog(SeeNoteFrame.this, String.format("선택한 단어: %s%n정말 삭제 하시겠습니까?", selectedWord.getWord()),
                    "삭제 확인", JOptionPane.YES_NO_OPTION);
            if (confirmDelete == JOptionPane.YES_OPTION) {
                dao.deleteWord(selectedWord);
                initWordsSeeNoteTable();
            }
            
        });
        btnDelete.setBounds(169, 535, 85, 47);
        contentPane.add(btnDelete);
        
        
        // 단어 상세 보기 버튼
        btnShowDetail = new JButton("상세 보기");

        btnShowDetail.addActionListener((e) -> {
           // TODO: 우선순위 높음. 단어 상세보기 버튼 작업
            int selectedWordIndex = tableWords.getSelectedRow();
            
            if (selectedWordIndex == -1) {
                JOptionPane.showMessageDialog(SeeNoteFrame.this, "단어를 선택해 주세요");
                return;
            }
            
            Word word = words.get(selectedWordIndex);
            
            WordInDetailFrame.showWordInDetailFrame(parnet, word);
        });
        btnShowDetail.setBounds(475, 535, 97, 47);
        contentPane.add(btnShowDetail);
        
        
        // 단어 추가 버튼
        btnAddWord = new JButton("추가");
        btnAddWord.addActionListener((e) -> {
            AddWordFrame.showAddWordFrame(SeeNoteFrame.this, note);
        });
        btnAddWord.setBounds(12, 535, 97, 47);
        contentPane.add(btnAddWord);
        
        
        // 단어 수정 버튼
        btnUpdate = new JButton("수정");
        btnUpdate.addActionListener((e) -> {
           int selectedWordIndex = tableWords.getSelectedRow();
           if (selectedWordIndex == -1) {
               JOptionPane.showMessageDialog(SeeNoteFrame.this, "수정할 단어를 선택해 주세요", "에러", JOptionPane.WARNING_MESSAGE);
               return;
           }
           
           Word word = words.get(selectedWordIndex);
           
           
           UpdateWordFrame.showUpdateWordFrame(SeeNoteFrame.this, word);
        });
        btnUpdate.setBounds(326, 535, 97, 47);
        contentPane.add(btnUpdate);
        
        textSearch = new JTextField();
        // 50자로 입력 제한
        textSearch.addKeyListener(new KeyAdapter() {
        	
        	@Override
			public void keyTyped(KeyEvent e) {
				// 글자수 50글자로 제한
				char c = e.getKeyChar();
				if (textSearch.getText().length() >= 50) {
					if (c == '\b') {
						textSearch.setEditable(true);
					} else {
						textSearch.setEditable(false);
						e.setKeyChar('\b');
						textSearch.setEditable(true);
					}
				}
				
			}
        	
        });
        
        
        textSearch.setFont(new Font("D2Coding", Font.PLAIN, 15));
        textSearch.setBounds(131, 91, 318, 27);
        contentPane.add(textSearch);
        textSearch.setColumns(10);
        
        
        // 검색 기능 구현
        btnSearch = new JButton("검색");
        btnSearch.addActionListener((e) -> {
            String searchingWord = textSearch.getText();
            if (searchingWord.equals("")) {
                JOptionPane.showMessageDialog(SeeNoteFrame.this, "검색어를 입력해 주세요");
                return;
            }
            
            initSearchedWordSeeNoteTable(searchingWord);
            btnHome.setVisible(true);
            btnAddWord.setVisible(false);
            
        });
        btnSearch.setBounds(494, 93, 78, 23);
        contentPane.add(btnSearch);
        
        // 홈기능 구현
        btnHome = new JButton("노트 홈");
        btnHome.addActionListener((e) -> {
            btnAddWord.setVisible(true);
            initWordsSeeNoteTable();
            btnHome.setVisible(false);
        });
        btnHome.setVisible(false);
        btnHome.setBounds(12, 91, 97, 27);
        contentPane.add(btnHome);
        

    }
    
    
    // 메서드들
    /**
     * 해당 노트의 단어들을 테이블에 초기화
     */
    void initWordsSeeNoteTable() {
        words = dao.readWords(note);
        DefaultTableModel tableModel = new DefaultTableModel(null, COLUMN_NAME) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }    
        };
        
        
        for (Word word : words) {
            Object[] row = {
                    word.getWord(),
                    word.getMeaning()
            };
            tableModel.addRow(row);
        }
        
        tableWords.setModel(tableModel);

    }
    
    /**
     * 검색어를 포함한 단어들을 테이블에 초기화
     * @param search
     */
    void initSearchedWordSeeNoteTable(String search) {
        words = dao.searchWordsInLanguage(note, search);
        DefaultTableModel tableModel = new DefaultTableModel(null, COLUMN_NAME) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        for (Word word : words) {
            Object[] row = {
                    word.getWord(),
                    word.getMeaning()
            };
            tableModel.addRow(row);
        }
        
        tableWords.setModel(tableModel);
    }


}
