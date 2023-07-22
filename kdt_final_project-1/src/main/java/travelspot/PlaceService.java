package travelspot;

import java.util.HashMap;
import java.util.List;


public interface PlaceService {
	 public List<PlaceDTO> listPlaces(HashMap<String, Object> param);
	 public PlaceDTO selectPlace(int contentId);
	 public int selectPlaceId(int contentId);
	 public void plusViewCount(int contentId); //조회수 증가
	 public int getTotalCnt(int areaCode);
	 public int getTotalThemeCnt(String theme);
	 public void insertThemeBasicInfo(PlaceDTO placeDTO);
	 public void updateThemePlace(PlaceDTO placeDTO);
	 public void insertTheme12Detail(ContentsDTO contentsDTO);
	 public void insertTheme39Detail(ContentsDTO contentsDTO);
	 public void insertTheme14Detail(ContentsDTO contentsDTO);
	 public void insertTheme28Detail(ContentsDTO contentsDTO);
	 public List<PlaceContentsDTO> listThemePlaces(HashMap<String, Object> param);
	 public PlaceContentsDTO getPlaceContentThemeDetail(int contentId);
	 public PlaceDTO getPlaceThemeDetail(int id);
	 public void likePlace(int contentId);
	 public Integer CheckPlaceLikes(HashMap<String, Integer> map);
	 public void insertLikes(HashMap<String, Integer> map);
	 public List<PlaceDTO> searchPlace(HashMap<String, Object> map);
	public int searchPlaceCnt(HashMap<String, Object> map);
	 public List<PlaceContentsDTO> searchThemePlaces(HashMap<String, Object> map);//테마검색
	 public int searchThemePlacesCnt(HashMap<String, Object> map);
	 public void cancelLikes(HashMap<String, Integer> map);
	 public Integer cancelPlaceLike(int contentId);
}
