package com.itwill.spring2.dto.comment;

import java.sql.Timestamp;

import com.itwill.spring2.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentListItemDto {
    
    
    private Long id;
    private String ctext;
    private String writer;
    private Timestamp createdTime;
    private Timestamp modifiedTime;
    
    // 엔티티 Comment 객체를 DTO 객체로 변환해서 리턴.
    public static CommentListItemDto fromEntity(Comment comment) {
        return CommentListItemDto.builder()
                                .id(comment.getId())
                                .ctext(comment.getCtext())
                                .writer(comment.getWriter())
                                .createdTime(Timestamp.valueOf(comment.getCreated_time()))
                                .modifiedTime(Timestamp.valueOf(comment.getModified_time()))
                                .build();
    }
    
    
    
}
