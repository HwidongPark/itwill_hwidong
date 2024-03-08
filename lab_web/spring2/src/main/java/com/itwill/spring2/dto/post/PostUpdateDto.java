package com.itwill.spring2.dto.post;

import com.itwill.spring2.domain.Post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostUpdateDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    
    
    public static Post toEntity(PostUpdateDto dto) {
        
        return Post.builder()
                    .id(dto.getId())
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .author(dto.getAuthor())
                    .build();
    }
}
