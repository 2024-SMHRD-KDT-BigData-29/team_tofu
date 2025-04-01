package com.together.furture.service;

 
import java.util.List;

import com.together.furture.entity.schedule_info;

public interface schedule_service {
	List<schedule_info> get_schedules_by_cw_idx(int cw_idx);

	int add_schedule(schedule_info schedule);

	int delete_schedule(int sche_idx);
}