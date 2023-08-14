package foodService_inheritance;

public class Korean extends Restaurant{
	private final String foodType = "한식";	
	private String name;
	private String[] menu = new String[1];
	private int[] price= new int[1];
	
	public Korean() {}
	
	//생으로 다넣기
	public Korean(boolean delivery,String[] closeDay,String startTime,String endTime,boolean reservation,int point,int count,String name,String[] menu,int[] price){		
		this.delivery =delivery;
		this.closeDay =closeDay;
		this.startTime=startTime;
		this.endTime=endTime;
		this.setReservation(reservation);
		this.setPoint(point); 
		this.setCount(count);
		this.name =name;
		this.menu= menu;
		this.price = price;
	}
	
	//restaurant내용에서 추가
	public Korean(Restaurant restaurant,String name,String[] menu,int[] price){		
		super(restaurant);					
		this.name =name;
		this.menu= menu;
		this.price = price;
	}
	
	public Korean(Restaurant restaurant,String name,String menu,int price){		
		super(restaurant);					
		this.name =name;
		this.menu[0]= menu;
		this.price[0] = price;
	}
	
	public Korean(Korean korean){		
		super(korean);				
		this.name =korean.getName();
		this.menu= korean.getMenu();
		this.price = korean.getPrice();
	}
	
	@Override
	public void info() {
		System.out.println(this.name+"에 오신걸 환영합니다. **평점 :"+this.getGrade()+"**");
		System.out.print("영업일 운영시간 : "+this.startTime+"~"+this.endTime+" /");
		
		System.out.print("공휴일 : ");
		for(int i =0;i<closeDay.length;i++) {
			System.out.print(closeDay[i]+" ");			
		}
		System.out.println();
		ableDelivery();		
		ableReservation();	
		
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

	public String getStoreType() {
		return StoreType;
	}
	
}
