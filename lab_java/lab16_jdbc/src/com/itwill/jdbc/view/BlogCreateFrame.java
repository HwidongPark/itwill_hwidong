package com.itwill.jdbc.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.jdbc.controller.BlogDao;
import com.itwill.jdbc.model.Blog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.beans.beancontext.BeanContextContainerProxy;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDialog;

public class BlogCreateFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textTitle;
    private JTextField textAuthor;
    
    private BlogDao  dao = BlogDao.getInstance();
    private Component parent;
    private JLabel lblTitle;
    private JLabel lblContent;
    private JScrollPane scrollPane;
    private JLabel lblAuthor;
    private JButton btnSave;
    private JButton btnCancel;
    private JTextArea textContent;
    
    private BlogMain app;

    /**
     * Launch the application.
     */
    public static void showBlogCreateFrame(Component parent, BlogMain app) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BlogCreateFrame frame = new BlogCreateFrame(parent, app);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    // 생성자
    public BlogCreateFrame(Component parent, BlogMain app) {
        this.dao = BlogDao.getInstance();
        this.parent = parent;
        this.app = app;
        initialize();
    }
    
    /**
     * Create the frame.
     */
    public void initialize() {
        setTitle("새 포스트 작성");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // 현재 창만 닫기
        
        int x = 100;
        int y = 100;
        if (parent != null) {
            x = parent.getX() + parent.getWidth();
            y = parent.getY();
        }
        
        setBounds(x, y, 550, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblTitle = new JLabel("제목");
        lblTitle.setFont(new Font("굴림", Font.PLAIN, 20));
        lblTitle.setBounds(30, 47, 67, 39);
        contentPane.add(lblTitle);
        
        textTitle = new JTextField();
        textTitle.setBounds(30, 84, 469, 39);
        contentPane.add(textTitle);
        textTitle.setColumns(10);
        
        lblContent = new JLabel("내용");
        lblContent.setFont(new Font("굴림", Font.PLAIN, 20));
        lblContent.setBounds(30, 133, 67, 39);
        contentPane.add(lblContent);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 171, 469, 235);
        contentPane.add(scrollPane);
        
        textContent = new JTextArea();
        textContent.setFont(new Font("Monospaced", Font.PLAIN, 15));
        scrollPane.setViewportView(textContent);
        
        lblAuthor = new JLabel("작성자");
        lblAuthor.setFont(new Font("굴림", Font.PLAIN, 20));
        lblAuthor.setBounds(30, 416, 67, 39);
        contentPane.add(lblAuthor);
        
        textAuthor = new JTextField();
        textAuthor.setBounds(30, 448, 469, 33);
        contentPane.add(textAuthor);
        textAuthor.setColumns(10);
        
        btnSave = new JButton("저장");
        btnSave.addActionListener((e) -> {
           createNewBlogPost();
        });
        btnSave.setFont(new Font("굴림", Font.BOLD, 18));
        btnSave.setBounds(208, 491, 137, 60);
        contentPane.add(btnSave);
        
        btnCancel = new JButton("취소");
        btnCancel.addActionListener((e) -> dispose());
        btnCancel.setFont(new Font("굴림", Font.BOLD, 18));
        btnCancel.setBounds(362, 491, 137, 60);
        contentPane.add(btnCancel);
    }
    
    private void createNewBlogPost() {
        // 제목, 내용, 작성자에 입력된 내용을 읽고, Blog 객체를 생성해서,
        // DAO 메서드를 사용해서 DB에 삽입
        String title = textTitle.getText();
        String content = textContent.getText();
        String author = textAuthor.getText();
        if (title.equals("") || content.equals("") || author.equals("")) {
            JOptionPane.showMessageDialog(BlogCreateFrame.this, "제목, 내용, 작성자는 반드시 입력되어야 합니다.");
            
            return;
        }
        
        Blog blog = new Blog(null, title, content, author, null, null);
        int result = dao.create(blog);
        // TODO: DB insert 성공하면 메인 프레임에게 알려줌 -> 메인 프레임에서 테이블을 새로고침.
        if (result == 1) {
            dispose();
            app.notifyBlogCreated(); // 메인프레임에게 알려줌
        }
        
    }
    
}
