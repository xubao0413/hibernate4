package cn.sxt.pojo;

public class User {
	private String id;
	private String name;
	private int age;
	private IdCard idCard;
	public User() {
	}
	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public User(String name, int age, IdCard idCard) {
		super();
		this.name = name;
		this.age = age;
		this.idCard = idCard;
	}

	public User(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public IdCard getIdCard() {
		return idCard;
	}
	public void setIdCard(IdCard idCard) {
		this.idCard = idCard;
	}
	
}
