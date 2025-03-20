package kr.smhrd.myapp;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.smhrd.myapp.entity.Board;
import kr.smhrd.myapp.mapper.BoardMapper;

@Controller
public class BoardController {

	@Autowired
	BoardMapper mapper;

//	@GetMapping("/boardDetail/{idx}") // PathVariable 방식으로 보냈을 때
//	public String boardDetail(@PathVariable("idx") int idx) {
//		
//		return "boardDetail";
//	}
	
	@GetMapping("/boardDetail") // 쿼리스트링 방식으로 보냈을 때
	public String boardDetail(@RequestParam("idx") int idx, Model model) {
		
		Board board = mapper.boardDetail(idx);
		model.addAttribute("board", board);
		
		return "boardDetail";
	}
	
	@PostMapping("/insertBoard") // title, writer, filename, content
	public String insertBoard(Board board, HttpServletRequest request) {
		
		// 파일 업로드 시 필요한 객체 -> MultipartRequest 객체
		
		// 1. 요청객체 -> request
		// 2. 이미지 저장할 폴더의 경로(String)
//		request.getSession().getServletContext().getRealPath("resources/upload");
		String savePath = request.getRealPath("resources/upload");
		System.out.println("이미지 저장 경로 : " + savePath);
		
		// 3. 이미지 용량 크기(int)
		int maxSize = 1024 * 1024 * 10;
		
		// 4. 파일명 인코딩 방식(String)
		String encoding = "UTF-8";
		
		// 5. 파일명 중복제거(DefaultFileRenamePolicy)
		DefaultFileRenamePolicy rename = new DefaultFileRenamePolicy();
		
		MultipartRequest multi = null;
		
		try {
			multi = new MultipartRequest(request, savePath, maxSize, encoding, rename);
			
			String title = multi.getParameter("title");
			String writer = multi.getParameter("writer");
			String filename = multi.getFilesystemName("filename");
			String content = multi.getParameter("content");
			
			board = new Board(title, writer, filename, content);
			
			System.out.println(board.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int cnt = mapper.insertBoard(board);
		
		if(cnt == 1) {
			System.out.println("[게시물 업로드 성공]");
		}else {
			System.out.println("[게시물 업로드 실패]");
		}
		
		return "redirect:/goBoardMain";
	}
	
	// deleteBoard?idx=1
	@GetMapping("/deleteBoard/{idx}") // deleteBoard/1
	public String deleteBoard(@PathVariable("idx") int idx) {
		mapper.deleteBoard(idx);
		return "redirect:/goBoardMain";
	}
	
	@GetMapping("/goBoardMain")
	public String goBoardMain(Model model) {
		
		List<Board> list = mapper.boardList();
		model.addAttribute("list", list);
		
		return "boardMain";
	}
	
	@GetMapping("/goBoardWrite")
	public String goBoardWrite() {
		return "boardWrite";
	}
	
}
