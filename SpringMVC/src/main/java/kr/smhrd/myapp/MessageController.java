package kr.smhrd.myapp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.smhrd.myapp.entity.Member;
import kr.smhrd.myapp.entity.Message;
import kr.smhrd.myapp.mapper.MessageMapper;

@Controller
public class MessageController {
	
	@Autowired
	MessageMapper mapper;
	
	@GetMapping("/deleteMessage/{idx}") // deleteMessage/2
	public String deleteMessage(@PathVariable("idx") int idx) {
		
		System.out.println("삭제할 메세지 번호 : " + idx);
		mapper.deleteMessage(idx);
		
		return "redirect:/messageShow.do";
	}
	
	@RequestMapping("/messageSend.do")
	public String messageSend(Message msg) {
		System.out.println("메시지보내기요청...");
		System.out.println(msg);
		
		mapper.messageSend(msg);
		
		return "redirect:/messageShow.do";
	}
	
	@RequestMapping("/messageShow.do")
	public String messageShow(HttpServletRequest request, Model model) {
		System.out.println("메시지 조회 요청...");
		
		Member member = (Member)(request.getSession().getAttribute("loginMember"));
		
		if(member != null) {
			List<Message> list = mapper.messageShow(member.getEmail());
			model.addAttribute("msgList",list);
		}
		
		return "index";
		
	}
	
}










