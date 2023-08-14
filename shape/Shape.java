package shape;

import java.util.ArrayList;
import java.util.List;

public class Shape {

	
	
	//제곱근
	
	
	
	//제곱(재귀)
	static int pow(int a, int b) {
		if(b == 1) {
			return a;
		}
		return a*pow(a,b-1);
	}
	
	//적분원리 함수? -> 배열로? 칸채우기
	
	
	//주어진 꼭지점의 좌표들로 넓이를 구하는 함수(신발끈공식)
	double areaByVertex(Point...ps) {
		List<Point> points = new ArrayList<Point>();
		for(Point p: ps) {
			points.add(p);
		}
		int sum1 = 0;
		for(int i =0;i<points.size();i++) {
			if(i == points.size()) {
				sum1 += points.get(i).x * points.get(0).y;
				break;
			}
			sum1 += points.get(i).x * points.get(i+1).y;
		}
		int sum2 = 0;
		for(int i =0;i<points.size();i++) {
			if(i == points.size()) {
				sum1 += points.get(i).y * points.get(0).x;
				break;
			}
			sum1 += points.get(i).y * points.get(i+1).x;
		}
		return (sum1-sum2)/2.0;
		
	}
}
