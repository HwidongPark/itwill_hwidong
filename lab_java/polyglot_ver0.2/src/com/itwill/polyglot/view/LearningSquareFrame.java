package com.itwill.polyglot.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.polyglot.controller.PolyglotDao;
import com.itwill.polyglot.model.Account;
import com.itwill.polyglot.model.Language;
import com.itwill.polyglot.model.Note;
import com.itwill.polyglot.model.Word;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LearningSquareFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    private Component parent;
    private JLabel lblLanLearning;
    private JButton btnSwitch;
    private JButton btnAddNote;
    private JButton btnLearning;
    
    private PolyglotMain app;
    private PolyglotDao dao = PolyglotDao.getInstance();
    private Language language;
    private Account accountSignedIn;
    private List<String> languageToStudyList = new ArrayList<>(List.of("영어", "한국어", "스페인어", "이탈리아어",
            "독일어", "일본어", "중국어(만다린)", "중국어(광둥어)", "스웨덴어", 
            "프랑스어", "히브리어", "아랍어"));
    private List<Note> searchedNotes = new ArrayList<>();
    private List<Word> searchedWords = new ArrayList<>();
    private String[] searchedWordTableColumb = {"단어", "뜻", "노트"};
    DefaultTableModel searchedWordsTableModel;
    

    private List<Note> listValues = new ArrayList<>();
    private JScrollPane scrollPane;
    private JList<String> listNotes;
    private JTextField textSearch;
    private JButton btnSearch;
    private JLabel lblNoteList;
    private JTabbedPane tabbedPane;
    private JList listSearchedNotes;
    private JTable tableSearchedWords;
    private JButton btnHome;
    private JScrollPane scrollPaneSearchTable;
    private JScrollPane scrollPaneSearchList;
    private JButton btnSignOut;
    private JButton btnMyInfo;
    
    
    /**
     * Launch the application.
     */
    public static void showLearningSquareFrame(Component parent, Language language, PolyglotMain app, Account accountSignedIn) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LearningSquareFrame frame = new LearningSquareFrame(parent, language, app, accountSignedIn);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public LearningSquareFrame(Component parent, Language language, PolyglotMain app, Account accountSignedIn) {
        this.app = app;
        this.accountSignedIn = accountSignedIn;
        this.language = language;
        this.parent = parent;
        initialize(parent);
    }
    
    /**
     * Create the frame.
     */
    public void initialize(Component parent) {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        int x = 100;
        int y = 100;
        if (parent != null) {
            x = parent.getX() + parent.getWidth();
            y = parent.getY();
        }
        setBounds(x, y, 603, 730);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        contentPane.setBackground(Color.WHITE);
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // 검색할 대 사용할 탭 패널..
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        
                tabbedPane.setBackground(new Color(255, 255, 255));
                tabbedPane.setBounds(12, 173, 560, 350);
                // 일단 안보이도록 함
                tabbedPane.setVisible(false);
                
                contentPane.add(tabbedPane);
                
                listSearchedNotes = new JList();
                // TODO: 확인해보기
                listSearchedNotes.setFont(new Font("D2Coding", Font.PLAIN, 18));
                AbstractListModel<String> listsearchedNoteModel = new AbstractListModel<>() {
                    @Override
                    public int getSize() {
                        return 0;
                    }
                    
                    @Override
                    public String getElementAt(int index) {
                        return null;
                    }
                };
                
                listSearchedNotes.setFixedCellHeight(40);
                listSearchedNotes.setModel(listsearchedNoteModel);
                
                scrollPaneSearchList = new JScrollPane(listSearchedNotes);
                scrollPaneSearchList.setBounds(12, 173, 560, 350);
                
                
                tabbedPane.addTab("노트 검색 결과", scrollPaneSearchList);
                
                
                
                tableSearchedWords = new JTable();
                tableSearchedWords.getTableHeader().setResizingAllowed(false);
                tableSearchedWords.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                tableSearchedWords.setRowHeight(40);
                searchedWordsTableModel = new DefaultTableModel(null, searchedWordTableColumb) {

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                    
                };
                tableSearchedWords.getTableHeader().setReorderingAllowed(false);
                tableSearchedWords.setModel(searchedWordsTableModel);
                tableSearchedWords.setFont(new Font("D2Coding", Font.PLAIN, 18));
                
                // TODO: 실험
                scrollPaneSearchTable = new JScrollPane(tableSearchedWords);
                scrollPaneSearchTable.setBounds(12, 173, 560, 350);

                // TODO: 실험
//                tabbedPane.addTab("단어 검색 결과", tableSearchedWords);
                tabbedPane.addTab("단어 검색 결과", scrollPaneSearchTable);
        
                
                
        lblLanLearning = new JLabel(language.getLanguageInKorean() + " 학습장");
        lblLanLearning.setFont(new Font("D2Coding", Font.BOLD, 25));
        lblLanLearning.setBounds(12, 27, 270, 25);
        contentPane.add(lblLanLearning);
        
        
        // 다른 언어 이동가능하도록 콤보박스 Array 만들기. Default로 현재 언어를 콤보박스에 선택되도록 함
        JComboBox<String> comboBoxSwitch = new JComboBox<>();
        DefaultComboBoxModel<String> comboBoxSwitchModel = new DefaultComboBoxModel<String>();
        comboBoxSwitchModel.addAll(languageToStudyList);
        comboBoxSwitchModel.setSelectedItem(language.getLanguageInKorean());
        comboBoxSwitch.setModel(comboBoxSwitchModel);
        
        
        comboBoxSwitch.setBounds(398, 29, 101, 23);
        contentPane.add(comboBoxSwitch);
        
        // 다른 언어로 이동하는 버튼
        btnSwitch = new JButton("이동");
        btnSwitch.addActionListener((e) -> { 
            String selectedLanguageInKorean = (String) comboBoxSwitch.getSelectedItem();
            String selectedLangugeCode = app.getLanguageCode(selectedLanguageInKorean);
            language = dao.readLanguage(selectedLangugeCode);
            lblLanLearning.setText(language.getLanguageInKorean() + " 학습장");
            initNotesList();
            
        });
        btnSwitch.setBounds(504, 29, 68, 23);
        contentPane.add(btnSwitch);
        
        JLabel lblSwitch = new JLabel("*다른 언어로 이동:");
        lblSwitch.setFont(new Font("D2Coding", Font.ITALIC, 9));
        lblSwitch.setBounds(410, 10, 134, 15);
        contentPane.add(lblSwitch);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 173, 560, 350);
        contentPane.add(scrollPane);
        
        
        // 노트 리스트
        listNotes = new JList<>();
        listNotes.setFont(new Font("D2Coding", Font.PLAIN, 18));
        initNotesList();   
        scrollPane.setViewportView(listNotes);
        
        
        // 노트 추가 버튼
        btnAddNote = new JButton("노트 추가");
        btnAddNote.addActionListener((e) -> {   // 노트버튼 추가 ActionListener***
            String noteName = JOptionPane.showInputDialog(LearningSquareFrame.this, "새 노트 이름을 입력해 주세요.");
            
            if(noteName == null) {
                return; // 유저가 취소버튼을 누른 경우 창 닫기
            } else if (noteName.equals("")) {
                JOptionPane.showMessageDialog(LearningSquareFrame.this,
                        "노트 추가를 위해선 이름을 입력하셔야 합니다.", "노트 추가 불가", JOptionPane.WARNING_MESSAGE);
                
                return; // 노트이름 입력 안했을 시 경고문 주고 나옴.
            }
            
            // 해당 언어에 같은 이름의 노트가 있을 시 경고문
            List<Note> existingNotes = dao.readNotesInLanguage(accountSignedIn.getAccount(), language);
            for (Note existingNote : existingNotes) {
                if (noteName.equals(existingNote.getNoteName())) {
                    JOptionPane.showMessageDialog(LearningSquareFrame.this,
                            String.format("%s노트에 같은 이름의 노트가 존재합니다.", language.getLanguageInKorean()),
                            "노트 생성 불가", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            dao.addNote(language, noteName, accountSignedIn.getAccount());            
            initNotesList();
            
        });

        btnAddNote.setBounds(12, 533, 160, 20);
        contentPane.add(btnAddNote);
        
        
        // 선택된 노트 보기 (상세보기)
        JButton btnSeeNote = new JButton("상세보기");
        btnSeeNote.addActionListener(new ActionListener() { // 노트선택 버튼 ActionListener
            public void actionPerformed(ActionEvent e) {
                
                if (scrollPane.isVisible()) {   // 홈 화면인 경우
                    if (listNotes.getSelectedIndex() == -1) {   // 노트선택X 경우 
                        JOptionPane.showMessageDialog(LearningSquareFrame.this, "노트를 선택해 주세요.");
                        
                        return;
                    }
                    
                    // 선택된 노트
                    Note selectedNote = listValues.get(listNotes.getSelectedIndex());
                    SeeNoteFrame.showSeeNote(LearningSquareFrame.this, selectedNote);
                }
                
                // TODO: 우선순위 이거 tab마다 동작 
                if (tabbedPane.isVisible()) {
                    int selectedTabIndex = tabbedPane.getSelectedIndex();
                    if (selectedTabIndex == 0) {    // 노트검색 화면
                        int selectedNoteIndex = listSearchedNotes.getSelectedIndex();
                        if (selectedNoteIndex == -1) {
                            JOptionPane.showMessageDialog(LearningSquareFrame.this, "노트를 선택해 주세요.");
                            return;
                        }
                        Note selectedNote = searchedNotes.get(selectedNoteIndex);
                        SeeNoteFrame.showSeeNote(LearningSquareFrame.this, selectedNote);
                    } else if (selectedTabIndex == 1) {
                        int selectedWordIndex = tableSearchedWords.getSelectedRow();
                        if (selectedWordIndex == -1) {
                            JOptionPane.showMessageDialog(LearningSquareFrame.this, "단어를 선택해 주세요.");
                            return;
                        }
                        Word selectedWord = searchedWords.get(selectedWordIndex);
                        WordInDetailFrame.showWordInDetailFrame(LearningSquareFrame.this, selectedWord);
                    } else {
                        JOptionPane.showMessageDialog(LearningSquareFrame.this, "탭패인에서 뭔가 잘못됐습니다.");
                    }
                }

            }
        });
        btnSeeNote.setBounds(208, 533, 160, 20);
        contentPane.add(btnSeeNote);
        
        
        // 노트 삭제 버튼
        JButton btnDeleteNote = new JButton("노트 삭제");
        btnDeleteNote.addActionListener((e) -> {    // 노트 삭제버튼 ActionListener
            int selectedNoteIndex = listNotes.getSelectedIndex();
            
            if (selectedNoteIndex == -1) {  // 삭제 행 선택 안 했을 시 빠져나옴
                JOptionPane.showMessageDialog(LearningSquareFrame.this, "삭제할 노트를 선택해주세요.", "삭제 불가", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Note selectedNote = listValues.get(selectedNoteIndex);
            
            // 정말 삭제할지 확인하는 dialog
            int confirmDelete = 
                    JOptionPane.showConfirmDialog(LearningSquareFrame.this, String.format("삭제할 노트: %s%n정말 삭제하시겠습니까?", selectedNote.getNoteName()),
                            "삭제 실패", JOptionPane.YES_NO_OPTION );
            if (confirmDelete == JOptionPane.YES_OPTION) {
                dao.deleteNote(selectedNote);
                initNotesList();
            } else {
                return;
            }
             
        });
        btnDeleteNote.setBounds(410, 533, 160, 20);
        contentPane.add(btnDeleteNote);
        
        
        // 학습 시작 버튼
        btnLearning = new JButton("노트 학습");

        btnLearning.addActionListener((e) -> {
            int selectedNoteIndex = listNotes.getSelectedIndex();
            
            if (selectedNoteIndex == -1) {
                JOptionPane.showMessageDialog(LearningSquareFrame.this, "학습할 노트를 선택해 주세요.");
                return;
            }
            
            Note note = listValues.get(selectedNoteIndex);
            // 만약 노트에 단어 저장 안 돼있으면 경고창 띄우기.
            // DAO로 단어리스트 가져오고 size 0이면 dialog
            List<Word> wordsInNote = dao.readWords(note);
            if (wordsInNote.size() == 0) {
                JOptionPane.showMessageDialog(LearningSquareFrame.this, "해당 노트에 저장된 단어가 없습니다.");
                return;
            }
            ExerciseFrame.showExerciseFrame(parent, note);
        });
        btnLearning.setBounds(12, 579, 270, 90);
        contentPane.add(btnLearning);
        
        
        // 복습 버튼
        JButton btnReview = new JButton("전체 복습");
        btnReview.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Word> words = dao.readWords(accountSignedIn.getAccount(), language);
                
                if (words.size() == 0) {
                    JOptionPane.showMessageDialog(LearningSquareFrame.this, "해당 언어에 저장된 단어가 없습니다.", "복습 불가", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                ReviewFrame.showReviewFrame(LearningSquareFrame.this, language, accountSignedIn);
            }
        });
        btnReview.setBounds(302, 579, 270, 90);
        contentPane.add(btnReview);
        
        
        // 검색 textField
        textSearch = new JTextField();
        textSearch.setBounds(125, 104, 357, 23);
        contentPane.add(textSearch);
        textSearch.setColumns(10);
        
        
        // TODO: 검색 버튼 
        btnSearch = new JButton("검색");
        btnSearch.addActionListener((e) -> {
            if (textSearch.getText().equals("")) {
                JOptionPane.showMessageDialog(LearningSquareFrame.this, "검색어를 입력해 주세요.");
                return;
            }
            tabbedPane.setVisible(true);
            scrollPane.setVisible(false);
            btnDeleteNote.setVisible(false);
            btnAddNote.setVisible(false);
            // TODO: 크기 확인
            btnSeeNote.setBounds(12, 533, 560, 20);
            
            // 검색결과 탭패널에 나타냄
            initSearchedWordsList(textSearch.getText());
            initSearchNotesList(textSearch.getText());
            lblNoteList.setVisible(false);
            
            // 홈버튼 띄움
            btnHome.setVisible(true);
            
        });
        btnSearch.setBounds(494, 104, 78, 23);
        contentPane.add(btnSearch);
        
        // TODO: 홈버튼
        btnHome = new JButton("홈으로..");
        btnHome.addActionListener((e) -> {  // 홈버튼 ActionListener
            tabbedPane.setVisible(false);
            scrollPane.setVisible(true);
            btnHome.setVisible(false);
            btnDeleteNote.setVisible(true);
            btnAddNote.setVisible(true);
            btnSeeNote.setBounds(208, 533, 160, 20);
            
            initNotesList();
        });
        btnHome.setBounds(12 ,104 , 87, 23);
        btnHome.setVisible(false);
        contentPane.add(btnHome);
        DefaultComboBoxModel<String> comboBoxSearchDefaultModdel = new DefaultComboBoxModel(new String[] {"노트", "단어", "노트+단어"});
        
        
        lblNoteList = new JLabel("노트 리스트");
        lblNoteList.setFont(new Font("D2Coding", Font.BOLD, 15));
        lblNoteList.setBounds(12, 156, 134, 15);
        contentPane.add(lblNoteList);
        
        btnSignOut = new JButton("로그아웃");
        btnSignOut.addActionListener((e) -> {       // 로그아웃 버튼 ActionListener
            // 로그아웃 시 로그인 화면으로 이동
            int option = JOptionPane.showConfirmDialog(LearningSquareFrame.this, "정말 로그아웃 하시겠습니까?", "로그아웃확인",
                    JOptionPane.YES_NO_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {
                PolyglotMain.showPolyglotMain();
                dispose();
            } else {
                return;
            }
            
        });
        btnSignOut.setFont(new Font("D2Coding", Font.PLAIN, 9));
        btnSignOut.setBounds(12, 58, 78, 23);
        contentPane.add(btnSignOut);
        
        btnMyInfo = new JButton("내 정보 보기");
        btnMyInfo.addActionListener((e) -> {
            MyInfoFrame.showMyInfoFrame(LearningSquareFrame.this, accountSignedIn);
        });
        // TODO: 내정보 보기 frame가서 내 정보 보여주고 수정 가능하게 하기
        btnMyInfo.setFont(new Font("D2Coding", Font.PLAIN, 8));
        btnMyInfo.setBounds(103, 58, 93, 23);
        contentPane.add(btnMyInfo);
        searchedWordsTableModel = new DefaultTableModel(null, searchedWordTableColumb);
    }
    
    
    
    /**
     * Learning Sqaure에서 노트 리스트를 초기화 함.
     * element의 ArrayList도 초기화.
     */
    private void initNotesList() {
        //리스트에 DB노트 추가
        listValues = dao.readNotesInLanguage(accountSignedIn.getAccount(), language);
        
        AbstractListModel<String> listInitModel = new AbstractListModel<>() {
            @Override
            public int getSize() {
                return LearningSquareFrame.this.listValues.size();
            }
            
            @Override
            public String getElementAt(int index) {
                return LearningSquareFrame.this.listValues.get(index).getNoteName();
            }
        };
        
        listNotes.setFixedCellHeight(40);
        listNotes.setModel(listInitModel);
        
    }
    
    private void initSearchedWordsList(String search) {
        searchedWords = dao.searchWordsInLanguage(language, search, accountSignedIn.getAccount());
        searchedWordsTableModel = new DefaultTableModel(null, searchedWordTableColumb) {

            @Override
            public boolean isCellEditable(int row, int column) {
                // TODO Auto-generated method stub
                return false;
            }
            
        };
        
        for (Word word : searchedWords) {
            String[] row = {
                    word.getWord(),
                    word.getMeaning(),
                    word.getNoteName()
            };
            
            searchedWordsTableModel.addRow(row);    // 검색 단어 테이블 행에 추가
        }
        
        tableSearchedWords.setModel(searchedWordsTableModel);
    }
    
    private void initSearchNotesList(String search) {
        searchedNotes = dao.searchNotesInLanguage(language, search, accountSignedIn.getAccount());
        
        AbstractListModel<String> notesModel = new AbstractListModel<>() {

            @Override
            public int getSize() {
                return searchedNotes.size();
            }

            @Override
            public String getElementAt(int index) {
                return searchedNotes.get(index).getNoteName();
            }  
        };
        
        listSearchedNotes.setFixedCellHeight(40);
        listSearchedNotes.setModel(notesModel);
    }
}
