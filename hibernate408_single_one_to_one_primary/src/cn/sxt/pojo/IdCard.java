package cn.sxt.pojo;

public class IdCard {
	private String id;
	private String address;
	public IdCard() {
	}
	public IdCard(String address) {
		super();
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
