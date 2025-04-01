package com.together.furture.mapper; // 패키지명 확인!

import com.together.furture.entity.schedule_info;
import java.util.List;

public interface schedule_info_mapper {
	List<schedule_info> select_by_cw_idx(int cw_idx);

	schedule_info select_by_sche_idx(int sche_idx);

	int insert_schedule(schedule_info schedule);

	int delete_schedule(int sche_idx);
}