package rpgGame;

public class Monster extends Unit {
	private String name; //인스턴스 변수

	
	public Monster(String name,int level, int HP, int MP, int STR, int DEF) {
		this.name = name;
		this.setLevel(level);
		this.setHP(HP);
		this.setMP(MP);
		this.setSTR(STR);
		this.setDEF(DEF);
		
	}
	
		
	void attack(Character character) {
		System.out.print( this.getName()+"이(가)"+character.getName()+"을 공격합니다!!");
		System.out.println(this.getSTR());
		character.damaged(this);
		System.out.println();
	}
	void damaged(Character character) {
		System.out.print( this.getName()+"이(가)"+character.getName()+"에게 공격받습니다!!");
		System.out.println("-"+character.getSTR());
		this.setHP(getHP()-character.getSTR());
		System.out.println();
	}
	
	void avoid() {
		System.out.println(this.name+"이 전투에서 도망쳤습니다!!!");
	}
	@Override
	void info() {
		
		System.out.println("========스탯창========");
		System.out.println("이름   : "+ this.name);
		System.out.println("Levle : "+this.getLevel());
		System.out.print("HP  : "+this.getHP()+"     ");
		System.out.println("MP :  "+this.getMP());
		System.out.print("STR : "+this.getSTR()+"    ");
		System.out.println("DEF :  "+this.getDEF());
		System.out.println("====================");
		
	}
	
	
	//getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
