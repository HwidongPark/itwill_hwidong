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
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlogDetailedFrame extends JFrame {
    private BlogDao dao;
    
    private Component parent;
    private int id;
    private BlogMain app;

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblId;
    private JTextField textId;
    private JTextField textTitle;
    private JTextField textAuthor;
    private JTextField textCreated;
    private JTextField textModified;
    private JLabel lblAuthor;
    private JLabel lblCreated;
    private JLabel lblModified;
    private JTextArea textContent;
    private JLabel lblContent;
    private JLabel lblTitle;

    /**
     * Launch the application.
     */
    public static void showBlogDetailedFrame(Component parent, int id, BlogMain app) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BlogDetailedFrame frame = new BlogDetailedFrame(parent, id, app);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public BlogDetailedFrame(Component parent, int id, BlogMain app) {
        this.app = app;
        this.parent = parent;
        this.id = id;
        
        dao = BlogDao.getInstance();
        
        // UI 컴포넌트들을 초기화
        initialize();
        
        // UI 컴포넌트에 값들을 초기화
        initBlogDetails();
    }
    
    /**
     * Create the frame.
     */
    public void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        int x = 100;
        int y = 100;
        if (parent != null) {
            x = parent.getX() + parent.getWidth();
            y = parent.getY();
        }
        
        setBounds(x, y, 670, 740);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblId = new JLabel("번호");
        lblId.setFont(new Font("굴림", Font.PLAIN, 17));
        lblId.setBounds(12, 25, 78, 46);
        contentPane.add(lblId);
        
        lblTitle = new JLabel("제목");
        lblTitle.setFont(new Font("굴림", Font.PLAIN, 17));
        lblTitle.setBounds(12, 81, 78, 46);
        contentPane.add(lblTitle);
        
        lblContent = new JLabel("내용");
        lblContent.setFont(new Font("굴림", Font.PLAIN, 17));
        lblContent.setBounds(12, 125, 78, 46);
        contentPane.add(lblContent);
        
        textId = new JTextField();
        textId.setEditable(false);
        textId.setBounds(89, 32, 499, 35);
        contentPane.add(textId);
        textId.setColumns(10);
        
        textTitle = new JTextField();
        textTitle.setColumns(10);
        textTitle.setBounds(89, 81, 499, 35);
        contentPane.add(textTitle);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 161, 617, 283);
        contentPane.add(scrollPane);
        
        textContent = new JTextArea();
        scrollPane.setViewportView(textContent);
        
        textAuthor = new JTextField();
        textAuthor.setEditable(false);
        textAuthor.setColumns(10);
        textAuthor.setBounds(89, 461, 499, 35);
        contentPane.add(textAuthor);
        
        lblAuthor = new JLabel("작성자");
        lblAuthor.setFont(new Font("굴림", Font.PLAIN, 17));
        lblAuthor.setBounds(12, 454, 78, 46);
        contentPane.add(lblAuthor);
        
        lblCreated = new JLabel("작성시간");
        lblCreated.setFont(new Font("굴림", Font.PLAIN, 17));
        lblCreated.setBounds(12, 506, 78, 46);
        contentPane.add(lblCreated);
        
        textCreated = new JTextField();
        textCreated.setEditable(false);
        textCreated.setColumns(10);
        textCreated.setBounds(89, 513, 499, 35);
        contentPane.add(textCreated);
        
        lblModified = new JLabel("수정시간");
        lblModified.setFont(new Font("굴림", Font.PLAIN, 17));
        lblModified.setBounds(12, 562, 78, 46);
        contentPane.add(lblModified);
        
        textModified = new JTextField();
        textModified.setEditable(false);
        textModified.setColumns(10);
        textModified.setBounds(89, 569, 499, 35);
        contentPane.add(textModified);
        
        JButton btnUpdate = new JButton("업데이트");
        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateBlogPost();
            }
        });
        btnUpdate.addActionListener((e) -> {
            // TODO: 메서드 추가
        });
        btnUpdate.setBounds(178, 618, 97, 73);
        contentPane.add(btnUpdate);
        
        JButton btnClose = new JButton("닫기");
        btnClose.addActionListener((e) -> dispose());
        btnClose.setBounds(344, 618, 97, 73);
        contentPane.add(btnClose);
    }
    
    private void updateBlogPost() {
        Integer id = Integer.parseInt(textId.getText());
        String title = textTitle.getText();
        String content = textContent.getText();
        if (title.equals("") || content.equals("")) {
            JOptionPane.showMessageDialog(this, "제목과 내용은 반드시 입력하세요");
            
            return;
        }
        
        Blog blog = new Blog(id, title, content, null, null, null);
        int result = dao.update(blog);
        if (result == 1) {
            dispose();
            app.notifyBlogUpdated();
        }
        
    }

    private void initBlogDetails() {
        // DAO(컨트롤러) 메서드를 사용해서 DB에서 검색하고, 그 결과를 보여줌.
        Blog blog = dao.read(id);
        if (blog != null) {
            textId.setText(blog.getId().toString());
            textTitle.setText(blog.getTitle());
            textContent.setText(blog.getContent());
            textAuthor.setText(blog.getAuthor());
            textCreated.setText(blog.getCreatedTime().toString());
            textModified.setText(blog.getModifiedTime().toString());
        }
    }
    
}
