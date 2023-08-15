package foodService_inheritance;

public class ModelFoodService {
	public String[][] categorys; //카테고리구조

	public int categorysId[]; //식당 :1 {"한식","양식","중식"} ,카페:2{"프랜차이즈","북","브런치"}
	public int storeTypesId[];	//음식점 종류 id
	
	
	public String names[];
	public boolean deliverys[];
	public String closeDays[];	
	public String startTimes[];
	public String endTimes[];
	public boolean reservations[];
	public int seat[];
	public int points[];
	public int counts[];
	public double grade[]; 
	

	public int storeId[];
	public String menus[];//,로 구분? List에담으면 인덱스관리어려우니까?
	public String prices[];
	
	public int storeIdByReview[];
	public String reviews[];
	public String recommendMenus[];
	public String recommendComment[];

	public int[] reviewPoints;
	

	ModelFoodService(){
		this.categorys = new String[][] {{"식당","한식","양식","중식"},{"카페","프랜차이즈","북","브런치"}};	
		this.categorysId = new int[] { //String 비교하는것보다 숫자로비교하는게 빠르니까...
				1,
				1,
				2
		};
		this.storeTypesId = new int[] { //String 비교하는것보다 숫자로비교하는게 빠르니까...
				1,
				3,
				1
		};
		
		this.names = new String[] {
				"국밥조아",
				"아리랑",
				"이디야"		
		};
		
		this.deliverys = new boolean[] {
				true,
				false,
				true
		};
		this.closeDays = new String[] {
				"N",
				"wednesday",
				"N"			
		};
		this.startTimes = new String[] {
				"00:00",
				"11:00",
				"9:00"			
		};
		this.endTimes = new String[] {
				"00:00",
				"20:00",
				"22:00"			
		};
		this.reservations = new boolean[] {
				true,
				true,
				false			
		};
		this.seat = new int[] { 
				20,
				50,
				25			
		};
		this.points = new int[] { 
				48,
				50,
				30			
		};
		this.counts = new int[] { 
				10,
				10,
				10				
		};
		this.grade = setGrade();
		
	
		this.storeId = new int[] { //ㅎㅐ당단일가게 인덱스
				0,
				0,
				0,
				1,
				1,
				1,
				2,
				2
		};
		this.menus = new String[] { 
				"돼지국밥","내장국밥","순대국밥",
				"짜장면","짬뽕","지삼선",
				"아메리카노","라떼"			
		};
		this.prices = new String[] {
				"8000,8500,8500",
				"7500,8000,12000",
				"3500,4500"			
		};
		this.storeIdByReview = new int [] { 
				1,
				1,
				2			
		};
		this.reviewPoints = new int[] { 
				5,
				4,
				3
		};
		
		this.reviews = new String[] { 
				"맛있으면짖는개: 멈오암망뫙",
				"깔끔해요",
				"사장님이 고량주를선물로 주셨어요"			
		};
		this.recommendMenus = new String[] { 
				"내장국밥",
				"돼지국밥",
				"지삼선"				
		};
		this.recommendComment = new String[] { 
				"존맛탱",
				"고기많음",
				"안먹어본자는있어도 한번만 먹는사람은없다"			
		};
				
	}
	double[] setGrade() {
		double grade[] = new double[counts.length];
		for(int i = 0;i<counts.length;i++) {
			grade[i] = (double)points[i]/counts[i];
		}
		return grade;
	}

}
