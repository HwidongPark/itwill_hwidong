
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP 2</title>

		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
        rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
        crossorigin="anonymous">

	</head>
	<body>
		<header class="my-2 p-4 bg-dark text-white text-center">
            <h1>메인 페이지</h1>
        </header>
        
        <!-- 네비게이션 메뉴 -->
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <ul class="navbar-nav">
            <div class="my-2">
                <li class="nav-item">
                    <c:url var="postList" value="/post/list" />
                    <a class="nav-link" href="${postList}">포스트 목록 페이지</a>
                </li>
             </div>
             <%-- 세션에 signedInUser 속성이 있으면(로그인되어 있으면) --%>
             <c:if test="${ not empty sessionScope.signedInUser }">
                <div class="my-2">
                    <li>
                        <c:url var="signout" value="/user/signout"/> <%-- TODO --%>                        
                        <a class="nav-link" href="${ signout }" /><span>${ sessionScope.signedInUser }</span> 로그아웃</a>
                    </li>
                </div>
             </c:if>
             <%-- 세션에 signedInUser속성이 없으면(로그인되어 있지 않으면) --%>
             <c:if test="${ empty sessionScope.signedInUser }">
                 <div class="my-2">
                    <li class="nav-item">
                        <c:url var="signInPage" value="/user/signin">
                            <c:param name="target" value="http://localhost:8081/jsp2/" />
                        </c:url>
                        <a class="nav-link" href="${ signInPage }">로그인</a>
                    </li>
                </div>
                <div class="my-2">
                    <li class="nav-item">
                        <c:url var="signUpPage" value="/user/signup" />
                        <a class="nav-link" href="${ signUpPage }">회원 가입</a>
                    </li>
                </div>
            </c:if>

            </ul>
        </nav>
        
        <!-- 메인 컨텐트 -->
        <main></main>

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" 
        crossorigin="anonymous"></script>

	</body>
</html>