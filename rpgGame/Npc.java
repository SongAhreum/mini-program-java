package rpgGame;

import java.util.Scanner;

public class Npc extends Unit{
	
	private  String name;
	private String quest[];
	private String qestReward[];
	private String words[];
	
	public Npc(String name,String quest[],String qestReward[],String words[]) {
		this.name = name;
		this.quest = quest;
		this.qestReward = qestReward;
		this.words = words;
	}
	
	//해당 인덱스 반환
	public int giveQeust() {
		System.out.println("어떤퀘스트를 받아가고 싶으신가. 번호로 입력");
		System.out.print("{");
		for(int i = 1;i <= quest.length;i++) {
			System.out.print(i+"."+quest[i-1]+" ");
		}
		System.out.println("}");
		Scanner sc = new Scanner(System.in);
		int questIdx = sc.nextInt();  
		return questIdx;
	}	
	public void hi() {
		System.out.println("반갑다 나는"+this.name);
	}
	public void speak() {		
		int num = (int)Math.random()*(this.words.length);
		System.out.println(this.words[num]);
	}
	
	//getter setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getQuest() {
		return quest;
	}
	public void setQuest(String[] quest) {
		this.quest = quest;
	}
	public String[] getQestReward() {
		return qestReward;
	}
	public void setQestReward(String[] qestReward) {
		this.qestReward = qestReward;
	}
	public String[] getWords() {
		return words;
	}
	public void setWords(String[] words) {
		this.words = words;
	}
	
	

	
}
