package com.itwill.spring2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.post.PostCreateDto;
import com.itwill.spring2.dto.post.PostDetailDto;
import com.itwill.spring2.dto.post.PostListItemDto;
import com.itwill.spring2.dto.post.PostUpdateDto;
import com.itwill.spring2.repository.PostDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostService {
    // PostDao를 주입받음.
    // @Autowired private PostDao postDao;
    private PostDao postDao;
    PostService (PostDao postDao) {
        this.postDao = postDao;
    }
    
    
    public List<PostListItemDto> read() {
        // postDao 메서드를 호출해서 포스트 목록을 리턴받고, 컨트롤러에게 리턴해줌.
        List<Post> postList = postDao.selectOrderByIdDesc();
        
        log.debug("post list = {}", postList);
        
//        return postList;
        return postList.stream()
                    .map(PostListItemDto::fromEntity)
                    .toList();
    }
    
    
    public PostDetailDto read(Long id) {
        log.debug("read(id={})", id);
        
        Post post = postDao.selectById(id);
        
        PostDetailDto postDetailDto = PostDetailDto.fromEntity(post);
        log.debug("PostDetailDto={}", postDetailDto);
        
        return postDetailDto;
    }
    
    
    public int create(PostCreateDto dto) {
        log.debug("create(dto={})", dto);
        int result = 0;
        
        
        
        // 리포지터리(DAO) 계층의 메서드를 호출해서 테이블에 데이터를 삽입(insert).
        postDao.insert(dto.toEntity());
        log.debug("create result={}", result);
        
        return result;
    }
    
    // 삭제
    public int delete(Long id) {
        
        int result= postDao.delete(id);
        
        return result;
    }
    
    // 업데이트
    public int update(PostUpdateDto dto) {
        
        log.debug("update(PostUpdateDto={})", dto);
        
        Post post = dto.toEntity(dto);
        int result = postDao.update(post);
        
        return result;
    }
    
    // 검색
    public List<Post> search(String category, String keyword) {
        log.debug("search(category={}, searchKeyword={})", category, keyword);
        
        List<Post> postList = postDao.selectByKeyword(category, keyword);
        log.debug("postList={}", postList);
        return postList;
    }
    
    
}
