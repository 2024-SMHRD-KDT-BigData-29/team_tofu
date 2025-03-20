package kr.smhrd.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.smhrd.myapp.entity.Member;

//root-context.xml에서 scan 될 수 있게 @Mapper 어노테이션 표기
// -> 자동으로 객체 생성
@Mapper
public interface MemberMapper {

	void memberJoin(Member member);

	Member memberLogin(Member member);

	//회원정보를 수정하는 추상메소드 구현
	void memberUpdate(Member member);

	List<Member> selectMember();

	void memberDelete(String email);

	String emailCheck(String email);	 
	
}




