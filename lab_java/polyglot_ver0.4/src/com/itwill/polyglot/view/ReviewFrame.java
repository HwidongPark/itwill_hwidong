package com.itwill.polyglot.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.polyglot.controller.PolyglotDao;
import com.itwill.polyglot.model.Account;
import com.itwill.polyglot.model.Language;
import com.itwill.polyglot.model.Note;
import com.itwill.polyglot.model.Word;

import javax.swing.JSplitPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class ReviewFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    private JTextField textWord;
    private JPanel panelWord;
    private JScrollPane scrollPane;
    private JTextArea textMeaning;
    private JPanel panel;
    private JRadioButton rdbtnVeryHard;
    private JRadioButton rdbtnHard;
    private JRadioButton rdbtnNormal;
    private JRadioButton rdbtnEasy;
    private JRadioButton rdbtnVeryEasy;
    private JButton btnPrevious;
    private JButton btnNext;
    private JButton btnCheckAnswer;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    
    private PolyglotDao dao = PolyglotDao.getInstance();
    private Note note;
    private Language language;
    private Component parent;
    private List<Word> words = new ArrayList<>();
    private int reviewNumber = 0;
    private int wordsIndex = 0;
    private List<Word> selectedWords = new ArrayList<>();
    private Account accountSignedIn;
    
    private JButton btnShuffle;
    
    private Random rand = new Random();
    private JButton btnNewWordsSet;
    
    
    /**
     * Launch the application.
     */
    public static void showReviewFrame(Component parent, Language language, Account accountSignedIn) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReviewFrame frame = new ReviewFrame(parent, language, accountSignedIn);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ReviewFrame(Component parent, Language language, Account accountSignedIn) {
        this.parent = parent;
        this.language = language;
        this.accountSignedIn = accountSignedIn;
        initialize(parent, language, accountSignedIn);
    }
    
    /**
     * Create the frame.
     */
    public void initialize(Component parent, Language language, Account accountSignedIn) {

        setResizable(false);
        // 학습할 언어의 모든 단어를 읽어오고 섞음
        words = dao.readWords(accountSignedIn.getAccount(), language);
        Collections.shuffle(words);
        
        // 만약 뽑힌 단어가 없으면 뽑힐 때 까지 무한정으로 뽑음
        while (selectedWords.size() == 0) {
            selectedWords = selectWords(words);
        }
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Polyglot - Review");
        
        int x = 100;
        int y = 100;
        if (parent != null) {
            x = parent.getX();
            y = parent.getY();
        }
        setBounds(x, y, 550, 681);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        panelWord = new JPanel();
        panelWord.setBounds(40, 60, 450, 100);
        contentPane.add(panelWord);
        panelWord.setLayout(new BorderLayout(0, 0));
        
        String initWord = selectedWords.get(wordsIndex).getWord();
        textWord = new JTextField(initWord);
        textWord.setFont(new Font("D2Coding", Font.BOLD, 30));
        textWord.setHorizontalAlignment(SwingConstants.CENTER);
        panelWord.add(textWord, BorderLayout.CENTER);
        textWord.setColumns(10);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(40, 175, 450, 290);
        contentPane.add(scrollPane);
        
        String initMeaning = selectedWords.get(wordsIndex).getMeaning();
        textMeaning = new JTextArea(initMeaning);
        textMeaning.setFont(new Font("D2Coding", Font.PLAIN, 20));
        scrollPane.setViewportView(textMeaning);
        
        textMeaning.setVisible(false);
        
        
        panel = new JPanel();
        panel.setBounds(40, 485, 450, 50);
        contentPane.add(panel);
        
        rdbtnVeryHard = new JRadioButton("매우 어려움");
        buttonGroup.add(rdbtnVeryHard);
        rdbtnVeryHard.setFont(new Font("D2Coding", Font.ITALIC, 10));
        panel.add(rdbtnVeryHard);
        
        rdbtnHard = new JRadioButton("어려움");
        buttonGroup.add(rdbtnHard);
        rdbtnHard.setFont(new Font("D2Coding", Font.ITALIC, 10));
        panel.add(rdbtnHard);
        
        rdbtnNormal = new JRadioButton("보통");
        buttonGroup.add(rdbtnNormal);
        rdbtnNormal.setFont(new Font("D2Coding", Font.ITALIC, 10));
        panel.add(rdbtnNormal);
        
        rdbtnEasy = new JRadioButton("쉬움");
        buttonGroup.add(rdbtnEasy);
        rdbtnEasy.setFont(new Font("D2Coding", Font.ITALIC, 10));
        panel.add(rdbtnEasy);
        
        rdbtnVeryEasy = new JRadioButton("매우 쉬움");
        buttonGroup.add(rdbtnVeryEasy);
        rdbtnVeryEasy.setFont(new Font("D2Coding", Font.ITALIC, 10));
        panel.add(rdbtnVeryEasy);
        
        
        // 이전 버튼
        btnPrevious = new JButton("이전");
        btnPrevious.addActionListener((e) -> {
            
            Word previousWord = previousWord();
            
            textWord.setText(previousWord.getWord());
            textMeaning.setText(previousWord.getMeaning());
            
            buttonGroup.clearSelection();   // 이전으로 넘어가면서 radio button선택 사라짐
        });
        btnPrevious.setBounds(40, 560, 70, 57);
        contentPane.add(btnPrevious);
        
        // TODO: 오류. 이전 후 다음 할 경우 한 바퀴로 읽어짐
        // TODO: 절대값으로 + - 해서 단어개수만큼 절대값이 될 경우 한바퀴로 간주하도록 수정하기
        // 다음 버튼
        btnNext = new JButton("다음");
        btnNext.addActionListener((e) -> {
            Word nextWord = nextWord();
            
            
            textWord.setText(nextWord.getWord());
            textMeaning.setText(nextWord.getMeaning());
            
            
            buttonGroup.clearSelection();   // 다음 페이지로 넘어가면서 radio button선택 사라짐
            
        });
        btnNext.setBounds(420, 560, 70, 57);
        contentPane.add(btnNext);
        
        
        // 섞기 버튼
        btnShuffle = new JButton("섞기");
        btnShuffle.addActionListener((e) -> {
            Collections.shuffle(words);
            wordsIndex = 0;
            String firstWord = words.get(wordsIndex).getWord();
            String firstMeaning = words.get(wordsIndex).getMeaning();
            textWord.setText(firstWord);
            textMeaning.setText(firstMeaning);
            
            JOptionPane.showMessageDialog(ReviewFrame.this, "단어의 순서가 랜덤으로 섞였습니다.", "단어 순서 변경 완료", JOptionPane.INFORMATION_MESSAGE);
        });
        btnShuffle.setBounds(420, 27, 70, 23);
        contentPane.add(btnShuffle);
        
        // 정답 확인 누를 때만 답 보이도록 설정하기
        btnCheckAnswer = new JButton("정답 확인");
        btnCheckAnswer.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                textMeaning.setVisible(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                textMeaning.setVisible(false);
            }
            
            
            
        });
        btnCheckAnswer.setBounds(204, 560, 122, 57);
        contentPane.add(btnCheckAnswer);
        
        // 단어 세트 새로뽑기 버튼
        btnNewWordsSet = new JButton("새 단어세트");
        btnNewWordsSet.addActionListener((e) -> {   // 단어세트 새로뽑기 액션리스너
            selectedWords = new ArrayList<>();
            while(selectedWords.size() == 0) {
                selectedWords = selectWords(words);
            }
            wordsIndex = 0;
            reviewNumber = 0;
            textWord.setText(selectedWords.get(wordsIndex).getWord());
            textMeaning.setText(selectedWords.get(wordsIndex).getMeaning());
            
            JOptionPane.showMessageDialog(ReviewFrame.this, "새로운 복습 단어 세트가 구성 되었습니다.", "새 단어 선정 완료", JOptionPane.INFORMATION_MESSAGE);
            
        });
        btnNewWordsSet.setBounds(273, 27, 133, 23);
        contentPane.add(btnNewWordsSet);
        

    }
    
    /**
     * 다음 순번의 word를 리턴
     * @return
     */
    private Word nextWord() {
        
        // 라이오 버튼에 입력된 난이도를 업데이트
        // 라디오버튼 선택한거 없도록 만들기
        String difficulty = chosenDifficulty();
        Word currentWord = selectedWords.get(wordsIndex);

        if (!difficulty.equals("")) {
            int result = dao.updateDifficulty(currentWord, difficulty);
            System.out.println(result + "행이 업데이트 되었습니다.");
        }
        
        
        if (wordsIndex == selectedWords.size() -1) {
            reviewNumber++;
            wordsIndex = 0;
            JOptionPane.showMessageDialog(this, String.format("%d바퀴 학습 완료", reviewNumber));
            return selectedWords.get(wordsIndex);   // 0번 index 리턴
        }
        
        
        return selectedWords.get(++wordsIndex); // 그 다음 인덱스 리턴
        
    }
    
    
    /**
     * 이전 순번의 word를 리턴
     * @return
     */
    private Word previousWord() {
        
        // 라이오 버튼에 입력된 난이도를 업데이트
        String difficulty = chosenDifficulty();
        Word currentWord = selectedWords.get(wordsIndex);

        if (!difficulty.equals("")) {
            int result = dao.updateDifficulty(currentWord, difficulty);
            System.out.println(result + "행이 업데이트 되었습니다.");
        }
        
        
        if (wordsIndex == 0) {
            JOptionPane.showMessageDialog(this, "첫번째 단어 입니다.");
            
            return words.get(0);
        }
        
        return selectedWords.get(--wordsIndex);
        
    }
    
    
    // 50개 단어를 난이도별 가중치를 주어 선정
    /**
     * 난이도 가중치를 고려하여 학습 언어의 단어를 최대 50개까지 포함할 수 있는 리스트를 반환함.
     * @param words
     * @return
     */
    private List<Word> selectWords(List<Word> words) {
        List <Word> result = new ArrayList<>();
        
        

        int count = 0;
        for (Word word : words) {
            if (word.getDifficulty() == null) {
                if (rand.nextInt(100) < 60) {
                    result.add(word);
                    count++;
                }
            } else if (word.getDifficulty().equals("Normal")) {
                
                if (rand.nextInt(100) < 60) {
                    result.add(word);
                    count++;
                }
            } else if (word.getDifficulty().equals("Very Hard")) {
                
                result.add(word);
                count++;
            } else if (word.getDifficulty().equals("Hard")) {
               
                if (rand.nextInt(100) < 80) {
                    result.add(word);
                    count++;
                }
            } else if (word.getDifficulty().equals("Easy")) {
                
                if (rand.nextInt(100) < 40) {
                    result.add(word);
                    count++;
                }
            } else if (word.getDifficulty().equals("Very Easy")) {
                
                if (rand.nextInt(100) < 20) {
                    result.add(word);
                    count++;
                }
            }
            
            if (count >= 50) {
                break;
            }
        }
        
        return result;   
    }
    
    

    
    private String chosenDifficulty() {
        String result = "";
        
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements() ; buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                result = button.getText();
            }
        }
        
        if (result.equals("매우 어려움")) {
            result = "Very Hard";
        } else if (result.equals("어려움")) {
            result = "Hard";
        } else if (result.equals("보통")) {
            result = "Normal";
        } else if (result.equals("쉬움")) {
            result = "Easy";
        } else if (result.equals("매우 쉬움")) {
            result = "Very Easy";
        }
        
        return result;
    }
}