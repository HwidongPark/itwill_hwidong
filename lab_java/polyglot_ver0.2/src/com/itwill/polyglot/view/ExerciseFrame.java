package com.itwill.polyglot.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.polyglot.controller.PolyglotDao;
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

public class ExerciseFrame extends JFrame {

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
    private Component parent;
    private List<Word> words = new ArrayList<>();
    private int reviewNumber = 0;
    private int wordsIndex = 0;
    private JButton btnShuffle;
    private int numOfWordsCovered = 0;
    
    
    /**
     * Launch the application.
     */
    public static void showExerciseFrame(Component parent, Note note) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExerciseFrame frame = new ExerciseFrame(parent, note);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ExerciseFrame(Component parent, Note note) {
        this.parent = parent;
        this.note = note;
        initialize(parent, note);
    }
    
    /**
     * Create the frame.
     */
    public void initialize(Component parent, Note note) {
        setResizable(false);
        // 노트에 단어 초기화 및 섞기
        words = dao.readWords(note);
        Collections.shuffle(words);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
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
        
        String initWord = words.get(wordsIndex).getWord();
        textWord = new JTextField(initWord);
        textWord.setFont(new Font("D2Coding", Font.BOLD, 30));
        textWord.setHorizontalAlignment(SwingConstants.CENTER);
        panelWord.add(textWord, BorderLayout.CENTER);
        textWord.setColumns(10);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(40, 175, 450, 290);
        contentPane.add(scrollPane);
        
        String initMeaning = words.get(wordsIndex).getMeaning();
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
            JOptionPane.showMessageDialog(ExerciseFrame.this, "단어의 순서가 랜덤으로 섞였습니다.", "단어 순서 변경 완료", JOptionPane.INFORMATION_MESSAGE);
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
        

    }
    
    /**
     * 다음 순번의 word를 리턴
     * @return
     */
    private Word nextWord() {
        
        // 라이오 버튼에 입력된 난이도를 업데이트
        // 라디오버튼 선택한거 없도록 만들기
        String difficulty = chosenDifficulty();
        Word currentWord = words.get(wordsIndex);
        System.out.println(currentWord);
        if (!difficulty.equals("")) {
            System.out.println(difficulty);
            int result = dao.updateDifficulty(currentWord, difficulty);
            System.out.println(result + "행이 업데이트 되었습니다.");
        }
        
        
        if (wordsIndex == words.size() -1) {
            reviewNumber++;
            wordsIndex = 0;
            JOptionPane.showMessageDialog(this, String.format("%d바퀴 학습 완료", reviewNumber));
            return words.get(wordsIndex);   // 0번 index 리턴
        }
        
        
        return words.get(++wordsIndex); // 그 다음 인덱스 리턴
        
    }
    
    /**
     * 이전 순번의 word를 리턴
     * @return
     */
    private Word previousWord() {
        
        if (wordsIndex == 0) {
            JOptionPane.showMessageDialog(this, "첫번째 단어 입니다.");
            
            return words.get(0);
        }
        
        return words.get(--wordsIndex);
        
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
