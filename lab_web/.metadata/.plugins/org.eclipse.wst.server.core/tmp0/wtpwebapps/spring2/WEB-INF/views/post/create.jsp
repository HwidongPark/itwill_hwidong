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
        <div>
            <header>
                <c:set var="title" value="새 포스트 작성" />
        		<%@ include file="../fragments/title.jspf" %>                
                <%@ include file="../fragments/navigation.jspf" %>
            </header>
        </div>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        
        <main class="my-2">
            <div class="my-2 card">
                <c:url var="postCreatePage" value="/post/create" />
                <form class="card-body" action="${ postCreatePage }" method="post">
                    <div class="my-2">
                        <input class="form-control" type="text" name="title" placeholder="제목" required autofocus />
                    </div>
                    <div class="my-2">
                        <textarea class="form-control" name="content" placeholder="내용" rows="30" cols="50" required></textarea>
                    </div>
                    <div class="my-2">
                        <input class="d-none" type="text" name="author" value ="${ sessionScope.signedInUser }" readonly />
                    </div>
                    <div class="my-2">
                        <input class="form-control btn btn-success" type="submit" value="작성 완료"/>
                    </div>
                </form>
            </div>
        </main>
	</body>
</html>