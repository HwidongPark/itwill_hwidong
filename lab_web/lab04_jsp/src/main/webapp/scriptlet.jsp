<%@page import="com.itwill.jps1.model.Contact"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>

		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

	</head>
	<body>
		<h1>JSP Scriptlet 활용</h1>
        <%-- 
            scriptlet: JSP 안엣어 자바 코드들을 작성하기 위한 태그.
            지역변수 선언 & 초기화, 객체 생성, 메서드 호출, 조건문, 반복문, ...
         --%>
         
         
         <%-- html 테이블 작성에 필요한 더미 데이터생성 --%>
         <%
         ArrayList<Contact> data = new ArrayList<>();   // 더미 데이터를 저장할 리스트
         for (int i = 1; i <= 10; i++) {
             // 컨택트 타입의 객체 생성:
             Contact contact = new Contact(i, "이름_" + i, "전화번호_" + i, "이메일_" + i);
             data.add(contact); // ArrayList에 연락처를 저장.
         }
         
         %>
         
         <h2>스크립트릿 사용한 테이블 작성</h2>
         <table class="table">
            <thaed>
                <tr>
                    <th>번호</th>
                    <th>이름</th>
                    <th>전화번호</th>
                    <th>이메일</th>
                </tr>
            </thaed>
            <tbody>
                <%
                for(Contact c: data) {
                    out.write("<tr>");
                    out.write("<td>" + c.getId() + "</td>");
                    out.write("<td>" + c.getName() + "</td>");
                    out.write("<td>" + c.getPhone() + "</td>");
                    out.write("<td>" + c.getEmail() + "</td>");
                    out.write("</tr>");
                }
                %>
            </tbody>         
         </table>
         
         
         
         <h2>스크립트릿과 식(expression)을 사용한 테이블 작성</h2>
         <table class="table">
            <thaed>
                <tr>
                    <th>NO.</th>
                    <th>NAME</th>
                    <th>PHONE</th>
                    <th>EMAIL</th>
                </tr>
            </thaed>
            <tbody>
                <%
                for (Contact c : data) { %>
                    <tr>
                        <td><%= c.getId() %></td>
                        <td><%= c.getName() %></td>
                        <td><%= c.getPhone() %></td>
                        <td><%= c.getEmail() %></td>
                    </tr>
               <% } %>
                
            </tbody>         
         </table>
         
         
         <%-- 연습문제 --%>
         <h2>Unordered List</h2>
         <%-- 스크립트릿과 식을 사용해서 연락처의 이름들을 ul로 작성. --%>
         <ul>
         <% for(Contact c : data) { %>
                <li><%= c.getName() %></li>
         
         <%} %>
         </ul>
         
         <h2>Description List</h2>
         <%-- 스크립트릿과 식을 사용해서 dl을 작성(dt: 이름, dd: 전화번호, 이메일) --%>
         <dl>
         <% for(Contact c : data) { %>
                <dt><%= c.getName() %></dt>
                <dd><%= c.getPhone() %></dd>
                <dd><%= c.getEmail() %></dd>
         <% } %>
         </dl>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

	</body>
</html>