<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>TOFU FIND</title>
	<link rel="stylesheet"
		href="resources/css/find_detail.css">
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<!-- FullCalendar CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.css">

<!-- FullCalendar JS -->
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/locales/ko.min.js"></script>
<script src="https://apis.google.com/js/api.js"></script>
</head>
<body>
    <c:set var="selectedIdx" value="${param.sel}"/>
    
    <!-- 상단바 -->
    <div id="top-bar">
        <div id="left-section">
            <span id="tofu-main"><a href="main.do">To.fu</a></span>
            <span id="find-main"><a href="find.do">FIND</a></span>
        </div>
        <form action="#" method="get">
            <input class="search-txt" type="text" placeholder="검색어를 입력해주세요">
            <button class="search-btn">
                <i class="fas fa-search"></i>
            </button>
        </form>
        <%-- <button id="profile-btn" type="button">
            <c:choose>
                <c:when test="${empty login_user.user_profile}">
                    <img src="resources/img/default_profile.png" alt="기본 프로필 이미지" class="profile-img">
                </c:when>
                <c:otherwise>
                    <img src="resources/img/${login_user.user_profile}" alt="프로필 이미지" class="profile-img">
                </c:otherwise>
            </c:choose>
        </button> --%>
    </div>
    
   <%-- <c:forEach var="i" items="${insert_cowork}">
        <c:if test="${i.cw_idx == selectedIdx}"> --%>
            <div class="container">
                <h1>${insert_cowork.cw_title}</h1>
                <div class="menu-item">
                    <h2>${insert_cowork.cw_intro}</h2>
                    <%-- <div class="profile-pic">
                        <c:choose>
                            <c:when test="${insert_cowork.user_id == login_user.user_id}">
                                <c:choose>
                                    <c:when test="${empty login_user.user_profile}">
                                        <img src="resources/img/default_profile.png" alt="기본 프로필 이미지" class="profile-img">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="resources/img/${login_user.user_profile}" alt="프로필 이미지" class="profile-img">
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                        </c:choose>
                    </div> --%>
                    <div class="profile-info">
                        <div class="profile-status">${insert_cowork.cw_content}</div>
                        <div class="profile-status">${insert_cowork.cw_img}</div>
                        <div class="profile-status">참여가능 인원 : ${insert_cowork.cw_limit}명</div>
                        <div class="profile-status">${insert_cowork.hash_tag}</div>
                    </div>
                    <form action="group_chat">
                    	<input type="hidden">
                    	<button name=group_chat>
						<i class="fas fa-comment-alt">그룹채팅</i> 
                    	</button>
                    </form>
                    <!-- <i class="fas fa-share-alt"></i> 공유하기 -->
                </div>
            </div>
    <%--     </c:if>
    </c:forEach> --%>
    
    <hr class="divider">

    <!-- 탭 섹션 -->
    <section class="content-section">
        <div class="tab-container">
            <div class="tab active" data-tab="share">공유 자료실</div>
            <div class="tab" data-tab="notice">공지사항</div>
            <div class="tab" data-tab="schedule">일정</div>
        </div>
        
        <div class="tab-content active" id="share">
            <h3>공유 자료실</h3>
            <div class="file-list">
                <div class="file-item"><i class="fas fa-file-alt"></i> <span>공유 문서 1</span> <span class="file-date">2023-11-15</span></div>
                <div class="file-item"><i class="fas fa-file-image"></i> <span>이미지 자료</span> <span class="file-date">2023-11-10</span></div>
                <div class="file-item"><i class="fas fa-file-pdf"></i> <span>PDF 문서</span> <span class="file-date">2023-11-05</span></div>
            </div>
        </div>
        
        <div class="tab-content" id="notice">
            <h3>공지사항</h3>
            <div class="notice-list">
                <div class="notice-item"><h4>시스템 점검 안내</h4><p>2023년 11월 20일 00:00 ~ 06:00 시스템 점검이 진행됩니다.</p><span class="notice-date">2023-11-15</span></div>
                <div class="notice-item"><h4>새로운 기능 업데이트</h4><p>공유 자료실에 캘린더 기능이 추가되었습니다.</p><span class="notice-date">2023-11-10</span></div>
            </div>
        </div>

<<<<<<< HEAD
			<section class="content-section">
				<div class="tab-container">
					<div class="tab active" data-tab="share">공유 자료실</div>
					<div class="tab" data-tab="notice">공지사항</div>
					<div class="tab" data-tab="schedule">일정</div>
				</div>

				<div class="tab-content active" id="share">
					<h3>공유 자료실</h3>
					<div class="file-list">
						<div class="file-item">
							<i class="fas fa-file-alt"></i> <span>공유 문서 1</span> <span
								class="file-date">2023-11-15</span>
						</div>
						<div class="file-item">
							<i class="fas fa-file-image"></i> <span>이미지 자료</span> <span
								class="file-date">2023-11-10</span>
						</div>
						<div class="file-item">
							<i class="fas fa-file-pdf"></i> <span>PDF 문서</span> <span
								class="file-date">2023-11-05</span>
						</div>
					</div>
				</div>
		
				<div class="tab-content" id="notice">
					<h3>공지사항</h3>
					<div class="notice-list">
						<div class="notice-item">
							<h4>시스템 점검 안내</h4>
							<p>2023년 11월 20일 00:00 ~ 06:00 시스템 점검이 진행됩니다.</p>
							<span class="notice-date">2023-11-15</span>
						</div>
						<div class="notice-item">
							<h4>새로운 기능 업데이트</h4>
							<p>공유 자료실에 캘린더 기능이 추가되었습니다.</p>
							<span class="notice-date">2023-11-10</span>
						</div>
					</div>
				</div>

				<div class="tab-content" id="schedule">
					<h3>일정</h3>
					<div id ="calendar"></div>
				</div>
	
	<!-- 상단 이동 버튼 및 게시물 작성 버튼 -->
   <div class="floating-buttons">
      <form method="get" action="write">
         <button id="write-post">✏️</button>
      </form>
      <button id="scroll-top">&#9650;</button>
   </div>
	<!-- 팝업오버레이  -->
   <div id="popup-overlay"></div>
   <script>
          const user_profile = "${login_user.user_profile}"
           const user_nick = "${login_user.user_nick}"
           const user_intro = "${login_user.user_intro}";
   </script>
	<script src="resources/js/find_detail.js"></script>
</body>
</html>
