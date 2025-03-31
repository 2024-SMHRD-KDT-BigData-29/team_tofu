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

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'ko'
    });
    
    // Google API 로드
    gapi.load('client:auth2', function() {
        initClient().then(function() {
            calendar.refetchEvents(); // 캘린더 이벤트 다시 불러오기
        });
    });

    calendar.render();
});

function initClient() {
    return gapi.client.init({
        apiKey: 'AIzaSyBvxYY91uGYw85Is0qKzpg6IN-ge4r28TA',
        clientId: '91cc23a4a3e6f9692df23a9dd3150c3e5185fa2ee8ecc2069ac7b5bd1fb3ecef@group.calendar.google.com',
        discoveryDocs: ["https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest"],
        scope: "https://www.googleapis.com/auth/calendar.readonly"
    }).then(function() {
        var GoogleAuth = gapi.auth2.getAuthInstance();
        if (!GoogleAuth.isSignedIn.get()) {
            return GoogleAuth.signIn();
        }
        return Promise.resolve();
    });
}

function loadGoogleCalendarEvents(fetchInfo, successCallback, failureCallback) {
    if (!gapi.client.calendar) {
        console.error("Google Calendar API가 로드되지 않았습니다.");
        return failureCallback(new Error("Google Calendar API not loaded"));
    }

    gapi.client.calendar.events.list({
        'calendarId': 'primary',
        'timeMin': fetchInfo.start.toISOString(),
        'timeMax': fetchInfo.end.toISOString(),
        'showDeleted': false,
        'singleEvents': true,
        'orderBy': 'startTime'
    }).then(function(response) {
        var events = response.result.items.map(function(event) {
            return {
                id: event.id,
                title: event.summary,
                start: event.start.dateTime || event.start.date,
                end: event.end.dateTime || event.end.date
            };
        });
        successCallback(events);
    }).catch(failureCallback);
}