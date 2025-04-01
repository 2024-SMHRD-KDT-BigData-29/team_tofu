package com.together.furture;

import com.together.furture.entity.schedule_info;
import com.together.furture.service.schedule_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/find_detail")
public class schedule_controller {

	@Autowired
	private schedule_service schedule_service;

	@GetMapping("/list")
	public List<schedule_info> get_schedule_list(@RequestParam int cw_idx) {
		System.out.println("Received GET /find_detail/list with cw_idx: " + cw_idx);
		List<schedule_info> schedules = schedule_service.get_schedules_by_cw_idx(cw_idx);
		System.out.println("Returning schedules: " + schedules);
		return schedules;
	}

	@PostMapping("/add")
	public Map<String, Integer> add_schedule(@RequestBody schedule_info schedule) {
		System.out.println("Received POST /find_detail/add with schedule: " + schedule);
		int result = schedule_service.add_schedule(schedule);
		Map<String, Integer> response = new HashMap<>();
		response.put("result", result);
		System.out.println("Returning result: " + result);
		return response;
	}

	@DeleteMapping("/delete/{sche_idx}")
	public Map<String, Integer> delete_schedule(@PathVariable int sche_idx) {
		System.out.println("Received DELETE /find_detail/delete with sche_idx: " + sche_idx);
		int result = schedule_service.delete_schedule(sche_idx);
		Map<String, Integer> response = new HashMap<>();
		response.put("result", result);
		System.out.println("Returning result: " + result);
		return response;
	}
}