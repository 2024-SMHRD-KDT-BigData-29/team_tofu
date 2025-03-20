//package kr.smhrd.myapp;
//
//import java.text.DateFormat;
//import java.util.Date;
//import java.util.Locale;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//// Controller 생성해주는 역할
//@Controller
//public class HomeController {
//	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
//		
//		return "home";
//	}
//	
//	//@RequestMapping: 요청에 대한 경로설정
//	@RequestMapping("/home2")
//	public String home2(Model model) {
//
//		//Spring에서 의존성주입(DI)에 의해서 Model객체가 생성
//		//Model객체: 데이터를 임시보관하는 역할
//		model.addAttribute("msg", "Hello eGovFramework!");		
//		
//		//view name 지정 -> view resolver로 전달 -> /WEB-INF/views/home2.jsp -> forward
//		//Model에 저장된 데이터를 forward방식으로 이동하면서 home2.jsp로 전달
//		return "home2";
//	}
//	
//}
