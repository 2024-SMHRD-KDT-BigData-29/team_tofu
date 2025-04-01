<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 탈퇴 | TO.FU</title>
    <link href="resources/css/join.css" rel="stylesheet">
</head>
<body>
    <div class="join-wrapper">
        <h2 class="logo">TO.FU</h2>
        <p style="text-align: center; margin-bottom: 20px;">회원 탈퇴</p>
        
        <form method="post" action="user_delete.do" id="delete-form">
            <input type="submit" value="탈퇴하기" class="submit-btn">
        </form>
    </div>
</body>
</html>