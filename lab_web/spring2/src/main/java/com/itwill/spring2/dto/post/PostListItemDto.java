package com.itwill.spring2.dto.post;

import java.time.LocalDateTime;

import com.itwill.spring2.domain.Post;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PostListItemDto {
    
    private Long id;
    private String title;
    private String author;
    private LocalDateTime created_time;
    private LocalDateTime modified_time;
    
    
    // Post 모델(엔터티) 객체를 PostListItemDto 타입 객체로 변환해서 리턴.
    public static PostListItemDto fromEntity(Post post) {
        return PostListItemDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .author(post.getAuthor())
                .created_time(post.getCreated_time())
                .modified_time(post.getModified_time())
                .build();
    }
}
