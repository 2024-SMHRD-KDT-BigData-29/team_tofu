<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TOFU FIND</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/find_detail.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.15/index.global.min.js'></script>
<script
	src='https://cdn.jsdelivr.net/npm/@fullcalendar/google-calendar@6.1.15/index.global.min.js'></script>
<script>
	window.context_path = "${pageContext.request.contextPath}";
	window.current_cw_idx = "${cw_idx}" || 1; // 기본값 설정
	window.user_profile = "${login_user.user_profile}";
	window.user_nick = "${login_user.user_nick}";
	window.user_intro = "${login_user.user_intro}";
</script>
</head>
<body>
	<c:set var="selectedIdx" value="${param.sel}" />

	<!-- 고정 상단바 -->
	<div id="top-bar">
		<div id="left-section">
			<span id="tofu-main"><a href="main.do">To.fu</a></span> <span
				id="find-main"><a href="find.do">FIND</a></span>
		</div>
		<form action="#" method="get">
			<input class="search-txt" type="text" placeholder="검색어를 입력해주세요">
			<button class="search-btn">
				<i class="fas fa-search"></i>
			</button>
		</form>
		<button id="profile-btn" type="button">
			<c:choose>
				<c:when test="${empty login_user.user_profile}">
					<img
						src="${pageContext.request.contextPath}/resources/img/default_profile.png"
						alt="기본 프로필 이미지" class="profile-img">
				</c:when>
				<c:otherwise>
					<img
						src="${pageContext.request.contextPath}/resources/img/${login_user.user_profile}"
						alt="프로필 이미지" class="profile-img">
				</c:otherwise>
			</c:choose>
		</button>
	</div>
	<div class="container">
		<h1>${insert_cowork.cw_title}</h1>
		<div class="menu-item">
			<h2>${insert_cowork.cw_intro}</h2>
			<div class="profile-info">
				<div class="profile-content">${insert_cowork.cw_content}</div>
				<div class="profile-image">${insert_cowork.cw_img}</div>
				<div class="profile-status">참여가능 인원 :
					${insert_cowork.cw_limit}명</div>
				<div class="profile-hash">${insert_cowork.hash_tag}</div>
			</div>
			<form action="group_chat">
				<input type="hidden">
				<button name="group_chat">
					<i class="fas fa-comment-alt">그룹채팅</i>
				</button>
			</form>
		</div>
	</div>
	<hr class="divider">
	<section class="content-section">
		<div class="tab-container">
			<div class="tab active" data-tab="notice">공지사항</div>
			<div class="tab" data-tab="schedule">일정</div>
		</div>
		<div class="tab-content active" id="notice">
			<h3>공지사항</h3>
			<div class="notice-list">
				<div class="notice-item">
					<h4>시스템 점검 안내</h4>
					<p>2024년 11월 20일 00:00 ~ 06:00 시스템 점검이 진행됩니다.</p>
					<span class="notice-date">2023-11-15</span>
				</div>
				<div class="notice-item">
					<h4>새로운 기능 업데이트</h4>
					<p>공유 자료실에 캘린더 기능이 추가되었습니다.</p>
					<span class="notice-date">2024-11-10</span>
				</div>
			</div>
		</div>
		<div class="tab-content" id="schedule">
			<div id="calendar"></div>
		</div>
		<div id="event-modal" class="modal" style="display: none;"></div>
		<div id="add-modal" class="modal" style="display: none;"></div>
		<div id="eventModal" class="modal" style="display: none;">
			<div class="modal-content">
				<h2>일정 관리</h2>
				<input type="hidden" id="eventId">
				<div class="form-group">
					<label>제목</label> <input type="text" id="eventTitle"
						class="form-control">
				</div>
				<div class="form-group">
					<label>시작</label> <input type="datetime-local" id="eventStart"
						class="form-control">
				</div>
				<div class="form-group">
					<label>종료</label> <input type="datetime-local" id="eventEnd"
						class="form-control">
				</div>
				<div class="form-group">
					<label>색상</label> <input type="color" id="eventColor"
						class="form-control" value="#3788d8">
				</div>
				<div class="form-group">
					<label>내용</label>
					<textarea id="eventContent" class="form-control"></textarea>
				</div>
				<button id="saveEvent" class="btn btn-primary">저장</button>
				<button id="deleteEvent" class="btn btn-danger">삭제</button>
				<button id="closeModal" class="btn btn-secondary">닫기</button>
			</div>
		</div>
		<dialog id="popup-dialog">
		<div class="popup-content"></div>
		<button class="dialog-button"
			onclick="document.getElementById('popup-dialog').removeAttribute('open')">닫기</button>
		</dialog>
	</section>

	<!-- 상단 이동 버튼 및 게시물 작성 버튼 -->
	<div class="floating-buttons">
		<form method="get" action="write">
			<button id="write-post">✏️</button>
		</form>
		<button id="scroll-top">▲</button>
	</div>
	<!-- 팝업 오버레이 -->
	<div id="popup-overlay"></div>
	<script
		src="${pageContext.request.contextPath}/resources/js/find_detail.js"></script>
</body>
</html>