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