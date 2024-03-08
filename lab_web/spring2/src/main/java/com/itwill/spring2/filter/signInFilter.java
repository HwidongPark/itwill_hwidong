package com.itwill.spring2.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Servlet Filter implementation class signInFilter
 */
@Slf4j
public class signInFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public signInFilter() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO WAS(Tomcat)가 종료될 때 필터가 사용했던 리소스 등을 해제하기 위해서..
	    // 리소스 해제 코드가 필요하면 여기서 하면 됨
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 필터 체인의 다음 필터 또는 서블릿으로 요청이 전달되기 전에 할 일 작성.
		// 로그인되어 있는 지를 확인해서,
	    // (1) 로그인되어 있으면, 요청을 계속 진행
	    // (2) 로그인되어 있지 않으면, 로그인 후 이동할 페이지(타겟) 정보를 포함해서 로그인 페이지로 이동.
	    HttpServletResponse servletResponse = (HttpServletResponse) response;
	    HttpServletRequest servletRequest = (HttpServletRequest) request;
	    HttpSession session = servletRequest.getSession();
	    String sessionUserid = (String) session.getAttribute("signedInUser");
	    
	    
	    if (sessionUserid != null) {   // 세션에 로그인 정보가 있으면
	        log.debug("로그인 상태: {}", sessionUserid);
	        
	        // 요청을 필터 체인의 다음 단계(다음 필터 또는 서블릿)로 전달.
	        chain.doFilter(request, response);
	        
	    } else {   // 세션에 로그인 정보가 없으면
	        log.debug("로그아웃 상태 ---> 로그인 페이지로 이동");
	        
	        
	        String reqUrl = servletRequest.getRequestURL().toString();
	        log.debug("요청 주소: {}", reqUrl);
	        
	        // 요청에 쿼리 스트링이 있는지 확인.
	        String qs = servletRequest.getQueryString();
	        log.debug("쿼리 스트링: {}", qs);
	        
	        
	        String target = "";     // 로그인 후 이동할 페이지(타겟) 정보를 저장하기 위한 문자열. 
	        
	        if (qs == null) {
	            target = URLEncoder.encode(reqUrl, "UTF-8");
	        } else {
	            target = URLEncoder.encode(reqUrl + "?" + qs, "UTF-8");
	        }
	        
	        log.debug("target: {}", target);
	        
	        // 로그인 페이지로 이동(redirect)
	        String redirectUrl = servletRequest.getContextPath() + "/user/signin?target=" + target;
	        servletResponse.sendRedirect(redirectUrl);
	        
	    }
	    
//	    if (sessionUserid == null) {   
//	        String url = servletRequest.getContextPath() + "/user/signin";
//	        
//	        servletResponse.sendRedirect(url);
//	    } 
//	    
//	    chain.doFilter(request, response);
	    
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// WAS(Tomcat)가 필터를 생성하고 난 후, 필터의 초기화 작업이 필요한 경우.
	}

}
