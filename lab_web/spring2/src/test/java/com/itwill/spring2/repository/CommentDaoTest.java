package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Comment;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        locations = {"file:src/main/webapp/WEB-INF/application-context.xml"}
)
@Slf4j
public class CommentDaoTest {
    
    @Autowired private CommentDao commentDao;
    
//    @Test
//    public void testSelectByPostId() {
//        
//        Assertions.assertNotNull(commentDao);
//       
//        List<Comment> list = commentDao.selectByPostId(1);
//        for (Comment c : list) {
//            log.debug(c.toString());
//        }
//        
//    }
    
//    @Test
//    public void testInsert() {
//        
//        int result = commentDao.insert(Comment.builder().postid(1L).writer("admin").ctext("댓글 추가 테스트6").build());
//        Assertions.assertEquals(1, result);
//        
//    }
    
//    @Test
//    public void testDeleteById() {
//        int result = commentDao.deleteById(8L);
//        Assertions.assertEquals(1, result);
//    }
    
    
//    @Test
//    public void testDeleteByPostid() {
//        
//        int result = commentDao.deleteByPostId(1);
//        Assertions.assertNotEquals(1, result);
//    }
    
//    @Test
//    public void testUpdate() {
//        Comment comment = Comment.builder().id(8L).ctext("이건 MyBatis를 이용한 수정").build();
//        
//        int result = commentDao.update(comment);
//        Assertions.assertEquals(1, result);
//    }
    
    @Test
    public void testSelectCommentCounts() {
        
        Long result = commentDao.selectCommentCounts(1L);
        log.debug("result={}", result);
        
    }
    
    
    
}
