import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JButton;

public class TestWindow {

    private JFrame frame;
    private JTable table;
    private JList list;

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
        
        
        JButton btnNewButton = new JButton("New button");
        btnNewButton.setBounds(143, 139, 200, 200);

        frame.getContentPane().add(btnNewButton);
//        btnNewButton.setVisible(false);
        btnNewButton.addActionListener((e) -> {
            System.out.println(e.getSource());
        });
        
        
        
        
    }
}