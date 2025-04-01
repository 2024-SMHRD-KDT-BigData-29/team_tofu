<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>To.fu</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="resources/css/main.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

</head>
<body>
	<!-- 고정된 상단 바 -->
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
					<img src="resources/img/default_profile.png" alt="기본 프로필 이미지"
						class="profile-img">
				</c:when>
				<c:otherwise>
					<img src="resources/img/${login_user.user_profile}" alt="프로필 이미지"
						class="profile-img">
				</c:otherwise>
			</c:choose>
		</button>
	</div>

	<!-- 스토리 섹션 -->
	<div id="story-section">
		<c:forEach var="i" begin="1" end="5">
			<div class="story">
				<img src="resources/images/story${i}.jpg" alt="프로필${i}"> <span
					class="story-nickname">닉네임${i}</span>
			</div>
		</c:forEach>
	</div>

	<!-- 피드 섹션 -->
	<div id="feed-section">
		<!-- 태그 필터 섹션 -->
		<div class="tag-filter">
			<label class="tag-label"> <input type="checkbox" name="tag"
				value="그림"> <span>#그림</span>
			</label> <label class="tag-label"> <input type="checkbox" name="tag"
				value="영상"> <span>#영상</span>
			</label> <label class="tag-label"> <input type="checkbox" name="tag"
				value="음악"> <span>#음악</span>
			</label> <label class="tag-label"> <input type="checkbox" name="tag"
				value="편집"> <span>#편집</span>
			</label>
		</div>

		<!-- 게시글 목록 -->
		<c:forEach var="feed" items="${feedList}">
			<div class="post">
				<!-- 포스트 헤더 -->
				<div class="post-header">
					<div class="user-profile">
						<img class="post-image user-click-target"
							src="resources/img/${not empty feed.user_profile ? feed.user_profile : 'default_profile.png'}"
							data-user-id="${feed.user_id}" data-user-nick="${feed.user_nick}"
							alt="프로필 이미지"> <span class="nickname user-click-target"
							data-user-id="${feed.user_id}" data-user-nick="${feed.user_nick}">
							${feed.user_nick} </span>
					</div>
					<script>
    				const contextPath = '${pageContext.request.contextPath}';
				</script>
					<c:if test="${login_user.user_id == feed.user_id}">
						<div class="post-menu-container">
							<button class="post-menu-btn">
								<i class="fas fa-ellipsis-h"></i>
							</button>
							<div class="post-menu-dropdown">
								<button class="menu-option" name="editPost"
									onclick="editPost(${feed.feed_idx})">
									<i class="far fa-edit"></i> 수정하기
								</button>
								<button class="menu-option" name="deletePost"
									onclick="deletePost(${feed.feed_idx})">
									<i class="far fa-trash-alt"></i> 삭제하기
								</button>
							</div>
						</div>
					</c:if>
				</div>

				<!-- 포스트 이미지 -->
				<!-- 포스트 내용 -->
				<div class="post-content">
					<div class="post-title">${feed.feed_title}</div>
					<div class="post-text">${feed.feed_content}</div>
				</div>
				<div class="post-image-container">
					<c:if test="${not empty feed.feed_file}">
						<img src="resources/feed_img/${feed.feed_file}" alt="피드 이미지">
					</c:if>
				</div>


				<!-- 포스트 액션 버튼 -->
				<div class="post-actions">
					<div class="actions-left">
						<button class="like-btn" data-feed-id="${feed.feed_idx}">
							<i class="far fa-heart"></i>
						</button>
						<button class="comment-btn">
							<i class="far fa-comment"></i>
						</button>
					</div>
					<div class="like-count"></div>
				</div>

				<!-- 댓글 목록 -->
				<div class="comment-list">
					<c:forEach var="cmt" items="${cmtList}">
						<c:if test="${cmt.feed_idx == feed.feed_idx}">
							<div class="comment-item">
								<span class="comment-nickname">${cmt.user_nick}</span> <span
									class="comment-text">${cmt.cmt_content}</span>
							</div>
						</c:if>
					</c:forEach>
				</div>

				<!-- 댓글 작성 폼 -->
				<form class="comment-section" method="post" action="comment_send">
					<input type="hidden" name="feed_idx" value="${feed.feed_idx}">
					<textarea name="cmt_content" class="comment-input"
						placeholder="댓글 달기..." required></textarea>
					<button type="submit" class="comment-submit">게시</button>
				</form>
			</div>
		</c:forEach>
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
          const user_id = "${login_user.user_id}";
   </script>
	<script src="resources/js/main.js"></script>
</body>
</html>


