document.getElementById("scroll-top").addEventListener("click", function() {
	window.scrollTo({ top: 0, behavior: "smooth" });
});
document.querySelectorAll(".tag").forEach(tag => {
	tag.addEventListener("click", function() {
		let selectedTag = this.getAttribute("data-tag");
		alert(selectedTag + " 태그를 선택했습니다!");

		// 여기서 태그에 맞는 게시글만 보이도록 필터링 가능
	});
});


// 기존 댓글 삭제 기능
document.querySelectorAll('.comment-delete').forEach(button => {
	button.addEventListener('click', function() {
		const commentItem = this.parentElement; // 댓글 항목
		commentItem.remove(); // 댓글 삭제
	});
});

// 스토리 프로필 클릭 이벤트 추가
document.querySelectorAll('.story').forEach(story => {
	story.addEventListener('click', function() {
		const nickname = this.querySelector('.story-nickname').textContent;
		alert(nickname + "의 프로필로 이동합니다!"); // 실제로는 해당 프로필 페이지로 이동
		// 예: window.location.href = `/profile/${nickname}`;
	});
});

// 프로필 팝업 기능 (로그아웃 버튼 이벤트 제거 버전)
document.getElementById('profile-btn').addEventListener('click', function(e) {
	e.stopPropagation(); // 이벤트 버블링 방지
	const overlay = document.getElementById('popup-overlay');
	overlay.style.display = 'flex';
	const profileImage = user_profile ?
		`resources/img/${user_profile}` :
		`resources/img/default_profile.png`;



	overlay.innerHTML = `
        <div class="profile-container">
            <div class="profile-header">
                <h2>프로필</h2>
            </div>
            <div class="profile-content">
                <div class="profile-image">
                    <img src=${profileImage} alt="프로필 이미지">
                </div>
                <div class="profile-info">
                    <h3 name="user_nick">${user_nick}</h3>
                    <p>${user_intro}</p>
                    <div class="profile-actions">
                        <button id="view-posts-button" onclick="location.href='mypost'">게시글 보기</button>
                        <button id="view-profile-button" onclick="location.href='mypage'">프로필 보기</button>
                        <button id="logout" onclick="location.href='logout'">로그아웃</button>
                    </div>
                </div>
            </div>
            <div class="profile-stats">
                <div class="stat">
                    <span class="stat-number">120</span>
                    <span class="stat-label">게시글</span>
                </div>
            </div>

        </div>
    `;

	// 로그아웃 버튼 이벤트는 여기서 제거됨
});

// 오버레이 클릭 시 팝업 닫기
document.getElementById('popup-overlay').addEventListener('click', function(e) {
	if (e.target === this) {
		this.style.display = 'none';
	}
});

// 게시글 메뉴 기능
document.addEventListener('DOMContentLoaded', function() {
	// 메뉴 버튼 클릭 이벤트
	document.addEventListener('click', function(e) {
		const menuBtn = e.target.closest('.post-menu-btn');

		// 메뉴 버튼 클릭 시
		if (menuBtn) {
			e.stopPropagation();
			const dropdown = menuBtn.nextElementSibling;
			const isShowing = dropdown.classList.contains('show');

			// 모든 메뉴 닫기
			document.querySelectorAll('.post-menu-dropdown').forEach(d => {
				d.classList.remove('show');
			});

			// 현재 메뉴 열기
			if (!isShowing) {
				dropdown.classList.add('show');
			}
		}
		// 다른 곳 클릭 시 모든 메뉴 닫기
		else {
			document.querySelectorAll('.post-menu-dropdown').forEach(d => {
				d.classList.remove('show');
			});
		}
	});
});

// 메뉴 옵션 클릭 시 이벤트 전파 방지
document.querySelectorAll('.menu-option').forEach(option => {
	option.addEventListener('click', function(e) {
		e.stopPropagation();
	});
});

// 수정 기능
function editPost(feed_idx) {
    if (!feed_idx || isNaN(feed_idx)) {
        console.error('유효하지 않은 feed_idx:', feed_idx);
        alert('게시글 ID가 유효하지 않습니다.');
        return;
    }
    console.log('수정할 feed_idx:', feed_idx);
    window.location.href = contextPath + '/editPost/' + feed_idx;
}

// 삭제 기능
function deletePost(feed_idx) {
    if (!feed_idx || isNaN(feed_idx)) {
        console.error('유효하지 않은 feed_idx:', feed_idx);
        alert('게시글 ID가 유효하지 않습니다.');
        return;
    }

    if (confirm('정말로 이 게시글을 삭제하시겠습니까?')) {
        console.log('삭제할 feed_idx:', feed_idx);
        fetch(contextPath + '/deletePost/' + feed_idx, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
        })
        .then(response => {
            if (response.ok) {
                console.log('게시물 삭제 성공');
                window.location.href = contextPath + '/main'; // 삭제 후 리다이렉트
            } else {
                console.error('게시물 삭제 실패');
                alert('게시물 삭제에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('삭제 요청 중 오류:', error);
            alert('삭제 중 오류가 발생했습니다.');
        });
    }
}

// 좋아요 버튼 클릭 시
likeBtn.addEventListener('click', function() {
	const icon = this.querySelector('i');
	icon.classList.toggle('far'); // 빈 하트
	icon.classList.toggle('fas'); // 채워진 하트
	this.classList.toggle('liked');

	// 카운트 업데이트
	const countElement = this.querySelector('.like-count');
	const currentCount = parseInt(countElement.textContent);
	countElement.textContent = this.classList.contains('liked') ? currentCount + 1 : currentCount - 1;
});