package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Post;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        locations = {"file:src/main/webapp/WEB-INF/application-context.xml"}
)
public class PostDaoTest {
    // PostDao 타입 객체 주입.
    @Autowired private PostDao postDao;
    
    
    @Test
    public void selectTest() {
        Assertions.assertNotNull(postDao);
        log.debug("postDao={}", postDao);
        
        List<Post> list = postDao.selectOrderByIdDesc();
        log.debug("list size={}", list.size());
        
        if (list.size() >0) {
            log.debug(list.get(0).toString());
        }
    }
    
    
//    @Test
//    public void selectByIdTest() {
//        // 테이블에 존재하는 아이디로 검색했을 때
//        Post selectedPost1 = postDao.selectById(1);
//        log.debug("selectedBy1={}", selectedPost1);
//        Assertions.assertNotNull(selectedPost1);
//        
//        
//        // 테이블에 존재하지 않는 아이디로 검색했을 때
//        Post selectedPost2 = postDao.selectById(2412);
//        log.debug("selectedBy2={}", selectedPost2);
//        Assertions.assertNull(selectedPost2);
//        
//    }
    
//    @Test
//    public void insertTest() {
//        Post post = Post.builder().title("MyBatis 테스트").content("11/29 MyBatis 테스트").author("admin").build();
//        int result = postDao.insert(post);
//        
//        log.debug("result={}", result);
//        Assertions.assertEquals(1, result);
//        
//    }
    
    
//    @Test
//    public void updateTest() {
//        Post post = Post.builder()
//                .title("Mybatis 업데이트 테스트")
//                .content("업데이트")
//                .id(28L).build();
//        int result1 = postDao.update(post);
//        
//        log.debug("result1={}", result1);
//        Assertions.assertNotNull(result1);  // 아이디가 존재하는 경우 업데이트 성공
//        
//        Post post2 = Post.builder().id(123124L)
//                .title("")
//                .content("")
//                .build();
//        int result2 = postDao.update(post2);
//        Assertions.assertEquals(0, result2);
//    }

    
//    @Test
//    public void deleteTest() {
//        long id = 21;
//        int result1 = postDao.delete(id);
//        
//        log.debug("result1={}", result1);
//        Assertions.assertEquals(1, result1);
//    }
    

    
    
}
