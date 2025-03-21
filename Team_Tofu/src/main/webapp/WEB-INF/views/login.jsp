<%@ page import="com.together.furture.entity.user_info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 메인페이지 로그인 구현 -->
	<div class="login-wrapper">
         <h2>TO.FU</h2>
         <form method="post" action="/user_login.do" id="login-form">
             <input type="text" name="user_id" placeholder="아이디 또는 이메일">
             <input type="password" name="user_pw" placeholder="비밀번호 입력">
             <a href="views/join.jsp">회원가입</a>
             <input type="submit" value="Login">
         </form>
     </div>
</body>
</html>