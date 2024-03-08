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
            <c:set var="title" value="회원가입" />
            <%@ include file="../fragments/title.jspf" %>
            <%@ include file="../fragments/navigation.jspf" %>
        </div>
        
        <c:url var="/user/signup" value="signUpPage" />
        <form action="${ signUpPage }" method="post" >
            <div class="my-2">
                <input class="form-control" id="userid" type="text" name="userid" placeholder="아이디" required>
            </div>
            <div class="my-2">
                <input class="form-control" id="password" type="password" name="password" placeholder="비밀번호" required>
            </div>
            <div class="my-2">
                <input class="form-control" id="email" type="email" name="email" placeholder="이메일">
            </div>
            <div id="isExistingUser"></div>
            <div class="my-2">                
                <input id="btnSignUP" class="btn btn-primary disabled" type="submit" value="회원가입">
            </div>
        </form>
        
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
        <script src="../js/user.js"></script>
        
	</body>
</html>