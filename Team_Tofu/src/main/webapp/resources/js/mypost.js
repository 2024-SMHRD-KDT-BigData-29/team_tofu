// 뒤로가기 버튼 기능
document.getElementById('back-button').addEventListener('click', function() {
    alert('뒤로가기 버튼이 클릭되었습니다.'); // 실제로는 페이지 이동 또는 다른 동작을 구현
});

// 게시글 수정 버튼 기능
document.getElementById('edit-post-button').addEventListener('click', function() {
    alert('게시글 수정 페이지로 이동합니다.'); // 실제로는 게시글 수정 페이지로 이동
});

// 게시글 삭제 버튼 기능
document.getElementById('delete-post-button').addEventListener('click', function() {
    if (confirm('정말로 게시글을 삭제하시겠습니까?')) {
        alert('게시글이 삭제되었습니다.'); // 실제로는 게시글 삭제 로직을 구현
    }
});

// 게시글 작성 기능
document.getElementById('submit-post-button').addEventListener('click', function() {
    const postInput = document.getElementById('post-input');
    const postText = postInput.value.trim();

    if (postText) {
        const postBody = document.querySelector('.post-body');
        const newPost = document.createElement('p');
        newPost.textContent = postText;
        postBody.appendChild(newPost);
        postInput.value = ''; // 입력란 초기화
    } else {
        alert('게시글을 입력해주세요!');
    }
});