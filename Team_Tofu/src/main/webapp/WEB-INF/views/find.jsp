<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FIND</title>
    <link rel="stylesheet" href="resources/css/find.css">
</head>
<body>
    <div class="find-container">
        <div class="find-header">
            <form action="main">
                <button id="back-button">←</button>
            </form>
        <h2>FIND</h2>
    </div>
    <div class="find-content">
        <!-- 동적으로 협업방 목록 출력 -->
        <c:forEach var="c" items="${coworkList}">
            <div class="room">
                <div class="room-author">
                    <!-- user_id를 기준으로 feedList에서 프로필 이미지 찾기 -->
                    <c:set var="profileFound" value="false" />
                    <c:forEach var="f" items="${feedList}">
                        <c:if test="${c.user_id == f.user_id && !profileFound}">
                            <c:set var="profileFound" value="true" />
                            <c:choose>
                                <c:when test="${empty f.user_profile}">
                                    	<img src="resources/img/default_profile.png" alt="프로필 이미지" class="profile-img">
                                </c:when>
                                <c:otherwise>
                                    <img src="resources/img/${f.user_profile}" alt="프로필 이미지" class="profile-img">
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </c:forEach>
                    	<span class="author-nickname">${f.user_nick}</span>
                </div>
                <h3>${c.cw_title}</h3>
                <p>0/${c.cw_limit}</p> <!-- 현재 인원 수는 추후 개선 -->
                 <form method="post" action="find_detail">
                 	<input type="hidden" name="sel" value="${c.cw_idx}">
                	<button  class="join-button">참여하기</button>
            	</form>
            </div>
        </c:forEach>
    </div>
        <!-- 참여방 만들기 버튼 -->
        <div class="create-room-section">
            <form method="get" action="find_write">
                <button id="create-room-button">참여방 만들기</button>
            </form>
        </div>
    </div>
    <script src="resources/css/find.js"></script>
</body>
</html>





