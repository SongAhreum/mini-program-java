package rpgGame;

import java.util.Arrays;
import java.util.Scanner;

//전직함
public class Wizard extends Character {
	Scanner sc = new Scanner(System.in);
	//한번전직하면 끝이징 final
	private final String classType = "wizard";
	private String[] skillList = {"아이스스피어","파이어볼"}; 
	private int[] skillDamageList = {50,45};
	private int[] skillMPConsumption = {5,4};
	
	
	//생성자
	public Wizard(int level, int HP, int MP, int STR, int DEF, String name, int EXP) {
		super(level, HP, MP, STR, DEF, name, EXP);		
	}
	
	//메서드
	
	//캐릭터가 몬스터 공격해서 잡을때 전투상황
	@Override
	void fight(Monster monster) {
		System.out.println(this.getName() + "이(가)"+monster.getName()+"과 전투를 시작했습니다.");
		while(true) {
			System.out.println(monster.getName()+"의 HP : "+monster.getHP());
			System.out.println("선택 : {1.공격 2.방어 3.회피 4.도망}");
			
			int num = sc.nextInt();
			switch(num) {
			case 1:
				/////////////////////////////////
				System.out.println("{1.일반공격 2.스킬사용}");
				int n = sc.nextInt();
				if(n == 1) {
					this.attack(monster);
					if(monster.getHP() <= 0) {
						System.err.println(monster.getName()+"과의 전투에서 승리하였습니다.");
						//몬스터잡을때마다 얻는 경험치 수치 정해야함**
						this.getExperience(10);
						return;
					}
					monster.attack(this);
					if(this.getHP() <= 0) {
						System.err.println("사망하셨습니당");
						this.looseEXP();
						return;
					}
				} else if(n == 2) {
					attackWithSkill(monster);
					 
					if(monster.getHP() <= 0) {
						System.err.println(monster.getName()+"과의 전투에서 승리하였습니다.");
						//몬스터잡을때마다 얻는 경험치 수치 정해야함**
						this.getExperience(10);
						return;
					}
					monster.attack(this);
					if(this.getHP() <= 0) {
						System.err.println("사망하셨습니당");
						this.looseEXP();
						return;
					}
					
				}			
				break;
			case 2:
				System.out.println(monster.getName()+"의 공격을 방어합니다");
				monster.attack(this);
				if(this.getDEF()-monster.getSTR()>0) {
					int all = this.getHP()+this.getDEF()-monster.getSTR();
					this.setHP(all);
				}else {
					System.out.println("MISS");
				}
				if(this.getHP() <= 0) {
					System.err.println("사망하셨습니당");
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
				System.out.println("전투에서 도망쳤습니다");
				
			}
		
		
		}
		
	}	
		
		
		
		
		
//		//선공
//		System.out.println("스킬을 사용하시겠습니까? y/n" );
//		String how = sc.next();
//		if(how.equals("y")) {		
//			System.out.println();
//			attackWithSkill(monster);			
//		}				
//		System.err.print(this.getSTR());
//		System.out.println("만큼의 피해를 입힙니다.");
//		this.attack(monster);
//		
//		while(true) {
//			
//			if(monster.getHP() <= 0) {
//				System.err.println(monster.getName()+"과의 전투에서 승리하였습니다.");
//				//몬스터잡을때마다 얻는 경험치 수치 정해야함**
//				this.getExperience(10);
//				return;
//			}
//			monster.attack(this);
//			if(this.getHP() <= 0) {
//				System.err.println("사망하셨습니당");
//				this.looseEXP();
//				return;
//			}
//			this.attack(monster);
			
			
	
	void attackWithSkill(Monster monster) {
		
		System.out.println("어떤 공격을 할 지 고르세요");
		//skill list 출력
		printSkillList();	
		//skill 선택
		int n = sc.nextInt();
		this.checkInput(n);
		System.out.println(this.getSkillList(n-1)+"!!!!!!!!!!");
		
		//스킬사용가능여부-마나량체크
//		*******************
		
		
		//해당 스킬공격력을 캐릭터공격력에 적용후 공격함 이후 원복
		int tmp = this.getSTR();
		this.setSTR(skillDamageList[n-1]);
		this.attack(monster);
		this.skillMPConsumption(n);
		this.setSTR(tmp);
	}
	//스킬사용시 mp 소모량 적용
	void skillMPConsumption(int n) {
		this.setMP(getMP()-this.skillMPConsumption[n-1]);
	}
	void printSkillList() {
		System.out.print("[");
		for(int i = 0;i < skillList.length;i++) {
			System.out.print(i+1+"."+skillList[i]+"  ");
		}
		System.out.print("]");
	}
	void checkInput(int n) {
		while(true) {
			if(n > 0 && n <= skillList.length ) {
				break;
			}
			System.out.println("다시입력하세요");
			n = sc.nextInt();
		}
	}
	@Override
	void info() {
		
		System.out.println("========스탯창========");
		System.out.println("이름   :"+this.getName());
		System.out.println("Class :"+this.classType);
		System.out.println("Levle : "+this.getLevel());
		System.out.println("EXP   : "+this.getEXP());
		System.out.print("HP  : "+this.getHP()+"     ");
		System.out.println("MP :  "+this.getMP());
		System.out.print("STR : "+this.getSTR()+"    ");
		System.out.println("DEF :  "+this.getDEF());
		System.out.println("====================");
		
	}
	
	//getter setter
	public String[] getSkillList() {
		return skillList;
	}
	public String getSkillList(int n) {
		return skillList[n];
	}
	public void setSkillList(String[] skillList) {
		this.skillList = skillList;
	}

	public int[] getSkillDamageList() {
		return skillDamageList;
	}

	public void setSkillDamageList(int[] skillDamageList) {
		this.skillDamageList = skillDamageList;
	}

	public int[] getSkillMPConsumption() {
		return skillMPConsumption;
	}

	public void setSkillMPConsumption(int[] skillMPConsumption) {
		this.skillMPConsumption = skillMPConsumption;
	}

	
	
	
	
}
