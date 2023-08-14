package foodService_inheritance;

import java.util.Arrays;


public class StoreMain {
	public static void main(String[] args) {
		FoodService foodService= new FoodService(false,new String[] {"Sunday"},"9:00","22:00");
		//foodService.info();
		Restaurant restaurant = new Restaurant(foodService,true,5000,5500);
		//restaurant.info();
		Korean kuuukbob = new Korean(restaurant,"국밥조아","국밥",8000);
		//kuuukbob.info();
		
		
		//가게 2개매장잇음
		Store kukbobjoah = new Store(kuuukbob,50);
		
		Store arirang = 
				new Store(
						true,
						new String[] {"Saturday","Sunday"},
						"09:00",
						"22:00",
						true,
						100,
						10,
						"아리랑",
						new String[] {"비빔밥","잔치국수","김치말이국수","너비아니"},
						new int[] {8000,5000,5500,6500},
						50
						); 
	
//		kukbobjoah.service();
//		kukbobjoah.reservationService();
		//arirang.service();
		arirang.reservationService();
	}
	
}
