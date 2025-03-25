<%@ page import="com.together.furture.entity.user_info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>join</title>
    <link href="resources/css/join.css" rel="stylesheet"/>
</head>
<body>
    <div class="join-wrapper">
        <h2>TO.FU</h2>
        <form method="post" action="user_join.do" id="join-form" enctype="multipart/form-data">
            <input type="text" name="user_id" placeholder="아이디 또는 이메일">
            <input type="password" name="user_pw" id="user_pw" placeholder="비밀번호 입력">
            <input type="password" id="confirm_pw" placeholder="비밀번호 확인">
            <span id="password-match-message"></span> <!-- 비밀번호 일치 여부 메시지 -->
            <select name="user_gender" id="user_gender" class="join-form">
                <option value="M">남</option>
                <option value="W">여</option>
            </select>
            <input type="text" name="user_nick" placeholder="닉네임을 입력해주세요">
            <input type="email" name="user_email" placeholder="이메일을 입력해주세요">
            <textarea name="user_intro" placeholder="자신을 소개할 수 있는 한마디"></textarea>
            <div class="file-upload">
                <label for="user_profile" class="file-label" >프로필 사진을 업로드하세요</label>
                <input type="file" name="user_profile" id="user_profile" class="file-input">
            </div>
            <input type="submit" value="회원가입">
        </form>
    </div>
    <script src="resources/js/join.js"></script>
</body>
</html>