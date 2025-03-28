document.addEventListener('DOMContentLoaded', function() {
    // 탭 전환 기능
    const tabs = document.querySelectorAll('.tab');
    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            tabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');
            
            document.querySelectorAll('.tab-content').forEach(content => {
                content.classList.remove('active');
            });
            
            const tabId = tab.getAttribute('data-tab');
            document.getElementById(tabId).classList.add('active');
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
    
    // 캘린더 생성 (일정 섹션으로 이동)
    let currentDate = new Date();
    renderCalendar(currentDate);
    
    // 이전 달 버튼
    document.getElementById('prev-month').addEventListener('click', () => {
        currentDate.setMonth(currentDate.getMonth() - 1);
        renderCalendar(currentDate);
    });
    
    // 다음 달 버튼
    document.getElementById('next-month').addEventListener('click', () => {
        currentDate.setMonth(currentDate.getMonth() + 1);
        renderCalendar(currentDate);
    });
    
    // 일정 추가 기능
    document.getElementById('add-schedule').addEventListener('click', addSchedule);
    
    // 초기 일정 로드
    loadSchedules();
    
    // 프로필 데이터 로드
    // loadProfile();
    
    // 프로필 클릭 이벤트
    document.querySelectorAll('.profile-section').forEach(profile => {
        profile.addEventListener('click', function() {
            openProfileModal();
        });
    });
});

// 캘린더 렌더링 함수
function renderCalendar(date) {
    const calendarEl = document.getElementById('calendar');
    const monthYearEl = document.getElementById('current-month');
    
    // 현재 월/년도 표시
    const monthNames = ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'];
    monthYearEl.textContent = `${date.getFullYear()}년 ${monthNames[date.getMonth()]}`;
    
    // 첫 날과 마지막 날 구하기
    const firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
    const lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
    
    // 이전 달의 마지막 날들
    const prevLastDay = new Date(date.getFullYear(), date.getMonth(), 0).getDate();
    
    // 요일 인덱스 (0: 일요일, 6: 토요일)
    const firstDayIndex = firstDay.getDay();
    const lastDayIndex = lastDay.getDay();
    
    // 다음 달의 날들
    const nextDays = 6 - lastDayIndex;
    
    let days = '';
    
    // 요일 헤더 추가
    const dayNames = ['일', '월', '화', '수', '목', '금', '토'];
    for (let i = 0; i < 7; i++) {
        days += `<div class="calendar-day-header">${dayNames[i]}</div>`;
    }
    
    // 이전 달 날짜
    for (let i = firstDayIndex; i > 0; i--) {
        days += `<div class="calendar-day other-month">${prevLastDay - i + 1}</div>`;
    }
    
    // 현재 달 날짜
    const today = new Date();
    for (let i = 1; i <= lastDay.getDate(); i++) {
        const isToday = i === today.getDate() && 
                        date.getMonth() === today.getMonth() && 
                        date.getFullYear() === today.getFullYear();
        days += `<div class="calendar-day ${isToday ? 'today' : ''}">${i}</div>`;
    }
    
    // 다음 달 날짜
    for (let i = 1; i <= nextDays; i++) {
        days += `<div class="calendar-day other-month">${i}</div>`;
    }
    
    calendarEl.innerHTML = days;
}

// 일정 추가 함수
function addSchedule() {
    const titleInput = document.getElementById('schedule-title');
    const dateInput = document.getElementById('schedule-date');
    
    if (!titleInput.value || !dateInput.value) {
        alert('일정 제목과 날짜를 입력해주세요.');
        return;
    }
    
    const schedule = {
        id: Date.now(),
        title: titleInput.value,
        date: dateInput.value
    };
    
    // 로컬 스토리지에 저장
    let schedules = JSON.parse(localStorage.getItem('schedules')) || [];
    schedules.push(schedule);
    localStorage.setItem('schedules', JSON.stringify(schedules));
    
    // 입력 필드 초기화
    titleInput.value = '';
    dateInput.value = '';
    
    // 일정 목록 새로고침
    loadSchedules();
}

// 일정 목록 로드 함수
function loadSchedules() {
    const scheduleListEl = document.getElementById('schedule-list');
    const schedules = JSON.parse(localStorage.getItem('schedules')) || [];
    
    scheduleListEl.innerHTML = '';
    
    if (schedules.length === 0) {
        scheduleListEl.innerHTML = '<p>등록된 일정이 없습니다.</p>';
        return;
    }
    
    // 날짜 순으로 정렬
    schedules.sort((a, b) => new Date(a.date) - new Date(b.date));
    
    schedules.forEach(schedule => {
        const scheduleItem = document.createElement('div');
        scheduleItem.className = 'schedule-item';
        scheduleItem.innerHTML = `
            <div class="schedule-info">
                <span>${schedule.title}</span>
                <span class="schedule-date">${formatDate(schedule.date)}</span>
            </div>
            <i class="fas fa-trash delete-schedule" data-id="${schedule.id}"></i>
        `;
        scheduleListEl.appendChild(scheduleItem);
    });
    
    // 일정 삭제 이벤트 추가
    document.querySelectorAll('.delete-schedule').forEach(btn => {
        btn.addEventListener('click', function() {
            const id = parseInt(this.getAttribute('data-id'));
            deleteSchedule(id);
        });
    });
}

// 일정 삭제 함수
function deleteSchedule(id) {
    let schedules = JSON.parse(localStorage.getItem('schedules')) || [];
    schedules = schedules.filter(schedule => schedule.id !== id);
    localStorage.setItem('schedules', JSON.stringify(schedules));
    loadSchedules();
}

// 날짜 포맷팅 함수
function formatDate(dateString) {
    const date = new Date(dateString);
    return `${date.getFullYear()}년 ${date.getMonth() + 1}월 ${date.getDate()}일`;
}
