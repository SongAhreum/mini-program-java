package shape;

//초기화조건 검증
//원메서드에서 전체 오버라이딩 ->원넓이에서 a/b곱한값을 리턴
public class Oval extends Circle{ //타원
	//타원위의점 Point P ;
	Point center;
	Point centerA;
	Point centerB;
	
	//a < b
	int a; //원에서는 반지름
	int b; //a = rcos세타


	Oval(int x,int y,int a, int b){
		super(x,y,a);
		this.b = b;
	}
	Oval(Point p,int a, int b){
		super(p,a);
		this.b = b;
	}
	//넓이
	//중심?에서 타원위의점까지의 거리로 구하는 공식
	public double getAreaOval() { //해당인스턴스 넓이
		return this.a * this.b * PI; 
	}
	public double getAreaOval(int a, int b) {//입력한 조건으로 넓이구하기
		return a * b * PI; //카발리에공식? 원통을 사선으로 자르고  해당 지름비율차이(정사영)를 통한?
	}	
	@Override
	public double getArea(Point center,Point p) {
		int result = Shape.pow(center.x-p.x,2)+Shape.pow(center.y-p.y,2);
		return PI * Shape.pow(result,2)*(b/a);
	}
	@Override
	public double getArea(int r) {
		return PI*r*r*(b/a);
	}
	@Override
	public double getArea() { //해당인스턴스로 넓이구하기
		return PI * super.getR() * super.getR()* (this.b / this.a);
	}
	@Override
	double getArea(double n) { //원넓이 , n을 큰수로잡음
		double result  =0;
		for(int i = (int)n ; i > 0;i--) {
			if(i == 0) {
				return result*4;
			}
			result += getA(i);		
		}
		return result*(b/a);
	}
	
	//타원위의점으로 넓ㅅ이구하는 공식
	
	//타원의둘레 공식
	@Override
	public double round(int b) {
		return 2*PI*Math.sqrt((Math.pow(this.a,2)+Math.pow(b,2))/2);
	}
	
	//제곱근이랑 제곱구하는 함수 
	
	/*
	 * 
	 * x =a*a
	 * 
	 * 
	 * 
	 */
}
