package shape;

public class Point {
	int x;
	int y;
	//초기화여부 체크하는 변수로 체크
	
//	Point(){
//		
//	}
	Point(int x,int y){
		this.x = x;
		this.y = y;
	}
	Point(Point p){
		this.x = p.x;
		this.y = p.y;
	}
}
