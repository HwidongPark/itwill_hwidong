<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP 2</title>

		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        
        <style>
        </style>
        
	</head>
    
	<body>
    <header class="my-2 p-4 bg-dark text-white">
        <h1>포스트 목록 페이지</h1>
    </header>		
        
        <nav class="navbar navbar-expand-lg bg-body-tertiary my-2">
            <div class="container-fluid">
                    <ul class="navbar-nav">
                        <div class="my-2">
                            <li class="nav-item">
                            <c:url var="mainPage" value="/" />    <%-- context root --%>
                                <a class="nav-link" href="${ mainPage }">메인 페이지</a>
                            </li>
                        </div>
                        <div class="my-2">
                            <li class="nav-item">
                                <c:url var="postCreate" value="/post/create" />
                                <a class="nav-link" href="${ postCreate }">새 포스트 작성</a>
                            </li>
                        </div>
                        <div class="my-2">
                            <c:if  test="${toListButton eq 'inline-block'}"  >
                            <li id="toList">
                                <c:url var="postList" value="/post/list" />
                                <a class="nav-link" href="${ postList }">포스트 목록</a>
                            </li>
                            </c:if>
                        </div>
                        <%-- 확인 --%>
                        <c:if test="${ not empty sessionScope.signedInUser }">
                            <div class="my-2">
                                <li>
                                    <c:url var="signOut" value="/user/signout"/> <%-- TODO --%>
                                    <a class="nav-link" href="${ signOut }"><span>${ sessionScope.signedInUser }</span> 로그아웃</a>
                                </li>
                            </div>
                         </c:if>
                         <c:if test="${ empty sessionScope.signedInUser }">
                             <div class="my-2">
                                <li class="nav-item">
                                    <c:url var="signInPage" value="/user/signin" >
                                        <c:param name="target" value="http://localhost:8081/jsp2/post/list" />
                                    </c:url>
                                    <a class="nav-link" href="${ signInPage }">로그인</a>
                                </li>
                            </div>
                            <div class="my-2">
                                <li class="nav-item">
                                    <c:url var="signUpPage" value="/user/signup" >
                                        <c:param name="target" value="http://localhost:8081/jsp2/list" />
                                    </c:url>
                                    <a class="nav-link" href="${ signUpPage }">회원 가입</a>
                                </li>
                            </div>
                        </c:if>
                        <%-- 확인 끝 --%>
                    </ul>
            </div>
        </nav>
        
        <main class="my-2">
            <div class="card p-3">
            <div class="card-header my-2">
            <c:url var="searchPage" value="/post/search" />
            <form action="${ searchPage }">
                    <select name="category">
                        <option value="t">제목</option>
                        <option value="c">내용</option>
                        <option value="a">작성자</option>
                        <option value="tc">제목 + 내용</option>
                    </select>
                    <input type="text" name="keyword" autofocus required />
                    <button id="searchBtn">검색</button>
                </form>
            </div>
                <table class="table table-striped card-body">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>수정시간</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="p" items="${ posts }">
                            <tr>
                                <td>${ p.id }</td>
                                <td>
                                    <c:url var="postDetails" value="/post/details" >
                                        <c:param name="id" value="${ p.id }" />
                                    </c:url>
                                    <a href="${ postDetails }">${ p.title }</a>
                                </td>

                                <td>${ p.author }</td>
                                <td>${ p.modifiedTime }</td>                                
                            </tr>
                        </c:forEach>
                    </tbody>        
                </table>
            </div>
        </main>

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script src="../js/post_search.js"></script>
	</body>
</html>