package foodService_inheritance;

public class Customer {
	private String name;
	private int personNum;
	private String time;
	
	
	Customer(int personNum,String time){
		this.name = name;
		this.personNum = personNum;
		this.time = time;
	}
	Customer(String name,int personNum,String time){
		this.name = name;
		this.personNum = personNum;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPersonNum() {
		return personNum;
	}

	public void setPersonNum(int personNum) {
		this.personNum = personNum;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	
	
}
