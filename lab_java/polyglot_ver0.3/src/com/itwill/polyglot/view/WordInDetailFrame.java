package com.itwill.polyglot.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.itwill.polyglot.model.Word;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class WordInDetailFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblWord;
    private JTextField textWord;
    private JScrollPane scrollPane;
    private JTextArea textMeaning;
    
    private Component parent;
    private Word word;
    private JLabel lblMeaning;
    private JButton btnExit;
    private JLabel lblwordCount;
    

    /**
     * Launch the application.
     */
    public static void showWordInDetailFrame(Component parent, Word word) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WordInDetailFrame frame = new WordInDetailFrame(parent, word);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    
    WordInDetailFrame(Component parent, Word word) {
        this.parent = parent;
        this.word = word;
        initialize(parent, word);
    }
    
    
    /**
     * Create the frame.
     */
    public void initialize(Component parent, Word word) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        int x = 100;
        int y = 100;
        
        if (parent != null) {
            x = parent.getX();
            y = parent.getY();
        }
        
        setBounds(x, y, 450, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.WHITE);

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblWord = new JLabel("단어");
        lblWord.setFont(new Font("D2Coding", Font.BOLD, 18));
        lblWord.setBounds(26, 23, 36, 22);
        contentPane.add(lblWord);
        
        lblMeaning = new JLabel("뜻");
        lblMeaning.setFont(new Font("D2Coding", Font.BOLD, 18));
        lblMeaning.setBounds(26, 99, 75, 33);
        contentPane.add(lblMeaning);
        
        
        // textFields
        textWord = new JTextField(word.getWord());
        textWord.setEditable(false);
        textWord.setFont(new Font("D2Coding", Font.PLAIN, 16));
        textWord.setBounds(26, 56, 374, 33);
        contentPane.add(textWord);
        textWord.setColumns(10);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(26, 132, 374, 244);
        contentPane.add(scrollPane);
        
        textMeaning = new JTextArea(word.getMeaning());
        textMeaning.setBackground(UIManager.getColor("EditorPane.disabledBackground"));
        textMeaning.setEditable(false);
        textMeaning.setFont(new Font("D2Coding", Font.PLAIN, 16));
        scrollPane.setViewportView(textMeaning);
        
        btnExit = new JButton("나가기");
        btnExit.setFont(new Font("D2Coding", Font.PLAIN, 15));
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnExit.setBounds(311, 408, 89, 43);
        contentPane.add(btnExit);
        

    }
}
