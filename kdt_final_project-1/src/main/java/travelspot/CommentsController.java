package travelspot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import User.UserDTO;

@org.springframework.stereotype.Controller
public class CommentsController {

	@Autowired
	APIServiceImpl apiservice;

	@Autowired
	PlaceServiceImpl placeservice;

	@Autowired
	CommentsServiceImpl commentsservice;
/*
	@GetMapping("/travelspot/post/comments")
	@ResponseBody
	public List<CommentsDTO> getComments(@RequestParam int contentId){
		
		List<CommentsDTO> commentsList = commentsservice.getComments(contentId);
		return commentsList;
	}
	*/
	
	@GetMapping("/travelspot/post/comments")
	@ResponseBody
	public Map<String, Object> getComments(@RequestParam int contentId, HttpSession session){
		Map<String, Object> map = new HashMap<>();
		
		List<CommentsDTO> commentsList = commentsservice.getComments(contentId);
		map.put("commentsList", commentsList);
		
		UserDTO userdto = (UserDTO)session.getAttribute("user");
		if(ObjectUtils.isEmpty(userdto)) {
			map.put("userdto", "null");
		}else {
			map.put("userdto", userdto.getNickname());
		}
		
		return map;
	}
	
	 @RequestMapping(value="/travelspot/post/comments/save", produces = {"application/json; charset=utf-8"} )
	 @ResponseBody 
	 public void saveComments(int contentId, String contents, HttpServletRequest request) {
		 //로그인 체크
		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO)session.getAttribute("user");
		//String userNickname = userdto.getNickname();
		//System.out.println(userdto.getNickname());
		
		if(ObjectUtils.isEmpty(userdto) ) {
		} else {
		CommentsDTO commentsDTO = new CommentsDTO();
		 commentsDTO.contents = contents;
		 commentsDTO.place_id = contentId;
		 commentsDTO.ref_group = 1;
		 commentsDTO.writer = userdto.getNickname();
		 commentsservice.insertComments(commentsDTO);
		}
	 }
	 
	 @RequestMapping(value="/travelspot/post/comments/delete", produces = {"application/json; charset=utf-8"} )
	 @ResponseBody 
	 public void deleteComments(int id) {
		 commentsservice.deleteComments(id);
	 }
	 
	 @RequestMapping(value="/travelspot/post/comments/modify", produces = {"application/json; charset=utf-8"} )
	 @ResponseBody 
	 public CommentsDTO modifyComments(int id) {
		 return commentsservice.getOneContent(id);
	 }
	 
	 @GetMapping(value="/travelspot/post/comments/modify_save", produces = {"application/json; charset=utf-8"} )
	 @ResponseBody 
	 public void modify_SaveComments(int id, String contents) {
		 CommentsDTO dto = new CommentsDTO();
		 dto.id = id;
		 dto.contents = contents;
		 
		 commentsservice.updateComments(dto);
	 }
	 
	 @GetMapping(value="/travelspot/post/comments/modify_cancel", produces = {"application/json; charset=utf-8"} )
	 @ResponseBody 
	 public void modify_CancelComments(int contentId) {
		
	 }
	 

}// controller
