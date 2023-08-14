package shape;
/**
 * 원 넓이,둘레를 구하는 다양한 메서드 제공 
 * 
 * 1.파라미터에따른 생성자 생성제한
 * 		아래 경우에만 인스턴스생성
 * 		1) Point center, Point p 2) int r 3) Circle타입 인스턴스
 * 
 * 2.생성자에서 Circle타입으로 매개변수를 받았을떼,초기화된 필드값경우에 따라 검증해서 객체생성
 * 
 * 3.getArea 오버로딩 : 해당 인스턴스가 가진 값으로 vs 입력값에대한 값으로
 * 
 * 4.getArea() :해당 인스턴스의(제한한 생성자에따라 생성된) 값으로 함수를 쓸 때,초기화된필드값이 각각 다른경우 검증후 실행
 * 
 *  */
public class Circle extends Shape{
	static final double PI =3.14;
	private Point p; //원위의점 //포함관계	
	private Point center;  //포함관계
	private int r; //반지름

	//생성자	
	//기본생성자 x : 조건없으면 생성못하게 막음
	//public Circle(){
	//		
	//}	
	//public Circle(int x,int y,int r){ //원중심과 반지름으로 원생성
	//	this.center = new Point(x,y);
	//	this.r = r;
	//}
	//public Circle(Point center,int r){  
	//	this.center = center;
	//	this.r = r;
	//}
		
	public Circle(Point center,Point p){ //원중심과 원위의점으로 원생성
		this.center = center;
		this.p = p;
	}
	//초기화조건 : r존재 or p,center존재 ->try ctach or if로 검증? 
	public Circle(Circle circle){
		if(isNullPoint(circle.center)|| isNullPoint(circle.p)) {
			this.r = circle.r;
		} else {
			this.center = circle.center;
			this.p = circle.p;
		}                    
	}
	public Circle(int r){ //원중심과 반지름으로 원생성
		try {
			this.r = r;
			if(r <= 0) {
				Exception e = new Exception("반지름의 값은 양수여야 합니다."); // ---->>> exception들어가서 찾아보장 거깄는거 써보장
				throw e;
			}
		}catch(Exception e) {
			System.out.println("error!!: "+e.getMessage());
			e.printStackTrace();
		}		
	}
	
	//getter setter
	public Point getP() {
		return p;
	}
	public void setP(Point p) {
		this.p = p;
	}
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	
	//Point객체 null체크하는 함수를 만들기>>>리턴값 boolean******
	public boolean isNullPoint(Point p) {
		if(p == null) {
			return true;
		} else {
			return false;
		}
	}
	
	//원 넓이 구하기 메서드 오버로딩****
	//원중심,원위의점 으로 원넓이구하기
	//************해당인스턴스의 초기화된필드값이 각각 다른경우 검증하기 위해 if문으로 검증 코드넣음**********
	public double getArea() {//해당인스턴스로 원넓이 구하기
		if(this.r > 0) {
			return PI * this.r * this.r;
		}
		//r이 없고 Point center,Point p값이 존재하는경우
		else if(isNullPoint(this.p) || isNullPoint(this.center) != true ) {
			int result = Shape.pow(this.center.x-this.p.x,2)+Shape.pow(this.center.y-this.p.y,2);
			return PI * result;
		} 
		else {//조건이 부족할때
			System.out.println("원의 넓이를 구하기 위한 조건이 부족합니다."); //******생성자부터 수정하면 조건이부족한 객체자체가 생성안되서 없어도될거같은데
			return 0;
		}	
	}
	public double getArea(Point center,Point p) {//중심과 원위의점 입력시 넓이구하기
		int result = Shape.pow(center.x-p.x,2)+Shape.pow(center.y-p.y,2);
		return PI * result;
	}
	//반지름으로 원넓이구하기
	public double getArea(int r) {//입력한 반지름의 값으로 넓이 구하기
		return PI * r * r;
	}

	//구분구적? 으로 넓이구하기**********(재귀함수?)
	//double getAreaByInfinite(double n)
	double getArea(double n) { //원넓이 , n을 큰수로 잡을수록 정확도 올라감(50정도?) //해당인스턴스반지름 작으면 계산안될수도있음?
		double result  =0;
		for(int i = (int)n ; i > 0;i--) {
			if(i == 0) {
				return result*4;
			}
			result += getA(i);		
		}
		return result;
	}
	
	double getX(double n) { //n번째 X좌표
		if(n==0) {
			return 0;
		}
		double result = 0;
		for(int i =1; i <= n; i++) {
			result += this.r*(Math.pow(0.5, i));
			
		}
		return result;
	}
	//double getX(int r,double n) { //n번째 X좌표
	//	
	//	if(n == 0) {
	//		return 0;
	//	}
	//	return  (r/2.0)*(1.0 / Math.pow(2, n) +getX(r,n-1));
	//}
	
	double getH(double n) { // n번쩨 높이구하기 // n번째 밑변은 
		//System.out.println(Math.pow(this.r,2));
		//System.out.println(Math.pow(this.getX(n),2));
		double h = Math.pow(this.r,2) - Math.pow(this.getX(n),2);
		
		return Math.sqrt(h);				
	}
	double getA(double n) { //n번째 사각형넓이구하기
		if(n==0) {
			return 0;
		}
		double base = this.r * Math.pow(0.5, n);
		System.out.println("base: "+base);
		System.out.println("h: "+getH(n));
		double result = base * getH(n);
		System.out.println("사각형넓이 "+result);
		return result;		
	}
	@Override
	double areaByVertex(Point...ps) {
		System.out.println("꼭지점으로 넓이계산이 되지않는 원형 도형입니다.");
		return 0;		
	}
	
	//원둘레 구하기
	public double round() {
		return this.PI *2*this.r;
	}
	public double round(int r) {
		return this.PI *2*r;
	}
	
}
