package com.together.furture.service;

import com.together.furture.mapper.schedule_info_mapper;
import com.together.furture.entity.schedule_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class schedule_service_impl implements schedule_service {

	@Autowired
	private schedule_info_mapper schedule_mapper;

	@Override
	@Transactional
	public int delete_schedule(int sche_idx) {
		schedule_info schedule = schedule_mapper.select_by_sche_idx(sche_idx);
		if (schedule == null) {
			throw new RuntimeException("일정이 존재하지 않습니다");
		}
		if (!has_permission_to_delete(schedule.get_user_id())) {
			throw new RuntimeException("삭제 권한이 없습니다");
		}
		return schedule_mapper.delete_schedule(sche_idx);
	}

	private boolean has_permission_to_delete(String creator_id) {
		String current_user_id = get_current_user_id();
		return creator_id.equals(current_user_id);
	}

	private String get_current_user_id() {
		return "current_user"; // 임시 값
	}

	@Override
	public List<schedule_info> get_schedules_by_cw_idx(int cw_idx) {
		return schedule_mapper.select_by_cw_idx(cw_idx);
	}

	@Override
	public int add_schedule(schedule_info schedule) {
		try {
			schedule.set_user_id(get_current_user_id_from_token());
			// sche_status 기본값 설정
			if (schedule.get_sche_status() == null) {
				schedule.set_sche_status("ACTIVE"); // 기본 상태
			}
			return schedule_mapper.insert_schedule(schedule);
		} catch (Exception e) {
			throw new RuntimeException("일정 추가 중 오류 발생: " + e.getMessage(), e);
		}
	}

	private String get_current_user_id_from_token() {
		return "current_user"; // 임시 값
	}
}