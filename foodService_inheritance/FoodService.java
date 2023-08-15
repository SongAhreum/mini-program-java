package foodService_inheritance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class FoodService {	
	public static Scanner sc = new Scanner(System.in);
	public boolean delivery;
	public String[] closeDay;	
	public String startTime;
	public String endTime;
	
	//생성자
	public FoodService(){
		defaultSetting();
	}
	
	public FoodService(FoodService foodService) {
		this.delivery = foodService.delivery;
		this.closeDay=foodService.closeDay;
		this.startTime=foodService.startTime;
		this.endTime=foodService.endTime;
		
	}
	public FoodService(Restaurant restaurant) {
		this.delivery = restaurant.delivery;
		this.closeDay=restaurant.closeDay;
		this.startTime=restaurant.startTime;
		this.endTime=restaurant.endTime;		
	}
	public FoodService(Korean korean) {
		this.delivery = korean.delivery;
		this.closeDay=korean.closeDay;
		this.startTime=korean.startTime;
		this.endTime=korean.endTime;		
	}
	public FoodService(boolean delivery,String closeDay,String startTime,String endTime){
		this.delivery = delivery;
		this.closeDay[0]=closeDay;
		this.startTime=startTime;
		this.endTime=endTime;
	}
	public FoodService(boolean delivery,String[] closeDay,String startTime,String endTime){
		this.delivery = delivery;
		this.closeDay=closeDay;
		this.startTime=startTime;
		this.endTime=endTime;
	}
	//getter setter
	public String[] getCloseDay() {
		return closeDay;
	}

	public void setCloseDay(String[] closeDay) {
		this.closeDay = closeDay;
	}
	public void setCloseDay(String closeDay) {
		this.closeDay[0] = closeDay;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	//메서드
	public void defaultSetting() {
		startTime="9:00";
		endTime="18:00";
		closeDay= new String[2];	
		closeDay[0]="Saturday";
		closeDay[1]="Sunday";
	}

	@Override //함해봄
	public String toString() {
		return "영업일 운영시간 : "+this.startTime+"~"+this.endTime+" / 휴일 : "+closeDay()+"\n"+ableDelivery();
	}
	public String ableDelivery() {
		if(this.delivery) {
			return "배달가능여부 : 배달 가능";
		} else {
			return "배달가능여부 : 서비스 안함";
		}
	}
	public String closeDay() {
		String result = "";
		for(int i =0;i<closeDay.length;i++) {
			result += closeDay[i]+" ";			
		}
		return result;
	}
	
	//서비스MAIN
	void service(ModelFoodService foodServices) {
		System.out.println("*** 안녕하세요 ***");
		System.out.println("찾으시는 서비스를 선택해 주세요");
		System.out.println("1. 검색 2. 예약(예약서비스는 식당만 지원합니다.) 3.리뷰남기기");		
		int n = sc.nextInt();
		while(true) {
			if(n==1||n==2) {
				break;				
			}
			System.out.println("다시입력해주세요");
			n = sc.nextInt();
		}	
		switch(n) {
		case 1:
			search(foodServices);
			break;
		case 2:
			//reservation();
			break;
		case 3:
			//review(); customer방문리스트에잇다면?작성가능?
			break;
			
		}	
	}
	//1.검색 ===========================================================================
	void search(ModelFoodService foodServices) {
		//1) 요식업 카테고리 선택
		System.out.println("번호를 입력해주세요");
		System.out.print("[");
		for(int i = 0;i < foodServices.categorys.length; i++) {
			System.out.print(i+1+". "+foodServices.categorys[i][0]+" ");
		}
		System.out.println("]");
		int categorysId = sc.nextInt();//
				
		//2)검색옵션선택
		optionSearch(foodServices,categorysId);
	}
	void optionSearch(ModelFoodService foodServices,int categorysId) {
		System.out.println("검색옵션");		
		System.out.println("1.카테고리 2.카테고리,평점 필터 3.평점 필터 4.메뉴");		
		int n = sc.nextInt();
		while(true) {
			switch(n) {
			case 1:					
				int storeTypesId = selectStoreType(foodServices,categorysId);	
					
				for(int i = 0;i < foodServices.names.length;i++) {//전체범위탐색 i를인덱스로씀
					if(categorysId == foodServices.categorysId[i]) {
						if(storeTypesId == foodServices.storeTypesId[i]) {						
							viewList(foodServices,i);
						}				
					}
					System.out.println("==============================");
				}
				System.out.println("세부정보를 열람하고싶은 가게고유번호를 입력하세요");
				int idx1 = sc.nextInt();
				veiwDetail(foodServices,idx1);
				//veiwReview?
				int e = endPoint(foodServices,categorysId);
				if(e == 0) {
					continue;
				} else {
					break;
				}
			case 2:
				int storeTypesId2 = selectStoreType(foodServices,categorysId);
				
				System.out.println("번호를 입력하세요");
				System.out.println("1.평점 4.5이상 2.평점 4.0이상 3.평점 3.0이상 4.평점3.0이상");
				int grade2 = sc.nextInt();
				
				for(int i = 0;i < foodServices.names.length;i++) {//전체범위탐색 i를인덱스로씀
					if(categorysId == foodServices.categorysId[i]) {
						if(storeTypesId2 == foodServices.storeTypesId[i]) {
							viewListByGrade(foodServices,i,grade2);
						}
					}				
					System.out.println("==============================");
				}
			
				System.out.println("세부정보를 열람하고싶은 가게고유번호를 입력하세요");
				int idx2 = sc.nextInt();
				veiwDetail(foodServices,idx2);
				
				
				int e2 = endPoint(foodServices,categorysId);
				if(e2 == 0) {
					continue;
				} else {
					break;
				}
		
			case 3:
				System.out.println("1.평점 4.5이상 2.평점 4.0이상 3.평점 3.5이상 4.평점3.0이상");
				int grade3 = sc.nextInt();
				for(int i = 0;i < foodServices.names.length;i++) {//전체범위탐색 i를인덱스로씀
					viewListByGrade(foodServices,i,grade3);
					System.out.println("==============================");
				}
				System.out.println("세부정보를 열람하고싶은 가게고유번호를 입력하세요");
				int idx3 = sc.nextInt();
				veiwDetail(foodServices,idx3);
				
				
				int e3 = endPoint(foodServices,categorysId);
				if(e3 == 0) {
					continue;
				} else {
					break;
				}
				
			case 4:
				System.out.println("검색하고 싶은 메뉴를 입력하여 주세요");
				String menu = sc.next();
				searchMenu(foodServices,menu);
				
				System.out.println("세부정보를 열람하고싶은 가게고유번호를 입력하세요");
				int idx4 = sc.nextInt();
				veiwDetail(foodServices,idx4);
				
				int e4 = endPoint(foodServices,categorysId);
				if(e4 == 0) {
					continue;
				} else {
					break;
				}
			
			}
		}
		
	}
	int selectStoreType(ModelFoodService foodServices, int categorysId) {
		System.out.println("번호를 입력하세요");
		for(int i = 1;i < foodServices.categorys[categorysId-1].length;i++) {
			System.out.print(i+". "+foodServices.categorys[categorysId-1][i]);
		}
//		int storeTypesId = sc.nextInt();
		return sc.nextInt();
	}
	
	void viewList(ModelFoodService foodServices,int i) {
		
		System.out.print("가게명: "+foodServices.names[i]+" **가게고유번호 : "+i);
		System.out.println("평점:"+foodServices.grade[i] );
		System.out.println("=========메뉴=========");
		viewMenu(foodServices,i);
	}
	void viewMenu(ModelFoodService foodServices,int i) {
		for(int j =1;j < foodServices.menus.length;j++) {
			if(foodServices.storeId[i] == i) {
				System.out.println(foodServices.menus[j]+ " : "+foodServices.prices[j]+"원");
			}
		}	
	}
	void viewListByGrade(ModelFoodService foodServices,int i,int grade) {
		if(grade == 1 && foodServices.grade[i] >= 4.5) {
			viewList(foodServices,i);
		}
		if(grade == 2 && foodServices.grade[i] >= 4.0) {
			viewList(foodServices,i);			
		}
		if(grade == 3 && foodServices.grade[i] >= 3.5) {
			viewList(foodServices,i);
		}
		if(grade == 4 && foodServices.grade[i] >= 3.0) {
			viewList(foodServices,i);
		}	
		
	}
	
	void searchMenu(ModelFoodService foodServices,String menu) {
		int cnt = 0;
		for(int i =0;i< foodServices.menus.length;i++) {
			if(foodServices.menus[i].equals(menu)) {
				int n = foodServices.storeId[i];
				viewList(foodServices,n);
				cnt++;
			}		
		}
		if(cnt == 0) {
			System.out.println("검색한메뉴를 파는 식당이 없습니다.");
		}
	}
	String albeString(boolean b) {
		if(b) {
			return "가능";
		} else {
			return "서비스안함";
		}
	}
	String checkYN() {
		String input = sc.next();
		while(true) {
			if(input.equals("y")||input.equals("n")) {
				return input;
			}
			System.out.println("다시입력하세요");
			input = sc.next();
		}
	}
	void veiwDetail(ModelFoodService foodServices,int idx) {
		
		System.out.println(foodServices.names[idx]+"에 오신걸 환영합니다. **평점 :"+foodServices.grade[idx]+"**");
		System.out.print("영업일 운영시간 : "+foodServices.startTimes[idx]+"~"+foodServices.endTimes[idx]+" /");		
		System.out.println("휴무일 : "+foodServices.closeDays[idx]);		
		boolean delivery = foodServices.deliverys[idx];
		System.out.print("배달가능 여부: "+albeString(delivery));		
		boolean reservation=foodServices.reservations[idx];
		System.out.print("예약가능 여부: "+albeString(reservation));
		System.out.println("=====메뉴======");
		viewMenu(foodServices,idx);
		System.out.println("=============");
		System.out.println();
		System.out.println("해당 가게의 리뷰을 열람하시겠습니까? y/n");		
		String input = checkYN();
		if(input.equals("y")) {
			viewReview(foodServices,idx);
		} else {
			return;
		}
	}
	void viewReview(ModelFoodService foodServices,int idx) {
		for(int i =0;i<foodServices.reviews.length;i++) {
			if(foodServices.storeIdByReview[i] == idx) {
				System.out.println("평점: "+foodServices.reviewPoints[i]);
				System.out.println("리뷰내용: "+foodServices.reviews[i] );
				System.out.println("추천메뉴 : "+foodServices.recommendMenus[i]);
				System.out.println(foodServices.recommendComment[i]);
			}
		}
	}
	int endPoint(ModelFoodService foodServices, int categorysId) {
		System.out.println("뒤로가기는 0을 입력해주세요 : 1.처음으로돌아가기 2.다시검색하기 3.검색옵션 다시선택하기 ");
		int go = sc.nextInt();
		while(true) {
			if(go == 1 || go == 4 || go == 3 ||go == 0) {
				break;
			}
			System.out.println("뒤로가기는 0을 입력해주세요 : 1.검색옵션 다시선택하기 2.검색 처음으로 돌아가기 3.서비스처음으로 돌아가기  ");
			go = sc.nextInt();
		}
		if(go == 0) {
			return 0;
			
		} else if(go == 1) {
			optionSearch(foodServices,categorysId);
			
			return 1;
			
		} else if(go == 2) {
			search(foodServices);
			return 1;
		} else if(go == 3) {
			service(foodServices);
			return 1;
		} else {
			return 1;
		}
	}
}