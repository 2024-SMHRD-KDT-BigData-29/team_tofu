%@ page import="com.together.furture.entity.user_info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원정보 수정 | TO.FU</title>
    <link href="resources/css/join.css" rel="stylesheet"> <!-- 스타일은 join.css 재사용 -->
</head>
<body>
    <div class="join-wrapper">
        <h2 class="logo">TO.FU</h2>
        <form method="post" action="user_edit.do" id="edit-form" enctype="multipart/form-data">
            <input type="text" name="user_id" id="user_id" class="join-input" value="${user.user_id}" readonly>
            <input type="password" name="user_pw" id="user_pw" class="join-input" placeholder="새 비밀번호 입력" required>
            <input type="password" id="confirm_pw" class="join-input" placeholder="새 비밀번호 확인" required>
            <span id="password-match-message" class="message"></span>
            
            <select name="user_gender" id="user_gender" class="join-select" required>
                <option value="" disabled>성별 선택</option>
                <option value="M" <c:if test="${user.user_gender == 'M'}">selected</c:if>>남성</option>
                <option value="W" <c:if test="${user.user_gender == 'W'}">selected</c:if>>여성</option>
            </select>
            
            <input type="text" name="user_nick" id="user_nick" class="join-input" value="${user.user_nick}" placeholder="닉네임을 입력해주세요" required>
            <input type="email" name="user_email" id="user_email" class="join-input" value="${user.user_email}" placeholder="이메일을 입력해주세요" required>
            <textarea name="user_intro" id="user_intro" class="join-textarea" placeholder="자신을 소개할 수 있는 한마디">${user.user_intro}</textarea>
            
            <div class="file-upload">
                <label for="user_profile" class="file-label" id="file-label">
                    <i class="fas fa-camera"></i> 프로필 사진 변경
                </label>
                <input type="file" name="user_profile" id="user_profile" class="file-input" accept="image/*">
                <div id="preview-container" class="preview-box">
                    <img id="preview-image" src="${user.user_profile != null ? 'resources/uploads/' + user.user_profile : 'resources/images/default-profile.png'}" alt="프로필 미리보기" class="preview-img"/>
                    <div id="file-info" class="file-meta"></div>
                </div>
            </div>
            
            <input type="submit" value="수정 완료" class="submit-btn">
        </form>
    </div>
    <script src="resources/js/join.js"></script> <!-- join.js 재사용 가능 -->
</body>
</html>