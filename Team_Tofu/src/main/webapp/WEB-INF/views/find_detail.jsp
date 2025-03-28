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
		href="resources/css/find_detail.css">
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
	<!-- 상단바 (main.html과 동일한 구조) -->
	<div id="top-bar">
		<div id="left-section">
			<span id="tofu-main">To.fu</span> <span id="find-main"><a
				href="#">FIND</a></span>
		</div>
		<input class="search-txt" type="text" placeholder="검색어를 입력해주세요">
		<button class="search-btn">
			<i class="fas fa-search"></i>
		</button>

		<img
			src="/Team_Tofu/src/main/webapp/resources/images/default_profile.png"
			alt="프로필 이미지" id="profile-bar">
	</div>

	<div class="container">
		<header>
			<h1>참여방 이름</h1>
		</header>

		<main>
			<section class="menu-section">
				<div class="menu-item">
					<h2>TOFU</h2>
					<ul>
						<li class="profile-section">
							<div class="profile-pic">
								<img src="https://via.placeholder.com/50" alt="프로필 사진">
							</div>
							<div class="profile-info">
								<span class="profile-name">사용자 이름</span> <span
									class="profile-status">온라인</span>
							</div>
						</li>
						<li><i class="fas fa-comment-alt"></i> 메세지 보내기</li>
						<li><i class="fas fa-share-alt"></i> 공유하기</li>
					</ul>
				</div>

				<div class="menu-item">
					<h2>TO.FU</h2>
					<ul>
						<li class="profile-section">
							<div class="profile-pic">
								<img src="https://via.placeholder.com/50" alt="프로필 사진">
							</div>
							<div class="profile-info">
								<span class="profile-name">사용자 이름</span> <span
									class="profile-status">온라인</span>
							</div>
						</li>
						<li><i class="fas fa-comment-alt"></i> 메세지 보내기</li>
						<li><i class="fas fa-share-alt"></i> 공유하기</li>
					</ul>
				</div>
			</section>

			<hr class="divider">

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
					<div class="calendar-container">
						<div class="calendar-header">
							<button id="prev-month">
								<i class="fas fa-chevron-left"></i>
							</button>
							<h4 id="current-month">2023년 11월</h4>
							<button id="next-month">
								<i class="fas fa-chevron-right"></i>
							</button>
						</div>
						<div class="calendar-grid" id="calendar">
							<!-- 달력 내용이 여기에 동적으로 생성됩니다 -->
						</div>
					</div>
					<div class="schedule-form">
						<input type="text" id="schedule-title" placeholder="일정 제목">
						<input type="date" id="schedule-date">
						<button id="add-schedule">추가</button>
					</div>
					<div class="schedule-list" id="schedule-list">
						<!-- 일정 목록이 여기에 동적으로 생성됩니다 -->
					</div>
				</div>
			</section>
		</main>
	</div>
	<script src="resources/js/find_detail.js"></script>
</body>
</html>