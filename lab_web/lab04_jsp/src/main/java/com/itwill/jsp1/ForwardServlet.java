package com.itwill.jsp1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ForwardServlet
 */
@WebServlet(name="forwardServlet", urlPatterns = {"/ex3"})
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("forwardServlet::doGet() 호출");
	    
	    // servlet 클래스에서 html코드를 작성하는 것은 너무 번거로움
	    // JSP에서 HTML 코드들을 생성하는 것이 좋음.
	    // 클라이언트에서 서버로 온 요청을 "forward" 방식으로 이동:
	    // - 같은 WAS의 같은 웹 애플리케이션 안에서만 페이지 를 이동하는 방식.. 다른 WAS라는건 context root가 다른 것을 의미
	    // - 요청 주소(URL)이 바뀌지 않음.
	    // - request, response 객체가 유지됨.
	    // - 다른 WAS 또는 다른 웹 애플리케이션의 페이지로 forward할 수는 없음!
	    
	    
	    
	    // src/main/webapp 폴더 아래의 파일 경로와 이름 아규먼트로 전달. webapp아래부터 주소만 쓰면 됨
	    request.getRequestDispatcher("example.jsp")
	        .forward(request, response);
	    
	}

}