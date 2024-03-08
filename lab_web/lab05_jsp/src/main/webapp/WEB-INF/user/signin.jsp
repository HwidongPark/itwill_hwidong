<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>

		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

	</head>
	<body>
		<header>
            <h1 class="text-center text-white bg-black p-4">로그인 페이지</h1>
        </header>
        
        <nav>
        </nav>
        
        <main>
            <div class="card">
                <div class="card-body">
                    <%-- 세션에 signedInUser 속성이 있으면 --%>
                    <c:if test="${empty sessionScope.signedInUser && param.result eq 'fail' }">  <%--  not empty의 의미는 비어있는 문자열 또는 null이 아니다 라는 의미 --%>
                        <div class="text-danger">아이디와 비밀번호를 확인하세요</div>
                    </c:if>
                    <c:url var="signInPage" value="/user/signin"  />
                    
                    <form action="${ signInPage }" method="post">
                        <div class="my-2">
                            <input class="form-control" type="text" name="userid" placeholder="아이디" required autofocus />
                        </div>
                        <div class="my-2">
                            <input class="form-control" type="password" name="password" placeholder="비밀번호" required />
                        </div>
                        <div class="d-none">
                            <input type="hidden" name="target" value="${ param.target }" readonly>
                        </div>
                        <div class="my-2">
                            <input class="form-control btn btn-primary" type="submit" value="로그인" />                            
                        </div>
                    </form>
                </div>
            </div>
        </main>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

	</body>
</html>