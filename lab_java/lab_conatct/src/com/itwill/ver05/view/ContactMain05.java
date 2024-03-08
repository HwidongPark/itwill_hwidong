package com.itwill.ver05.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.itwill.ver04.controller.ContactDaoImpl;
import com.itwill.ver04.model.Contact;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;



public class ContactMain05 {
    public static final String[] COLUMN_NAMES = {"이름", "전화번호"};
    
    private JFrame frame;
    private JButton btnCreate;
    private JButton btnUpdate;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    
    // Controller
    private ContactDaoImpl dao = ContactDaoImpl.getInstance();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ContactMain05 window = new ContactMain05();
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
    public ContactMain05() {
        initialize();   // Swing 컴포넌트를 생성, 초기화
        loadContactData();  // 파일에 저장된 연락처 데이터를 로딩(JTable 초기화)
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("연락처 프로그램");
        frame.setBounds(100, 100, 500, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        
        btnCreate = new JButton("새 연락처");
        btnCreate.addActionListener((e) -> {
            ContactCreateFrame.showContactCreateFrame(frame, ContactMain05.this);
        });
        btnCreate.setHorizontalAlignment(SwingConstants.LEFT);
        btnCreate.setFont(new Font("D2Coding", Font.PLAIN, 16));
        panel.add(btnCreate);
        
        btnUpdate = new JButton("업데이트");

        btnUpdate.addActionListener((e) -> {
            
            updateContact();
        });
        btnUpdate.setFont(new Font("D2Coding", Font.PLAIN, 16));
        panel.add(btnUpdate);
        
        JButton btnDelete = new JButton("삭제");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteContact();
            }
        });
        btnDelete.setFont(new Font("D2Coding", Font.PLAIN, 16));
        panel.add(btnDelete);
        
        JButton btnSearch = new JButton("검색");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: 여기 다시 오기
                ContactSearchFrame.showContactSearchFrame(frame);
            }
        });
        btnSearch.setFont(new Font("D2Coding", Font.PLAIN, 16));
        panel.add(btnSearch);
        
        scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable();
        // 테이블 컬럼 이름 폰트 변경
        table.getTableHeader().setFont(new Font("D2Coding", Font.BOLD, 16));
        // 칼럼 못움직이도록함
        table.getTableHeader().setReorderingAllowed(false);
        
        // 테이블 데이터 행의 폰트 변경
        table.setFont(new Font("D2Coding", Font.ITALIC, 13));
        // 테이블 행 높이(세로) 크기 설정
        table.setRowHeight(40);
        
        // 한번에 한 row만 선택할 수 있도록 설정
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        scrollPane.setViewportView(table);
        model = new DefaultTableModel(null, COLUMN_NAMES);
        table.setModel(model);
    }
    
    

    // 업데이트 메서드
    public void notifyContactUpdated() {
        resetTableModel();
        JOptionPane.showMessageDialog(frame, "연락처 업데이트 성공", "업데이트 성공", JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    private void updateContact() {
        // 테이블에서 선택된 행의 인덱스를 찾음.
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(frame, "업데이트 하려는 행을 먼저 선택하세요...", "경고", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // 이 객체 넘겨준 이유는 이 객체의 메서드 사용 위해서.
        // index넘겨준 이유는 다른 창에서도 이 창에서 선택된 행의 index를 넘겨주기 위해서
        ContactUpdateFrame.showContactUpdateFrame(frame, ContactMain05.this, index);
        
             
    }

    /*
     * 연락처 데이터 로딩, 테이블 초기화
     */
    private void loadContactData() {
        List<Contact> list = dao.read();    // 파일에서 데이터 읽어옴.
        for (Contact c : list) {
            Object[] row = {c.getName(), c.getPhone()};
            model.addRow(row);
        }
    }
    
    
    private void deleteContact() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(frame, "삭제하려는 행을 먼저 선택하세요...", "경고", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(frame, "정말 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dao.delete(index);
//            resetTablemodel();
            model.removeRow(index); // 선택된 행을 테이블 모델에서 삭제.
            
            JOptionPane.showMessageDialog(frame, "삭제 성공", "삭제 성공", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * 새로운 연락처 저장 성공했음을 알려주기 위한 메서드.
     * 메서드가 호출되면 JTable 내용을 갱신.
     */
    public void notifyContactCreated() {

        resetTableModel();
        JOptionPane.showMessageDialog(frame, "새 연락처 저장 성공");
    }
    
    private void resetTableModel() {
        // 데이터가 없는 새로운 테이블 모델 객체를 생성
        model = new DefaultTableModel(null, COLUMN_NAMES);
        table.setModel(model);
        // 파일에 저장된 데이터를 다시 읽고 테이블 모델에 행동을 추가.
        loadContactData();
        // 새롭게 만들어진 테이블 모델을 테이블에 세팅
    }
    

}   // AppMain class 끝
