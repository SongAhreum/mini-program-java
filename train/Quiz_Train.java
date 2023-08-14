package train;

import java.util.Scanner;

public class Quiz_Train {
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
			
		String station[] = {"김포공항", "공항시장", "마곡나루", "양천향교", "가양", "증미", "당산", "국회의사당", "여의도"} ;
		String source;
		String destination ;
		String kindOfTrain="";
			  
		System.out.println("출발하는역을 입력하세요");
	    source = sc.next();
	    System.out.println("도착하는역을 입력하세요");
	    destination = sc.next();
	  
	    int srcIdx = -1;
	    int desInx = -1;
	    
	    for(int i =0;i < station.length;i ++) {
	    	//전철탐
	    	if(station[i].equals(source)) {
	    		
	    		srcIdx = i; //출발지 index
	    		if(i%2 == 0 ) {
	    			System.out.println("해당역은 일반/급행 열차가 모두 정차하는역입니다.");
	    			System.out.println("급행/일반");
	    	    	kindOfTrain = sc.next();
	    		} else {
	    			System.out.println("해당역은 일반열차만 정차하는역입니다. 급행을 타려면 다음역에서 승차하세요.");
	    			System.out.println("급행/일반");
	    	    	kindOfTrain = sc.next();
	    		}
	    		//for문에서 else if로묶고나니 타는역이 출력안되서 추가함
	    		System.out.println(station[i]+"에서 전철 탐");
	    	//전철 탄 이후로 srcIdx값이 초기화 되었을 때 	
	    	} else if(srcIdx >= 0) {
	    		
	    		//일반역선택했을때
	    		if(kindOfTrain.equals("일반")) {
	    			System.out.println(station[i]);
	    		//급행선택시	
	    		} else if(kindOfTrain.equals("급행")) { 
	    			//둘다탈수있는역에서 급행탓을때
	    			if(srcIdx%2 ==0&&i%2 ==0) {
	    				System.out.println(station[i]);
	    			//일반만있는역에서 급행탓을때(다음역에서 갈아탐..)	
	    			}else if(srcIdx%2 ==1) {
	    				//일반열차를 타고 다음역에서 갈아탈거임
	    				if(i==srcIdx) {
	    					System.out.println(station[i]);
	    				}
	    				//다음역에서 급행을 탄 내용 출력
	    				if(i%2 ==0) {
	    					System.out.println(station[i]);
	    				}
	    			}    			
	    		}	  	    		
	    	}
	    	////////////////////////////////////////
	    	//내리는역이 다음역인지 체크하는 코드로 수정!!!
	    	if(station[i+1].equals(destination)) {
//	    		desInx = i; //도착지 index
	    		
	    		//급행을 탓는데 도착지가 일반열차만 정차하는 역인경우
	    		if((i+1)%2 ==1 && kindOfTrain.equals("급행")) {
					  //System.out.println("급행탓는데 일반열차만정차하는역");
//					  System.out.println(station[i]);
					  System.out.println("내리기 전역에서 일반열차로 갈아탔습니다.");
					  
				  }
	    	}
	    	if(station[i].equals(destination)) {
	    		System.out.println(station[i]);
	    		System.out.println("내림");
	    		break;
	    	}
	    }
	}
}
