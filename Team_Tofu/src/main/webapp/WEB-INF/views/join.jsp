<%@ page import="com.together.furture.entity.user_info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="join-wrapper">
        <h2>TO.FU</h2>
        <form method="post" action="user_join.do" id="join-form">
            <input type="text" name="user_id" placeholder="아이디 또는 이메일">
            <input type="password" name="user_pw" placeholder="비밀번호 입력">
            <input type="password" placeholder="비밀번호 확인">
            <input type="text" name="user_nick" placeholder="닉네임을 입력해주세요">
            <input type="email" name="user_email" placeholder="이메일을 입력해주세요">
            <input type="textarea" name="user_intro" placeholder="자신을 소개할 수 있는 한마디">
            <input type="file" name="user_profile" >
            <input type="submit" value="회원가입">
        </form>
   </div>
</body>
</html>