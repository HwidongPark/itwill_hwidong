<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous">

</head>
<body>
    <header class="my-2 p-4 bg-dark text-white text-center">
        <h1>포스트 상세보기 페이지</h1>
    </header>

    <nav class="navbar navbar-expand-lg bg-body-tertiary my-2">
        <div class="container-fluid">
            <ul class="navbar-nav">
                <li class="nav-item"><c:url var="mainPage"
                        value="/" /> <%-- context root --%> <a
                    class="nav-link" href="${ mainPage }">메인 페이지</a></li>
                <li class="nav-item"><c:url var="postList"
                        value="/post/list" /> <a class="nav-link"
                    href="${ postList }">포스트 리스트</a></li>

            </ul>
        </div>
    </nav>

    <main>
        <div class="card m-4">
            <form class="card-body">
                <div class="my-2">
                    <label class="form-label" for="id">번호</label> <input
                        class="form-control" id="id" type="number"
                        value="${ post.id }" readonly />
                </div>
                <div class="my-2">
                    <label class="form-label" for="title">제목</label> <input
                        class="form-control" id="title" type="text"
                        value="${ post.title }" readonly />
                </div>
                <div class="my-2">
                    <label for="content" class="form-label">내용</label> <br>
                    <textarea id="content" class="form-control"
                        rows="10" cols="80" readonly>${ post.content }</textarea>
                </div>
                <div class="my-2">
                    <label for="author" class="form-label">작성자</label> <input
                        id="author" class="form-control" type="text"
                        value="${ post.author }" readonly />
                </div>
                <div class="my-2">
                    <label for="createdTime" class="form-label">작성
                        시간</label> <input id="createdTime" class="form-control"
                        type="text" value="${ post.createdTime }"
                        readonly />
                </div>
                
                <div class="my-2">
                    <label for="modifiedTime" class="form-label">수정
                        시간</label> <input id="modifiedTime" class="form-control"
                        type="text" value="${ post.modifiedTime }"
                        readonly />
                </div>
                
            </form>
        </div>
        
        <%-- 로그인한 사용자 아이디와 포스트 작성자 아이디가 같은 경우에만 수정 버튼을 보여줌. --%>
        <c:if test="${ signedInUser eq post.author }">
        <div class="card-footer m-4">
            <c:url var="postModify" value="/post/modify">
                <c:param name="id" value="${ post.id }" />
            </c:url>

            <a href="${ postModify }"
                class="btn btn-primary">수정</a>

        </div>
        </c:if>
    </main>

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>

</body>
</html>