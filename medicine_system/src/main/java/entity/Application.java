package entity;

public class Application {
	
	private int a_id;
	private int id;
	private String name;
	private int num;
	private String account;
	private String a_time;
	private String h_time;
	private int state;//0未处理，1已接受，2已拒绝。
	
	
	public Application(int a_id) {
		this.a_id=a_id;
	}
	
	
	public int getId() {
		return id;
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getA_time() {
		return a_time;
	}


	public void setA_time(String a_time) {
		this.a_time = a_time;
	}


	public String getH_time() {
		return h_time;
	}


	public void setH_time(String h_time) {
		this.h_time = h_time;
	}


	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
