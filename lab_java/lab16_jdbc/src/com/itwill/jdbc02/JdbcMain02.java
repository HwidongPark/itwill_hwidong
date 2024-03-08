package com.itwill.jdbc02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleDriver;

import static com.itwill.jdbc.OracleJdbc.*;

public class JdbcMain02 {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        // JDBC insert
        
        try {
            // 1. OracleDriver를 등록:
            DriverManager.registerDriver(new OracleDriver());
            System.out.println("오라클 등록 성공");
            
            // 2. Connection 생성
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            // 3. Statement 준비
            final String sql = "insert into BLOGS (TITLE, CONTENT, AUTHOR) values (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            // 테이블에 insert하기 위한 정보들을 입력받음.
            System.out.print("제목 입력 >>> ");
            String title = sc.nextLine();
            System.out.print("내용 입력 >>> ");
            String content = sc.nextLine();
            System.out.print("작성자 입력 >>> ");
            String author = sc.nextLine();
            
            // statement 객체의 ?로 비워져 있는 부분의 값들을 채움.
            pstmt.setString(1, title);  // 첫번재 물음표를 title 변수 값으로 채움.
            pstmt.setString(2, content);    // 두번재 물음표를 title 변수 값으로 채움...
            pstmt.setString(3, author);
            
            
            // 4. Statement를 실행 & 결과 처리:
            int result = pstmt.executeUpdate();
            System.out.println(result + "개행이 삽입됐습니다.");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 리소스들 해제
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
    }

}
