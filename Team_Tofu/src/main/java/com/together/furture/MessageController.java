package com.together.furture;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.together.furture.entity.ChatPreview;
import com.together.furture.mapper.personal_msg_mapper;

@Controller
public class MessageController {

 @Autowired
 private personal_msg_mapper mapper;
 private ChatPreview chat_mapper;

 @ResponseBody
 @GetMapping("/unread_count")
 public int getUnreadMessageCount(HttpSession session) {
     String userId = (String) session.getAttribute("user_id");
     if (userId == null) return 0;

     return mapper.countUnreadMessages(userId);
 }
 
 @GetMapping("/my_msg_list")
 public String myMessageList(HttpSession session, Model model) {
     String userId = (String) session.getAttribute("user_id");
     List<ChatPreview> chatRoomList = mapper.getChatPreviewList(userId);
     model.addAttribute("chatRoomList", chatRoomList);
     return "my_msg_list";
 }
}
