package com.together.furture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.together.furture.entity.insert_cowork;
import com.together.furture.entity.user_info;

@Mapper
public interface cowork_info_mapper {

	int insertcowork(insert_cowork cowork);
	
	user_info user_login(user_info user);

	List<insert_cowork> getCoworkList();


}