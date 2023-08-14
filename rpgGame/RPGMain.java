package rpgGame;

public class RPGMain {
	public static void main(String[] args) {
		Monster monster1 = new Monster("고숨도오오치",2,50,0,5,5);
		Monster monster2 = new Monster("빨강달팽이",6,100,0,20,5);
		Character newBe = new Character(1,200,10,10,10,"뉴비",50);
		Wizard magician = new Wizard(10,500,500,30,15,"마법사",80);
		
		//고숨도치 vs 뉴비
		
		monster1.info();
		newBe.info();
		
		newBe.fight(monster1);
		
		//고숨도치 vs 마법사
		monster2.info();
		magician.info();
		magician.fight(monster2);
		
		
	}
	public static void battel(Monster monster,Character character) {
		
	}
}
