package travelspot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import User.UserDTO;

@org.springframework.stereotype.Controller
public class PlaceController {

	@Autowired
	APIServiceImpl apiservice;

	@Autowired
	PlaceServiceImpl placeservice;

	@Autowired
	CommentsServiceImpl commentsservice;

	//기본 관광지
	@RequestMapping("/travelspot/list")
	public ModelAndView showList(
		@RequestParam(required = true, defaultValue = "32") int areaCode,
		@RequestParam(required = true, defaultValue = "1") int page,
		HttpSession session) throws Exception {
		//데이터 저장: apiservice.getBasicInfo(areaCode);
		//테마별 info 저장: apiservice.getThemeInfo();
		
		ModelAndView mv = new ModelAndView();

		HashMap<String, Object> param = new HashMap<>();
		param.put("areaCode", areaCode); // 지역코드
		int pageindex = (page - 1) * 9; // 페이징처리 - 시작인덱스
		param.put("page", pageindex);

		UserDTO userdto = (UserDTO)session.getAttribute("user");
		
		mv.addObject("userdto", userdto);
		mv.addObject("placelist", placeservice.listPlaces(param));
		mv.addObject("totalCnt", placeservice.getTotalCnt(areaCode));
		mv.addObject("areaCode", areaCode);
		mv.setViewName("travelspot_list");
		return mv;
	}
	
	// 검색한 게시글 조회
	@RequestMapping("/travelspot/search")
	public ModelAndView searchPlace(
			@RequestParam(value="item", required=false, defaultValue="주소")String item, 
			@RequestParam(value="searchword", required=false, defaultValue="강원")String searchword, 
			@RequestParam(value="page", required=false, defaultValue="1")int page) {

			// 검색 조건으로 검색한 게시글 리스트, 게시글수
			HashMap<String, Object> map = new HashMap<>();

			if (item.equals("장소명")) {
				map.put("colname", "title");
			}
			if (item.equals("주소")) {
				map.put("colname", "address");
			}
			
			map.put("searchitem", item);
			map.put("colvalue", "%"+searchword+"%");
			map.put("limitindex",(page-1)*9 );
			map.put("limitcount",9);
			
			List<PlaceDTO> placelist = placeservice.searchPlace(map);
			int totalCnt = placeservice.searchPlaceCnt(map);

			ModelAndView mv = new ModelAndView();
			mv.addObject("placelist", placelist);
			mv.addObject("searchmap", map);
			mv.addObject("totalCnt", totalCnt);
			mv.setViewName("travelspot_searchlist");
			return mv;
		}

	@RequestMapping("/travelspot/post")
	public ModelAndView showPost(@RequestParam int contentId, HttpSession session) {
		PlaceDTO placedto = placeservice.selectPlace(contentId);
		
		placeservice.plusViewCount(contentId);//조회수 증가
		
		ModelAndView mv = new ModelAndView();
		
		UserDTO userdto = (UserDTO)session.getAttribute("user");
		if(ObjectUtils.isEmpty(userdto)) {
			mv.addObject("userdto", "null");
		}else {
			mv.addObject("userdto", userdto);
		}

		mv.setViewName("travelspot_post");
		mv.addObject("placedto", placedto);
		
		return mv;		
	}
	
	@RequestMapping("/travelspot/post/likes")
	@ResponseBody
	public String showPostLikes(@RequestParam int contentId, HttpSession session) {
		
		HashMap<String, Integer> map = new HashMap<>();
		
		UserDTO userdto = (UserDTO)session.getAttribute("user");
		map.put("user_id", userdto.getId());
		map.put("place_id", contentId);
		
		String response="fail";
		
		if( placeservice.CheckPlaceLikes(map)== null || placeservice.CheckPlaceLikes(map) == 0 ) { //이미 찜되어있는 게시글이면
			placeservice.likePlace(contentId); //place테이블에서 찜하기
			placeservice.insertLikes(map); //likes테이블에 정보(회원아이디,게시글아이디,찜여부) 저장	
			response = "success";
		}else if(placeservice.CheckPlaceLikes(map)!= null && placeservice.CheckPlaceLikes(map) == 1 ) { //이미 찜되어있는 게시글이면
			response = "alreadyliked";
		} 

		return response;		
	}
	
	@RequestMapping("/travelspot/post/cancelLikes")
	@ResponseBody
	public String cancelPostLikes(@RequestParam int contentId, HttpSession session) {
		
		HashMap<String, Integer> map = new HashMap<>();
		
		UserDTO userdto = (UserDTO)session.getAttribute("user");
		map.put("user_id", userdto.getId());
		map.put("place_id", contentId);
		
		String response="fail";
		
		if(placeservice.CheckPlaceLikes(map)!= null && placeservice.CheckPlaceLikes(map) == 1 ) { //이미 찜되어있는 게시글이면
			placeservice.cancelPlaceLike(contentId); //place테이블에서 찜취소
			placeservice.cancelLikes(map); //likes테이블에 정보(회원아이디,게시글아이디,찜여부) 저장	
			response = "success";
		} 
		
		return response;		
		
	}
	
	@GetMapping("/travelspot/post/images")
	@ResponseBody
	public PlaceDTO showPostImages(@RequestParam int contentId) {
		PlaceDTO placedto = placeservice.selectPlace(contentId);
		return placedto;
	}
	@GetMapping("/travelspot/post/info")
	@ResponseBody
	public PlaceDTO showPostInfo(@RequestParam int contentId) {
		PlaceDTO placeDTO = placeservice.getPlaceThemeDetail(contentId);
		return placeDTO;
	}
	
	// 테마 관광지 
	@RequestMapping("/travelspot/list_theme")
	public ModelAndView showThemeMain(@RequestParam String theme,
			@RequestParam(required = true, defaultValue = "1") int page, HttpSession session) throws Exception{
		//정보 저장: apiservice.getThemeBasicInfo(); 
		ModelAndView mv = new ModelAndView();
		
		HashMap<String, Object> param = new HashMap<>();
		param.put("theme", theme); // 지역코드
		int pageindex = (page - 1) * 9; // 페이징처리 - 시작인덱스
		param.put("page", pageindex);

		UserDTO userdto = (UserDTO)session.getAttribute("user");
		mv.addObject("placelist", placeservice.listThemePlaces(param)); //PlaceContentsDTO
		mv.addObject("totalCnt", placeservice.getTotalThemeCnt(theme));
		mv.addObject("userdto", userdto);

		mv.addObject("theme", theme);
		mv.setViewName("travelspot_list_theme");
		return mv;
	}
		
	// 검색한 게시글 조회
	@RequestMapping("/travelspot/themesearch")
	public ModelAndView searchThemePlace(
		@RequestParam(value="item", required=false, defaultValue="주소")String item, 
		@RequestParam(value="searchword", required=false, defaultValue="강원")String searchword, 
		@RequestParam(value="page", required=false, defaultValue="1")int page) {

			// 검색 조건으로 검색한 게시글 리스트, 게시글수
			HashMap<String, Object> map = new HashMap<>();

			if (item.equals("장소명")) {
				map.put("colname", "title");
			}
			if (item.equals("주소")) {
				map.put("colname", "address");
			}
				
			map.put("searchitem", item);
			map.put("colvalue", "%"+searchword+"%");
			map.put("limitindex",(page-1)*9 );
			map.put("limitcount",9);
				
			List<PlaceContentsDTO> placelist = placeservice.searchThemePlaces(map);
			int totalCnt = placeservice.searchThemePlacesCnt(map);
			
			ModelAndView mv = new ModelAndView();
			mv.addObject("placelist", placelist);
			mv.addObject("searchmap", map);
			mv.addObject("totalCnt", totalCnt);
			mv.setViewName("travelspot_searchlist_theme");
			return mv;
		}
		
	@RequestMapping("/travelspot/themepost")
	public ModelAndView showThemePost(@RequestParam int contentId, HttpSession session) {
		PlaceDTO placedto = placeservice.selectPlace(contentId);
		
		ModelAndView mv = new ModelAndView();
		
		UserDTO userdto = (UserDTO)session.getAttribute("user");
		if(ObjectUtils.isEmpty(userdto)) {
			mv.addObject("userdto", "null");
		}else {
			mv.addObject("userdto", userdto);
		}
		
		mv.addObject("placedto", placedto);
		mv.setViewName("travelspot_post_theme");
		return mv;		
	}
	
	@RequestMapping("/travelspot/themepost/likes")
	@ResponseBody
	public String showThemePostLikes(@RequestParam int contentId, HttpSession session) {
		
		HashMap<String, Integer> map = new HashMap<>();
		
		UserDTO userdto = (UserDTO)session.getAttribute("user");
		map.put("user_id", userdto.getId());
		map.put("place_id", contentId);
		
		String response="fail";
		
		if( placeservice.CheckPlaceLikes(map)== null || placeservice.CheckPlaceLikes(map) == 0 ) { //이미 찜되어있는 게시글이면
			placeservice.likePlace(contentId); //place테이블에서 찜하기
			placeservice.insertLikes(map); //likes테이블에 정보(회원아이디,게시글아이디,찜여부) 저장	
			response = "success";
		} else if(placeservice.CheckPlaceLikes(map)!= null && placeservice.CheckPlaceLikes(map) == 1 ) { //이미 찜되어있는 게시글이면
			response = "alreadyliked";
		} 

		return response;		
		
	}
	
	@RequestMapping("/travelspot/themepost/cancelLikes")
	@ResponseBody
	public String cancelThemePostLikes(@RequestParam int contentId, HttpSession session) {
		
		HashMap<String, Integer> map = new HashMap<>();
		
		UserDTO userdto = (UserDTO)session.getAttribute("user");
		map.put("user_id", userdto.getId());
		map.put("place_id", contentId);
		
		String response="fail";
		
		if(placeservice.CheckPlaceLikes(map)!= null && placeservice.CheckPlaceLikes(map) == 1 ) { //이미 찜되어있는 게시글이면
			placeservice.cancelPlaceLike(contentId); //place테이블에서 찜취소
			placeservice.cancelLikes(map); //likes테이블에 정보(회원아이디,게시글아이디,찜여부) 저장	
			response = "success";
		} 
		
		return response;		
		
	}
	
	@GetMapping("/travelspot/post/themeimages")
	@ResponseBody
	public PlaceDTO showPostThemeImages(@RequestParam int contentId) {
		PlaceDTO placedto = placeservice.selectPlace(contentId);
		return placedto;
	}
	
	@GetMapping("/travelspot/post/themeinfo")
	@ResponseBody
	public PlaceContentsDTO showPostThemeInfo(@RequestParam int contentId) {
		PlaceContentsDTO placeContentsDTO = placeservice.getPlaceContentThemeDetail(contentId);
		return placeContentsDTO;
	}
	
	
}// controller
