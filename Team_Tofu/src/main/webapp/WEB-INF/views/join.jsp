<%@ page import="com.together.furture.entity.user_info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입 | TO.FU</title>
    <link href="resources/css/join.css" rel="stylesheet">
</head>
<body>
    <div class="join-wrapper">
        <h2 class="logo">TO.FU</h2>
        <form method="post" action="user_join.do" id="join-form" enctype="multipart/form-data">
            <input type="text" name="user_id" id="user_id" class="join-input" placeholder="아이디 또는 이메일" required>
            <input type="password" name="user_pw" id="user_pw" class="join-input" placeholder="비밀번호 입력" required>
            <input type="password" id="confirm_pw" class="join-input" placeholder="비밀번호 확인" required>
            <span id="password-match-message" class="message"></span>
            
            <select name="user_gender" id="user_gender" class="join-select" required>
                <option value="" disabled selected>성별 선택</option>
                <option value="M">남성</option>
                <option value="W">여성</option>
            </select>
            
            <input type="text" name="user_nick" id="user_nick" class="join-input" placeholder="닉네임을 입력해주세요" required>
            <input type="email" name="user_email" id="user_email" class="join-input" placeholder="이메일을 입력해주세요" required>
            <textarea name="user_intro" id="user_intro" class="join-textarea" placeholder="자신을 소개할 수 있는 한마디"></textarea>
            
            <div class="file-upload">
                <label for="user_profile" class="file-label" id="file-label">
                    <i class="fas fa-camera"></i> 프로필 사진 업로드
                </label>
                <input type="file" name="user_profile" id="user_profile" class="file-input" accept="image/*">
                <div id="preview-container" class="preview-box">
                    <img id="preview-image" src="resources/images/default-profile.png" alt="프로필 미리보기" class="preview-img"/>
                    <div id="file-info" class="file-meta"></div>
                </div>
            </div>
            
            <input type="submit" value="회원가입 완료" class="submit-btn">
        </form>
    </div>
    <script src="resources/js/join.js"></script>
</body>
</html>