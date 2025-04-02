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

	@PostMapping("/update_feed")
	public String update_feed(HttpServletRequest request) {
	    MultipartRequest multi = null;

	    try {
	        // 파일 업로드 처리
	        multi = feedUploadUtile.feedupload(request);
	        System.out.println("파일 업로드 완료");

	        // feed_idx 검증
	        String feedIdxStr = multi.getParameter("feed_idx");
	        System.out.println("전달된 feed_idx: " + feedIdxStr);
	        if (feedIdxStr == null || feedIdxStr.trim().isEmpty()) {
	            System.out.println("feed_idx가 전달되지 않았습니다. 요청 파라미터 확인: " + multi.getParameterNames());
	            return "redirect:/main";
	        }

	        int feed_idx;
	        try {
	            feed_idx = Integer.parseInt(feedIdxStr);
	            System.out.println("파싱된 feed_idx: " + feed_idx);
	        } catch (NumberFormatException e) {
	            System.out.println("feed_idx 형식이 잘못되었습니다: " + feedIdxStr);
	            return "redirect:/main";
	        }

	        String feed_title = multi.getParameter("feed_title");
	        String feed_content = multi.getParameter("feed_content");
	        String hash_tag = multi.getParameter("hash_tag");
	        String feed_file = multi.getFilesystemName("feed_file");

	        // 입력값 디버깅
	        System.out.println("feed_title: " + feed_title);
	        System.out.println("feed_content: " + feed_content);
	        System.out.println("hash_tag: " + hash_tag);
	        System.out.println("feed_file: " + feed_file);

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
	        System.out.println("기존 게시물 데이터: " + existingFeed.toString());

	        // 새 이미지가 업로드되지 않았으면 기존 이미지 유지
	        if (feed_file == null) {
	            feed_file = existingFeed.getFeed_file();
	            System.out.println("새 파일이 없으므로 기존 파일 사용: " + feed_file);
	        }

	        // 수정된 데이터로 객체 생성
	        insert_feed feed = new insert_feed();
	        feed.setFeed_idx(feed_idx);
	        feed.setFeed_title(feed_title);
	        feed.setFeed_content(feed_content != null ? feed_content : "");
	        feed.setHash_tag(hash_tag != null ? hash_tag : "");
	        feed.setFeed_file(feed_file);
	        feed.setUser_id(existingFeed.getUser_id());

	        System.out.println("업데이트 전 feed 객체: " + feed.toString());

	        // DB 업데이트
	        int result = mapper.updateFeed(feed);
	        System.out.println("업데이트 쿼리 실행 결과: " + result);

	        if (result == 1) {
	            System.out.println("게시물 수정 성공: feed_idx=" + feed_idx);
	        } else {
	            System.out.println("게시물 수정 실패: feed_idx=" + feed_idx + ", result=" + result);
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
	@PostMapping("/deletePost/{feed_idx}")
	public ResponseEntity<String> deletepost(@PathVariable("feed_idx") int idx) {
	    System.out.println("삭제 시도: feed_idx=" + idx);
	    mapper.deleteCommentsByFeedIdx(idx);
	    int result = mapper.deletepost(idx);
	    System.out.println("삭제 결과: " + result);
	    if (result == 1) {
	        System.out.println("게시물 삭제 성공: feed_idx=" + idx);
	        return ResponseEntity.ok("삭제 성공");
	    } else {
	        System.out.println("게시물 삭제 실패: feed_idx=" + idx);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
	    }
	}

}
