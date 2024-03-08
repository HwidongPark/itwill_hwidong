package com.itwill.spring2.dto.comment;

import com.itwill.spring2.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateDto {
    long id;
    String ctext;
    
    
    public Comment toEntity() {
        return Comment.builder().id(this.id).ctext(this.ctext).build();
    }
}
