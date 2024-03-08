package com.itwill.jsp2.dto;

import com.itwill.jsp2.domain.Post;

public class PostSearchDto {
    private String category;
    private String keyword;
    
    // constructors
    public PostSearchDto(String category, String keyword) {        
        this.category = category;
        this.keyword = keyword;
    }
    
    // Getters
    public String getCategory() {
        return category;
    }

    public String getKeyword() {
        return keyword;
    }
    
    
    // To String
    @Override
    public String toString() {
        return "PostSearchDto [category=" + category + ", keyword=" + keyword + "]";
    }
    
    
    
    
}
