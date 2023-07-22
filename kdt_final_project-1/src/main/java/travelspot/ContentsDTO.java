package travelspot;

import org.springframework.stereotype.Component;

@Component
public class ContentsDTO {
	int contentId;
	
	/*관광지=12*/
	String infocenter; //문의 및 안내
	String chkbabycarriage; //유모차 대여
	String chkcreditcard; //신용카드
	String chkpet;	//애완동물
	String restdate; //쉬는날
	String usetime; //이용시간

	/*식당=39*/
	String infocenterfood; //문의 및 안내
	String kidsfacility; //어린이놀이방여부
	String chkcreditcardfood; //신용카드 가능정보
	String restdatefood; //쉬는날
	String opentimefood; //오픈시간
	String discountinfofood; //할인정보
	String firstmenu; //대표메뉴
	String reservationfood; //예약안내
	String packing; //포장가능
	String parkingfood; //주차시설
	
	/*문화시설=14*/
	String infocenterculture; //문의 및 안내
	String chkbabycarriageculture; //유모차대여
	String chkcreditcardculture; //신용카드가능정보
	String chkpetculture; //애완동물동반 가능정보
	String accomcountculture; //수용인원
	String parkingculture; //주차시설
	String parkingfee; //주차요금
	String restdateculture; //쉬는날
	String usefee; //이용요금
	String discountinfo; //할인정보
	String usetimeculture; //이용시간
	
	/*contentTypeId=28 (레포츠)	*/
	String infocenterleports; //문의 및 안내
	String chkbabycarriageleports; //유모차대여정보
	String chkcreditcardleports; //신용카드
	String chkpetleports; //애완동물
	String restdateleports; //쉬는날
	String usetimeleports;//이용시간
	String accomcountleports; //수용인원
	String openperiod; //개장기간
	String usefeeleports;//입장료
	String parkingleports; //주차시설
	String parkingfeeleports; //주차요금
	String reservation; //예약안내
	
	public ContentsDTO() {	}

	//관광지 정보
	public ContentsDTO(int contentId, String infocenter, String chkbabycarriage, String chkcreditcard,
			String chkpet, String restdate, String usetime) {
		this.contentId = contentId;
		this.infocenter = infocenter;
		this.chkbabycarriage = chkbabycarriage;
		this.chkcreditcard = chkcreditcard;
		this.chkpet = chkpet;
		this.restdate = restdate;
		this.usetime = usetime;
	}

	//식당 정보
	public ContentsDTO(int contentId, String infocenterfood, String chkcreditcardfood, String discountinfofood,
			String firstmenu, String reservationfood, String restdatefood, String packing, String parkingfood,
			String kidsfacility, String opentimefood) {
		this.contentId = contentId;
		this.infocenterfood = infocenterfood;
		this.chkcreditcardfood = chkcreditcardfood;
		this.discountinfofood = discountinfofood;
		this.firstmenu = firstmenu;
		this.reservationfood = reservationfood;
		this.restdatefood = restdatefood;
		this.packing = packing;
		this.parkingfood = parkingfood;
		this.kidsfacility = kidsfacility;
		this.opentimefood = opentimefood;
	}

	//문화시설 정보
	public ContentsDTO(int contentId, String accomcountculture, String chkbabycarriageculture,
			String chkcreditcardculture, String chkpetculture, String infocenterculture, String parkingculture,
			String parkingfee, String restdateculture, String usefee, String discountinfo, String usetimeculture) {
		this.contentId = contentId;
		this.accomcountculture = accomcountculture;
		this.chkbabycarriageculture = chkbabycarriageculture;
		this.chkcreditcardculture = chkcreditcardculture;
		this.chkpetculture = chkpetculture;
		this.infocenterculture = infocenterculture;
		this.parkingculture = parkingculture;
		this.parkingfee = parkingfee;
		this.restdateculture = restdateculture;
		this.usefee = usefee;
		this.discountinfo = discountinfo;
		this.usetimeculture = usetimeculture;
	}

	//레포츠시설 정보
	public ContentsDTO(int contentId, String infocenterleports, String chkbabycarriageleports,
			String chkcreditcardleports, String chkpetleports, String restdateleports, String usetimeleports,
			String accomcountleports, String openperiod, String usefeeleports, String parkingleports,
			String parkingfeeleports, String reservation) {
		this.contentId = contentId;
		this.infocenterleports = infocenterleports;
		this.chkbabycarriageleports = chkbabycarriageleports;
		this.chkcreditcardleports = chkcreditcardleports;
		this.chkpetleports = chkpetleports;
		this.restdateleports = restdateleports;
		this.usetimeleports = usetimeleports;
		this.accomcountleports = accomcountleports;
		this.openperiod = openperiod;
		this.usefeeleports = usefeeleports;
		this.parkingleports = parkingleports;
		this.parkingfeeleports = parkingfeeleports;
		this.reservation = reservation;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getInfocenter() {
		return infocenter;
	}

	public void setInfocenter(String infocenter) {
		this.infocenter = infocenter;
	}

	public String getChkbabycarriage() {
		return chkbabycarriage;
	}

	public void setChkbabycarriage(String chkbabycarriage) {
		this.chkbabycarriage = chkbabycarriage;
	}

	public String getChkcreditcard() {
		return chkcreditcard;
	}

	public void setChkcreditcard(String chkcreditcard) {
		this.chkcreditcard = chkcreditcard;
	}

	public String getChkpet() {
		return chkpet;
	}

	public void setChkpet(String chkpet) {
		this.chkpet = chkpet;
	}

	public String getRestdate() {
		return restdate;
	}

	public void setRestdate(String restdate) {
		this.restdate = restdate;
	}

	public String getUsetime() {
		return usetime;
	}

	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}

	public String getInfocenterfood() {
		return infocenterfood;
	}

	public void setInfocenterfood(String infocenterfood) {
		this.infocenterfood = infocenterfood;
	}

	public String getKidsfacility() {
		return kidsfacility;
	}

	public void setKidsfacility(String kidsfacility) {
		this.kidsfacility = kidsfacility;
	}

	public String getChkcreditcardfood() {
		return chkcreditcardfood;
	}

	public void setChkcreditcardfood(String chkcreditcardfood) {
		this.chkcreditcardfood = chkcreditcardfood;
	}

	public String getRestdatefood() {
		return restdatefood;
	}

	public void setRestdatefood(String restdatefood) {
		this.restdatefood = restdatefood;
	}

	public String getOpentimefood() {
		return opentimefood;
	}

	public void setOpentimefood(String opentimefood) {
		this.opentimefood = opentimefood;
	}

	public String getDiscountinfofood() {
		return discountinfofood;
	}

	public void setDiscountinfofood(String discountinfofood) {
		this.discountinfofood = discountinfofood;
	}

	public String getFirstmenu() {
		return firstmenu;
	}

	public void setFirstmenu(String firstmenu) {
		this.firstmenu = firstmenu;
	}

	public String getReservationfood() {
		return reservationfood;
	}

	public void setReservationfood(String reservationfood) {
		this.reservationfood = reservationfood;
	}

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	public String getParkingfood() {
		return parkingfood;
	}

	public void setParkingfood(String parkingfood) {
		this.parkingfood = parkingfood;
	}

	public String getInfocenterculture() {
		return infocenterculture;
	}

	public void setInfocenterculture(String infocenterculture) {
		this.infocenterculture = infocenterculture;
	}

	public String getChkbabycarriageculture() {
		return chkbabycarriageculture;
	}

	public void setChkbabycarriageculture(String chkbabycarriageculture) {
		this.chkbabycarriageculture = chkbabycarriageculture;
	}

	public String getChkcreditcardculture() {
		return chkcreditcardculture;
	}

	public void setChkcreditcardculture(String chkcreditcardculture) {
		this.chkcreditcardculture = chkcreditcardculture;
	}

	public String getChkpetculture() {
		return chkpetculture;
	}

	public void setChkpetculture(String chkpetculture) {
		this.chkpetculture = chkpetculture;
	}

	public String getAccomcountculture() {
		return accomcountculture;
	}

	public void setAccomcountculture(String accomcountculture) {
		this.accomcountculture = accomcountculture;
	}

	public String getParkingculture() {
		return parkingculture;
	}

	public void setParkingculture(String parkingculture) {
		this.parkingculture = parkingculture;
	}

	public String getParkingfee() {
		return parkingfee;
	}

	public void setParkingfee(String parkingfee) {
		this.parkingfee = parkingfee;
	}

	public String getRestdateculture() {
		return restdateculture;
	}

	public void setRestdateculture(String restdateculture) {
		this.restdateculture = restdateculture;
	}

	public String getUsefee() {
		return usefee;
	}

	public void setUsefee(String usefee) {
		this.usefee = usefee;
	}

	public String getDiscountinfo() {
		return discountinfo;
	}

	public void setDiscountinfo(String discountinfo) {
		this.discountinfo = discountinfo;
	}

	public String getUsetimeculture() {
		return usetimeculture;
	}

	public void setUsetimeculture(String usetimeculture) {
		this.usetimeculture = usetimeculture;
	}

	public String getInfocenterleports() {
		return infocenterleports;
	}

	public void setInfocenterleports(String infocenterleports) {
		this.infocenterleports = infocenterleports;
	}

	public String getChkbabycarriageleports() {
		return chkbabycarriageleports;
	}

	public void setChkbabycarriageleports(String chkbabycarriageleports) {
		this.chkbabycarriageleports = chkbabycarriageleports;
	}

	public String getChkcreditcardleports() {
		return chkcreditcardleports;
	}

	public void setChkcreditcardleports(String chkcreditcardleports) {
		this.chkcreditcardleports = chkcreditcardleports;
	}

	public String getChkpetleports() {
		return chkpetleports;
	}

	public void setChkpetleports(String chkpetleports) {
		this.chkpetleports = chkpetleports;
	}

	public String getRestdateleports() {
		return restdateleports;
	}

	public void setRestdateleports(String restdateleports) {
		this.restdateleports = restdateleports;
	}

	public String getUsetimeleports() {
		return usetimeleports;
	}

	public void setUsetimeleports(String usetimeleports) {
		this.usetimeleports = usetimeleports;
	}

	public String getAccomcountleports() {
		return accomcountleports;
	}

	public void setAccomcountleports(String accomcountleports) {
		this.accomcountleports = accomcountleports;
	}

	public String getOpenperiod() {
		return openperiod;
	}

	public void setOpenperiod(String openperiod) {
		this.openperiod = openperiod;
	}

	public String getUsefeeleports() {
		return usefeeleports;
	}

	public void setUsefeeleports(String usefeeleports) {
		this.usefeeleports = usefeeleports;
	}

	public String getParkingleports() {
		return parkingleports;
	}

	public void setParkingleports(String parkingleports) {
		this.parkingleports = parkingleports;
	}

	public String getParkingfeeleports() {
		return parkingfeeleports;
	}

	public void setParkingfeeleports(String parkingfeeleports) {
		this.parkingfeeleports = parkingfeeleports;
	}

	public String getReservation() {
		return reservation;
	}

	public void setReservation(String reservation) {
		this.reservation = reservation;
	}

	

	
	
}