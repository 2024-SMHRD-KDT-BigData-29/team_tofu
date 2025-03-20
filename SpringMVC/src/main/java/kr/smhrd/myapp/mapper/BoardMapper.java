package kr.smhrd.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.smhrd.myapp.entity.Board;

@Mapper
public interface BoardMapper {

	List<Board> boardList();

	void deleteBoard(int idx);

	int insertBoard(Board board);

	Board boardDetail(int idx);
	

}
