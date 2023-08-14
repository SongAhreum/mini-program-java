package foodService_inheritance;

import java.util.ArrayList;import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//입력값받거나 의미가적은 부분만따로 묶어서 함수화, 강조해야할 부분만 남겨놓도록 수정(-)
//코드길어져서 예약기능만 따로 묶을 수있나?->예약기능은다있으니가 restaurant에 넣는거나 상위클래스에 넣자
//this 없어도되는부분..!?
//예약가능한 시간대 출력하는 함수(-)
//방문시간 입력값검증 경우의수에따라 구현
public class Store extends Korean {
	Scanner sc = new Scanner(System.in);
	private String name;
	private int seat;
	private int storeTimeTableBySeat[] = new int[48];
	private List<Customer>customerList = new ArrayList<Customer>();
	private int listN = 1;
	//매번 스트링으로 받은 시간 int로 파싱?하는함수돌려야하니까 애초에 List<Map<Customer,timeIdx>>customerList?
	//자료형대신에 자료형원리를 구현해서 사용?***************
	//key값으로 객체를 쓸수있는지? map원리를 알아야..? 객체검색시(list도마찬가지) equals hashcode오버라이딩...?
	
	//생성자
	Store(boolean delivery,String[] closeDay,String startTime,String endTime,boolean reservation,int point,int count,String name,String[] menu,int[] price,int seat) {
		super(new Korean(delivery,closeDay,startTime,endTime,reservation, point, count, name, menu, price)); //*************************
//		this.delivery =delivery; 
//		this.closeDay =closeDay;
//		this.startTime=startTime;
//		this.endTime=endTime;		
//		this.setReservation(reservation);
//		this.setPoint(point);
//		this.setCount(count);
//		this.setName(name);		
//		this.setMenu(menu);
//		this.setPrice(price);
		
		this.seat = seat;
		timeTableBySeatDefaultSetting();
		//System.out.println(Arrays.toString(this.storeTimeTableBySeat));				
	}
	Store(Korean korean,int seat) {
		super(korean);
		this.name = korean.getName();
		this.seat = seat;	
	}
	
	//getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public List<Customer> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
	public int getStoreTimeTableBySeat(int n) {
		return storeTimeTableBySeat[n];
	}
	public int[] getStoreTimeTableBySeat() {
		return storeTimeTableBySeat;
	}
	public void setStoreTimeTableBySeat(int[] storeTimeTableBySeat) {
		this.storeTimeTableBySeat = storeTimeTableBySeat;
	}
	public void setStoreTimeTableBySeat(int timeIdx,int seat) {
		this.storeTimeTableBySeat[timeIdx] = seat;
	}
	
	//메서드
	public void service() {
		System.out.println("**"+ name +"에 오신걸 환영합니다.** ");
		Customer customer = makeInstanceOfCustomer(); //입력정보로 인스턴스만들기
		customerInfo(customer);
		String time = customer.getTime();
		askService(customer,timeTableIdx(time)); //식사 가능여부,좌석수변동 적용		
	}
	public Customer makeInstanceOfCustomer() {
//		String name = this.name;
//		if(this.name == null) name = super.getName();----------------->설계를 잘못함 name값가져올 때 null이어서 추가 (이유?-)
		System.out.println("몇분이신가요?");
		int num = sc.nextInt();
		System.out.println("방문시간 입력 ex)16:00");
		String time = sc.next();//입력값검증(-)*********************************
		return new Customer("customer"+this.listN++,num,time); //인스턴스 생성시마다 listN++;
	}
	//예약이랑 겹침 수정필요 (-)**************
	public void askService(Customer customer,int timeIdx) {
		int seat = this.getStoreTimeTableBySeat(timeIdx);//해당시간 좌석수
		if(seat - customer.getPersonNum() >= 0) {
			System.out.println("자리를 안내해 드리겠습니다");
			reserved(customer,timeIdx);
			//System.out.println(Arrays.toString(this.storeTimeTableBySeat));
		} else {
			System.out.println("빈좌석은"+seat+"개로 식사가 어려워요ㅠㅠ");
			
			//예약가능한 시간대 출력하는 함수(-)
			//시간만 재입력할 순없을까?(-)
			//********************//
		}
	}
	public void reservationService() {
		
		System.out.println(super.getName()+"의 예약 안내 서비스입니다. ");
		System.out.println("가게정보를 열람하시겠습니까? y/n");
		String input = inputYN();//검증해서 입력 값받는 함수
		if(input.equals("y")) {
			this.info();
		}
		
		System.out.println("예약하시겠습니까? y/n");
		input = inputYN();
		
		if(input.equals("y")) {			
			System.out.println("예약자명과 인원수를 차례대로 입력해주세요");
			String name = sc.next();
			int num = sc.nextInt();
			
			System.out.println("예약하고자하는 시간을 입력해주세요 ex)16:00");
			input = sc.next();
			//입력형태 검증..휴이게맞나**************
			//예외처리 경우 생각해보기***************
			String result[] = input.split(":");			
			while(true) { 
				if(result.length == 2) {
					break;
				}
//				System.out.println(Arrays.toString(result));
				System.out.println("예시와 같은 형태로 입력해주세요");
				input = sc.next();
			}
			//입력정보로 예약자생성
			Customer customer = new Customer(name,num,input);		
			//예약시간 가능한지(가능하면예약/가능하지않으면 ****예약가능한 시간 출력(-)*****)	
			askReservation(customer,timeTableIdx(input));
			
			reservationService(); //처음으로
			return;	
			
		} else {
			System.out.println("예약정보를 변경하시려면 y/ 종료하시려면 n");
			input = inputYN();
			if(input.equals("y")) {
				//예약정보변경 함수
				cancelReservation();
				System.out.println("예약취소가 완료 되었습니다.");
				reservationService(); //처음으로
				return;			
			}
		}			
	}
//	public Customer makeInstanceOfCustomer2() {
//		
//	}
	//입력검증함수
	public String inputYN() {
		String input = sc.next();
		checkInput(input);
		return input;
	}
	public String checkInput(String input){ //입력값검증
		while(true) {
			if(input.equals("y") || input.equals("n")) {
				return input;
			} else {
				System.out.println("다시입력해주세요.");
				input = sc.next();
			}		
		}
	}
	
	
	public void askReservation(Customer customer,int timeIdx) {
		//setStoreTimeTableBySeat(int timeIdx,int seat)
		//예약가능한지.예약까지
		int seat = this.getStoreTimeTableBySeat(timeIdx);//해당시간 좌석수
		
		if(seat - customer.getPersonNum() >= 0) {
			System.out.println(customer.getName()+"님 예약자 수 "+customer.getPersonNum()+"명 예약가능합니다.");
			//System.out.println(Arrays.toString(this.storeTimeTableBySeat));
			System.out.println("예약하시겠습니까? y/n");			
			String input = inputYN();		
			if(input.equals("y")) {
				//예약적용
				reserved(customer,timeIdx);
				System.out.println("예약이 완료되었습니다.");	
				customerInfo(customer);
				this.printCustomerList();
			} 			
			
		} else {
			//System.out.println(Arrays.toString(this.storeTimeTableBySeat));
			System.out.println("빈좌석은"+this.seat+"개로 "+customer.getName()+"의 예약이 불가능 합니다.");
			//예약가능한 시간대 출력하는 함수(-)
			//********************//
			System.out.println("다시 예약 하겠습니까? y/n (y:예약메인으로 돌아갑니다 /n:종료)"); //->시간만바꾸게 수정(-)
			String input = inputYN();
			if(input.equals("y")) {
				reservationService();
				return;
			}
		}
	}
	
	
	//손님들 예약함 손님++좌석수변동 -> 함수명 수정(-)
	public void reserved(Customer customer,int timeIdx) {		
		//가게 해당시간 좌석수 - 손님이 예약하고자하는 명수
		int result = this.getStoreTimeTableBySeat(timeIdx) - customer.getPersonNum();
		//시간대별 좌석수 변동 적용
		this.setStoreTimeTableBySeat(timeIdx,result);
		//예약자명단수정
		this.customerList.add(customer);
				
	}
		
	//예약취소 //for문에서 출력
	public void cancelReservation() {
		//해당매장에 이사람이 예약한지 확인하는 코드 필요
		System.out.println("취소할 예약자명을 입력해주세요");
		String name = sc.next();
		int cnt = 0;
		//size확인하는법 -> for문몇번도는지?....**************************
//		for(Customer c : this.getCustomerList()) {
//			System.out.println(c.getName()+" "+c.getPersonNum()+" "+c.getTime());
//			if(c.getName().equals(name)) {
//				//질문*******************************************
//				//해당객체를 검색해서 삭제 -> 동등성비교 ?
//				//필드값 바로불러와서 삭제했는데 해당 인스턴스필드값에 바로 적용				
//				int timeIdx = timeTableIdx(c.getTime()); 
//				int result = c.getPersonNum()+ this.getStoreTimeTableBySeat(timeIdx);
//				setStoreTimeTableBySeat(timeIdx,result);
//				this.customerList.remove(c);
//				
//				cnt++;
//			}
//		}	
		for(int i =0;i < this.getCustomerList().size();i++ ) {
			
			Customer c = this.getCustomerList().get(i);
			if(c.getName().equals(name)) {
				int timeIdx = timeTableIdx(c.getTime());
				int result = c.getPersonNum()+ this.getStoreTimeTableBySeat(timeIdx);
				setStoreTimeTableBySeat(timeIdx,result);
				this.customerList.remove(c);				
				cnt++;
			}
		}
		if(cnt == 0) {
			System.out.println("예약자명단에 없습니다. 다시 검색하시겠습니까? y/n");
			String input = inputYN();
			if(input.equals("y")) {
				cancelReservation();
				return;
			} else {
				reservationService();
			}
		}			
	}
	public void customerInfo(Customer customer) {
		System.out.println("예약자 정보출력");
		System.out.println("방문 : "+customer.getName());
		System.out.println("인원수 : "+customer.getPersonNum());
		System.out.println("방문시각 : "+customer.getTime());
	}
	//손님--,좌석수변동
	public void outCustomer(Customer customer,int timeIdx) {
		int result = this.getStoreTimeTableBySeat(timeIdx) - customer.getPersonNum();
		//좌석수 적용
		this.setStoreTimeTableBySeat(timeIdx,result); //-->식사시간30분으로잡았는데 1시간이여야할까? 그럼 +2 
		//명단삭제
		this.customerList.remove(customer);
		
	}
	void timeTableBySeatDefaultSetting() {
		
		for(int i = 0;i < storeTimeTableBySeat.length;i++) {
			if(i >= timeTableIdx(this.startTime) && i < timeTableIdx(this.endTime)) {
			storeTimeTableBySeat[i] = this.seat;
			
			} else {
				storeTimeTableBySeat[i] = 0;
			}
		}
	}
	//LocalTime 클래스를 사용하는게...
	//String으로 받은시간 int로리턴 -> timetalbebyseat에 적용
	public int timeTableIdx(String time) {
		//00:00
		String result[] = time.split(":");//구현*************
		int resultInt[] = new int[] {Integer.parseInt(result[0]),Integer.parseInt(result[1])};
//		int로 형변환하는 내용 구현 *****************************
		if(resultInt[1] >= 30) {
			return (resultInt[0]*2) + 1;
		} else {
			return (resultInt[0]*2);
		}
		
	}
	@Override
	public void ableReservation() {
		if(this.isReservation()) {
			System.out.println("예약이 가능한 매장입니다");
		} else {
			System.out.println("예약이 불가능한 매장입니다");
		}
	}
	//아직 덜만듬************************* 예약자명단확인
	public void printCustomerList() {
		System.out.print("예약자명단 : ");
		for(Customer c : customerList) {
			System.out.print(c.getName()+" ");
		}
		System.out.println();		
	}

}
