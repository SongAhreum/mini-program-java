package rpgGame;

import java.util.Scanner;

public class Character extends Unit {
	Scanner sc = new Scanner(System.in);
//	private int level;
//	private int HP;  //체력
//	private int MP;  // 
//	private int STR; //공격력
//	private int DEF; //방어력  방어력 적용 공식정해야 함..귀찮앙ㅇ아ㅏㅏㅏ 1.공격받을때마다 hp = hp+def? 2. 공격력/공격력+방어력%? 
	
	private String name;
	private int EXP; //경험치 전투, 비전투 행동의 종료 후 캐릭터가 얻게되는 경험을 수치화한 것 100 에도달하면 levelup
	
	
	public Character(int level, int HP, int MP, int STR, int DEF,String name, int EXP) {
		super(level, HP, MP, STR, DEF);
		this.name = name;
		this.EXP = EXP;
	}
	public Character(Unit unit,String name, int EXP) {
		super(unit);
		this.name = name;
		this.EXP = EXP;
	}
	public Character(Character character) {
		//*********.
		this.setLevel(character.getLevel());
		this.setHP(character.getHP());
		this.setMP(character.getMP());
		this.setSTR(character.getSTR());
		this.setDEF(character.getDEF());		
		this.name = character.getName();
		this.EXP = character.getEXP();
	}
	public Character(String name, int EXP) {
		super();
		this.name = name;
		this.EXP = EXP;
	}
	
	
	@Override
	void info() {		
		System.out.println("========스탯창========");
		System.out.println("이름   :"+this.getName());
		System.out.println("Levle : "+this.getLevel());
		System.out.println("EXP   : "+this.getEXP());
		System.out.print("HP  : "+this.getHP()+"     ");
		System.out.println("MP :  "+this.getMP());
		System.out.print("STR : "+this.getSTR()+"    ");
		System.out.println("DEF :  "+this.getDEF());
		System.out.println("====================");
		
	}
	//캐릭터가 몬스터 공격해서 잡을때 전투상황
	void fight(Monster monster) {
		System.out.println(this.name + "이(가)"+monster.getName()+"과 전투를 시작했습니다.");
	
		while(true) {
			System.out.println(monster.getName()+"의 hp : "+monster.getHP());
			System.out.println("{1.공격 2.방어 3.회피 4.도망}");
			
			int num = sc.nextInt();
			switch(num) {
			case 1:
				this.attack(monster);
				if(monster.getHP() <= 0) {
					System.out.println(monster.getName()+"과의 전투에서 승리하였습니다.");
					//몬스터잡을때마다 얻는 경험치 수치 정해야함**
					this.getExperience(10);
					return;
				}
				monster.attack(this);
				if(this.getHP() <= 0) {
					System.out.println("사망하셨습니당");
					this.looseEXP();
					return;
				}
				break;
			case 2:
				System.out.println(monster.getName()+"의 공격을 방어합니다");
				if(this.getDEF()-monster.getSTR()>0) {
					System.out.println(monster.getName()+"이(가)"+this.getName()+"을 공격합니다!!");
					System.out.print("MISS!!!!!");
					System.out.println();
				}else {
					System.out.println(monster.getName()+"이(가)"+this.getName()+"을 공격합니다!!");
					System.out.print((-1)*(monster.getSTR()-this.getDEF()));
					System.out.println();
				}
				if(this.getHP() <= 0) {
					System.out.println("사망하셨습니당");
					this.looseEXP();
					return;
				}
				break;
			case 3 :
				System.out.println(monster.getName()+"의 공격을 회피합니다");
				//회피율산정
				//회피못하면 damage받음
				//**********************(-)//
				monster.attack(this);
				if(this.getHP() <= 0) {
					System.err.println("사망하셨습니당");
					this.looseEXP();
					return;
				}
				break;
			case 4:
				System.out.println("전투에서 도망쳤습니다!!ㅜㅜㅜㅜ");
				return;
			}					
		}
	}
	//방어력산정도해야함
	void attack(Monster monster) {
		System.out.print( this.getName()+"이(가)"+monster.getName()+"을 공격합니다!!");	
		System.out.println(this.getSTR());
	
		monster.damaged(this);
	}
	void damaged(Monster monster) {
		System.out.print(this.getName()+"이(가)"+monster.getName()+"에게 공격받습니다.");		
		System.out.println("-"+monster.getSTR());	
		this.setHP(this.getHP() - monster.getSTR());
		
	}
	
	
	void clear(int EXP) {
		System.out.println("전투를 성공적으로 클리어하였습니다.");
		System.out.println(EXP + "의 경험치를 획득하였습니다.");
		getExperience(EXP);
	}
	
	void getExperience(int EXP) {
		this.EXP += EXP;
		if(this.EXP >= 100) {
			System.out.println(name + "의 레벨이 상승합니다." );
			int levelup = this.getLevel()+1;
			this.setLevel(levelup);
			System.out.println("현재 " + this.getName() + "의 레벨은 " + this.getLevel() + "입니다.");
			this.EXP -= 100;
		}
	}
	void looseEXP() {
		//사망시 잃은 EXP - 10프로씩 하락		
		this.setEXP(this.getEXP()-10);
	}
	
	void avoid() {
		System.out.println("공격를 회피합니다.");
	}
	void run() {
		System.out.println("전투에서 도망쳤습니다.");
	}

	//getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEXP() {
		return EXP;
	}
	public void setEXP(int eXP) {
		EXP = eXP;
	}
	
}
