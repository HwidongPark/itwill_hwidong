package com.itwill.jdbc.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itwill.jdbc.controller.BlogDao;
import com.itwill.jdbc.model.Blog;

import javax.swing.JButton;
import javax.swing.JTable;

public class BlogMain {
    
    public static final String[] COLUMN_NAMES = {"번호", "제목", "작성자", "작성시간"};
    
    private JFrame frame;
    private JComboBox<String> comboBox;
    private JPanel searchPanel;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private JTextField textSearchKeyword;
    private JButton btnSearch;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnCreate;
    private JButton btnDetails;
    private JButton btnDelete;
    
    private BlogDao dao = BlogDao.getInstance();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BlogMain window = new BlogMain();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public BlogMain() {
        initialize();   // UI 컴포넌트들 초기화
        initTable();    // 테이블 데이터 초기화
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 584, 620);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("블로그 프로그램");
        
        searchPanel = new JPanel();
        frame.getContentPane().add(searchPanel, BorderLayout.NORTH);
        
        comboBox = new JComboBox<>();
        comboBox.setFont(new Font("굴림", Font.PLAIN, 15));
        final String[] searchTypes = new String[] {"제목", "내용", "제목 + 내용", "작성자"};
        final DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(searchTypes);
        comboBox.setModel(comboBoxModel);
        searchPanel.add(comboBox);
        
        textSearchKeyword = new JTextField();
        searchPanel.add(textSearchKeyword);
        textSearchKeyword.setColumns(20);
        
        btnSearch = new JButton("검색");
        btnSearch.addActionListener((e) -> {
            searchByKeyword();
        });
        btnSearch.setFont(new Font("굴림", Font.PLAIN, 15));
        searchPanel.add(btnSearch);
        
        scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel = new DefaultTableModel(null, COLUMN_NAMES);
        table.setModel(tableModel);
        scrollPane.setViewportView(table);
        
        buttonPanel = new JPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        btnCreate = new JButton("새 포스트 작성");
        btnCreate.addActionListener((e) -> {
            BlogCreateFrame.showBlogCreateFrame(frame, BlogMain.this);
        });
        buttonPanel.add(btnCreate);
        
        btnDetails = new JButton("상세보기");
        btnDetails.addActionListener((e) -> {
            showBlogDetails();
        });
        buttonPanel.add(btnDetails);
        
        btnDelete = new JButton("삭제");
        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBlogPost();
                
            }
            
        });
        buttonPanel.add(btnDelete);
    }
    
    private void showBlogDetails() {
        // 테이블에서 선택된 행 인덱스
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(frame, "게시물을 선택해 주세요.");
            return;
        }
        
        // 선택된 행(row)에서 글 번호(ID)를 찾음.
        Integer id = (Integer) tableModel.getValueAt(row, 0);
        
        // 블로그 상세보기 프레임을 보여줌
        BlogDetailedFrame.showBlogDetailedFrame(frame, id, BlogMain.this);
        
    }

    private void initTable() {
        List<Blog> blogs = dao.read(); // DB에서 BLOGS테이블 전체 검색.
        
        tableModel = new DefaultTableModel(null, COLUMN_NAMES); // 테이블모델 리셋(초기화)
        for (Blog b : blogs) {  // DB에서 검색한 내용으로 테이블의 행들을 만듦
            Object[] row = {
                    b.getId(),
                    b.getTitle(),
                    b.getAuthor(),
                    b.getCreatedTime()
            };
            tableModel.addRow(row);
        }
        table.setModel(tableModel);
        
    }
    
    public void notifyBlogCreated() {
        initTable();
        JOptionPane.showMessageDialog(frame, "새 포스트 등록 성공");
    }
    
    public void notifyBlogUpdated() {
        initTable();
        JOptionPane.showMessageDialog(frame, "포스트 업데이트 성공");
    }
    
    
    private void deleteBlogPost() {
        int row = table.getSelectedRow();   // 테이블에서 선택된 행 인덱스
        if (row == -1) {    // 선택된 행이 없는 경우
            
            JOptionPane.showMessageDialog(frame, "삭제하려는 행을 테이블에서 선택하세요");
            
            return; // 메서드 종료
        }
        
        int confirm = JOptionPane.showConfirmDialog(frame, "정말 삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // 선택된 행에서 번호(id)를 찾음.
            Integer id = (Integer) tableModel.getValueAt(row, 0);
            // DAO(컨트롤러)의 메서드를 사용해서 DB에서 행을 삭제.
            int result = dao.delete(id);
            if (result == 1) {
             // 삭제 성공하면 테이블 새로고침.
                initTable();
                JOptionPane.showMessageDialog(frame, "블로그 포스트 삭제 성공");
            }
        }
    }
    
    public void searchByKeyword() {
        int type = comboBox.getSelectedIndex();
        String keyword = textSearchKeyword.getText();
        List<Blog> blogs = new ArrayList<>();
        
        if (keyword.equals("")) {
            JOptionPane.showMessageDialog(frame, "검색어를 입력해 주세요.");
            
            return;
        }
        
        blogs = dao.search(type, keyword);
//        나중에하자
//        tableModel = new DefaultTableModel(null, ) 
    }

}
