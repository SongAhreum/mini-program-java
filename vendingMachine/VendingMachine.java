package vendingMachine;

import java.util.Scanner;

class VendingMachine{
	static Scanner sc = new Scanner(System.in);
	//최대 갯수
	public static final int MAX = 5;
//	static String kind = "beverage";
//	static String menu[] = {"","","","",""};
//	static int price[] = {-1,-1,-1,-1,-1};
//	static int stock[] = {0,0,0,0,0};
//	static int income = 0;
	static String kind = "beverage";
	static String menu[] = new String[MAX];
	static int price[]=new int[MAX];
	static int stock[]=new int[MAX];
	static int income=0;
	
	//기본생성자
	VendingMachine(){
		defaultSetting();
	}
	//인스턴스생성시 기본값 세팅
	public void defaultSetting() {
		for(int i =0;i< MAX;i++) {
			menu[i]="";
			price[i]=-1;	
			stock[i]=0;
		}
	}
	
	//메뉴보기
	public void menuDisplay() {
		if(menu[0]=="") {
			System.out.println("주문할 수 있는 메뉴가없습니다.");
			return;
		}
		
		for(int i =0; i<menu.length;i++) {			
			if(menu[i] == "") {
				break;
			}
			System.out.println(i+1+"."+menu[i]+":"+price[i]+"원 재고 :"+stock[i]+"개");			
		}
	}
	//메뉴수정
	public void modifyMenu(int a) {
		
		//수정 전 메뉴
		System.out.println(menu[a-1]+":"+price[a-1]);
		System.out.println("어떤 메뉴로 수정하시겠습니까");
		menu[a-1] =sc.next();
		System.out.println("어떤 가격으로 판매하시겠습니까");
		price[a-1] = sc.nextInt();
		//수정 후 메뉴
		menuDisplay();				
	}
	//메뉴 추가
	public void addMenu() {
		
		System.out.println("어떤메뉴를 추가하시겠습니까");
		String add = sc.next();
		//빈 슬롯에 순차적으로 추가, 슬롯이 다 차있는 경우 안내문구 출력
		int i=0;
		while(true) {
		 if(menu[i] == "") {
				menu[i] = add;
				System.out.println("가격은 얼마로 설정하시겠습니까");		
				price[i] = sc.nextInt();
				break;
			} else if(i == menu.length-1) {//해봐야함
				System.out.println("더이상 메뉴를 추가 할 수 없습니다.");
			}
			i++;
		}
		menuDisplay();					
	}
	public void modifyStock(int a) {
		
		System.out.println(a+"."+menu[a-1]+":"+price[a-1]+"원 재고 :"+stock[a-1]+"개");		
		System.out.println("해당 상품의 재고량을 입력해 주세요");
		stock[a-1] = sc.nextInt();
		menuDisplay();
	}
	//입력값검증
	public int inputCheck(int a) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			if((a > menu.length)||(menu[a-1] == "")) {
				System.out.println("다시입력해주세요");
				menuDisplay();					
				a = sc.nextInt();
				
			}else {
				return a;
			}
		}
	}
	
	public void purchase(int a) {
		Scanner sc = new Scanner(System.in);
		//menu[a-1]구매, price[a-1]가격, stock[a-1]재고 하나줄어야함
		
		//선택물품재고없음
		if(stock[a-1]==0) {
			System.out.println("선택한 상품의 재고가 없습니다.");
			user();//???
			return;//???
		}
		
		System.out.println("금액을 넣어주세요");
		int money = sc.nextInt();		
		int change = money - price[a-1];
		int more = 0;
		
		while(change < 0) {
			System.out.println((-1)*change+"원이 모자랍니다. 추가금액을 넣어주세요. 상위 메뉴로 돌아가려면 0을 입력하세요 ");
			more = sc.nextInt();
			if(more == 0) {
				user();
				return;//??? 계속 함수를 재귀하는거처럼 부르고부르니까 마무리를 어떻게 해야할지모르겟음
			}
			change += more;
				
		}
		System.out.println(menu[a-1]+"상품을 받아주세요. 잔돈은 "+change+"원 입니다.");	
	
		stock[a-1] -= 1;
		income += price[a-1];
		
		//구매이후 값 재고,수입 수정되었는지보는 확인용
		//menuDisplay();
		//System.out.println("수입 :"+income+"원");
	}

	//사용자 메뉴
	public void user() {
		Scanner sc = new Scanner(System.in);
		System.out.println("============================================");
		System.out.println("어떤상품을 구매할것 입니까? 번호로 입력해주세요");
		menuDisplay();		
		
		int a = sc.nextInt();
		//1004를입력하면 관리자메뉴로 이동 숨은기능!
		if(a == 1004) {
			admin();
			return;//???필요한가
		}
		inputCheck(a);
		purchase(a);
		//다시 상위메뉴로 돌아가기
		user(); 
		
		
	}
	//관리자메뉴
	public void admin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("============================================");
		System.out.println("관리자 페이지입니다");
		System.out.println("1.메뉴수정 2.재고관리 3.수익관리 4.사용자메뉴로 가기");
		
		
		int n = sc.nextInt();
//admin();중복되는데 그냥계속 함수마지막에 넣나?		
		switch(n) {
		//1.메뉴수정
		case 1:
			//메뉴가 하나도없을 때 바로 메뉴추가로 넘어감
			if(menu[0] == "") {
				System.out.println("메뉴가 존재하지 않습니다. 메뉴를 추가해 주세요");
				addMenu();
				admin();  				
			} else { //메뉴수정 or 메뉴추가
														
				System.out.println("변경할 메뉴의 번호를 입력하거나 메뉴를 추가하려면 0번을 입력하세요");
				menuDisplay();
				int a = sc.nextInt();
				
				//메뉴삭제코드?
				//배열이라 삭제하면 뒤에오는 값들 index 땡겨야하는데 어려울거같은데-> linkedlist로 자료형바꾸면 가능하려나?
				
				//메뉴추가
				if(a==0) {
					addMenu();					
					admin();
				}else {
					//입력값검증
//if문 밖으로 위로 빼니까 a=0인경우에 error,위치옮김					
					a = inputCheck(a); 
					//메뉴수정
					modifyMenu(a);
					admin();
				}							
			}
			break; 
		//2.재고관리	
		case 2:
			System.out.println("어떤 메뉴의 재고를 변경하실건가요. 번호로 입력해 주세요.");
			menuDisplay();
			int a = sc.nextInt();
			a = inputCheck(a);
			modifyStock(a);
			admin();
			break;
		//3.수익관리	
		case 3:
			System.out.println("총"+income+"원");
			System.out.println("1.관리자메뉴로 돌아가기 2.매출가져가기");
			
			int input = sc.nextInt();
			//수정(입력값검증도(무한루프에 넣음) 되고 case3:아래에 또 switch문을 쓰기보단 if문이 좀더 가독성좋을거같아서)
			while(true) {
				if(input == 1) {
					admin();
					break;
				} else if(input == 2) {
					income = 0;
					admin();
					break;
				} else {
					System.out.println("다시입력해주세요");
					input = sc.nextInt();
					//break; 여기는 없어야 1.2값을 가지기 전까지 계속 돔
				}
			}		
		//4.사용자메뉴가기
		case 4:
			user();
			break;
		}				
	}
}
