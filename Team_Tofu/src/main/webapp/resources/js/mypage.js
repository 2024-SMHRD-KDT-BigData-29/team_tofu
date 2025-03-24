// 뒤로가기 버튼 기능
document.getElementById('back-button').addEventListener('click', function() {
    alert('뒤로가기 버튼이 클릭되었습니다.'); // 실제로는 페이지 이동 또는 다른 동작을 구현
});

// 프로필 수정 버튼 기능
document.getElementById('edit-profile-button').addEventListener('click', function() {
    alert('프로필 수정 페이지로 이동합니다.'); // 실제로는 프로필 수정 페이지로 이동
});

// 추가하기 버튼 기능
document.querySelectorAll('.add-button').forEach(button => {
    button.addEventListener('click', function() {
        const sectionTitle = this.parentElement.querySelector('h3').textContent;
        alert(sectionTitle + "에 추가하기 버튼이 클릭되었습니다!"); // 실제로는 추가 로직을 구현
    });
});

// FIND 페이지에서 활동 중인 내용을 가져와 출력
const ongoingActivities = [
    { title: "함께 그림 작업 하실 분", description: "2/7" },
    { title: "함께 밴드 하실 분", description: "2/5" },
    { title: "공모전 함께 참가하실 분", description: "2/3" }
];

const ongoingActivitiesContainer = document.getElementById('ongoing-activities');

if (ongoingActivities.length > 0) {
    ongoingActivities.forEach(activity => {
        const activityItem = document.createElement('div');
        activityItem.className = 'activity-item';
        activityItem.innerHTML = `
            <h4>${activity.title}</h4>
            <p>${activity.description}</p>
        `;
        ongoingActivitiesContainer.appendChild(activityItem);
    });
} else {
    ongoingActivitiesContainer.innerHTML = "<p>참여 중인 활동이 없습니다.</p>";
}