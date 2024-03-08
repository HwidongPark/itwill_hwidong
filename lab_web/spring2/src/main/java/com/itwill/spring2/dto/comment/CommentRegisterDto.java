package com.itwill.spring2.dto.comment;

import com.itwill.spring2.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // 아규먼트를 갖지 않는 생성자. 기본 생성자
@AllArgsConstructor // 아규먼트 3개를 갖는 생성자
@Builder
public class CommentRegisterDto {
    private Long postId;
    private String ctext;
    private String writer;
    
    
    // CommentRegisterDto 타입 dto를 Comment타입으로 변환
    public Comment toEntity() {
        return Comment.builder()
                    .postid(postId)
                    .ctext(ctext)
                    .writer(writer)
                    .build();
    }

}