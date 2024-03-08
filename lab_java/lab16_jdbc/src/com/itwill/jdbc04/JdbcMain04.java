package com.itwill.jdbc04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static com.itwill.jdbc.OracleJdbc.*;

public class JdbcMain04 {

    public static void main(String[] args) {
        // JDBC update
        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            String sql = "Update BLOGS SET TITLE=?, CONTENT=?, modified_time = systimestamp where ID = ?";
            pstmt = conn.prepareStatement(sql);
            
            System.out.print("글 번호 >> ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("제목 변경 >> ");
            String title = sc.nextLine();
            System.out.print("내용 변경 >> ");
            String content = sc.nextLine();
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setInt(3, id);
            
            int result = pstmt.executeUpdate();
            System.out.println(result + "개 행이 업데이트 됐습니다.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
        }
        
        
        

    }

}
