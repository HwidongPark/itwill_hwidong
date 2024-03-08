package com.itwill.jsp2.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Post;
import com.itwill.jsp2.dto.PostCreateDto;
import com.itwill.jsp2.dto.PostListItemDto;
import com.itwill.jsp2.dto.PostSearchDto;
import com.itwill.jsp2.dto.PostUpdateDto;
import com.itwill.jsp2.repository.PostDao;
import com.itwill.jsp2.repository.UserDao;


// Model 2 MVC 아키텍쳐에서 서비스(비즈니스) 계층을 담당하는 클래스.
public class PostService {
    private static final Logger log = LoggerFactory.getLogger(PostService.class);
    private final PostDao postDao;
    private final UserDao userDao;  
    
    // final로 선언된 필드는 반드시 명싲거으로 초기화를 수행해야 함!
    // (1) 필드를 선언하는 위치에서 초기화를 하거나,
    // (2) 생성자에서 초기화.
    
    
    // Singleton 적용
    private static PostService instance = null;
    
    private PostService() {
        postDao = PostDao.getInstance();
        userDao = UserDao.getInstance();
    }
    
    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService();
        }
        
        return instance;
    }
    
    
    
    public List<PostListItemDto> read() {
        log.info("read()");
        
        // DAO의 메서드를 호출해서 DB POSTS 테이블에서 (아이디 내림차순) 전체 검색
        List<Post> list = postDao.select();
        // List<Post>를 List<PostListItemDto>로 변환해서 컨트롤러에게 리턴.
        List<PostListItemDto> result = list.stream()
                .map(PostListItemDto::fromPost)    // (x) -> PostListItemDto.fromPost(x)
                .toList();
        
        return result;
    }
    
    public Post read(long id) {
        log.debug("read(id={})", id);
        
        Post post = postDao.select(id);
        log.debug("select result = {}", post);
        
        return post;
    };
    
    
    
    public void create(PostCreateDto dto) {
        log.debug("create(dto={})", dto);
        
        // PostCreateDto를 Post 타입으로 변환해서, PostDao의 메서드를 호출할 때 전달.
        int resultInsert = postDao.insert(dto.toPost());
        log.debug("insert result = {}", resultInsert);
        
//        int resultUpdate = userDao.updatePoints(10, dto.getAuthor());
        if (resultInsert == 1) {  // 새 포스트 작성을 성공하면,
            // 포스트 작성자에게 10포인트를 지급.
            userDao.updatePoints(10, dto.getAuthor());
            
        }
        
    }
    
    
    public int delete(long id) {
        log.debug("delete(id={})", id);
        
        
        return postDao.delete(id);
    }
    
    
    public int update(PostUpdateDto dto) {
        log.debug("update(dto={})", dto);
        
        int result = postDao.update(dto.toPost());
        
        return result;
    }
    
    
    public List<Post> search(PostSearchDto dto) {
        log.debug("search(dto={})", dto);
        String category = dto.getCategory();
        String keyword = dto.getKeyword();
        
        List<Post> posts = postDao.select();
        List<Post> result = new ArrayList<>();
        
        if (category.equals("t")) { // 제목 검색
            
            result = posts.stream().filter((e) -> e.getTitle().toLowerCase().contains(keyword.toLowerCase())).toList();
            
        } else if (category.equals("c")) {  // 컨텐트 검색
            
            result = posts.stream().filter((e) -> e.getContent().toLowerCase().contains(keyword.toLowerCase())).toList();
            
        } else if (category.equals("tc")) { // 제목 + 컨탠트
            
            result = posts.stream().filter((e) -> e.getTitle().toLowerCase().contains(keyword.toLowerCase()) || e.getContent().toLowerCase().contains(keyword.toLowerCase())).toList();
            
        } else {    // 작성자 검색
            
            result = posts.stream().filter((e) -> e.getAuthor().toLowerCase().contains(keyword.toLowerCase())).toList();
            
        }
        
        log.debug("검색 결과={}",result);
        
        return result;
        
    }
    

}
