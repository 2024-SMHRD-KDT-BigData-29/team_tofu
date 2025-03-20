package kr.smhrd.myapp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.smhrd.myapp.entity.Member;
import kr.smhrd.myapp.mapper.MemberMapper;

@Controller
public class MemberController {
	
	//@Autowired: Spring Container에 이미 생성된 객체를 주입해주는 역할
	@Autowired
	MemberMapper mapper;	
	
	@RequestMapping("/indexPage.do")
	public String index() {
		return "index";
	}
	
	//@RequestParam(input의 name속성값)
	// - 요청객체 내 담긴 데이터 접근하는 어노테이션 ( 생략가능 )
	@RequestMapping("/memberJoin.do")
	public String memberJoin(Member member, Model model) {
		
		mapper.memberJoin(member);		
		model.addAttribute("email", member.getEmail());
				
		return "joinSuccess"; //이동한 view name
	}
	
	//로그인을 처리하는 메소드 구현해보기(기본틀만)
	//이메일, 패스워드 콘솔창에 출력해보기
	@RequestMapping("/memberLogin.do")
	public String memberLogin(Member member, HttpServletRequest request) {
		
	    Member loginMember = mapper.memberLogin(member);
	    //System.out.println(loginMember);
	    
	    request.getSession().setAttribute("loginMember", loginMember);
				
		return "redirect:/messageShow.do"; 
	}
	
	@RequestMapping("/memberLogout.do")
	public String memberLogout(HttpServletRequest request) {
			    
	    request.getSession().removeAttribute("loginMember");
				
		return "redirect:/indexPage.do"; 
	}
	
	@RequestMapping("/memberUpdatePage.do")
	public String memberUpdatePage() {
		return "update";
	}
	
	/*
	 	1.memberUpdate 메소드 틀 생성
	 	 - 수정할 데이터를 받기 위한 매개변수 정의(pw, tel, address)
	 
	 	2.mapper 내 memberUpdate() 를 호출
	 	 - 수정할 데이터를 인자로 Member객체 전달
	 	 
	 	3.DB업데이트 후 세션에 저장된 Member 정보 수정

		4.index 페이지로 이동
	  
	 */
	@RequestMapping("/memberUpdate.do")
	public String memberUpdate(Member member, HttpServletRequest request) {
		
		//1.세션에 저장된 회원정보 중 email 데이터 가져오기
		//2.member객체의 email 변수에 초기화
		Member mem = (Member)(request.getSession().getAttribute("loginMember"));
		member.setEmail(mem.getEmail());
		
		System.out.println(member);
		
		mapper.memberUpdate(member);
		
		request.getSession().setAttribute("loginMember", member);
		
		return "redirect:/indexPage.do";
	}
	
	@RequestMapping("/selectMemberPage.do")
	public String selectMemberPage(Model model) {
		
		//1.모든 회원정보를 조회한 후 List에 저장
		//2.Model객체 저장
		List<Member> list = mapper.selectMember();
		model.addAttribute("list", list);
		
		return "select";
	}
	
	@RequestMapping("/memberDelete.do")
	public String memberDelete(String email) {
		
		mapper.memberDelete(email);
		
		return "redirect:/selectMemberPage.do";
	}

}











