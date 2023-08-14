package rpgGame;

public class Unit {
	
	private int level;
	private int HP;  //체력
	private int MP;  // 
	private int STR; //공격력
	private int DEF; //방어력  방어력적용공식 : 공격력/공격력+방어력%
	
	
	public Unit() {
		
	}
	
	
	public Unit(int level, int HP, int MP, int STR, int DEF) {
		
		this.level = level;
		this.HP = HP;
		this.MP = MP;
		this.STR = STR;
		this.DEF = DEF;
		
	}


	public Unit(Unit unit) {
		
		this.level = unit.level;
		this.HP = unit.HP;
		this.MP = unit.MP;
		this.STR = unit.STR;
		this.DEF = unit.DEF;
		
		
	}
	
	void attact() {
		
		System.out.println("기본공격 : 공격력"+STR);
	}
	
	void info() {
		
		System.out.println("========스탯창========");
		
		System.out.println("Levle : "+this.level);
		System.out.print("HP  : "+this.HP+"     ");
		System.out.println("MP :  "+this.MP);
		System.out.print("STR : "+this.STR+"    ");
		System.out.println("DEF :  "+this.DEF);
		System.out.println("====================");
		
	}
	void statInfo() {
		System.out.println("HP  : Health Point,피,체력이라 불리는 이 수치가 0이 되면 전투 불능, 혹은 사망으로 처리된다");
		System.out.println("MP : Mana Point,마나,마력 이라 불리는 이 수치는 캐릭터의 특수 기술을 쓰는 데 사용된다");
		System.out.println("STR : 공격했을 때 대미지를 얼마나 주느냐의 척도");
		System.out.println("DEF : 공격을 받았을 때 대미지를 얼마나 입느냐의 척도");
		
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getHP() {
		return HP;
	}


	public void setHP(int hP) {
		HP = hP;
	}


	public int getMP() {
		return MP;
	}


	public void setMP(int mP) {
		MP = mP;
	}


	public int getSTR() {
		return STR;
	}


	public void setSTR(int sTR) {
		STR = sTR;
	}


	public int getDEF() {
		return DEF;
	}


	public void setDEF(int dEF) {
		DEF = dEF;
	}
	
}
