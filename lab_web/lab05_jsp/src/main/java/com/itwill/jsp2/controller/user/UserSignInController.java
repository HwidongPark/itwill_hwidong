package com.itwill.jsp2.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.User;
import com.itwill.jsp2.dto.UserSignInDto;
import com.itwill.jsp2.service.UserService;

/**
 * Servlet implementation class UserSignInController
 */
@WebServlet(name="userSignInController", urlPatterns = {"/user/signin"})
public class UserSignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UserSignInController.class);
	
	private final UserService userService = UserService.getInstance();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("doGet()");
		
		request.setAttribute("target", request.getParameter("target"));
		
		log.debug("doGet()에서 target Parameter = {}", request.getParameter("target"));
		
		request.getRequestDispatcher("/WEB-INF/user/signin.jsp")
		    .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("doPost()");
	    
	    // 요청 파라미터 userid, password를 찾음.
	    String userid = request.getParameter("userid");
	    String password = request.getParameter("password");
	    UserSignInDto dto = new UserSignInDto(userid, password);
	    
	    log.debug("dto=({})",dto);
	    
	    // 서비스 메서드를 호출하면서 로그인 정보를 전달.
	    UserService userService = UserService.getInstance();
	    User signedInUser = userService.signIn(dto);
	    
	    log.debug("user={}", signedInUser);
	    
        // 타겟 가져옴
        String target = request.getParameter("target");
        log.debug("target확인확인={}", target);
	    
	    
	    if (signedInUser != null) {
//	        response.sendRedirect(request.getContextPath() + "/post/list");
	        HttpSession session = request.getSession();
	        // -> 세션이 생성되어 있지 않은 경우에는 새로운 세션 객체를 생성해서 리턴,
	        // -> 세션이 이미 생성되어 있는 경우에는 기존 세션을 리턴.
	        log.debug("user not null, will be signed in");
	        
	        session.setAttribute("signedInUser", signedInUser.getUserid());
	        
	        // -> 세션의 로그인 성공한 사용자의 아이디를 저장
	        
	        
	        
	        response.sendRedirect(target);
	        
	    } else {
//	        request.setAttribute("result", "fail");
////	        response.sendRedirect(request.getContextPath() + "/user/signin");
//	        request.getRequestDispatcher("/WEB-INF/user/signin.jsp")
//	            .forward(request, response);
	        log.debug("user null");
	        String url = request.getContextPath() + "/user/signin?result=fail&target=" + URLEncoder.encode(target, "UTF-8");
	        response.sendRedirect(url);
	    }

	    
	}

}
