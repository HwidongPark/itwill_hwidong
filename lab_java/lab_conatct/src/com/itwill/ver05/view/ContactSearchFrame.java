package com.itwill.ver05.view;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itwill.ver04.controller.ContactDaoImpl;
import com.itwill.ver04.model.Contact;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class ContactSearchFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Component parent;
    private ContactMain05 app;
    private JTextField textToSearch;
    private JTable table;
    private String[] COLUMN_NAMES = {"이름", "전화 번호", "이메일"};
    private DefaultTableModel model;
    private ContactDaoImpl dao = ContactDaoImpl.getInstance();
    private JButton btnSearch;
    private JScrollPane scrollPane;

    /**
     * Launch the application.
     */
    public static void showContactSearchFrame(Component parent) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ContactSearchFrame frame = new ContactSearchFrame(parent);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public ContactSearchFrame(Component parent) {
        this.parent = parent;
        initialize();
    }
    
    /**
     * Create the frame.
     */
    public void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        int x = 100;
        int y = 100;
        if (parent != null) {
            x = parent.getX() + parent.getWidth();
            y = parent.getY();
        }
        setBounds(x, y, 600, 550);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitle = new JLabel("연락처 검색");
        lblTitle.setFont(new Font("D2Coding", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(192, 10, 188, 62);
        contentPane.add(lblTitle);
        
        textToSearch = new JTextField();
        textToSearch.setBounds(39, 82, 388, 30);
        contentPane.add(textToSearch);
        textToSearch.setColumns(10);
        
        btnSearch = new JButton("검색");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchContacts();
                
            }
        });
        btnSearch.setBounds(439, 81, 97, 33);
        contentPane.add(btnSearch);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(39, 140, 497, 332);
        contentPane.add(scrollPane);
        
        table = new JTable();
        model = new DefaultTableModel(null, COLUMN_NAMES);
        table.setModel(model);
        table.getTableHeader().setFont(new Font("D2Coding", Font.BOLD, 18));
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("D2Coding", Font.ITALIC, 18));
        table.setRowHeight(30);
        
        scrollPane.setViewportView(table);
    }

    private void searchContacts() {
        String text = textToSearch.getText();
        textToSearch.setText("");
        
        if (text == null || text.equals("")) {
            JOptionPane.showMessageDialog(this, "검색어를 입력해 주세요.", "검색 오류", JOptionPane.WARNING_MESSAGE);
            
            return;
        }
        
        cleanTable();
        
        List<Contact> searchedContacts = dao.searchContacts(text);
        for (Contact contact : searchedContacts) {
            String[] newRow = {contact.getName(), contact.getPhone(), contact.getEmail()};
            model.addRow(newRow);
        }
    }
    
    private void cleanTable() {
        model.setRowCount(0);;
    }
    
}
