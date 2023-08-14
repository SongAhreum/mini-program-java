package foodService_inheritance;

public class Cafe extends FoodService {
	
	private final String category = "카페";
	int point =0;
	int count =0;
	
	
	public Cafe(){		
		point=0;
		count=0;
		
	}
	
	public Cafe(FoodService foodService,int point,int count){		
		super(foodService.delivery,foodService.closeDay,foodService.startTime,foodService.endTime);
		
		this.point =point;
		this.count = count;
	}
	
	//메서드
	public void info() {
		System.out.println("**평점 :"+this.getGrade()+"**");
		System.out.print("영업일 운영시간 : "+this.endTime+"~"+this.startTime+" /");
		System.out.print("휴일 : ");
		for(int i =0;i<closeDay.length;i++) {
			System.out.print(closeDay[i]+" ");			
		}
		System.out.println();
		ableDelivery();	
		
			
	}
	void setPoint(int point) {
		this.point += point;
		count++;
	}
	double getGrade() {
		double grade = (1.0)*this.point/this.count;	
		return grade;
	}
		
	
}
