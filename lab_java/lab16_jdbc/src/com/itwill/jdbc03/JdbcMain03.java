package com.itwill.jdbc03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;

import static com.itwill.jdbc.OracleJdbc.*;

public class JdbcMain03 {

    public static void main(String[] args) {
        // JDBC delete
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // 오라클 드라이버를 등록
            DriverManager.registerDriver(new OracleDriver());
            
            // 오라클 DB에 접속
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            // statement 준비
            String sql = "delete from BLOGS where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 1); // 첫번재 물음표를 1로 채움.
            
            // statement 실행 & 결과 처리:
            int result = pstmt.executeUpdate();
            System.out.println(result + "개 행이 삭제됐습니다.");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 리소스 해제
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
