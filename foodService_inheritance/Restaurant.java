package foodService_inheritance;

public class Restaurant extends FoodService{
	private final String category = "식당";
	private boolean reservation;
	//point와 count로 grade(평점)만듬 -> 실제 검색에 사용될건 grade
	//point를 주는 기준도 추가해야함(몇점만점인지) ->point주고 후기작성하는 프로그램? 별점,사진,후기?
	private int point;
	private int count;
	
	//생성자
	public Restaurant(){
		
		reservation = true;
		point=0;
		count=0;
	}
	//
	public Restaurant(boolean delivery,String[] closeDay,String startTime,String endTime,String name,boolean reservation,int point,int count){		
		
		this.delivery =delivery;
		this.closeDay =closeDay;
		this.startTime=startTime;
		this.endTime=endTime;
		this.reservation = reservation;
		this.point =point;
		this.count = count;
	}
	public Restaurant(boolean delivery,String closeDay,String startTime,String endTime,String name,boolean reservation,int point,int count){		
		
		this.delivery =delivery;
		this.closeDay = new String[1];
		this.closeDay[0] =closeDay;
		this.startTime=startTime;
		this.endTime=endTime;
		this.reservation = reservation;
		this.point =point;
		this.count = count;
	}
	//
	public Restaurant(FoodService foodService,boolean reservation,int point,int count){		
		super(foodService);
		
		this.reservation = reservation;
		this.point =point;
		this.count = count;
	}
	public Restaurant(Restaurant restaurant){				
		super(restaurant);
		
		this.reservation = restaurant.reservation;
		this.point =restaurant.point;
		this.count = restaurant.count;
	}	
	public Restaurant(Korean korean){				
		super(korean);
		
		this.reservation = korean.isReservation();
		this.point =korean.getPoint();
		this.count = korean.getCount();
	}

	//메서드
	@Override
	
	public void info() {
		System.out.println("**평점 :"+this.getGrade()+"**");
		System.out.print("영업일 운영시간 : "+this.startTime+"~"+this.endTime+" /");
		System.out.print("휴일 : ");
		for(int i =0;i<closeDay.length;i++) {
			System.out.print(closeDay[i]+" ");			
		}
		System.out.println();
		ableDelivery();		
		ableReservation();			
	}
	
	public void addPoint(int point) {
		//point줄때 유효값범위 필요함 후기작성하는 기능도 구현한다면?****************
		this.point += point;
		count++;
	}
	public double getGrade() {
		if(count ==0) {
			System.out.println("점수를 받은 적이 없습니다");
			return 0;
		}
		double grade = (1.0)*this.point/this.count;
		return grade;
	}
	
	public void ableReservation() {
		if(this.reservation) {
			System.out.println("예약가능");
		} else {
			System.out.println("예약불가능");
		}
	}
	public boolean isReservation() {
		return reservation;
	}
	public void setReservation(boolean reservation) {
		this.reservation = reservation;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}			
}
