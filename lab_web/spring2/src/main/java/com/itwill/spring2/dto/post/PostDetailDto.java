package com.itwill.spring2.dto.post;

import java.time.LocalDateTime;

import com.itwill.spring2.domain.Post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDetailDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    
    public static PostDetailDto fromEntity(Post post) {
        return PostDetailDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .author(post.getAuthor())
                    .createdTime(post.getCreated_time())
                    .modifiedTime(post.getModified_time())
                    .build();
    }
}
