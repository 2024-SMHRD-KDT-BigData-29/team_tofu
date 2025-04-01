document.addEventListener('DOMContentLoaded', function() {
    const context_path = window.context_path; // JSP에서 설정된 값 사용
    const current_cw_idx = window.current_cw_idx || 1; // 기본값 설정
    const user_profile = window.user_profile || "";
    const user_nick = window.user_nick || "";
    const user_intro = window.user_intro || "";
    let calendar;

    // 날짜 형식을 MySQL TIMESTAMP에 맞게 변환
    const formatDateForMySQL = (dateString) => {
        const date = new Date(dateString);
        return date.toISOString().slice(0, 19).replace('T', ' ');
    };

    // 탭 전환 기능
    document.querySelectorAll('.tab').forEach(tab => {
        tab.addEventListener('click', () => {
            document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
            tab.classList.add('active');
            document.querySelectorAll('.tab-content').forEach(content => content.classList.remove('active'));
            const tab_id = tab.getAttribute('data-tab');
            document.getElementById(tab_id).classList.add('active');
            if (tab_id === 'schedule' && calendar) {
                setTimeout(() => calendar.updateSize(), 100);
            }
        });
    });

    // 프로필 팝업 기능 (기존 코드 유지)
    document.getElementById('profile-btn')?.addEventListener('click', function(e) {
        e.stopPropagation();
        const overlay = document.getElementById('popup-overlay');
        overlay.style.display = 'flex';
        const profile_image = user_profile ? `resources/img/${user_profile}` : `resources/img/default_profile.png`;
        overlay.innerHTML = `
            <div class="profile-container">
                <div class="profile-header"><h2>프로필</h2></div>
                <div class="profile-content">
                    <div class="profile-image"><img src="${profile_image}" alt="프로필 이미지"></div>
                    <div class="profile-info">
                        <h3 name="user_nick">${user_nick}</h3>
                        <p>${user_intro}</p>
                        <div class="profile-actions">
                            <button onclick="location.href='mypost'">게시글 보기</button>
                            <button onclick="location.href='mypage'">프로필 보기</button>
                            <button onclick="location.href='logout'">로그아웃</button>
                        </div>
                    </div>
                </div>
            </div>
        `;
    });

    document.getElementById('popup-overlay')?.addEventListener('click', function(e) {
        if (e.target === this) this.style.display = 'none';
    });

    // 일정 추가 모달 열기 함수
    function open_add_event_modal() {
        // 기존 모달 제거
        const existingModal = document.querySelector('.event-modal');
        if (existingModal) existingModal.remove();

        // 새 모달 생성
        const modal = document.createElement('div');
        modal.className = 'event-modal';
        modal.innerHTML = `
            <div class="modal-content">
                <h2>일정 추가</h2>
                <form id="add-event-form">
                    <div class="form-group">
                        <label>제목</label>
                        <input type="text" id="event-title" required>
                    </div>
                    <div class="form-group">
                        <label>시작</label>
                        <input type="datetime-local" id="event-start" required>
                    </div>
                    <div class="form-group">
                        <label>종료</label>
                        <input type="datetime-local" id="event-end" required>
                    </div>
                    <div class="form-group">
                        <label>색상</label>
                        <input type="color" id="event-color" value="#3788d8">
                    </div>
                    <div class="form-group">
                        <label>설명</label>
                        <textarea id="event-description"></textarea>
                    </div>
                    <div class="form-actions">
                        <button type="submit">저장</button>
                        <button type="button" class="close-modal">닫기</button>
                    </div>
                </form>
            </div>
        `;
        document.body.appendChild(modal);

        // 닫기 버튼 이벤트
        modal.querySelector('.close-modal').addEventListener('click', () => modal.remove());

        // 폼 제출 이벤트
        const form = modal.querySelector('#add-event-form');
        form.addEventListener('submit', function(e) {
            e.preventDefault();
            const eventData = {
                sche_title: document.getElementById('event-title').value,
                st_dt: formatDateForMySQL(document.getElementById('event-start').value),
                ed_dt: formatDateForMySQL(document.getElementById('event-end').value),
                sche_color: document.getElementById('event-color').value,
                sche_content: document.getElementById('event-description').value,
                cw_idx: current_cw_idx,
                user_id: "current_user" // 실제 사용자 ID로 대체 필요
            };

            fetch(`${context_path}/find_detail/add`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + (localStorage.getItem('token') || '')
                },
                body: JSON.stringify(eventData)
            })
            .then(response => {
                if (!response.ok) throw new Error(`HTTP ${response.status} 오류`);
                return response.json();
            })
            .then(data => {
                if (data.result > 0) {
                    calendar.refetchEvents();
                    modal.remove();
                } else {
                    alert("일정 추가에 실패했습니다.");
                }
            })
            .catch(error => {
                console.error('일정 추가 오류:', error);
                alert("일정 추가 중 오류가 발생했습니다.");
            });
        });
    }

    // 일정 상세보기 함수 (기존 코드 유지)
    function show_event_details(event) {
        const modal = document.createElement('div');
        modal.className = 'event-modal';
        modal.innerHTML = `
            <div class="modal-content">
                <h2>일정 상세</h2>
                <div class="form-group">
                    <label>제목</label>
                    <span>${event.title}</span>
                </div>
                <div class="form-group">
                    <label>시작</label>
                    <span>${event.start.toLocaleString()}</span>
                </div>
                <div class="form-group">
                    <label>종료</label>
                    <span>${event.end ? event.end.toLocaleString() : '미정'}</span>
                </div>
                <div class="form-group">
                    <label>설명</label>
                    <span>${event.extendedProps.description || '없음'}</span>
                </div>
                <div class="form-actions">
                    <button type="button" class="close-modal">닫기</button>
                </div>
            </div>
        `;
        document.body.appendChild(modal);
        modal.querySelector('.close-modal').addEventListener('click', () => modal.remove());
    }

    // 캘린더 초기화 (기존 코드 유지)
    const init_calendar = function() {
        const calendar_el = document.getElementById('calendar');
        if (!calendar_el) {
            console.error("캘린더 요소를 찾을 수 없습니다.");
            return;
        }

        calendar = new FullCalendar.Calendar(calendar_el, {
            initialView: 'dayGridMonth',
            locale: 'ko',
            headerToolbar: {
                left: 'prev,next today add_event_btn',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay'
            },
            customButtons: {
                add_event_btn: {
                    text: '일정 추가',
                    click: open_add_event_modal
                }
            },
            events: function(fetchInfo, successCallback, failureCallback) {
                fetch(`${context_path}/find_detail/list?cw_idx=${current_cw_idx}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': 'Bearer ' + (localStorage.getItem('token') || '')
                    }
                })
                .then(response => {
                    if (!response.ok) throw new Error(`HTTP ${response.status} 오류`);
                    return response.json();
                })
                .then(data => {
                    successCallback(data.map(event => ({
                        id: event.sche_idx,
                        title: event.sche_title,
                        start: event.st_dt,
                        end: event.ed_dt,
                        color: event.sche_color || '#3788d8',
                        extendedProps: { description: event.sche_content || "" }
                    })));
                })
                .catch(error => {
                    console.error("일정 불러오기 실패:", error);
                    failureCallback(error);
                });
            },
            eventClick: function(info) {
                show_event_details(info.event);
            }
        });
        calendar.render();
    };

    init_calendar();
}); 