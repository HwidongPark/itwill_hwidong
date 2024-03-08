package com.itwill.spring2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Comment;
import com.itwill.spring2.dto.comment.CommentListItemDto;
import com.itwill.spring2.dto.comment.CommentRegisterDto;
import com.itwill.spring2.dto.comment.CommentUpdateDto;
import com.itwill.spring2.repository.CommentDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {
    
    private final CommentDao commentDao;    // 생성자에 의한 의존성 주입
    public int create(CommentRegisterDto dto) {
        log.debug("create");
        
        // 리포지토리 계층의 메서드를 호출해서 COMMENTS 테이블에 데이터를 삽입(insert).
        int result = commentDao.insert(dto.toEntity());
        log.debug("댓글 등록 결과 = {}", result);
        
        
        return result;
    }
    
    
    public List<CommentListItemDto> read(long postId) {
        log.debug("read(postId={}", postId);
        
        // 리포지토리 계층의 메서드를 호출해서 데이터베이스 테이블 검색(select)
        List<Comment> list = commentDao.selectByPostId(postId);
        log.debug("댓글 개수 = {}", list.size());
        
        return list.stream()
                    .map(CommentListItemDto::fromEntity)    // (x) -> CommentListItemDto.fromEntity(x)
                    .toList();
        
    }
    
    
    public int delete(long id) {
        log.debug("delete(id={})", id);
        
        // 리포지토리 계층의 메서드를 호출해서 COMMENTS 테이블에서 댓글 1개를 삭제.
        int result = commentDao.deleteById(id);
        
        return result;
    }
    
    
    public CommentListItemDto readById(long id) {
        log.debug("readById(id={})", id);
        
        // 리포지토리 계층에 메서드를 호출해서 Comments 테이블에서 id로 검색
        Comment comment = commentDao.selectById(id);
        log.debug("comment={}", comment);
        
        return CommentListItemDto.fromEntity(comment);
        
    }
    
    
    public int updateById(CommentUpdateDto dto) {
        log.debug("updateById(dto={})", dto);
        
        //레포지토리 계층에 메서드 호출해서 comment테이블에서 해당 id 댓글 업데이트
        Comment comment = dto.toEntity();
        int result = commentDao.update(comment);
        log.debug("result={}", result);
        
        return result;
    }
    
    
}
