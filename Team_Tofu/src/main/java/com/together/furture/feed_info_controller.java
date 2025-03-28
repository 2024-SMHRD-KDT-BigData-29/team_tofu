package com.together.furture;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oreilly.servlet.MultipartRequest;
import com.together.furture.entity.comment_insert;
import com.together.furture.entity.insert_feed;
import com.together.furture.entity.user_info;
import com.together.furture.mapper.comment_mapper;
import com.together.furture.mapper.feed_info_mapper;
import com.together.furture.mapper.user_info_mapper;
import com.together.furture.util.FileUploadUtil;
import com.together.furture.util.feedUploadUtile;

@Controller
public class feed_info_controller {

	@Autowired
	feed_info_mapper mapper;
	@Autowired
	user_info_mapper usermapper;
	@Autowired
	comment_mapper commentmapper;
	@Autowired
	private feedUploadUtile feedUploadUtile;

	@Autowired
	private FileUploadUtil fileUploadUtil;

	// main 페이지 이동
	@GetMapping("/main")
	public String mainPage(Model model) {

		System.out.println("피드로 이동");
		List<insert_feed> feedList = mapper.getFeedList();
		model.addAttribute("feedList", feedList);
		System.out.println("feedList길이 확인>>" + feedList.size());

		List<comment_insert> cmtList = commentmapper.comment_show();
		System.out.println("cmtList길이 확인>>" + cmtList.size());
		model.addAttribute("cmtList", cmtList);
		return "main";
	}

	// 글쓰기 이동
	@RequestMapping("/write")
	public String Write() {
		System.out.println("글쓰기 이동");
		return "write";
	}

	// 경로만 추가 -> 내가 쓴글 보기
	@GetMapping("/mypost")
	public String myPost() {
		System.out.println("mypost 이동");
		return "mypost";
	}

	// 게시글 작성
	@PostMapping("/write")
	public String insert_feed(insert_feed feed, HttpServletRequest request) throws IOException {

		MultipartRequest multi = feedUploadUtile.feedupload(request);

		user_info user = (user_info) request.getSession().getAttribute("login_user");
		String user_id = (String) request.getSession().getAttribute("user_id");
		String user_nick = (String) request.getSession().getAttribute("user_nick");
		String user_profile = (String) request.getSession().getAttribute("user_profile");

		String feed_title = multi.getParameter("feed_title");
		String feed_content = multi.getParameter("feed_content");
		String hash_tag = multi.getParameter("hash_tag");
		String feed_file = multi.getFilesystemName("feed_file");

		// feed_title null 체크
		if (feed_title == null || feed_title.trim().isEmpty()) {
			throw new IllegalArgumentException("feed_title은 필수 입력값입니다.");
		}

		feed = new insert_feed(feed_title, feed_content, feed_file, hash_tag, user_id, user_nick, 0, user_profile);

		System.out.println("원영 test2 : " + user_nick);

		System.out.println("원영 test2 : " + feed.toString());

		int cnt = mapper.insertfeed(feed);

		if (cnt == 1) {
			System.out.println("[게시물 업로드 성공]");
		} else {
			System.out.println("[게시물 업로드 실패]");
		}

		return "redirect:/main";
	}

	// 게시글 삭제
	@PostMapping("/deletePost/{feed_idx}")
	public ResponseEntity<String> deletepost(@PathVariable("feed_idx") int idx) {
		int result = mapper.deletepost(idx);
		if (result == 1) {
			System.out.println("게시물 삭제 성공: feed_idx=" + idx);
			return ResponseEntity.ok("삭제 성공");
		} else {
			System.out.println("게시물 삭제 실패: feed_idx=" + idx);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
		}
	}

	// 게시글 수정 페이지 이동
	@GetMapping("/editPost/{feed_idx}")
	public String editPost(@PathVariable("feed_idx") int feed_idx, Model model) {
		insert_feed feed = mapper.getFeedById(feed_idx);
		if (feed == null) {
			System.out.println("게시물이 존재하지 않습니다: feed_idx=" + feed_idx);
			return "redirect:/main";
		}
		if (feed.getFeed_idx() == null) {
			System.out.println("조회된 게시물의 feed_idx가 null입니다: " + feed.toString());
			return "redirect:/main";
		}
		System.out.println("조회된 게시물: " + feed.toString());
		model.addAttribute("feed", feed);
		return "feed_edit";
	}

	// 게시물 수정 처리
	@PostMapping("/update_feed")
	public String update_feed(HttpServletRequest request) {
		MultipartRequest multi = null;

		try {
			// 파일 업로드 처리
			multi = feedUploadUtile.feedupload(request);

			// feed_idx 검증
			String feedIdxStr = multi.getParameter("feed_idx");
			if (feedIdxStr == null || feedIdxStr.trim().isEmpty()) {
				System.out.println("feed_idx가 전달되지 않았습니다. 요청 파라미터 확인: " + multi.getParameterNames());
				return "redirect:/main";
			}

			int feed_idx;
			try {
				feed_idx = Integer.parseInt(feedIdxStr);
			} catch (NumberFormatException e) {
				System.out.println("feed_idx 형식이 잘못되었습니다: " + feedIdxStr);
				return "redirect:/main";
			}

			String feed_title = multi.getParameter("feed_title");
			String feed_content = multi.getParameter("feed_content");
			String hash_tag = multi.getParameter("hash_tag");
			String feed_file = multi.getFilesystemName("feed_file");

			// 필수 필드 검증
			if (feed_title == null || feed_title.trim().isEmpty()) {
				System.out.println("feed_title은 필수 입력값입니다.");
				return "redirect:/main";
			}

			// 기존 게시물 정보 가져오기
			insert_feed existingFeed = mapper.getFeedById(feed_idx);
			if (existingFeed == null) {
				System.out.println("게시물이 존재하지 않습니다: feed_idx=" + feed_idx);
				return "redirect:/main";
			}

			// 새 이미지가 업로드되지 않았으면 기존 이미지 유지
			if (feed_file == null) {
				feed_file = existingFeed.getFeed_file();
			}

			// 수정된 데이터로 객체 생성
			insert_feed feed = new insert_feed();
			feed.setFeed_idx(feed_idx);
			feed.setFeed_title(feed_title);
			feed.setFeed_content(feed_content != null ? feed_content : "");
			feed.setHash_tag(hash_tag != null ? hash_tag : "");
			feed.setFeed_file(feed_file);
			feed.setUser_id(existingFeed.getUser_id());

			// DB 업데이트
			int result = mapper.updateFeed(feed);
			if (result == 1) {
				System.out.println("게시물 수정 성공: feed_idx=" + feed_idx);
			} else {
				System.out.println("게시물 수정 실패: feed_idx=" + feed_idx);
			}

			return "redirect:/main";

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("파일 업로드 중 오류 발생: " + e.getMessage());
			return "redirect:/main";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예상치 못한 오류 발생: " + (e.getMessage() != null ? e.getMessage() : "알 수 없는 에러"));
			return "redirect:/main";
		}
	}

	// 회원정보 수정 처리
	@RequestMapping("/user_edit.do")
	public String user_edit(user_info user, Model model, HttpServletRequest request) {
	    MultipartRequest multi = null;

	    try {
	        // 파일 업로드 처리
	        multi = fileUploadUtil.uploadFile(request);
	        
	        user_info login_user = usermapper.user_login(user);
	        
	        String profile_name = multi.getFilesystemName("user_profile");
	        String user_id = multi.getParameter("user_id");
	        String user_pw = multi.getParameter("user_pw");
	        String user_gender = multi.getParameter("user_gender");
	        String user_intro = multi.getParameter("user_intro");
	        String user_nick = multi.getParameter("user_nick");
	        String user_email = multi.getParameter("user_email");

	        // 새 객체 생성 (기존 데이터 유지 및 업데이트, 종현이형>>사용자가 정보를 빈값으로 두면 기본정보로 유지하게 만들었어요)
	        login_user = new user_info(
	            user_id.isEmpty() ? login_user.getUser_id() : user_id,
	            user_pw.isEmpty() ? login_user.getUser_pw() : user_pw,
	            user_nick.isEmpty() ? login_user.getUser_nick() : user_nick,
	            user_gender.isEmpty() ? login_user.getUser_gender() : user_gender,
	            user_email.isEmpty() ? login_user.getUser_email() : user_email,
	            user_intro.isEmpty() ? login_user.getUser_intro() : user_intro,
	            profile_name != null ? profile_name : login_user.getUser_profile(),
	            login_user.getUser_role(),                                      // 회원구분 유지
	            login_user.getJoined_at()                                     // 가입일자 유지
	        );
	        System.out.println("check : " + login_user.toString());

	     // 마이페이지로 전달할 데이터 추가
	        model.addAttribute("user_id", login_user.getUser_id());             // 사용자 ID
	        model.addAttribute("user_nick", login_user.getUser_nick());         // 닉네임
	        model.addAttribute("user_email", login_user.getUser_email());       // 이메일
	        model.addAttribute("user_gender", login_user.getUser_gender());     // 성별
	        model.addAttribute("user_intro", login_user.getUser_intro());       // 소개글
	        model.addAttribute("user_profile", login_user.getUser_profile());   // 프로필 사진 경로
	        model.addAttribute("user_division", login_user.getUser_role()); // 회원구분
	        model.addAttribute("joined_at", login_user.getJoined_at());         // 가입일자 (Timestamp)
	        System.out.println("회원정보 수정 성공");

	    } catch (IOException e) {
	        e.printStackTrace();
	        model.addAttribute("error", "파일 업로드 중 오류 발생");
	        return "edit_profile"; // 오류 시 수정 페이지로 돌아감
	    }

	    return "redirect:/main"; // 수정 완료 후 마이페이지로 이동 (혹은 원하는 페이지)
	}

}
