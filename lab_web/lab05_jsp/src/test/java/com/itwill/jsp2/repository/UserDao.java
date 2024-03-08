package com.itwill.jsp2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.datasource.DataSourceUtil;
import com.itwill.jsp2.domain.User;
import com.itwill.jsp2.dto.UserSignInDto;
import com.zaxxer.hikari.HikariDataSource;


public class UserDao {
    private static final Logger log = LoggerFactory.getLogger(UserDao.class);
    
    private static UserDao instance = null;
    private final HikariDataSource ds = DataSourceUtil.getInstance().getDataSource();
    
    private UserDao() {}
    
    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        
        return instance;
    }
    
    // 회원가입 SQL
    private static final String SQL_SIGN_UP = "INSERT INTO users (userid, password, email) values (?, ?, ?)";
    
    // 회원가입 메서드
    public int insert(User user) {
        log.debug("insert(user={})", user);
        
        int result = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_SIGN_UP);
            stmt.setString(1, user.getUserid());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            
            result = stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        
        
        return result;
    }
    
    // 로그인 체크 SQL
    private static final String SQL_SIGN_IN = "SELECT * FROM users WHERE userid = ? AND password = ?";
    
    // 
    public User selectByUserIdAndPassword(UserSignInDto dto) {
        // TODO: userid와 password가 일치하면 user 객체를, 일치하지 않으면 null을 리턴.
        log.debug("dto={}", dto);
        User user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_SIGN_IN);
            stmt.setString(1, dto.getUserid());
            stmt.setString(2, dto.getPassword());
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                long id = rs.getLong("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                long points = rs.getLong("points");
                
                user = new User(id, username, password, email, points);
                log.debug("user={}", user);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        
        return user;
    }
    
    
    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
       try {
           if (rs != null) {
               rs.close();
           }
           if(stmt != null) {
               stmt.close();
           }
           if(conn != null) {
               conn.close();
           }
           
       } catch (SQLException e) {
        e.printStackTrace();
        }
    }
    
    private void closeResources(Connection conn, Statement stmt) {
        closeResources(conn, stmt, null);
    }
    
}
