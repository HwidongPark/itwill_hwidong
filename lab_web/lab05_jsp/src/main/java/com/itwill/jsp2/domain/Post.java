package com.itwill.jsp2.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

// MVC에서 모델에 해당하는 클래스. DB의 POSTS 테이블의 컬럼들과 같은 구조.
public class Post {
    private long id;    // long or Long?
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    
    public Post() {}

    public Post(long id, String title, String content, String author, LocalDateTime createdTime,
            LocalDateTime modifiedTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }
    
    public Post(long id, String title, String content, String author,
            Timestamp createdTime, Timestamp modifiedTime) {
        this(id, title, content, author, createdTime.toLocalDateTime(),
                modifiedTime.toLocalDateTime());
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.author = author;
//        this.createdTime = createdTime.toLocalDateTime();
//        this.modifiedTime = modifiedTime.toLocalDateTime();
    }
    
    public Post (String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    
    // Getters / Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + ", createdTime="
                + createdTime + ", modifiedTime=" + modifiedTime + "]";
    }
    
    
    
    
}
