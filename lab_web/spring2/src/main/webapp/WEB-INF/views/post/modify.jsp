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
		    <c:set var="title" value="포스트 수정" />
            <%@ include file="../fragments/title.jspf" %>        
            <%@ include file="../fragments/navigation.jspf" %>
            
            <main>
                <div class="card my-2">
                    <div class="card-body">
                        <form id="postModifyForm">
                            <div class="d-none">
                            <label>번호</label>
                            <input type="text" name="id" id="id" value="${ postDetailDto.id }" />
                            </div>
                            <label class="form-label" autofocus>제목</label>
                            <input class="form-control" type="text" id="title" name="title" value="${ postDetailDto.title }" required />
                            <label class="form-label">내용</label> 
                            <textarea class="form-control" rows="20" name="content" id="content" required>${ postDetailDto.content }</textarea>
                            <br>
                            <label class="form-label">작성자 </label>
                            <input class="form-control" name="author" type="text" id="author" value="${ postDetailDto.author }" readonly />
                            <label class="form-label">작성 시간</label>
                            <input class="form-control"  type="text" id="created_time" value="${ postDetailDto.createdTime }" readonly />
                            <label class="form-label">수정 시간</label>
                            <input class="form-control" type="text" id="modified_time" value="${ postDetailDto.modifiedTime }" readonly />
                        </form>
                        <c:if test="${ postDetailDto.author eq signedInUser }">
                            <button class="btn btn-danger my-2" id="btnDelete">삭제</button>
                            <button class="btn btn-success my-2" id="btnUpdate">수정완료</button>
                        </c:if>
                    </div>
                    
                </div>
            </main>
            
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script src="../js/post-modify.js"></script>
        
	</body>
</html>