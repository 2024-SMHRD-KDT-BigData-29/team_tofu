document.getElementById("scroll-top").addEventListener("click", function () {
    window.scrollTo({ top: 0, behavior: "smooth" });
});
document.querySelectorAll(".tag").forEach(tag => {
    tag.addEventListener("click", function () {
        let selectedTag = this.getAttribute("data-tag");
        alert(selectedTag + " 태그를 선택했습니다!");
        
        // 여기서 태그에 맞는 게시글만 보이도록 필터링 가능
    });
});

// 댓글 게시 기능
document.querySelectorAll('.comment-submit').forEach(button => {
    button.addEventListener('click', function() {
        const commentInput = this.previousElementSibling; // 댓글 입력란
        const commentText = commentInput.value.trim();

        if (commentText) {
            const commentList = this.parentElement.previousElementSibling; // 댓글 목록
            const commentItem = document.createElement('div');
            commentItem.className = 'comment-item';
            commentItem.innerHTML = `
                <span class="comment-nickname">닉네임:</span>
                <span class="comment-text">${commentText}</span>
                <button class="comment-delete">🗑️</button>
            `;
            commentList.appendChild(commentItem); // 댓글 목록에 추가
            commentInput.value = ''; // 입력란 초기화

            // 삭제 버튼에 이벤트 리스너 추가
            const deleteButton = commentItem.querySelector('.comment-delete');
            deleteButton.addEventListener('click', function() {
                commentItem.remove(); // 댓글 삭제
            });
        }
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
        
    
    overlay.innerHTML = `
        <div class="profile-container">
            <div class="profile-header">
                <h2>프로필</h2>
            </div>
            <div class="profile-content">
                <div class="profile-image">
                    <img src=${user_profile} alt="프로필 이미지">
                </div>
                <div class="profile-info">
                    <h3 name="user_nick">${user_nick}</h3>
                    <p>${user_intro}</p>
                    <div class="profile-actions">
                        <button id="view-posts-button" onclick="location.href='mypost'">게시글 보기</button>
                        <button id="view-profile-button" onclick="location.href='mypage'">프로필 보기</button>
                        <button id="logout">로그아웃</button>
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