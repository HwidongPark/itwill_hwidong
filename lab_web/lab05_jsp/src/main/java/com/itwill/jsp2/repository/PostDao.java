package com.itwill.jsp2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.datasource.DataSourceUtil;
import com.itwill.jsp2.domain.Post;
import com.itwill.jsp2.dto.PostSearchDto;
import com.itwill.jsp2.dto.PostUpdateDto;
import com.zaxxer.hikari.HikariDataSource;

// MVC 아키텍쳐에서 영속성 계층(Persistence Layer)을 담당하는 클래스.
// DB CRUD(Create, Read, Update, Delete)
// DAO(Data Access Object)
public class PostDao {
    // slf4j의 로깅 기능 사용:
    private static final Logger log = LoggerFactory.getLogger(PostDao.class);
    
    // singleton 구현
    private static PostDao instance = null;
    
    private HikariDataSource ds;
    
    private PostDao() {
        ds = DataSourceUtil.getInstance().getDataSource();
    }
    
    public static PostDao getInstance() {
        if (instance == null) {
            instance = new PostDao();
        }
        
        return instance;
    }
    
    
    // POSTS 테이블에 전체 레코드를 id에 내림차순 정렬로 검색.
    String SQL_SELECT_POSTS = "SELECT * FROM POSTS ORDER BY id DESC";
    
    // SQL_SELECT를 실행하는 메서드
    public List<Post> select() {
        List<Post> list = new ArrayList<>();
        
        Connection conn = null; // 데이터 소스에서 커넥션 객체를 빌려옴.
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_POSTS);
            log.debug(SQL_SELECT_POSTS);
            rs = stmt.executeQuery();
            while (rs.next()) { // ResultSet 현재 위치에 레코드가 있으면 
                Post post = generatePostFromRs(rs);
                list.add(post);
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           closeResources(conn, stmt, rs);
            
        }
        
        
        return list;
    }
    
    // POSTS 테이블에서 아이디(PK)로 검색하기 
    String SQL_SELECT_BY_ID = "SELECT * FROM POSTS WHERE id = ?";
    
    // SQL_SELECT_BY_ID 문장을 실행하고 결과를 처리하는 메서드
    public  Post select(long id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Post post = null;
        
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {    // select 결과가 있으면
                // ResultSet에서 Post 객체를 만듦.
                post = generatePostFromRs(rs);
            }
        } catch (SQLException e) {            
            e.printStackTrace();
        } finally {
            // 리소스 해제
            closeResources(conn, stmt, rs);
            
        }
        
        return post;
    }
    
    
    // 새 포스트 작성에서 사용되는 SQL 문장
    private static final String SQL_INSERT = 
            "INSERT INTO posts (title, content, author) VALUES (?, ?, ?)";
    
    // SQL_INSERT를 실행하는 메서드
    public int insert(Post post) {
        log.debug("insert(post={})", post);
        
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            log.debug(SQL_INSERT);
            
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setString(3, post.getAuthor());
            
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                closeResources(conn, stmt);
            
        }
        
        
        return result;
    }
    
    
    // 포스트 아이디(PK)로 포스트 삭제하기
    String SQL_DELETE_BY_ID= "DELETE FROM posts WHERE id = ?";
    
    // SQL_DELETE_BY_ID를 실행하는 메서드
    public int delete(long id) {
        log.debug("delete(id={})", id);
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_BY_ID);
            log.debug(SQL_DELETE_BY_ID);
            stmt.setLong(1, id);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return result;
    }
    
    
    // Update하는 SQL
    String SQL_Update_Post = "UPDATE posts SET title = ?, content = ?, modified_time = systimestamp where id = ?";
    
    public int update(Post post) {
        log.debug("update(pst={})", post);
        int result = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_Update_Post);
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setLong(3, post.getId());
            
            result = stmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return result;
    }
    
    

    
    // post form만드는 메서드
    private Post generatePostFromRs(ResultSet rs) throws SQLException {
        Long id = rs.getLong("ID");
        String title = rs.getString("TITLE");
        String content = rs.getString("CONTENT");
        String author = rs.getString("AUTHOR");
        Timestamp created = rs.getTimestamp("CREATED_TIME");
        Timestamp modified = rs.getTimestamp("MODIFIED_TIME");
        
        return new Post(id, title, content, author, created, modified);
    }
    
    
    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if(rs != null) { 
                rs.close();
            };
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
    
    
    private void closeResources (Connection conn, Statement stmt) {
        closeResources(conn, stmt, null);
    }
    
}
