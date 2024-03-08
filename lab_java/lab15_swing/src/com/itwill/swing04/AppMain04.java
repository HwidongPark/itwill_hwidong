package com.itwill.swing04;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppMain04 {
    
    private JFrame frame;
    private List<File> images = new ArrayList<>(List.of(new File("images\\flower1"), new File("images\\flower2"),
        new File("images\\flower3"), new File("images\\flower4"), new File("images\\flower5")));
    

    private int imageNumber = 0;
    private JButton btnPrevious;
    private JButton btnNext;
    private JLabel lblPic;
    
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AppMain04 window = new AppMain04();
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
    public AppMain04() {
        initialize();
    }
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 700, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        

        lblPic = new JLabel();
        lblPic.setIcon(new ImageIcon(images.get(imageNumber).getPath() + ".jpg"));
        lblPic.setBounds(25, 21, 640, 640);
        frame.getContentPane().add(lblPic);
        
        btnPrevious = new JButton("<");
        btnPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveToAnotherImage(e);
            }
        });
        
        btnPrevious.setBounds(166, 691, 71, 60);
        frame.getContentPane().add(btnPrevious);
        
        btnNext = new JButton(">");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveToAnotherImage(e);                
            }
        });
        btnNext.setBounds(442, 691, 71, 60);
        frame.getContentPane().add(btnNext);
    }
    
    private void moveToAnotherImage(ActionEvent e) {
        if (e.getSource() == btnPrevious) {
            imageNumber = imageNumber == 0 ? images.size() - 1 : --imageNumber;

            lblPic.setIcon(new ImageIcon(images.get(imageNumber).getPath() + ".jpg"));
        } else if (e.getSource() == btnNext){
            imageNumber = (imageNumber == images.size() - 1 ? 0 : ++imageNumber);

            lblPic.setIcon(new ImageIcon(images.get(imageNumber).getPath() + ".jpg"));
            
            
        }
    }

}
