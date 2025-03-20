package kr.smhrd.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.smhrd.myapp.mapper.MemberMapper;

//@RestController: 비동기 통신에 대한 처리를 하는 컨트롤러
//@ResponseBody + @Controller
@RestController
public class MemberRestController {
	
	@Autowired
	MemberMapper mapper;
	
	@RequestMapping("/emailCheck.do")
	public String emailCheck(String email) {
		
		String result = mapper.emailCheck(email);
		
		System.out.println(email);
	
		//return값 정의--> 중복X: 0 | 중복: 1
		if(result == null) { return "0"; }
		else { return "1"; } //응답보낼 데이터를 정의
	}	
	
}
