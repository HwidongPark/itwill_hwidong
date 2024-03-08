package com.itwill.spring2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.post.PostCreateDto;
import com.itwill.spring2.dto.post.PostDetailDto;
import com.itwill.spring2.dto.post.PostListItemDto;
import com.itwill.spring2.dto.post.PostUpdateDto;
import com.itwill.spring2.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// @RequiredArgsConstructor    // final field들을 아규먼트로 갖는 생성자.
@Slf4j
@Controller
@RequestMapping("/post")
//-> PostController의 컨트롤러 메서드의 매핑URL(주소)는 "/post/"로 시작
public class PostController {
    
    // PostService 객체를 주입받음.
    // (1) 애너테이션을 사용한 의존성 주입
    // @Autowired PostService postService;
    // (2) 생성자에 의한 의존성 주입
    private final PostService postService;
    
    public PostController(PostService postService) {
        this.postService = postService;
    }
    
    
    @GetMapping("/list")    //-> Get방식의 "/post/list" 요청 주소를 처리하는 메서드
    public void list(Model model) {
        log.debug("list() Spring2");
        List<PostListItemDto> postList = postService.read();
                
        // postService의 메서드를 호출해서 포스트 목록을 만들고, 뷰에 전달.
        model.addAttribute("postList", postList);

        
        // 리턴 값이 없으면 요쳥 경로로 JSP를 찾음.
        //-> /WEB-INF/views/post/list.jsp  
        
    }
    
    @GetMapping("/create")
    public void create() {
        
        log.debug("GET create()");
    }
    
    @PostMapping("/create")
    public String create(PostCreateDto dto) {
        log.debug("Post Create(dto={})", dto);
        
        // 서비스 계층의 메서드를 호출해서 새 포스트 작성 서비스를 수행.
        postService.create(dto);
        
        return "redirect:/post/list";    // Context root(메인 페이지)로 이동(redirect)
    }
    
    
    @GetMapping({"/detail", "/modify"})
    //-> /post/detail, /post/modify 2개의 요청을 처리하는 메서드
    public void detail(@RequestParam Long id, Model model) {
        log.debug("detail(id={})", id);
        
        PostDetailDto postDetailDto =  postService.read(id);
        model.addAttribute("postDetailDto", postDetailDto);
        
    }
    
    @GetMapping("/delete")
    public String delete(Long id, Model model) {
        
        log.debug("delete(), id={}", id);
        
        postService.delete(id);
        
        return "redirect:/post/list";
    }
    
    @PostMapping("/update")
    public String update(PostUpdateDto dto) {
        log.debug("update(PostUpdateDto={})", dto);
        postService.update(dto);
        
        return "redirect:/post/detail?id=" + dto.getId();
    }
    
    @GetMapping("/search")
    public String search(String category, String keyword, Model model) {
        log.debug("search(category={}, keyword={})", category, keyword);
        
        List<Post> postList = postService.search(category, keyword);
        log.debug("postList={}", postList);
        
        model.addAttribute("postList", postList);
        
        return "post/list"; // 이러면 GET방식으로 /post/list 컨트롤러 호출하는게 아니라, 매핑된 대로 /WEB-INF/views/post/list.jsp파일을 찾아감 
//        return "forward:/WEB-INF/views/post/list.jsp";
        
    }
    
}
