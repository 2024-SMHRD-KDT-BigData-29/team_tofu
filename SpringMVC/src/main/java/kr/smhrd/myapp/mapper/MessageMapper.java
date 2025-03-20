package kr.smhrd.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import kr.smhrd.myapp.entity.Message;

@Mapper
public interface MessageMapper {

	void messageSend(Message msg);

	List<Message> messageShow(String email);

	@Delete("delete from message where idx=#{idx}")
	void deleteMessage(int idx);

}
