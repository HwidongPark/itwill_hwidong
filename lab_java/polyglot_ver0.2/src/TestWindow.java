import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TestWindow {

    private JFrame frame;
    private JTable table;
    private JList list;
    private JPasswordField passwordField2;
    private JLabel lblPassword2;
    private JLabel lblPassword1;
    private JPasswordField passwordField1;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TestWindow window = new TestWindow();
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
    public TestWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        // TEST
        Arrays.deepEquals(null, null);
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.setBounds(143, 172, 118, 79);

        frame.getContentPane().add(btnNewButton);
        
        passwordField2 = new JPasswordField();
        passwordField2.setBounds(129, 132, 200, 30);
        frame.getContentPane().add(passwordField2);
        
        char[] password = passwordField2.getPassword();
        
        lblPassword2 = new JLabel("비밀번호2");
        lblPassword2.setBounds(48, 139, 57, 15);
        frame.getContentPane().add(lblPassword2);
        
        lblPassword1 = new JLabel("비밀번호1");
        lblPassword1.setBounds(48, 99, 57, 15);
        frame.getContentPane().add(lblPassword1);
        
        passwordField1 = new JPasswordField();
        passwordField1.setBounds(129, 92, 200, 30);
        frame.getContentPane().add(passwordField1);
        
        textField = new JTextField();
        textField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(textField.getText().length());
                
                if(textField.getText().length() == 10) {
                    textField.setEditable(false);
                }
                
                if(textField.getText().length() > 10) {
                    JOptionPane.showMessageDialog(frame, "1111");
                    
                }
            }
            
        });
        textField.setBounds(129, 39, 200, 21);
        frame.getContentPane().add(textField);
        textField.setColumns(10);
//        btnNewButton.setVisible(false);
        btnNewButton.addActionListener((e) -> {
            System.out.println(e.getSource());
            String password1 = String.valueOf(passwordField1.getPassword());
            String password2 = String.valueOf(passwordField2.getPassword());
            
            if (password1.equals(password2)) {
                System.out.println("비번1: " + password1);
                System.out.println("비번2: " + password2);
            }
//            System.out.println("비밀번호 테스트: " + password);    

            
        });
        
        
        
        
    }
}