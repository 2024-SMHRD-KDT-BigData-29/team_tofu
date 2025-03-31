package com.together.furture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.together.furture.entity.insert_feed;
import com.together.furture.entity.user_info;


@Mapper
public interface feed_info_mapper {
	

	int insertfeed(insert_feed feed);

	user_info user_login(user_info user);
	
	List<insert_feed> getFeedList();
	
	int deletepost(int feed_idx);

	insert_feed getFeedById(int feed_idx);

	int updateFeed(insert_feed feed);

}
