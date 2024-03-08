package com.itwill.jsp2.controller.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Post;
import com.itwill.jsp2.service.PostService;

/**
 * Servlet implementation class PostDetailsController
 */
@WebServlet(name="PostDetailsController", urlPatterns = { "/post/details" })
public class PostDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(PostDetailsController.class);
	
	private final PostService postService = PostService.getInstance();
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("doGet()");
		
		// 요청 파라미터 id를 찾음 -> DB에서 ID로 검색 -> 검색 결과를 뷰에 전달
		String param = request.getParameter("id");    // 요청 파라미터 id의 값을 읽음
		long id = Long.parseLong(param);  // 요청 파라미터 값(문자열)을 long 타입으로 변환
		log.debug("--- id = {}", id);
		
		// 서비스(비즈니스) 계층의 메서드를 호출헤서 뷰에 작성할 내용을 찾음.
		Post post = postService.read(id);
		
		request.setAttribute("post", post);
		
		
		request.getRequestDispatcher("/WEB-INF/post/details.jsp")
		    .forward(request, response);
		
	}

}
