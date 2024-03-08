<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Spring 2</title>

		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

	</head>
	<body>
        <div class="container-fluid">
    		<header>
                <c:set var="title" value="포스트 목록" />
                <%@ include file="../fragments/title.jspf" %>
                
                <!--navigation -->
                <%@ include file="../fragments/navigation.jspf" %>
            </header>
            
            <main class="my-2">
                <!-- TODO: 포스트 목록을 테이블로 그리기 -->
                <c:url var="searchPage" value="/post/search"/>
                <form id="searchForm" class="my-2" action="${ searchPage }">
                    <select name="category">
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                        <option value="author">작성자</option>
                        <option value="title_content">제목 + 내용</option>                        
                    </select>
                    <input type="text" name="keyword" palceholder="검색" class="col-5" required/>
                    <input type="submit" value="검색"/>
                </form>
                
                <div class="card">
                    <table class="card-body table table-hover table-striped">
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성 시간</th>
                                <th>수정 시간</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${ postList }" var="postList">
                                <tr>
                                    <td>${ postList.id }</td>
                                    <td>
                                        <!-- 나중에 value생각해보고 돌아오기 -->
                                        <c:url var="postDetailPage" value="/post/detail">
                                            <c:param name="id" value="${ postList.id }"/>
                                        </c:url>
                                        <a href="${ postDetailPage }">${ postList.title }</a>
                                    </td>
                                    <td>${ postList.author }</td>
                                    <td>${ postList.created_time }</td>
                                    <td>${ postList.modified_time }</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tbody>
            
                        </tbody>
                    </table>
                </div>
    
            </main>
        </div>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

	</body>
</html>