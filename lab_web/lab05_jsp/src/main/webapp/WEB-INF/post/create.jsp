<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP 2</title>

		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

	</head>
	<body>
        <header class="my-2 p-4 bg-dark text-white">
            <h1>새 포스트 작성 페이지</h1>
        </header>
		<nav>
            <ul class="navbar navbar-expand-lg bg-body-tertiary">
                <li class="navbar-nav">
                <c:url var="mainPage" value="/" />    <%-- context root --%>
                    <a class="nav-link" href="${ mainPage }">메인 페이지</a>
                </li class="navbar-nav">
                <li class="navbar-nav">
                    <c:url var="postList" value="/post/list" />
                    <a class="nav-link" href="${ postList }">포스트 목록</a>
                </li>
            </ul>
        </nav>
        
        <main>
            <div>
                <c:url var="postCreate" value= "/post/create"/>
                <form method="post" action="${ postCreate }">
                    <div>
                        <label id="title">제목: </label>
                        <input class="form-control" type="text" name="title" placeholder="제목을 입력" id="title" required />
                    </div>
                    <br>
                    <div>
                        <label id="content">내용</label>
                        <br>
                        <textarea class="form-control" name="content" placeholder="내용 입력" cols="50" rows="20" id=content></textarea>
                    </div>
                    <br>
                    <div class="d-none">
                    <%-- d-none: 요소는 생성되지만 화면에서는 보이지 않음.
                         작성자 input의 value를 로그인한 사용자 아이디로 채움 --%>
                        <input class="form-control" type="text" placeholder="작성자" name="author" value="${ signedInUser }" readonly/>
                    </div>
                    
                    <div>
                        <button class="form-control btn-success bg-success my-2">작성</button>
                        <input class="form-control btn-secondary" type="button" value="취소">
                    </div>
                </form>
            </div>
        </main>

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

	</body>
</html>