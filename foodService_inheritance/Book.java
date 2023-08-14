package foodService_inheritance;

public class Book extends Cafe{
	String name;
	String[] menu = new String[1];
	int[] price= new int[1];

	Book(FoodService foodService,Cafe cafe,String name,String menu,int price){		
		super(
				foodService,
				cafe.point,
				cafe.count
				);					
		this.name =name;
		this.menu[0]= menu;
		this.price[0] = price;
	}
	Book(FoodService foodService,Cafe cafe,int point,int count,String name,String[] menu,int[] price){		
		super(
				foodService,
				cafe.point,
				cafe.count
				);
		this.name =name;
		this.menu= menu;
		this.price = price;
	}
	//가게정보 출력
	public void info() {
		System.out.println(this.name+"에 오신걸 환영합니다. **평점 :"+this.getGrade()+"**");
		System.out.print("영업일 운영시간 : "+this.endTime+"~"+this.startTime+" /");
		
		System.out.print("공휴일 : ");
		for(int i =0;i<closeDay.length;i++) {
			System.out.print(closeDay[i]+" ");			
		}
		System.out.println();
		ableDelivery();				
		System.out.println("=====메뉴======");
		for(int i =0;i<menu.length;i++) {
			System.out.println(i+1+"."+menu[i]+" : "+price[i]+"원");			
		}
		System.out.println("=============");
		System.out.println();
	}
	//getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getMenu() {
		return menu;
	}
	public void setMenu(String[] menu) {
		this.menu = menu;
	}
	public int[] getPrice() {
		return price;
	}
	public void setPrice(int[] price) {
		this.price = price;
	}
	
	

}
