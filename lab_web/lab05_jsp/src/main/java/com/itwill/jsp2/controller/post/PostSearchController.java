package com.itwill.jsp2.controller.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Post;
import com.itwill.jsp2.dto.PostSearchDto;
import com.itwill.jsp2.service.PostService;

/**
 * Servlet implementation class PostSearchController
 */
@WebServlet(name="PostSearchController", urlPatterns = {"/post/search"})
public class PostSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostService postService = PostService.getInstance();
	    
	    String category = request.getParameter("category");
	    String keyword = request.getParameter("keyword");
	    List<Post> searchedPosts = new ArrayList<>();
	    Logger log = LoggerFactory.getLogger(PostSearchController.class);
	    
	    PostSearchDto dto =  new PostSearchDto(category, keyword);
	    log.debug("Search Dto={}", dto);
	    
	    searchedPosts = postService.search(dto);
	    
	    log.debug("searchedPosts={}", searchedPosts);
	    
	    request.setAttribute("posts", searchedPosts);
	    request.setAttribute("toListButton", "inline-block");
	    
	    request.getRequestDispatcher("/WEB-INF/post/list.jsp")
	        .forward(request, response);
	    
	    
	}
	

}
