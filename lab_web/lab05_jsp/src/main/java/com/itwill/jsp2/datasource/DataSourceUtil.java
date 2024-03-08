package com.itwill.jsp2.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceUtil {
    // singleton 구현:
    private static DataSourceUtil instance = null;
    
    private DataSourceUtil() {
        HikariConfig config = new HikariConfig();   // HikariCP 환경설정 객체.
        config.setDriverClassName("oracle.jdbc.OracleDriver");
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        config.setUsername("scott");
        config.setPassword("tiger");
        
        // 데이터 소스 객체 생성
        ds = new HikariDataSource(config);
    };
    
    private HikariDataSource ds;
    
    public static DataSourceUtil getInstance() {
        if (instance == null) {
            instance = new DataSourceUtil();
        }
        
        return instance;
    }
    
    public HikariDataSource getDataSource() {
        return ds;
    }
    
    
}
