package travelspot;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper 	//매퍼 파일이야, @MapperScan 필요 
@Repository //객체 생성, @ComponentScan 필요
public interface PlaceMapper {
	 public void insertPlaces(PlaceDTO placeDTO);
	 public List<PlaceDTO> listPlaces(HashMap<String, Object> param);
	 public PlaceDTO selectPlace(int contentId);
	 public void plusViewCount(int contentId); //조회수 증가
	 public int selectPlaceId(int contentId);
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
	 public void likePlace(int contentId); //관광지 찜하기
	 public Integer CheckPlaceLikes(HashMap<String, Integer> map);//관광지 찜 체크
	 public void insertLikes(HashMap<String, Integer> map);
	 public List<PlaceDTO> searchPlace(HashMap<String, Object> map); //검색 관광지 리스트
	 public int searchPlaceCnt(HashMap<String, Object> map);
	 public List<PlaceContentsDTO> searchThemePlaces(HashMap<String, Object> map);//테마검색
	 public int searchThemePlacesCnt(HashMap<String, Object> map);
	 public void cancelLikes(HashMap<String, Integer> map);
	 public Integer cancelPlaceLike(int contentId);
}

