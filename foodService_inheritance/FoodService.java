package foodService_inheritance;

import java.util.ArrayList;
import java.util.List;

public class FoodService {	
	
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

	public void info() {
		System.out.print("영업일 운영시간 : "+this.startTime+"~"+this.endTime+" /");
		ableDelivery();		
		System.out.print("휴일 : ");
		for(int i =0;i<closeDay.length;i++) {
			System.out.print(closeDay[i]+" ");			
		}
		System.out.println();
		ableDelivery();
	}
	public void ableDelivery() {
		if(this.delivery) {
			System.out.println("배달가능여부 : 배달 가능");
		} else {
			System.out.println("배달가능여부 : 서비스 안함");
		}
	}	
	
	public List<FoodService> makeFoodServiceList() {
		List<FoodService> listAll = new ArrayList<>();
		//가게들을 만들어서 list에 넣어 반환********************
		return listAll;
	
	}	
	
	//매개변수 ArrayList foundList는 안되고 ArrayList<FoodService> foundList여야 하는이유
	//제네릭 타입의 명시성과 타입 안정성?
	
	//java.lang.ClassCastException: -> instanceof함수로 해결**********
	//<FoodService> listAll에 Cafe로만 캐스팅할 수있는,Restaurant으로만 캐스팅할 수있는 객체들이 섞여있어서 나눠줘야함..
	static void searchCategory(String categ,ArrayList<FoodService> listAll,ArrayList<FoodService> foundList) {
		//종류늘어나면 추가가능(switch문)
		switch(categ) {
		case "카페":
			
			for(FoodService store : listAll) {
				//System.out.println(store instanceof Cafe);*************
				if(store instanceof Cafe) {
					Cafe cafe = (Cafe)store;
					if(cafe.category.equals("카페")) {
						foundList.add(store);
					}
				}						
			}		
			break;
		case "식당":
			for(FoodService store : listAll) {
				if(store instanceof Restaurant) {
					Restaurant restaurant = (Restaurant)store;
					if(restaurant.category.equals("식당")) {
						foundList.add(store);
					}
				}							
			}		
			break;	
		}	
	}
	//중복되는 코드 많은뎅 어케하ㅈ???????
	static ArrayList<FoodService> isMenu(String menu,ArrayList<FoodService> foundList){
		//에러남ㅎㅎㅎㅎㅎㅎ****************************************************************		
		for(int i = 0; i < foundList.size();i++) {
			FoodService store =	foundList.get(i);	
			if( store instanceof Korean) {
				Korean korean =(Korean)store;
				String menus[] = korean.getMenu();
				for(String item: menus) {
					if(item.equals(menu) == false) {
						foundList.remove(i--); //삭제되면 index도 줄어서 ... java.lang.IndexOutOfBoundsException:
						
					}
				}
			}
			if( store instanceof Book) {
				Korean korean =(Korean)store;
				String menus[] = korean.getMenu();
				for(String item: menus) {
					if(item.equals(menu) == false) {
						foundList.remove(i--);
					}
				}
			}
			
		}
		return foundList;		
	}
	//*************************************************************************************
	static void printRes(ArrayList<FoodService> foundList) {
		//foreach문으로 안되네 
		//for(Korean k :foundList) {} ->타입일치 for(FoodService k :foundList) {}
		
		for(FoodService k :foundList) {
			if( k instanceof Korean) {
				Korean kk = (Korean)k;
				kk.info();
			}
			if( k instanceof Book) {
				Korean kk = (Korean)k;
				kk.info();
			}
			
		}					
	}	
		
		
		
		
		
		
	}
}
