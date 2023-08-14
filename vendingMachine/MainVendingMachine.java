package vendingMachine;

import java.util.Scanner;



public class MainVendingMachine {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		VendingMachine vendingMachine = new VendingMachine(); 		
		
		System.out.println("============================================");
		System.out.println(vendingMachine.kind+"자판기입니다" );
		vendingMachine.user();
		
		//System.out.println(vendingMachine.kind+"자판기입니다. 사용하시겠습니까? 1.사용 2.관리자페이지" );				
		//int n = sc.nextInt();
		
		//switch(n) {
		//case 1:
		//	vendingMachine.user();
		//	break;
		//case 2:
		//	vendingMachine.admin();
		//	break;
		//}	
	}		
}
