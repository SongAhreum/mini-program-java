package foodService_inheritance;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		
		
		FoodService foodService= new FoodService(false,"Sunday","9:00","20:00");
		Cafe cafe = new Cafe(foodService,1000,100);
		Book bookcafe = new Book( foodService,cafe,"꿈과책과힘과벽","시간권",2000);

		FoodService foodService2= new FoodService(false,"Sunday","9:00","22:00");
		Restaurant restaurant = new Restaurant(foodService2,true,5000,5500);
		Korean kuuukbob = new Korean(restaurant,"국밥조아","국밥",8000);
		
		
		FoodService foodService3= new FoodService(false,new String[]{"Sunday","wednesday"},"9:00","20:00");
		Restaurant restaurant2 = new Restaurant(foodService3,true,10,2);
		Korean bibimbbobb = new Korean(restaurant2,"비빔밥조아",new String[]{"소고기돌솥비빔밥","치즈돌솥비빔밥"},new int[]{10000,9500});
		
		
		bookcafe.info();
//		bookcafe.setCloseDay("wednesday");
//		bookcafe.setStartTime("11:00");
//		bookcafe.setEndTime("24:00");
//		bookcafe.setPoint(5);
//		bookcafe.setPoint(4);		
//		
//		System.out.println("수정후");
//		bookcafe.info();
		
		kuuukbob.info();
		kuuukbob.setCloseDay("wendsday");
		kuuukbob.setStartTime("00:00");
		kuuukbob.setEndTime("00:00");
		kuuukbob.addPoint(3);
		kuuukbob.addPoint(3);
		
		System.out.println("수정후");
		kuuukbob.info();
		
		bibimbbobb.info();
	
		
		//여러 가게리스트가 있다고가정,
		//원하는 정보대로 검색하는과정?
		//해당 정보들을 입력한 후 만족하는 가게 리스트 출력
		
		//list setting
		ArrayList <FoodService> listAll = new ArrayList<>();
		ArrayList <FoodService> foundList = new ArrayList<>();
		listAll.add(bookcafe);
		listAll.add(kuuukbob);
		listAll.add(bibimbbobb);
		
		search(listAll,foundList);
				
	}
	
	
	static void search(ArrayList<FoodService> listAll, ArrayList<FoodService> foundList) {
		System.out.println("요식업 리스트에서 검색할 것입니다.");
		//요식업 카테고리만 모은 list가있으면 좋을것 같음
		
		
		System.out.println("카테고리를 입력하세요 ex)식당,카페");
		String categ = sc.next();
		searchCategory(categ,listAll,foundList);
		
		System.out.println("어떤메뉴를 찾으세요?");
		String menu = sc.next();
		foundList = isMenu(menu,foundList);
		
		if(foundList.size() == 0) {
			System.out.println("찾는메뉴가 없어요");
		}
		printRes(foundList);
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
