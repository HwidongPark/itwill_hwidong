package com.itwill.swing03;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppMain03 {

    private JFrame frame;
    private JTextField num1TextField;
    private JTextField num2TextField;
    private JTextArea resultTextArea;
    private JButton additionBtn;
    private JButton subtractionBtn;
    private JButton multiplicationBtn;
    private JButton divisionBtn;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AppMain03 window = new AppMain03();
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
    public AppMain03() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel num1Lbl = new JLabel("num1");
        num1Lbl.setFont(new Font("굴림", Font.BOLD, 20));
        num1Lbl.setHorizontalAlignment(SwingConstants.CENTER);
        num1Lbl.setBounds(55, 34, 86, 38);
        frame.getContentPane().add(num1Lbl);
        
        JLabel num2Lbl = new JLabel("num2");
        num2Lbl.setFont(new Font("굴림", Font.BOLD, 20));
        num2Lbl.setHorizontalAlignment(SwingConstants.CENTER);
        num2Lbl.setBounds(55, 80, 86, 38);
        frame.getContentPane().add(num2Lbl);
        
        num1TextField = new JTextField();
        num1TextField.setBounds(167, 41, 230, 30);
        frame.getContentPane().add(num1TextField);
        num1TextField.setColumns(10);
        
        num2TextField = new JTextField();
        num2TextField.setBounds(167, 88, 230, 30);
        frame.getContentPane().add(num2TextField);
        num2TextField.setColumns(10);
        
        additionBtn = new JButton("+");
        additionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 덧셈 연산
                handleButtonClick(e);
            }
        });
        additionBtn.setFont(new Font("굴림", Font.BOLD, 15));
        additionBtn.setBounds(112, 149, 50, 50);
        frame.getContentPane().add(additionBtn);
        
        subtractionBtn = new JButton("-");
        subtractionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 뺄셈 연산
                handleButtonClick(e);
            }
        });
        subtractionBtn.setFont(new Font("굴림", Font.BOLD, 15));
        subtractionBtn.setBounds(188, 149, 50, 50);
        frame.getContentPane().add(subtractionBtn);
        
        multiplicationBtn = new JButton("x");
        multiplicationBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 곱셈 연산
                handleButtonClick(e);
            }
        });
        multiplicationBtn.setFont(new Font("굴림", Font.BOLD, 15));
        multiplicationBtn.setBounds(270, 149, 50, 50);
        frame.getContentPane().add(multiplicationBtn);
        
        divisionBtn = new JButton("/");
        divisionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 나눗셈 연산
                handleButtonClick(e);
            }
        });
        divisionBtn.setFont(new Font("굴림", Font.BOLD, 15));
        divisionBtn.setBounds(347, 149, 50, 50);
        frame.getContentPane().add(divisionBtn);
        
        resultTextArea = new JTextArea();
        resultTextArea.setLineWrap(true);
        resultTextArea.setFont(new Font("D2Coding", Font.BOLD, 20));
        resultTextArea.setBounds(93, 209, 304, 92);
        frame.getContentPane().add(resultTextArea);
    }
    
    private void handleButtonClick(ActionEvent e) {
//        System.out.println(e.getSource());
        // -> 아규먼트로 ActionEvent 객체에서 이벤트가 발생된 GUI 컴포넌트 정보를 알 수 있음
        
        // -> JTextField에 입력된 문자열 -> 숫자 변환 -> 버튼 종류에 따라 사칙연산 -> JTextArea에 결과 출력
        double number1 = 0; // 지역변수는 선언과 동시에 초기화 해주는게 좋음
        double number2 = 0;
        
        try {
            number1 = Double.parseDouble(num1TextField.getText());
            number2 = Double.parseDouble(num2TextField.getText());
        } catch (NumberFormatException ex) {
            resultTextArea.setText("number1 또는 number2는 숫자여야 합니다.");
            num1TextField.setText("");
            num2TextField.setText("");
            return; // 메서드 종료
        }
        
        double result = 0;  // 사칙 연산의 결과를 저장할 변수.
        String op = ""; // 사칙연산 연산자 문자열(+, -, x, /)을 저장하기 위한 변수.
        Object source = e.getSource();  // 이벤트가 발생한 객체(컴포넌트)
        if (source == additionBtn) {
            result = number1 + number2;
            op = "+";
        } else if (source == subtractionBtn) {
            result = number1 - number2;
            op = "-";
        } else if (source == multiplicationBtn) {
            result = number1 * number2;
            op = "*";
        } else if (source == divisionBtn) {
            result = number1 / number2;
            op = "/";
        } else {
            resultTextArea.setText("이럴 일은 없다");
        }
        
        String msg = String.format("%.1f %s %.1f = %.1f", number1, op, number2, result);
        resultTextArea.setText(msg);
        num1TextField.setText("");
        num2TextField.setText("");
        
        
    }
    
    
//    // 덧셈 연산
//    private void addition() {
//        try {
//            int num1 = Integer.parseInt(num1TextField.getText());
//            int num2 = Integer.parseInt(num2TextField.getText());
//            
//            resultTextArea.setText("덧셈 결과: \n" + num1 + " + " + num2 + " = "  + (num1 + num2));        
//        } catch (NumberFormatException e) {
//            resultTextArea.setText("정수만 입력 가능합니다.");
//        } finally {
//            num1TextField.setText("");
//            num2TextField.setText("");
//        }
//    }
//    
//    // 뺄셈 연산
//    private void subtraction() {
//        try {
//            int num1 = Integer.parseInt(num1TextField.getText());
//            int num2 = Integer.parseInt(num2TextField.getText());
//            
//            resultTextArea.setText("뺄셈 결과: \n" + num1 + " - " + num2 + " = "  + (num1 - num2));
//        } catch (NumberFormatException e) {
//            resultTextArea.setText("정수만 입력 가능합니다.");
//        } finally {
//            num1TextField.setText("");
//            num2TextField.setText("");
//        }
//    }
//    
//    
//    // 곱셈 연산
//    private void multiplication() {
//        try {
//            int num1 = Integer.parseInt(num1TextField.getText());
//            int num2 = Integer.parseInt(num2TextField.getText());
//            
//            resultTextArea.setText("곱셈 결과: \n" + num1 + " * " + num2 + " = "  + (num1 * num2));
//        } catch (NumberFormatException e) {
//            resultTextArea.setText("정수만 입력 가능합니다.");
//        } finally {
//            num1TextField.setText("");
//            num2TextField.setText("");
//        }
//    }
//    
//    
//    // 나눗셈 연산
//    private void division() {
//        try {
//            double num1 = Double.parseDouble(num1TextField.getText());
//            double num2 = Double.parseDouble(num2TextField.getText());
//            
//            resultTextArea.setText("나눗셈 결과: \n" + num1 + " / " + num2 + " = "  + (num1 / num2));
//        } catch (NumberFormatException e) {
//            resultTextArea.setText("정수만 입력 가능합니다.");
//        } finally {
//            num1TextField.setText("");
//            num2TextField.setText("");
//        }
//    }
    
    
    
}
