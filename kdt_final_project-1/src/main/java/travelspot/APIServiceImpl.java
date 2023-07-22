package travelspot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service("apiservice")
public class APIServiceImpl {

	// (장훈님) String serviceKey ="gyesYtRw%2BO5TYGJgK%2FiI%2FFD6htVqBdnM8lz7Qp2noL4lQCWtcnA%2BWzJ9dWkBu0dMagfS1sVHzJi3Vn8CQaqM%2Fw%3D%3D";
	// (연균님) String serviceKey ="oMd9ys9uR2xk4AhaEw3PdU3zeKbjOIsk2Bz3OemXqEvLvkWYKrTrlHBgO9KXoLCRGHdi6VbCLaOkL4NB6AJIBA%3D%3D";
	// (경민님) String serviceKey ="A7Izc9O8PXKHKY%2FsWnQUYwt5u6dujTuf3unurHLSOEIfHLv%2F7waxlcZgPnRqpsKUO60J64lzoZ%2FYO3wW5sH1rw%3D%3D";
	// 상세정보 가져오기
	String serviceKey = "V5T49DK1YRF29Mu4k6Fkn%2BXzVJry5UAPEVgaM0XM5ZvqYo3aoOyvLFFRgxdZ0KtsLJoXAx%2BswJPxcjmoxm8JYg%3D%3D";

	StringBuilder urlBuilder;

	@Autowired
	PlaceMapper placemapper;

	// 전체 관광지 추천용: 테마 없는 기본 장소 불러오기(지역코드 이용)
	public void getBasicInfo(int areaCode) throws Exception {

		String totalurl = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?ServiceKey=V5T49DK1YRF29Mu4k6Fkn%2BXzVJry5UAPEVgaM0XM5ZvqYo3aoOyvLFFRgxdZ0KtsLJoXAx%2BswJPxcjmoxm8JYg%3D%3D&pageNo=1&numOfRows=100&MobileOS=ETC&arrange=O&areaCode="
				+ areaCode + "&contentTypeId=12&MobileApp=test";

		urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/areaBasedList1");

		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("test", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("arrange", "UTF-8") + "=" + URLEncoder.encode("O", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("areaCode", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(areaCode), "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode("12", "UTF-8"));
		url(urlBuilder);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(totalurl);

		NodeList nList = doc.getElementsByTagName("item");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				int contentId = Integer.valueOf(getValue("contentid", eElement));
				String title = getValue("title", eElement);
				int areaCode1 = Integer.valueOf(getValue("areacode", eElement));
				String image1 = getValue("firstimage", eElement);
				String address = getValue("addr1", eElement);
				double mapx = Double.valueOf(getValue("mapx", eElement));
				double mapy = Double.valueOf(getValue("mapy", eElement));
				// String contents = getValue("", eElement);

				PlaceDTO placeDTO = new PlaceDTO(contentId, title, areaCode1, image1, address, mapx, mapy);
				System.out.println(placeDTO.toString());

				placemapper.insertPlaces(placeDTO);
			} // if
		} // for
	}// test method

	// 캠핑(친구)
	// http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&ServiceKey=인증키&listYN=Y&arrange=A&contentTypeId=&areaCode=&sigunguCode=&cat1=C01&cat2=C0116&cat3=C01160001
	// 힐링(연인)
	// http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&ServiceKey=인증키&listYN=Y&arrange=A&contentTypeId=&areaCode=&sigunguCode=&cat1=C01&cat2=C0114&cat3=C01140001
	// 혼자
	// http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&ServiceKey=인증키&listYN=Y&arrange=A&contentTypeId=&areaCode=&sigunguCode=&cat1=C01&cat2=C0113&cat3=C01130001
	// 가족
	// http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&ServiceKey=인증키&listYN=Y&arrange=A&contentTypeId=&areaCode=&sigunguCode=&cat1=C01&cat2=C0112&cat3=C01120001

	// 테마별 게시글 contentId 불러오기
	public void getThemeBasicInfo() throws Exception {
		// (36)String friendUrl =
		// "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&ServiceKey="+serviceKey+"&listYN=Y&arrange=A&contentTypeId=&areaCode=&sigunguCode=&cat1=C01&cat2=C0116&cat3=C01160001";
		// (147) String coupleUrl =
		// "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&ServiceKey="+serviceKey+"&listYN=Y&arrange=A&contentTypeId=&areaCode=&sigunguCode=&cat1=C01&cat2=C0114&cat3=C01140001";
		// (54)String aloneUrl =
		// "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&ServiceKey="+serviceKey+"&listYN=Y&arrange=A&contentTypeId=&areaCode=&sigunguCode=&cat1=C01&cat2=C0113&cat3=C01130001";
		// (36)String familyUrl =
		// "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&ServiceKey="+serviceKey+"&listYN=Y&arrange=A&contentTypeId=&areaCode=&sigunguCode=&cat1=C01&cat2=C0112&cat3=C01120001";

		String[] themeList = new String[] { "friends", "couple", "alone", "family" };
		// String[] urlList = new String[] {friendUrl, coupleUrl, aloneUrl, familyUrl };
		String[] numOfRowsList = new String[] { "1", "0", "0", "0" };
		String[] cat2List = new String[] { "C0116", "C0114", "C0113", "C0112" };
		String[] cat3List = new String[] { "C01160001", "C01140001", "C01130001", "C01120001" };

		for (int i = 0; i < themeList.length; i++) {
			urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/areaBasedList1");

			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRowsList[i], "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("listYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("arrange", "UTF-8") + "=" + URLEncoder.encode("A", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("cat1", "UTF-8") + "=" + URLEncoder.encode("C01", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("cat2", "UTF-8") + "=" + URLEncoder.encode(cat2List[i], "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("cat3", "UTF-8") + "=" + URLEncoder.encode(cat3List[i], "UTF-8"));

			url(urlBuilder);

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(String.valueOf(urlBuilder));

			NodeList nList = doc.getElementsByTagName("item");
			ArrayList<Integer> contentIdList = new ArrayList<Integer>();
			ArrayList<Integer> contentTypeIdList = new ArrayList<Integer>();

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					int contentId = Integer.valueOf(getValue("contentid", eElement));
					int contentTypeId = Integer.valueOf(getValue("contenttypeid", eElement));
					contentTypeIdList.add(contentTypeId);
					contentIdList.add(contentId);

				} // if
					// System.out.println(contentIdList.get(i));
			} // for
				// arraylist 잘 들어갔는지 확인용: System.out.println(contentIdList.size());
			System.out.println("게시글 id 저장 성공");
			getThemeInfo(contentIdList, contentTypeIdList, themeList[i]); // 기본 정보 DB 등록 - 장소명, 테마, id

		} // url for
	}// test method

	// 테마 장소 불러오기 -> 장소 arrayList 가져와서 상세정보 insert(for문)
	public void getThemeInfo(ArrayList<Integer> contentIdList, ArrayList<Integer> contentTypeIdList, String theme)
			throws Exception {
		
		for (int i = 0; i < contentIdList.size(); i++) {
			urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/detailInfo1");
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(contentTypeIdList.get(i)), "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "="+ URLEncoder.encode(String.valueOf(contentIdList.get(i)), "UTF-8"));

			url(urlBuilder);

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(urlBuilder.toString());

			NodeList nList = doc.getElementsByTagName("item");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					int contentId = Integer.valueOf(getValue("subcontentid", eElement));
					String title = getValue("subname", eElement);

					PlaceDTO placeDTO = new PlaceDTO(contentId, title, theme);
					// DB 저장 확인용: System.out.println(placeDTO.contentId +" , "+contentTypeId);
				
					// DB 해당 content 존재하는지 확인(문제)
					if (placemapper.selectPlaceId(placeDTO.contentId) == 0 ) { //기존 데이터 없는경우
						System.out.println("기존데이터x");
						placemapper.insertThemeBasicInfo(placeDTO); // contentId, title, contentTypeId만 저장		
						getThemePlaceDetail(contentId); //기본정보 불러오기: 상세정보 불러오기 포함되어 있음
					} 					
					System.out.println("기존데이터o");
					getThemePlaceDetail(contentId); //기본정보 불러오기: 상세정보 불러오기 포함되어 있음
			} // if
			}// for
			//int placeContentId, int placeContentTypeId
		
		} // contentIdList for

	}// test method

	// 장소별 기본정보 불러오기**
	public void getThemePlaceDetail(int placeContentId) throws Exception {
		System.out.println(placeContentId );
		
			urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/detailCommon1");
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("test", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "="+ URLEncoder.encode(String.valueOf(placeContentId), "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("overviewYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); // 개요
			urlBuilder.append("&" + URLEncoder.encode("addrinfoYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); // 주소
			urlBuilder.append("&" + URLEncoder.encode("mapinfoYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); // 좌표
			urlBuilder.append("&" + URLEncoder.encode("areacodeYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); // 지역코드
			urlBuilder.append("&" + URLEncoder.encode("firstImageYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); // 대표이미지
			urlBuilder.append("&" + URLEncoder.encode("defaultYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); // 기본정보

			url(urlBuilder);

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(String.valueOf(urlBuilder));

			NodeList nodeList = doc.getElementsByTagName("item"); // xml 태그 이름 중 <item>인 부분을 전부 가져온다(NodeList 타입)
			
			if(nodeList.item(0)!=null) {
			Node node = nodeList.item(0); // 모든 <item> 태그 중 첫번째 node

			if (node.getNodeType() == Node.ELEMENT_NODE) { // 자식 노드가 요소일 경우에만 실행한다
				Element e = (Element) node;

				System.out.println(getValue("contentid", e));

				PlaceDTO placedto = new PlaceDTO();

				// null체크
				Optional<Integer> contentId = Optional.ofNullable(Integer.parseInt(getValue("contentid", e)));
				System.out.println("contentId: " + contentId.orElse(0));
				
				Optional<Integer> contenttypeid = Optional.ofNullable(Integer.parseInt(getValue("contenttypeid", e)));
				System.out.println("contenttypeid: " + contenttypeid.orElse(0));
				
				Optional<String> title = Optional.ofNullable(getValue("title", e));
				System.out.println("title: " + title.orElse("null"));
				Optional<Integer> areaCode = Optional.ofNullable(Integer.parseInt(getValue("areacode", e)));
				System.out.println("areaCode: " + areaCode.orElse(0));

				Optional<String> image1 = Optional.ofNullable(getValue("firstimage", e));
				System.out.println("image1: " + image1.orElse("null"));

				Optional<String> addr1 = Optional.ofNullable(getValue("addr1", e));
				System.out.println("addr1: " + addr1);

				Optional<String> addr2 = Optional.ofNullable(getValue("addr2", e));
				System.out.println("addr2: " + addr2);
				System.out.println("address: " + addr1.orElse("").concat(addr2.orElse("")));

				Optional<Double> mapx = Optional.ofNullable(Double.parseDouble(getValue("mapx", e)));
				System.out.println("mapx: " + mapx.orElse(0.0));

				Optional<Double> mapy = Optional.ofNullable(Double.parseDouble(getValue("mapy", e)));
				System.out.println("mapy: " + mapy.orElse(0.0));

				Optional<String> contents = Optional.ofNullable((getValue("overview", e)));
				System.out.println("contents: " + contents.orElse("null"));

				Optional<String> homepage = Optional.ofNullable((getValue("homepage", e)));
				System.out.println("homepage: " + homepage.orElse("null"));

				placedto.contentId = contentId.orElse(0);
				placedto.contentTypeId = contenttypeid.orElse(0);
				placedto.title = title.orElse("");
				placedto.areaCode = areaCode.orElse(0);
				placedto.image1 = image1.orElse("");
				placedto.address = addr1.orElse("").concat(addr2.orElse(""));
				placedto.mapx = mapx.orElse(0.0);
				placedto.mapy = mapy.orElse(0.0);
				placedto.contents = contents.orElse("");
				placedto.homepage = homepage.orElse("");

				placemapper.updateThemePlace(placedto);
				getThemePlaceDetailIntro(placedto.contentId, placedto.contentTypeId);
			}//if
			}
	}// method

	// 장소별 상세정보 불러오기
	public void getThemePlaceDetailIntro(int placeContentId, int placeContentTypeId)
			throws Exception {
	
		//for (int i = 0; i < placeContentIds.size(); i++) {

			urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/detailIntro1");
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("test", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "="+ URLEncoder.encode(String.valueOf(placeContentId), "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "="+ URLEncoder.encode(String.valueOf(placeContentTypeId), "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			url(urlBuilder);

			System.out.println(urlBuilder); //확인용
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(String.valueOf(urlBuilder));

			NodeList nodeList = doc.getElementsByTagName("item"); // xml 태그 이름 중 <item>인 부분을 전부 가져온다(NodeList 타입)

			if (nodeList.item(0) != null) {
				Node node = nodeList.item(0); // 모든 <item> 태그 중 첫번째 node

				if (node.getNodeType() == Node.ELEMENT_NODE) { // 자식 노드가 요소일 경우에만 실행한다
					Element e = (Element) node;

					if (placeContentTypeId == 12) {
						ContentsDTO contentsdto = getContentType_12(e);
						placemapper.insertTheme12Detail(contentsdto);
						System.out.println("12성공");
					}
					if (placeContentTypeId == 39) {
						ContentsDTO contentsdto = getContentType_39(e);
						placemapper.insertTheme39Detail(contentsdto);
						System.out.println("39성공");
					}
					if (placeContentTypeId == 14) {
						ContentsDTO contentsdto = getContentType_14(e);
						placemapper.insertTheme14Detail(contentsdto);
						System.out.println("14성공");
					}
					if (placeContentTypeId == 28) {
						ContentsDTO contentsdto = getContentType_28(e);
						placemapper.insertTheme28Detail(contentsdto);
						System.out.println("28성공");
					}
					else {
						System.out.println("contenttypeid:"+placeContentTypeId);
					}

				} // if문
			}
			//} // 바깥 for문
		
	}// method

	public void url(StringBuilder urlBuilder) throws Exception {
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");

		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		StringBuilder sb = new StringBuilder(); // 변경 가능한 문자열: StringBuilder
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		String result = sb.toString();
		System.out.println(result);
	}

	public ContentsDTO getContentType_14(Element e) { // 문화시설
		ContentsDTO Contentsdto = new ContentsDTO();

		Optional<Integer> contentId = Optional.ofNullable(Integer.parseInt(getValue("contentid", e)));
		System.out.println("contentId: " + contentId.orElse(0));

		Optional<String> accomcountculture = Optional.ofNullable(getValue("accomcountculture", e)); // 문의 및 안내
		System.out.println("accomcountculture: " + accomcountculture.orElse("null"));

		Optional<String> chkbabycarriageculture = Optional.ofNullable(getValue("chkbabycarriageculture", e)); // 유모차 대여
		System.out.println("chkbabycarriageculture(유모차대여): " + chkbabycarriageculture.orElse("null"));

		Optional<String> chkcreditcardculture = Optional.ofNullable(getValue("chkcreditcardculture", e));
		System.out.println("chkcreditcardculture: " + chkcreditcardculture.orElse("null"));

		Optional<String> chkpetculture = Optional.ofNullable(getValue("chkpetculture", e));
		System.out.println("chkpetculture: " + chkpetculture.orElse("null"));

		Optional<String> infocenterculture = Optional.ofNullable(getValue("infocenterculture", e));
		System.out.println("infocenterculture: " + infocenterculture.orElse("null"));

		Optional<String> parkingculture = Optional.ofNullable((getValue("parkingculture", e)));
		System.out.println("parkingculture: " + parkingculture.orElse("null"));

		Optional<String> parkingfee = Optional.ofNullable(getValue("parkingfee", e)); // 문의 및 안내
		System.out.println("parkingfee: " + parkingfee.orElse("null"));

		Optional<String> restdateculture = Optional.ofNullable(getValue("restdateculture", e)); // 유모차 대여
		System.out.println("restdateculture: " + restdateculture.orElse("null"));

		Optional<String> usefee = Optional.ofNullable(getValue("usefee", e));
		System.out.println("usefee: " + usefee.orElse("null"));

		Optional<String> discountinfo2 = Optional.ofNullable(getValue("discountinfo", e));
		System.out.println("discountinfo: " + discountinfo2.orElse("null"));

		Optional<String> usetimeculture = Optional.ofNullable(getValue("usetimeculture", e));
		System.out.println("usetimeculture: " + usetimeculture.orElse("null"));

		Contentsdto.contentId = contentId.orElse(0); //PK값 없으면 cannot add or update a child row 에러
		Contentsdto.accomcountculture = accomcountculture.orElse("");
		Contentsdto.chkbabycarriageculture = chkbabycarriageculture.orElse("");
		Contentsdto.chkcreditcardculture = chkcreditcardculture.orElse("");
		Contentsdto.chkpetculture = chkpetculture.orElse("");
		Contentsdto.infocenterculture = infocenterculture.orElse("");
		Contentsdto.parkingculture = parkingculture.orElse("");
		Contentsdto.parkingfee = parkingfee.orElse("");
		Contentsdto.restdateculture = restdateculture.orElse("");
		Contentsdto.usefee = usefee.orElse("");
		Contentsdto.discountinfo = discountinfo2.orElse("");
		Contentsdto.usetimeculture = usetimeculture.orElse("");

		return Contentsdto;
	}

	public ContentsDTO getContentType_28(Element e) { // 레포츠
		ContentsDTO Contentsdto = new ContentsDTO();
		
		// null체크
		Optional<Integer> contentId = Optional.ofNullable(Integer.parseInt(getValue("contentid", e)));
		System.out.println("contentId: " + contentId.orElse(0));

		Optional<String> infocenterleports = Optional.ofNullable(getValue("infocenterleports", e));
		System.out.println("infocenterleports: " + infocenterleports.orElse("null"));

		Optional<String> chkbabycarriageleports = Optional.ofNullable(getValue("chkbabycarriageleports", e));
		System.out.println("chkbabycarriageleports: " + chkbabycarriageleports.orElse("null"));

		Optional<String> chkcreditcardleports = Optional.ofNullable(getValue("chkcreditcardleports", e));
		System.out.println("chkcreditcardleports: " + chkcreditcardleports.orElse("null"));

		Optional<String> chkpetleports = Optional.ofNullable(getValue("chkpetleports", e));
		System.out.println("chkpetleports: " + chkpetleports.orElse("null"));

		Optional<String> restdateleports = Optional.ofNullable(getValue("restdateleports", e));
		System.out.println("restdateleports: " + restdateleports.orElse("null"));

		Optional<String> usetimeleports = Optional.ofNullable((getValue("usetimeleports", e)));
		System.out.println("usetimeleports: " + usetimeleports.orElse("null"));

		Optional<String> accomcountleports = Optional.ofNullable(getValue("accomcountleports", e));
		System.out.println("accomcountleports: " + accomcountleports.orElse("null"));

		Optional<String> openperiod = Optional.ofNullable(getValue("openperiod", e));
		System.out.println("openperiod: " + openperiod.orElse("null"));

		Optional<String> usefeeleports = Optional.ofNullable(getValue("usefeeleports", e));
		System.out.println("usefeeleports: " + usefeeleports.orElse("null"));

		Optional<String> parkingleports = Optional.ofNullable(getValue("parkingleports", e));
		System.out.println("parkingleports: " + parkingleports.orElse("null"));

		Optional<String> parkingfeeleports = Optional.ofNullable((getValue("parkingfeeleports", e)));
		System.out.println("parkingfeeleports: " + parkingfeeleports.orElse("null"));

		Optional<String> reservation = Optional.ofNullable(getValue("reservation", e));
		System.out.println("reservation: " + reservation.orElse("null"));

		Contentsdto.contentId = contentId.orElse(0);
		Contentsdto.infocenterleports = infocenterleports.orElse("");
		Contentsdto.chkbabycarriageleports = chkbabycarriageleports.orElse("");
		Contentsdto.chkcreditcardleports = chkcreditcardleports.orElse("");
		Contentsdto.chkpetleports = chkpetleports.orElse("");
		Contentsdto.restdateleports = restdateleports.orElse("");
		Contentsdto.usetimeleports = usetimeleports.orElse("");
		Contentsdto.accomcountleports = accomcountleports.orElse("");
		Contentsdto.openperiod = openperiod.orElse("");
		Contentsdto.usefeeleports = usefeeleports.orElse("");
		Contentsdto.parkingfeeleports = parkingfeeleports.orElse("");
		Contentsdto.reservation = reservation.orElse("");

		return Contentsdto;
	}

	public ContentsDTO getContentType_12(Element e) { // 관광지
		ContentsDTO Contentsdto = new ContentsDTO();
		// null체크
		Optional<Integer> contentId = Optional.ofNullable(Integer.parseInt(getValue("contentid", e)));
		System.out.println("contentId: " + contentId.orElse(0));

		Optional<String> infocenter = Optional.ofNullable(getValue("infocenter", e)); // 문의 및 안내
		System.out.println("infocenter: " + infocenter.orElse("null"));

		Optional<String> chkbabycarriage = Optional.ofNullable(getValue("chkbabycarriage", e)); // 유모차 대여
		System.out.println("chkbabycarriage(유모차대여): " + chkbabycarriage.orElse("null"));

		Optional<String> chkpet = Optional.ofNullable(getValue("chkpet", e));
		System.out.println("chkpet: " + chkpet.orElse("null"));

		Optional<String> chkcreditcard = Optional.ofNullable(getValue("chkcreditcard", e));
		System.out.println("chkcreditcard: " + chkpet.orElse("null"));

		Optional<String> restdate = Optional.ofNullable(getValue("restdate", e));
		System.out.println("restdate: " + restdate.orElse("null"));

		Optional<String> usetime = Optional.ofNullable((getValue("usetime", e)));
		System.out.println("usetime: " + usetime.orElse("null"));

		Contentsdto.contentId = contentId.orElse(0);
		Contentsdto.infocenter = infocenter.orElse("");
		Contentsdto.chkbabycarriage = chkbabycarriage.orElse("");
		Contentsdto.chkcreditcard = chkcreditcard.orElse("");
		Contentsdto.chkpet = chkpet.orElse("");
		Contentsdto.restdate = restdate.orElse("");
		Contentsdto.usetime = usetime.orElse("");

		return Contentsdto;
	}

	public ContentsDTO getContentType_39(Element e) { // 식당
		ContentsDTO Contentsdto = new ContentsDTO();

		// null체크
		Optional<Integer> contentId = Optional.ofNullable(Integer.parseInt(getValue("contentid", e)));
		System.out.println("contentId: " + contentId.orElse(0));

		Optional<String> infocenterfood = Optional.ofNullable(getValue("infocenterfood", e)); // 문의 및 안내
		System.out.println("infocenterfood: " + infocenterfood.orElse("null"));

		Optional<String> chkcreditcardfood = Optional.ofNullable(getValue("chkcreditcardfood", e)); // 신용카드
		System.out.println("chkcreditcardfood: " + chkcreditcardfood.orElse("null"));

		Optional<String> discountinfofood = Optional.ofNullable(getValue("discountinfofood", e)); // 할인정보
		System.out.println("discountinfofood: " + discountinfofood.orElse("null"));

		Optional<String> firstmenu = Optional.ofNullable(getValue("firstmenu", e)); // 대표메뉴
		System.out.println("firstmenu: " + firstmenu.orElse("null"));

		Optional<String> reservationfood = Optional.ofNullable(getValue("reservationfood", e)); // 예약안내
		System.out.println("reservationfood: " + reservationfood.orElse("null"));

		Optional<String> restdatefood = Optional.ofNullable((getValue("restdatefood", e)));// 쉬는날
		System.out.println("restdatefood: " + restdatefood.orElse("null"));

		Optional<String> packing = Optional.ofNullable(getValue("packing", e)); // 포장가능
		System.out.println("packing: " + packing.orElse("null"));

		Optional<String> parkingfood = Optional.ofNullable(getValue("parkingfood", e)); // 주차시설
		System.out.println("parkingfood: " + parkingfood.orElse("null"));

		Optional<String> kidsfacility = Optional.ofNullable((getValue("kidsfacility", e)));// 어린이놀이방여부
		System.out.println("kidsfacility: " + kidsfacility.orElse("null"));

		Contentsdto.contentId = contentId.orElse(0);
		Contentsdto.infocenterfood = infocenterfood.orElse("");
		Contentsdto.chkcreditcardfood = chkcreditcardfood.orElse("");
		Contentsdto.discountinfofood = discountinfofood.orElse("");
		Contentsdto.firstmenu = firstmenu.orElse("");
		Contentsdto.reservationfood = reservationfood.orElse("");
		Contentsdto.restdatefood = restdatefood.orElse("");
		Contentsdto.packing = packing.orElse("");
		Contentsdto.parkingfood = parkingfood.orElse("");
		Contentsdto.kidsfacility = kidsfacility.orElse("");

		return Contentsdto;
	}
	
	private static String getValue(String tag, Element element) {
		// 태그 이름이 매개변수인 노드를 찾아 > 찾은 노드에서 n번째 노드에 접근 > n번째 노드 안에 정보에 접근할 수 있는 nodelist
		String result = "null";

		if (element.getElementsByTagName(tag).item(0).getChildNodes() == null) { // null일때
		} else { // not null
			NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
			// Node node = (Node) nodes.item(0);
			if ((Node) nodes.item(0) == null) {
			} else {
				result = ((Node) nodes.item(0)).getNodeValue();
			}
		}
		return result;
	}

}// class
